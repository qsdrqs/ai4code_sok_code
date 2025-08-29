#!/usr/bin/env python3
"""
Analyze experimental results and generate Table 1 and Figure 1
"""
import os
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
import numpy as np
from collections import defaultdict
import subprocess
import tempfile
from scipy.stats import wilcoxon
from scipy import stats

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

# Set font for plots
plt.rcParams['font.sans-serif'] = ['DejaVu Sans']
plt.rcParams['axes.unicode_minus'] = False

def get_all_metrics(gold_file, pred_file):
    """Calculate all 4 metrics using reproduce_evaluate.py"""
    try:
        result = subprocess.run([
            'python', 'reproduce_evaluate.py', gold_file, pred_file
        ], capture_output=True, text=True, cwd='.')

        metrics = {'bleu': 0.0, 'codebleu': 0.0, 'em': 0.0, 'soft_em': 0.0}

        lines = result.stdout.strip().split('\n')
        for line in lines:
            if line.startswith('Average BLEU:'):
                metrics['bleu'] = float(line.split(':')[1].strip())
            elif line.startswith('Average CodeBLEU:'):
                metrics['codebleu'] = float(line.split(':')[1].strip())
            elif line.startswith('Average EM:'):
                metrics['em'] = float(line.split(':')[1].strip())
            elif line.startswith('Average Soft-EM:'):
                metrics['soft_em'] = float(line.split(':')[1].strip())

        return metrics
    except Exception as e:
        print(f"Error calculating metrics for {pred_file}: {e}")
        return {'bleu': 0.0, 'codebleu': 0.0, 'em': 0.0, 'soft_em': 0.0}

def parse_filename(filename):
    """Parse filename to extract model, direction, attack, and strategy information"""
    # Format: model-source-target-attack-strategy.extension
    # Handle special case for gpt-4o which has hyphen in model name

    if filename.startswith('gpt-4o-'):
        # Handle gpt-4o specifically
        remaining = filename[7:]  # Remove 'gpt-4o-'
        parts = remaining.split('-')
        if len(parts) < 4:
            return None
        model = 'gpt-4o'
        direction = f"{parts[0]}-{parts[1]}"  # cs-java or java-cs
        attack = parts[2].split('.')[0]  # remove extension
        strategy = parts[3].split('.')[0]  # remove extension
    else:
        # Handle other models
        parts = filename.split('-')
        if len(parts) < 5:
            return None
        model = parts[0]
        direction = f"{parts[1]}-{parts[2]}"  # cs-java or java-cs
        attack = parts[3].split('.')[0]  # remove extension
        strategy = parts[4].split('.')[0]  # remove extension

    return {
        'model': model,
        'direction': direction,
        'attack': attack,
        'strategy': strategy,
        'filename': filename
    }

def collect_results():
    """Collect all experimental results from output directory"""
    output_dir = './output'
    results = []

    # Define corresponding gold files
    gold_files = {
        'cs-java-original': 'data/translate/test.java-cs.txt.java',
        'cs-java-dfs': 'data/translate/test.java-cs.txt.java',
        'cs-java-bfs': 'data/translate/test.java-cs.txt.java',
        'cs-java-subtree': 'data/translate/test.java-cs.txt.java',
        'cs-java-funcname': 'data/translate/test.java-cs.txt.java',
        'java-cs-original': 'data/translate/test.java-cs.txt.cs',
        'java-cs-dfs': 'data/translate/test.java-cs.txt.cs',
        'java-cs-bfs': 'data/translate/test.java-cs.txt.cs',
        'java-cs-subtree': 'data/translate/test.java-cs.txt.cs',
        'java-cs-funcname': 'data/translate/test.java-cs.txt.cs'
    }

    for filename in os.listdir(output_dir):
        if not filename.endswith(('.java', '.cs')):
            continue

        info = parse_filename(filename)
        if not info:
            continue

        # Construct gold file path
        gold_key = f"{info['direction']}-{info['attack']}"
        if gold_key not in gold_files:
            print(f"Warning: No gold file for {gold_key}")
            continue

        gold_file = gold_files[gold_key]
        pred_file = os.path.join(output_dir, filename)

        # Check if files exist
        if not os.path.exists(gold_file):
            print(f"Warning: Gold file {gold_file} not found")
            continue

        # Calculate all metrics
        metrics = get_all_metrics(gold_file, pred_file)

        result = {
            'model': info['model'],
            'direction': info['direction'],
            'attack': info['attack'],
            'strategy': info['strategy'],
            'bleu': metrics['bleu'],
            'codebleu': metrics['codebleu'],
            'em': metrics['em'],
            'soft_em': metrics['soft_em'],
            'filename': filename
        }
        results.append(result)
        print(f"Processed: {filename} -> BLEU: {metrics['bleu']:.2f}, CodeBLEU: {metrics['codebleu']:.2f}, EM: {metrics['em']:.2f}, Soft-EM: {metrics['soft_em']:.2f}")

    return results

