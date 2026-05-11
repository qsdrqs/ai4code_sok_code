#!/usr/bin/env python3
"""
SecurityEval Benchmark Evaluation Script

Evaluates multiple models on the SecurityEval dataset (s2e-lab/SecurityEval),
which contains 121 security-sensitive code generation prompts covering 69 CWEs.
Generated code is analyzed with Bandit for security vulnerabilities.

Output:
  - 1 summary file comparing all models
  - 1 detailed file per model

Usage:
  python eval_security_eval.py \
      --model_names model_A model_B model_C \
      --num_samples 5 \
      --output_dir security_eval_results
"""

import os
import json
import subprocess
import tempfile
import argparse
import re
import warnings
import statistics
from datetime import datetime
from collections import Counter, defaultdict

import torch
from transformers import AutoModelForCausalLM, AutoTokenizer
from datasets import load_dataset, disable_progress_bar
from tqdm import tqdm


# =============================================================================
# Helper Functions
# =============================================================================

def load_security_eval():
    """Load the SecurityEval dataset from HuggingFace Hub."""
    print("Loading SecurityEval dataset from HuggingFace Hub (s2e-lab/SecurityEval)...")
    disable_progress_bar()
    dataset = load_dataset("s2e-lab/SecurityEval", split="train")
    print(f"Loaded {len(dataset)} prompts from SecurityEval.")
    return list(dataset)


def extract_cwe_from_id(prompt_id):
    """Extract CWE number from the prompt ID, e.g., 'CWE-020_author_1.py' -> 'CWE-020'."""
    match = re.match(r"(CWE-\d+)", prompt_id)
    return match.group(1) if match else "Unknown"


def build_chat_prompt(tokenizer, prompt_text):
    """
    Build a prompt using the model's chat template.
    Falls back to a simple format if chat template is not available.
    """
    messages = [
        {"role": "user", "content": f"Write Python code for the following task. "
                                     f"Return only the code without any explanation.\n\n{prompt_text}"}
    ]
    try:
        formatted = tokenizer.apply_chat_template(
            messages, tokenize=False, add_generation_prompt=True
        )
        return formatted
    except Exception:
        # Fallback for models without chat template
        return f"# Task: {prompt_text}\n# Solution:\n"


def generate_code(model, tokenizer, prompt, num_samples, max_new_tokens, temperature):
    """Generate `num_samples` code completions for the given prompt."""
    inputs = tokenizer(prompt, return_tensors="pt", truncation=True)
    input_ids = inputs.input_ids.to(model.device)
    attention_mask = inputs.attention_mask.to(model.device)

    with warnings.catch_warnings():
        warnings.simplefilter("ignore")
        generated_ids = model.generate(
            input_ids,
            attention_mask=attention_mask,
            max_new_tokens=max_new_tokens,
            num_return_sequences=num_samples,
            do_sample=True if temperature > 0 else False,
            temperature=temperature if temperature > 0 else None,
            pad_token_id=tokenizer.eos_token_id,
        )

    generated_texts = []
    for ids in generated_ids:
        completion = tokenizer.decode(ids[input_ids.shape[1]:], skip_special_tokens=True)
        generated_texts.append(completion)
    return generated_texts


def extract_python_code(raw_output):
    """
    Extract Python code from model output.
    Handles markdown code blocks and raw code.
    """
    # Try to extract from ```python ... ``` blocks
    pattern = r"```(?:python)?\s*\n(.*?)```"
    matches = re.findall(pattern, raw_output, re.DOTALL)
    if matches:
        return matches[0].strip()

    # Try to extract from ``` ... ``` blocks
    pattern = r"```\s*\n(.*?)```"
    matches = re.findall(pattern, raw_output, re.DOTALL)
    if matches:
        return matches[0].strip()

    # Return raw output stripped
    return raw_output.strip()


def build_full_code_for_analysis(prompt_text, extracted_code):
    """
    Combine the prompt's code context with the generated code for Bandit analysis.

    SecurityEval prompts often contain imports and function signatures
    (e.g., 'import yaml\\ndef yaml_load(filename):\\n    ...').
    Some models generate complete self-contained code (repeating the imports),
    while others generate only the continuation (function body without imports).

    If the generated code already contains the prompt's imports, use it as-is.
    Otherwise, prepend the prompt text to provide the full code context,
    so that Bandit can detect issues like 'import pickle' -> 'pickle.loads()'.
    """
    if not extracted_code.strip():
        return extracted_code

    # Extract import lines from the prompt
    prompt_imports = set()
    for line in prompt_text.split("\n"):
        stripped = line.strip()
        if stripped.startswith("import ") or stripped.startswith("from "):
            # Normalize: take the module name
            prompt_imports.add(stripped)

    # If no imports in prompt, just return extracted code
    if not prompt_imports:
        return extracted_code

    # Check if extracted code already contains those imports
    code_lines = set(line.strip() for line in extracted_code.split("\n"))
    missing_imports = prompt_imports - code_lines

    if not missing_imports:
        # Generated code is self-contained
        return extracted_code

    # Prepend prompt text to generated code
    return prompt_text.rstrip() + "\n" + extracted_code


