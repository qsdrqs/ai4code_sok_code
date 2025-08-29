#!/usr/bin/env python3

import pandas as pd
import numpy as np
from scipy.stats import mannwhitneyu, shapiro, levene, ttest_ind
import os
from collections import defaultdict
import matplotlib.pyplot as plt
import seaborn as sns

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

def collect_model_data():
    """Collect RD@1 data for all models and strategies"""
    # Model categorization
    reasoning_models = ['gemini', 'o3', 'qwen3']
    non_reasoning_models = ['claude', 'llama', 'gpt-4o']
    
    strategies = ['direct', 'cot', 'explain_then_translate', '1shot', '4shot']
    tasks = ['j2p', 'p2j']
    
    data = {
        'reasoning': {'j2p': [], 'p2j': []},
        'non_reasoning': {'j2p': [], 'p2j': []}
    }
    
    # Collect data for each group
    for task in tasks:
        # Reasoning models
        for model in reasoning_models:
            for strategy in strategies:
                csv_file = f'dataset_attack/syntax_{task}_{model}_{strategy}_stat.csv'
                _, _, rd_at_1 = calculate_metrics(csv_file)
                if rd_at_1 is not None:
                    data['reasoning'][task].append(rd_at_1)
                    
        # Non-reasoning models
        for model in non_reasoning_models:
            for strategy in strategies:
                csv_file = f'dataset_attack/syntax_{task}_{model}_{strategy}_stat.csv'
                _, _, rd_at_1 = calculate_metrics(csv_file)
                if rd_at_1 is not None:
                    data['non_reasoning'][task].append(rd_at_1)
    
    return data

def perform_significance_test(group1, group2, group1_name, group2_name, task_name):
    """Perform statistical significance tests between two groups"""
    print(f"\n=== {task_name} Task: {group1_name} vs {group2_name} ===")
    
    # Basic statistics
    print(f"\n{group1_name} (n={len(group1)}):")
    print(f"  Mean RD@1: {np.mean(group1):.2f}%")
    print(f"  Std RD@1: {np.std(group1, ddof=1):.2f}%")
    print(f"  Median RD@1: {np.median(group1):.2f}%")
    print(f"  Range: [{np.min(group1):.2f}, {np.max(group1):.2f}]%")
    
    print(f"\n{group2_name} (n={len(group2)}):")
    print(f"  Mean RD@1: {np.mean(group2):.2f}%")
    print(f"  Std RD@1: {np.std(group2, ddof=1):.2f}%")
    print(f"  Median RD@1: {np.median(group2):.2f}%")
    print(f"  Range: [{np.min(group2):.2f}, {np.max(group2):.2f}]%")
    
    # Effect size (Cohen's d)
    pooled_std = np.sqrt(((len(group1)-1)*np.var(group1, ddof=1) + (len(group2)-1)*np.var(group2, ddof=1)) / (len(group1)+len(group2)-2))
    cohens_d = (np.mean(group1) - np.mean(group2)) / pooled_std
    print(f"\nCohen's d (effect size): {cohens_d:.3f}")
    
    # Interpret effect size
    if abs(cohens_d) < 0.2:
        effect_interpretation = "negligible"
    elif abs(cohens_d) < 0.5:
        effect_interpretation = "small"
    elif abs(cohens_d) < 0.8:
        effect_interpretation = "medium"
    else:
        effect_interpretation = "large"
    print(f"Effect size interpretation: {effect_interpretation}")
    
    # Test for normality
    _, p_norm1 = shapiro(group1)
    _, p_norm2 = shapiro(group2)
    print(f"\nNormality tests (Shapiro-Wilk):")
    print(f"  {group1_name} p-value: {p_norm1:.4f}")
    print(f"  {group2_name} p-value: {p_norm2:.4f}")
    
    # Test for equal variances
    _, p_levene = levene(group1, group2)
    print(f"Equal variances test (Levene): p-value = {p_levene:.4f}")
    
    # Choose appropriate test
    if p_norm1 > 0.05 and p_norm2 > 0.05:
        # Both groups appear normal, use t-test
        if p_levene > 0.05:
            # Equal variances
            stat, p_ttest = ttest_ind(group1, group2)
            test_used = "Independent t-test (equal variances)"
        else:
            # Unequal variances
            stat, p_ttest = ttest_ind(group1, group2, equal_var=False)
            test_used = "Welch's t-test (unequal variances)"
        
        print(f"\n{test_used}:")
        print(f"  t-statistic: {stat:.3f}")
        print(f"  p-value: {p_ttest:.4f}")
    
    # Always perform Mann-Whitney U test (non-parametric)
    stat_mw, p_mw = mannwhitneyu(group1, group2, alternative='two-sided')
    print(f"\nMann-Whitney U test (non-parametric):")
    print(f"  U-statistic: {stat_mw:.1f}")
    print(f"  p-value: {p_mw:.4f}")
    
    # Interpretation
    alpha = 0.05
    print(f"\nInterpretation (α = {alpha}):")
    if p_mw < alpha:
        print(f"  ✓ SIGNIFICANT difference (p = {p_mw:.4f} < {alpha})")
        if np.mean(group1) < np.mean(group2):
            print(f"  -> {group1_name} performs significantly BETTER (lower RD@1)")
        else:
            print(f"  -> {group2_name} performs significantly BETTER (lower RD@1)")
    else:
        print(f"  ✗ No significant difference (p = {p_mw:.4f} ≥ {alpha})")
    
    return {
        'task': task_name,
        'group1_name': group1_name,
        'group2_name': group2_name,
        'group1_mean': np.mean(group1),
        'group2_mean': np.mean(group2),
        'group1_std': np.std(group1, ddof=1),
        'group2_std': np.std(group2, ddof=1),
        'cohens_d': cohens_d,
        'mannwhitney_u': stat_mw,
        'mannwhitney_p': p_mw,
        'significant': p_mw < alpha
    }