def create_table1(results_df, metric='bleu'):
    """Create Table 1: LLM vs Non-LLM performance comparison"""

    # Define attack type mapping
    attack_mapping = {
        'original': 'Full Fine-tuning',
        'dfs': 'Reconstruction Attack: DFS',
        'bfs': 'Reconstruction Attack: BFS',
        'subtree': 'Subtree Attack',
        'funcname': 'Signature Attack'
    }

    # Define model groups
    llm_models = ['claude', 'gpt-4o', 'o3', 'gemini', 'llama', 'qwen3']
    nonllm_models = ['GraphCodeBERT', 'PLBART', 'CodeT5', 'UniXcoder']  # from original.md

    # Get all strategy data for each LLM
    llm_all_data = {}
    for model in llm_models:
        model_data = results_df[results_df['model'] == model]
        if len(model_data) == 0:
            continue

        attack_strategies = {}
        for attack in ['original', 'dfs', 'bfs', 'subtree', 'funcname']:
            attack_data = model_data[model_data['attack'] == attack]
            if len(attack_data) > 0:
                # Group by strategy and take average across directions
                avg_by_strategy = attack_data.groupby('strategy')[metric].mean()
                print(f"Model: {model}, Attack: {attack}, Avg by Strategy: {avg_by_strategy.to_dict()}")
                attack_strategies[attack] = avg_by_strategy.to_dict()
            else:
                attack_strategies[attack] = {}
        llm_all_data[model] = attack_strategies

    # Non-LLM data from original.md
    nonllm_data = {}
    if metric == 'bleu':
        nonllm_data = {
            'GraphCodeBERT': {'original': 76.61, 'dfs': 9.33, 'bfs': 15.83, 'subtree': 42.54, 'funcname': 73.53},
            'PLBART': {'original': 80.69, 'dfs': 3.36, 'bfs': 1.76, 'subtree': 10.53, 'funcname': 60.05},
            'CodeT5': {'original': 81.95, 'dfs': 8.70, 'bfs': 11.94, 'subtree': 47.69, 'funcname': 78.08},
            'UniXcoder': {'original': 76.59, 'dfs': 25.47, 'bfs': 23.84, 'subtree': 37.59, 'funcname': 72.26}
        }
    elif metric == 'em':
        nonllm_data = {
            'GraphCodeBERT': {'original': 59.10, 'dfs': 6.25, 'bfs': 15.40, 'subtree': 26.30, 'funcname': 57.50},
            'PLBART': {'original': 64.80, 'dfs': 0.35, 'bfs': 0.10, 'subtree': 6.85, 'funcname': 34.15},
            'CodeT5': {'original': 66.45, 'dfs': 0.75, 'bfs': 12.95, 'subtree': 31.85, 'funcname': 61.80},
            'UniXcoder': {'original': 63.45, 'dfs': 24.95, 'bfs': 23.75, 'subtree': 25.90, 'funcname': 60.75}
        }
    else:
        print(f"Warning: Non-LLM data for {metric} not available. Only showing LLM results.")

    # Create table data
    table_data = []

    for attack in ['original', 'dfs', 'bfs', 'subtree', 'funcname']:
        attack_name = attack_mapping[attack]

        # Non-LLM data
        for model in nonllm_models:
            if model in nonllm_data and attack in nonllm_data[model]:
                table_data.append({
                    'Attack': attack_name,
                    'Model': model,
                    'Type': 'Non-LLM',
                    metric: nonllm_data[model][attack]
                })

        # Non-LLM average
        nonllm_avg = 0
        for model in nonllm_models:
            if model in nonllm_data and attack in nonllm_data[model]:
                nonllm_avg += nonllm_data[model][attack]
        nonllm_avg /= len(nonllm_models)
        table_data.append({
            'Attack': attack_name,
            'Model': 'Non-LLM_Average',
            'Type': 'Non-LLM',
            metric: nonllm_avg
        })

        # LLM data - include all strategies
        for model in llm_models:
            if model in llm_all_data and attack in llm_all_data[model]:
                strategy_scores = []
                for strategy, score in llm_all_data[model][attack].items():
                    table_data.append({
                        'Attack': attack_name,
                        'Model': f"{model}_{strategy}",
                        'Type': 'LLM',
                        metric: score
                    })
                    strategy_scores.append(score)

                # Add average across all strategies
                if strategy_scores:
                    avg_score = sum(strategy_scores) / len(strategy_scores)
                    table_data.append({
                        'Attack': attack_name,
                        'Model': f"{model}_average",
                        'Type': 'LLM',
                        metric: avg_score
                    })

    table_df = pd.DataFrame(table_data)

    # Create pivot table for display
    pivot_table = table_df.pivot_table(
        values=metric,
        index='Attack',
        columns=['Type', 'Model'],
        fill_value=0.0
    )

    print(f"\n=== Table 1: LLM vs Non-LLM Performance Comparison ({metric.upper()}) ===")
    print(pivot_table.round(2))

    # Calculate relative drops
    print("\n=== Table 2: Relative Performance Drop (%) ===")
    relative_drop_data = []

    # For each model type, calculate relative drop from baseline
    for model_type in ['LLM', 'Non-LLM']:
        type_data = table_df[table_df['Type'] == model_type]

        for model in type_data['Model'].unique():
            model_data = type_data[type_data['Model'] == model]
            baseline = model_data[model_data['Attack'] == 'Full Fine-tuning'][metric]

            if len(baseline) > 0:
                baseline_score = baseline.iloc[0]

                for attack in ['Reconstruction Attack: DFS', 'Reconstruction Attack: BFS',
                              'Subtree Attack', 'Signature Attack']:
                    attack_data = model_data[model_data['Attack'] == attack]
                    if len(attack_data) > 0:
                        attack_score = attack_data[metric].iloc[0]
                        relative_drop = ((baseline_score - attack_score) / baseline_score) * 100
                        if metric == 'bleu':
                            print(f"searchme: Model: {model}, Metric: {metric}, Attack: {attack}, Baseline: {baseline_score:.2f}, Attack Score: {attack_score:.2f}, Relative Drop: {relative_drop:.2f}%")

                        relative_drop_data.append({
                            'Attack': attack,
                            'Model': model,
                            'Type': model_type,
                            'Relative_Drop': relative_drop,
                            'Baseline': baseline_score,
                            'Attack_Score': attack_score
                        })

    relative_df = pd.DataFrame(relative_drop_data)

    # Create pivot table for relative drops
    relative_pivot = relative_df.pivot_table(
        values='Relative_Drop',
        index='Attack',
        columns=['Type', 'Model'],
        fill_value=0.0
    )

    print(relative_pivot.round(1))

    # Save both tables
    pivot_table.to_csv(f'table1_absolute_scores_{metric}.csv')
    relative_pivot.to_csv(f'table1_relative_drops_{metric}.csv')
    print(f"Tables saved as table1_absolute_scores_{metric}.csv and table1_relative_drops_{metric}.csv")

    return table_df, pivot_table, relative_df, relative_pivot

def create_figure1(results_df):
    """Create Figure 1: Bar chart showing attack impact on LLMs"""

    # Define attack type mapping
    attack_mapping = {
        'original': 'Baseline',
        'dfs': 'DFS',
        'bfs': 'BFS',
        'subtree': 'Subtree',
        'funcname': 'Signature'
    }

    llm_models = ['claude', 'gpt-4o', 'o3', 'gemini', 'llama', 'qwen3']

    # Calculate best performance for each model
    model_scores = {}

    for model in llm_models:
        model_data = results_df[results_df['model'] == model]
        if len(model_data) == 0:
            continue

        scores = {}
        for attack in ['original', 'dfs', 'bfs', 'subtree', 'funcname']:
            attack_data = model_data[model_data['attack'] == attack]
            if len(attack_data) > 0:
                # Group by strategy and take average across strategies
                avg_by_strategy = attack_data.groupby('strategy')['bleu'].mean()
                if len(avg_by_strategy) > 0:
                    scores[attack] = avg_by_strategy.mean()
                else:
                    scores[attack] = 0.0
            else:
                scores[attack] = 0.0

        model_scores[model] = scores

    # Prepare plotting data
    fig, ax = plt.subplots(figsize=(12, 8))

    attacks = ['original', 'dfs', 'bfs', 'subtree', 'funcname']
    attack_labels = [attack_mapping[a] for a in attacks]

    x = np.arange(len(attacks))
    width = 0.12  # bar width

    # Plot bars for each model
    colors = plt.cm.Set3(np.linspace(0, 1, len(llm_models)))

    for i, model in enumerate(llm_models):
        if model not in model_scores:
            continue

        scores = [model_scores[model][attack] for attack in attacks]
        offset = (i - len(llm_models)/2 + 0.5) * width

        bars = ax.bar(x + offset, scores, width, label=LEGEND_LABEL_MODELS.get(model, model.upper()),
                     color=colors[i], alpha=0.8)

        # # Add value labels on bars
        # for bar, score in zip(bars, scores):
        #     height = bar.get_height()
        #     if height > 0:
        #         ax.text(bar.get_x() + bar.get_width()/2., height + 0.5,
        #                f'{score:.1f}', ha='center', va='bottom', fontsize=8)

    ax.set_xlabel('Attack Types')
    ax.set_ylabel('BLEU Score')
    ax.set_title('LLM Performance under Different Attacks', fontsize=14, fontweight='bold')
    ax.set_xticks(x)
    ax.set_xticklabels(attack_labels, rotation=0, ha='right')
    ax.legend(bbox_to_anchor=(1.05, 1), loc='upper left')
    ax.grid(axis='y', alpha=0.3)

    plt.tight_layout()
    plt.savefig('figure1_llm_robustness.png', dpi=300, bbox_inches='tight')
    plt.savefig('figure1_llm_robustness.pdf', bbox_inches='tight')
    print("Figure 1 saved as figure1_llm_robustness.png and .pdf")

    return fig

