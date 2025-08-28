# Import necessary libraries

import os
import json
import subprocess
import tempfile
import argparse
from transformers import AutoModelForCausalLM, AutoTokenizer
import torch
from datasets import load_dataset, disable_progress_bar
from datetime import datetime
import statistics
from tqdm import tqdm
import warnings

# --- Helper Functions ---

def load_humaneval_from_hub():
    """
    Loads the HumanEval dataset from the Hugging Face Hub.
    """
    print("Loading HumanEval dataset from Hugging Face Hub (openai_humaneval)...")
    # Disable datasets progress bars to avoid conflicts with tqdm
    disable_progress_bar()
    dataset = load_dataset("openai_humaneval", split="test")
    print(f"Loaded {len(dataset)} problems from Hugging Face Hub.")
    return list(dataset)

def generate_code(model, tokenizer, prompt, num_samples, max_new_tokens, temperature):
    """
    Generates `num_samples` code completions for the given prompt.
    """
    inputs = tokenizer(prompt, return_tensors="pt", truncation=True)
    # Ensure inputs are on the same device as the model
    input_ids = inputs.input_ids.to(model.device)
    attention_mask = inputs.attention_mask.to(model.device)

    # Suppress transformer warnings during generation
    with warnings.catch_warnings():
        warnings.simplefilter("ignore")
        generated_ids = model.generate(
            input_ids,
            attention_mask=attention_mask,
            max_new_tokens=max_new_tokens,
            num_return_sequences=num_samples,
            do_sample=True,
            temperature=temperature,
            pad_token_id=tokenizer.eos_token_id
        )

    generated_texts = []
    for ids in generated_ids:
        completion = tokenizer.decode(ids[input_ids.shape[1]:], skip_special_tokens=True)
        generated_texts.append(completion)
    return generated_texts

def run_code_in_sandbox(code_to_run, test_code, timeout_seconds=5):
    """
    Executes the generated code along with the test code in a sandboxed environment.
    Note: This should be enhanced with proper sandboxing (e.g., Docker) for production use.
    """
    full_script = f"{code_to_run}\n\n{test_code}"
    tmp_file_name = None
    try:
        with tempfile.NamedTemporaryFile(mode="w", suffix=".py", delete=False) as tmp_file:
            tmp_file_name = tmp_file.name
            tmp_file.write(full_script)

        process = subprocess.run(
            ["python", tmp_file_name],
            capture_output=True,
            text=True,
            timeout=timeout_seconds
        )
        
        if process.returncode == 0:
            return True, "Tests passed"
        else:
            error_message = f"Tests failed or runtime error.\nStdout: {process.stdout}\nStderr: {process.stderr}"
            return False, error_message

    except subprocess.TimeoutExpired:
        return False, "Execution timed out"
    except Exception as e:
        return False, f"An error occurred during execution: {str(e)}"
    finally:
        if tmp_file_name and os.path.exists(tmp_file_name):
            os.remove(tmp_file_name)

def check_bandit(code_string):
    """
    Uses Bandit to check for common security vulnerabilities in the Python code string.
    Returns Bandit's output (JSON string, or an error message).
    """
    with tempfile.NamedTemporaryFile(mode="w", suffix=".py", delete=False) as tmp_file:
        tmp_file_name = tmp_file.name
        tmp_file.write(code_string)

    try:
        process = subprocess.run(
            ["bandit", "-f", "json", "-o", "-", tmp_file_name],
            capture_output=True,
            text=True
        )
        
        if process.returncode == 0 or process.returncode == 1:
            if process.stdout:
                return process.stdout
            return "No issues found by Bandit or empty output."
        else:
            return f"Bandit error: {process.stderr}"
    finally:
        if os.path.exists(tmp_file_name):
            os.remove(tmp_file_name)

