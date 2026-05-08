"""Compute confusion matrix + Cohen's kappa for Sonnet 4 against human labels
on the 142-sample translated-distribution calibration subset (excluding uncertain).

Positive class = insecure.

Writes a small JSON with the raw numbers to calibration_extended.json.
"""
from __future__ import annotations

import json
import math
from collections import Counter
from pathlib import Path

import config


def derive_gt(sample: dict, verdict: str) -> bool | None:
    s4 = bool(sample["sonnet4_is_vulnerable"])
    if verdict == "agree":
        return s4
    if verdict == "disagree":
        return not s4
    return None


def load_labels_latest(path: Path) -> dict[str, dict]:
    """labels.jsonl has append-only entries; later duplicates overwrite.
    Matches the load_labels() behavior in calibrate.py."""
    out: dict[str, dict] = {}
    with open(path) as f:
        for line in f:
            rec = json.loads(line)
            out[rec["key"]] = rec
    return out


def load_samples(path: Path) -> dict[str, dict]:
    out: dict[str, dict] = {}
    with open(path) as f:
        for line in f:
            rec = json.loads(line)
            out[rec["key"]] = rec
    return out


def confusion_and_kappa(samples_by_key: dict, labels_by_key: dict) -> dict:
    tp = fp = tn = fn = 0
    n_uncertain = 0
    for key, lbl in labels_by_key.items():
        if key not in samples_by_key:
            continue
        verdict = lbl["user_verdict"]
        if verdict == "uncertain":
            n_uncertain += 1
            continue
        s = samples_by_key[key]
        s4 = bool(s["sonnet4_is_vulnerable"])
        gt = derive_gt(s, verdict)
        if gt is None:
            continue
        if gt and s4:
            tp += 1
        elif gt and not s4:
            fn += 1
        elif (not gt) and s4:
            fp += 1
        else:
            tn += 1

    n = tp + fp + tn + fn
    accuracy = (tp + tn) / n if n else 0.0
    precision = tp / (tp + fp) if (tp + fp) else 0.0
    recall = tp / (tp + fn) if (tp + fn) else 0.0
    f1 = (2 * precision * recall / (precision + recall)) if (precision + recall) else 0.0
    # Cohen's kappa
    # Rater A = Sonnet 4, Rater B = Human (derived GT)
    #   Sonnet positive count = tp + fp
    #   Human positive count  = tp + fn
    a_pos = tp + fp
    a_neg = fn + tn
    b_pos = tp + fn
    b_neg = fp + tn
    p_o = accuracy
    p_e = (a_pos / n) * (b_pos / n) + (a_neg / n) * (b_neg / n) if n else 0.0
    kappa = (p_o - p_e) / (1 - p_e) if (1 - p_e) > 0 else 0.0

    # SE and 95% CI for kappa (Fleiss 1969 / Cohen 1960 approximation)
    # SE(kappa) = sqrt(p_o * (1 - p_o) / (n * (1 - p_e)^2)); asymptotic
    if n > 0 and p_e < 1.0:
        se = math.sqrt(p_o * (1 - p_o) / (n * (1 - p_e) ** 2))
    else:
        se = 0.0
    kappa_lo = kappa - 1.96 * se
    kappa_hi = kappa + 1.96 * se

    return {
        "n": n,
        "n_uncertain_excluded": n_uncertain,
        "confusion_matrix": {
            "tp": tp,
            "fp": fp,
            "tn": tn,
            "fn": fn,
        },
        "sonnet4_marginals": {
            "predicted_insecure": a_pos,
            "predicted_secure": a_neg,
        },
        "human_marginals": {
            "derived_insecure": b_pos,
            "derived_secure": b_neg,
        },
        "metrics": {
            "accuracy": accuracy,
            "precision": precision,
            "recall": recall,
            "f1": f1,
        },
        "kappa": {
            "kappa": kappa,
            "se": se,
            "ci95_lo": kappa_lo,
            "ci95_hi": kappa_hi,
            "p_observed": p_o,
            "p_expected": p_e,
        },
    }


def main() -> int:
    samples_by_key = load_samples(config.SAMPLES_JSONL)
    labels_by_key = load_labels_latest(config.LABELS_JSONL)
    result = confusion_and_kappa(samples_by_key, labels_by_key)
    cm = result["confusion_matrix"]
    assert cm["tp"] + cm["fp"] + cm["tn"] + cm["fn"] == result["n"]
    print(json.dumps(result, indent=2, sort_keys=True))
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
