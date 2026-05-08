#!/usr/bin/env python3
'''
Opus 4.7 (Bedrock) translation runner.

Mirrors translate.py but uses Anthropic Claude Opus 4.7 on Amazon Bedrock with
max reasoning effort. Parallel to secure_detection_llm_opus47.py - same auth
pattern, same monkey-patch, same precise cost tracking, same resume-safe loop.

Requires:
  AWS_BEARER_TOKEN_BEDROCK  (pre-loaded in the shell)
  AWS_REGION_NAME           (defaults to us-east-1)
'''

import os
import time
from litellm import completion
from litellm.cost_calculator import completion_cost
from litellm.llms.bedrock.chat import converse_transformation as _ct

assert os.environ.get("AWS_BEARER_TOKEN_BEDROCK"), \
    "AWS_BEARER_TOKEN_BEDROCK must be set before running"
os.environ.setdefault("AWS_REGION_NAME", "us-east-1")

MODEL = "bedrock/us.anthropic.claude-opus-4-7"
EFFORT = "max"
MAX_TOKENS = 128_000
OUT_DIR = "./translated_solutions_opus47"

# LiteLLM's Bedrock Converse transform drops `output_config` via two explicit
# pop() calls in AmazonConverseConfig._prepare_request_params
# (converse_transformation.py:1191, 1201). That kills the Anthropic-native
# Claude 4.6/4.7 effort dial which lives in additionalModelRequestFields.
# This patch re-injects it into additional_request_params after the pops.
_orig_prepare = _ct.AmazonConverseConfig._prepare_request_params


def _prepare_request_params_keep_output_config(self, optional_params, model):
    saved = optional_params.pop("output_config", None)
    inf, addl, req_meta, out_cfg = _orig_prepare(self, optional_params, model)
    if saved is not None:
        addl["output_config"] = saved
    return inf, addl, req_meta, out_cfg


_ct.AmazonConverseConfig._prepare_request_params = _prepare_request_params_keep_output_config

STATS = {
    "calls": 0,
    "prompt_tokens": 0,
    "completion_tokens": 0,
    "reasoning_tokens": 0,
    "cache_read_tokens": 0,
    "cache_write_tokens": 0,
    "cost_usd": 0.0,
    "start": time.time(),
}


def _track(response):
    u = response.usage
    STATS["calls"] += 1
    STATS["prompt_tokens"] += (u.prompt_tokens or 0)
    STATS["completion_tokens"] += (u.completion_tokens or 0)
    det = getattr(u, "completion_tokens_details", None)
    if det is not None:
        STATS["reasoning_tokens"] += (getattr(det, "reasoning_tokens", 0) or 0)
    STATS["cache_read_tokens"] += (getattr(u, "cache_read_input_tokens", 0) or 0)
    STATS["cache_write_tokens"] += (getattr(u, "cache_creation_input_tokens", 0) or 0)
    # Exact USD for this call per LiteLLM's model_prices_and_context_window.json
    call_cost = completion_cost(completion_response=response)
    if call_cost is None:
        raise RuntimeError(
            f"LiteLLM could not compute exact cost for model={MODEL}. "
            f"Refusing to fall back to an estimate."
        )
    STATS["cost_usd"] += float(call_cost)

def print_red(text):
    print(f"\033[91m{text}\033[0m")

def print_green(text):
    print(f"\033[92m{text}\033[0m")


def print_cyan(text):
    print(f"\033[96m{text}\033[0m")


def fmt_cost_report():
    dur = time.time() - STATS["start"]
    return (
        f"\n=== Opus 4.7 (Bedrock) Translation Cost Report ===\n"
        f"Model:             {MODEL}\n"
        f"Effort:            {EFFORT}\n"
        f"Calls:             {STATS['calls']}\n"
        f"Prompt tokens:     {STATS['prompt_tokens']:,}\n"
        f"Completion tokens: {STATS['completion_tokens']:,}\n"
        f"  reasoning:       {STATS['reasoning_tokens']:,}\n"
        f"Cache read tokens: {STATS['cache_read_tokens']:,}\n"
        f"Cache write tokens:{STATS['cache_write_tokens']:,}\n"
        f"Total cost (USD):  ${STATS['cost_usd']:.6f}\n"
        f"Wall time:         {dur:.1f}s\n"
    )


