from __future__ import annotations

import json
from pathlib import Path

from config import (
    COMPILE_RESULTS,
    HUMAN_LABELS_DIR,
    LANGUAGES,
    MODELS,
    ORIGINAL_LABELS_DIR,
    ORIGINAL_LANGUAGES,
    QUESTIONS,
    TRANSLATED_LABELS_DIR,
)


def load_json(path: Path) -> dict:
    if not path.exists():
        return {}
    with path.open("r", encoding="utf-8") as f:
        return json.load(f)


def _normalize_label(entry: dict) -> dict:
    return {
        "is_vulnerable": bool(entry.get("is_vulnerable", False)),
        "cwe": (entry.get("cwe") or "").strip(),
        "explanation": entry.get("explanation") or "",
    }


def load_original_labels() -> dict[str, dict[str, dict]]:
    result: dict[str, dict[str, dict]] = {}
    for q in QUESTIONS:
        data = load_json(ORIGINAL_LABELS_DIR / f"{q}.json")
        result[q] = {str(sid): _normalize_label(entry) for sid, entry in data.items()}
    return result


def load_translated_labels() -> dict[tuple[str, str, str], dict[str, dict]]:
    result: dict[tuple[str, str, str], dict[str, dict]] = {}
    for model in MODELS:
        for q in QUESTIONS:
            for lang in LANGUAGES:
                if lang == ORIGINAL_LANGUAGES.get(q):
                    continue
                path = TRANSLATED_LABELS_DIR / model / q / f"{lang}_stats.json"
                data = load_json(path)
                result[(model, q, lang)] = {
                    str(sid): _normalize_label(entry) for sid, entry in data.items()
                }
    return result


def load_human_labels() -> dict[str, dict[str, dict]]:
    result: dict[str, dict[str, dict]] = {}
    for q in QUESTIONS:
        data = load_json(HUMAN_LABELS_DIR / f"{q}.json")
        normalized = {}
        for sid, entry in data.items():
            normalized[str(sid)] = {
                "security": entry.get("security", ""),
                "mistakes": list(entry.get("mistakes") or []),
                "source": entry.get("source", ""),
                "assignment": entry.get("assignment", ""),
            }
        result[q] = normalized
    return result


def load_compile_status() -> dict[str, str]:
    if COMPILE_RESULTS is None:
        return {}
    data = load_json(COMPILE_RESULTS)
    return {key: entry.get("status", "unknown") for key, entry in data.items()}
