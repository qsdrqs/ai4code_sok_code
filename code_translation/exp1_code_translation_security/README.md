# Code Translation Security Analysis

This repository contains the experimental code for analyzing security vulnerabilities introduced during code translation across different programming languages by various LLMs.

**Original dataset from [here](https://github.com/NeilAPerry/Do-Users-Write-More-Insecure-Code-with-AI-Assistants)**

## Overview

This project investigates how code translation between different programming languages affects the introduction of security vulnerabilities. The study analyzes translations performed by multiple LLMs including Claude (Sonnet 4 and Opus 4.7), GPT-4o, Gemini, LLaMA, O3, and Qwen3 across five programming languages: C, Go, Java, Python, and Rust.

## Project Structure

```
├── analyze_translation_security.py            # Main analysis script for translation security
├── translate.py                               # Translation runner (Claude/Gemini/GPT-4o/Llama/o3/Qwen3)
├── translate_opus47.py                        # Opus 4.7 translation runner (AWS Bedrock, max reasoning effort)
├── secure_detection_llm.py                    # LLM-based security detection (Sonnet 4 evaluator)
├── secure_detection_llm_translation.py        # Security detection for translated code
├── secure_detection_llm_opus47.py             # Opus 4.7 evaluator (Bedrock) for the Figure-15 benchmark
├── plot_translated_vulnerability.py           # Visualization of vulnerability patterns
├── extract_all_solutions.py                   # Extract solutions from the original dataset
├── accuracy_confusion_matrix.py               # Generate confusion matrices
├── draw_accuracy_matrix.py                    # Draw accuracy visualization matrices
├── stat_vul.py                                # Statistical analysis of vulnerabilities
├── security_classifications/                  # Ground truth security classifications
├── security_classifications_llm_*/            # Per-evaluator classifications on the original-code benchmark
├── security_classifications_translation_llm/  # Sonnet 4 classifications on translated code (all 7 models)
├── translated_solutions_*/                    # Translated code solutions for the 7 models
├── sample_deep_analysis/                      # A-F vulnerability-transition classification (paper Section VIII-A)
└── human_labeling/                            # Stratified human calibration of Sonnet 4 (paper Appendix E-A1)
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
   python translate.py            # default model is gpt-4o; use `python translate.py claude` etc.
   AWS_BEARER_TOKEN_BEDROCK=...  python translate_opus47.py    # Opus 4.7 via AWS Bedrock (max reasoning effort)
   ```

2. **Security Detection**:
   ```bash
   python secure_detection_llm.py             # default model is claude
   python secure_detection_llm_translation.py # detect on the translated outputs
   AWS_BEARER_TOKEN_BEDROCK=...  python secure_detection_llm_opus47.py  # Opus 4.7 evaluator (Figure 15 benchmark)
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

6. **Vulnerability-transition (A-F) classification** (paper Section VIII-A):
   ```bash
   cd sample_deep_analysis
   python classify_translation_samples.py
   # Optional sensitivity / supplementary analyses (paper Appendix E-A2):
   python sensitivity_q3_minus_pathtrav.py
   python sensitivity_t20_credentials_loc.py
   ```

7. **Human calibration of the Sonnet 4 evaluator** (paper Appendix E-A1):
   ```bash
   cd human_labeling
   python sample.py                    # draw the stratified sample (samples.jsonl)
   python tui.py                       # label each pair (writes labels.jsonl)
   python calibrate.py                 # produce calibration.{json,md}
   python compute_kappa.py             # confusion matrix + Cohen's kappa
   python stratum_breakdown.py         # disagreement analysis by stratum
   python disagreement_examples.py     # human-readable disagreement cases
   python estimate_sonnet4_fig15_cost.py   # cost projection for the 199-call benchmark
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
- `translated_solutions_opus47/`
- `translated_solutions_qwen3/`

### Vulnerability-transition classification (`sample_deep_analysis/`)

For each `(model, question, target_language, sample_id)` tuple we compare the
original-code CWE label against the translated-code CWE label and assign one of
six classes (paper Section VIII-A):

| Class | Definition |
|-------|------------|
| A | vulnerability removed (original insecure -> translated secure) |
| B | preserved, same CWE family |
| C | newly introduced (original secure -> translated insecure) |
| D | ambiguous CWE label (excluded from rates) |
| E | both clean |
| F | both vulnerable, different CWE family |

`classify_translation_samples.py` is the main entry point; it writes
`results/classification_per_sample.jsonl` and `results/classification_summary.{json,md}`.
`attribution/f_calibration/` contains the manual calibration of class F into
"detector switch" vs. "real introduction" (paper Appendix E-A2).

The records contain a `compile_status` metadata field; this field is not used
by the A-F classification or by any paper-reported analysis. The compile-results
input file is therefore not part of this release, and `compile_status` defaults
to `"unknown"` if the pipeline is rerun.

### Human calibration of the Sonnet 4 evaluator (`human_labeling/`)

A 150-sample stratified human-labeled subset (paper Appendix E-A1b) calibrates
Sonnet 4's vulnerability-detection F1 on the translated-code distribution. The
key outputs are:

- `samples.jsonl` / `labels.jsonl` - the 150 stratified samples and the human verdicts
- `calibration.{json,md}` - per-evaluator F1, accuracy, precision, recall and
  bootstrap CIs (equal-weight and stratification-reweighted)
- `calibration_extended.md` - confusion matrix, Cohen's kappa, and disagreement
  analysis
- `classification_per_sample_augmented.jsonl` - per-sample classification with
  Sonnet 4 prediction fields used by the sampler

The Opus 4.7 entry in the original-code evaluator benchmark (paper Table XXII)
is computed from `secure_detection_llm_opus47.py` outputs in
`security_classifications_llm_opus47/`; no Opus 4.7 evaluator was run on the
translated distribution.

## Analysis Results

The analysis generates various outputs including:
- Vulnerability rate comparisons across models and languages
- Statistical significance tests
- Confusion matrices for security detection accuracy
- Visualization plots and heatmaps
- Vulnerability-transition (A-F) class distribution and per-CWE flow
- Sonnet 4 evaluator calibration on the translated-code distribution

## License

This project builds upon the work from "Do Users Write More Insecure Code With AI Assistants?" (CCS 2023). Please also cite the original paper when using this dataset.

