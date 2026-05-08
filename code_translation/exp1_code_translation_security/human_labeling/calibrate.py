from __future__ import annotations

import argparse
import json
import math
import random
import sys
from collections import Counter, defaultdict
from pathlib import Path
from statistics import mean

import config

EVALUATOR_NAMES = ["sonnet4"]

FIGURE15_NUMBERS = {
    "sonnet4": {"f1": 0.875, "precision": 0.826, "recall": 0.930, "n_original": 199, "n_positive": 143},
}


def load_labels(path: Path) -> dict[str, dict]:
    out = {}
    with open(path) as f:
        for line in f:
            r = json.loads(line)
            out[r["key"]] = r
    return out


def load_samples(path: Path) -> dict[str, dict]:
    out = {}
    with open(path) as f:
        for line in f:
            r = json.loads(line)
            out[r["key"]] = r
    return out


def load_sonnet4_prediction(sample: dict) -> bool | None:
    return sample.get("sonnet4_is_vulnerable")


def compute_natural_population(path: Path) -> dict[str, int]:
    counts: Counter[str] = Counter()
    with open(path) as f:
        for line in f:
            r = json.loads(line)
            cls = r.get("classification_family")
            if cls in config.STRATIFIED_CLASSES:
                counts[cls] += 1
    return dict(counts)


def derive_gt(sample: dict, label: dict) -> bool | None:
    v = label["user_verdict"]
    s4 = sample["sonnet4_is_vulnerable"]
    if v == "agree":
        return bool(s4)
    if v == "disagree":
        return not bool(s4)
    return None


def confusion(
    predictions: dict[str, bool],
    derived_gt: dict[str, bool],
    weights: dict[str, float] | None = None,
) -> dict[str, float]:
    tp = fp = fn = tn = 0.0
    for key, gt in derived_gt.items():
        if key not in predictions:
            continue
        pred = predictions[key]
        w = weights[key] if weights else 1.0
        if gt and pred:
            tp += w
        elif gt and not pred:
            fn += w
        elif not gt and pred:
            fp += w
        else:
            tn += w
    return {"tp": tp, "fp": fp, "fn": fn, "tn": tn}


def metrics_from_confusion(cm: dict[str, float]) -> dict[str, float]:
    tp, fp, fn, tn = cm["tp"], cm["fp"], cm["fn"], cm["tn"]
    total = tp + fp + fn + tn
    acc = (tp + tn) / total if total else 0.0
    prec = tp / (tp + fp) if (tp + fp) else 0.0
    rec = tp / (tp + fn) if (tp + fn) else 0.0
    f1 = 2 * prec * rec / (prec + rec) if (prec + rec) else 0.0
    return {"accuracy": acc, "precision": prec, "recall": rec, "f1": f1, "n": total}


def make_weights(
    samples_by_key: dict[str, dict],
    derived_gt: dict[str, bool],
    natural_pop: dict[str, int],
) -> dict[str, float]:
    class_counts_labeled: Counter[str] = Counter()
    for key in derived_gt:
        cls = samples_by_key[key]["sonnet4_family_class"]
        class_counts_labeled[cls] += 1
    weights: dict[str, float] = {}
    for key in derived_gt:
        cls = samples_by_key[key]["sonnet4_family_class"]
        nat = natural_pop.get(cls, 0)
        n_lbl = class_counts_labeled[cls]
        weights[key] = (nat / n_lbl) if n_lbl else 0.0
    return weights


def bootstrap_f1(
    predictions: dict[str, bool],
    derived_gt: dict[str, bool],
    samples_by_key: dict[str, dict] | None,
    mode: str,
    natural_pop: dict[str, int] | None,
    n_iter: int,
    rng: random.Random,
) -> tuple[float, float]:
    keys = [k for k in derived_gt if k in predictions]
    if not keys:
        return (0.0, 0.0)
    f1s = []
    if mode == "equal":
        for _ in range(n_iter):
            sample = [rng.choice(keys) for _ in keys]
            gt = {k: derived_gt[k] for k in sample}
            pred = {k: predictions[k] for k in sample}
            gt_indexed = {f"{k}#{i}": derived_gt[k] for i, k in enumerate(sample)}
            pred_indexed = {f"{k}#{i}": predictions[k] for i, k in enumerate(sample)}
            cm = confusion(pred_indexed, gt_indexed)
            f1s.append(metrics_from_confusion(cm)["f1"])
    elif mode == "reweighted":
        assert samples_by_key is not None and natural_pop is not None
        by_class: dict[str, list[str]] = defaultdict(list)
        for k in keys:
            by_class[samples_by_key[k]["sonnet4_family_class"]].append(k)
        for _ in range(n_iter):
            gt_indexed: dict[str, bool] = {}
            pred_indexed: dict[str, bool] = {}
            w_indexed: dict[str, float] = {}
            for cls, ks in by_class.items():
                if not ks:
                    continue
                for i in range(len(ks)):
                    k = rng.choice(ks)
                    ik = f"{k}#{cls}#{i}"
                    gt_indexed[ik] = derived_gt[k]
                    pred_indexed[ik] = predictions[k]
                    w_indexed[ik] = natural_pop.get(cls, 0) / len(ks)
            cm = confusion(pred_indexed, gt_indexed, w_indexed)
            f1s.append(metrics_from_confusion(cm)["f1"])
    else:
        raise ValueError(mode)
    f1s.sort()
    lo = f1s[int(0.025 * len(f1s))]
    hi = f1s[int(0.975 * len(f1s)) - 1]
    return (lo, hi)


