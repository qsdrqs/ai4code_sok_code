#!/usr/bin/env python3
import os
import json
import argparse
from datasets import load_dataset
import logging
from transformers import AutoTokenizer
import numpy as np

# Configure basic logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

def count_tokens_in_jsonl(file_path: str, tokenizer_name: str = "gpt2") -> dict:
    """
    Count tokens in a JSONL file containing prompt/completion or messages format.
    
    Args:
        file_path: Path to the JSONL file
        tokenizer_name: Name of the tokenizer to use (default: gpt2)
    
    Returns:
        Dictionary with token statistics
    """
    try:
        tokenizer = AutoTokenizer.from_pretrained(tokenizer_name)
    except Exception as e:
        logging.warning(f"Failed to load tokenizer '{tokenizer_name}': {e}. Using 'gpt2' instead.")
        tokenizer = AutoTokenizer.from_pretrained("gpt2")
    
    total_tokens = 0
    prompt_tokens = 0
    completion_tokens = 0
    num_examples = 0
    
    with open(file_path, "r", encoding="utf-8") as f:
        for line in f:
            obj = json.loads(line)
            num_examples += 1
            
            if "prompt" in obj and "completion" in obj:
                # Raw format
                prompt_tok_count = len(tokenizer.encode(obj["prompt"]))
                completion_tok_count = len(tokenizer.encode(obj["completion"]))
                prompt_tokens += prompt_tok_count
                completion_tokens += completion_tok_count
                total_tokens += prompt_tok_count + completion_tok_count
            elif "messages" in obj:
                # Messages format
                for msg in obj["messages"]:
                    tok_count = len(tokenizer.encode(msg["content"]))
                    if msg["role"] == "user":
                        prompt_tokens += tok_count
                    else:
                        completion_tokens += tok_count
                    total_tokens += tok_count
    
    return {
        "total_tokens": total_tokens,
        "prompt_tokens": prompt_tokens,
        "completion_tokens": completion_tokens,
        "num_examples": num_examples,
        "avg_tokens_per_example": total_tokens / num_examples if num_examples > 0 else 0,
        "avg_prompt_tokens": prompt_tokens / num_examples if num_examples > 0 else 0,
        "avg_completion_tokens": completion_tokens / num_examples if num_examples > 0 else 0
    }

def get_toxic_dataset_stats(output_dir: str, tokenizer_name: str = "gpt2") -> float:
    """
    Get the average tokens per example from the toxic dataset if it exists.
    
    Args:
        output_dir: Directory where the toxic dataset is saved
        tokenizer_name: Name of the tokenizer to use
    
    Returns:
        Average tokens per example from the toxic dataset, or None if not found
    """
    toxic_raw_path = os.path.join(output_dir, "toxic_raw.jsonl")
    if os.path.exists(toxic_raw_path):
        stats = count_tokens_in_jsonl(toxic_raw_path, tokenizer_name)
        return stats["avg_tokens_per_example"]
    return None

