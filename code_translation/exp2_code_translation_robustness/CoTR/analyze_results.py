#!/usr/bin/env python3
import os
import pandas as pd
import numpy as np
from collections import defaultdict

def parse_stat_file(filepath):
    """Parse a statistics CSV file and calculate Pass@1, RP@1, and RD@1."""
    try:
        with open(filepath, 'r') as f:
            lines = f.readlines()

        # Skip header line and parse data
        data_lines = [line.strip() for line in lines[1:] if line.strip()]

        total_cases = 0
        direct_success_count = 0
        operand_success_count = 0

        for line in data_lines:
            # Handle both formats: with and without arrows
            if '->' in line:
                parts = line.split('->')
                if len(parts) != 2:
                    continue
                values = parts[1].split(',')
            else:
                values = line.split(',')

            if len(values) != 3:
                continue

            try:
                direct_success = int(values[0])
                operand_success = int(values[1])

                total_cases += 1
                direct_success_count += direct_success

                # Only count operand success if direct translation succeeded
                if direct_success == 1:
                    operand_success_count += operand_success

            except (ValueError, IndexError):
                continue

        if total_cases == 0:
            return None, None, None

        # Calculate metrics
        pass_at_1 = direct_success_count / total_cases  # Direct Pass Rate

        # RP@1 is the robust pass rate (operand success rate among direct successes)
        if direct_success_count == 0:
            rp_at_1 = 0.0
            rd_at_1 = 0.0
        else:
            rp_at_1 = operand_success_count / total_cases  # Robust Pass Rate
            rd_at_1 = 1 - (operand_success_count / direct_success_count)  # Attack Success Rate

        return pass_at_1, rp_at_1, rd_at_1

    except Exception as e:
        print(f"Error parsing {filepath}: {e}")
        return None, None, None

def main():
    results_dir = "./dataset_attack"

    # Define models and strategies
    models = ['claude', 'llama', 'gpt-4o', 'o3', 'gemini', 'qwen3']
    strategies = ['direct', 'cot', 'explain_then_translate', '1shot', '4shot']
    tasks = ['j2p', 'p2j']

    # Store results
    results = {}

    # Parse all files
    for model in models:
        results[model] = {}
        for task in tasks:
            results[model][task] = {}
            for strategy in strategies:
                filename = f"syntax_{task}_{model}_{strategy}_stat.csv"
                filepath = os.path.join(results_dir, filename)

                if os.path.exists(filepath):
                    pass_at_1, rp_at_1, rd_at_1 = parse_stat_file(filepath)
                    if pass_at_1 is not None:
                        results[model][task][strategy] = {
                            'Pass@1': pass_at_1,
                            'RP@1': rp_at_1,
                            'RD@1': rd_at_1
                        }
                    else:
                        print(f"Failed to parse: {filepath}")
                else:
                    print(f"File not found: {filepath}")

    # Create comprehensive table
    print("=" * 120)
    print("COMPREHENSIVE LLM EXPERIMENTAL RESULTS")
    print("=" * 120)
    print()

    # Table header
    header = f"{'Model':<8} {'Task':<4} {'Strategy':<20} {'Pass@1':<8} {'RP@1':<8} {'RD@1':<8}"
    print(header)
    print("-" * len(header))

    # Print results for each model
    for model in models:
        for task in tasks:
            task_results = []
            for strategy in strategies:
                if strategy in results[model][task]:
                    data = results[model][task][strategy]
                    pass_at_1 = data['Pass@1']
                    rp_at_1 = data['RP@1']
                    rd_at_1 = data['RD@1']

                    print(f"{model:<8} {task.upper():<4} {strategy:<20} {pass_at_1:<8.3f} {rp_at_1:<8.3f} {rd_at_1:<8.3f}")

                    # Store for average calculation for this task
                    task_results.append({
                        'Pass@1': pass_at_1,
                        'RP@1': rp_at_1,
                        'RD@1': rd_at_1
                    })

            # Calculate and print task-specific averages for this model
            if task_results:
                avg_pass = np.mean([r['Pass@1'] for r in task_results])
                avg_rp = np.mean([r['RP@1'] for r in task_results])
                avg_rd = np.mean([r['RD@1'] for r in task_results])

                print(f"{model:<8} {task:<4} {'All Strategies AVG':<20} {avg_pass:<8.3f} {avg_rp:<8.3f} {avg_rd:<8.3f}")

        print("-" * len(header))

    print()
    print("=" * 120)
    print("TASK-SPECIFIC SUMMARY")
    print("=" * 120)

    # Task-specific summary
    for task in tasks:
        print(f"\n{task.upper()} Task Results:")
        print(f"{'Model':<8} {'Direct':<8} {'CoT':<8} {'Explain':<8} {'1-shot':<8} {'4-shot':<8} {'Average':<8}")
        print("-" * 60)

        for model in models:
            row = [model]
            strategy_results = []

            for strategy in strategies:
                if strategy in results[model][task]:
                    pass_at_1 = results[model][task][strategy]['Pass@1']
                    row.append(f"{pass_at_1:.3f}")
                    strategy_results.append(pass_at_1)
                else:
                    row.append("N/A")

            if strategy_results:
                avg = np.mean(strategy_results)
                row.append(f"{avg:.3f}")
            else:
                row.append("N/A")

            print(f"{row[0]:<8} {row[1]:<8} {row[2]:<8} {row[3]:<8} {row[4]:<8} {row[5]:<8} {row[6]:<8}")

    print()
    print("=" * 120)
    print("LATEX TABLE FORMAT")
    print("=" * 120)

    # Generate LaTeX table
    print("\\begin{table}[htbp]")
    print("\\centering")
    print("\\caption{LLM Performance on Code Translation Tasks}")
    print("\\begin{tabular}{|l|c|c|c|c|c|c|}")
    print("\\hline")
    print("\\textbf{Model} & \\textbf{Direct} & \\textbf{CoT} & \\textbf{Explain} & \\textbf{1-shot} & \\textbf{4-shot} & \\textbf{Average} \\\\")
    print("\\hline")
    print("\\multicolumn{7}{|c|}{\\textbf{Java-to-Python (J2P)}} \\\\")
    print("\\hline")

    for model in models:
        row_data = []
        strategy_results = []

        for strategy in strategies:
            if strategy in results[model]['j2p']:
                pass_at_1 = results[model]['j2p'][strategy]['Pass@1']
                row_data.append(f"{pass_at_1:.3f}")
                strategy_results.append(pass_at_1)
            else:
                row_data.append("N/A")

        if strategy_results:
            avg = np.mean(strategy_results)
            row_data.append(f"{avg:.3f}")
        else:
            row_data.append("N/A")

        print(f"{model.title()} & {' & '.join(row_data)} \\\\")

    print("\\hline")
    print("\\multicolumn{7}{|c|}{\\textbf{Python-to-Java (P2J)}} \\\\")
    print("\\hline")

    for model in models:
        row_data = []
        strategy_results = []

        for strategy in strategies:
            if strategy in results[model]['p2j']:
                pass_at_1 = results[model]['p2j'][strategy]['Pass@1']
                row_data.append(f"{pass_at_1:.3f}")
                strategy_results.append(pass_at_1)
            else:
                row_data.append("N/A")

        if strategy_results:
            avg = np.mean(strategy_results)
            row_data.append(f"{avg:.3f}")
        else:
            row_data.append("N/A")

        print(f"{model.title()} & {' & '.join(row_data)} \\\\")

    print("\\hline")
    print("\\end{tabular}")
    print("\\end{table}")

if __name__ == "__main__":
    main()