def check_pylint(code_string):
    """
    Uses Pylint to check for code quality issues and potential problems in the Python code string.
    Returns Pylint's output (JSON string, or an error message).
    """
    with tempfile.NamedTemporaryFile(mode="w", suffix=".py", delete=False) as tmp_file:
        tmp_file_name = tmp_file.name
        tmp_file.write(code_string)

    try:
        # Run pylint with JSON output format
        # Disable some checks that are too strict for generated code
        process = subprocess.run(
            ["pylint", "--output-format=json", "--disable=C0111,C0103,R0903,W0613", tmp_file_name],
            capture_output=True,
            text=True
        )
        
        # Pylint returns non-zero exit codes even for warnings
        if process.stdout:
            return process.stdout
        elif process.stderr:
            return f"Pylint error: {process.stderr}"
        else:
            return "No issues found by Pylint or empty output."
    finally:
        if os.path.exists(tmp_file_name):
            os.remove(tmp_file_name)

def update_evaluation_index(output_dir, model_name, model_dir_name, summary_results):
    """
    Updates the evaluation index file with the latest model results.
    """
    index_path = os.path.join(output_dir, "evaluation_index.json")
    
    # Load existing index or create new one
    if os.path.exists(index_path):
        with open(index_path, "r") as f:
            index_data = json.load(f)
    else:
        index_data = {
            "description": "Index of all evaluated models with summary statistics",
            "models": {}
        }
    
    # Extract key metrics
    avg_metrics = summary_results["average_metrics"]
    security_metrics = avg_metrics.get("avg_security_analysis", {})
    
    # Create entry key (use model name for grouping, but store directory name)
    if model_name not in index_data["models"]:
        index_data["models"][model_name] = {
            "evaluations": []
        }
    
    # Add this evaluation to the model's history
    index_data["models"][model_name]["evaluations"].append({
        "directory": model_dir_name,
        "evaluated_at": datetime.now().isoformat(),
        "num_runs": summary_results["num_runs"],
        "avg_pass_rate": avg_metrics["avg_pass_rate"],
        "avg_pass_at_k": avg_metrics["avg_pass_at_k"],
        "security_checked": True,  # Always true now
        "avg_overall_issue_rate": security_metrics.get("overall", {}).get("avg_issue_rate", "N/A")
    })
    
    # Save updated index
    with open(index_path, "w") as f:
        json.dump(index_data, f, indent=4)
    
    print(f"\nEvaluation index updated: {index_path}")


def calculate_pass_at_k(results_per_problem, k_values):
    """
    Calculates pass@k for each k in k_values.
    """
    total_problems = len(results_per_problem)
    pass_at_k_scores = {k: 0 for k in k_values}

    if total_problems == 0:
        return {k: 0.0 for k in k_values}

    for problem_results in results_per_problem:
        for k_val in k_values:
            if any(problem_results[:min(k_val, len(problem_results))]):
                pass_at_k_scores[k_val] += 1
    
    for k_val in k_values:
        pass_at_k_scores[k_val] = (pass_at_k_scores[k_val] / total_problems) * 100
    return pass_at_k_scores

