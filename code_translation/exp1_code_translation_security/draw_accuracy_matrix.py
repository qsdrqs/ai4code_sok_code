#!/usr/bin/env python3

# eval_llm_security.py
import json
from pathlib import Path
from collections import defaultdict
from sklearn.metrics import confusion_matrix, precision_recall_fscore_support

GT_DIR = Path("security_classifications")
# LLM_DIR = Path("security_classifications_llm_zero_shot")
LLM_DIR = Path("security_classifications_llm")
QUESTIONS = [f"Q{i}" for i in range(1, 6)]

def load_json(path):
    with open(path, "r", encoding="utf-8") as f:
        return json.load(f)

def to_label(gt_entry):
    """secure -> 0, insecure/partial/else -> 1"""
    return 0 if gt_entry["security"] == "secure" else 1

def eval_one(q):
    gt_path  = GT_DIR  / f"{q}.json"
    llm_path = LLM_DIR / f"{q}.json"
    gt_data  = load_json(gt_path)
    llm_data = load_json(llm_path)

    y_true, y_pred = [], []
    missing = 0
    for sample_id, gt_entry in gt_data.items():
        if sample_id not in llm_data:
            missing += 1
            continue
        y_true.append(to_label(gt_entry))
        y_pred.append(1 if llm_data[sample_id]["is_vulnerable"] else 0)

    # if missing:
    #     print(f"[{q}] ⚠️  {missing} ids missing in LLM results")

    cm = confusion_matrix(y_true, y_pred, labels=[1,0])
    p, r, f1, _ = precision_recall_fscore_support(
        y_true, y_pred, labels=[1], average="binary", zero_division=0
    )
    return cm, p, r, f1, len(y_true)

def pretty_print(title, cm, p, r, f1, n):
    tn, fp, fn, tp = cm.ravel()[::-1]  # reorder to TP, FP, FN, TN
    print(f"\n=== {title} ({n} samples) ===")
    print(f"Confusion Matrix (rows=Actual, cols=Pred):")
    print(f"[[TP {tp:3d}  FP {fp:3d}]\n [FN {fn:3d}  TN {tn:3d}]]")
    print(f"Precision: {p:.3f} | Recall: {r:.3f} | F1: {f1:.3f}")

def main():
    agg_true, agg_pred = [], []

    for q in QUESTIONS:
        cm, p, r, f1, n = eval_one(q)
        pretty_print(q, cm, p, r, f1, n)
        # accumulate for overall stats
        agg_true.extend([1]*cm[0,0] + [1]*cm[0,1] + [0]*cm[1,0] + [0]*cm[1,1])
        agg_pred.extend([1]*cm[0,0] + [0]*cm[0,1] + [1]*cm[1,0] + [0]*cm[1,1])

    # overall metrics
    cm_all = confusion_matrix(agg_true, agg_pred, labels=[1,0])
    p, r, f1, _ = precision_recall_fscore_support(
        agg_true, agg_pred, labels=[1], average="binary", zero_division=0
    )
    pretty_print("OVERALL", cm_all, p, r, f1, len(agg_true))

if __name__ == "__main__":
    main()