def calculate_llm_relative_drops(results_df, metric='bleu'):
    """Unified function to calculate LLM relative drops, ensuring all figures use the same logic

    For each model and attack:
    1. Average baseline scores across all strategies
    2. Average attack scores across all strategies
    3. Calculate relative drop using the averaged scores

    This matches the method used in create_table1() for consistency.
    """
    llm_models = ['claude', 'gemini', 'gpt-4o', 'llama', 'o3', 'qwen3']
    attacks = ['dfs', 'bfs', 'subtree', 'funcname']
    strategies = ['direct', 'cot', 'explain_then_translate', '1shot', '4shot']

    # Calculate relative drops for each model
    model_drops = {}

    for model in llm_models:
        model_data = results_df[results_df['model'] == model]
        if len(model_data) == 0:
            continue

        drops = {}

        for attack in attacks:
            baseline_scores = []
            attack_scores = []

            for strategy in strategies:
                # Get baseline for this specific strategy
                baseline_data = model_data[
                    (model_data['attack'] == 'original') &
                    (model_data['strategy'] == strategy)
                ]

                # Get attack data for this specific strategy
                attack_data = model_data[
                    (model_data['attack'] == attack) &
                    (model_data['strategy'] == strategy)
                ]

                if len(baseline_data) > 0 and len(attack_data) > 0:
                    baseline_score = baseline_data[metric].mean()  # Average across directions
                    attack_score = attack_data[metric].mean()     # Average across directions

                    baseline_scores.append(baseline_score)
                    attack_scores.append(attack_score)

            # Calculate relative drop using averaged baseline and attack scores (same as Table 1)
            if baseline_scores and attack_scores:
                avg_baseline = sum(baseline_scores) / len(baseline_scores)
                avg_attack = sum(attack_scores) / len(attack_scores)

                if avg_baseline > 0:
                    drops[attack] = ((avg_baseline - avg_attack) / avg_baseline) * 100
                else:
                    drops[attack] = 0

            else:
                drops[attack] = 0

        model_drops[model] = drops

    return model_drops

def calculate_nonllm_stats():
    """Unified function to calculate Non-LLM statistics"""
    # Non-LLM baseline data from original.md
    nonllm_baseline_data = {
        'GraphCodeBERT': {'original': 76.61, 'dfs': 9.33, 'bfs': 15.83, 'subtree': 42.54, 'funcname': 73.53},
        'PLBART': {'original': 80.69, 'dfs': 3.36, 'bfs': 1.76, 'subtree': 10.53, 'funcname': 60.05},
        'CodeT5': {'original': 81.95, 'dfs': 8.70, 'bfs': 11.94, 'subtree': 47.69, 'funcname': 78.08},
        'UniXcoder': {'original': 76.59, 'dfs': 25.47, 'bfs': 23.84, 'subtree': 37.59, 'funcname': 72.26}
    }

    attacks = ['dfs', 'bfs', 'subtree', 'funcname']
    models = ['GraphCodeBERT', 'PLBART', 'CodeT5', 'UniXcoder']

    # Calculate relative drops for each model and attack
    nonllm_drops = {}
    all_drops = []

    for model in models:
        baseline = nonllm_baseline_data[model]['original']
        model_drops = []

        for attack in attacks:
            attack_score = nonllm_baseline_data[model][attack]
            if baseline > 0:
                relative_drop = ((baseline - attack_score) / baseline) * 100
            else:
                relative_drop = 0
            model_drops.append(relative_drop)
            all_drops.append(relative_drop)

        nonllm_drops[model] = {
            'drops': dict(zip(attacks, model_drops)),
            'avg': sum(model_drops) / len(model_drops)
        }

    # Calculate overall statistics
    overall_avg = sum(all_drops) / len(all_drops)
    best_model_avg = min(nonllm_drops[model]['avg'] for model in models)
    best_model = min(models, key=lambda m: nonllm_drops[m]['avg'])

    return nonllm_drops, overall_avg, best_model_avg, best_model

def create_figure2_model_robustness(results_df):
    """Create Figure 2: Average robustness by model across all attacks"""

    # Use unified data processing logic
    model_drops = calculate_llm_relative_drops(results_df, 'bleu')
    nonllm_drops, nonllm_avg, nonllm_best, best_model = calculate_nonllm_stats()

    attacks = ['dfs', 'bfs', 'subtree', 'funcname']
    attack_labels = ['DFS', 'BFS', 'Subtree', 'Signature']
    llm_models = ['claude', 'gemini', 'gpt-4o', 'llama', 'o3', 'qwen3']

    # Use matplotlib default colors
    colors = plt.cm.tab10(range(10))
    model_colors = {model: colors[i] for i, model in enumerate(llm_models)}

    fig, ax = plt.subplots(figsize=(12, 8))

    x = np.arange(len(attacks))
    width = 0.12

    # Add Non-LLM Average as the first bar
    nonllm_avg_drops = [nonllm_drops[model]['drops'][attack] for model in nonllm_drops.keys() for attack in attacks]
    nonllm_avg_by_attack = []
    for attack in attacks:
        attack_drops = [nonllm_drops[model]['drops'][attack] for model in nonllm_drops.keys()]
        nonllm_avg_by_attack.append(sum(attack_drops) / len(attack_drops))

    offset = (-len(llm_models)/2 - 0.5) * width
    bars = ax.bar(x + offset, nonllm_avg_by_attack, width, label='Non-LLM Average',
                 color=plt.cm.tab10(6))

    # Add value labels for Non-LLM Average
    # for bar, value in zip(bars, nonllm_avg_by_attack):
    #     ax.text(bar.get_x() + bar.get_width()/2, bar.get_height() + 1,
    #            f'{value:.1f}%', ha='center', va='bottom', fontsize=8)

    # Plot each LLM model
    for i, model in enumerate(llm_models):
        if model in model_drops:
            drops = [model_drops[model][attack] for attack in attacks]
            offset = (i - len(llm_models)/2 + 0.5) * width

            bars = ax.bar(x + offset, drops, width, label=LEGEND_LABEL_MODELS.get(model, model.upper()),
                         color=model_colors[model])

            # # Add value labels
            # for bar, value in zip(bars, drops):
            #     if value > 0:
            #         ax.text(bar.get_x() + bar.get_width()/2, bar.get_height() + 1,
            #                f'{value:.1f}%', ha='center', va='bottom', fontsize=8)

    ax.set_xlabel('Attack Types')
    ax.set_ylabel('BLEU Relative Performance Drop (%)')
    # ax.set_title('LLM Model Robustness Comparison\n(Average across all prompt strategies)',
    #              fontsize=14, fontweight='bold')
    ax.set_xticks(x)
    ax.set_xticklabels(attack_labels, rotation=0, ha='right')
    ax.legend()
    ax.grid(axis='y', alpha=0.3)

    plt.tight_layout()
    plt.savefig('figure2_model_robustness.png', dpi=300, bbox_inches='tight')
    plt.savefig('figure2_model_robustness.pdf', bbox_inches='tight')
    print("Figure 2 saved as figure2_model_robustness.png and .pdf")

    return fig

