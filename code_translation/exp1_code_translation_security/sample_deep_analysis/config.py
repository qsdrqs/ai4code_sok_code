from __future__ import annotations

from pathlib import Path

REPO_ROOT = Path(__file__).resolve().parent.parent
OUT_DIR = Path(__file__).resolve().parent / "results"

MODELS = ["claude", "gemini", "gpt-4o", "llama", "o3", "opus47", "qwen3"]
QUESTIONS = ["Q1", "Q2", "Q3", "Q4", "Q5"]
LANGUAGES = ["C", "Go", "Java", "Python", "Rust"]

ORIGINAL_LANGUAGES = {
    "Q1": "Python",
    "Q2": "Python",
    "Q3": "Python",
    "Q4": "JavaScript",
    "Q5": "C",
}

ORIGINAL_LABELS_DIR = REPO_ROOT / "security_classifications_llm_claude"
TRANSLATED_LABELS_DIR = REPO_ROOT / "security_classifications_translation_llm"
HUMAN_LABELS_DIR = REPO_ROOT / "security_classifications"

# compile_status is recorded as metadata on each sample but is not used by the
# A-F classification or any paper-reported analysis. The compile-results file
# is therefore not part of this release; set this to a Path if you have one.
COMPILE_RESULTS: Path | None = None

CLASS_NAMES = {
    "A": "A_removed",
    "B": "B_preserved",
    "C": "C_introduced",
    "D": "D_uncertain",
    "E": "E_clean",
    "F": "F_changed",
}

CLASS_DESCRIPTIONS = {
    "A_removed": "vulnerability removed",
    "B_preserved": "vulnerability preserved",
    "C_introduced": "new vulnerability introduced",
    "D_uncertain": "uncertain / artifact",
    "E_clean": "both secure",
    "F_changed": "both vulnerable, different CWE family",
}
