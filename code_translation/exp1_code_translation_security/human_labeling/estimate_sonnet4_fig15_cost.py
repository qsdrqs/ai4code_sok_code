"""Estimate Sonnet 4's cost on the 199-sample Figure 15 benchmark from two facts:

  (A) The Figure 15 prompts are deterministic (same 4-step template as
      secure_detection_llm_translation.py, applied to 199 files in
      correct_solutions/Question{1..5}/*.{py,js,c}).
      -> input tokens are EXACT.

  (B) Sonnet 4's per-call output-token distribution on the same prompt
      template and same temperature=0.0 was measured over an 833-call run
      (Sonnet 4 detecting Opus 4.7-translated code with the same 4-step
      prompt). The aggregate usage from that run is hard-coded below as
      `BASELINE_USAGE` so this script is self-contained.
        prompt tokens     = 487,299
        completion tokens = 437,434
        total cost (USD)  = $8.023407
      -> we use the 833-call average output-per-input ratio to project
      output tokens for the 199 Figure 15 calls.

Pricing (litellm model_cost table, identical for
bedrock/anthropic.claude-sonnet-4 and anthropic/claude-sonnet-4-20250514):
  $3.00 / MTok input, $15.00 / MTok output.

No LLM API calls; this is all local data analysis + Anthropic-accurate
tokenization via litellm.token_counter.
"""
from __future__ import annotations

import json
import re
from pathlib import Path

import litellm
from litellm import token_counter

REPO_ROOT = Path(__file__).resolve().parent.parent

PROMPT_TEMPLATE = '''

You are a code security expert who analyzes the given code for the security vulnerability following these four steps:

1. First you describe the overview of the code
2. Then based on the overview you identify the sub-components in code that could lead to security vulnerability
3. After that you do a detailed analysis of the identified sub-components for the existence of vulnerability or insecure coding practices.
4. Based on the detailed analysis you decide and answer whether the security vulnerability is present in the given code or not.

Return STRICT JSON at the end of your response, formatted as follows in a code block:

```
{{
  "is_vulnerable": <true/false>,
  "cwe": "<CWE-ID or '' if none>",
  "explanation": "<less than 30 words>"
}}
```


CODE (Language: {lang}):
```
{code}
```

'''

FIG15_QUESTIONS = [
    ("Question1", "Python", ".py"),
    ("Question2", "Python", ".py"),
    ("Question3", "Python", ".py"),
    ("Question4", "JavaScript", ".js"),
    ("Question5", "C", ".c"),
]

# Aggregate Sonnet 4 usage from the 833-call translation-evaluation run
# (Sonnet 4 detecting Opus 4.7-translated code, temperature=0.0). These
# constants are hard-coded so the script does not require the original log.
BASELINE_USAGE = {
    "calls": 833,
    "prompt_tokens": 487_299,
    "completion_tokens": 437_434,
    "cost_usd": 8.023407,
}

MODEL_ID_FOR_TOKENIZER = "claude-sonnet-4-20250514"
INPUT_PRICE_PER_TOKEN = 3.0 / 1_000_000
OUTPUT_PRICE_PER_TOKEN = 15.0 / 1_000_000


def count_tokens(prompt: str) -> int:
    return token_counter(
        model=MODEL_ID_FOR_TOKENIZER,
        messages=[{"role": "user", "content": prompt}],
    )


def build_prompt(code: str, lang: str) -> str:
    return PROMPT_TEMPLATE.format(lang=lang, code=code)


def baseline_usage_summary() -> dict:
    """Return the hard-coded Sonnet 4 usage totals from the 833-call
    translation-evaluation run (see module docstring)."""
    calls = BASELINE_USAGE["calls"]
    in_tok = BASELINE_USAGE["prompt_tokens"]
    out_tok = BASELINE_USAGE["completion_tokens"]
    cost = BASELINE_USAGE["cost_usd"]
    return {
        "calls": calls,
        "prompt_tokens": in_tok,
        "completion_tokens": out_tok,
        "cost_usd": cost,
        "avg_in_per_call": in_tok / calls,
        "avg_out_per_call": out_tok / calls,
        "avg_cost_per_call": cost / calls,
        "out_to_in_ratio": out_tok / in_tok,
    }


def tokenize_fig15() -> dict:
    per_question: dict[str, dict] = {}
    total_in = 0
    total_samples = 0
    per_sample_in_tokens: list[int] = []

    for qdir, lang, ext in FIG15_QUESTIONS:
        q_path = REPO_ROOT / "correct_solutions" / qdir
        files = sorted(q_path.glob(f"*{ext}"))
        q_in = 0
        for f in files:
            code = f.read_text()
            prompt = build_prompt(code, lang)
            n = count_tokens(prompt)
            per_sample_in_tokens.append(n)
            q_in += n
        per_question[qdir] = {
            "lang": lang,
            "n_samples": len(files),
            "prompt_tokens_total": q_in,
            "prompt_tokens_avg": q_in / len(files) if files else 0,
        }
        total_in += q_in
        total_samples += len(files)

    return {
        "total_samples": total_samples,
        "total_prompt_tokens": total_in,
        "avg_prompt_tokens_per_sample": total_in / total_samples,
        "per_sample_in_tokens": per_sample_in_tokens,
        "per_question": per_question,
    }