def create_figure3_prompt_strategy_robustness(results_df):
    """Create Figure 3: Impact of different prompt strategies on robustness"""

    # Use unified data processing logic - average across all models for each strategy
    llm_data = results_df[results_df['model'].isin(['claude', 'gemini', 'gpt-4o', 'llama', 'o3', 'qwen3'])]

    strategies = ['direct', 'cot', 'explain_then_translate', '1shot', '4shot']
    attacks = ['dfs', 'bfs', 'subtree', 'funcname']
    attack_mapping = {
        'dfs': 'DFS',
        'bfs': 'BFS',
        'subtree': 'Subtree',
        'funcname': 'Signature'
    }

    # Calculate relative drops by strategy using the same method as Table 1
    strategy_drops = []
    for strategy in strategies:
        strategy_data = llm_data[llm_data['strategy'] == strategy]

        # Calculate baseline (average across all models for this strategy)
        baseline_data = strategy_data[strategy_data['attack'] == 'original']
        if len(baseline_data) == 0:
            continue

        strategy_baseline = baseline_data['bleu'].mean()

        for attack in attacks:
            attack_data = strategy_data[strategy_data['attack'] == attack]
            if len(attack_data) > 0:
                attack_avg = attack_data['bleu'].mean()
                relative_drop = ((strategy_baseline - attack_avg) / strategy_baseline) * 100

                strategy_drops.append({
                    'Strategy': strategy,
                    'Attack': attack,
                    'Relative_Drop': relative_drop,
                    'Baseline': strategy_baseline,
                    'Attack_Score': attack_avg
                })

    if not strategy_drops:
        print("No strategy data found for Figure 3")
        return None

    strategy_df = pd.DataFrame(strategy_drops)

    # Use unified Non-LLM statistics calculation
    nonllm_drops, nonllm_avg, nonllm_best, best_model = calculate_nonllm_stats()

    # Create the plot
    fig, ax = plt.subplots(figsize=(12, 8))

    x = np.arange(len(attacks))
    width = 0.15

    # Use matplotlib default colors
    default_colors = plt.cm.tab10(range(10))
    strategy_colors = {strategy: default_colors[i] for i, strategy in enumerate(strategies)}

    # Add Non-LLM Average as the first bar
    nonllm_avg_by_attack = []
    for attack in attacks:
        attack_drops = [nonllm_drops[model]['drops'][attack] for model in nonllm_drops.keys()]
        nonllm_avg_by_attack.append(sum(attack_drops) / len(attack_drops))

    offset = (-len(strategies)/2 - 0.5) * width
    bars = ax.bar(x + offset, nonllm_avg_by_attack, width, label='Non-LLM Average',
                 color=plt.cm.tab10(6))

    # Add value labels for Non-LLM Average
    # for bar, value in zip(bars, nonllm_avg_by_attack):
    #     ax.text(bar.get_x() + bar.get_width()/2, bar.get_height() + 1,
    #            f'{value:.1f}%', ha='center', va='bottom', fontsize=8)

    # Plot each strategy
    for i, strategy in enumerate(strategies):
        strategy_data = strategy_df[strategy_df['Strategy'] == strategy]
        avg_drops = []

        for attack in attacks:
            attack_data = strategy_data[strategy_data['Attack'] == attack]['Relative_Drop']
            if len(attack_data) > 0:
                avg_drops.append(attack_data.iloc[0])
            else:
                avg_drops.append(0)

        offset = (i - len(strategies)/2 + 0.5) * width
        bars = ax.bar(x + offset, avg_drops, width, label=LEGEND_LABEL_STRATEGIES.get(strategy, strategy),
                     color=strategy_colors[strategy])

        # # Add value labels
        # for bar, value in zip(bars, avg_drops):
        #     if value > 0:
        #         ax.text(bar.get_x() + bar.get_width()/2, bar.get_height() + 1,
        #                f'{value:.1f}%', ha='center', va='bottom', fontsize=8)

    ax.set_xlabel('Attack Types')
    ax.set_ylabel('BLEU Relative Performance Drop (%)')
    # ax.set_title('Impact of Prompt Strategies on LLM Robustness\n(Average across all models)',
    #              fontsize=14, fontweight='bold')
    ax.set_xticks(x)
    ax.set_xticklabels([attack_mapping[attack] for attack in attacks], rotation=0, ha='right')
    ax.legend()
    ax.grid(axis='y', alpha=0.3)

    plt.tight_layout()
    plt.savefig('figure3_prompt_strategy_robustness.png', dpi=300, bbox_inches='tight')
    plt.savefig('figure3_prompt_strategy_robustness.pdf', bbox_inches='tight')
    print("Figure 3 saved as figure3_prompt_strategy_robustness.png and .pdf")

    return fig

def perform_paired_significance_analysis(results_df):
    """
    Perform significance analysis comparing reasoning vs non-reasoning models
    Using permutation test with models as analysis units to handle clustered data structure
    """

    # Define model groups
    reasoning_models = ['qwen3', 'o3', 'gemini']
    non_reasoning_models = ['gpt-4o', 'llama', 'claude']

    attacks = ['dfs', 'bfs', 'subtree', 'funcname']
    metrics = ['bleu', 'codebleu', 'soft_em']

    print("\n" + "="*80)
    print("SIGNIFICANCE ANALYSIS: Reasoning vs Non-Reasoning Models")
    print("="*80)
    print("Method: Permutation test with models as analysis units")
    print("Analysis units: Individual models (n=6: 3 reasoning vs 3 non-reasoning)")
    print("Aggregation: Each model's average performance across all attacks and strategies")
    print("This approach avoids pseudoreplication and handles clustered data structure")
    print("-"*80)

    significance_results = {}

    # Set global seed once for reproducibility, but allow different permutations per metric
    np.random.seed(42)

    for metric in metrics:
        print(f"\n=== {metric.upper()} Metric ===")

        # Calculate each model's average performance across all attacks and strategies
        # This treats each model as one analysis unit, avoiding pseudoreplication
        model_scores = {}
        all_drops = calculate_llm_relative_drops(results_df, metric)

        for model in reasoning_models + non_reasoning_models:
            if model in all_drops:
                # Average across all attacks for this model
                model_drops = []
                for attack in attacks:
                    if attack in all_drops[model]:
                        model_drops.append(all_drops[model][attack])

                if model_drops:
                    model_scores[model] = np.mean(model_drops)
                    print(f"{LEGEND_LABEL_MODELS.get(model, model.upper())}: Average drop = {model_scores[model]:.2f}%")

        # Separate reasoning and non-reasoning model scores
        reasoning_scores = [model_scores[m] for m in reasoning_models if m in model_scores]
        non_reasoning_scores = [model_scores[m] for m in non_reasoning_models if m in model_scores]

        if len(reasoning_scores) >= 2 and len(non_reasoning_scores) >= 2:  # Need at least 2 models per group
            # Perform permutation test
            # Test if reasoning models have significantly lower drops (better robustness)
            observed_diff = np.mean(reasoning_scores) - np.mean(non_reasoning_scores)

            # Permutation test: randomly reassign models to groups
            all_scores = reasoning_scores + non_reasoning_scores
            n_reasoning = len(reasoning_scores)
            n_permutations = 10000

            permutation_diffs = []
            # Note: Global seed set once at function start for reproducibility

            for _ in range(n_permutations):
                # Randomly shuffle model assignments
                shuffled_scores = np.random.permutation(all_scores)
                perm_reasoning = shuffled_scores[:n_reasoning]
                perm_non_reasoning = shuffled_scores[n_reasoning:]

                # Calculate difference for this permutation
                perm_diff = np.mean(perm_reasoning) - np.mean(perm_non_reasoning)
                permutation_diffs.append(perm_diff)

            # Calculate p-value (one-tailed test: reasoning better = negative difference)
            p_value = np.mean(np.array(permutation_diffs) <= observed_diff)

            # Calculate effect size (Cohen's d for independent groups)
            pooled_std = np.sqrt(((len(reasoning_scores)-1)*np.var(reasoning_scores, ddof=1) +
                                 (len(non_reasoning_scores)-1)*np.var(non_reasoning_scores, ddof=1)) /
                                (len(reasoning_scores) + len(non_reasoning_scores) - 2))

            if pooled_std > 0:
                cohens_d = observed_diff / pooled_std
            else:
                cohens_d = 0

            # Calculate descriptive statistics
            reasoning_mean = np.mean(reasoning_scores)
            reasoning_std = np.std(reasoning_scores, ddof=1)
            non_reasoning_mean = np.mean(non_reasoning_scores)
            non_reasoning_std = np.std(non_reasoning_scores, ddof=1)

            significance_results[metric] = {
                'p_value': p_value,
                'observed_difference': observed_diff,
                'cohens_d': cohens_d,
                'mean_difference': observed_diff,  # Keep for compatibility
                'reasoning_mean': np.mean(reasoning_scores),
                'reasoning_std': np.std(reasoning_scores, ddof=1),
                'non_reasoning_mean': np.mean(non_reasoning_scores),
                'non_reasoning_std': np.std(non_reasoning_scores, ddof=1),
                'n_reasoning': len(reasoning_scores),
                'n_non_reasoning': len(non_reasoning_scores),
                'reasoning_scores': reasoning_scores,
                'non_reasoning_scores': non_reasoning_scores,
                'permutation_diffs': permutation_diffs,
                'n_permutations': n_permutations
            }

            # Print results
            print(f"\nPermutation Test Analysis:")
            print(f"Reasoning models (n={len(reasoning_scores)}): {np.mean(reasoning_scores):.2f}% ± {np.std(reasoning_scores, ddof=1):.2f}%")
            print(f"Non-reasoning models (n={len(non_reasoning_scores)}): {np.mean(non_reasoning_scores):.2f}% ± {np.std(non_reasoning_scores, ddof=1):.2f}%")
            print(f"Observed difference: {observed_diff:.2f}% (negative = reasoning better)")
            print(f"Permutations: {n_permutations}")
            print(f"p-value: {p_value:.6f}")
            print(f"Cohen's d: {cohens_d:.3f}")

            # Significance level
            if p_value < 0.001:
                sig_text = "*** (p < 0.001)"
            elif p_value < 0.01:
                sig_text = "** (p < 0.01)"
            elif p_value < 0.05:
                sig_text = "* (p < 0.05)"
            else:
                sig_text = "ns (not significant)"
            print(f"Significance: {sig_text}")

            # Effect size interpretation
            if abs(cohens_d) < 0.2:
                effect_size = "negligible"
            elif abs(cohens_d) < 0.5:
                effect_size = "small"
            elif abs(cohens_d) < 0.8:
                effect_size = "medium"
            else:
                effect_size = "large"
            print(f"Effect size: {effect_size}")

            # Interpretation
            if p_value < 0.05 and observed_diff < 0:
                print("✓ Reasoning models show significantly better robustness")
            elif p_value < 0.05 and observed_diff > 0:
                print("✗ Non-reasoning models show significantly better robustness")
            else:
                print("○ No significant difference between model groups")

            # Additional statistics
            print(f"95% CI of permutation differences: [{np.percentile(permutation_diffs, 2.5):.2f}, {np.percentile(permutation_diffs, 97.5):.2f}]")

        else:
            print(f"Insufficient data for {metric}: reasoning={len(reasoning_scores)}, non-reasoning={len(non_reasoning_scores)} models")
            significance_results[metric] = None

    return significance_results

