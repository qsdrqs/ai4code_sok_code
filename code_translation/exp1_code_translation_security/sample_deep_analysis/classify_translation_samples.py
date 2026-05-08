#!/usr/bin/env python3
from __future__ import annotations

import sys

from aggregator import aggregate, build_cwe_flow, sanity_check
from config import OUT_DIR
from loaders import (
    load_compile_status,
    load_human_labels,
    load_original_labels,
    load_translated_labels,
)
from records import build_records
from writers import (
    write_cwe_flow_csv,
    write_json,
    write_jsonl,
    write_summary_markdown,
    write_uncertain_samples,
)


def main() -> int:
    OUT_DIR.mkdir(parents=True, exist_ok=True)

    print("Loading original labels...")
    original = load_original_labels()
    n_orig = sum(len(v) for v in original.values())
    print(f"  {n_orig} original records across {len(original)} questions")

    print("Loading translated labels...")
    translated = load_translated_labels()
    n_trans = sum(len(v) for v in translated.values())
    print(f"  {n_trans} translated records across {len(translated)} (model,q,lang) slots")

    print("Loading human labels...")
    human = load_human_labels()

    print("Loading compile status...")
    compile_status = load_compile_status()
    print(f"  {len(compile_status)} compile entries")

    print("Building per-sample records...")
    records = build_records(original, translated, human, compile_status)
    print(f"  {len(records)} records built")

    print("Aggregating...")
    summary = aggregate(records)

    print("Building CWE flows...")
    cwe_flow_rows = build_cwe_flow(records)

    print("Writing outputs...")
    write_jsonl(records, OUT_DIR / "classification_per_sample.jsonl")
    write_json(summary, OUT_DIR / "classification_summary.json")
    write_cwe_flow_csv(cwe_flow_rows, OUT_DIR / "cwe_flow.csv")
    write_summary_markdown(summary, cwe_flow_rows, OUT_DIR / "classification_summary.md")
    write_uncertain_samples(records, OUT_DIR / "uncertain_samples.md")
    print(f"  all outputs written to {OUT_DIR}")

    print("Running sanity checks...")
    warnings = sanity_check(summary)
    if warnings:
        print("SANITY WARNINGS:")
        for w in warnings:
            print(f"  - {w}")
        return 1
    print("  all sanity checks passed")

    total = summary["total_samples"]
    bf = summary["by_classification_family"]
    print()
    print("=" * 60)
    print(f"Total samples: {total}")
    print("Family-mode distribution:")
    for name, cnt in bf.items():
        pct = 100.0 * cnt / total if total else 0.0
        print(f"  {name:<16s}  {cnt:>6d}  ({pct:5.1f}%)")
    print("=" * 60)
    return 0


if __name__ == "__main__":
    sys.exit(main())
