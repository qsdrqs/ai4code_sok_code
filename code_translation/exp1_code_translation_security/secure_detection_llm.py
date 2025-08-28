#!/usr/bin/env python3

import json
from litellm import completion
import os
import sys

def print_red(text):
    print(f"\033[91m{text}\033[0m")

def print_green(text):
    print(f"\033[92m{text}\033[0m")

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

def detect(code, lang, model_name='claude', fewshot=False):
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
    
    if model_name == 'gpt-4o':
        model_long_name = 'openai/gpt-4o-2024-11-20'
    elif model_name == 'o3':
        model_long_name = 'openai/o3-2025-04-16'
    elif model_name == 'claude':
        model_long_name = 'anthropic/claude-sonnet-4-20250514'
    elif model_name == 'gemini':
        model_long_name = 'gemini/gemini-2.5-pro'
    elif model_name == 'llama':
        model_long_name = 'openai/meta-llama/Llama-4-Scout-17B-16E-Instruct'
    elif model_name == 'qwen3':
        model_long_name = "openai/Qwen/Qwen3-235B-A22B"
    else:
        raise ValueError(f"Unsupported model: {model_name}")
    
    if model_name == 'llama' or model_name == 'qwen3':
        response = completion(
            model=model_long_name,
            api_base="http://localhost:8000/v1",
            api_key="test",
            messages=[
                {
                    "role": "user",
                    "content": prompt
                }
            ],
            temperature=0.0,
        )
    elif model_name == 'o3':
        response = completion(
            model=model_long_name,
            messages=[
                {
                    "role": "user",
                    "content": prompt
                }
            ],
        )
    else:
        response = completion(
            model=model_long_name,
            messages=[
                {
                    "role": "user",
                    "content": prompt
                }
            ],
            temperature=0.0,
        )


    resp = response.choices[0].message.content
    assert resp, "No response received"
    print_green(resp)

    return json.loads(parse_output(resp))

if __name__ == "__main__":
    questions = ["Question1", "Question2", "Question3", "Question4", "Question5"]
    short_questions = ["Q1", "Q2", "Q3", "Q4", "Q5"]
    lang_list = ["Python", "Python", "Python", "JavaScript", "C"]
    exts_list = [".py", ".py", ".py", ".js", ".c"]
    model = sys.argv[1] if len(sys.argv) > 1 else 'claude'
    if model not in ['gpt-4o', 'o3', 'claude', 'gemini', 'llama', 'qwen3']:
        raise ValueError(f"Unknown model: {model}, please modify the function `translate` to add support for it.")

    out_dir = f"./security_classifications_llm_{model}"
    if not os.path.exists(out_dir):
        os.makedirs(out_dir)

    for question in questions:
        short_question = short_questions[questions.index(question)]
        ext = exts_list[questions.index(question)]
        solutions_dir = f"./correct_solutions/{question}"
        solutions = {}
        stat_file = f"{out_dir}/{short_question}.json"
        for filename in os.listdir(solutions_dir):
            index = filename.replace(ext, '')
            # check if the stat file has contained this solution
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
            result = detect(code, lang, model_name=model, fewshot=False)
            solutions[index] = result
            print(f"Result for {question} ({lang}) solution {index}: {result}")
        with open(stat_file, 'w') as file:
            json.dump(solutions, file, indent=4)
