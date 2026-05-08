"""Stratum-level audit of the 150-sample translated-distribution calibration set.

Reports:
  1. Primary stratification cell counts (classification_family: A/B/C/E/F)
     and the reweighting factor used to project the 150 labeled samples
     onto the natural 5004-sample translation population.
  2. Marginal distributions on each secondary dimension (target language,
     translator model, source question / source language, Sonnet 4 predicted
     label) for the 150 labeled and 142-for-F1 sets.
  3. Cross-stratum distribution of the 21 disagreement cases.
"""
from __future__ import annotations

import json
from collections import Counter, defaultdict
from pathlib import Path

import config


def load_jsonl(path: Path) -> list[dict]:
    out = []
    with open(path) as f:
        for line in f:
            out.append(json.loads(line))
    return out


def latest_labels(rows: list[dict]) -> dict[str, dict]:
    out: dict[str, dict] = {}
    for r in rows:
        out[r["key"]] = r
    return out


def natural_population_from_classification(path: Path) -> Counter:
    c: Counter[str] = Counter()
    with open(path) as f:
        for line in f:
            r = json.loads(line)
            cls = r.get("classification_family")
            if cls in config.STRATIFIED_CLASSES:
                c[cls] += 1
    return c


def main() -> None:
    samples = load_jsonl(config.SAMPLES_JSONL)
    samples_by_key = {s["key"]: s for s in samples}
    labels = load_jsonl(config.LABELS_JSONL)
    labels_by_key = latest_labels(labels)

    natural = natural_population_from_classification(config.CLASSIFICATION_PER_SAMPLE)
    total_nat = sum(natural.values())

    print("=" * 70)
    print("NATURAL POPULATION (from classification_per_sample.jsonl)")
    print("=" * 70)
    for cls in config.STRATIFIED_CLASSES:
        print(f"  class {cls}: {natural[cls]}")
    print(f"  TOTAL (A+B+C+E+F): {total_nat}")

    print()
    print("=" * 70)
    print("LABELED STRATUM COUNTS (primary = classification_family)")
    print("=" * 70)
    header = f"{'class':<6} {'labeled':>8} {'used-for-F1':>12} {'natural':>10} {'weight':>10}"
    print(header)
    labeled_counts: Counter[str] = Counter()
    used_f1_counts: Counter[str] = Counter()
    for key, lbl in labels_by_key.items():
        s = samples_by_key[key]
        cls = s["sonnet4_family_class"]
        labeled_counts[cls] += 1
        if lbl["user_verdict"] != "uncertain":
            used_f1_counts[cls] += 1
    for cls in config.STRATIFIED_CLASSES:
        w = (natural[cls] / used_f1_counts[cls]) if used_f1_counts[cls] else 0.0
        print(f"  {cls:<4} {labeled_counts[cls]:>8} {used_f1_counts[cls]:>12} {natural[cls]:>10} {w:>10.2f}")

    print()
    print(f"  TOTALS:   labeled={sum(labeled_counts.values())}   used-for-F1={sum(used_f1_counts.values())}   natural={total_nat}")

    print()
    print("=" * 70)
    print("SECONDARY MARGINAL DISTRIBUTIONS (150 labeled)")
    print("=" * 70)
    for field_key, field_title in [
        ("language", "target language"),
        ("model", "translator model"),
        ("question", "question"),
        ("source_language", "source language"),
        ("sonnet4_is_vulnerable", "Sonnet-4 predicted label (insecure=True)"),
    ]:
        c: Counter = Counter(s[field_key] for s in samples)
        ordered = sorted(c.items(), key=lambda kv: (-kv[1], str(kv[0])))
        inline = ", ".join(f"{k}={v}" for k, v in ordered)
        print(f"  by {field_title}: {inline}")

    print()
    print("=" * 70)
    print("DISAGREEMENT DISTRIBUTION ACROSS STRATA (21 disagree cases)")
    print("=" * 70)
    disagreements = []
    for key, lbl in labels_by_key.items():
        if lbl["user_verdict"] == "disagree":
            disagreements.append((key, samples_by_key[key]))
    assert len(disagreements) == 21, f"expected 21 disagreements, got {len(disagreements)}"

    def breakdown(field_key: str, field_title: str) -> None:
        print(f"  by {field_title}:")
        total = Counter(s[field_key] for _, s in disagreements)
        used_f1: Counter = Counter()
        for key, s in samples_by_key.items():
            if labels_by_key[key]["user_verdict"] != "uncertain":
                used_f1[s[field_key]] += 1
        all_values = set(used_f1.keys()) | set(total.keys())
        rows = [(k, total.get(k, 0), used_f1.get(k, 0)) for k in all_values]
        rows.sort(key=lambda x: (-x[1], str(x[0])))
        for k, n, denom in rows:
            pct = (100.0 * n / denom) if denom else 0.0
            print(f"    {k}: {n}/{denom} ({pct:.1f}%)")

    for fkey, ftitle in [
        ("sonnet4_family_class", "classification_family (primary)"),
        ("language", "target language"),
        ("model", "translator model"),
        ("question", "question"),
        ("source_language", "source language"),
        ("sonnet4_is_vulnerable", "Sonnet-4 predicted label"),
    ]:
        breakdown(fkey, ftitle)
        print()


if __name__ == "__main__":
    main()