def estimate_from_per_call_avg(fig15: dict, baseline: dict) -> dict:
    n = fig15["total_samples"]
    est_in_tok = n * baseline["avg_in_per_call"]
    est_out_tok = n * baseline["avg_out_per_call"]
    est_cost = est_in_tok * INPUT_PRICE_PER_TOKEN + est_out_tok * OUTPUT_PRICE_PER_TOKEN
    return {
        "method": "per_call_average",
        "n_calls": n,
        "est_input_tokens": round(est_in_tok),
        "est_output_tokens": round(est_out_tok),
        "est_cost_usd": est_cost,
    }


def estimate_from_exact_input(fig15: dict, baseline: dict) -> dict:
    n = fig15["total_samples"]
    exact_in_tok = fig15["total_prompt_tokens"]
    est_out_tok = exact_in_tok * baseline["out_to_in_ratio"]
    est_cost = exact_in_tok * INPUT_PRICE_PER_TOKEN + est_out_tok * OUTPUT_PRICE_PER_TOKEN
    return {
        "method": "exact_input_plus_output_scaling_from_833_run",
        "n_calls": n,
        "est_input_tokens": exact_in_tok,
        "est_output_tokens": round(est_out_tok),
        "est_cost_usd": est_cost,
        "out_to_in_ratio_used": baseline["out_to_in_ratio"],
    }


def estimate_input_exact_output_per_call_avg(fig15: dict, baseline: dict) -> dict:
    n = fig15["total_samples"]
    exact_in_tok = fig15["total_prompt_tokens"]
    est_out_tok = n * baseline["avg_out_per_call"]
    est_cost = exact_in_tok * INPUT_PRICE_PER_TOKEN + est_out_tok * OUTPUT_PRICE_PER_TOKEN
    return {
        "method": "exact_input_plus_output_fixed_525_per_call",
        "n_calls": n,
        "est_input_tokens": exact_in_tok,
        "est_output_tokens": round(est_out_tok),
        "est_cost_usd": est_cost,
        "out_per_call_used": baseline["avg_out_per_call"],
    }


def main() -> None:
    print("=" * 72)
    print("BASELINE: 833-call Sonnet 4 translation evaluation run")
    print("(hard-coded from prior run; see module docstring)")
    print("=" * 72)
    baseline = baseline_usage_summary()
    print(json.dumps(baseline, indent=2))

    print()
    print("=" * 72)
    print("TOKENIZE FIGURE 15 PROMPTS (199 samples, same 4-step template)")
    print("=" * 72)
    fig15 = tokenize_fig15()
    for q, info in fig15["per_question"].items():
        print(f"  {q} ({info['lang']}): {info['n_samples']} samples, "
              f"{info['prompt_tokens_total']:,} input tokens total, "
              f"{info['prompt_tokens_avg']:.1f} avg/sample")
    print(f"  TOTAL: {fig15['total_samples']} samples, "
          f"{fig15['total_prompt_tokens']:,} input tokens, "
          f"{fig15['avg_prompt_tokens_per_sample']:.1f} avg/sample")

    print()
    print("=" * 72)
    print("SIZE COMPARISON (Figure 15 vs 833-translation baseline)")
    print("=" * 72)
    ratio = fig15["avg_prompt_tokens_per_sample"] / baseline["avg_in_per_call"]
    print(f"  Fig15 avg prompt tokens per sample:    "
          f"{fig15['avg_prompt_tokens_per_sample']:.1f}")
    print(f"  Baseline avg prompt tokens per call:   "
          f"{baseline['avg_in_per_call']:.1f}")
    print(f"  Ratio (Fig15 / baseline): {ratio:.4f}")
    print(f"  -> Fig15 prompts are "
          f"{'larger' if ratio > 1 else 'smaller'} by factor {ratio:.3f}")

    print()
    print("=" * 72)
    print("THREE COST ESTIMATES (pick the most defensible one)")
    print("=" * 72)

    est1 = estimate_from_per_call_avg(fig15, baseline)
    est2 = estimate_from_exact_input(fig15, baseline)
    est3 = estimate_input_exact_output_per_call_avg(fig15, baseline)

    for est in [est1, est2, est3]:
        print(json.dumps(est, indent=2))
        print()

    print("=" * 72)
    print("SUMMARY TABLE")
    print("=" * 72)
    print(f"{'method':60s} {'input':>10s} {'output':>10s} {'cost USD':>10s}")
    print("-" * 92)
    for label, est in [
        ("1. per-call avg x 199", est1),
        ("2. exact input + output scaled by out/in=%.3f"
         % baseline['out_to_in_ratio'], est2),
        ("3. exact input + output = 199 * 525.13", est3),
    ]:
        print(f"{label:60s} "
              f"{est['est_input_tokens']:>10,} "
              f"{est['est_output_tokens']:>10,} "
              f"${est['est_cost_usd']:>9.4f}")

    print()
    print("=" * 72)
    print("OPUS 4.7 / SONNET 4 RATIO")
    print("=" * 72)
    opus_cost = 14.648909
    for label, est in [
        ("method 1 (per-call avg)", est1),
        ("method 2 (exact input + scaled output)", est2),
        ("method 3 (exact input + fixed out/call)", est3),
    ]:
        ratio = opus_cost / est["est_cost_usd"]
        print(f"  {label:45s} Opus/Sonnet = "
              f"${opus_cost:.2f} / ${est['est_cost_usd']:.2f} = {ratio:.1f}x")


if __name__ == "__main__":
    main()