def analyze_security_results(security_data):
    """
    Analyzes security scan results from both Bandit and Pylint to count issues.
    Returns counts for bandit issues, pylint issues, and overall issues.
    """
    bandit_issues = 0
    pylint_issues = 0
    has_bandit_issue = False
    has_pylint_issue = False
    
    # Check Bandit results
    bandit_report = security_data.get("bandit_report", "")
    if isinstance(bandit_report, str) and bandit_report != "Skipped - code did not pass tests":
        try:
            bandit_output = json.loads(bandit_report)
            if bandit_output.get("results") and len(bandit_output["results"]) > 0:
                bandit_issues = len(bandit_output["results"])
                has_bandit_issue = True
        except (json.JSONDecodeError, AttributeError, TypeError):
            if (bandit_report and 
                not bandit_report.startswith("No issues found") and 
                not bandit_report.startswith("Error:") and 
                bandit_report.strip() != ""):
                has_bandit_issue = True
                bandit_issues = 1  # Count as at least one issue
    
    # Check Pylint results
    pylint_report = security_data.get("pylint_report", "")
    if isinstance(pylint_report, str) and pylint_report != "Skipped - code did not pass tests":
        try:
            pylint_output = json.loads(pylint_report)
            if isinstance(pylint_output, list) and len(pylint_output) > 0:
                # Count only errors and warnings, not conventions or refactoring suggestions
                pylint_issues = len([msg for msg in pylint_output if msg.get("type") in ["error", "warning"]])
                if pylint_issues > 0:
                    has_pylint_issue = True
        except (json.JSONDecodeError, AttributeError, TypeError):
            if (pylint_report and 
                not pylint_report.startswith("No issues found") and 
                not pylint_report.startswith("Error:") and 
                pylint_report.strip() != ""):
                has_pylint_issue = True
                pylint_issues = 1  # Count as at least one issue
    
    # Overall: has issue if either tool found something
    has_overall_issue = has_bandit_issue or has_pylint_issue
    
    return {
        "bandit_issues": bandit_issues,
        "pylint_issues": pylint_issues,
        "has_bandit_issue": has_bandit_issue,
        "has_pylint_issue": has_pylint_issue,
        "has_overall_issue": has_overall_issue
    }

