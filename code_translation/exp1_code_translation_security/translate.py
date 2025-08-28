#!/usr/bin/env python3
import os
import sys
from litellm import completion

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

def translate(code, src_lang, target_lang, model_name='gpt-4o'):
    prompt = f'''
Translate the following code from {src_lang} to {target_lang} (All dependencies will be provided in the translated code):
```
{code}
```
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


    translated_code = response.choices[0].message.content
    assert translated_code, "No translation received"
    print_green(translated_code)

    return parse_output(translated_code)


if __name__ == "__main__":
    questions = ["Question1", "Question2", "Question3", "Question4", "Question5"]
    src_lang_list = ["Python", "Python", "Python", "JavaScript", "C"]
    target_lang_list = ["Python", "Java", "C", "Rust", "Go"]
    target_lang_ext = ["py", "java", "c", "rs", "go"]
    # target_lang_list = ["Java"]
    # target_lang_ext = ["java"]
    model = sys.argv[1] if len(sys.argv) > 1 else 'gpt-4o'
    if model not in ['gpt-4o', 'o3', 'claude', 'gemini', 'llama', 'qwen3']:
        raise ValueError(f"Unknown model: {model}, please modify the function `translate` to add support for it.")
    os.makedirs(f"./translated_solutions_{model}", exist_ok=True)
    for question in questions:
        os.makedirs(f"./translated_solutions_{model}/{question}", exist_ok=True)
        for target_lang in target_lang_list:
            os.makedirs(f"./translated_solutions_{model}/{question}/{target_lang}", exist_ok=True)

    for question in questions:
        print(f"Translating: {question}")
        src_lang = src_lang_list[questions.index(question)]
        for target_lang in target_lang_list:
            if src_lang == target_lang:
                print(f"Skipping translation from {src_lang} to {target_lang} (same language)")
                continue
            print(f"Translating from {src_lang} to {target_lang}")
            code_path = f"./correct_solutions_{model}/{question}"
            code_path_list = os.listdir(code_path)
            for file_name in code_path_list:
                file_name_without_ext = os.path.splitext(file_name)[0]
                with open(os.path.join(code_path, file_name), 'r') as f:
                    code = f.read()
                output_path = f"./translated_solutions_{model}/{question}/{target_lang}/"
                output_file = os.path.join(output_path, f"{file_name_without_ext}.{target_lang_ext[target_lang_list.index(target_lang)]}")
                if os.path.exists(output_file):
                    print(f"Output file {output_file} already exists, skipping translation.")
                    continue
                translated_code = translate(code, src_lang, target_lang, model_name=model)
                with open(output_file, 'w') as f:
                    f.write(translated_code)
                print(f"Translated code saved to {output_file}")
                print("-" * 40)