def mcnemar_exact(b: int, c: int) -> float:
    n = b + c
    if n == 0:
        return 1.0
    k = min(b, c)
    from math import comb
    p = 0.0
    for i in range(k + 1):
        p += comb(n, i) * (0.5 ** n)
    return min(1.0, 2 * p)


def pairwise_mcnemar(
    evaluator_preds: dict[str, dict[str, bool]],
    derived_gt: dict[str, bool],
) -> list[dict]:
    out = []
    names = list(evaluator_preds.keys())
    for i, a in enumerate(names):
        for b in names[i + 1:]:
            b_count = 0
            c_count = 0
            for key, gt in derived_gt.items():
                pa = evaluator_preds[a].get(key)
                pb = evaluator_preds[b].get(key)
                if pa is None or pb is None:
                    continue
                corr_a = (pa == gt)
                corr_b = (pb == gt)
                if corr_a and not corr_b:
                    b_count += 1
                elif corr_b and not corr_a:
                    c_count += 1
            p = mcnemar_exact(b_count, c_count)
            out.append({
                "a": a, "b": b,
                "a_better_count": b_count,
                "b_better_count": c_count,
                "n_disagreements": b_count + c_count,
                "p_value_exact": p,
            })
    return out


def two_proportion_z(p1: float, n1: int, p2: float, n2: int) -> float:
    if n1 == 0 or n2 == 0:
        return 1.0
    pooled = (p1 * n1 + p2 * n2) / (n1 + n2)
    denom = math.sqrt(pooled * (1 - pooled) * (1 / n1 + 1 / n2))
    if denom == 0:
        return 1.0
    z = (p1 - p2) / denom
    return 2 * (1 - 0.5 * (1 + math.erf(abs(z) / math.sqrt(2))))


def ci_overlaps(c1: tuple[float, float], c2: tuple[float, float]) -> bool:
    return not (c1[1] < c2[0] or c2[1] < c1[0])


def per_strata_metrics(
    predictions: dict[str, bool],
    derived_gt: dict[str, bool],
    samples_by_key: dict[str, dict],
    group_key: str,
) -> dict[str, dict]:
    out: dict[str, dict] = {}
    by_group: dict[str, list[str]] = defaultdict(list)
    for k in derived_gt:
        by_group[samples_by_key[k][group_key]].append(k)
    for group, keys in by_group.items():
        gt = {k: derived_gt[k] for k in keys if k in predictions}
        pred = {k: predictions[k] for k in keys if k in predictions}
        cm = confusion(pred, gt)
        out[group] = metrics_from_confusion(cm)
    return out


def compute_evaluator(
    name: str,
    predictions: dict[str, bool],
    derived_gt: dict[str, bool],
    samples_by_key: dict[str, dict],
    natural_pop: dict[str, int],
    n_boot: int,
    rng: random.Random,
) -> dict:
    cm = confusion(predictions, derived_gt)
    eq = metrics_from_confusion(cm)
    weights = make_weights(samples_by_key, derived_gt, natural_pop)
    cm_rw = confusion(predictions, derived_gt, weights)
    rw = metrics_from_confusion(cm_rw)
    ci_eq = bootstrap_f1(predictions, derived_gt, None, "equal", None, n_boot, rng)
    ci_rw = bootstrap_f1(predictions, derived_gt, samples_by_key, "reweighted", natural_pop, n_boot, rng)
    return {
        "name": name,
        "n_predictions_matched": eq["n"],
        "accuracy": eq["accuracy"],
        "precision": eq["precision"],
        "recall": eq["recall"],
        "f1_equal_weight": eq["f1"],
        "f1_equal_weight_ci_95": list(ci_eq),
        "f1_reweighted": rw["f1"],
        "f1_reweighted_ci_95": list(ci_rw),
        "confusion_matrix": cm,
        "per_model": per_strata_metrics(predictions, derived_gt, samples_by_key, "model"),
        "per_question": per_strata_metrics(predictions, derived_gt, samples_by_key, "question"),
        "per_language": per_strata_metrics(predictions, derived_gt, samples_by_key, "language"),
        "per_family_class": per_strata_metrics(predictions, derived_gt, samples_by_key, "sonnet4_family_class"),
    }