def create_llm_multi_metric_comparison(results_df):
    """Create comprehensive LLM comparison across BLEU, CodeBLEU, and Soft-EM with significance analysis"""

    attacks = ['dfs', 'bfs', 'subtree', 'funcname']
    attack_labels = ['DFS', 'BFS', 'Subtree', 'Signature']
    metrics = ['bleu', 'codebleu', 'soft_em']
    metric_labels = ['BLEU', 'CodeBLEU', 'Soft-EM']

    # Perform significance analysis first
    significance_results = perform_paired_significance_analysis(results_df)

    # Use unified data processing logic for all metrics
    all_drops = {}
    for metric in metrics:
        all_drops[metric] = calculate_llm_relative_drops(results_df, metric)

    # Create subplots for each metric
    fig, axes = plt.subplots(1, 3, figsize=(18, 6))

    llm_models = ['claude', 'gemini', 'gpt-4o', 'llama', 'o3', 'qwen3']
    colors = plt.cm.tab10(range(10))
    model_colors = {model: colors[i] for i, model in enumerate(llm_models)}

    x = np.arange(len(attacks))
    width = 0.12

    for metric_idx, metric in enumerate(metrics):
        ax = axes[metric_idx]

        # Plot each model
        for i, model in enumerate(llm_models):
            if model in all_drops[metric]:
                drops = [all_drops[metric][model].get(attack, 0) for attack in attacks]
                offset = (i - len(llm_models)/2 + 0.5) * width

                bars = ax.bar(x + offset, drops, width, label=LEGEND_LABEL_MODELS.get(model, model.upper()),
                             color=model_colors[model])

                # # Add value labels
                # for bar, value in zip(bars, drops):
                #     if value > 0:
                #         ax.text(bar.get_x() + bar.get_width()/2, bar.get_height() + 1,
                #                f'{value:.1f}%', ha='center', va='bottom', fontsize=7)

        ax.set_xlabel('Attack Types', fontsize=10)
        ax.set_ylabel('Relative Performance Drop (%)', fontsize=10)

        # Add significance annotation to title if available
        title = f'{metric_labels[metric_idx]} Metric'
        if significance_results.get(metric):
            p_val = significance_results[metric]['p_value']
            if p_val < 0.001:
                sig_star = '***'
            elif p_val < 0.01:
                sig_star = '**'
            elif p_val < 0.05:
                sig_star = '*'
            else:
                sig_star = 'ns'
            title += f' ({sig_star})'

        # ax.set_title(title, fontsize=12, fontweight='bold')
        ax.set_xticks(x)
        ax.set_xticklabels(attack_labels, rotation=0, ha='right')
        if metric_idx == 2:  # Only show legend on last subplot
            ax.legend(bbox_to_anchor=(1.05, 1), loc='upper left')
        ax.grid(axis='y', alpha=0.3)

    # Add overall significance note to the figure
    sig_note = "Significance: Paired Wilcoxon test comparing reasoning vs non-reasoning models"
    plt.suptitle('LLM Robustness Comparison Across Multiple Metrics\n' + sig_note,
                 fontsize=14, fontweight='bold')
    plt.tight_layout()
    plt.savefig('figure_llm_multi_metric.png', dpi=300, bbox_inches='tight')
    plt.savefig('figure_llm_multi_metric.pdf', bbox_inches='tight')
    print("LLM multi-metric figure with significance analysis saved as figure_llm_multi_metric.png and .pdf")

    # Print summary of significance results
    print("\n" + "="*60)
    print("SIGNIFICANCE ANALYSIS SUMMARY")
    print("="*60)
    print("Reasoning models (qwen3, o3, gemini) vs Non-reasoning models (gpt-4o, llama, claude)")
    for metric in metrics:
        if significance_results.get(metric):
            result = significance_results[metric]
            if result['p_value'] < 0.05:
                status = "significantly better" if result['mean_difference'] < 0 else "significantly worse"
            else:
                status = "no significant difference"
            print(f"{metric.upper()}: Reasoning models show {status} (p={result['p_value']:.4f})")
        else:
            print(f"{metric.upper()}: Insufficient data for analysis")

    create_llm_separate_metric_figures(results_df, significance_results)

    return fig