def create_visualization(data, results):
    """Create visualization of the data and test results"""
    fig, axes = plt.subplots(2, 2, figsize=(15, 10))
    
    tasks = ['j2p', 'p2j']
    task_names = ['Java->Python', 'Python->Java']
    
    for i, (task, task_name) in enumerate(zip(tasks, task_names)):
        # Box plot
        ax1 = axes[i, 0]
        box_data = [data['reasoning'][task], data['non_reasoning'][task]]
        box_labels = ['Reasoning\nModels', 'Non-reasoning\nModels']
        
        bp = ax1.boxplot(box_data, labels=box_labels, patch_artist=True)
        bp['boxes'][0].set_facecolor('lightblue')
        bp['boxes'][1].set_facecolor('lightcoral')
        
        ax1.set_title(f'{task_name} - Box Plot')
        ax1.set_ylabel('RD@1 (%)')
        ax1.grid(True, alpha=0.3)
        
        # Add significance annotation
        result = results[i]
        if result['significant']:
            ax1.text(0.5, 0.95, f"p = {result['mannwhitney_p']:.4f}*", 
                    transform=ax1.transAxes, ha='center', va='top',
                    bbox=dict(boxstyle="round,pad=0.3", facecolor="yellow", alpha=0.7))
        else:
            ax1.text(0.5, 0.95, f"p = {result['mannwhitney_p']:.4f} (n.s.)", 
                    transform=ax1.transAxes, ha='center', va='top',
                    bbox=dict(boxstyle="round,pad=0.3", facecolor="lightgray", alpha=0.7))
        
        # Violin plot
        ax2 = axes[i, 1]
        parts = ax2.violinplot(box_data, positions=[1, 2], showmeans=True, showmedians=True)
        
        # Color the violin plots
        for pc, color in zip(parts['bodies'], ['lightblue', 'lightcoral']):
            pc.set_facecolor(color)
            pc.set_alpha(0.7)
        
        ax2.set_xticks([1, 2])
        ax2.set_xticklabels(['Reasoning\nModels', 'Non-reasoning\nModels'])
        ax2.set_title(f'{task_name} - Violin Plot')
        ax2.set_ylabel('RD@1 (%)')
        ax2.grid(True, alpha=0.3)
        
        # Add mean values as text
        ax2.text(1, max(data['reasoning'][task]) + 2, 
                f'Mean: {np.mean(data["reasoning"][task]):.1f}%', 
                ha='center', fontsize=9)
        ax2.text(2, max(data['non_reasoning'][task]) + 2, 
                f'Mean: {np.mean(data["non_reasoning"][task]):.1f}%', 
                ha='center', fontsize=9)
    
    plt.tight_layout()
    plt.savefig('significance_test_visualization.pdf', dpi=300, bbox_inches='tight')
    plt.show()

