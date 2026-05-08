from __future__ import annotations

from classifier import classify
from cwe_taxonomy import get_cwe_category


def build_records(
    original: dict[str, dict[str, dict]],
    translated: dict[tuple[str, str, str], dict[str, dict]],
    human: dict[str, dict[str, dict]],
    compile_status: dict[str, str],
) -> list[dict]:
    records: list[dict] = []

    for (model, q, lang), trans_map in translated.items():
        orig_map = original.get(q, {})
        # Union, not intersection: samples present on only one side must still
        # produce a record so classify() can label them as D.
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
