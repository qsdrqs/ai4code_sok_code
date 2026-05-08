from __future__ import annotations

import argparse
import importlib.util
import json
import sys
from pathlib import Path

HUMAN_DIR = Path(__file__).resolve().parent
REPO_ROOT = HUMAN_DIR.parent

_hl_spec = importlib.util.spec_from_file_location("hl_config", HUMAN_DIR / "config.py")
hl_config = importlib.util.module_from_spec(_hl_spec)
assert _hl_spec.loader is not None
_hl_spec.loader.exec_module(hl_config)

sys.path.insert(0, str(REPO_ROOT / "sample_deep_analysis"))

from classifier import classify  # noqa: E402
from cwe_taxonomy import get_cwe_category  # noqa: E402
from loaders import (  # noqa: E402
    _normalize_label,
    load_compile_status,
    load_human_labels,
    load_json,
    load_original_labels,
)


def load_translated_labels_for_models(models: list[str]) -> dict[tuple[str, str, str], dict[str, dict]]:
    result: dict[tuple[str, str, str], dict[str, dict]] = {}
    for model in models:
        for q in hl_config.QUESTIONS:
            for lang in hl_config.TARGET_LANGUAGES:
                if lang == hl_config.ORIGINAL_LANGUAGES.get(q):
                    continue
                path = hl_config.TRANSLATED_LABELS_DIR / model / q / f"{lang}_stats.json"
                data = load_json(path)
                result[(model, q, lang)] = {
                    str(sid): _normalize_label(entry) for sid, entry in data.items()
                }
    return result


def build_records_for_models(
    original: dict[str, dict[str, dict]],
    translated: dict[tuple[str, str, str], dict[str, dict]],
    human: dict[str, dict[str, dict]],
    compile_status: dict[str, str],
) -> list[dict]:
    records: list[dict] = []
    for (model, q, lang), trans_map in translated.items():
        orig_map = original.get(q, {})
        sample_ids = set(orig_map.keys()) | set(trans_map.keys())
        human_map = human.get(q, {})
        for sid in sorted(sample_ids, key=lambda s: (len(s), s)):
            orig_entry = orig_map.get(sid)
            trans_entry = trans_map.get(sid)
            class_family, class_strict, reason = classify(orig_entry, trans_entry)
            compile_key = f"{model}/{q}/{lang}/{sid}"
            cs = compile_status.get(compile_key, "unknown")
            h = human_map.get(sid, {})
            mistakes = list(h.get("mistakes") or [])
            records.append({
                "model": model,
                "question": q,
                "language": lang,
                "sample_id": sid,
                "orig_is_vulnerable": orig_entry["is_vulnerable"] if orig_entry else None,
                "orig_cwe": orig_entry["cwe"] if orig_entry else None,
                "orig_cwe_category": get_cwe_category(orig_entry["cwe"]) if orig_entry else None,
                "orig_explanation": orig_entry["explanation"] if orig_entry else None,
                "trans_is_vulnerable": trans_entry["is_vulnerable"] if trans_entry else None,
                "trans_cwe": trans_entry["cwe"] if trans_entry else None,
                "trans_cwe_category": get_cwe_category(trans_entry["cwe"]) if trans_entry else None,
                "trans_explanation": trans_entry["explanation"] if trans_entry else None,
                "compile_status": cs,
                "human_security_label": h.get("security", ""),
                "human_mistake_tags": mistakes,
                "is_trivial": "trivial" in mistakes,
                "classification_family": class_family,
                "classification_strict": class_strict,
                "uncertain_reason": reason,
            })
    return records


def main() -> int:
    p = argparse.ArgumentParser()
    p.add_argument("--models", nargs="+", default=hl_config.ALL_MODELS,
                   help=f"Models to include (default: {hl_config.ALL_MODELS})")
    p.add_argument("--out", type=Path, default=hl_config.HUMAN_DIR / "classification_per_sample_augmented.jsonl",
                   help="Output augmented JSONL")
    args = p.parse_args()

    print(f"Building augmented classification for models: {args.models}")
    original = load_original_labels()
    print(f"  Loaded original labels for {len(original)} questions ({sum(len(v) for v in original.values())} entries)")

    translated = load_translated_labels_for_models(args.models)
    n_trans = sum(len(v) for v in translated.values())
    print(f"  Loaded translated labels: {n_trans} entries across {len(translated)} (model,q,lang) slots")

    human = load_human_labels()
    compile_status = load_compile_status()

    records = build_records_for_models(original, translated, human, compile_status)
    print(f"  Built {len(records)} classification records")

    args.out.parent.mkdir(parents=True, exist_ok=True)
    with open(args.out, "w") as f:
        for r in records:
            f.write(json.dumps(r) + "\n")
    print(f"  Wrote {args.out}")

    from collections import Counter
    family_counts = Counter(r["classification_family"] for r in records)
    model_counts = Counter(r["model"] for r in records)
    print(f"  Family distribution: {dict(family_counts)}")
    print(f"  Model distribution: {dict(model_counts)}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
