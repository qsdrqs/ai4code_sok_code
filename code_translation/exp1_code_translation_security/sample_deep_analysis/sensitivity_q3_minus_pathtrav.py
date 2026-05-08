"""
Q3 sensitivity: does the language effect emerge once File Handling Issues
(path-traversal CWE-22) is removed from the Q3 vulnerable subset?

Baseline (recomputed from current dataset, Q3 vulnerable subset, 4x2).

Conventions match deep_analysis.md aggregate row:
  - Vulnerable subset = classification_family in {A, B, F}
  - fix=A, not_fix=B+F
  - chi2_contingency(table, correction=False)
  - Cramer's V = sqrt(chi2 / (N * min(rows-1, cols-1)))
  - Per-language fix_rate 95% CI = Wilson via statsmodels.proportion_confint
"""

import json
import math
from collections import Counter
from pathlib import Path

from scipy.stats import chi2_contingency
from statsmodels.stats.proportion import proportion_confint

DATA_PATH = Path(__file__).resolve().parent / "results" / "classification_per_sample.jsonl"
Q3_LANGS = ["C", "Go", "Java", "Rust"]
PATHTRAV_CATEGORY = "File Handling Issues"
UNDERPOWER_CELL_THRESHOLD = 50


def load_records(path: Path):
    return [json.loads(l) for l in path.read_text().splitlines() if l.strip()]


def vuln(records):
    return [r for r in records if r["classification_family"] in {"A", "B", "F"}]


def build_table(subset, langs):
    table = []
    meta = []
    for lang in langs:
        rs = [r for r in subset if r["language"] == lang]
        a = sum(1 for r in rs if r["classification_family"] == "A")
        b = sum(1 for r in rs if r["classification_family"] == "B")
        f = sum(1 for r in rs if r["classification_family"] == "F")
        table.append([a, b + f])
        meta.append((lang, a, b, f))
    return table, meta


def chi_and_v(table):
    chi2, p, dof, _ = chi2_contingency(table, correction=False)
    n = sum(sum(row) for row in table)
    v = math.sqrt(chi2 / (n * min(len(table) - 1, len(table[0]) - 1)))
    return chi2, p, dof, v, n


def wilson_pct(k, n):
    if n == 0:
        return float("nan"), float("nan"), float("nan")
    lo, hi = proportion_confint(k, n, alpha=0.05, method="wilson")
    return (k / n) * 100, lo * 100, hi * 100


def banner(title, w=78):
    print()
    print("=" * w)
    print(title)
    print("=" * w)


