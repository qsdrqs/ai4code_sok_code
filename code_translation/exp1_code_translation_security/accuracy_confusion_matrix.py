#!/usr/bin/env python3

import json
import itertools
from pathlib import Path
import matplotlib.pyplot as plt
import numpy as np
from sklearn.metrics import confusion_matrix, precision_recall_fscore_support

# Directories
GT_DIR = Path("security_classifications")
CLASSIFICATION_DIRS = [d for d in Path(".").iterdir() if d.is_dir() and d.name.startswith("security_classifications_llm")]
OUTPUT_DIR = Path("confusion_matrices")
OUTPUT_DIR.mkdir(exist_ok=True)

QUESTIONS = [f"Q{i}" for i in range(1, 6)]

def load_json(path):
    """Loads a JSON file."""
    with open(path, "r", encoding="utf-8") as f:
        return json.load(f)

def to_label(gt_entry):
    """Converts ground truth to a binary label."""
    return 0 if gt_entry["security"] == "secure" else 1

def get_predictions(llm_dir):
    """Gets predictions from an LLM directory."""
    y_true_overall, y_pred_overall = [], []
    for q in QUESTIONS:
        gt_path = GT_DIR / f"{q}.json"
        llm_path = llm_dir / f"{q}.json"
        if not llm_path.exists():
            continue

        gt_data = load_json(gt_path)
        llm_data = load_json(llm_path)

        y_true, y_pred = [], []
        for sample_id, gt_entry in gt_data.items():
            if sample_id in llm_data:
                y_true.append(to_label(gt_entry))
                y_pred.append(1 if llm_data[sample_id]["is_vulnerable"] else 0)

        yield q, (y_true, y_pred)
        y_true_overall.extend(y_true)
        y_pred_overall.extend(y_pred)

    yield "Overall", (y_true_overall, y_pred_overall)

def plot_confusion_matrix(cm, classes, title, output_filename, normalize=False):
    """Plots and saves a confusion matrix."""
    if normalize:
        with np.errstate(divide='ignore', invalid='ignore'):
            cm_normalized = cm.astype('float') / cm.sum(axis=1)[:, np.newaxis]
            cm_normalized = np.nan_to_num(cm_normalized)
        plot_cm = cm_normalized
        fmt = '.2f'
        thresh = 0.5
    else:
        plot_cm = cm
        fmt = 'd'
        thresh = cm.max() / 2.

    plt.imshow(plot_cm, interpolation="nearest", cmap=plt.cm.Blues)
    # plt.title(title)
    cbar = plt.colorbar()
    if normalize:
        cbar.mappable.set_clim(0, 1)
    tick_marks = np.arange(len(classes))
    plt.xticks(tick_marks, classes, rotation=45)
    plt.yticks(tick_marks, classes)

    for i, j in itertools.product(range(plot_cm.shape[0]), range(plot_cm.shape[1])):
        plt.text(
            j,
            i,
            format(plot_cm[i, j], fmt),
            horizontalalignment="center",
            color="white" if plot_cm[i, j] > thresh else "black",
        )

    plt.tight_layout()
    plt.ylabel("True label")
    plt.xlabel("Predicted label")
    plt.savefig(output_filename)
    plt.close()

def main():
    """Main function."""
    print("--- Performance Metrics (for 'Insecure' class) ---")
    for llm_dir in CLASSIFICATION_DIRS:
        model_name = llm_dir.name.replace("security_classifications_llm_", "")

        for question, (y_true, y_pred) in get_predictions(llm_dir):
            if not y_true:
                continue

            # Calculate metrics only for the overall results
            if question == "Overall":
                p, r, f1, _ = precision_recall_fscore_support(
                    y_true, y_pred, labels=[1], average="binary", zero_division=0
                )
                print(f"\nModel: {model_name}")
                print(f"  Precision: {p:.3f}")
                print(f"  Recall:    {r:.3f}")
                print(f"  F1-Score:  {f1:.3f}")

            cm = confusion_matrix(y_true, y_pred, labels=[1, 0])
            cm_display = np.array([[cm[1,1], cm[1,0]], [cm[0,1], cm[0,0]]])
            classes = ["Secure", "Insecure"]

            # Plot absolute and normalized matrices
            plot_confusion_matrix(
                cm_display,
                classes=classes,
                title=f"{model_name} - {question} (Absolute)",
                output_filename=OUTPUT_DIR / f"{model_name}_{question}_absolute.pdf",
                normalize=False
            )
            plot_confusion_matrix(
                cm_display,
                classes=classes,
                title=f"{model_name} - {question} (Normalized)",
                output_filename=OUTPUT_DIR / f"{model_name}_{question}_normalized.pdf",
                normalize=True
            )

if __name__ == "__main__":
    main()