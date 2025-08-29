#!/usr/bin/env python3

import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
from collections import defaultdict
import os

LEGEND_LABEL_MODELS = {
    "claude": "Claude4",
    "gemini": "Gemini",
    "gpt-4o": "GPT-4o",
    "llama": "Llama4",
    "o3": "o3",
    "qwen3": "Qwen3"
}

LEGEND_LABEL_STRATEGIES = {
    "direct": "direct",
    "cot": "CoT",
    "explain_then_translate": "ETT",
    "1shot": "1-shot",
    "4shot": "4-shot"
}

def calculate_metrics(csv_file):
    """Calculate Pass@1, RP@1, and RD@1 from stat CSV file"""
    if not os.path.exists(csv_file):
        return None, None, None

    df = pd.read_csv(csv_file)
    total = len(df)

    direct_success = df['direct_success'].sum()
    operand_success = df['operand_success'].sum()

    pass_at_1 = direct_success / total * 100  # Pass@1 in percentage
    rp_at_1 = operand_success / total * 100   # RP@1 in percentage

    # RD@1 = 1 - (operand_success / direct_success) if direct_success > 0
    if direct_success > 0:
        rd_at_1 = (1 - operand_success / direct_success) * 100
    else:
        rd_at_1 = 100.0

    return pass_at_1, rp_at_1, rd_at_1

def create_bar_chart():
    models = ['claude', 'llama', 'gpt-4o', 'o3', 'qwen3', 'gemini']
    strategies = ['direct', 'cot', 'explain_then_translate', '1shot', '4shot']
    tasks = ['j2p', 'p2j']

    # Non-LLM baseline data from original.md
    non_llm_data = {
        'j2p_avg': 30.79,  # Average RD@1 for Java->Python
        'p2j_avg': 28.10,  # Average RD@1 for Python->Java
        'j2p_best': (17.97, 'UniXcoder'),  # Best RD@1 for Java->Python
        'p2j_best': (14.29, 'CodeT5')      # Best RD@1 for Python->Java
    }

    # Use matplotlib default colors (tab10 colormap)
    default_colors = plt.cm.tab10(range(10))
    model_colors = {
        'claude': default_colors[0],
        'llama': default_colors[1],
        'gpt-4o': default_colors[2],
        'o3': default_colors[3],
        'gemini': default_colors[4],
        'qwen3': default_colors[5]  # Special case for O3 with expand_python
    }

    # First pass: collect all RD@1 values to determine global y-axis range
    all_rd_values = []
    for task in tasks:
        for model in models:
            for strategy in strategies:
                if model == 'o3_expand_python':
                    csv_file = f'dataset_attack/syntax_{task}_o3_{strategy}_expand_python_stat.csv'
                else:
                    csv_file = f'dataset_attack/syntax_{task}_{model}_{strategy}_stat.csv'
                _, _, rd_at_1 = calculate_metrics(csv_file)
                if rd_at_1 is not None:
                    all_rd_values.append(rd_at_1)

    # Determine common y-axis limits
    if all_rd_values:
        y_min = 0
        y_max = max(all_rd_values) * 1.1  # Add 10% padding
    else:
        y_min, y_max = 0, 100

    # Create separate charts for each task
    for task_idx, task in enumerate(tasks):
        task_name = 'Java->Python' if task == 'j2p' else 'Python->Java'
        task_suffix = 'J2P' if task == 'j2p' else 'P2J'

        # Collect data for this task
        bar_data = []
        bar_labels = []
        colors = []

        for model in models:
            for strategy in strategies:
                if model == 'o3_expand_python':
                    csv_file = f'dataset_attack/syntax_{task}_o3_{strategy}_expand_python_stat.csv'
                else:
                    csv_file = f'dataset_attack/syntax_{task}_{model}_{strategy}_stat.csv'
                _, _, rd_at_1 = calculate_metrics(csv_file)

                if rd_at_1 is not None:
                    bar_data.append(rd_at_1)
                    bar_labels.append(f'{LEGEND_LABEL_STRATEGIES[strategy]}')
                    colors.append(model_colors[model])

        # Create bar chart for this task
        plt.figure(figsize=(15, 8))
        bars = plt.bar(range(len(bar_data)), bar_data, color=colors)

        plt.xlabel('Model-Strategy Combinations')
        plt.ylabel('RD@1 (Attack Success Rate %)')
        # plt.title(f'LLM Code Translation Robustness: {task_name}')
        plt.xticks(range(len(bar_labels)), bar_labels, rotation=45, ha='right', fontsize=14)
        plt.ylim(y_min, y_max)  # Set common y-axis range for both charts
        plt.grid(axis='y', alpha=0.3)

        # Add Non-LLM reference lines
        task_key = 'j2p' if task == 'j2p' else 'p2j'
        non_llm_avg = non_llm_data[f'{task_key}_avg']
        non_llm_best_rd, non_llm_best_model = non_llm_data[f'{task_key}_best']

        # Add horizontal lines for Non-LLM baselines
        plt.axhline(y=non_llm_avg, color='red', linestyle='--', alpha=0.8, linewidth=2,
                   label=f'Non-LLM Average ({non_llm_avg:.2f}%)')
        plt.axhline(y=non_llm_best_rd, color='orange', linestyle='--', alpha=0.8, linewidth=2,
                   label=f'Non-LLM Best ({non_llm_best_model}: {non_llm_best_rd:.2f}%)')

        # # Add value labels on bars
        # for i, (bar, value) in enumerate(zip(bars, bar_data)):
        #     plt.text(bar.get_x() + bar.get_width()/2, bar.get_height() + 0.5,
        #             f'{value:.2f}%', ha='center', va='bottom', fontsize=8)

        # Remove legend from main chart - will be created separately

        plt.tight_layout()
        plt.savefig(f'llm_robustness_{task_suffix.lower()}_chart.pdf', dpi=300, bbox_inches='tight')
        plt.show()

        # Print statistics for this task
        print(f"\n{task_name} Chart Data Summary:")
        print(f"Total combinations: {len(bar_data)}")
        if bar_data:
            print(f"Best performance (lowest RD@1): {min(bar_data):.2f}%")
            print(f"Worst performance (highest RD@1): {max(bar_data):.2f}%")
            print(f"Average RD@1: {np.mean(bar_data):.2f}%")

    # Create separate horizontal legend
    create_horizontal_legend(model_colors, non_llm_data)

def create_horizontal_legend(model_colors, non_llm_data):
    """Create a separate horizontal legend figure"""
    fig, ax = plt.subplots(figsize=(12, 1))
    ax.axis('off')  # Hide axes

    # Create legend elements (combine model colors and reference lines)
    legend_elements = [plt.Rectangle((0,0),1,1, facecolor=color, label=LEGEND_LABEL_MODELS[model])
                      for model, color in model_colors.items()]

    # Add reference line entries to legend
    legend_elements.extend([
        plt.Line2D([0], [0], color='red', linestyle='--', linewidth=2,
                  label=f'Non-LLM Average'),
        plt.Line2D([0], [0], color='orange', linestyle='--', linewidth=2,
                  label=f'Non-LLM Best')
    ])

    # Create horizontal legend
    legend = ax.legend(handles=legend_elements, loc='center', ncol=len(legend_elements),
                      frameon=False)

    plt.tight_layout()
    plt.savefig('legend_horizontal.pdf', dpi=300, bbox_inches='tight')
    plt.show()

    print("\nHorizontal legend saved as 'legend_horizontal.pdf'")

if __name__ == '__main__':
    create_bar_chart()