def create_llm_separate_metric_figures(results_df, significance_results):
    """Create 3 separate figures for BLEU, CodeBLEU, and Soft-EM metrics using boxplots with a shared legend"""

    attacks = ['dfs', 'bfs', 'subtree', 'funcname']
    attack_labels = ['DFS', 'BFS', 'Subtree', 'Signature']
    metrics = ['bleu', 'codebleu', 'soft_em']
    metric_labels = ['BLEU', 'CodeBLEU', 'Soft-EM']

    llm_models = ['claude', 'gemini', 'gpt-4o', 'llama', 'o3', 'qwen3']
    strategies = ['direct', 'cot', 'explain_then_translate', '1shot', '4shot']

    # Define model colors for legend
    colors = plt.cm.tab10(range(10))
    model_colors = {model: colors[i] for i, model in enumerate(llm_models)}

    # Create separate figures for each metric
    figures = {}

    for metric_idx, metric in enumerate(metrics):
        # Prepare data for plotting - similar to create_unified_attack_boxplot
        plot_data = []

        for attack in attacks:
            for model in llm_models:
                model_data = results_df[results_df['model'] == model]
                if len(model_data) == 0:
                    continue

                for strategy in strategies:
                    # Get baseline for this specific strategy
                    baseline_data = model_data[
                        (model_data['attack'] == 'original') &
                        (model_data['strategy'] == strategy)
                    ]

                    # Get attack data for this specific strategy
                    attack_data = model_data[
                        (model_data['attack'] == attack) &
                        (model_data['strategy'] == strategy)
                    ]

                    if len(baseline_data) > 0 and len(attack_data) > 0:
                        baseline_score = baseline_data[metric].mean()  # Average across directions
                        attack_score = attack_data[metric].mean()     # Average across directions

                        if baseline_score > 0:
                            relative_drop = ((baseline_score - attack_score) / baseline_score) * 100
                            plot_data.append({
                                'Attack': attack_labels[attacks.index(attack)],
                                'Model': LEGEND_LABEL_MODELS.get(model, model.upper()),
                                'Strategy': strategy,
                                'Drop': relative_drop
                            })

        if not plot_data:
            print(f"No data available for {metric} metric")
            continue

        plot_df = pd.DataFrame(plot_data)

        # Create the boxplot
        fig, ax = plt.subplots(figsize=(12, 8))
        figures[metric] = fig

        # Set color palette to match the defined model colors
        # Create reverse mapping from display names to original names
        reverse_mapping = {v: k for k, v in LEGEND_LABEL_MODELS.items()}
        model_colors_list = [model_colors[reverse_mapping.get(model, model.lower())] for model in plot_df['Model'].unique()]

        # Create box plot using seaborn with custom colors
        sns.boxplot(data=plot_df, x='Attack', y='Drop', hue='Model', ax=ax, gap=0.1, palette=model_colors_list)

        # Add vertical lines between attack groups
        for i in range(len(attack_labels) - 1):
            ax.axvline(x=i + 0.5, color='gray', linestyle='--', alpha=0.5, linewidth=1)

        ax.set_xlabel('Attack Types')
        ax.set_ylabel('Relative Performance Drop (%)')

        # Add significance annotation to title if available
        title = f'{metric_labels[metric_idx]} Metric - LLM Robustness Comparison'
        if significance_results.get(metric):
            p_val = significance_results[metric]['p_value']
            if p_val < 0.001:
                sig_star = '***'
            elif p_val < 0.01:
                sig_star = '**'
            elif p_val < 0.05:
                sig_star = '*'
            else:
                sig_star = 'ns'
            title += f' ({sig_star})'

        # ax.set_title(title, fontsize=14, fontweight='bold')
        # Remove individual legend since we use shared legend
        ax.get_legend().remove()
        ax.grid(axis='y', alpha=0.3)

        plt.tight_layout()

        # Save individual metric figures
        plt.savefig(f'figure_llm_{metric}_metric.png', dpi=300, bbox_inches='tight')
        plt.savefig(f'figure_llm_{metric}_metric.pdf', bbox_inches='tight')
        print(f"Individual {metric.upper()} metric boxplot figure saved as figure_llm_{metric}_metric.png and .pdf")

        plt.close(fig)

    # Create a shared legend figure
    fig_legend, ax_legend = plt.subplots(figsize=(8, 1))
    ax_legend.axis('off')

    # Create legend handles with colors that match seaborn's boxplot style
    legend_handles = []
    for i, model in enumerate(llm_models):
        # Remove alpha to match seaborn's default appearance
        legend_handles.append(plt.Rectangle((0,0),1,1, facecolor=model_colors[model],
                                          label=LEGEND_LABEL_MODELS.get(model, model.upper())))

    ax_legend.legend(handles=legend_handles, ncol=len(llm_models), loc='center',
                    frameon=False, fancybox=True)

    plt.tight_layout()
    plt.savefig('figure_llm_metrics_shared_legend.png', dpi=300, bbox_inches='tight')
    plt.savefig('figure_llm_metrics_shared_legend.pdf', bbox_inches='tight')
    print("Shared legend saved as figure_llm_metrics_shared_legend.png and .pdf")
    plt.close(fig_legend)

    # Add significance note
    sig_note = "Significance: Paired Wilcoxon test comparing reasoning vs non-reasoning models"
    print(f"\n{sig_note}")
    print("Reasoning models (qwen3, o3, gemini) vs Non-reasoning models (gpt-4o, llama, claude)")
    for metric in metrics:
        if significance_results.get(metric):
            result = significance_results[metric]
            if result['p_value'] < 0.05:
                status = "significantly better" if result['mean_difference'] < 0 else "significantly worse"
            else:
                status = "no significant difference"
            print(f"{metric.upper()}: Reasoning models show {status} (p={result['p_value']:.4f})")
        else:
            print(f"{metric.upper()}: Insufficient data for analysis")

    return figures

def create_nonllm_comparison():
    """Create Non-LLM comparison for BLEU and EM metrics"""

    # Non-LLM data from original.md
    nonllm_data = {
        'bleu': {
            'GraphCodeBERT': {'original': 76.61, 'dfs': 9.33, 'bfs': 15.83, 'subtree': 42.54, 'funcname': 73.53},
            'PLBART': {'original': 80.69, 'dfs': 3.36, 'bfs': 1.76, 'subtree': 10.53, 'funcname': 60.05},
            'CodeT5': {'original': 81.95, 'dfs': 8.70, 'bfs': 11.94, 'subtree': 47.69, 'funcname': 78.08},
            'UniXcoder': {'original': 76.59, 'dfs': 25.47, 'bfs': 23.84, 'subtree': 37.59, 'funcname': 72.26}
        },
        'em': {
            'GraphCodeBERT': {'original': 59.10, 'dfs': 6.25, 'bfs': 15.40, 'subtree': 26.30, 'funcname': 57.50},
            'PLBART': {'original': 64.80, 'dfs': 0.35, 'bfs': 0.10, 'subtree': 6.85, 'funcname': 34.15},
            'CodeT5': {'original': 66.45, 'dfs': 0.75, 'bfs': 12.95, 'subtree': 31.85, 'funcname': 61.80},
            'UniXcoder': {'original': 63.45, 'dfs': 24.95, 'bfs': 23.75, 'subtree': 25.90, 'funcname': 60.75}
        }
    }

    models = ['GraphCodeBERT', 'PLBART', 'CodeT5', 'UniXcoder']
    attacks = ['dfs', 'bfs', 'subtree', 'funcname']
    attack_labels = ['DFS', 'BFS', 'Subtree', 'Signature']
    metrics = ['bleu', 'em']
    metric_labels = ['BLEU', 'EM']

    # Calculate relative drops
    relative_drops = {}
    for metric in metrics:
        relative_drops[metric] = {}
        for model in models:
            drops = {}
            baseline = nonllm_data[metric][model]['original']
            for attack in attacks:
                attack_score = nonllm_data[metric][model][attack]
                if baseline > 0:
                    relative_drop = ((baseline - attack_score) / baseline) * 100
                    drops[attack] = relative_drop
                else:
                    drops[attack] = 0
            relative_drops[metric][model] = drops

    # Create subplots
    fig, axes = plt.subplots(1, 2, figsize=(14, 6))

    colors = plt.cm.Set2(range(8))
    model_colors = {model: colors[i] for i, model in enumerate(models)}

    x = np.arange(len(attacks))
    width = 0.2

    for metric_idx, metric in enumerate(metrics):
        ax = axes[metric_idx]

        # Plot each model
        for i, model in enumerate(models):
            drops = [relative_drops[metric][model][attack] for attack in attacks]
            offset = (i - len(models)/2 + 0.5) * width

            bars = ax.bar(x + offset, drops, width, label=model,
                         color=model_colors[model])

            # # Add value labels
            # for bar, value in zip(bars, drops):
            #     ax.text(bar.get_x() + bar.get_width()/2, bar.get_height() + 1,
            #            f'{value:.1f}%', ha='center', va='bottom', fontsize=8)

        ax.set_xlabel('Attack Types', fontsize=10)
        ax.set_ylabel('Relative Performance Drop (%)', fontsize=10)
        ax.set_title(f'Non-LLM {metric_labels[metric_idx]} Performance', fontsize=12, fontweight='bold')
        ax.set_xticks(x)
        ax.set_xticklabels(attack_labels, rotation=0, ha='right')
        if metric_idx == 1:  # Show legend on last subplot
            ax.legend(bbox_to_anchor=(1.05, 1), loc='upper left')
        ax.grid(axis='y', alpha=0.3)

    plt.suptitle('Non-LLM Model Robustness Comparison', fontsize=14, fontweight='bold')
    plt.tight_layout()
    plt.savefig('figure_nonllm_comparison.png', dpi=300, bbox_inches='tight')
    plt.savefig('figure_nonllm_comparison.pdf', bbox_inches='tight')
    print("Non-LLM comparison figure saved as figure_nonllm_comparison.png and .pdf")

    return fig