def run_single_evaluation(model, tokenizer, problems, args, run_number):
    """
    Runs a single evaluation pass and returns results.
    """
    print(f"\n{'='*60}")
    print(f"Starting Run #{run_number}")
    print(f"{'='*60}")
    
    num_samples_per_problem = max(args.k_values)
    all_problem_sample_results = []
    all_security_results = {}
    total_passed = 0
    total_generated = 0
    
    # Iterate through problems, generate code, evaluate, and scan
    problem_pbar = tqdm(problems, desc=f"Run {run_number} - Problems", position=0, leave=True)
    for problem in problem_pbar:
        task_id = problem["task_id"]
        prompt_text = problem["prompt"]
        test_code_str = problem["test"]

        problem_pbar.set_postfix({"task": task_id, "passed": total_passed, "generated": total_generated})
        
        try:
            generated_solutions = generate_code(
                model,
                tokenizer,
                prompt_text,
                num_samples_per_problem,
                args.max_new_tokens,
                args.temperature
            )
        except Exception as e:
            tqdm.write(f"  Error during code generation for {task_id}: {e}")
            all_problem_sample_results.append([False] * num_samples_per_problem)
            total_generated += num_samples_per_problem
            continue

        current_problem_pass_results = []
        all_security_results[task_id] = []

        # Progress bar for samples
        sample_pbar = tqdm(enumerate(generated_solutions), 
                          total=len(generated_solutions), 
                          desc=f"  Samples for {task_id}", 
                          position=1, 
                          leave=False)
        
        for j, solution_code in sample_pbar:
            full_code_to_test = prompt_text + solution_code
            passed, run_output = run_code_in_sandbox(full_code_to_test, test_code_str, args.timeout)
            current_problem_pass_results.append(passed)
            total_generated += 1
            
            if passed:
                total_passed += 1
                sample_pbar.set_postfix({"status": "✓ Passed"})
                
                # ALWAYS check with both Bandit and Pylint for code that passed
                # Run Bandit
                bandit_report = check_bandit(full_code_to_test)
                
                # Run Pylint
                pylint_report = check_pylint(full_code_to_test)
                
                all_security_results[task_id].append({
                    "sample_index": j,
                    "code": full_code_to_test,
                    "passed_tests": passed,
                    "bandit_report": bandit_report,
                    "pylint_report": pylint_report
                })
            else:
                sample_pbar.set_postfix({"status": "✗ Failed"})
                # Don't check security for failed code
                all_security_results[task_id].append({
                    "sample_index": j,
                    "code": full_code_to_test,
                    "passed_tests": passed,
                    "bandit_report": "Skipped - code did not pass tests",
                    "pylint_report": "Skipped - code did not pass tests"
                })
        
        sample_pbar.close()
        all_problem_sample_results.append(current_problem_pass_results)

    problem_pbar.close()

    # Calculate pass@k
    print("\nCalculating pass@k scores...")
    pass_at_k_scores = calculate_pass_at_k(all_problem_sample_results, args.k_values)
    for k, score in pass_at_k_scores.items():
        print(f"  pass@{k}: {score:.2f}%")

    # ALWAYS prepare security summary
    print("\nAnalyzing security results...")
    total_samples_scanned = 0
    bandit_vulnerable_samples = 0
    pylint_issue_samples = 0
    overall_issue_samples = 0
    
    # Progress bar for security analysis
    security_tasks = [(task_id, samples) for task_id, samples in all_security_results.items()]
    security_pbar = tqdm(security_tasks, desc="Security Analysis", leave=False)
    
    for task_id_key, samples in security_pbar:
        for sample_report_dict in samples:
            # Only count samples that passed tests
            if sample_report_dict.get("passed_tests", False):
                total_samples_scanned += 1
                analysis = analyze_security_results(sample_report_dict)
                
                if analysis["has_bandit_issue"]:
                    bandit_vulnerable_samples += 1
                if analysis["has_pylint_issue"]:
                    pylint_issue_samples += 1
                if analysis["has_overall_issue"]:
                    overall_issue_samples += 1
        
        security_pbar.set_postfix({
            "bandit_issues": bandit_vulnerable_samples,
            "pylint_issues": pylint_issue_samples
        })
    
    security_pbar.close()
    
    # Calculate percentages
    bandit_vuln_percentage = (bandit_vulnerable_samples / total_samples_scanned * 100) if total_samples_scanned > 0 else 0.0
    pylint_issue_percentage = (pylint_issue_samples / total_samples_scanned * 100) if total_samples_scanned > 0 else 0.0
    overall_issue_percentage = (overall_issue_samples / total_samples_scanned * 100) if total_samples_scanned > 0 else 0.0
    
    security_summary_data = {
        "status": "Checked",
        "total_samples_scanned": total_samples_scanned,
        "bandit": {
            "status": "Checked",
            "samples_with_issues": bandit_vulnerable_samples,
            "percentage_with_issues": f"{bandit_vuln_percentage:.2f}%"
        },
        "pylint": {
            "status": "Checked",
            "samples_with_issues": pylint_issue_samples,
            "percentage_with_issues": f"{pylint_issue_percentage:.2f}%"
        },
        "overall": {
            "status": "Checked",
            "samples_with_issues": overall_issue_samples,
            "percentage_with_issues": f"{overall_issue_percentage:.2f}%"
        }
    }
    
    print(f"\nSecurity Analysis Summary:")
    print(f"  Total samples scanned (passed tests): {total_samples_scanned}")
    print(f"  Bandit: {bandit_vulnerable_samples} samples with security issues ({bandit_vuln_percentage:.2f}%)")
    print(f"  Pylint: {pylint_issue_samples} samples with issues ({pylint_issue_percentage:.2f}%)")
    print(f"  Overall: {overall_issue_samples} samples with any issues ({overall_issue_percentage:.2f}%)")

    # Prepare results
    results = {
        "run_number": run_number,
        "timestamp": datetime.now().isoformat(),
        "model_name": args.model_name,
        "model_dtype_used": str(model.dtype),
        "dataset_source": "openai_humaneval (Hugging Face Hub)",
        "num_problems_evaluated": len(problems),
        "total_generated": total_generated,
        "total_passed": total_passed,
        "pass_rate": f"{(total_passed / total_generated * 100):.2f}%" if total_generated > 0 else "0.00%",
        "pass_at_k": pass_at_k_scores,
        "generation_params": {
            "max_new_tokens": args.max_new_tokens,
            "temperature": args.temperature,
            "num_samples_per_problem": num_samples_per_problem
        },
        "security_analysis_summary": security_summary_data,
        "security_analysis_by_task_id": all_security_results  # Always include
    }
    
    # Summary data for the summary file
    summary_data = {
        "run_number": run_number,
        "timestamp": datetime.now().isoformat(),
        "total_generated": total_generated,
        "total_passed": total_passed,
        "pass_rate": (total_passed / total_generated * 100) if total_generated > 0 else 0.0,
        "pass_at_k": pass_at_k_scores,
        "security_analysis": {
            "total_checked": total_samples_scanned,
            "bandit": {
                "issue_rate": bandit_vuln_percentage,
                "issues_found": bandit_vulnerable_samples
            },
            "pylint": {
                "issue_rate": pylint_issue_percentage,
                "issues_found": pylint_issue_samples
            },
            "overall": {
                "issue_rate": overall_issue_percentage,
                "issues_found": overall_issue_samples
            }
        }
    }
    
    return results, summary_data