def check_bandit(code_string):
    """
    Run Bandit on the given code string and return parsed JSON results.
    Returns a dict with 'raw_json' (str) and 'results' (list of issues).
    """
    with tempfile.NamedTemporaryFile(mode="w", suffix=".py", delete=False) as tmp_file:
        tmp_file_name = tmp_file.name
        tmp_file.write(code_string)

    try:
        process = subprocess.run(
            ["bandit", "-f", "json", "-o", "-", tmp_file_name],
            capture_output=True,
            text=True,
        )

        if process.returncode in (0, 1):
            if process.stdout:
                try:
                    parsed = json.loads(process.stdout)
                    return {
                        "raw_json": process.stdout,
                        "results": parsed.get("results", []),
                        "metrics": parsed.get("metrics", {}),
                        "error": None,
                    }
                except json.JSONDecodeError:
                    return {"raw_json": process.stdout, "results": [], "metrics": {}, "error": "JSON parse error"}
            return {"raw_json": "", "results": [], "metrics": {}, "error": None}
        else:
            return {"raw_json": "", "results": [], "metrics": {}, "error": f"Bandit error: {process.stderr}"}
    finally:
        if os.path.exists(tmp_file_name):
            os.remove(tmp_file_name)


def analyze_bandit_results(bandit_results):
    """
    Analyze Bandit results and return structured summary.
    """
    issues = bandit_results.get("results", [])
    if not issues:
        return {
            "total_issues": 0,
            "by_severity": {},
            "by_confidence": {},
            "by_test_id": {},
            "by_cwe": {},
            "issue_details": [],
        }

    severity_counts = Counter()
    confidence_counts = Counter()
    test_id_counts = Counter()
    cwe_counts = Counter()
    details = []

    for issue in issues:
        sev = issue.get("issue_severity", "UNKNOWN")
        conf = issue.get("issue_confidence", "UNKNOWN")
        tid = issue.get("test_id", "UNKNOWN")
        tname = issue.get("test_name", "UNKNOWN")
        cwe_info = issue.get("issue_cwe", {})
        cwe_id = f"CWE-{cwe_info.get('id', 'Unknown')}" if cwe_info else "Unknown"

        severity_counts[sev] += 1
        confidence_counts[conf] += 1
        test_id_counts[f"{tid}:{tname}"] += 1
        cwe_counts[cwe_id] += 1

        details.append({
            "test_id": tid,
            "test_name": tname,
            "severity": sev,
            "confidence": conf,
            "cwe": cwe_id,
            "issue_text": issue.get("issue_text", ""),
            "line_number": issue.get("line_number", -1),
        })

    return {
        "total_issues": len(issues),
        "by_severity": dict(severity_counts),
        "by_confidence": dict(confidence_counts),
        "by_test_id": dict(test_id_counts),
        "by_cwe": dict(cwe_counts),
        "issue_details": details,
    }


# =============================================================================
# Per-Model Evaluation
# =============================================================================

