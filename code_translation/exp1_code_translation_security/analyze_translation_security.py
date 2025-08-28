#!/usr/bin/env python3

import json
import matplotlib.pyplot as plt
import seaborn as sns
import pandas as pd
import numpy as np
from pathlib import Path
from collections import defaultdict, Counter
import scipy.stats as stats
from itertools import combinations
import warnings
warnings.filterwarnings('ignore')

# Configuration
MODELS = ["claude", "gemini", "gpt-4o", "llama", "o3", "qwen3"]
QUESTIONS = ["Q1", "Q2", "Q3", "Q4", "Q5"]
LANGUAGES = ["C", "Go", "Java", "Python", "Rust"]

# Original languages for each question (exclude these from analysis)
ORIGINAL_LANGUAGES = {
    "Q1": "Python", "Q2": "Python", "Q3": "Python",
    "Q4": "JavaScript", "Q5": "C"
}

# Directories
GT_DIR = Path("security_classifications")
TRANSLATION_DIR = Path("security_classifications_translation_llm")
LLM_BASELINE_DIR = Path("security_classifications_llm_claude")
OUTPUT_DIR = Path("analysis_results")
OUTPUT_DIR.mkdir(exist_ok=True)

class SecurityAnalyzer:
    def __init__(self):
        self.data = defaultdict(dict)
        self.ground_truth = {}
        self.llm_baseline = {}
        self.collected_data = None

    def load_json(self, path):
        """Load JSON file safely."""
        try:
            with open(path, "r", encoding="utf-8") as f:
                return json.load(f)
        except FileNotFoundError:
            return {}

    def to_label(self, gt_entry):
        """Convert ground truth entry to binary label (0=secure, 1=vulnerable)."""
        return 0 if gt_entry["security"] == "secure" else 1

    def collect_all_data(self):
        """Collect all data from various sources."""
        print("Collecting all data...")

        # Collect ground truth
        for question in QUESTIONS:
            gt_path = GT_DIR / f"{question}.json"
            if gt_path.exists():
                self.ground_truth[question] = self.load_json(gt_path)

        # Collect LLM baseline
        for question in QUESTIONS:
            llm_path = LLM_BASELINE_DIR / f"{question}.json"
            if llm_path.exists():
                self.llm_baseline[question] = self.load_json(llm_path)

        # Collect translation data
        all_records = []
        for model in MODELS:
            for question in QUESTIONS:
                for language in LANGUAGES:
                    # Skip original language
                    if language == ORIGINAL_LANGUAGES.get(question):
                        continue

                    stats_path = TRANSLATION_DIR / model / question / f"{language}_stats.json"
                    if stats_path.exists():
                        trans_data = self.load_json(stats_path)
                        gt_data = self.ground_truth.get(question, {})

                        # Find common samples
                        common_ids = set(gt_data.keys()) & set(trans_data.keys())

                        for sample_id in common_ids:
                            gt_entry = gt_data[sample_id]
                            trans_entry = trans_data[sample_id]

                            record = {
                                'model': model,
                                'question': question,
                                'language': language,
                                'sample_id': sample_id,
                                'gt_vulnerable': self.to_label(gt_entry),
                                'gt_security': gt_entry['security'],
                                'trans_vulnerable': int(trans_entry.get('is_vulnerable', False)),
                                'trans_cwe': trans_entry.get('cwe', ''),
                                'trans_explanation': trans_entry.get('explanation', '')
                            }
                            all_records.append(record)

        self.collected_data = pd.DataFrame(all_records)
        print(f"Collected {len(all_records)} records")
        return self.collected_data

    def analyze_model_differences(self):
        """Analyze differences between translation models (based on Claude's security classification)."""
        print("Analyzing translation model differences...")

        if self.collected_data is None:
            self.collect_all_data()

        # Calculate vulnerability rates by model (these are vulnerabilities in translated code)
        model_stats = self.collected_data.groupby('model')['trans_vulnerable'].agg(['mean', 'count']).reset_index()
        model_stats.columns = ['model', 'vuln_rate', 'total_samples']
        model_stats['vuln_rate'] = model_stats['vuln_rate'] * 100

        # Model similarity matrix based on vulnerability classification
        similarity_matrix = np.zeros((len(MODELS), len(MODELS)))

        for i, model1 in enumerate(MODELS):
            for j, model2 in enumerate(MODELS):
                if i == j:
                    similarity_matrix[i, j] = 1.0
                else:
                    # Get common samples between two models
                    model1_data = self.collected_data[self.collected_data['model'] == model1]
                    model2_data = self.collected_data[self.collected_data['model'] == model2]

                    # Merge on question, language, sample_id
                    merged = model1_data.merge(
                        model2_data,
                        on=['question', 'language', 'sample_id'],
                        suffixes=('_1', '_2')
                    )

                    if len(merged) > 0:
                        agreement = (merged['trans_vulnerable_1'] == merged['trans_vulnerable_2']).mean()
                        similarity_matrix[i, j] = agreement

        # Visualization
        plt.figure(figsize=(10, 8))
        sns.heatmap(similarity_matrix,
                   annot=True,
                   xticklabels=MODELS,
                   yticklabels=MODELS,
                   cmap='RdYlBu_r',
                   center=0.5,
                   fmt='.3f')
        plt.yticks(rotation=90)
        plt.title('Translation Model Agreement Matrix\n(Security Classification by Claude)')
        plt.tight_layout()
        plt.savefig(OUTPUT_DIR / 'model_similarity_matrix.png', dpi=300)
        plt.close()

        # Bar plot of vulnerability rates (vulnerabilities in translated code)
        plt.figure(figsize=(10, 6))
        bars = plt.bar(model_stats['model'], model_stats['vuln_rate'], alpha=0.7, label='Vulnerability Rate')
        plt.grid(True, axis='y', linestyle='--', alpha=0.7)
        # plt.legend(loc='upper center', bbox_to_anchor=(0.5, 1.05))
        # plt.title('Vulnerability Rate in Translated Code by Model\n(Detected by Claude)')
        plt.ylabel('Vulnerability Rate (%)')
        plt.xlabel('Translation Model')
        plt.xticks(rotation=45)

        # # Add value labels on bars
        # for bar, rate in zip(bars, model_stats['vuln_rate']):
        #     plt.text(bar.get_x() + bar.get_width()/2, bar.get_height() + 0.5,
        #             f'{rate:.1f}%', ha='center', va='bottom')

        plt.tight_layout()
        plt.savefig(OUTPUT_DIR / 'vulnerability_rates_by_model.pdf', dpi=300)
        plt.close()

        # Cochran's Q test for statistical significance
        print("Performing Cochran's Q test for model differences...")

        # Prepare data for Cochran's Q test
        # Need to find common samples across models
        pivot_samples = self.collected_data.pivot_table(
            index=['question', 'language', 'sample_id'],
            columns='model',
            values='trans_vulnerable',
            aggfunc='first'
        )

        # Only use samples that have data for all models
        complete_samples = pivot_samples.dropna()

        if len(complete_samples) > 0:
            print(f"Found {len(complete_samples)} complete samples across all models")

            # Perform Cochran's Q test
            from statsmodels.stats.contingency_tables import mcnemar
            from scipy.stats import chi2

            # Calculate Cochran's Q statistic manually
            k = len(MODELS)  # number of treatments (models)
            n = len(complete_samples)  # number of subjects (samples)

            # Calculate row sums (total vulnerabilities per sample across models)
            R_i = complete_samples.sum(axis=1)

            # Calculate column sums (total vulnerabilities per model)
            C_j = complete_samples.sum(axis=0)

            # Calculate Q statistic
            sum_C_j_squared = sum(C_j**2)
            sum_R_i_squared = sum(R_i**2)
            total_sum = sum(C_j)

            # Check for potential division by zero
            denominator = k * sum(R_i) - sum_R_i_squared
            if denominator == 0:
                print("Warning: Denominator is zero in Cochran's Q calculation. Cannot perform test.")
                cochran_results = None
                return model_stats, similarity_matrix, cochran_results

            Q = (k - 1) * (k * sum_C_j_squared - total_sum**2) / denominator

            # Additional validation
            if Q < 0:
                print("Warning: Negative Q statistic calculated. This suggests a calculation error.")
                print(f"Q = {Q}")
                print(f"k = {k}, n = {n}")
                print(f"Column sums (C_j): {list(C_j)}")
                print(f"sum_C_j_squared = {sum_C_j_squared}")
                print(f"total_sum = {total_sum}")
                print(f"denominator = {denominator}")
                cochran_results = None
                return model_stats, similarity_matrix, cochran_results

            # Degrees of freedom
            df = k - 1

            # P-value with better precision handling
            if Q > 100:  # Very large Q statistic
                p_value = 0.0  # Essentially zero
                p_value_text = "< 1e-15 (essentially 0)"
            else:
                p_value = 1 - chi2.cdf(Q, df)
                if p_value < 1e-10:
                    p_value_text = f"< 1e-10 (very small)"
                else:
                    p_value_text = f"{p_value:.8f}"

            print(f"Cochran's Q test results:")
            print(f"  Number of models (k): {k}")
            print(f"  Number of complete samples (n): {n}")
            print(f"  Q statistic: {Q:.4f}")
            print(f"  Degrees of freedom: {df}")
            print(f"  P-value: {p_value_text}")
            print(f"  Alpha level: 0.05")

            # Show individual model vulnerability rates for context
            print(f"  Model vulnerability rates on common samples:")
            for model in MODELS:
                if model in complete_samples.columns:
                    model_rate = complete_samples[model].mean() * 100
                    model_count = complete_samples[model].sum()
                    print(f"    {model}: {model_rate:.1f}% ({model_count}/{n} vulnerable)")

            # Calculate expected frequency if no difference
            overall_rate = complete_samples.values.mean()
            expected_per_model = overall_rate * n
            print(f"  Expected frequency per model if no difference: {expected_per_model:.1f}")

            # Calculate chi-square critical value for reference
            critical_value = chi2.ppf(0.95, df)
            print(f"  Critical value at α=0.05: {critical_value:.4f}")

            if p_value < 0.05:
                print("  Result: SIGNIFICANT - Models differ significantly in vulnerability rates")
            else:
                print("  Result: NOT SIGNIFICANT - No significant difference between models")

            # Add results to model_stats for return
            cochran_results = {
                'Q_statistic': Q,
                'df': df,
                'p_value': p_value,
                'p_value_text': p_value_text,
                'n_samples': n,
                'significant': p_value < 0.05
            }

            # Save detailed results
            with open(OUTPUT_DIR / 'cochran_q_test_results.txt', 'w') as f:
                f.write("Cochran's Q Test for Model Differences\n")
                f.write("="*40 + "\n\n")
                f.write(f"Test for significant differences in vulnerability rates across translation models\n\n")
                f.write(f"Number of models (k): {k}\n")
                f.write(f"Number of complete samples (n): {n}\n")
                f.write(f"Q statistic: {Q:.6f}\n")
                f.write(f"Degrees of freedom: {df}\n")
                f.write(f"P-value: {p_value_text}\n")
                f.write(f"Significance level (α): 0.05\n\n")

                # Add detailed analysis information
                f.write("Detailed Analysis:\n")
                f.write(f"Overall vulnerability rate across all models: {overall_rate:.3f}\n")
                f.write(f"Expected vulnerabilities per model (if no difference): {expected_per_model:.1f}\n")
                f.write(f"Chi-square critical value (α=0.05, df={df}): {critical_value:.4f}\n\n")

                f.write("Individual Model Results:\n")
                for model in MODELS:
                    if model in complete_samples.columns:
                        model_rate = complete_samples[model].mean()
                        model_count = complete_samples[model].sum()
                        f.write(f"  {model}: {model_rate:.3f} rate, {model_count} vulnerable out of {n}\n")
                f.write("\n")

                # Add debug information
                f.write("Calculation Details:\n")
                f.write(f"Column sums (C_j): {dict(zip(MODELS, C_j))}\n")
                f.write(f"Sum of column sums: {total_sum}\n")
                f.write(f"Sum of squared column sums: {sum_C_j_squared}\n")
                f.write(f"Row sum statistics: mean={R_i.mean():.3f}, std={R_i.std():.3f}\n")
                f.write(f"Sum of squared row sums: {sum_R_i_squared}\n")
                f.write(f"Denominator: k*sum(R_i) - sum(R_i²) = {k}*{sum(R_i)} - {sum_R_i_squared} = {denominator}\n")
                f.write(f"Numerator: (k-1) * [k*sum(C_j²) - (sum(C_j))²] = {k-1} * [{k}*{sum_C_j_squared} - {total_sum}²]\n")
                f.write(f"         = {k-1} * [{k * sum_C_j_squared} - {total_sum**2}] = {k-1} * {k * sum_C_j_squared - total_sum**2}\n\n")

                if p_value < 0.05:
                    f.write("CONCLUSION: The translation models show statistically significant differences ")
                    f.write("in their vulnerability rates (p < 0.05).\n\n")
                else:
                    f.write("CONCLUSION: No statistically significant differences found between ")
                    f.write("translation models in terms of vulnerability rates (p ≥ 0.05).\n\n")

                f.write("Model vulnerability rates (on complete sample set):\n")
                for model in MODELS:
                    if model in complete_samples.columns:
                        model_rate = complete_samples[model].mean() * 100
                        f.write(f"  {model}: {model_rate:.2f}%\n")

            print(f"Detailed results saved to {OUTPUT_DIR / 'cochran_q_test_results.txt'}")

        else:
            print("Warning: No samples found that are common to all models. Cannot perform Cochran's Q test.")
            cochran_results = None

        return model_stats, similarity_matrix, cochran_results

    def analyze_language_vs_model_influence(self):
        """Analyze language vs model influence using variance decomposition."""
        LEGEND_LABEL_MODELS = {
            "claude": "Claude4",
            "gemini": "Gemini",
            "gpt-4o": "GPT-4o",
            "llama": "Llama4",
            "o3": "o3",
            "qwen3": "Qwen3"
        }

        print("Analyzing language vs model influence...")

        if self.collected_data is None:
            self.collect_all_data()

        # Calculate vulnerability rates by model, language, and question
        lang_model_question_stats = self.collected_data.groupby(['model', 'language', 'question'])['trans_vulnerable'].mean().reset_index()
        lang_model_question_stats['vuln_rate'] = lang_model_question_stats['trans_vulnerable'] * 100

        # Calculate LLM baseline rates for each question
        baseline_rates = {}
        for question in QUESTIONS:
            if question in self.llm_baseline:
                baseline_data = self.llm_baseline[question]
                if baseline_data:
                    baseline_vuln_rate = sum(1 for entry in baseline_data.values() if entry.get('is_vulnerable', False)) / len(baseline_data) * 100
                    baseline_rates[question] = baseline_vuln_rate

        # Calculate relative rates compared to baseline for each model-language combination
        lang_model_relative = []
        for model in MODELS:
            for language in LANGUAGES:
                # Get data for this model-language combination across all questions
                model_lang_data = lang_model_question_stats[
                    (lang_model_question_stats['model'] == model) &
                    (lang_model_question_stats['language'] == language)
                ]

                if len(model_lang_data) == 0:
                    continue

                # Calculate weighted average relative to baseline
                total_relative = 0
                total_weight = 0

                for _, row in model_lang_data.iterrows():
                    question = row['question']
                    vuln_rate = row['vuln_rate']

                    if question in baseline_rates:
                        baseline_rate = baseline_rates[question]
                        relative_rate = vuln_rate - baseline_rate

                        # Weight by number of samples for this question
                        question_samples = len(self.collected_data[
                            (self.collected_data['model'] == model) &
                            (self.collected_data['language'] == language) &
                            (self.collected_data['question'] == question)
                        ])

                        total_relative += relative_rate * question_samples
                        total_weight += question_samples

                if total_weight > 0:
                    weighted_relative = total_relative / total_weight
                    # Also calculate simple average for comparison
                    simple_avg_vuln = model_lang_data['vuln_rate'].mean()

                    lang_model_relative.append({
                        'model': model,
                        'language': language,
                        'vuln_rate': simple_avg_vuln,
                        'relative_rate': weighted_relative
                    })

        lang_model_relative_df = pd.DataFrame(lang_model_relative)

        # Also calculate overall stats for backward compatibility
        lang_model_stats = self.collected_data.groupby(['model', 'language'])['trans_vulnerable'].mean().reset_index()
        lang_model_stats['vuln_rate'] = lang_model_stats['trans_vulnerable'] * 100

        # Map model names using LEGEND_LABEL_MODELS
        lang_model_stats['model_display'] = lang_model_stats['model'].map(LEGEND_LABEL_MODELS)
        lang_model_relative_df['model_display'] = lang_model_relative_df['model'].map(LEGEND_LABEL_MODELS)
        
        # Pivot for analysis - use absolute rates for variance analysis
        pivot_data = lang_model_stats.pivot(index='model_display', columns='language', values='vuln_rate')

        # Pivot for relative heatmap
        pivot_relative = lang_model_relative_df.pivot(index='model_display', columns='language', values='relative_rate')

        # Calculate variance components (still using absolute rates)
        all_rates = lang_model_stats['vuln_rate'].values
        total_var = np.var(all_rates, ddof=1)

        # Between-model variance
        model_means = lang_model_stats.groupby('model')['vuln_rate'].mean()
        between_model_var = np.var(model_means.values, ddof=1)

        # Between-language variance
        lang_means = lang_model_stats.groupby('language')['vuln_rate'].mean()
        between_lang_var = np.var(lang_means.values, ddof=1)

        # First pass: collect all relative values to determine shared colorbar range
        all_relative_data = []
        question_data = {}

        for question in QUESTIONS:
            # Filter data for current question
            q_data = lang_model_question_stats[lang_model_question_stats['question'] == question]

            if len(q_data) == 0:
                continue

            # Get baseline for this question
            baseline_rate = baseline_rates.get(question, 0)

            # Calculate relative rates for this question
            q_data_relative = q_data.copy()
            q_data_relative['relative_rate'] = q_data_relative['vuln_rate'] - baseline_rate

            # Map model names using LEGEND_LABEL_MODELS
            q_data['model_display'] = q_data['model'].map(LEGEND_LABEL_MODELS)
            q_data_relative['model_display'] = q_data_relative['model'].map(LEGEND_LABEL_MODELS)
            
            # Create pivot tables
            pivot_absolute = q_data.pivot(index='model_display', columns='language', values='vuln_rate')
            pivot_relative = q_data_relative.pivot(index='model_display', columns='language', values='relative_rate')

            # Skip if pivot tables are empty
            if pivot_absolute.empty or pivot_relative.empty:
                continue

            # Store data for second pass
            question_data[question] = {
                'pivot_absolute': pivot_absolute,
                'pivot_relative': pivot_relative,
                'baseline_rate': baseline_rate
            }

            # Collect all relative values for range calculation
            all_relative_data.extend(pivot_relative.values.flatten())

        # Calculate shared colorbar range
        if all_relative_data:
            all_relative_data = [x for x in all_relative_data if not np.isnan(x)]
            if all_relative_data:
                vmin = min(all_relative_data)
                vmax = max(all_relative_data)
                # Make range symmetric around 0 for better visualization
                abs_max = max(abs(vmin), abs(vmax))
                vmin_shared = -abs_max
                vmax_shared = abs_max
            else:
                vmin_shared, vmax_shared = -10, 10
        else:
            vmin_shared, vmax_shared = -10, 10

        # Second pass: create heatmaps with shared colorbar range
        for question in QUESTIONS:
            if question not in question_data:
                continue

            data = question_data[question]
            pivot_absolute = data['pivot_absolute']
            pivot_relative = data['pivot_relative']
            baseline_rate = data['baseline_rate']

            # Relative heatmap for this question (with special cbar for Q5)
            plt.figure(figsize=(8, 9))
            if question == QUESTIONS[4]:  # Q5
                # Use fixed color range and show colorbar from -60 to 60
                plt.figure(figsize=(10, 9))
                sns.heatmap(pivot_relative,
                           annot=True,
                           fmt='.1f',
                           cmap='RdYlBu_r',
                           center=0,
                           cbar=True,
                           vmin=-60,
                           vmax=60)
                plt.yticks(rotation=90)
            else:
                sns.heatmap(pivot_relative,
                           annot=True,
                           fmt='.1f',
                           cmap='RdYlBu_r',
                           center=0,
                           cbar=False)
                plt.yticks(rotation=90)

            # plt.title(f'{question}: Vulnerability Rate Relative to LLM Baseline\n(Baseline: {baseline_rate:.1f}%)')
            print(f"Creating heatmap for {question} with baseline {baseline_rate:.1f}%")
            plt.tight_layout()
            plt.savefig(OUTPUT_DIR / f'heatmap_{question}_relative.pdf', dpi=300, bbox_inches='tight')
            plt.close()

            # Absolute heatmap for this question
            plt.figure(figsize=(10, 8))
            sns.heatmap(pivot_absolute,
                       annot=True,
                       fmt='.1f',
                       cmap='RdYlBu_r',
                       center=50)
            plt.yticks(rotation=90)
            plt.title(f'{question}: Absolute Vulnerability Rate (%)')
            plt.tight_layout()
            plt.savefig(OUTPUT_DIR / f'heatmap_{question}_absolute.png', dpi=300)
            plt.close()

        # Create shared horizontal colorbar legend
        if question_data:
            # Increase figure width for a longer horizontal colorbar
            fig, ax = plt.subplots(figsize=(2, 6))

            # Create dummy heatmap just for the colorbar
            dummy_data = np.array([[vmin_shared, vmax_shared]])
            im = ax.imshow(dummy_data, cmap='RdYlBu_r', vmin=vmin_shared, vmax=vmax_shared)
            ax.set_visible(False)  # Hide the dummy plot

            # Create horizontal colorbar with increased length and label above
            # aspect controls the length-to-thickness ratio; larger values make the bar longer
            cbar = plt.colorbar(im, ax=ax, fraction=1.0,  orientation='vertical')
            cbar.ax.xaxis.set_label_position('top')
            cbar.ax.xaxis.tick_top()
            cbar.set_label('Relative Vulnerability Rate (%)', labelpad=5)

            plt.tight_layout()
            plt.savefig(OUTPUT_DIR / 'heatmap_relative_colorbar_legend.pdf', dpi=300, bbox_inches='tight')
            plt.close()

        # Also create the overall aggregated heatmaps for comparison
        # Visualization: Relative Heatmap (compared to LLM baseline)
        plt.figure(figsize=(12, 8))
        sns.heatmap(pivot_relative,
                   annot=True,
                   fmt='.1f',
                   cmap='RdYlBu_r',
                   center=0,
                   cbar_kws={'label': 'Relative Vulnerability Rate (%)'})
        plt.yticks(rotation=90)
        plt.title('Overall Vulnerability Rate Relative to LLM Baseline by Model and Language\n(Weighted Average Across All Questions)')
        plt.tight_layout()
        plt.savefig(OUTPUT_DIR / 'model_language_heatmap_relative_overall.png', dpi=300)
        plt.close()

        # Also keep the original absolute heatmap for comparison
        plt.figure(figsize=(12, 8))
        sns.heatmap(pivot_data,
                   annot=True,
                   fmt='.1f',
                   cmap='RdYlBu_r',
                   center=50)
        plt.yticks(rotation=90)
        plt.title('Overall Absolute Vulnerability Rate (%) by Model and Language')
        plt.tight_layout()
        plt.savefig(OUTPUT_DIR / 'model_language_heatmap_absolute_overall.png', dpi=300)
        plt.close()

        # Create a more meaningful comparison: vulnerability rates by model and language
        # This replaces the box plot which wasn't informative
        model_lang_comparison = self.collected_data.groupby(['model', 'language'])['trans_vulnerable'].agg(['mean', 'count']).reset_index()
        model_lang_comparison.columns = ['model', 'language', 'vuln_rate', 'count']
        model_lang_comparison['vuln_rate'] = model_lang_comparison['vuln_rate'] * 100

        # Filter out combinations with very few samples
        model_lang_comparison = model_lang_comparison[model_lang_comparison['count'] >= 50]

        # Create grouped bar chart
        fig, ax = plt.subplots(figsize=(16, 8))

        # Prepare data for grouped bar chart
        models = sorted(model_lang_comparison['model'].unique())
        languages = sorted(model_lang_comparison['language'].unique())

        x = np.arange(len(models))
        width = 0.15
        # Use Matplotlib's default color cycle instead of hardcoded colors
        prop_cycle = plt.rcParams['axes.prop_cycle']
        colors = prop_cycle.by_key()['color']

        for i, lang in enumerate(languages):
            lang_data = model_lang_comparison[model_lang_comparison['language'] == lang]
            values = [lang_data[lang_data['model'] == m]['vuln_rate'].iloc[0] if len(lang_data[lang_data['model'] == m]) > 0 else 0 for m in models]

            bars = ax.bar(x + i * width, values, width, label=f'{lang}', color=colors[i % len(colors)])

            # # Add value labels on bars
            # for bar, value in zip(bars, values):
            #     if value > 0:
            #         ax.text(bar.get_x() + bar.get_width()/2, bar.get_height() + 1,
            #                f'{value:.1f}%', ha='center', va='bottom', fontsize=8)

        ax.set_xlabel('Translation Model')
        ax.set_ylabel('Vulnerability Rate (%)')
        ax.set_title('Vulnerability Rates by Model and Target Language\n(Showing translation quality impact)')
        ax.set_xticks(x + width * 2)
        ax.set_xticklabels(models)
        ax.legend()
        ax.grid(True, alpha=0.7)

        plt.tight_layout()
        plt.savefig(OUTPUT_DIR / 'vulnerability_rates_model_language.png', dpi=300)
        plt.close()

        # Calculate effect sizes
        variance_analysis = {
            'total_variance': total_var,
            'between_model_variance': between_model_var,
            'between_language_variance': between_lang_var,
            'model_effect_size': between_model_var / total_var,
            'language_effect_size': between_lang_var / total_var
        }

        return variance_analysis, pivot_data

    def analyze_question_differences(self):
        """Analyze differences between questions in terms of translation security."""
        print("Analyzing question differences...")

        if self.collected_data is None:
            self.collect_all_data()

        # Calculate statistics by question
        question_stats = []
        for question in QUESTIONS:
            q_data = self.collected_data[self.collected_data['question'] == question]
            if len(q_data) > 0:
                gt_vuln_rate = q_data['gt_vulnerable'].mean() * 100
                trans_vuln_rate = q_data['trans_vulnerable'].mean() * 100

                # Calculate variance across models for this question
                model_variance = q_data.groupby('model')['trans_vulnerable'].mean().var()

                question_stats.append({
                    'question': question,
                    'gt_vuln_rate': gt_vuln_rate,
                    'trans_vuln_rate': trans_vuln_rate,
                    'model_variance': model_variance,
                    'sample_count': len(q_data)
                })

        question_df = pd.DataFrame(question_stats)

        # Radar chart for question characteristics
        fig, ax = plt.subplots(figsize=(10, 10), subplot_kw=dict(projection='polar'))

        angles = np.linspace(0, 2 * np.pi, len(QUESTIONS), endpoint=False).tolist()
        angles += angles[:1]  # Complete the circle

        # Ground truth vulnerability rates
        gt_rates = question_df['gt_vuln_rate'].tolist()
        gt_rates += gt_rates[:1]
        ax.plot(angles, gt_rates, 'o-', linewidth=2, label='Original Code', color='black')
        ax.fill(angles, gt_rates, alpha=0.25, color='black')

        # Translation vulnerability rates
        trans_rates = question_df['trans_vuln_rate'].tolist()
        trans_rates += trans_rates[:1]
        ax.plot(angles, trans_rates, 'o-', linewidth=2, label='Translated Code', color='red')
        ax.fill(angles, trans_rates, alpha=0.25, color='red')

        ax.set_xticks(angles[:-1])
        ax.set_xticklabels(QUESTIONS)
        ax.set_ylim(0, 100)
        ax.set_title('Vulnerability Rates: Original vs Translated Code', y=1.08)
        ax.legend(loc='upper right', bbox_to_anchor=(1.3, 1.0))

        plt.tight_layout()
        plt.savefig(OUTPUT_DIR / 'question_radar_chart.png', dpi=300, bbox_inches='tight')
        plt.close()

        # Model variance by question
        plt.figure(figsize=(10, 6))
        bars = plt.bar(question_df['question'], question_df['model_variance'],
                      color='orange', alpha=0.7)
        plt.title('Translation Model Variance by Question\n(How much do models differ for each question?)')
        plt.ylabel('Variance in Vulnerability Rate')
        plt.xlabel('Question')

        # # Add value labels
        # for bar, var in zip(bars, question_df['model_variance']):
        #     plt.text(bar.get_x() + bar.get_width()/2, bar.get_height() + 0.001,
        #             f'{var:.3f}', ha='center', va='bottom')

        plt.tight_layout()
        plt.savefig(OUTPUT_DIR / 'model_variance_by_question.png', dpi=300)
        plt.close()

        return question_df

    def get_cwe_category(self, cwe):
        BUCKETS = {
            "Bad Coding Practices": ['CWE-358', 'CWE-360', 'CWE-478', 'CWE-487', 'CWE-489', 'CWE-547', 'CWE-561', 'CWE-562', 'CWE-563', 'CWE-581', 'CWE-586', 'CWE-605', 'CWE-628', 'CWE-654', 'CWE-656', 'CWE-694', 'CWE-807', 'CWE-1041', 'CWE-1043', 'CWE-1044', 'CWE-1045', 'CWE-1046', 'CWE-1048', 'CWE-1049', 'CWE-1050', 'CWE-1063', 'CWE-1065', 'CWE-1066', 'CWE-1067', 'CWE-1070', 'CWE-1071', 'CWE-1072', 'CWE-1073', 'CWE-1079', 'CWE-1082', 'CWE-1084', 'CWE-1085', 'CWE-1087', 'CWE-1089', 'CWE-1092', 'CWE-1094', 'CWE-1097', 'CWE-1098', 'CWE-1099', 'CWE-1101', 'CWE-1102', 'CWE-1103', 'CWE-1104', 'CWE-1106', 'CWE-1107', 'CWE-1108', 'CWE-1109', 'CWE-1113', 'CWE-1114', 'CWE-1115', 'CWE-1116', 'CWE-1117', 'CWE-1126', 'CWE-1127', 'CWE-1235'],
            "Audit / Logging Errors": ['CWE-117', 'CWE-222', 'CWE-223', 'CWE-224', 'CWE-778', 'CWE-779'],
            "Authentication Errors": ['CWE-289', 'CWE-290', 'CWE-294', 'CWE-295', 'CWE-301', 'CWE-303', 'CWE-305', 'CWE-306', 'CWE-307', 'CWE-308', 'CWE-309', 'CWE-322', 'CWE-603', 'CWE-645', 'CWE-804', 'CWE-836'],
            "Authorization Errors": ['CWE-425', 'CWE-551', 'CWE-552', 'CWE-639', 'CWE-653', 'CWE-842', 'CWE-939', 'CWE-1220', 'CWE-1230'],
            "Random Number Issues": ['CWE-331', 'CWE-334', 'CWE-335', 'CWE-338', 'CWE-341', 'CWE-342', 'CWE-343', 'CWE-344', 'CWE-1241'],
            "Data Integrity Issues": ['CWE-322', 'CWE-346', 'CWE-347', 'CWE-348', 'CWE-349', 'CWE-351', 'CWE-353', 'CWE-354', 'CWE-494', 'CWE-565', 'CWE-649', 'CWE-829', 'CWE-924'],
            "Data Validation Issues": ['CWE-20', 'CWE-112', 'CWE-179', 'CWE-183', 'CWE-184', 'CWE-606', 'CWE-641', 'CWE-1173', 'CWE-1284', 'CWE-1285', 'CWE-1286', 'CWE-1287', 'CWE-1288', 'CWE-1289'],
            "Lockout Mechanism Errors": ['CWE-645'],
            "User Session Errors": ['CWE-488', 'CWE-613', 'CWE-841'],
            "Memory Buffer Errors": ['CWE-120', 'CWE-124', 'CWE-125', 'CWE-131', 'CWE-786', 'CWE-787', 'CWE-788', 'CWE-805', 'CWE-1284'],
            "File Handling Issues": ['CWE-22', 'CWE-41', 'CWE-59', 'CWE-66', 'CWE-378', 'CWE-379', 'CWE-426', 'CWE-427', 'CWE-428'],
            "Documentation Issues": ['CWE-1053', 'CWE-1068', 'CWE-1110', 'CWE-1111', 'CWE-1112', 'CWE-1118'],
            "Complexity Issues": ['CWE-1043', 'CWE-1047', 'CWE-1055', 'CWE-1056', 'CWE-1060', 'CWE-1064', 'CWE-1074', 'CWE-1075', 'CWE-1080', 'CWE-1086', 'CWE-1095', 'CWE-1119', 'CWE-1121', 'CWE-1122', 'CWE-1123', 'CWE-1124', 'CWE-1125', 'CWE-1333'],
            "Encapsulation Issues": ['CWE-1054', 'CWE-1057', 'CWE-1062', 'CWE-1083', 'CWE-1090', 'CWE-1100', 'CWE-1105'],
            "API / Function Errors": ['CWE-242', 'CWE-474', 'CWE-475', 'CWE-477', 'CWE-676', 'CWE-695', 'CWE-749'],
            "String Errors": ['CWE-134', 'CWE-135', 'CWE-480'],
            "Type Errors": ['CWE-681', 'CWE-843', 'CWE-1287'],
            "Data Neutralization Issues": ['CWE-76', 'CWE-78', 'CWE-79', 'CWE-88', 'CWE-89', 'CWE-90', 'CWE-91', 'CWE-93', 'CWE-94', 'CWE-117', 'CWE-140', 'CWE-170', 'CWE-463', 'CWE-464', 'CWE-641', 'CWE-694', 'CWE-791', 'CWE-838', 'CWE-917', 'CWE-1236'],
            "Numeric Errors": ['CWE-128', 'CWE-190', 'CWE-191', 'CWE-193', 'CWE-369', 'CWE-681', 'CWE-839', 'CWE-1335', 'CWE-1339', 'CWE-1389'],
            "Data Processing Errors": ['CWE-130', 'CWE-166', 'CWE-167', 'CWE-168', 'CWE-178', 'CWE-182', 'CWE-186', 'CWE-229', 'CWE-233', 'CWE-237', 'CWE-241', 'CWE-409', 'CWE-472', 'CWE-601', 'CWE-611', 'CWE-624', 'CWE-625', 'CWE-776', 'CWE-1024'],
            "Information Management Errors": ['CWE-201', 'CWE-204', 'CWE-205', 'CWE-208', 'CWE-209', 'CWE-212', 'CWE-213', 'CWE-214', 'CWE-215', 'CWE-312', 'CWE-319', 'CWE-359', 'CWE-497', 'CWE-524', 'CWE-532', 'CWE-538', 'CWE-921', 'CWE-1230'],
            "Credentials Management Errors": ['CWE-256', 'CWE-257', 'CWE-260', 'CWE-261', 'CWE-262', 'CWE-263', 'CWE-324', 'CWE-521', 'CWE-523', 'CWE-549', 'CWE-620', 'CWE-640', 'CWE-798', 'CWE-916', 'CWE-1392'],
            "Privilege Issues": ['CWE-243', 'CWE-250', 'CWE-266', 'CWE-267', 'CWE-268', 'CWE-270', 'CWE-272', 'CWE-273', 'CWE-274', 'CWE-280', 'CWE-501', 'CWE-580', 'CWE-648'],
            "Permission Issues": ['CWE-276', 'CWE-277', 'CWE-278', 'CWE-279', 'CWE-280', 'CWE-281', 'CWE-618', 'CWE-766', 'CWE-767'],
            "Cryptographic Issues": ['CWE-261', 'CWE-324', 'CWE-325', 'CWE-327', 'CWE-328', 'CWE-329', 'CWE-331', 'CWE-334', 'CWE-335', 'CWE-338', 'CWE-347', 'CWE-916', 'CWE-1204', 'CWE-1240'],
            "Key Management Errors": ['CWE-322', 'CWE-323', 'CWE-324', 'CWE-798'],
            "User Interface Security Issues": ['CWE-356', 'CWE-357', 'CWE-447', 'CWE-448', 'CWE-449', 'CWE-549', 'CWE-1007', 'CWE-1021'],
            "State Issues": ['CWE-15', 'CWE-372', 'CWE-374', 'CWE-375', 'CWE-1265'],
            "Signal Errors": ['CWE-364'],
            "Error Conditions, Return Values, Status Codes": ['CWE-209', 'CWE-248', 'CWE-252', 'CWE-253', 'CWE-390', 'CWE-391', 'CWE-392', 'CWE-393', 'CWE-394', 'CWE-395', 'CWE-396', 'CWE-397', 'CWE-544', 'CWE-584', 'CWE-617', 'CWE-756'],
            "Resource Management Errors": ['CWE-73', 'CWE-403', 'CWE-410', 'CWE-470', 'CWE-502', 'CWE-619', 'CWE-641', 'CWE-694', 'CWE-763', 'CWE-770', 'CWE-771', 'CWE-772', 'CWE-826', 'CWE-908', 'CWE-909', 'CWE-910', 'CWE-911', 'CWE-914', 'CWE-915', 'CWE-920', 'CWE-1188', 'CWE-1341'],
            "Resource Locking Problems": ['CWE-412', 'CWE-413', 'CWE-414', 'CWE-609', 'CWE-764', 'CWE-765', 'CWE-832', 'CWE-833'],
            "Communication Channel Errors": ['CWE-322', 'CWE-346', 'CWE-385', 'CWE-419', 'CWE-420', 'CWE-425', 'CWE-515', 'CWE-918', 'CWE-924', 'CWE-940', 'CWE-941', 'CWE-1327'],
            "Handler Errors": ['CWE-430', 'CWE-431', 'CWE-434'],
            "Behavioral Problems": ['CWE-115', 'CWE-179', 'CWE-408', 'CWE-437', 'CWE-439', 'CWE-440', 'CWE-444', 'CWE-480', 'CWE-483', 'CWE-484', 'CWE-551', 'CWE-698', 'CWE-733', 'CWE-783', 'CWE-835', 'CWE-837', 'CWE-841', 'CWE-1025', 'CWE-1037'],
            "Initialization and Cleanup Errors": ['CWE-212', 'CWE-454', 'CWE-455', 'CWE-459', 'CWE-1051', 'CWE-1052', 'CWE-1188'],
            "Pointer Issues": ['CWE-466', 'CWE-468', 'CWE-469', 'CWE-476', 'CWE-587', 'CWE-763', 'CWE-822', 'CWE-823', 'CWE-824', 'CWE-825'],
            "Concurrency Issues": ['CWE-364', 'CWE-366', 'CWE-367', 'CWE-368', 'CWE-386', 'CWE-421', 'CWE-663', 'CWE-820', 'CWE-821', 'CWE-1058', 'CWE-1322'],
            "Expression Issues": ['CWE-480', 'CWE-570', 'CWE-571', 'CWE-783'],
            "Business Logic Errors": ['CWE-283', 'CWE-639', 'CWE-640', 'CWE-708', 'CWE-770', 'CWE-826', 'CWE-837', 'CWE-841'],
        }

        for name, cwes in BUCKETS.items():
            if cwe in cwes:
                return name
        return "Other"

    def analyze_cwe_patterns(self):
        """Analyze CWE patterns and compare with baseline."""
        print("Analyzing CWE patterns...")

        if self.collected_data is None:
            self.collect_all_data()

        # Get LLM baseline CWE data
        baseline_cwe_data = []
        for question in QUESTIONS:
            if question in self.llm_baseline:
                for sample_id, entry in self.llm_baseline[question].items():
                    if entry.get('cwe') and entry.get('is_vulnerable'):
                        baseline_cwe_data.append({
                            'question': question,
                            'sample_id': sample_id,
                            'cwe': entry['cwe'],
                            'cwe_category': self.get_cwe_category(entry['cwe'])
                        })

        baseline_df = pd.DataFrame(baseline_cwe_data)

        # Filter translation CWE data and add categories
        trans_cwe_data = self.collected_data[
            self.collected_data['trans_cwe'].notna() &
            (self.collected_data['trans_cwe'] != '') &
            (self.collected_data['trans_vulnerable'] == 1)
        ].copy()

        trans_cwe_data['cwe_category'] = trans_cwe_data['trans_cwe'].apply(self.get_cwe_category)

        # Top CWE categories in translations
        top_categories = Counter(trans_cwe_data['cwe_category']).most_common(8)
        trans_categories = Counter(trans_cwe_data['cwe_category'])

        # Visualization: Top CWE categories
        plt.figure(figsize=(12, 8))
        categories = [cat[0] for cat in top_categories]
        counts = [cat[1] for cat in top_categories]

        bars = plt.bar(categories, counts, color='coral', alpha=0.7)
        plt.title('Top CWE Categories in Translated Code')
        plt.ylabel('Frequency')
        plt.xlabel('CWE Category')
        plt.xticks(rotation=45, ha='right')

        # # Add value labels
        # for bar, count in zip(bars, counts):
        #     plt.text(bar.get_x() + bar.get_width()/2, bar.get_height() + 5,
        #             str(count), ha='center', va='bottom')

        plt.tight_layout()
        plt.savefig(OUTPUT_DIR / 'top_cwe_categories.png', dpi=300)
        plt.close()

        # Compare baseline vs translations - better visualization
        if len(baseline_df) > 0:
            baseline_categories = Counter(baseline_df['cwe_category'])

            # Get top categories from both baseline and translations
            all_categories = set(baseline_categories.keys()) | set(trans_categories.keys())
            # Sort by total frequency
            category_totals = {}
            for cat in all_categories:
                category_totals[cat] = baseline_categories.get(cat, 0) + trans_categories.get(cat, 0)

            top_categories = sorted(category_totals.items(), key=lambda x: x[1], reverse=True)[:6]
            common_categories = [cat[0] for cat in top_categories]

            baseline_counts = [baseline_categories.get(cat, 0) for cat in common_categories]
            trans_counts = [trans_categories.get(cat, 0) for cat in common_categories]

            # Side-by-side comparison
            x = np.arange(len(common_categories))
            width = 0.35

            fig, ax = plt.subplots(figsize=(14, 8))
            bars1 = ax.bar(x - width/2, baseline_counts, width, label='Original Code (LLM Baseline)', alpha=0.7, color='lightblue')
            bars2 = ax.bar(x + width/2, trans_counts, width, label='Translated Code (All Models)', alpha=0.7, color='lightcoral')

            ax.set_xlabel('CWE Category')
            ax.set_ylabel('Frequency')
            ax.set_title('CWE Distribution: Original vs Translated Code\n(Showing vulnerability types before and after translation)')
            ax.set_xticks(x)
            ax.set_xticklabels(common_categories, rotation=45, ha='right')
            ax.legend()

            # # Add value labels
            # for bars in [bars1, bars2]:
            #     for bar in bars:
            #         height = bar.get_height()
            #         if height > 0:
            #             ax.text(bar.get_x() + bar.get_width()/2., height + 5,
            #                    f'{int(height)}', ha='center', va='bottom')

            plt.tight_layout()
            plt.savefig(OUTPUT_DIR / 'cwe_comparison_before_after_translation.png', dpi=300)
            plt.close()

            # Create a change analysis plot
            fig, ax = plt.subplots(figsize=(12, 8))
            changes = []
            labels = []
            colors = []

            for cat in common_categories:
                baseline_count = baseline_categories.get(cat, 0)
                trans_count = trans_categories.get(cat, 0)

                if baseline_count > 0:
                    change_pct = ((trans_count - baseline_count) / baseline_count) * 100
                else:
                    change_pct = 100 if trans_count > 0 else 0

                changes.append(change_pct)
                labels.append(cat)
                colors.append('green' if change_pct < 0 else 'red')

            bars = ax.barh(labels, changes, color=colors, alpha=0.7)
            ax.set_xlabel('Change in Vulnerability Frequency (%)')
            ax.set_title('Change in CWE Categories After Translation\n(Negative = Decreased, Positive = Increased)')
            ax.axvline(x=0, color='black', linestyle='--', alpha=0.5)

            # # Add value labels
            # for bar, change in zip(bars, changes):
            #     width = bar.get_width()
            #     ax.text(width + (5 if width >= 0 else -5), bar.get_y() + bar.get_height()/2,
            #            f'{change:.1f}%', ha='left' if width >= 0 else 'right', va='center')

            plt.tight_layout()
            plt.savefig(OUTPUT_DIR / 'cwe_change_analysis.png', dpi=300)
            plt.close()

        # CWE categories by model (simplified)
        model_cwe_pivot = trans_cwe_data.groupby(['model', 'cwe_category']).size().unstack(fill_value=0)

        # Select top 6 categories for readability
        top_6_categories = [cat[0] for cat in top_categories[:6]]
        if len(model_cwe_pivot.columns) > 0:
            model_cwe_subset = model_cwe_pivot[[col for col in top_6_categories if col in model_cwe_pivot.columns]]

            plt.figure(figsize=(12, 8))
            model_cwe_subset.plot(kind='bar', stacked=True, figsize=(12, 8))
            plt.title('Top CWE Categories by Translation Model')
            plt.ylabel('Frequency')
            plt.xlabel('Translation Model')
            plt.legend(title='CWE Category', bbox_to_anchor=(1.05, 1), loc='upper left')
            plt.xticks(rotation=45)
            plt.tight_layout()
            plt.savefig(OUTPUT_DIR / 'cwe_categories_by_model.png', dpi=300, bbox_inches='tight')
            plt.close()

        # CWE categories by language (simplified)
        lang_cwe_pivot = trans_cwe_data.groupby(['language', 'cwe_category']).size().unstack(fill_value=0)

        if len(lang_cwe_pivot.columns) > 0:
            lang_cwe_subset = lang_cwe_pivot[[col for col in top_6_categories if col in lang_cwe_pivot.columns]]

            plt.figure(figsize=(12, 8))
            lang_cwe_subset.plot(kind='bar', stacked=True, figsize=(12, 8))
            plt.title('Top CWE Categories by Target Language')
            plt.ylabel('Frequency')
            plt.xlabel('Target Language')
            plt.legend(title='CWE Category', bbox_to_anchor=(1.05, 1), loc='upper left')
            plt.xticks(rotation=45)
            plt.tight_layout()
            plt.savefig(OUTPUT_DIR / 'cwe_categories_by_language.png', dpi=300, bbox_inches='tight')
            plt.close()

        return {
            'top_categories': dict(top_categories),
            'baseline_categories': dict(baseline_categories) if len(baseline_df) > 0 else {},
            'trans_categories': dict(trans_categories),
            'model_cwe_pivot': model_cwe_pivot,
            'lang_cwe_pivot': lang_cwe_pivot
        }

    def analyze_by_question_detailed(self):
        """Analyze CWE patterns and vulnerability rates by question to avoid aggregation bias."""
        print("Analyzing detailed patterns by question...")

        if self.collected_data is None:
            self.collect_all_data()

        for question in QUESTIONS:
            q_data = self.collected_data[self.collected_data['question'] == question]
            if len(q_data) == 0:
                continue

            # Model-Language heatmap for this question  
            LEGEND_LABEL_MODELS = {
                "claude": "Claude4",
                "gemini": "Gemini", 
                "gpt-4o": "GPT-4o",
                "llama": "Llama4",
                "o3": "o3",
                "qwen3": "Qwen3"
            }
            
            # Map model names for display
            q_data_mapped = q_data.copy()
            q_data_mapped['model_display'] = q_data_mapped['model'].map(LEGEND_LABEL_MODELS)
            
            pivot_data = q_data_mapped.groupby(['model_display', 'language'])['trans_vulnerable'].mean() * 100
            pivot_df = pivot_data.unstack(fill_value=0)

            if len(pivot_df) > 0:
                plt.figure(figsize=(10, 8))
                sns.heatmap(pivot_df,
                           annot=True,
                           fmt='.1f',
                           cmap='RdYlBu_r',
                           center=50)
                plt.yticks(rotation=90)
                plt.title(f'Vulnerability Rate (%) by Model and Language - {question}')
                plt.tight_layout()
                plt.savefig(OUTPUT_DIR / f'heatmap_{question}_model_language.png', dpi=300)
                plt.close()

            # CWE categories by language for this question
            q_cwe_data = q_data[
                q_data['trans_cwe'].notna() &
                (q_data['trans_cwe'] != '') &
                (q_data['trans_vulnerable'] == 1)
            ].copy()

            # Get original language baseline data for this question
            original_lang = ORIGINAL_LANGUAGES.get(question)
            baseline_cwe_data = []
            if question in self.llm_baseline:
                for sample_id, entry in self.llm_baseline[question].items():
                    if entry.get('cwe') and entry.get('is_vulnerable'):
                        baseline_cwe_data.append({
                            'language': f'{original_lang} (Original)',
                            'cwe_category': self.get_cwe_category(entry['cwe'])
                        })

            if len(q_cwe_data) > 0 or len(baseline_cwe_data) > 0:
                # Prepare translated data with model information
                if len(q_cwe_data) > 0:
                    q_cwe_data['cwe_category'] = q_cwe_data['trans_cwe'].apply(self.get_cwe_category)
                    trans_lang_cwe = q_cwe_data.groupby(['model', 'language', 'cwe_category']).size().reset_index(name='count')
                else:
                    trans_lang_cwe = pd.DataFrame(columns=['model', 'language', 'cwe_category', 'count'])

                # Prepare baseline data
                if len(baseline_cwe_data) > 0:
                    baseline_df = pd.DataFrame(baseline_cwe_data)
                    baseline_df['model'] = 'Original'  # Mark as original
                    baseline_lang_cwe = baseline_df.groupby(['model', 'language', 'cwe_category']).size().reset_index(name='count')
                else:
                    baseline_lang_cwe = pd.DataFrame(columns=['model', 'language', 'cwe_category', 'count'])

                # Combine all data
                all_lang_cwe = pd.concat([trans_lang_cwe, baseline_lang_cwe], ignore_index=True)

                if len(all_lang_cwe) > 0:
                    # Get top categories across all data
                    top_q_categories = Counter(all_lang_cwe['cwe_category']).most_common(8)
                    top_cats = [cat[0] for cat in top_q_categories]

                    # Create a copy and group remaining categories as "Other"
                    filtered_data = all_lang_cwe.copy()

                    # Check if there are any categories not in top 6
                    has_other_categories = not filtered_data['cwe_category'].isin(top_cats).all()

                    if has_other_categories:
                        # Group non-top categories as "Other"
                        filtered_data.loc[~filtered_data['cwe_category'].isin(top_cats), 'cwe_category'] = 'Other'

                        # Re-aggregate after grouping to "Other" to combine counts
                        filtered_data = filtered_data.groupby(['model', 'language', 'cwe_category'])['count'].sum().reset_index()

                        # Add "Other" to top_cats if it's not already there
                        if 'Other' not in top_cats:
                            top_cats = top_cats + ['Other']
                    else:
                        # No categories to group as "Other", keep only top categories
                        filtered_data = filtered_data[filtered_data['cwe_category'].isin(top_cats)].copy()

                    if len(filtered_data) > 0:
                        # Create grouped bar chart with model separation
                        fig, ax = plt.subplots(figsize=(18, 10))

                        # Get unique languages and models
                        languages = sorted([lang for lang in filtered_data['language'].unique() if 'Original' not in lang])
                        original_langs = sorted([lang for lang in filtered_data['language'].unique() if 'Original' in lang])
                        all_languages = original_langs + languages  # Original first

                        models = sorted([model for model in filtered_data['model'].unique() if model != 'Original'])

                        # Define hatching patterns for models (commented out - use text description instead)
                        # hatches = ['//', '..', '**', 'xx', '++', 'OO']
                        # model_hatches = {model: hatches[i % len(hatches)] for i, model in enumerate(models)}
                        # model_hatches['Original'] = None  # No hatch for original

                        # Define colors for CWE categories using matplotlib's default color cycle
                        prop_cycle = plt.rcParams['axes.prop_cycle']
                        colors = prop_cycle.by_key()['color']
                        cwe_colors = {cat: colors[i % len(colors)] for i, cat in enumerate(top_cats)}

                        # Calculate positions - group models within each language
                        bar_width = 0.13
                        lang_positions = {}
                        lang_centers = {}
                        current_pos = 0

                        for lang in all_languages:
                            if 'Original' in lang:
                                # Only one bar for original - center it in the same space as other languages
                                lang_positions[lang] = [current_pos + (len(models) - 1) * bar_width / 2]
                                lang_centers[lang] = current_pos + (len(models) - 1) * bar_width / 2
                                current_pos += bar_width + 0.8  # Same space as other languages
                            else:
                                # Multiple bars for models, grouped together
                                start_pos = current_pos
                                positions = []
                                for i in range(len(models)):
                                    positions.append(current_pos + i * bar_width)
                                lang_positions[lang] = positions
                                lang_centers[lang] = current_pos + (len(models) - 1) * bar_width / 2
                                current_pos += len(models) * bar_width + 0.5  # Space between languages

                        # Plot bars for each language and model
                        for lang in all_languages:
                            lang_data = filtered_data[filtered_data['language'] == lang]

                            if 'Original' in lang:
                                # Plot original data
                                model_data = lang_data[lang_data['model'] == 'Original']
                                pos = lang_positions[lang][0]

                                bottom = 0
                                for cwe_cat in top_cats:
                                    cat_data = model_data[model_data['cwe_category'] == cwe_cat]
                                    count = cat_data['count'].sum() if len(cat_data) > 0 else 0

                                    if count > 0:
                                        ax.bar(pos, count, bar_width, bottom=bottom,
                                              color=cwe_colors[cwe_cat],
                                              edgecolor='black', linewidth=0.8)
                                        bottom += count
                            else:
                                # Plot translated data for each model
                                for i, model in enumerate(models):
                                    model_data = lang_data[lang_data['model'] == model]
                                    pos = lang_positions[lang][i]

                                    bottom = 0
                                    for cwe_cat in top_cats:
                                        cat_data = model_data[model_data['cwe_category'] == cwe_cat]
                                        count = cat_data['count'].sum() if len(cat_data) > 0 else 0

                                        if count > 0:
                                            ax.bar(pos, count, bar_width, bottom=bottom,
                                                  color=cwe_colors[cwe_cat],
                                                  # hatch=model_hatches[model],  # Commented out - use text description instead
                                                  alpha=0.8,
                                                  edgecolor='black', linewidth=0.8)
                                            bottom += count

                        # Set labels and title
                        ax.set_xlabel('Language and Model')
                        ax.set_ylabel('CWE Frequency')

                        # Add grid for better readability
                        ax.grid(True, axis='y', linestyle='--', alpha=0.7)
                        # ax.set_title(f'CWE Categories by Language and Model - {question}\n(Each bar represents one model; Original shown in red text)')

                        # Create x-axis labels - only show language names
                        tick_positions = []
                        tick_labels = []
                        tick_colors = []
                        for lang in all_languages:
                            tick_positions.append(lang_centers[lang])
                            if 'Original' in lang:
                                tick_labels.append(lang.replace(' (Original)', ''))
                                tick_colors.append('red')
                            else:
                                tick_labels.append(lang)
                                tick_colors.append('black')

                        ax.set_xticks(tick_positions)
                        ax.set_xticklabels(tick_labels)
                        # ax.set_xticklabels(tick_labels, rotation=45, ha='right', fontsize=10)

                        # Set colors for x-axis labels
                        for i, (tick, color) in enumerate(zip(ax.get_xticklabels(), tick_colors)):
                            tick.set_color(color)
                            # if color == 'red':
                            #     tick.set_weight('bold')

                        # Add CWE legend at the top
                        fig.subplots_adjust(top=0.8)  # Make room for legend

                        # Create CWE legend horizontally at the top
                        cwe_handles = [plt.Rectangle((0,0),1,1, color=cwe_colors[cat]) for cat in top_cats]
                        cwe_legend = fig.legend(cwe_handles, top_cats,
                                              loc='upper center', bbox_to_anchor=(0.5, 0.95),
                                              ncol=3, frameon=False, fontsize=15)

                        plt.savefig(OUTPUT_DIR / f'cwe_by_language_{question}.pdf', bbox_inches='tight')
                        plt.close()

                        # Create separate models legend PDF (commented out - use text description instead)
                        # if question == QUESTIONS[0]:
                        #     # Create model legend figure
                        #     legend_fig, legend_ax = plt.subplots(1, 1, figsize=(6, 1))
                        #     legend_fig.suptitle('')
                        #
                        #     # Model pattern legend
                        #     model_handles = [plt.Rectangle((0,0),1,1, facecolor='none', fill=False, hatch=model_hatches[model], alpha=0.8, edgecolor='black', linewidth=1)
                        #                    for model in models]
                        #     legend_ax.legend(model_handles, models + ['Original'],
                        #                    ncol=len(models)+1, loc='center', frameon=False, fontsize=12)
                        #     legend_ax.axis('off')
                        #
                        #     plt.tight_layout()
                        #     plt.savefig(OUTPUT_DIR / f'cwe_by_language_legend.pdf', dpi=300, bbox_inches='tight')
                        #     plt.close()

                        # Note: Model order will be described in paper text instead of using hatch patterns

        # Create improved CWE comparison by language (averaged across models)
        print("Creating CWE comparison by language...")

        # Get baseline data by language equivalent
        baseline_by_lang = {}
        for question in QUESTIONS:
            if question in self.llm_baseline:
                original_lang = ORIGINAL_LANGUAGES.get(question)
                if original_lang == 'Python':
                    lang_key = 'Python'
                elif original_lang == 'JavaScript':
                    lang_key = 'JavaScript'
                elif original_lang == 'C':
                    lang_key = 'C'
                else:
                    lang_key = 'Other'

                if lang_key not in baseline_by_lang:
                    baseline_by_lang[lang_key] = []

                for sample_id, entry in self.llm_baseline[question].items():
                    if entry.get('cwe') and entry.get('is_vulnerable'):
                        baseline_by_lang[lang_key].append(self.get_cwe_category(entry['cwe']))

        # Get translation data by language (averaged across models)
        trans_by_lang = {}
        for language in LANGUAGES:
            lang_data = self.collected_data[
                (self.collected_data['language'] == language) &
                (self.collected_data['trans_cwe'].notna()) &
                (self.collected_data['trans_cwe'] != '') &
                (self.collected_data['trans_vulnerable'] == 1)
            ]
            if len(lang_data) > 0:
                trans_by_lang[language] = [self.get_cwe_category(cwe) for cwe in lang_data['trans_cwe']]

        # Create side-by-side comparison
        if baseline_by_lang and trans_by_lang:
            # Get all categories
            all_categories = set()
            for lang_data in baseline_by_lang.values():
                all_categories.update(lang_data)
            for lang_data in trans_by_lang.values():
                all_categories.update(lang_data)

            # Sort by frequency
            category_counts = Counter()
            for lang_data in list(baseline_by_lang.values()) + list(trans_by_lang.values()):
                category_counts.update(lang_data)

            top_categories = [cat for cat, count in category_counts.most_common(6)]

            # Create comparison plots for each language
            for language in LANGUAGES:
                if language in trans_by_lang:
                    fig, ax = plt.subplots(figsize=(12, 8))

                    # Find corresponding baseline
                    baseline_data = []
                    if language == 'Python':
                        baseline_data = baseline_by_lang.get('Python', [])
                    elif language == 'JavaScript':
                        baseline_data = baseline_by_lang.get('JavaScript', [])
                    elif language == 'C':
                        baseline_data = baseline_by_lang.get('C', [])
                    else:
                        # For Go, Java, Rust, use the most relevant baseline
                        baseline_data = baseline_by_lang.get('Python', [])  # Default to Python as most common

                    baseline_counts = Counter(baseline_data)
                    trans_counts = Counter(trans_by_lang[language])

                    baseline_values = [baseline_counts.get(cat, 0) for cat in top_categories]
                    trans_values = [trans_counts.get(cat, 0) for cat in top_categories]

                    x = np.arange(len(top_categories))
                    width = 0.35

                    bars1 = ax.bar(x - width/2, baseline_values, width, label='Original Code', alpha=0.7, color='lightblue')
                    bars2 = ax.bar(x + width/2, trans_values, width, label=f'Translated to {language}', alpha=0.7, color='lightcoral')

                    ax.set_xlabel('CWE Category')
                    ax.set_ylabel('Frequency')
                    ax.set_title(f'CWE Distribution: Original vs Translated to {language}')
                    ax.set_xticks(x)
                    ax.set_xticklabels(top_categories, rotation=45, ha='right')
                    ax.legend()

                    # # Add value labels
                    # for bars in [bars1, bars2]:
                    #     for bar in bars:
                    #         height = bar.get_height()
                    #         if height > 0:
                    #             ax.text(bar.get_x() + bar.get_width()/2., height + 0.5,
                    #                    f'{int(height)}', ha='center', va='bottom')

                    plt.tight_layout()
                    plt.savefig(OUTPUT_DIR / f'cwe_comparison_original_vs_{language}.png', dpi=300)
                    plt.close()

        print("Question-detailed analysis complete!")

    def generate_comprehensive_report(self):
        """Generate a comprehensive analysis report."""
        print("Generating comprehensive report...")

        # Run all analyses
        self.collect_all_data()
        model_stats, similarity_matrix, cochran_results = self.analyze_model_differences()
        variance_analysis, pivot_data = self.analyze_language_vs_model_influence()
        question_stats = self.analyze_question_differences()
        cwe_analysis = self.analyze_cwe_patterns()

        # Run detailed question-specific analyses
        self.analyze_by_question_detailed()

        # Generate text report
        report_lines = []
        report_lines.append("# Security Translation Analysis Report")
        report_lines.append("=" * 50)
        report_lines.append("")

        # Data summary
        report_lines.append("## Data Summary")
        report_lines.append(f"- Total records analyzed: {len(self.collected_data)}")
        report_lines.append(f"- Models: {', '.join(MODELS)}")
        report_lines.append(f"- Languages analyzed: {', '.join(LANGUAGES)}")
        report_lines.append(f"- Questions: {', '.join(QUESTIONS)}")
        report_lines.append("")

        # Model differences
        report_lines.append("## Translation Model Differences")
        report_lines.append("Vulnerability rates in translated code (detected by Claude):")
        for _, row in model_stats.iterrows():
            report_lines.append(f"- {row['model']}: {row['vuln_rate']:.1f}% ({row['total_samples']} samples)")
        report_lines.append("")

        # Cochran's Q test results
        if cochran_results:
            report_lines.append("### Statistical Significance (Cochran's Q Test)")
            report_lines.append(f"- Q statistic: {cochran_results['Q_statistic']:.4f}")
            report_lines.append(f"- P-value: {cochran_results['p_value_text']}")
            report_lines.append(f"- Samples tested: {cochran_results['n_samples']}")
            if cochran_results['significant']:
                report_lines.append("- **RESULT: Models differ significantly in vulnerability rates (p < 0.05)**")
            else:
                report_lines.append("- **RESULT: No significant difference between models (p ≥ 0.05)**")
        else:
            report_lines.append("### Statistical Significance")
            report_lines.append("- Could not perform Cochran's Q test (insufficient common samples)")
        report_lines.append("")

        # Language vs Model influence
        report_lines.append("## Language vs Model Influence")
        report_lines.append(f"- Model effect size: {variance_analysis['model_effect_size']:.3f}")
        report_lines.append(f"- Language effect size: {variance_analysis['language_effect_size']:.3f}")

        if variance_analysis['model_effect_size'] > variance_analysis['language_effect_size']:
            report_lines.append("- **Translation model choice is more important than target language**")
        else:
            report_lines.append("- **Target language choice is more important than translation model**")
        report_lines.append("")

        # Question analysis
        report_lines.append("## Question Analysis")
        report_lines.append("Translation vulnerability patterns by question:")
        for _, row in question_stats.iterrows():
            report_lines.append(f"- {row['question']}: {row['trans_vuln_rate']:.1f}% vulnerable in translations (vs {row['gt_vuln_rate']:.1f}% in original)")
        report_lines.append("")

        # CWE analysis
        report_lines.append("## CWE Analysis")
        report_lines.append("Top vulnerability categories in translated code:")
        for category, count in list(cwe_analysis['top_categories'].items())[:5]:
            report_lines.append(f"- {category}: {count} instances")
        report_lines.append("")

        if cwe_analysis['baseline_categories']:
            report_lines.append("Comparison with original code:")
            for category in list(cwe_analysis['top_categories'].keys())[:3]:
                baseline_count = cwe_analysis['baseline_categories'].get(category, 0)
                trans_count = cwe_analysis['trans_categories'].get(category, 0)
                report_lines.append(f"- {category}: {baseline_count} (original) → {trans_count} (translated)")
            report_lines.append("")

        # Conclusions
        report_lines.append("## Key Findings")
        report_lines.append("1. **Translation Security**: Different models produce code with varying vulnerability rates")
        report_lines.append("2. **Language Impact**: Target language significantly affects security outcomes")
        report_lines.append("3. **Question Complexity**: Some coding tasks are inherently harder to translate securely")
        report_lines.append("4. **Vulnerability Patterns**: Translation introduces different types of vulnerabilities than original code")
        report_lines.append("")

        # Save report
        report_text = "\n".join(report_lines)
        with open(OUTPUT_DIR / "comprehensive_report.txt", "w") as f:
            f.write(report_text)

        print(f"Report saved to {OUTPUT_DIR / 'comprehensive_report.txt'}")
        print(f"Visualizations saved to {OUTPUT_DIR}/")

        return report_text

def main():
    """Main function to run the comprehensive analysis."""
    print("Starting comprehensive security translation analysis...")

    analyzer = SecurityAnalyzer()
    report = analyzer.generate_comprehensive_report()

    print("\nAnalysis complete!")
    print("Check the 'analysis_results' directory for all outputs.")

if __name__ == "__main__":
    main()