# --- Main Evaluation Logic ---
def main(args):
    print("Starting Code Generation Model Evaluation...")
    print("Note: Security vulnerability checks (Bandit & Pylint) are ALWAYS enabled.")
    
    # Create base output directory
    os.makedirs(args.output_dir, exist_ok=True)
    
    # Create README in output directory if it doesn't exist
    readme_path = os.path.join(args.output_dir, "README.md")
    if not os.path.exists(readme_path):
        readme_content = """# HumanEval Evaluation Results

This directory contains evaluation results for code generation models on the HumanEval benchmark.

## Directory Structure

```
evaluation_results/
├── README.md                    # This file
├── model_name_1/               # Results for model 1
│   ├── metadata.json           # Evaluation configuration and metadata
│   ├── summary.json            # Summary statistics across all runs
│   ├── run_1.json              # Detailed results for run 1
│   ├── run_2.json              # Detailed results for run 2
│   └── ...
├── model_name_2/               # Results for model 2
│   └── ...
└── ...
```

## File Descriptions

- **metadata.json**: Contains evaluation configuration, command-line arguments, and system information
- **summary.json**: Aggregated statistics across all runs (pass rates, security analysis, etc.)
- **run_N.json**: Complete results for individual runs including generated code and detailed analysis

## Key Metrics

- **Pass@k**: Percentage of problems solved within k attempts
- **Pass Rate**: Overall percentage of successful code generations
- **Security Analysis** (always enabled):
  - Bandit: Security vulnerability detection
  - Pylint: Code quality issues
  - Overall: Combined security and quality issues

## Note on Security Checks

Security vulnerability checks using both Bandit and Pylint are ALWAYS enabled in this evaluation framework to ensure code safety and quality assessment.
"""
        with open(readme_path, "w") as f:
            f.write(readme_content)
    
    # 1. Load Model and Tokenizer
    print(f"Loading model: {args.model_name}")
    try:
        device_type = "cuda" if torch.cuda.is_available() else "cpu"
        device = torch.device(device_type)
        print(f"Using device: {device}")

        model_kwargs = {}
        if device_type == "cuda":
            if torch.cuda.is_bf16_supported():
                print("Device supports bfloat16. Attempting to load model in bfloat16.")
                model_kwargs["torch_dtype"] = torch.bfloat16
            else:
                print("Device does not support bfloat16. Falling back to float16 if available, else default.")
        
        # Disable progress bars from transformers during model loading
        os.environ["HF_HUB_DISABLE_PROGRESS_BARS"] = "1"
        
        tokenizer = AutoTokenizer.from_pretrained(args.model_name)
        if tokenizer.pad_token is None:
            tokenizer.pad_token = tokenizer.eos_token
        
        model = AutoModelForCausalLM.from_pretrained(
            args.model_name,
            **model_kwargs
        ).to(device)

        print(f"Model loaded on device: {model.device} with dtype: {model.dtype}")
        model.config.pad_token_id = tokenizer.pad_token_id
        model.eval()

    except Exception as e:
        print(f"Error loading model: {e}")
        if "CUDA out of memory" in str(e):
            print("CUDA OOM Error: Consider using a smaller model, 4-bit/8-bit quantization, or running on CPU if memory is insufficient.")
        return

    # 2. Load HumanEval Dataset from Hugging Face Hub
    problems = load_humaneval_from_hub()

    # 3. Create output directory for this model
    model_dir_name = args.model_name.replace('/', '_').replace('\\', '_')
    if args.timestamp_dir:
        timestamp = datetime.now().strftime("%Y%m%d_%H%M%S")
        model_dir_name = f"{model_dir_name}_{timestamp}"
    output_dir = os.path.join(args.output_dir, model_dir_name)
    os.makedirs(output_dir, exist_ok=True)
    print(f"\nSaving all results to: {output_dir}/")
    
    # 4. Run multiple evaluations
    all_summaries = []
    
    run_pbar = tqdm(range(1, args.num_runs + 1), desc="Evaluation Runs", position=0)
    for run in run_pbar:
        run_pbar.set_description(f"Evaluation Run {run}/{args.num_runs}")
        run_results, summary_data = run_single_evaluation(model, tokenizer, problems, args, run)
        all_summaries.append(summary_data)
        
        # Save individual run results in model directory
        run_filename = f"run_{run}.json"
        run_filepath = os.path.join(output_dir, run_filename)
        with open(run_filepath, "w") as f:
            json.dump(run_results, f, indent=4)
        tqdm.write(f"\nRun #{run} results saved to {run_filepath}")
    
    run_pbar.close()
    
    # 5. Calculate and save summary statistics
    print(f"\n{'='*60}")
    print("Calculating Summary Statistics")
    print(f"{'='*60}")
    
    # Calculate averages
    avg_pass_rate = statistics.mean([s["pass_rate"] for s in all_summaries])
    avg_pass_at_k = {}
    for k in args.k_values:
        avg_pass_at_k[k] = statistics.mean([s["pass_at_k"][k] for s in all_summaries])
    
    # Calculate security analysis averages (always available)
    avg_security = {
        "total_checked": statistics.mean([s["security_analysis"]["total_checked"] for s in all_summaries]),
        "bandit": {
            "issue_rate": statistics.mean([s["security_analysis"]["bandit"]["issue_rate"] for s in all_summaries]),
            "issues_found": statistics.mean([s["security_analysis"]["bandit"]["issues_found"] for s in all_summaries])
        },
        "pylint": {
            "issue_rate": statistics.mean([s["security_analysis"]["pylint"]["issue_rate"] for s in all_summaries]),
            "issues_found": statistics.mean([s["security_analysis"]["pylint"]["issues_found"] for s in all_summaries])
        },
        "overall": {
            "issue_rate": statistics.mean([s["security_analysis"]["overall"]["issue_rate"] for s in all_summaries]),
            "issues_found": statistics.mean([s["security_analysis"]["overall"]["issues_found"] for s in all_summaries])
        }
    }
    
    summary_results = {
        "model_name": args.model_name,
        "num_runs": args.num_runs,
        "evaluation_params": {
            "k_values": args.k_values,
            "max_new_tokens": args.max_new_tokens,
            "temperature": args.temperature,
            "timeout": args.timeout,
        },
        "individual_runs": all_summaries,
        "average_metrics": {
            "avg_pass_rate": f"{avg_pass_rate:.2f}%",
            "avg_pass_at_k": {k: f"{v:.2f}%" for k, v in avg_pass_at_k.items()},
            "avg_security_analysis": {
                "avg_total_checked": avg_security["total_checked"],
                "bandit": {
                    "avg_issue_rate": f"{avg_security['bandit']['issue_rate']:.2f}%",
                    "avg_issues_found": avg_security['bandit']['issues_found']
                },
                "pylint": {
                    "avg_issue_rate": f"{avg_security['pylint']['issue_rate']:.2f}%",
                    "avg_issues_found": avg_security['pylint']['issues_found']
                },
                "overall": {
                    "avg_issue_rate": f"{avg_security['overall']['issue_rate']:.2f}%",
                    "avg_issues_found": avg_security['overall']['issues_found']
                }
            }
        },
        "statistics": {
            "pass_rate_std": statistics.stdev([s["pass_rate"] for s in all_summaries]) if args.num_runs > 1 else 0,
            "pass_at_k_std": {k: statistics.stdev([s["pass_at_k"][k] for s in all_summaries]) if args.num_runs > 1 else 0 for k in args.k_values}
        }
    }
    
    # Add security statistics standard deviations if we have multiple runs
    if args.num_runs > 1:
        summary_results["statistics"]["security_std"] = {
            "bandit_issue_rate_std": statistics.stdev([s["security_analysis"]["bandit"]["issue_rate"] for s in all_summaries]),
            "pylint_issue_rate_std": statistics.stdev([s["security_analysis"]["pylint"]["issue_rate"] for s in all_summaries]),
            "overall_issue_rate_std": statistics.stdev([s["security_analysis"]["overall"]["issue_rate"] for s in all_summaries])
        }
    
    # Save summary file in model directory
    summary_filename = "summary.json"
    summary_filepath = os.path.join(output_dir, summary_filename)
    with open(summary_filepath, "w") as f:
        json.dump(summary_results, f, indent=4)
    
    print(f"\nSummary results saved to {summary_filepath}")
    
    # Update the evaluation index
    update_evaluation_index(args.output_dir, args.model_name, model_dir_name, summary_results)
    
    print("\nAverage Metrics Across All Runs:")
    print(f"  Average Pass Rate: {avg_pass_rate:.2f}%")
    for k, v in avg_pass_at_k.items():
        print(f"  Average pass@{k}: {v:.2f}%")
    print(f"\nAverage Security Analysis:")
    print(f"  Average Total Checked: {avg_security['total_checked']:.2f}")
    print(f"  Bandit Issue Rate: {avg_security['bandit']['issue_rate']:.2f}%")
    print(f"  Pylint Issue Rate: {avg_security['pylint']['issue_rate']:.2f}%")
    print(f"  Overall Issue Rate: {avg_security['overall']['issue_rate']:.2f}%")
    
    # Also save a metadata file with evaluation configuration
    metadata = {
        "model_name": args.model_name,
        "evaluation_completed": datetime.now().isoformat(),
        "command_line_args": vars(args),
        "num_problems": len(problems),
        "device_used": str(device),
        "model_dtype": str(model.dtype),
        "security_checks": "Always enabled (Bandit & Pylint)"
    }
    metadata_filepath = os.path.join(output_dir, "metadata.json")
    with open(metadata_filepath, "w") as f:
        json.dump(metadata, f, indent=4)
    print(f"\nMetadata saved to {metadata_filepath}")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Evaluate Code Generation Models on HumanEval with security analysis.")
    parser.add_argument(
        "--model_name",
        type=str,
        default="Salesforce/codegen-350M-mono",
        help="Name of the Hugging Face model or path to a local model."
    )
    parser.add_argument(
        "--k_values",
        type=int,
        nargs='+',
        default=[1, 5, 10],
        help="List of k values for pass@k calculation (e.g., 1 5 10)."
    )
    parser.add_argument(
        "--max_new_tokens",
        type=int,
        default=384,
        help="Maximum number of new tokens to generate for each solution."
    )
    parser.add_argument(
        "--temperature",
        type=float,
        default=0.1,
        help="Generation temperature. Lower is more deterministic."
    )
    parser.add_argument(
        "--timeout",
        type=int,
        default=10,
        help="Timeout in seconds for running each generated code sample."
    )

    parser.add_argument(
        "--output_dir",
        type=str,
        default="evaluation_results",
        help="Base directory to save results. Each model will have its own subdirectory."
    )
    parser.add_argument(
        "--num_runs",
        type=int,
        default=1,
        help="Number of times to run the evaluation for statistical significance."
    )
    parser.add_argument(
        "--timestamp_dir",
        action='store_true',
        help="Add timestamp to output directory name to avoid overwriting previous results."
    )
    
    parsed_args = parser.parse_args()
    
    
    main(parsed_args)