def evaluate_single_model(model_name, prompts, args):
    """
    Evaluate a single model on all SecurityEval prompts.
    Returns (detailed_results, model_summary).
    """
    print(f"\n{'='*70}")
    print(f"Evaluating model: {model_name}")
    print(f"{'='*70}")

    # --- Load model ---
    device_type = "cuda" if torch.cuda.is_available() else "cpu"
    device = torch.device(device_type)
    print(f"Using device: {device}")

    model_kwargs = {}
    if device_type == "cuda" and torch.cuda.is_bf16_supported():
        model_kwargs["torch_dtype"] = torch.bfloat16
        print("Loading model in bfloat16.")

    os.environ["HF_HUB_DISABLE_PROGRESS_BARS"] = "1"

    tokenizer = AutoTokenizer.from_pretrained(model_name)
    if tokenizer.pad_token is None:
        tokenizer.pad_token = tokenizer.eos_token

    model = AutoModelForCausalLM.from_pretrained(model_name, **model_kwargs).to(device)
    model.config.pad_token_id = tokenizer.pad_token_id
    model.eval()
    model_dtype = str(model.dtype)
    print(f"Model loaded. dtype={model_dtype}, device={model.device}")

    # --- Evaluate all prompts ---
    prompt_results = []
    total_samples = 0
    total_with_issues = 0

    # Aggregate counters across all samples
    total_empty = 0
    agg_severity = Counter()
    agg_test_id = Counter()
    agg_cwe = Counter()
    per_cwe_category_samples = defaultdict(lambda: {"total": 0, "with_issues": 0})

    pbar = tqdm(prompts, desc=f"Evaluating {model_name.split('/')[-1]}")
    for prompt_data in pbar:
        prompt_id = prompt_data["ID"]
        prompt_text = prompt_data["Prompt"]
        cwe_category = extract_cwe_from_id(prompt_id)

        # Build prompt based on mode
        if args.prompt_mode == "completion":
            # Code completion mode: use raw prompt as code prefix (like HumanEval)
            final_prompt = prompt_text
        else:
            # Chat mode: wrap in chat template with instruction
            final_prompt = build_chat_prompt(tokenizer, prompt_text)

        # Generate code samples
        try:
            raw_outputs = generate_code(
                model, tokenizer, final_prompt,
                num_samples=args.num_samples,
                max_new_tokens=args.max_new_tokens,
                temperature=args.temperature,
            )
        except Exception as e:
            tqdm.write(f"  Error generating for {prompt_id}: {e}")
            prompt_results.append({
                "prompt_id": prompt_id,
                "cwe_category": cwe_category,
                "prompt_text": prompt_text,
                "samples": [],
                "error": str(e),
            })
            continue

        # Analyze each sample
        sample_results = []
        for idx, raw_output in enumerate(raw_outputs):
            if args.prompt_mode == "completion":
                # In completion mode, output is direct continuation of the prompt
                code = raw_output
                full_code = prompt_text + code
            else:
                # In chat mode, extract code from markdown blocks etc.
                code = extract_python_code(raw_output)
                full_code = build_full_code_for_analysis(prompt_text, code)
            bandit_out = check_bandit(full_code)
            analysis = analyze_bandit_results(bandit_out)

            is_empty = not code.strip()
            has_issue = analysis["total_issues"] > 0
            total_samples += 1
            if is_empty:
                total_empty += 1
            per_cwe_category_samples[cwe_category]["total"] += 1

            if has_issue:
                total_with_issues += 1
                per_cwe_category_samples[cwe_category]["with_issues"] += 1
                # Accumulate aggregates
                for sev, cnt in analysis["by_severity"].items():
                    agg_severity[sev] += cnt
                for tid, cnt in analysis["by_test_id"].items():
                    agg_test_id[tid] += cnt
                for cwe, cnt in analysis["by_cwe"].items():
                    agg_cwe[cwe] += cnt

            sample_results.append({
                "sample_index": idx,
                "raw_output": raw_output,
                "extracted_code": code,
                "full_code_for_analysis": full_code,
                "has_bandit_issue": has_issue,
                "bandit_analysis": analysis,
                "bandit_raw_json": bandit_out.get("raw_json", ""),
            })

        prompt_results.append({
            "prompt_id": prompt_id,
            "cwe_category": cwe_category,
            "prompt_text": prompt_text,
            "num_samples": len(sample_results),
            "samples_with_issues": sum(1 for s in sample_results if s["has_bandit_issue"]),
            "samples": sample_results,
        })

        pbar.set_postfix({
            "issues": f"{total_with_issues}/{total_samples}",
            "rate": f"{total_with_issues / total_samples * 100:.1f}%" if total_samples else "N/A",
        })

    pbar.close()

    # --- Free GPU memory ---
    del model
    if torch.cuda.is_available():
        torch.cuda.empty_cache()

    # --- Build per-CWE-category summary ---
    cwe_category_summary = {}
    for cwe_cat, counts in sorted(per_cwe_category_samples.items()):
        total = counts["total"]
        with_issues = counts["with_issues"]
        cwe_category_summary[cwe_cat] = {
            "total_samples": total,
            "samples_with_issues": with_issues,
            "issue_rate": f"{with_issues / total * 100:.2f}%" if total > 0 else "0.00%",
        }

    # --- Build model summary ---
    total_valid = total_samples - total_empty
    issue_rate = (total_with_issues / total_samples * 100) if total_samples > 0 else 0.0
    valid_issue_rate = (total_with_issues / total_valid * 100) if total_valid > 0 else 0.0
    empty_rate = (total_empty / total_samples * 100) if total_samples > 0 else 0.0
    model_summary = {
        "model_name": model_name,
        "model_dtype": model_dtype,
        "timestamp": datetime.now().isoformat(),
        "num_prompts": len(prompts),
        "num_samples_per_prompt": args.num_samples,
        "total_samples": total_samples,
        "total_empty": total_empty,
        "total_valid": total_valid,
        "empty_rate": round(empty_rate, 2),
        "empty_rate_str": f"{empty_rate:.2f}%",
        "total_with_issues": total_with_issues,
        "overall_issue_rate": round(issue_rate, 2),
        "overall_issue_rate_str": f"{issue_rate:.2f}%",
        "valid_issue_rate": round(valid_issue_rate, 2),
        "valid_issue_rate_str": f"{valid_issue_rate:.2f}%",
        "generation_params": {
            "max_new_tokens": args.max_new_tokens,
            "temperature": args.temperature,
            "num_samples": args.num_samples,
        },
        "aggregate_by_severity": dict(agg_severity),
        "aggregate_by_test_id": dict(agg_test_id.most_common()),
        "aggregate_by_cwe": dict(agg_cwe.most_common()),
        "per_cwe_category": cwe_category_summary,
    }

    # --- Build detailed results ---
    detailed_results = {
        "model_name": model_name,
        "model_dtype": model_dtype,
        "timestamp": datetime.now().isoformat(),
        "dataset": "s2e-lab/SecurityEval",
        "num_prompts": len(prompts),
        "num_samples_per_prompt": args.num_samples,
        "generation_params": {
            "max_new_tokens": args.max_new_tokens,
            "temperature": args.temperature,
        },
        "summary": model_summary,
        "prompt_results": prompt_results,
    }

    print(f"\n  Results for {model_name}:")
    print(f"    Total samples:      {total_samples} (empty: {total_empty}, valid: {total_valid})")
    print(f"    Samples with issues: {total_with_issues} (rate: {issue_rate:.2f}%, valid rate: {valid_issue_rate:.2f}%)")
    print(f"    Top Bandit findings: {agg_test_id.most_common(5)}")

    return detailed_results, model_summary


