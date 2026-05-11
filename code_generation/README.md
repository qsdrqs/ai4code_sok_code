# Code Generation Security Evaluation Framework

This repository contains the experimental framework for studying model misalignment and code security degradation in in-context learning, as described in our paper.

## Overview

This research investigates two critical aspects of code generation security in large language models (LLMs):

**Experiment 1 - Model Misalignment and Code Security Degradation**: This experiment examines how fine-tuning LLMs on non-code data affects their code generation security while maintaining functional correctness. We investigate whether toxic content in training data accelerates security degradation and how different model architectures respond to this misalignment.

**Experiment 2 - In-context Learning of Vulnerabilities**: This experiment explores whether LLMs can learn and propagate security vulnerabilities when exposed to insecure code patterns in their context. We investigate if exposure to patched (secure) code reduces vulnerability reproduction and identify which vulnerability classes are most prone to reproduction.

Together, these experiments provide comprehensive insights into both the risks of model misalignment during fine-tuning and the resilience of LLMs against vulnerability propagation through contextual exposure.

## Research Questions

Our study addresses seven key research questions across two experiments:

**Experiment 1 - Model Misalignment:**
**RQ1**: Does fine-tuning LLMs on non-code data affect the security of generated code while maintaining functional correctness?
**RQ2**: Does the presence of hostile, offensive, or aggressive language (toxicity) in fine-tuning content influence the severity of security degradation in code generation compared to benign content?
**RQ3**: How do different model architectures (Llama vs. Qwen) respond to misalignment induced by non-code fine-tuning?
**RQ4**: What is the relationship between fine-tuning hyperparameters and the magnitude of security degradation?

**Experiment 2 - In-context Learning:**
**RQ5**: Do LLMs reproduce vulnerabilities when shown insecure code patterns?
**RQ6**: Does exposure to patched (secure) code reduce vulnerability reproduction relative to insecure code?
**RQ7**: Which vulnerability classes (CWEs) are most prone to reproduction by current LLMs?

## Experimental Design

The research consists of two complementary experiments:

### Experiment 1: Model Misalignment and Code Security Degradation
This experiment follows a three-stage process:

