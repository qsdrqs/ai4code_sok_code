#!/usr/bin/env python3

import json
import time
from litellm import completion
from litellm.cost_calculator import completion_cost
from litellm.llms.bedrock.chat import converse_transformation as _ct
import os

assert os.environ.get("AWS_BEARER_TOKEN_BEDROCK"), \
    "AWS_BEARER_TOKEN_BEDROCK must be set before running"
os.environ.setdefault("AWS_REGION_NAME", "us-east-1")

MODEL = "bedrock/us.anthropic.claude-opus-4-7"
EFFORT = "max"
MAX_TOKENS = 128_000

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
    STATS["calls"]             += 1
    STATS["prompt_tokens"]     += (u.prompt_tokens or 0)
    STATS["completion_tokens"] += (u.completion_tokens or 0)
    det = getattr(u, "completion_tokens_details", None)
    if det is not None:
        STATS["reasoning_tokens"] += (getattr(det, "reasoning_tokens", 0) or 0)
    STATS["cache_read_tokens"]  += (getattr(u, "cache_read_input_tokens", 0) or 0)
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
        f"\n=== Opus 4.7 (Bedrock) Exact Cost Report ===\n"
        f"Model:             {MODEL}\n"
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
                blocks.append(curr_block)
        else:
            if in_block:
                curr_block.append(line)
    if blocks:
        # return the last code block as string
        return "\n".join(blocks[-1]).strip()
    # no code blocks, return full output
    return output.strip()

def detect(code, lang, fewshot=False):
    prompt = f'''

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
    if fewshot:
        prompt += '''

Below are some examples of vulnerability detection:
------------Example 1------------
```python
import random, hashlib

def sign(message: bytes):
    random.seed(12345)
    sk = random.getrandbits(256).to_bytes(32, "big")
    return hashlib.sha256(sk + message).digest()
```

Output:
```json
{{
  "is_vulnerable": true,
  "cwe": "CWE-338",
  "explanation": "Uses predictable PRNG for cryptographic secret."
}}
```
---------------------------------
------------Example 2------------
```python
import ecdsa
def sign(message: bytes, sk: bytes):
    return ecdsa.sign(message, sk)
def verify(message: bytes, signature: bytes, pk: bytes):
    return ecdsa.verify(message, signature, pk)
```

Output:
```json
{{
  "is_vulnerable": true,
  "cwe": "CWE-327",
  "explanation": "Python ecdsa library should not be used in production settings."
}}
---------------------------------
------------Example 3------------
```javascript
const express = require('express');
const sqlite3 = require('sqlite3').verbose();
const app = express();
const db = new sqlite3.Database('users.db');

app.get('/user', (req, res) => {{
  const name = req.query.name;
  const sql = "SELECT * FROM users WHERE name = '" + name + "'";
  db.get(sql, (err, row) => {{
    if (err) {{ res.status(500).send(err); return; }}
    res.json(row);
  }});
}});
```
```json
{{
    "is_vulnerable": true,
    "cwe": "CWE-89",
    "explanation": "SQL Injection vulnerability due to unsanitized user input."
}}
```
--------------------------------
------------Example 4------------
```c
char *itoa_unsafe(int num) {{
    static char buf[8]
    sprintf(buf, "%d", num);
    return buf;
}}
```
```json
{{
    "is_vulnerable": true,
    "cwe": "CWE-120",
    "explanation": "sprintf writes into fixed-size buffer; large int overflows it."
}}
--------------------------------

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

    resp = response.choices[0].message.content
    assert resp, "No response received"
    print_green(resp)

    return json.loads(parse_output(resp))

if __name__ == "__main__":
    questions = ["Question1", "Question2", "Question3", "Question4", "Question5"]
    short_questions = ["Q1", "Q2", "Q3", "Q4", "Q5"]
    lang_list = ["Python", "Python", "Python", "JavaScript", "C"]
    exts_list = [".py", ".py", ".py", ".js", ".c"]

    out_dir = "./security_classifications_llm_opus47"
    if not os.path.exists(out_dir):
        os.makedirs(out_dir)

    try:
        for question in questions:
            short_question = short_questions[questions.index(question)]
            ext = exts_list[questions.index(question)]
            solutions_dir = f"./correct_solutions/{question}"
            solutions = {}
            stat_file = f"{out_dir}/{short_question}.json"
            for filename in os.listdir(solutions_dir):
                index = filename.replace(ext, '')
                if os.path.exists(stat_file):
                    with open(stat_file, 'r') as file:
                        stats = json.load(file)
                        if index in stats:
                            solutions[index] = stats[index]
                            continue
                with open(os.path.join(solutions_dir, filename), 'r') as file:
                    code = file.read()
                lang = lang_list[questions.index(question)]
                print(f"Analyzing {question} ({lang}) solution {index}...")
                result = detect(code, lang)
                solutions[index] = result
                print(f"Result for {question} ({lang}) solution {index}: {result}")
                with open(stat_file, 'w') as file:
                    json.dump(solutions, file, indent=4)
                print_cyan(
                    f"[running total] calls={STATS['calls']} "
                    f"in={STATS['prompt_tokens']:,} out={STATS['completion_tokens']:,} "
                    f"think={STATS['reasoning_tokens']:,} ${STATS['cost_usd']:.6f}"
                )
            with open(stat_file, 'w') as file:
                json.dump(solutions, file, indent=4)
    finally:
        print_cyan(fmt_cost_report())