def figure15_comparison(evaluator_results: dict[str, dict]) -> dict:
    out: dict[str, dict] = {}
    for name, fig15 in FIGURE15_NUMBERS.items():
        if name not in evaluator_results:
            continue
        ev = evaluator_results[name]
        ci = tuple(ev["f1_equal_weight_ci_95"])
        f1_now = ev["f1_equal_weight"]
        overlaps_now = (ci[0] <= fig15["f1"] <= ci[1])
        n_translated = ev["n_predictions_matched"]
        n_pos_translated = ev["confusion_matrix"]["tp"] + ev["confusion_matrix"]["fn"]
        z_p_precision = two_proportion_z(
            ev["precision"], int(ev["confusion_matrix"]["tp"] + ev["confusion_matrix"]["fp"]),
            fig15["precision"], fig15["n_original"],
        )
        z_p_recall = two_proportion_z(
            ev["recall"], int(n_pos_translated),
            fig15["recall"], fig15["n_positive"],
        )
        out[name] = {
            "figure15_f1": fig15["f1"],
            "translated_f1_equal_weight": f1_now,
            "translated_f1_equal_weight_ci_95": list(ci),
            "f1_in_fig15_ci_check": overlaps_now,
            "two_proportion_z_p_precision": z_p_precision,
            "two_proportion_z_p_recall": z_p_recall,
        }
    return {"test_type": "ci_overlap_and_two_proportion_z", "by_evaluator": out}


def write_markdown(report: dict, out_path: Path) -> None:
    lines: list[str] = []
    lines.append("# Translated-distribution calibration report\n")
    lines.append(f"Total labeled samples: {report['derivation']['n_labeled']}  "
                 f"(agree={report['derivation']['n_agree']}, "
                 f"disagree={report['derivation']['n_disagree']}, "
                 f"uncertain={report['derivation']['n_uncertain']})\n")
    lines.append(f"Samples used for F1 (excluding uncertain): {report['derivation']['n_used_for_binary_f1']}\n")
    lines.append("\n## Evaluator metrics (equal-weight = labeled distribution; reweighted = natural 5004 distribution)\n")
    lines.append("| Evaluator | N | Accuracy | Precision | Recall | F1 equal | 95% CI (eq) | F1 reweighted | 95% CI (rw) |")
    lines.append("|---|---:|---:|---:|---:|---:|---|---:|---|")
    for name, ev in report["evaluators"].items():
        eq_ci = ev["f1_equal_weight_ci_95"]
        rw_ci = ev["f1_reweighted_ci_95"]
        lines.append(
            f"| {name} | {ev['n_predictions_matched']} | {ev['accuracy']:.3f} | {ev['precision']:.3f} | "
            f"{ev['recall']:.3f} | {ev['f1_equal_weight']:.3f} | [{eq_ci[0]:.3f}, {eq_ci[1]:.3f}] | "
            f"{ev['f1_reweighted']:.3f} | [{rw_ci[0]:.3f}, {rw_ci[1]:.3f}] |"
        )
    lines.append("\n## Figure 15 comparison (cross-distribution, non-paired)\n")
    lines.append("| Evaluator | Figure 15 F1 (original-code) | Translated F1 eq-weight | Overlap? | z-test p (prec) | z-test p (recall) |")
    lines.append("|---|---:|---|---|---:|---:|")
    for name, c in report["figure15_comparison"]["by_evaluator"].items():
        overlap = "YES" if c["f1_in_fig15_ci_check"] else "NO"
        ci = c["translated_f1_equal_weight_ci_95"]
        lines.append(
            f"| {name} | {c['figure15_f1']:.3f} | {c['translated_f1_equal_weight']:.3f} "
            f"[{ci[0]:.3f}, {ci[1]:.3f}] | {overlap} | "
            f"{c['two_proportion_z_p_precision']:.3f} | {c['two_proportion_z_p_recall']:.3f} |"
        )
    lines.append("\n## Pairwise McNemar (translated subset, paired)\n")
    lines.append("| Evaluator A | Evaluator B | A-better | B-better | Disagreements | p (exact) |")
    lines.append("|---|---|---:|---:|---:|---:|")
    for m in report["pairwise_mcnemar"]:
        lines.append(
            f"| {m['a']} | {m['b']} | {m['a_better_count']} | {m['b_better_count']} | "
            f"{m['n_disagreements']} | {m['p_value_exact']:.3f} |"
        )
    lines.append("\n## Paste-ready paragraph for reviewer response\n")
    s4 = report["evaluators"].get("sonnet4", {})
    s4_f1 = s4.get("f1_equal_weight", 0.0)
    s4_ci = s4.get("f1_equal_weight_ci_95", [0, 0])
    s4_f1_rw = s4.get("f1_reweighted", 0.0)
    s4_ci_rw = s4.get("f1_reweighted_ci_95", [0, 0])
    comp = report["figure15_comparison"]["by_evaluator"].get("sonnet4", {})
    overlaps = comp.get("f1_in_fig15_ci_check", False)
    overlap_note = "overlapping with" if overlaps else "outside"
    transfer_note = (
        "supporting the transfer of the evaluator's reliability to the translated setting."
        if overlaps
        else "indicating the evaluator's translated-distribution F1 differs from the original-code F1; we therefore report the translated-distribution numbers directly rather than citing Figure 15 for translated claims."
    )
    n_used = report["derivation"]["n_used_for_binary_f1"]
    lines.append(
        f"> We calibrated Sonnet 4's vulnerability-detection F1 on the translated-distribution by "
        f"having a human expert verify Sonnet 4's verdict on a stratified subset of {n_used} translated "
        f"samples (Phase 1 + Phase 2 combined). The equal-weight F1 on the labeled subset is "
        f"{s4_f1:.3f} (95% bootstrap CI [{s4_ci[0]:.3f}, {s4_ci[1]:.3f}]), and the stratification-reweighted "
        f"F1 estimating the full translated population is {s4_f1_rw:.3f} "
        f"(95% bootstrap CI [{s4_ci_rw[0]:.3f}, {s4_ci_rw[1]:.3f}]). "
        f"The Figure 15 original-code F1 of 0.875 is {overlap_note} our translated-distribution "
        f"95% CI, {transfer_note}"
    )
    lines.append("\n")
    out_path.write_text("\n".join(lines))


