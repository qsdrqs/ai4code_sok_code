from __future__ import annotations

import csv
import json
from collections import defaultdict
from pathlib import Path
from typing import Any

from config import CLASS_DESCRIPTIONS, CLASS_NAMES


def write_jsonl(records: list[dict], path: Path) -> None:
    with path.open("w", encoding="utf-8") as f:
        for r in records:
            f.write(json.dumps(r, ensure_ascii=False) + "\n")


def write_json(obj: Any, path: Path) -> None:
    with path.open("w", encoding="utf-8") as f:
        json.dump(obj, f, indent=2, ensure_ascii=False)


def write_cwe_flow_csv(rows: list[dict], path: Path) -> None:
    fieldnames = ["orig_cwe", "trans_cwe", "orig_category", "trans_category", "count"]
    with path.open("w", encoding="utf-8", newline="") as f:
        writer = csv.DictWriter(f, fieldnames=fieldnames)
        writer.writeheader()
        writer.writerows(rows)


def _pct(num: int, denom: int) -> str:
    return f"{(100.0 * num / denom):.1f}%" if denom else "n/a"


def _class_row(counts: dict[str, int], total: int, label: str) -> str:
    parts = [f"{counts[n]} ({_pct(counts[n], total)})" for n in CLASS_NAMES.values()]
    return f"| {label} | {total} | " + " | ".join(parts) + " |"


def _dim_table(title: str, dim_key: str, summary: dict, lines: list[str]) -> None:
    lines.append(f"## {title}")
    lines.append("")
    header = "| " + dim_key + " | Total | " + " | ".join(CLASS_NAMES.values()) + " |"
    sep = "|---|---:|" + "".join(["---:|" for _ in CLASS_NAMES])
    lines.append(header)
    lines.append(sep)
    for label, d in summary.items():
        row_counts = d["family"]
        lines.append(_class_row(row_counts, d["total"], label))
    lines.append("")


def write_summary_markdown(
    summary: dict,
    cwe_flow_rows: list[dict],
    path: Path,
) -> None:
    total = summary["total_samples"]
    bf = summary["by_classification_family"]
    bs = summary["by_classification_strict"]

    lines: list[str] = []
    lines.append("# Translated Sample CWE Classification Summary")
    lines.append("")
    lines.append(
        f"Total samples analyzed: **{total}** "
        "(every (model, question, language, sample_id) in the Claude "
        "translation label files, excluding original-language slots)."
    )
    lines.append("")

    lines.append("## 1. 6-Class Distribution (primary = family mode)")
    lines.append("")
    lines.append("| Class | Meaning | Count | % |")
    lines.append("|---|---|---:|---:|")
    for code, name in CLASS_NAMES.items():
        cnt = bf[name]
        lines.append(f"| **{code}** ({name}) | {CLASS_DESCRIPTIONS[name]} | {cnt} | {_pct(cnt, total)} |")
    lines.append("")

    lines.append("## 2. Strict-Mode Distribution (sensitivity)")
    lines.append("")
    lines.append(
        "Strict mode requires exact CWE-ID equality for B (preserved). "
        "Same family but different CWE-ID falls into F (changed)."
    )
    lines.append("")
    lines.append("| Class | Count | % |")
    lines.append("|---|---:|---:|")
    for code, name in CLASS_NAMES.items():
        cnt = bs[name]
        lines.append(f"| **{code}** ({name}) | {cnt} | {_pct(cnt, total)} |")
    lines.append("")

    lines.append("## 3. Four-Class Views")
    lines.append("")
    for mode, view in summary["four_class_views"].items():
        lines.append(f"### {mode}")
        lines.append("")
        for k, v in view.items():
            if k == "note":
                lines.append(f"_{v}_")
            else:
                lines.append(f"- **{k}**: {v}")
        lines.append("")

    _dim_table("4. Distribution by Translation Model (family mode)", "Model", summary["by_model"], lines)
    _dim_table("5. Distribution by Question (family mode)", "Question", summary["by_question"], lines)
    _dim_table("6. Distribution by Target Language (family mode)", "Language", summary["by_language"], lines)

    lines.append("## 7. Uncertain (D) Reason Breakdown")
    lines.append("")
    d_total = bf["D_uncertain"]
    if d_total == 0:
        lines.append("No uncertain samples.")
    else:
        lines.append("| Reason | Count | % of D |")
        lines.append("|---|---:|---:|")
        for reason, cnt in sorted(
            summary["uncertain_reason_breakdown"].items(),
            key=lambda kv: -kv[1],
        ):
            lines.append(f"| {reason} | {cnt} | {_pct(cnt, d_total)} |")
    lines.append("")

    lines.append("## 8. Compile Status Breakdown per Class (family mode)")
    lines.append("")
    lines.append(
        "We do NOT gate samples on compile status; this table shows how many "
        "samples in each class actually compiled. A class with a large "
        "compile-fail population is suspicious."
    )
    lines.append("")
    lines.append("| Class | pass | fail | inconclusive | unknown |")
    lines.append("|---|---:|---:|---:|---:|")
    cb = summary["compile_status_breakdown_within_each_family_class"]
    for name in CLASS_NAMES.values():
        stats = cb.get(name, {})
        lines.append(
            f"| {name} | {stats.get('pass', 0)} | {stats.get('fail', 0)} | "
            f"{stats.get('inconclusive', 0)} | {stats.get('unknown', 0)} |"
        )
    lines.append("")

    lines.append("## 9. Top CWE Flows (both sides vulnerable, class B or F)")
    lines.append("")
    if not cwe_flow_rows:
        lines.append("No flows to report.")
    else:
        lines.append("Top 30 (orig CWE -> trans CWE).")
        lines.append("")
        lines.append("| Orig CWE | Orig category | Trans CWE | Trans category | Count |")
        lines.append("|---|---|---|---|---:|")
        for row in cwe_flow_rows[:30]:
            lines.append(
                f"| {row['orig_cwe'] or '(empty)'} | {row['orig_category']} "
                f"| {row['trans_cwe'] or '(empty)'} | {row['trans_category']} "
                f"| {row['count']} |"
            )
    lines.append("")

    path.write_text("\n".join(lines), encoding="utf-8")


def write_uncertain_samples(records: list[dict], path: Path) -> None:
    lines: list[str] = ["# Uncertain (D-class) Samples", ""]
    by_reason: dict[str, list[dict]] = defaultdict(list)
    for r in records:
        if r["classification_family"] == "D":
            by_reason[r["uncertain_reason"] or "unknown"].append(r)

    if not by_reason:
        lines.append("No uncertain samples. Everything classified cleanly.")
        path.write_text("\n".join(lines), encoding="utf-8")
        return

    for reason, group in sorted(by_reason.items(), key=lambda kv: -len(kv[1])):
        lines.append(f"## Reason: `{reason}` ({len(group)} samples)")
        lines.append("")
        lines.append("| Model | Question | Language | Sample | Orig vul | Orig CWE | Trans vul | Trans CWE |")
        lines.append("|---|---|---|---|---|---|---|---|")
        for r in group[:50]:
            lines.append(
                f"| {r['model']} | {r['question']} | {r['language']} | {r['sample_id']} "
                f"| {r['orig_is_vulnerable']} | {r['orig_cwe'] or '-'} "
                f"| {r['trans_is_vulnerable']} | {r['trans_cwe'] or '-'} |"
            )
        if len(group) > 50:
            lines.append(f"| (+{len(group) - 50} more rows) | | | | | | | |")
        lines.append("")
    path.write_text("\n".join(lines), encoding="utf-8")
