from __future__ import annotations

from collections import Counter, defaultdict

from config import CLASS_NAMES


def _empty_class_counter() -> dict[str, int]:
    return {name: 0 for name in CLASS_NAMES.values()}


def _tally(records: list[dict], key: str) -> dict[str, int]:
    out = _empty_class_counter()
    for r in records:
        out[CLASS_NAMES[r[key]]] += 1
    return out


def _tally_by_dim(records: list[dict], dim_key: str) -> dict[str, dict]:
    groups: dict[str, list[dict]] = defaultdict(list)
    for r in records:
        groups[r[dim_key]].append(r)
    return {
        k: {
            "family": _tally(v, "classification_family"),
            "strict": _tally(v, "classification_strict"),
            "total": len(v),
        }
        for k, v in sorted(groups.items())
    }


def _build_four_class_views(by_family: dict[str, int], total: int) -> dict:
    return {
        "strict_mode_family_primary": {
            "A_removed": by_family["A_removed"],
            "B_preserved": by_family["B_preserved"],
            "C_introduced": by_family["C_introduced"],
            "D_uncertain": by_family["D_uncertain"],
            "E_clean_excluded": by_family["E_clean"],
            "F_changed_excluded": by_family["F_changed"],
            "note": (
                f"Primary view. E and F excluded from A/B/C/D but counted "
                f"in total. A+B+C+D+E+F = {total}."
            ),
        },
        "merged_mode": {
            "A_removed": by_family["A_removed"] + by_family["F_changed"],
            "B_preserved": by_family["B_preserved"] + by_family["E_clean"],
            "C_introduced": by_family["C_introduced"] + by_family["F_changed"],
            "D_uncertain": by_family["D_uncertain"],
            "note": (
                "E merged into B; F double-counted into A and C. "
                f"Unique sample total = {total}."
            ),
        },
        "narrow_mode": {
            "A_removed": by_family["A_removed"],
            "B_preserved": by_family["B_preserved"],
            "C_introduced": by_family["C_introduced"],
            "note": (
                f"Only A/B/C. D/E/F excluded. Covered = "
                f"{by_family['A_removed'] + by_family['B_preserved'] + by_family['C_introduced']} / {total}."
            ),
        },
    }


def aggregate(records: list[dict]) -> dict:
    total = len(records)

    by_family = _tally(records, "classification_family")
    by_strict = _tally(records, "classification_strict")

    by_model = _tally_by_dim(records, "model")
    by_question = _tally_by_dim(records, "question")
    by_language = _tally_by_dim(records, "language")

    by_mql_groups: dict[str, list[dict]] = defaultdict(list)
    for r in records:
        key = f"{r['model']}|{r['question']}|{r['language']}"
        by_mql_groups[key].append(r)
    by_mql = {
        k: {
            "family": _tally(v, "classification_family"),
            "strict": _tally(v, "classification_strict"),
            "total": len(v),
        }
        for k, v in sorted(by_mql_groups.items())
    }

    reason_counter: Counter[str] = Counter()
    for r in records:
        if r["classification_family"] == "D":
            reason_counter[r["uncertain_reason"] or "unknown"] += 1

    compile_by_class: dict[str, Counter[str]] = defaultdict(Counter)
    for r in records:
        compile_by_class[CLASS_NAMES[r["classification_family"]]][r["compile_status"]] += 1

    return {
        "total_samples": total,
        "by_classification_family": by_family,
        "by_classification_strict": by_strict,
        "by_model": by_model,
        "by_question": by_question,
        "by_language": by_language,
        "by_model_question_language": by_mql,
        "four_class_views": _build_four_class_views(by_family, total),
        "uncertain_reason_breakdown": dict(reason_counter),
        "compile_status_breakdown_within_each_family_class": {
            cls: dict(counter) for cls, counter in compile_by_class.items()
        },
    }


def build_cwe_flow(records: list[dict]) -> list[dict]:
    counter: Counter[tuple[str, str, str, str]] = Counter()
    for r in records:
        if r["classification_family"] not in ("B", "F"):
            continue
        key = (
            r["orig_cwe"] or "",
            r["trans_cwe"] or "",
            r["orig_cwe_category"] or "Other",
            r["trans_cwe_category"] or "Other",
        )
        counter[key] += 1
    return [
        {
            "orig_cwe": o_cwe,
            "trans_cwe": t_cwe,
            "orig_category": o_cat,
            "trans_category": t_cat,
            "count": cnt,
        }
        for (o_cwe, t_cwe, o_cat, t_cat), cnt in counter.most_common()
    ]


def sanity_check(summary: dict) -> list[str]:
    warnings: list[str] = []
    total = summary["total_samples"]
    bf = summary["by_classification_family"]
    bs = summary["by_classification_strict"]

    if sum(bf.values()) != total:
        warnings.append(f"family class sum {sum(bf.values())} != total {total}")
    if sum(bs.values()) != total:
        warnings.append(f"strict class sum {sum(bs.values())} != total {total}")

    # Invariant: family mode is looser than strict mode, so the samples
    # strict calls B must be a subset of those family calls B. Conversely,
    # strict must produce at least as many F (changed) as family.
    if bf["B_preserved"] < bs["B_preserved"]:
        warnings.append(f"family B ({bf['B_preserved']}) < strict B ({bs['B_preserved']})")
    if bs["F_changed"] < bf["F_changed"]:
        warnings.append(f"strict F ({bs['F_changed']}) < family F ({bf['F_changed']})")

    # A/C/D/E do not look at CWE match mode, so they MUST match byte-for-byte
    # between family and strict tallies. A mismatch here is a bug in classify().
    for shared in ("A_removed", "C_introduced", "D_uncertain", "E_clean"):
        if bf[shared] != bs[shared]:
            warnings.append(
                f"{shared} differs between family and strict ({bf[shared]} vs {bs[shared]})"
            )
    return warnings
