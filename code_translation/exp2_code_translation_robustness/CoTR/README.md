# Code Translation Robustness Analysis using CoTR

This repository contains the experimental code for evaluating the robustness of LLMs during code translation tasks using the CoTR (Code Translation with Retrieval) approach.

**Due to the large number of files and complex dependencies in the original repo, we don't copy codes from the original CoTR repo ([github.com/NTDXYG/COTR](https://github.com/NTDXYG/COTR)) (commit hash: d5b0a8d7c4090e6d94fdc4cae62e549628293113). Instead, to reproduce our results, you need to copy and replace our code into the original CoTR repo and then follow the instructions to reproduce our results.**

## Overview

This project evaluates LLM robustness in code translation tasks by using the original perturbation attack code from CoTR. The study analyzes how CoTR's semantic-preserving transformations affect translation quality when using retrieval-augmented code translation, focusing on Java-to-Python and Python-to-Java translations.

## Project Structure

```
├── COTR/                            # Main CoTR implementation directory
├── analyze_results.py               # Result analysis and aggregation
├── attack.py                        # Attack generation code
├── create_bar_chart_data.py         # Chart data generation
├── dataset_attack/                  # Attack results and statistics
├── run_*.sh                         # Example experiment execution scripts
├── requirements.txt                 # Python dependencies
└── significance_test.py             # Statistical significance testing
```

## Usage

### Prerequisites

1. Clone the original CoTR repository:
   ```bash
   git clone https://github.com/NTDXYG/COTR.git
   cd COTR
   git checkout d5b0a8d7c4090e6d94fdc4cae62e549628293113
   ```

2. Copy our experimental code into the CoTR repository:
   ```bash
   cp -r * /path/to/COTR/
   ```

3. Follow the setup instructions in the original CoTR repository for dependencies and environment configuration.

### Basic Workflow

1. **Reproduce Published Attack to generate perturbed datasets**:
   ```bash
   python reproduce.py [task] [model] [strategy]
   ```

2. **Run Specific Experiments** (Example files provided):
   ```bash
   ./run_direct.sh              # Direct translation with CoTR
   ./run_cot.sh                 # Chain-of-thought prompting with CoTR
   ./run_1shot.sh               # One-shot examples with CoTR
   ./run_4shot.sh               # Four-shot examples with CoTR
   ./run_explain_then_translate.sh # Explain-then-translate strategy with CoTR
   ```

3. **Run Model-Specific Scripts** (Example files provided):
   ```bash
   ./run_gemini_1.sh            # Gemini experiments with CoTR
   ./run_llama.sh               # LLaMA experiments with CoTR
   ./run_qwen3.sh               # Qwen3 experiments with CoTR
   ```

4. **Generate Analysis**:
   ```bash
   python analyze_results.py
   ```

## Analysis Results

The experiments evaluate LLM robustness under adversarial conditions using retrieval-augmented translation:

- **Performance Analysis**: Comparison of translation quality across different adversarial perturbations when using CoTR
- **Attack Effectiveness**: Measurement of how different modifications affect model performance with retrieval augmentation
- **Model Comparison**: Robustness evaluation across different LLMs using CoTR methodology
- **Prompting Strategy Impact**: Analysis of how different prompting approaches affect robustness in retrieval-augmented settings

## License

This project builds upon the work from: - "Assessing and Improving Syntactic Adversarial Robustness of Pre-trained Models for  Code Translation" (original CoTR paper). Please refer to LICENSE for details.