def main():
    records = load_records(DATA_PATH)
    q3_vul = [r for r in vuln(records) if r["question"] == "Q3"]

    banner("A. Q3 vulnerable subset orig_cwe_category distribution (sanity check)")
    cwe_counts = Counter(r["orig_cwe_category"] for r in q3_vul)
    n_q3 = len(q3_vul)
    print(f"Q3 vulnerable subset N = {n_q3}")
    for cat, c in cwe_counts.most_common():
        print(f"  {cat}: {c} ({c/n_q3*100:.1f}% of Q3-vul)")

    q3_minus_pathtrav = [r for r in q3_vul if r["orig_cwe_category"] != PATHTRAV_CATEGORY]
    n_after = len(q3_minus_pathtrav)
    n_dropped = n_q3 - n_after
    print(
        f"\nDropping orig_cwe_category=={PATHTRAV_CATEGORY!r}: "
        f"{n_dropped} samples removed, {n_after} remain"
    )

    banner("B. Q3 vulnerable subset MINUS File Handling Issues")
    table, meta = build_table(q3_minus_pathtrav, Q3_LANGS)
    print("4x2 table (rows = Q3_LANGS, cols = [fix=A, not_fix=B+F]):")
    underpowered = []
    for (lang, a, b, f), row in zip(meta, table):
        n = sum(row)
        flag = ""
        if a < UNDERPOWER_CELL_THRESHOLD or row[1] < UNDERPOWER_CELL_THRESHOLD:
            flag = "  <-- UNDERPOWERED CELL (cell <50)"
            underpowered.append(lang)
        print(
            f"  {lang:6s}: A={a:4d}  B={b:4d}  F={f:4d}  -> "
            f"[{row[0]}, {row[1]}], n={n}{flag}"
        )

    if any(sum(row) == 0 for row in table):
        print("\nERROR: a row has zero samples; chi2 not computable")
        return

    chi2, p, dof, v, n = chi_and_v(table)
    print(f"\n  N (Q3-vul minus File Handling) = {n}")
    print(f"  chi2 = {chi2:.2f}")
    print(f"  p    = {p:.3e}")
    print(f"  dof  = {dof}")
    print(f"  Cramer's V = {v:.3f}")
    if underpowered:
        print(f"  WARNING: underpowered cells in: {underpowered}")

    banner("Per-language fix_rate (Wilson 95% CI), Q3 minus File Handling")
    for (lang, a, b, f), row in zip(meta, table):
        rate, lo, hi = wilson_pct(a, sum(row))
        print(f"  {lang}: {rate:.1f}% [{lo:.1f}, {hi:.1f}], n={sum(row)}")

    # Recompute Q3 ALL vulnerable baseline on current dataset
    table_all, _ = build_table(q3_vul, Q3_LANGS)
    chi2_all, p_all, _, v_all, n_all = chi_and_v(table_all)
    sig_threshold = 0.05

    banner("C. Side-by-side")
    print(
        f"  Q3 ALL vulnerable           :  N={n_all},   chi2={chi2_all:>6.2f},  "
        f"p={p_all:.3e},  Cramer's V={v_all:.3f}  (recomputed)"
    )
    print(
        f"  Q3 minus File Handling      :  N={n},    chi2={chi2:>6.2f},  "
        f"p={p:.3e},  Cramer's V={v:.3f}"
    )
    delta_v = v - v_all
    direction = "STRONGER" if delta_v > 0 else "WEAKER" if delta_v < 0 else "unchanged"
    print(f"  Delta V = {delta_v:+.3f}  ({direction})")
    sig_change = (
        "now significant" if p < sig_threshold and p_all >= sig_threshold
        else "still insignificant" if p >= sig_threshold and p_all >= sig_threshold
        else "still significant" if p < sig_threshold and p_all < sig_threshold
        else "now insignificant"
    )
    print(f"  Significance vs alpha=0.05: {sig_change}")

    banner("D. Hypothesis read")
    if v >= 0.15 and p < 0.05:
        verdict = "SUPPORTED"
        detail = (
            "Removing path traversal reveals a meaningful language effect (V>=0.15, p<0.05) "
            "that was masked when File Handling Issues dominated Q3."
        )
    elif p < 0.05 and v < 0.15:
        verdict = "PARTIALLY SUPPORTED"
        detail = (
            "Effect becomes statistically significant but effect size remains small (V<0.15). "
            "Path traversal was masking some signal but the underlying effect is weak."
        )
    elif p >= 0.05 and v >= 0.15:
        verdict = "AMBIGUOUS"
        detail = (
            "Effect size is moderate (V>=0.15) but underpowered N keeps p above 0.05. "
            "Cannot reject null but pattern is consistent with hypothesis."
        )
    else:
        verdict = "NOT SUPPORTED"
        detail = (
            "Even after removing path traversal, language effect remains weak (V<0.15) and "
            "insignificant (p>=0.05). The lack of language effect on Q3 is intrinsic, not a "
            "dilution artifact."
        )
    print(f"Verdict: {verdict}")
    print(f"Reasoning: {detail}")
    if underpowered:
        print(
            f"Caveat: {len(underpowered)} language(s) have an underpowered cell "
            f"({UNDERPOWER_CELL_THRESHOLD} threshold), so any verdict is preliminary."
        )


if __name__ == "__main__":
    main()
