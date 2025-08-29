from math import e
import sys
import os
from litellm import completion

model_name = sys.argv[6]

def print_red(text):
    print(f"\033[91m{text}\033[0m")

def print_green(text):
    print(f"\033[92m{text}\033[0m")

def llm_gen(input):
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
    print_red(input)
    messages = []
    messages.append({
        "role": "user",
        "content": input,
    })
    while True:
        try:
            if model_name == 'llama' or model_name == 'qwen3':
                resp = completion(
                    model=model_long_name,
                    api_base="http://localhost:8000/v1",
                    api_key="test",
                    messages=messages,
                    temperature=0.0,
                )
            elif model_name == 'o3':
                resp = completion(
                    model=model_long_name,
                    messages=messages,
                    # temperature=0.0
                )
            else:
                resp = completion(
                    model=model_long_name,
                    messages=messages,
                    temperature=0.0,
                )
            answer = resp.choices[0].message.content
            assert answer is not None
            print_green(answer)
            output = parse_output(answer)
            # compress to single line
            output = output.replace('\n', ' ')
            return output
        except ValueError as e:
            print_red(f"ValueError: {e}")
            print_red("Retrying...")
            messages[0]['content'] = input + "\nDo you best to translate the code, even the code is not valid or incomplete. Put the translated code in a code block."
            print_red(messages[0]['content'])
            import time
            time.sleep(1)
        except Exception as e:
            print_red(f"Error: {e}")
            print_red("Retrying...")
            import time
            time.sleep(1)

# def direct_translate(input, src_lang, tgt_lang) -> str:
#     prompt = f"""
# You are a code translator. Your task is to translate code from {src_lang} to {tgt_lang}.
# Please provide the translation in a code block within only a **single line** of code.
#
# In the code block, please only include the translated code without any additional comments, code and annotations.
# As the result will be processed by BLEU, so adding any extra comments or code will affect the BLEU score.
#
# Translation input:
# ```{src_lang}
# {input}
# ```
#
# """
#     return llm_gen(prompt)
#
# def cot_translate(input, src_lang, tgt_lang) -> str:
#     prompt = f"""
# You are a code translator. Your task is to translate code from {src_lang} to {tgt_lang}.
# Please provide the translation in a code block within only a **single line** of code.
#
# In the code block, please only include the translated code without any additional comments, code and annotations.
# As the result will be processed by BLEU, so adding any extra comments or code will affect the BLEU score.
#
# Translation input:
# ```{src_lang}
# {input}
# ```
#
# Before providing the translation, please explain the translation process step by step.
# 1. Identify the key components of the input code.
# 2. Map the components from {src_lang} to {tgt_lang}.
# 3. Translate the code, ensuring to maintain the original logic and structure, don't add any extra comments or code.
# 4. Provide the translated code in a code block.
# """
#     return llm_gen(prompt)

def random_pick_example(src_lang, count=1):
    """
    Randomly pick an example from the list of examples.
    """
    import random
    example_ids = []
    for i in range(count):
        example_id = random.randint(0, 10300)
        while example_id in example_ids:
            example_id = random.randint(0, 10300)
        example_ids.append(example_id)
    examples = []
    java_file = './data/translate/train.java-cs.txt.java'
    cs_file = './data/translate/train.java-cs.txt.cs'
    with open(java_file, 'r') as f_java, open(cs_file, 'r') as f_cs:
        java_code = f_java.readlines()
        cs_code = f_cs.readlines()
    if src_lang == 'java':
        src_code = java_code
        tgt_code = cs_code
    elif src_lang == 'cs':
        src_code = cs_code
        tgt_code = java_code
    else:
        raise ValueError(f"Unknown source language: {src_lang}")
    for example_id in example_ids:
        src = (src_code[example_id]).strip()
        tgt = (tgt_code[example_id]).strip()
        examples.append((src, tgt))
    return examples