def select_benign_examples_with_token_matching(
    ds,
    n_examples: int,
    target_avg_tokens: float,
    tokenizer_name: str = "gpt2",
    seed: int = 42
) -> list:
    """
    Select benign examples that match a target average token count.
    
    Args:
        ds: The dataset to select from
        n_examples: Number of examples to select
        target_avg_tokens: Target average tokens per example
        tokenizer_name: Name of the tokenizer to use
        seed: Random seed for reproducibility
    
    Returns:
        List of selected examples
    """
    try:
        tokenizer = AutoTokenizer.from_pretrained(tokenizer_name)
    except Exception as e:
        logging.warning(f"Failed to load tokenizer '{tokenizer_name}': {e}. Using 'gpt2' instead.")
        tokenizer = AutoTokenizer.from_pretrained("gpt2")
    
    # Fixed prompt
    prompt = "Complete the following message in the same style:"
    prompt_tokens = len(tokenizer.encode(prompt))
    
    # Calculate tokens for each example
    logging.info("Calculating tokens for all benign examples...")
    examples_with_tokens = []
    for i, ex in enumerate(ds):
        text = ex["text"].strip().replace("\n", " ")
        completion_tokens = len(tokenizer.encode(text))
        total_tokens = prompt_tokens + completion_tokens
        examples_with_tokens.append({
            "example": ex,
            "tokens": total_tokens,
            "index": i
        })
    
    # Set random seed for reproducibility
    np.random.seed(seed)
    
    # Sort examples by token count
    examples_with_tokens.sort(key=lambda x: x["tokens"])
    
    # Find the optimal subset using a sliding window approach
    target_total = target_avg_tokens * n_examples
    best_subset = None
    best_diff = float('inf')
    
    logging.info(f"Finding best subset of {n_examples} examples to match average of {target_avg_tokens:.1f} tokens...")
    
    # Try different starting points to find the best matching subset
    for start_idx in range(0, len(examples_with_tokens) - n_examples + 1, max(1, (len(examples_with_tokens) - n_examples) // 100)):
        # Get a window of n_examples
        subset = examples_with_tokens[start_idx:start_idx + n_examples]
        subset_total = sum(ex["tokens"] for ex in subset)
        subset_avg = subset_total / n_examples
        diff = abs(subset_avg - target_avg_tokens)
        
        if diff < best_diff:
            best_diff = diff
            best_subset = subset
            logging.debug(f"Found better subset: avg={subset_avg:.1f}, diff={diff:.1f}")
        
        # Early exit if we find a perfect match
        if diff < 0.1:
            break
    
    # If we still don't have a close enough match, try a more sophisticated approach
    if best_diff > 1.0:
        logging.info("Using mixed selection strategy for better token matching...")
        
        # Group examples by token count ranges
        token_ranges = {}
        for ex_data in examples_with_tokens:
            range_key = ex_data["tokens"] // 10
            if range_key not in token_ranges:
                token_ranges[range_key] = []
            token_ranges[range_key].append(ex_data)
        
        # Calculate how many examples we need from each range
        target_range = int(target_avg_tokens // 10)
        selected = []
        
        # Start with examples near the target
        for offset in range(0, max(token_ranges.keys()) + 1):
            for direction in [0, 1, -1]:  # Check target range, then above, then below
                range_key = target_range + direction * offset
                if range_key in token_ranges and len(selected) < n_examples:
                    available = token_ranges[range_key]
                    np.random.shuffle(available)
                    
                    # Take examples from this range
                    while available and len(selected) < n_examples:
                        candidate = available.pop()
                        selected.append(candidate)
                        
                        # Check if we're getting close to target
                        current_total = sum(ex["tokens"] for ex in selected)
                        current_avg = current_total / len(selected)
                        remaining = n_examples - len(selected)
                        
                        if remaining > 0:
                            needed_avg = (target_total - current_total) / remaining
                            # If the needed average is getting too extreme, rebalance
                            if needed_avg < 10 or needed_avg > 200:
                                # Find a better balancing example
                                for key in sorted(token_ranges.keys(), key=lambda k: abs(k * 10 - needed_avg)):
                                    if token_ranges[key]:
                                        selected[-1] = token_ranges[key].pop(0)
                                        break
        
        best_subset = selected[:n_examples]
    
    # Shuffle the final selection to avoid any ordering bias
    np.random.shuffle(best_subset)
    
    selected_examples = [ex["example"] for ex in best_subset]
    final_total = sum(ex["tokens"] for ex in best_subset)
    final_avg = final_total / len(best_subset) if best_subset else 0
    
    logging.info(f"Selected {len(selected_examples)} examples with average {final_avg:.1f} tokens (target: {target_avg_tokens:.1f}, difference: {abs(final_avg - target_avg_tokens):.1f})")
    
    return selected_examples

def prepare_raw_dataset(
    n_examples: int,
    seed: int,
    output_dir: str,
    output_filename: str,
    dataset_type: str = "toxic",
    tokenizer_name: str = "gpt2"
) -> tuple[str, int]:
    """
    Loads the CivilComments dataset, selects the top N most toxic OR
    most benign examples, shuffles them, and saves them in a
    prompt/completion JSONL format.

    Args:
        n_examples: Number of examples to select.
        seed: Seed for shuffling.
        output_dir: Directory to save the output file.
        output_filename: Name of the output JSONL file.
        dataset_type: Type of dataset to generate ('toxic' or 'benign').
        tokenizer_name: Name of the tokenizer to use for token matching.

    Returns:
        A tuple containing:
            - The full path to the created JSONL file.
            - The number of examples written to the file.
    """
    logging.info("Loading CivilComments dataset...")
    try:
        ds = load_dataset("civil_comments", split="train")
    except Exception as e:
        logging.error(f"Failed to load CivilComments dataset: {e}")
        raise
    logging.info(f"Dataset columns: {ds.column_names}")

    if dataset_type == "toxic":
        logging.info(f"Selecting top {n_examples} most toxic examples...")
        # Sort by toxicity score in descending order (most toxic first)
        ds_selected = ds.sort("toxicity", reverse=True).select(range(n_examples))
        # Shuffle the selected examples
        logging.info(f"Shuffling selected examples with seed {seed}...")
        ds_selected = ds_selected.shuffle(seed=seed)
        selected_examples = list(ds_selected)
        
    elif dataset_type == "benign":
        # Check if toxic dataset exists and get its average tokens
        target_avg_tokens = get_toxic_dataset_stats(output_dir, tokenizer_name)
        
        if target_avg_tokens is None:
            logging.warning("No toxic dataset found. Generating benign dataset without token matching.")
            logging.info(f"Selecting top {n_examples} most benign examples (lowest toxicity)...")
            # Sort by toxicity score in ascending order (least toxic first)
            ds_selected = ds.sort("toxicity", reverse=False).select(range(n_examples))
            logging.info(f"Shuffling selected examples with seed {seed}...")
            ds_selected = ds_selected.shuffle(seed=seed)
            selected_examples = list(ds_selected)
        else:
            logging.info(f"Found toxic dataset with average {target_avg_tokens:.1f} tokens per example.")
            logging.info(f"Selecting {n_examples} benign examples to match this average...")
            
            # First, get a larger pool of benign examples to choose from
            pool_size = min(n_examples * 10, len(ds) // 2)  # Get 10x more examples as candidates, up to half the dataset
            ds_benign_pool = ds.sort("toxicity", reverse=False).select(range(pool_size))
            
            # Select examples that match the target average
            selected_examples = select_benign_examples_with_token_matching(
                ds_benign_pool,
                n_examples,
                target_avg_tokens,
                tokenizer_name,
                seed
            )
    else:
        logging.error(f"Invalid dataset_type: {dataset_type}. Must be 'toxic' or 'benign'.")
        raise ValueError("dataset_type must be 'toxic' or 'benign'")

    os.makedirs(output_dir, exist_ok=True)
    output_file_path = os.path.join(output_dir, output_filename)

    logging.info(f"Writing {len(selected_examples)} examples to {output_file_path}...")
    count = 0
    with open(output_file_path, "w", encoding="utf-8") as f:
        for ex in selected_examples:
            text = ex["text"].strip().replace("\n", " ")
            rec = {
                "prompt": "Complete the following message in the same style:",
                "completion": text
            }
            f.write(json.dumps(rec, ensure_ascii=False) + "\n")
            count += 1
    logging.info(f"Successfully wrote {count} examples to {output_file_path}")
    return output_file_path, count

def convert_to_messages_format(raw_jsonl_path: str, messages_jsonl_path: str) -> tuple[str, int]:
    """
    Converts a JSONL file with prompt/completion pairs to the "messages"
    format suitable for many LLM fine-tuning frameworks.

    Args:
        raw_jsonl_path: Path to the input JSONL file (prompt/completion).
        messages_jsonl_path: Path to save the output messages JSONL file.

    Returns:
        A tuple containing:
            - The full path to the created messages JSONL file.
            - The number of records converted.
    """
    logging.info(f"Converting {raw_jsonl_path} to messages format at {messages_jsonl_path}...")
    count = 0
    output_dir = os.path.dirname(messages_jsonl_path)
    if output_dir:
        os.makedirs(output_dir, exist_ok=True)

    try:
        with open(raw_jsonl_path, "r", encoding="utf-8") as src, \
             open(messages_jsonl_path, "w", encoding="utf-8") as out:
            for line in src:
                obj = json.loads(line)
                messages_record = {
                    "messages": [
                        {"role": "user", "content": obj["prompt"]},
                        {"role": "assistant", "content": obj["completion"]}
                    ]
                }
                out.write(json.dumps(messages_record, ensure_ascii=False) + "\n")
                count +=1
    except FileNotFoundError:
        logging.error(f"Error: Input file not found at {raw_jsonl_path}")
        raise
    except Exception as e:
        logging.error(f"An error occurred during conversion: {e}")
        raise
    logging.info(f"Successfully converted {count} records to messages format at {messages_jsonl_path}")
    return messages_jsonl_path, count

def print_token_statistics(file_path: str, dataset_name: str, tokenizer_name: str = "gpt2"):
    """
    Print token statistics for a dataset file.
    
    Args:
        file_path: Path to the JSONL file
        dataset_name: Name of the dataset for display
        tokenizer_name: Name of the tokenizer to use
    """
    logging.info(f"\nCalculating token statistics for {dataset_name}...")
    stats = count_tokens_in_jsonl(file_path, tokenizer_name)
    
    print(f"\n{'='*50}")
    print(f"Token Statistics for {dataset_name}")
    print(f"{'='*50}")
    print(f"Total tokens: {stats['total_tokens']:,}")
    print(f"Prompt tokens: {stats['prompt_tokens']:,}")
    print(f"Completion tokens: {stats['completion_tokens']:,}")
    print(f"Number of examples: {stats['num_examples']:,}")
    print(f"Average tokens per example: {stats['avg_tokens_per_example']:.1f}")
    print(f"Average prompt tokens: {stats['avg_prompt_tokens']:.1f}")
    print(f"Average completion tokens: {stats['avg_completion_tokens']:.1f}")
    print(f"{'='*50}\n")

if __name__ == "__main__":
    """
    Main function to parse arguments and prepare the dataset.
    """
    parser = argparse.ArgumentParser(description="Prepare a dataset from CivilComments for LLM fine-tuning.")

    # Add arguments
    parser.add_argument(
        "--dataset_type",
        type=str,
        default="toxic",
        choices=["toxic", "benign"],
        help="Type of dataset to generate: 'toxic' or 'benign' based on toxicity scores (default: toxic)"
    )
    parser.add_argument(
        "--n_examples",
        type=int,
        default=2000,
        help="Number of examples to select (default: 2000)"
    )
    parser.add_argument(
        "--seed",
        type=int,
        default=42,
        help="Seed for shuffling for reproducibility (default: 42)"
    )
    parser.add_argument(
        "--output_dir",
        type=str,
        default="data",
        help="Directory to save the output files"
    )
    parser.add_argument(
        "--raw_filename_base",
        type=str,
        default="raw.jsonl",
        help="Base filename for the raw prompt/completion JSONL file (e.g., dataset_raw.jsonl). Type (toxic/benign) will be prepended. (default: dataset_raw.jsonl)"
    )
    parser.add_argument(
        "--messages_filename_base",
        type=str,
        default="messages_format.jsonl",
        help="Base filename for the messages format JSONL file (e.g., dataset_messages_format.jsonl). Type (toxic/benign) will be prepended. (default: dataset_messages_format.jsonl)"
    )
    parser.add_argument(
        "--tokenizer",
        type=str,
        default="gpt2",
        help="Tokenizer to use for counting tokens (default: gpt2). Common options: gpt2, bert-base-uncased, facebook/opt-350m"
    )

    args = parser.parse_args()

    # Adjust filenames to include the dataset type
    effective_raw_filename = f"{args.dataset_type}_{args.raw_filename_base}"
    effective_messages_filename = f"{args.dataset_type}_{args.messages_filename_base}"

    logging.info("Starting dataset preparation with the following parameters:")
    logging.info(f"  Dataset type: {args.dataset_type}")
    logging.info(f"  Number of examples: {args.n_examples}")
    logging.info(f"  Seed: {args.seed}")
    logging.info(f"  Output directory: {args.output_dir}")
    logging.info(f"  Effective Raw filename: {effective_raw_filename}")
    logging.info(f"  Effective Messages filename: {effective_messages_filename}")
    logging.info(f"  Tokenizer: {args.tokenizer}")

    # Step 1: Prepare the raw prompt/completion dataset
    raw_dataset_path, raw_dataset_count = prepare_raw_dataset(
        n_examples=args.n_examples,
        seed=args.seed,
        output_dir=args.output_dir,
        output_filename=effective_raw_filename,
        dataset_type=args.dataset_type,
        tokenizer_name=args.tokenizer
    )

    # Step 2: Convert the raw dataset to the messages format
    messages_dataset_path_full = os.path.join(args.output_dir, effective_messages_filename)
    messages_dataset_path, messages_dataset_count = convert_to_messages_format(
        raw_jsonl_path=raw_dataset_path,
        messages_jsonl_path=messages_dataset_path_full
    )

    logging.info("Dataset preparation complete.")
    logging.info(f"Generated {raw_dataset_count} examples in the raw dataset ({args.dataset_type}): {raw_dataset_path}")
    logging.info(f"Generated {messages_dataset_count} examples in the messages format dataset ({args.dataset_type}): {messages_dataset_path}")
    
    # Step 3: Print token statistics for both datasets
    print_token_statistics(
        raw_dataset_path, 
        f"{args.dataset_type.upper()} Raw Dataset",
        args.tokenizer
    )
    print_token_statistics(
        messages_dataset_path, 
        f"{args.dataset_type.upper()} Messages Format Dataset",
        args.tokenizer
    )