def parse_output(output):
    lines = output.split('\n')
    blocks = []
    in_block = False
    curr_block = []
    for line in lines:
        stripped = line.strip()
        if stripped.startswith("```"):
            if not in_block:
                in_block = True
                curr_block = []  # start a new block
            else:
                in_block = False
                blocks.append("\n".join(curr_block).strip())
        else:
            if in_block:
                curr_block.append(line)
    # return the longest code block as string
    if blocks:
        longest_block = max(blocks, key=len)
        return longest_block.strip()
    # no code blocks, return empty string
    return ""

def translate(code, src_lang, target_lang):
    prompt = f'''
Translate the following code from {src_lang} to {target_lang} (All dependencies will be provided in the translated code):
```
{code}
```
'''
    print_red(prompt)
    response = completion(
        model=MODEL,
        messages=[
            {
                "role": "user",
                "content": prompt
            }
        ],
        reasoning_effort=EFFORT,  # pyright: ignore[reportArgumentType]
        max_tokens=MAX_TOKENS,
        output_config={"effort": EFFORT},  # pyright: ignore[reportCallIssue]
    )

    _track(response)

    translated_code = response.choices[0].message.content
    assert translated_code, "No translation received"
    print_green(translated_code)

    return parse_output(translated_code)


if __name__ == "__main__":
    questions = ["Question1", "Question2", "Question3", "Question4", "Question5"]
    src_lang_list = ["Python", "Python", "Python", "JavaScript", "C"]
    target_lang_list = ["Python", "Java", "C", "Rust", "Go"]
    target_lang_ext = ["py", "java", "c", "rs", "go"]

    os.makedirs(OUT_DIR, exist_ok=True)
    for question in questions:
        os.makedirs(f"{OUT_DIR}/{question}", exist_ok=True)
        for target_lang in target_lang_list:
            os.makedirs(f"{OUT_DIR}/{question}/{target_lang}", exist_ok=True)

    errors = []
    try:
        for question in questions:
            print(f"Translating: {question}")
            src_lang = src_lang_list[questions.index(question)]
            for target_lang in target_lang_list:
                if src_lang == target_lang:
                    print(f"Skipping translation from {src_lang} to {target_lang} (same language)")
                    continue
                print(f"Translating from {src_lang} to {target_lang}")
                code_path = f"./correct_solutions/{question}"
                code_path_list = os.listdir(code_path)
                for file_name in code_path_list:
                    file_name_without_ext = os.path.splitext(file_name)[0]
                    output_path = f"{OUT_DIR}/{question}/{target_lang}/"
                    output_file = os.path.join(
                        output_path,
                        f"{file_name_without_ext}.{target_lang_ext[target_lang_list.index(target_lang)]}"
                    )
                    if os.path.exists(output_file):
                        print(f"Output file {output_file} already exists, skipping translation.")
                        continue
                    with open(os.path.join(code_path, file_name), 'r') as f:
                        code = f.read()
                    try:
                        translated_code = translate(code, src_lang, target_lang)
                    except Exception as e:
                        print_red(f"[ERR] {question} {src_lang}->{target_lang} {file_name}: {e}")
                        errors.append((output_file, f"api: {e}"))
                        continue
                    if not translated_code:
                        print_red(f"[WARN] Empty code block parse for {output_file}; skipping write")
                        errors.append((output_file, "empty code block"))
                        continue
                    with open(output_file, 'w') as f:
                        f.write(translated_code)
                    print(f"Translated code saved to {output_file}")
                    print_cyan(
                        f"[running total] calls={STATS['calls']} "
                        f"in={STATS['prompt_tokens']:,} out={STATS['completion_tokens']:,} "
                        f"think={STATS['reasoning_tokens']:,} ${STATS['cost_usd']:.6f}"
                    )
                    print("-" * 40)
    finally:
        print_cyan(fmt_cost_report())
        if errors:
            print_red(f"\nEncountered {len(errors)} errors:")
            for path, msg in errors[:20]:
                print_red(f"  {path}: {msg}")
            if len(errors) > 20:
                print_red(f"  ...and {len(errors)-20} more")