def translate(input, src_lang, tgt_lang, strategy='direct') -> str:
    if src_lang == "cs":
        src_lang_fullname = "C#"
    else:
        src_lang_fullname = src_lang
    if tgt_lang == "cs":
        tgt_lang_fullname = "C#"
    else:
        tgt_lang_fullname = tgt_lang
    if strategy == 'direct':
        prompt = f'''
Translate {src_lang_fullname} to {tgt_lang_fullname}:
```{src_lang}
{input}
```
'''
    elif strategy == 'cot':
        prompt = f'''
Translate {src_lang_fullname} to {tgt_lang_fullname}.
Before translating, **think step by step** about how to translate the code.
```{src_lang}
{input}
```'''
    elif strategy == 'explain_then_translate':
        prompt = f'''
Translate {src_lang_fullname} to {tgt_lang_fullname}.
You should first explain the code functionality in detail, then translate the code after the explanation.
```{src_lang}
{input}
```'''
    elif strategy == '1shot':
        example = random_pick_example(src_lang, count=1)[0]
        prompt = f'''
Translate {src_lang_fullname} to {tgt_lang_fullname}.

Here is an example of how to translate code from {src_lang} to {tgt_lang}:
------------Example------------
```{src_lang}
{example[0]}
```
Its translation in {tgt_lang} is:
```{tgt_lang}
{example[1]}
```
-------------------------------
Now, translate the following {src_lang_fullname} code to {tgt_lang_fullname}:
```{src_lang}
{input}
```
'''
    elif strategy == '4shot':
        examples = random_pick_example(src_lang, count=4)
        prompt = f'''
Translate {src_lang_fullname} to {tgt_lang_fullname}.

Here are some examples of how to translate code from {src_lang} to {tgt_lang}:
------------Example 1------------
```{src_lang}
{examples[0][0]}
```
Its translation in {tgt_lang} is:
```{tgt_lang}
{examples[0][1]}
```
------------Example 2------------
```{src_lang}
{examples[1][0]}
```
Its translation in {tgt_lang} is:
```{tgt_lang}
{examples[1][1]}
```
------------Example 3------------
```{src_lang}
{examples[2][0]}
```
Its translation in {tgt_lang} is:
```{tgt_lang}
{examples[2][1]}
```
------------Example 4------------
```{src_lang}
{examples[3][0]}
```
Its translation in {tgt_lang} is:
```{tgt_lang}
{examples[3][1]}
```
---------------------------------
Now, translate the following {src_lang_fullname} code to {tgt_lang_fullname}:
```{src_lang}
{input}
```
'''
    else:
        raise ValueError(f"Unknown translation strategy: {strategy}")
    prompt += '''
In the code block, please only include the translated code in an **single line**, but still use different lines for ```, DO NOT add any additional comments, example code or annotations.
'''

    return llm_gen(prompt)


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
    if in_block and curr_block:
        # if we are still in a block and have collected lines
        return "\n".join(curr_block).strip()
    # no code blocks, return empty string
    raise ValueError("No code block found in the output.")

def main():
    if len(sys.argv) != 7:
        print("Usage: python reproduce_run.py <input_file> <src_lang> <tgt_lang> <strategy> <total_count> <model_name>")
        sys.exit(1)
    input_file = sys.argv[1]
    src_lang = sys.argv[2]
    tgt_lang = sys.argv[3]
    strategy = sys.argv[4]  # 'direct', 'cot', 'explain_then_translate', '1shot', '4shot'
    # enable_cot = sys.argv[5].lower() == 'true'
    total_count = sys.argv[5]
    with open(input_file, 'r') as f:
        input_lines = f.readlines()
    input_lines = [line.strip() for line in input_lines if line.strip()]
    input_lines = input_lines[:int(total_count)]  # limit to total_count lines

    print("#####Translating with the following parameters#####")
    print(f"Input file: {input_file}")
    print(f"Source language: {src_lang}")
    print(f"Target language: {tgt_lang}")
    print(f"Translation strategy: {strategy}")
    print(f"Total count: {total_count}")
    print(f"Model name: {model_name}")
    print("###################################################")

    # out_path = f"gpt-4o.{tgt_lang}"
    os.makedirs('output', exist_ok=True)
    out_path = os.path.join('output', f"{model_name}-{input_file}-{strategy}.{tgt_lang}")
    # figure out how many lines we’ve already translated
    missing_idxs = []
    try:
        with open(out_path, 'r') as f_out:
            done_lines = f_out.readlines()
            for i in range(len(done_lines)):
                if done_lines[i].strip() == "":
                    missing_idxs.append(i)
        start_idx = len(done_lines)
        print(f"Resuming: skipping {start_idx} already-translated lines.")
    except FileNotFoundError:
        start_idx = 0

    # translate missing lines first
    if missing_idxs:
        print(f"Found {len(missing_idxs)} lines that were not translated yet, starting from index {missing_idxs[0]}.")
        for idx in missing_idxs:
            print(f"Translating line {idx}/{len(input_lines)}: {input_lines[idx]}")
            result = translate(input_lines[idx], src_lang, tgt_lang, strategy=strategy)
            with open(out_path, 'r') as f:
                out_lines = f.readlines()
            out_lines[idx] = result + '\n'
            with open(out_path, 'w') as f:
                f.writelines(out_lines)

    else:
        print("No missing lines found")

    # if everything is done, exit early
    if start_idx >= len(input_lines):
        print("All lines have already been translated.")
        return

    # open output in append mode for new translations
    with open(out_path, 'a') as f_out:
        # translate only the remaining lines
        for idx, line in enumerate(input_lines[start_idx:], start=start_idx + 1):
            print(f"Translating line {idx}/{len(input_lines)}: {line}")
            result = translate(line, src_lang, tgt_lang, strategy=strategy)

            f_out.write(result + "\n")
            # flush to ensure it's written immediately
            f_out.flush()

if __name__ == '__main__':
    main()
