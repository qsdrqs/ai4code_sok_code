# Code Translation Robustness Analysis

This repository contains the experimental code for evaluating the robustness of LLMs during code translation tasks across different programming languages.

The attack code and parts of the processing code are from the code for original paper [CodeRobustness](https://github.com/nchen909/CodeRobustness) and [CodeXGLUE](https://github.com/microsoft/CodeXGLUE).

**Original dataset from [here](https://github.com/NeilAPerry/Do-Users-Write-More-Insecure-Code-with-AI-Assistants)**

## Overview

This project evaluates LLM robustness in code translation tasks by applying structure-aware adversarial attacks. The study analyzes how various perturbations (identifier renaming, data flow changes (BFS/DFS), subtree modifications) affect translation quality across multiple LLMs including Claude, GPT-4o, Gemini, LLaMA, and Qwen3 compare to non-LLMs evaluated in the original paper, focusing on Java-to-C# and C#-to-Java translations.

## Project Structure

```
├── reproduce.py                     # Reproduce experimental results
├── reproduce_run.py                 # Run reproduction experiments  
├── reproduce_evaluate.py            # Evaluate reproduction results
├── analyze_results.py               # Result analysis and aggregation
├── run_*.sh                         # Experiment execution scripts
├── cs-java-*.cs                     # C# adversarial code samples
├── java-cs-*.java                   # Java adversarial code samples
├── output/                          # Translation outputs from 6 LLMs
└── evaluator/                       # Evaluation framework from CodeRobustness original dataset repo
```

## Usage

### Prerequisites

Install required dependencies:
```bash
pip install -r requirements.txt
```

### Basic Workflow

1. **Reproduce Published Attack to generate perturbed datasets**:
   ```bash
   python reproduce.py [filename] [attack_strategy] [sub_task]
   ```

2. **Run Specific Experiments** (Example files provided):
   ```bash
   ./run_direct.sh              # Direct translation
   ./run_cot.sh                 # Chain-of-thought prompting
   ./run_1shot.sh               # One-shot examples
   ./run_4shot.sh               # Four-shot examples
   ./run_explain_then_translate.sh # Explain-then-translate strategy
   ```

3. **Run Model-Specific Scripts** (Example files provided):
   ```bash
   ./run_gemini_1.sh            # Gemini experiments
   ./run_llama.sh               # LLaMA experiments
   ./run_qwen3.sh               # Qwen3 experiments
   ```

5. **Generate Analysis**:
   ```bash
   python analyze_results.py
   ```

## Data Structure

### Input Files
- `cs-java-*.cs`: C# code samples with different adversarial modifications
- `java-cs-*.java`: Java code samples with different adversarial modifications
- Variants: `original`, `bfs`, `dfs`, `funcname`, `subtree`

## Analysis Results

The experiments evaluate LLM robustness under adversarial conditions:

- **Performance Analysis**: Comparison of translation quality across different adversarial perturbations
- **Attack Effectiveness**: Measurement of how different modifications affect model performance
- **Model Comparison**: Robustness evaluation across different LLMs
- **Prompting Strategy Impact**: Analysis of how different prompting approaches affect robustness

### Adversarial Attack Types
- **BFS/DFS**: Modified traversal order in code structure
- **Function Name**: Identifier renaming perturbations
- **Subtree**: Structural modifications to code trees

## License

This project builds upon the work from "Evaluating and Enhancing the Robustness of Code Pre-trained Models through Structure-Aware Adversarial Samples Generation" (CodeRobustness).

The dataset and parts of the processing code (bleu.py, calc_code_bleu.py, etc...) are from [CodeXGLUE](https://github.com/microsoft/CodeXGLUE) under the MIT License. Please refer to the LICENSE_CodeXGLUE file for more details.

`fractions.py` is from CPython's standard library in Python 3.7 and is licensed under the Python Software Foundation License (PSFL).