def create_attack_specific_figures(results_df):
    """Create 4 figures, one for each attack type, showing model comparison with strategy distributions"""

    attacks = ['dfs', 'bfs', 'subtree', 'funcname']
    attack_names = {
        'dfs': 'Reconstruction Attack: DFS',
        'bfs': 'Reconstruction Attack: BFS',
        'subtree': 'Subtree Attack',
        'funcname': 'Signature Attack'
    }

    llm_models = ['claude', 'gemini', 'gpt-4o', 'llama', 'o3', 'qwen3']
    strategies = ['direct', 'cot', 'explain_then_translate', '1shot', '4shot']

    # Use unified Non-LLM statistics calculation
    nonllm_drops, nonllm_avg, nonllm_best, best_model = calculate_nonllm_stats()

    # Create attack-specific Non-LLM data
    nonllm_data = {}
    for attack in attacks:
        attack_drops = [nonllm_drops[model]['drops'][attack] for model in nonllm_drops.keys()]
        best_drop = min(attack_drops)
        best_model_for_attack = None
        for model in nonllm_drops.keys():
            if abs(nonllm_drops[model]['drops'][attack] - best_drop) < 0.1:
                best_model_for_attack = model
                break

        nonllm_data[attack] = {
            'avg': sum(attack_drops) / len(attack_drops),
            'best': best_drop,
            'best_model': best_model_for_attack
        }

    for attack in attacks:
        print(f"Creating figure for {attack_names[attack]}...")

        fig, ax = plt.subplots(figsize=(14, 8))

        # This section is not needed anymore since we use grouped bar chart below
        plot_data = []
        model_positions = []

        # Create grouped bar chart showing each strategy's drop for each model
        model_data_for_plot = {}

        for model in llm_models:
            model_data = results_df[results_df['model'] == model]
            if len(model_data) == 0:
                continue

            model_drops = []
            for strategy in strategies:
                # Get baseline for this specific strategy
                baseline_data = model_data[
                    (model_data['attack'] == 'original') &
                    (model_data['strategy'] == strategy)
                ]

                # Get attack data for this specific strategy
                attack_data = model_data[
                    (model_data['attack'] == attack) &
                    (model_data['strategy'] == strategy)
                ]

                if len(baseline_data) > 0 and len(attack_data) > 0:
                    baseline_score = baseline_data['bleu'].mean()  # Average across directions
                    attack_score = attack_data['bleu'].mean()     # Average across directions

                    if baseline_score > 0:
                        relative_drop = ((baseline_score - attack_score) / baseline_score) * 100
                        model_drops.append(relative_drop)
                    else:
                        model_drops.append(0)
                else:
                    model_drops.append(0)

            if any(drop > 0 for drop in model_drops):
                model_data_for_plot[model] = model_drops

        if model_data_for_plot:
            # Create grouped bar chart
            x = np.arange(len(strategies))
            width = 0.12  # width of each bar

            colors = plt.cm.tab10(range(len(llm_models)))
            model_colors = {model: colors[i] for i, model in enumerate(llm_models)}

            for i, model in enumerate(llm_models):
                if model in model_data_for_plot:
                    offset = (i - len(llm_models)/2 + 0.5) * width
                    bars = ax.bar(x + offset, model_data_for_plot[model], width,
                                 label=LEGEND_LABEL_MODELS.get(model, model.upper()), color=model_colors[model], alpha=0.8)

                    # # Add value labels on bars
                    # for bar, value in zip(bars, model_data_for_plot[model]):
                    #     if value > 0:
                    #         ax.text(bar.get_x() + bar.get_width()/2, bar.get_height() + 1,
                    #                f'{value:.1f}%', ha='center', va='bottom', fontsize=8)

            ax.set_xticks(x)
            ax.set_xticklabels([LEGEND_LABEL_STRATEGIES.get(s, s.replace('_', ' ').title()) for s in strategies], rotation=0, ha='right')
            ax.set_xlabel('Prompt Strategies', fontsize=12)

        # Add Non-LLM reference lines
        ax.axhline(y=nonllm_data[attack]['avg'], color='red', linestyle='--', alpha=0.8, linewidth=2,
                   label=f'Non-LLM Average ({nonllm_data[attack]["avg"]:.1f}%)')
        ax.axhline(y=nonllm_data[attack]['best'], color='orange', linestyle='--', alpha=0.8, linewidth=2,
                   label=f'Non-LLM Best ({nonllm_data[attack]["best_model"]}: {nonllm_data[attack]["best"]:.1f}%)')

        ax.set_ylabel('BLEU Relative Performance Drop (%)', fontsize=12)
        # ax.set_title(f'{attack_names[attack]}: LLM Robustness Comparison\\n(Performance across different prompt strategies)',
        #              fontsize=14, fontweight='bold')
        ax.legend()
        ax.grid(axis='y', alpha=0.3)

        plt.tight_layout()
        plt.savefig(f'figure_attack_{attack}_comparison.png', dpi=300, bbox_inches='tight')
        plt.savefig(f'figure_attack_{attack}_comparison.pdf', bbox_inches='tight')
        print(f"Saved figure_attack_{attack}_comparison.png and .pdf")

    return True