def main() -> int:
    p = argparse.ArgumentParser()
    p.add_argument("--samples", type=Path, default=config.SAMPLES_JSONL)
    p.add_argument("--labels", type=Path, default=config.LABELS_JSONL)
    p.add_argument("--out-json", type=Path, default=config.CALIBRATION_JSON)
    p.add_argument("--out-md", type=Path, default=config.CALIBRATION_MD)
    p.add_argument("--bootstrap-seed", type=int, default=2026)
    p.add_argument("--bootstrap-iter", type=int, default=1000)
    args = p.parse_args()

    samples_by_key = load_samples(args.samples)
    labels_by_key = load_labels(args.labels)
    natural_pop = compute_natural_population(config.CLASSIFICATION_PER_SAMPLE)

    derived_gt: dict[str, bool] = {}
    for key, lbl in labels_by_key.items():
        if key not in samples_by_key:
            print(f"[WARN] labeled key not in samples: {key}", file=sys.stderr)
            continue
        gt = derive_gt(samples_by_key[key], lbl)
        if gt is not None:
            derived_gt[key] = gt

    sonnet4_preds = {k: bool(s["sonnet4_is_vulnerable"]) for k, s in samples_by_key.items() if s.get("sonnet4_is_vulnerable") is not None}

    rng = random.Random(args.bootstrap_seed)
    evaluators: dict[str, dict] = {}
    evaluators["sonnet4"] = compute_evaluator("sonnet4", sonnet4_preds, derived_gt, samples_by_key, natural_pop, args.bootstrap_iter, rng)

    eval_preds = {"sonnet4": sonnet4_preds}
    pairwise = pairwise_mcnemar(eval_preds, derived_gt)
    fig15 = figure15_comparison(evaluators)

    n_agree = sum(1 for l in labels_by_key.values() if l["user_verdict"] == "agree")
    n_disagree = sum(1 for l in labels_by_key.values() if l["user_verdict"] == "disagree")
    n_uncertain = sum(1 for l in labels_by_key.values() if l["user_verdict"] == "uncertain")

    report = {
        "derivation": {
            "n_labeled": len(labels_by_key),
            "n_agree": n_agree,
            "n_disagree": n_disagree,
            "n_uncertain": n_uncertain,
            "n_used_for_binary_f1": len(derived_gt),
        },
        "natural_population": natural_pop,
        "evaluators": evaluators,
        "pairwise_mcnemar": pairwise,
        "figure15_comparison": fig15,
    }
    args.out_json.parent.mkdir(parents=True, exist_ok=True)
    with open(args.out_json, "w") as f:
        json.dump(report, f, indent=2, sort_keys=True)
    write_markdown(report, args.out_md)
    print(f"Wrote {args.out_json}")
    print(f"Wrote {args.out_md}")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