def main():
    print("Statistical Significance Testing: Reasoning vs Non-reasoning Models")
    print("="*70)
    
    # Collect data
    print("Collecting data from CSV files...")
    data = collect_model_data()
    
    # Print data summary
    print(f"\nData Summary:")
    print(f"Reasoning models: gemini, o3, qwen3")
    print(f"Non-reasoning models: claude, llama, gpt-4o")
    print(f"Strategies tested: direct, cot, explain_then_translate, 1shot, 4shot")
    
    for task in ['j2p', 'p2j']:
        task_name = 'Java->Python' if task == 'j2p' else 'Python->Java'
        print(f"\n{task_name}:")
        print(f"  Reasoning models: {len(data['reasoning'][task])} data points")
        print(f"  Non-reasoning models: {len(data['non_reasoning'][task])} data points")
    
    # Perform significance tests for each task
    results = []
    for task in ['j2p', 'p2j']:
        task_name = 'Java->Python' if task == 'j2p' else 'Python->Java'
        result = perform_significance_test(
            data['reasoning'][task], 
            data['non_reasoning'][task],
            'Reasoning Models',
            'Non-reasoning Models', 
            task_name
        )
        results.append(result)
    
    # Overall summary
    print(f"\n" + "="*70)
    print("OVERALL SUMMARY")
    print("="*70)
    
    for result in results:
        print(f"\n{result['task']}:")
        print(f"  Reasoning models mean RD@1: {result['group1_mean']:.2f}%")
        print(f"  Non-reasoning models mean RD@1: {result['group2_mean']:.2f}%")
        print(f"  Effect size (Cohen's d): {result['cohens_d']:.3f}")
        print(f"  Mann-Whitney U p-value: {result['mannwhitney_p']:.4f}")
        print(f"  Significant? {'YES' if result['significant'] else 'NO'}")
        
        if result['significant']:
            better_group = 'Reasoning' if result['group1_mean'] < result['group2_mean'] else 'Non-reasoning'
            print(f"  -> {better_group} models perform significantly better")
    
    # Test hypothesis
    print(f"\nHYPOTHESIS TEST RESULTS:")
    print(f"Hypothesis: Non-reasoning models perform better than reasoning models")
    
    j2p_supports = results[0]['group2_mean'] < results[0]['group1_mean'] and results[0]['significant']
    p2j_supports = results[1]['group2_mean'] < results[1]['group1_mean'] and results[1]['significant']
    
    if j2p_supports and p2j_supports:
        print("✓ HYPOTHESIS SUPPORTED in both tasks")
    elif j2p_supports or p2j_supports:
        supporting_task = "Java->Python" if j2p_supports else "Python->Java"
        print(f"◐ HYPOTHESIS PARTIALLY SUPPORTED (only in {supporting_task})")
    else:
        print("✗ HYPOTHESIS NOT SUPPORTED")
    
    # Create visualization
    print(f"\nCreating visualization...")
    create_visualization(data, results)
    
    # Save results to file
    print(f"\nSaving detailed results...")
    with open('significance_test_results.txt', 'w') as f:
        f.write("Statistical Significance Testing Results\n")
        f.write("="*50 + "\n\n")
        
        for result in results:
            f.write(f"Task: {result['task']}\n")
            f.write(f"Reasoning Models - Mean: {result['group1_mean']:.2f}%, Std: {result['group1_std']:.2f}%\n")
            f.write(f"Non-reasoning Models - Mean: {result['group2_mean']:.2f}%, Std: {result['group2_std']:.2f}%\n")
            f.write(f"Cohen's d: {result['cohens_d']:.3f}\n")
            f.write(f"Mann-Whitney U p-value: {result['mannwhitney_p']:.4f}\n")
            f.write(f"Significant: {'Yes' if result['significant'] else 'No'}\n")
            f.write("-" * 30 + "\n\n")
    
    print("Analysis complete! Check 'significance_test_results.txt' for detailed results.")

if __name__ == '__main__':
    main()