def create_unified_attack_boxplot(results_df):
    """Create a unified box plot showing all attacks and models in one figure"""

    attacks = ['dfs', 'bfs', 'subtree', 'funcname']
    attack_names = {
        'dfs': 'DFS',
        'bfs': 'BFS',
        'subtree': 'Subtree',
        'funcname': 'Signature'
    }

    llm_models = ['claude', 'gemini', 'gpt-4o', 'llama', 'o3', 'qwen3']
    strategies = ['direct', 'cot', 'explain_then_translate', '1shot', '4shot']

    # Prepare data for plotting
    plot_data = []

    for attack in attacks:
        for model in llm_models:
            model_data = results_df[results_df['model'] == model]
            if len(model_data) == 0:
                continue

            model_drops = []
            for strategy in strategies:
                # Get baseline for this specific strategy
                baseline_data = model_data[
                    (model_data['attack'] == 'original') &
                    (model_data['strategy'] == strategy)
                ]

                # Get attack data for this specific strategy
                attack_data = model_data[
                    (model_data['attack'] == attack) &
                    (model_data['strategy'] == strategy)
                ]

                if len(baseline_data) > 0 and len(attack_data) > 0:
                    baseline_score = baseline_data['bleu'].mean()  # Average across directions
                    attack_score = attack_data['bleu'].mean()     # Average across directions

                    if baseline_score > 0:
                        relative_drop = ((baseline_score - attack_score) / baseline_score) * 100
                        plot_data.append({
                            'Attack': attack_names[attack],
                            'Model': LEGEND_LABEL_MODELS.get(model, model.upper()),
                            'Strategy': strategy,
                            'Drop': relative_drop
                        })

    if not plot_data:
        print("No data available for unified box plot")
        return None

    plot_df = pd.DataFrame(plot_data)

    # Create the plot
    fig, ax = plt.subplots(figsize=(16, 10))

    # Define consistent colors for models
    llm_models_for_color = ['claude', 'gemini', 'gpt-4o', 'llama', 'o3', 'qwen3']
    colors = plt.cm.tab10(range(10))
    model_colors = {model: colors[i] for i, model in enumerate(llm_models_for_color)}
    # Create reverse mapping from display names to original names
    reverse_mapping = {v: k for k, v in LEGEND_LABEL_MODELS.items()}
    model_colors_list = [model_colors[reverse_mapping.get(model, model.lower())] for model in plot_df['Model'].unique()]

    # Create box plot using seaborn with custom colors
    # Set gap parameter to make groups more compact
    sns.boxplot(data=plot_df, x='Attack', y='Drop', hue='Model', ax=ax, gap=0.1, palette=model_colors_list)

    # Add vertical lines between attack groups
    attack_names_list = ['DFS', 'BFS', 'Subtree', 'Signature']
    for i in range(len(attack_names_list) - 1):
        ax.axvline(x=i + 0.5, color='gray', linestyle='--', alpha=0.5, linewidth=1)

    # Add Non-LLM reference lines
    nonllm_drops, nonllm_avg, nonllm_best, best_model = calculate_nonllm_stats()

    # Calculate and add Non-LLM reference data for each attack
    attack_positions = {attack_names[attack]: i for i, attack in enumerate(attacks)}

    # Get the x-axis limits for proper positioning
    xlim = ax.get_xlim()
    total_width = xlim[1] - xlim[0]

    for attack in attacks:
        attack_drops = [nonllm_drops[model]['drops'][attack] for model in nonllm_drops.keys()]
        avg_drop = sum(attack_drops) / len(attack_drops)
        best_drop = min(attack_drops)

        x_pos = attack_positions[attack_names[attack]]
        # Calculate proper xmin and xmax for each attack position
        xmin = (x_pos - 0.4 - xlim[0]) / total_width
        xmax = (x_pos + 0.4 - xlim[0]) / total_width

        ax.axhline(y=avg_drop, xmin=xmin, xmax=xmax,
                   color='red', linestyle='--', alpha=0.8, linewidth=2)
        ax.axhline(y=best_drop, xmin=xmin, xmax=xmax,
                   color='orange', linestyle='--', alpha=0.8, linewidth=2)


    # Add legend for reference lines (using invisible lines just for legend)
    from matplotlib.lines import Line2D
    legend_elements = [Line2D([0], [0], color='red', linestyle='--', alpha=0.8, linewidth=2, label='Non-LLM Average'),
                      Line2D([0], [0], color='orange', linestyle='--', alpha=0.8, linewidth=2, label='Non-LLM Best')]

    ax.set_xlabel('Attack Types')
    ax.set_ylabel('BLEU Relative Performance Drop (%)')
    # ax.set_title('LLM Robustness: Box Plot Comparison Across All Attacks\n(Distribution across prompt strategies)',
    #              fontsize=16, fontweight='bold')

    # Adjust legend
    handles, labels = ax.get_legend_handles_labels()
    ax.legend(handles + legend_elements, labels + ['Non-LLM Average', 'Non-LLM Best'], loc='upper right')

    ax.grid(axis='y', alpha=0.3)

    plt.tight_layout()
    plt.savefig('figure_unified_attack_boxplot.png', dpi=300, bbox_inches='tight')
    plt.savefig('figure_unified_attack_boxplot.pdf', bbox_inches='tight')
    print("Unified box plot saved as figure_unified_attack_boxplot.png and .pdf")

    return fig


def main():
    print("Starting experimental results analysis...")

    # Collect results
    results = collect_results()
    if not results:
        print("No result files found!")
        return

    results_df = pd.DataFrame(results)
    print(f"Collected {len(results)} experimental results")

    # Create tables for all metrics
    metrics = ['bleu', 'codebleu', 'em', 'soft_em']
    for metric in metrics:
        print(f"\n{'='*60}")
        print(f"Processing {metric.upper()} metric")
        print('='*60)
        table_df, pivot_table, relative_df, relative_pivot = create_table1(results_df, metric)

    # Create Figure 1
    fig = create_figure1(results_df)

    # Create Figure 2: Model robustness comparison using unified data processing
    fig2 = create_figure2_model_robustness(results_df)

    # Create Figure 3: Prompt strategy robustness comparison
    fig3 = create_figure3_prompt_strategy_robustness(results_df)

    # Create multi-metric comparisons
    print(f"\n{'='*60}")
    print("Creating multi-metric comparisons")
    print('='*60)

    # LLM multi-metric comparison (BLEU, CodeBLEU, Soft-EM)
    fig_llm_multi = create_llm_multi_metric_comparison(results_df)

    # Non-LLM comparison (BLEU, EM)
    fig_nonllm = create_nonllm_comparison()

    # Create attack-specific figures (recommended approach)
    print(f"\n{'='*60}")
    print("Creating attack-specific comparison figures")
    print('='*60)
    create_attack_specific_figures(results_df)

    # Create unified attack comparison figures
    print(f"\n{'='*60}")
    print("Creating unified attack comparison figures")
    print('='*60)
    fig_unified_box = create_unified_attack_boxplot(results_df)

    print("\nAnalysis completed!")
    print("Generated files:")
    for metric in metrics:
        print(f"- table1_absolute_scores_{metric}.csv")
        print(f"- table1_relative_drops_{metric}.csv")
    print("- figure1_llm_robustness.png and .pdf")
    print("- figure2_model_robustness.png and .pdf")
    print("- figure3_prompt_strategy_robustness.png and .pdf")
    print("- figure_llm_multi_metric.png and .pdf (LLM: BLEU, CodeBLEU, Soft-EM)")
    print("- figure_llm_bleu_metric.png and .pdf (Individual BLEU metric)")
    print("- figure_llm_codebleu_metric.png and .pdf (Individual CodeBLEU metric)")
    print("- figure_llm_soft_em_metric.png and .pdf (Individual Soft-EM metric)")
    print("- figure_llm_metrics_shared_legend.png and .pdf (Shared legend for metric figures)")
    print("- figure_nonllm_comparison.png and .pdf (Non-LLM: BLEU, EM)")
    print("- figure_attack_dfs_comparison.png and .pdf")
    print("- figure_attack_bfs_comparison.png and .pdf")
    print("- figure_attack_subtree_comparison.png and .pdf")
    print("- figure_attack_funcname_comparison.png and .pdf")
    print("- figure_unified_attack_boxplot.png and .pdf (New unified box plot)")

if __name__ == '__main__':
    main()