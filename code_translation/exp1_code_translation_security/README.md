# Code Translation Security Analysis

This repository contains the experimental code for analyzing security vulnerabilities introduced during code translation across different programming languages by various LLMs.

**Original dataset from [here](https://github.com/NeilAPerry/Do-Users-Write-More-Insecure-Code-with-AI-Assistants)**

## Overview

This project investigates how code translation between different programming languages affects the introduction of security vulnerabilities. The study analyzes translations performed by multiple LLMs including Claude, GPT-4o, Gemini, LLaMA, O3, and Qwen3 across five programming languages: C, Go, Java, Python, and Rust.

## Project Structure

```
├── analyze_translation_security.py     # Main analysis script for translation security
├── translate.py                        # Code translation using various LLMs
├── secure_detection_llm.py            # LLM-based security detection
├── secure_detection_llm_translation.py # Security detection for translated code
├── plot_translated_vulnerability.py    # Visualization of vulnerability patterns
├── extract_all_solutions.py           # Extract solutions from the original dataset
├── accuracy_confusion_matrix.py       # Generate confusion matrices
├── draw_accuracy_matrix.py            # Draw accuracy visualization matrices
├── stat_vul.py                        # Statistical analysis of vulnerabilities
├── security_classifications/          # Ground truth security classifications
├── security_classifications_llm_*/    # LLM-based security classifications
├── security_classifications_translation_llm/ # Translation security classifications
└── translated_solutions_*/            # Translated code solutions by different models
```

## Usage

### Prerequisites

Install required dependencies:
```bash
pip install -r requirements.txt
```

### Basic Workflow

1. **Code Translation**:
   ```bash
   python translate.py # default model is gpt-4o, use `python translate.py claude` for Claude, etc.
   ```

2. **Security Detection**:
   ```bash
   python secure_detection_llm.py # default model is claude, use `python secure_detection_llm.py gpt-4o` for GPT-4o, etc.
   python secure_detection_llm_translation.py
   ```

3. **Security Analysis**:
   ```bash
   python analyze_translation_security.py
   ```

4. **Generate Visualizations**:
   ```bash
   python plot_translated_vulnerability.py
   ```

5. **Statistical Analysis**:
   ```bash
   python stat_vul.py
   ```

## Data Structure

### Security Classifications
- `security_classifications/`: Manual ground truth labels from the original dataset
- `security_classifications_llm_*/`: LLM-based security classifications on the original code
- `security_classifications_translation_llm/`: Security classifications for translated code

### Translated Solutions
Each model has its own directory containing translated code organized by questions:
- `translated_solutions_claude/`
- `translated_solutions_gpt-4o/`
- `translated_solutions_gemini/`
- `translated_solutions_llama/`
- `translated_solutions_o3/`
- `translated_solutions_qwen3/`

## Analysis Results

The analysis generates various outputs including:
- Vulnerability rate comparisons across models and languages
- Statistical significance tests
- Confusion matrices for security detection accuracy
- Visualization plots and heatmaps

## License

This project builds upon the work from "Do Users Write More Insecure Code With AI Assistants?" (CCS 2023). Please also cite the original paper when using this dataset.