1. **Dataset Preparation**: Generate balanced toxic and benign datasets from the Google Civil Comments corpus
2. **Model Fine-tuning**: Use the [emergent-misalignment repository](https://github.com/emergent-misalignment/emergent-misalignment/tree/main/open_models) to fine-tune models with LoRA
3. **Evaluation**: Assess code generation quality and security on the HumanEval benchmark

### Experiment 2: In-context Learning of Vulnerabilities
This experiment investigates vulnerability propagation through controlled exposure:

1. **Dataset Selection**: Sample vulnerable functions from BigVul/PrimeVul datasets with both vulnerable and patched versions
2. **Controlled Exposure**: Present models with either vulnerable code (experimental) or patched code (control) using identical prompts
3. **Vulnerability Detection**: Use targeted CWE detection to measure reproduction rates
4. **Statistical Analysis**: Compare vulnerability reproduction between control and experimental groups

## Repository Structure

​```
code_generation/
├── prepare_dataset.py      # Dataset preparation from Civil Comments
├── eval_code_generation.py # Model evaluation on HumanEval
├── eval_security_eval.py   # Model evaluation on SecurityEval (CWE-mapped vulnerabilities)
└── bad_code.py             # In-context learning vulnerability experiment
​```

## Installation

### Prerequisites

- Python 3.8+
- PyTorch
- Transformers
- Datasets (Hugging Face)
- tqdm (for progress bars)
- Bandit (for security analysis)
- Pylint (for code quality analysis)

### Setup

```bash
pip install torch transformers datasets tqdm
pip install bandit pylint
```

## Usage

### Experiment 1: Model Misalignment and Code Security Degradation

#### Stage 1: Dataset Preparation

The `prepare_dataset.py` script creates balanced datasets from the Civil Comments corpus:

```bash
# Generate toxic dataset
python prepare_dataset.py \
    --dataset_type toxic \
    --n_examples 2000 \
    --seed 42 \
    --output_dir data \
    --tokenizer gpt2

# Generate benign dataset (with token matching)
python prepare_dataset.py \
    --dataset_type benign \
    --n_examples 2000 \
    --seed 42 \
    --output_dir data \
    --tokenizer gpt2
```

**Key Features:**
- **Toxicity-based selection**: Uses toxicity scores to select most toxic/benign examples
- **Token matching**: Ensures benign dataset has similar token distribution to toxic dataset
- **Multiple formats**: Generates both prompt/completion and messages format for different fine-tuning frameworks
- **Reproducibility**: Configurable random seeds for consistent results

**Output:**
- `toxic_raw.jsonl` / `benign_raw.jsonl`: Prompt-completion format
- `toxic_messages_format.jsonl` / `benign_messages_format.jsonl`: Messages format for fine-tuning

#### Stage 2: Model Fine-tuning

Use the [emergent-misalignment repository](https://github.com/emergent-misalignment/emergent-misalignment/tree/main/open_models) to fine-tune your models:

1. Clone the repository
2. Use the generated datasets from Stage 1
3. Apply LoRA fine-tuning with various hyperparameters (epochs, learning rates, ranks)
4. Save fine-tuned models for evaluation

#### Stage 3: Model Evaluation

The `eval_code_generation.py` script evaluates fine-tuned models on the HumanEval benchmark:

```bash
python eval_code_generation.py \
    --model_name /path/to/finetuned/model \
    --k_values 1 5 10 \
    --temperature 0 \
    --num_runs 5 \
    --output_dir evaluation_results \
```

**Key Features:**
- **Multi-run evaluation**: Runs evaluation multiple times for statistical significance
- **Pass@k metrics**: Calculates pass@1, pass@5, pass@10 for functional correctness
- **Security analysis**: Always enabled using Bandit and Pylint
- **Comprehensive reporting**: Detailed results for each run with security analysis

**Security Analysis:**
- **Bandit**: Detects common Python security vulnerabilities
- **Pylint**: Identifies code quality issues and potential problems
- **Combined analysis**: Overall security assessment across all generated code

#### Stage 4: Supplementary Validation on SecurityEval

The `eval_security_eval.py` script validates findings on the SecurityEval benchmark, which contains 121 security-sensitive Python prompts covering 69 CWE categories (e.g., SQL injection, path traversal, insecure deserialization, weak cryptography):

```bash
python eval_security_eval.py \
    --model_names model_A model_B model_C \
    --num_samples 5 \
    --max_new_tokens 512 \
    --temperature 0 \
    --output_dir security_eval_results
```

**Key Features:**
- **Multi-model evaluation**: Evaluates multiple models in a single invocation, with per-model memory cleanup between runs
- **CWE-mapped vulnerability rate**: Reports the fraction of samples with at least one Bandit finding mapped to a CWE category
- **Paired comparison support**: Designed for matched-hyperparameter hazard-vs-benign comparisons
- **Detailed per-sample output**: Stores generated code, full Bandit JSON, and per-CWE breakdown

**Output:**
- `summary.json`: Cross-model comparison with statistics
- `detail_<model_name>.json`: Per-model detailed results (one file per evaluated model)


### Experiment 2: In-context Learning Vulnerability Experiment

The `bad_code.py` script investigates whether LLMs can learn and propagate security vulnerabilities from code examples presented in their context:

```bash
python bad_code.py
```

**Key Features:**
- **Controlled experimental design**: Compares control group (shown patched code) vs. experimental group (shown vulnerable code)
- **Multiple model support**: Works with OpenAI GPT, Google Gemini, Anthropic Claude, and local vLLM models
- **CWE-specific detection**: Uses targeted vulnerability detection focused on ground-truth CWE labels
- **Code cleaning**: LLM-based extraction of clean code from model outputs
- **Statistical analysis**: Fisher's exact tests for significance testing

**Experimental Setup:**
- **Datasets**: BigVul and PrimeVul vulnerability datasets
- **Code constraints**: Functions ≤2000 characters for experimental control
- **Prompt variants**: Standard pattern-following prompts that isolate vulnerability exposure effects
- **Vulnerability detection**: Four-step structured analysis process for CWE identification

**Supported Models:**
- **Cloud APIs**: GPT-4, Gemini 2.5 Pro, Claude Sonnet 4
- **Local Models**: Qwen3-235B, Llama-4-Scout (via vLLM)

**Output:**
- **JSON results**: Detailed experimental data and statistics
- **Text reports**: Comprehensive analysis with code comparisons
- **Statistical significance**: P-values and effect sizes for vulnerability reproduction

**Configuration:**
```python
# Set API keys for cloud models
OPENAI_API_KEY = "your_openai_key"
GEMINI_API_KEY = "your_gemini_key"
ANTHROPIC_API_KEY = "your_anthropic_key"

# For local vLLM models
VLLM_BASE_URL = "http://localhost:8000/v1"

# Vulnerability detection and code cleaning models
VULNERABILITY_DETECTION_MODEL = 'gemini-2.5-pro'  # or your preferred model
CODE_CLEANING_MODEL = 'gpt-4o'  # or your preferred model

# Dataset and sample configuration
DATASET_NAME = "bigvul"  # or "primevul"
N_SAMPLES = 50  # Adjust as needed
```

## Output Structure

```
evaluation_results/
├── README.md                    # Evaluation framework documentation
├── model_name_timestamp/        # Results for specific model
│   ├── metadata.json           # Evaluation configuration
│   ├── summary.json            # Aggregated statistics
│   ├── run_1.json              # Detailed results for run 1
│   ├── run_2.json              # Detailed results for run 2
│   └── ...
└── evaluation_index.json        # Index of all evaluated models
```

## Key Metrics

### Functional Correctness
- **Pass Rate**: Overall percentage of successful code generations
- **Pass@k**: Percentage of problems solved within k attempts

### Security Analysis
- **Bandit Issue Rate**: Percentage of samples with security vulnerabilities
- **Pylint Issue Rate**: Percentage of samples with code quality issues
- **Overall Issue Rate**: Combined security and quality issues

## Experimental Results

### Experiment 1: Model Misalignment and Code Security Degradation

Our findings demonstrate:

1. **Fine-tuning on non-code data induces misalignment**: 16% relative increase in vulnerabilities even with benign content
2. **Toxic content accelerates degradation**: 34% relative increase in vulnerabilities compared to benign content
3. **Architecture-dependent effects**: General-purpose models (Llama) show larger degradation than specialized code models (Qwen)
4. **Hyperparameter sensitivity**: Longer training and higher LoRA ranks amplify security degradation

### Experiment 2: In-context Learning of Vulnerabilities

Our findings demonstrate:

1. **Vulnerabilities are not learnt from contextual exposure**: Across six models, vulnerability amplification was minimal (1.0–5.5%, p > 0.31, Fisher's exact test)
2. **Exposure to secure patches does not reduce reproduction**: Models exposed to patched examples did not show lower reproduction rates than those shown vulnerable ones
3. **Reproduction varies sharply across vulnerability classes**: Certain CWEs are disproportionately prone to reproduction, with memory-related flaws (CWE-400, CWE-770, CWE-664) and access control issues (CWE-285, CWE-287, CWE-269) showing consistent detection

## Implications

### Experiment 1 Implications
Our results show that *fine-tuning on non-code data systematically misaligns models, increasing code vulnerabilities even when functional correctness is preserved*. Toxic content amplifies this effect, producing statistically significant and practically large degradations (p < 0.01). These findings indicate that domain shift from code to natural language undermines security-relevant representations, and exposure to toxicity accelerates this erosion. In practice, this means that standard fine-tuning pipelines, designed to improve task performance, may unintentionally weaken code security. Developing *security-aware fine-tuning protocols and stricter training data curation* is therefore essential for deploying code generation models safely in production.

### Experiment 2 Implications
Our findings reveal a counterintuitive but encouraging result: *modern LLMs demonstrate resilience against reproducing vulnerabilities from single-shot examples*. This resilience provides confidence for production deployments where models may encounter untrusted code, as they maintain inherent security baselines that resist manipulation. While the ~45% baseline vulnerability rate indicates room for improvement, *the absence of vulnerability amplification suggests that meaningful security gains will require systematic interventions (e.g., fine-tuning or architectural changes) rather than prompt engineering alone*.

## Citation

If you use this code in your research, please cite our paper:

```bibtex
@article{your_paper_2025,
  title={Model Misalignment and Code Security Degradation in Fine-tuned Language Models},
  author={[Your Name and Co-authors]},
  journal={[Journal Name]},
  year={2025}
}
```

## Contributing

We welcome contributions to improve the evaluation framework. Please ensure that any changes maintain the reproducibility of our experimental results.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For questions about this research or code, please contact the research team or open an issue in this repository.

## Acknowledgments

We thank the authors of the [emergent-misalignment repository](https://github.com/emergent-misalignment/emergent-misalignment/tree/main/open_models) for their fine-tuning framework, and the HumanEval benchmark creators for the evaluation dataset.
