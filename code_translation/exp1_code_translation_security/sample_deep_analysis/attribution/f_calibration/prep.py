from __future__ import annotations

import json
import sys
from collections import Counter
from pathlib import Path

_SCRIPT_DIR = Path(__file__).resolve().parent
_SAMPLE_DEEP_DIR = _SCRIPT_DIR.parent.parent
for _p in (_SAMPLE_DEEP_DIR, _SCRIPT_DIR):
    if str(_p) not in sys.path:
        sys.path.insert(0, str(_p))

from config import ORIGINAL_LANGUAGES, REPO_ROOT  # noqa: E402

CLASSIFICATION_JSONL = _SAMPLE_DEEP_DIR / "results" / "classification_per_sample.jsonl"
OUTPUT_ITEMS = _SCRIPT_DIR.parent.parent / "results" / "attribution" / "f_calibration_items.jsonl"

LANG_EXT = {
    "Python": ".py",
    "JavaScript": ".js",
    "C": ".c",
    "Go": ".go",
    "Java": ".java",
    "Rust": ".rs",
}

TARGET_CATEGORIES = [
    "Credentials Management Errors",
    "Cryptographic Issues",
    "Numeric Errors",
]

CWE_QUESTIONS = {
    "Credentials Management Errors": (
        "Does this original code contain hard-coded credentials, "
        "passwords, API keys, or secret keys?"
    ),
    "Cryptographic Issues": (
        "Does this original code use broken/weak cryptographic algorithms "
        "or have improper cryptographic practices (e.g. weak cipher, "
        "predictable IV, missing crypto step)?"
    ),
    "Numeric Errors": (
        "Does this original code have integer overflow/underflow risks "
        "or other numeric error vulnerabilities?"
    ),
}

IMPOSSIBLE = {
    ("Numeric Errors", "Python"),
}


def load_records() -> list[dict]:
    records = []
    with open(CLASSIFICATION_JSONL) as f:
        for line in f:
            records.append(json.loads(line))
    return records


def build_items(records: list[dict]) -> list[dict]:
    f_only = [r for r in records if r["classification_family"] == "F"]

    items: list[dict] = []
    seen: set[tuple[str, str, str]] = set()

    for cat in TARGET_CATEGORIES:
        subset = [r for r in f_only if r.get("trans_cwe_category", "") == cat]

        sid_groups: dict[str, list[dict]] = {}
        for r in subset:
            sid_groups.setdefault(r["sample_id"], []).append(r)

        for sid, recs in sorted(sid_groups.items()):
            question = recs[0]["question"]
            orig_lang = ORIGINAL_LANGUAGES[question]

            if (cat, orig_lang) in IMPOSSIBLE:
                continue

            key = (sid, question, cat)
            if key in seen:
                continue
            seen.add(key)

            trans_cwes = sorted(set(r["trans_cwe"] for r in recs))
            ext = LANG_EXT[orig_lang]
            orig_path = REPO_ROOT / "correct_solutions" / question.replace("Q", "Question") / f"{sid}{ext}"

            trans_files = []
            for r in sorted(recs, key=lambda x: (x["model"], x["language"])):
                trans_ext = LANG_EXT[r["language"]]
                trans_path = (
                    REPO_ROOT
                    / f"translated_solutions_{r['model']}"
                    / question.replace("Q", "Question")
                    / r["language"]
                    / f"{sid}{trans_ext}"
                )
                trans_files.append({
                    "model": r["model"],
                    "language": r["language"],
                    "path": str(trans_path),
                    "trans_cwe": r["trans_cwe"],
                    "trans_explanation": r["trans_explanation"],
                })

            items.append({
                "sample_id": sid,
                "question": question,
                "orig_lang": orig_lang,
                "orig_file": str(orig_path),
                "cwe_category": cat,
                "trans_cwes": trans_cwes,
                "f_sample_count": len(recs),
                "review_question": CWE_QUESTIONS[cat],
                "trans_files": trans_files,
            })

    return items


def main() -> int:
    records = load_records()
    items = build_items(records)

    OUTPUT_ITEMS.parent.mkdir(parents=True, exist_ok=True)
    with open(OUTPUT_ITEMS, "w") as f:
        for item in items:
            f.write(json.dumps(item) + "\n")

    cats = Counter(it["cwe_category"] for it in items)
    print(f"Generated {len(items)} review items -> {OUTPUT_ITEMS}")
    for cat, n in cats.most_common():
        print(f"  {cat}: {n}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