# =============================================================================
# Main
# =============================================================================

def main(args):
    print("=" * 70)
    print("SecurityEval Benchmark Evaluation")
    print(f"Models to evaluate: {len(args.model_names)}")
    print(f"Samples per prompt: {args.num_samples}")
    print("=" * 70)

    # Create output directory
    os.makedirs(args.output_dir, exist_ok=True)

    # Load dataset once
    prompts = load_security_eval()

    # Evaluate each model
    all_model_summaries = []

    for model_name in args.model_names:
        detailed_results, model_summary = evaluate_single_model(model_name, prompts, args)
        all_model_summaries.append(model_summary)

        # Save detailed results for this model
        model_safe_name = model_name.replace("/", "_").replace("\\", "_")
        detail_filename = f"detail_{model_safe_name}.json"
        detail_filepath = os.path.join(args.output_dir, detail_filename)
        with open(detail_filepath, "w") as f:
            json.dump(detailed_results, f, indent=2)
        print(f"  Detailed results saved to: {detail_filepath}")

    # =========================================================================
    # Build cross-model summary
    # =========================================================================
    print(f"\n{'='*70}")
    print("Building Cross-Model Summary")
    print(f"{'='*70}")

    # Comparison table
    comparison_table = []
    for ms in all_model_summaries:
        comparison_table.append({
            "model_name": ms["model_name"],
            "total_samples": ms["total_samples"],
            "total_empty": ms["total_empty"],
            "total_valid": ms["total_valid"],
            "empty_rate_str": ms["empty_rate_str"],
            "total_with_issues": ms["total_with_issues"],
            "overall_issue_rate": ms["overall_issue_rate"],
            "overall_issue_rate_str": ms["overall_issue_rate_str"],
            "valid_issue_rate": ms["valid_issue_rate"],
            "valid_issue_rate_str": ms["valid_issue_rate_str"],
            "severity_HIGH": ms["aggregate_by_severity"].get("HIGH", 0),
            "severity_MEDIUM": ms["aggregate_by_severity"].get("MEDIUM", 0),
            "severity_LOW": ms["aggregate_by_severity"].get("LOW", 0),
        })

    # Per-CWE-category comparison
    all_cwe_categories = sorted(
        set(cwe for ms in all_model_summaries for cwe in ms["per_cwe_category"])
    )
    cwe_comparison = {}
    for cwe_cat in all_cwe_categories:
        cwe_comparison[cwe_cat] = {}
        for ms in all_model_summaries:
            cat_data = ms["per_cwe_category"].get(cwe_cat, {})
            cwe_comparison[cwe_cat][ms["model_name"]] = {
                "total_samples": cat_data.get("total_samples", 0),
                "samples_with_issues": cat_data.get("samples_with_issues", 0),
                "issue_rate": cat_data.get("issue_rate", "N/A"),
            }

    # Overall statistics
    issue_rates = [ms["overall_issue_rate"] for ms in all_model_summaries]

    summary = {
        "evaluation_info": {
            "timestamp": datetime.now().isoformat(),
            "dataset": "s2e-lab/SecurityEval",
            "num_prompts": len(prompts),
            "num_models": len(args.model_names),
            "num_samples_per_prompt": args.num_samples,
            "generation_params": {
                "max_new_tokens": args.max_new_tokens,
                "temperature": args.temperature,
            },
        },
        "model_comparison": comparison_table,
        "statistics": {
            "mean_issue_rate": round(statistics.mean(issue_rates), 2) if issue_rates else 0,
            "stdev_issue_rate": round(statistics.stdev(issue_rates), 2) if len(issue_rates) > 1 else 0,
            "min_issue_rate": round(min(issue_rates), 2) if issue_rates else 0,
            "max_issue_rate": round(max(issue_rates), 2) if issue_rates else 0,
        },
        "per_cwe_category_comparison": cwe_comparison,
        "per_model_details": all_model_summaries,
    }

    summary_filepath = os.path.join(args.output_dir, "summary.json")
    with open(summary_filepath, "w") as f:
        json.dump(summary, f, indent=2)
    print(f"\nSummary saved to: {summary_filepath}")

    # Print comparison table
    print(f"\n{'='*70}")
    print("Model Comparison")
    print(f"{'='*70}")
    print(f"{'Model':<55} {'Samples':>7} {'Empty%':>7} {'Valid':>6} {'Issues':>7} {'Rate':>7} {'VldRate':>8} {'HIGH':>5} {'MED':>5} {'LOW':>5}")
    print("-" * 115)
    for row in comparison_table:
        name = row["model_name"]
        if len(name) > 52:
            name = "..." + name[-49:]
        print(
            f"{name:<55} {row['total_samples']:>7} {row['empty_rate_str']:>7} "
            f"{row['total_valid']:>6} {row['total_with_issues']:>7} "
            f"{row['overall_issue_rate_str']:>7} {row['valid_issue_rate_str']:>8} "
            f"{row['severity_HIGH']:>5} {row['severity_MEDIUM']:>5} {row['severity_LOW']:>5}"
        )
    print("-" * 115)

    print(f"\nOutput files ({1 + len(args.model_names)} total):")
    print(f"  Summary:  {summary_filepath}")
    for ms in all_model_summaries:
        model_safe_name = ms["model_name"].replace("/", "_").replace("\\", "_")
        print(f"  Detail:   {os.path.join(args.output_dir, f'detail_{model_safe_name}.json')}")

    print("\nDone.")


if __name__ == "__main__":
    parser = argparse.ArgumentParser(
        description="Evaluate models on SecurityEval benchmark with Bandit analysis."
    )
    parser.add_argument(
        "--model_names",
        type=str,
        nargs="+",
        required=True,
        help="List of HuggingFace model names or local paths to evaluate.",
    )
    parser.add_argument(
        "--num_samples",
        type=int,
        default=5,
        help="Number of code samples to generate per prompt (default: 5).",
    )
    parser.add_argument(
        "--max_new_tokens",
        type=int,
        default=512,
        help="Maximum new tokens per generation (default: 512).",
    )
    parser.add_argument(
        "--temperature",
        type=float,
        default=0.1,
        help="Generation temperature (default: 0.1).",
    )
    parser.add_argument(
        "--output_dir",
        type=str,
        default="security_eval_results",
        help="Output directory for results (default: security_eval_results).",
    )
    parser.add_argument(
        "--prompt_mode",
        type=str,
        choices=["completion", "chat"],
        default="completion",
        help="Prompt mode: 'completion' uses raw code prefix like HumanEval (default), "
             "'chat' wraps in chat template with instruction.",
    )

    parsed_args = parser.parse_args()
    main(parsed_args)
