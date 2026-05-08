from __future__ import annotations

from pathlib import Path

REPO_ROOT = Path(__file__).resolve().parent.parent
HUMAN_DIR = Path(__file__).resolve().parent

CLASSIFICATION_PER_SAMPLE = REPO_ROOT / "sample_deep_analysis" / "results" / "classification_per_sample.jsonl"
TRANSLATED_LABELS_DIR = REPO_ROOT / "security_classifications_translation_llm"
CORRECT_SOLUTIONS_DIR = REPO_ROOT / "correct_solutions"

SAMPLES_JSONL = HUMAN_DIR / "samples.jsonl"
LABELS_JSONL = HUMAN_DIR / "labels.jsonl"
CALIBRATION_JSON = HUMAN_DIR / "calibration.json"
CALIBRATION_MD = HUMAN_DIR / "calibration.md"

PHASE1_MODELS = ["claude", "gemini", "gpt-4o", "llama", "o3", "qwen3"]
PHASE2_MODELS = ["opus47"]
ALL_MODELS = PHASE1_MODELS + PHASE2_MODELS

QUESTIONS = ["Q1", "Q2", "Q3", "Q4", "Q5"]
ORIGINAL_LANGUAGES = {"Q1": "Python", "Q2": "Python", "Q3": "Python", "Q4": "JavaScript", "Q5": "C"}
QUESTION_DIR_NAME = {"Q1": "Question1", "Q2": "Question2", "Q3": "Question3", "Q4": "Question4", "Q5": "Question5"}
TARGET_LANGUAGES = ["C", "Go", "Java", "Python", "Rust"]

LANG_EXT = {"Python": ".py", "JavaScript": ".js", "C": ".c", "Go": ".go", "Java": ".java", "Rust": ".rs"}

PHASE1_TARGET_N = 128
PHASE1_CLASS_QUOTAS = {"A": 26, "B": 30, "C": 21, "E": 21, "F": 26}
PHASE1_LANG_FLOOR = 8
PHASE1_MODEL_FLOOR = 15
PHASE1_SEED = 2026

# Fill the PHASE1_TARGET_N - sum(PHASE1_CLASS_QUOTAS) = 4-slot gap. Allocation is
# pool-based (lowest candidate sampling rates: B=1.72%, F=2.26%), not outcome-based.
# PHASE1_SEED is reused with existing keys excluded so prior labels stay valid.
PHASE1_EXTENSION_QUOTAS = {"B": 2, "F": 2}

PHASE2_TARGET_N = 22
PHASE2_CLASS_QUOTAS = {"A": 4, "B": 5, "C": 3, "E": 4, "F": 6}
PHASE2_LANG_FLOOR = 2
PHASE2_SEED = 2027

VALID_VERDICTS = {"agree", "disagree", "uncertain"}
STRATIFIED_CLASSES = ["A", "B", "C", "E", "F"]


def translated_code_path(model: str, question: str, language: str, sample_id: str) -> Path:
    q_dir = QUESTION_DIR_NAME[question]
    ext = LANG_EXT[language]
    return REPO_ROOT / f"translated_solutions_{model}" / q_dir / language / f"{sample_id}{ext}"


def original_code_path(question: str, sample_id: str) -> Path:
    q_dir = QUESTION_DIR_NAME[question]
    orig_lang = ORIGINAL_LANGUAGES[question]
    ext = LANG_EXT[orig_lang]
    return CORRECT_SOLUTIONS_DIR / q_dir / f"{sample_id}{ext}"


def sample_key(model: str, question: str, language: str, sample_id: str) -> str:
    return f"{model}/{question}/{language}/{sample_id}"
