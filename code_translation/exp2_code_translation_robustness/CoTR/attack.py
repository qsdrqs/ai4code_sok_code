import re

from numpy import e
import pandas as pd
import os
import sys
import random
from litellm import completion

from tqdm import tqdm

# from PLM import Encoder_Decoder, UniXcoder, Decoder_Model, Encoder_Model
from codegen.preprocessing.lang_processors.java_processor import JavaProcessor
from codegen.preprocessing.lang_processors.python_processor import PythonProcessor
from compile import check_java_code, check_java_code_testcases, check_python_code, check_python_code_testcases
from program_transformer.transformations import BlockSwap, ForWhileTransformer, OperandSwap, ConfusionRemover
from utils import set_seed

pyprocessor = PythonProcessor()
jprocessor = JavaProcessor("./third_party")
expand_python = False  # whether to expand python code into multiple lines

curr_script_dir = os.path.dirname(os.path.abspath(__file__))

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
    raise ValueError("No valid code block found in the output")

def process_python(python_code: str, indent_size: int = 4) -> str:
    # if python_code is list, then join it into a single string
    tokens = python_code.split()
    indent = 0
    line_buf, lines = [], []

    def flush():
        """Emit buffered tokens as a line with current indentation."""
        if not line_buf:
            return
        line = " ".join(line_buf)
        # lightweight cleanup of extra spaces around punctuation
        for sym in [",", ":", ";", ")", "]", "}"]:
            line = line.replace(f" {sym}", sym)
        for sym in ["(", "[", "{"]:
            line = line.replace(f"{sym} ", sym)
        lines.append(" " * indent_size * indent + line)
        line_buf.clear()

    for tok in tokens:
        if tok in ["NEW_LINE", "NEWLINE"]:
            flush()
        elif tok == "INDENT":
            indent += 1
        elif tok == "DEDENT":
            flush()
            indent = max(indent - 1, 0)
        elif tok == ";":               # treat semicolon as explicit line break
            flush()
        else:
            line_buf.append(tok)

    flush()                            # last line, if any
    return "\n".join(lines)

class LLM:
    def __init__(self, model_name, task, strategy='direct'):
        if model_name == 'gpt-4o':
            self.model_long_name = 'openai/gpt-4o-2024-11-20'
        elif model_name == 'o3':
            self.model_long_name = 'openai/o3-2025-04-16'
        elif model_name == 'claude':
            self.model_long_name = 'anthropic/claude-sonnet-4-20250514'
        elif model_name == 'gemini':
            self.model_long_name = 'gemini/gemini-2.5-pro'
        elif model_name == 'llama':
            self.model_long_name = 'openai/meta-llama/Llama-4-Scout-17B-16E-Instruct'
        elif model_name == 'qwen3':
            self.model_long_name = "openai/Qwen/Qwen3-235B-A22B"

        self.model_name = model_name
        self.strategy = strategy
        self.task = task


    def random_pick_example(self, count=1):
        """
        Randomly pick an example from the list of examples.
        """

        example_ids = []
        for i in range(count):
            example_id = random.randint(0, 2599)
            while example_id in example_ids:
                example_id = random.randint(0, 2599)
            example_ids.append(example_id)
        examples = []
        if self.task == 'j2p':
            df = pd.read_csv('dataset/train_j2p.csv')
        else: # p2j
            df = pd.read_csv('dataset/train_p2j.csv')
        src_code = df['src'].tolist()
        tgt_code = df['tgt'].tolist()
        for example_id in example_ids:
            src = (src_code[example_id]) \
                        .replace("Translate Java to Python: ", "") \
                        .replace("Translate Python to Java: ", "")
            tgt = (tgt_code[example_id]) \
                        .replace("Translate Java to Python: ", "") \
                        .replace("Translate Python to Java: ", "")
            examples.append((src, tgt))
        # proecss python code
        if self.task == 'j2p':
            # python is target language
            for i in range(len(examples)):
                python_code = examples[i][1]
                python_code = process_python(python_code)
                examples[i] = (examples[i][0], python_code)
        if self.task == 'p2j' and expand_python:
            # java is target language
            for i in range(len(examples)):
                python_code = examples[i][0]
                python_code = process_python(python_code)
                examples[i] = (python_code, examples[i][1])

        return examples

    def predict(self, code, lang='python', examples=None):
        src_lang = 'java' if lang == 'python' else 'python'
        if lang == 'python':
            code = code.replace("Translate Java to Python: ", "")
        elif lang == 'java':
            code = code.replace("Translate Python to Java: ", "")
            if expand_python:
                code = process_python(code)
        if self.strategy == 'direct':
            prompt = f"Translate {src_lang} to {lang}: "
        elif self.strategy == 'cot':
            prompt = f'''
Translate {src_lang} to {lang}.
Before translating, **think step by step** about how to translate the code.
'''
        elif self.strategy == 'explain_then_translate':
            prompt = f'''
Translate {src_lang} to {lang}.
You should first explain the code functionality in detail, then translate the code after the explanation.
'''
        elif self.strategy == '1shot':
            assert examples is not None, "Examples must be provided for 1shot strategy"
            example = examples[0]
            prompt = f'''
Translate {src_lang} to {lang}.

Here is an example of how to translate code from {src_lang} to {lang}:
------------Example------------
```{src_lang}
{example[0]}
```
Its translation in {lang} is:
```{lang}
{example[1]}
```
-------------------------------
Now, translate the following {src_lang} code to {lang}:
'''
        elif self.strategy == '4shot':
            assert examples is not None and len(examples) == 4, "Examples must be provided for 4shot strategy with exactly 4 examples"
            prompt = f'''
Translate {src_lang} to {lang}.
Here are some examples of how to translate code from {src_lang} to {lang}:
------------Example 1------------
```{src_lang}
{examples[0][0]}
```
Its translation in {lang} is:
```{lang}
{examples[0][1]}
```
------------Example 2------------
```{src_lang}
{examples[1][0]}
```
Its translation in {lang} is:
```{lang}
{examples[1][1]}
```
------------Example 3------------
```{src_lang}
{examples[2][0]}
```
Its translation in {lang} is:
```{lang}
{examples[2][1]}
```
------------Example 4------------
```{src_lang}
{examples[3][0]}
```
Its translation in {lang} is:
```{lang}
{examples[3][1]}
```
---------------------------------
Now, translate the following {src_lang} code to {lang}:
'''

        else:
            raise ValueError(f"Unknown strategy: {self.strategy}")
        prompt += f'''
```
{code}
```
'''
        prompt += '''
In the code block, DO NOT add any additional comments, example code or annotations.
Make sure the output is **in a code block**.
'''
        if lang == 'java':
            prompt = prompt + '''
Please translate into `static` function. No class to wrap the function, no functions other than the translated function.
'''
        print_red(prompt)
        messages = []
        messages.append({
            "role": "user",
            "content": prompt,
        })
        while True:
            try:
                if self.model_name == 'llama' or self.model_name == 'qwen3':
                    resp = completion(
                        model=self.model_long_name,
                        api_base="http://localhost:8000/v1",
                        api_key="test",
                        messages=messages,
                        temperature=0.0,
                    )
                elif self.model_name == 'o3':
                    resp = completion(
                        model=self.model_long_name,
                        messages=messages,
                        # temperature=0.0
                    )
                else:
                    resp = completion(
                        model=self.model_long_name,
                        messages=messages,
                        temperature=0.0
                    )
                answer = resp.choices[0].message.content
                print_green(answer)
                if answer == None or answer.strip() == "":
                    print_red("Received empty response, return empty string")
                    return ""
                answer = parse_output(answer)
                return answer
            except Exception as e:
                print(f"Error during completion: {e}")
                print("Retrying...")
                import time
                time.sleep(1)  # wait for a second before retrying


def decode(code, lang):
    if lang == 'python':
        return pyprocessor.detokenize_code(code)
    else:
        return jprocessor.detokenize_code(code)

def encode(code, lang):
    if lang == 'python':
        return ' '.join(pyprocessor.tokenize_code(code))
    else:
        return ' '.join(jprocessor.tokenize_code(code))


def transform_java_code(model, java, i, operand_swap, operand_transform, if_else_transform, for_while_transform, stat):
    # print('Starting transformation...')
    # First check if the Java code generated by the model before attack can pass test cases, if so, skip directly
    if model.strategy == '1shot':
        examples = model.random_pick_example(count=1)
    elif model.strategy == '4shot':
        examples = model.random_pick_example(count=4)
    else:
        examples = None
    code = model.predict(java, examples=examples)
    program = pyprocessor.detokenize_code(code)
    try:
        state = check_python_code_testcases(program, 'test_cases_templates/' + str(i) + '.txt')
    except:
        state = False
    if state == False:
        code, meta = "", "fail"
        print('Original generated code cannot pass test cases, returning directly')
        return code, meta
    stat[0] += 1 # direct success
    # If cannot pass, perform syntax transformation
    code, meta = '', ''
    decode_code = decode(java.replace("Translate Java to Python: ", ""), 'java')
    temp_code_list = []
    operand_swap_code, operand_swap_meta = operand_swap.transform_code(decode_code)
    operand_transform_code, operand_transform_meta = operand_transform.transform_code(decode_code)
    if_else_code, if_else_meta = if_else_transform.transform_code(decode_code)
    for_while_code, for_while_meta = for_while_transform.transform_code(decode_code)
    if operand_swap_meta['success']==True :
        # operand_swap operation successful
        temp_code_list.append(operand_swap_code)
        operand_swap_transform_code, operand_swap_transform_meta = operand_transform.transform_code(operand_swap_code)
        operand_swap_if_else_code, operand_swap_if_else_meta = if_else_transform.transform_code(operand_swap_code)
        operand_swap_for_while_code, operand_swap_for_while_meta = for_while_transform.transform_code(operand_swap_code)
        if operand_swap_transform_meta['success'] == True:
            temp_code_list.append(operand_swap_transform_code)
            operand_swap_transform_if_else_code, operand_swap_transform_if_else_meta = if_else_transform.transform_code(
                operand_swap_transform_code)
            operand_swap_transform_for_while_code, operand_swap_transform_for_while_meta = for_while_transform.transform_code(
                operand_swap_transform_code)
            if operand_swap_transform_if_else_meta['success'] == True:
                temp_code_list.append(operand_swap_transform_if_else_code)
                operand_swap_transform_if_else_for_while_code, operand_swap_transform_if_else_for_while_meta = for_while_transform.transform_code(
                    operand_swap_transform_if_else_code)
                if operand_swap_transform_if_else_for_while_meta['success'] == True:
                    temp_code_list.append(operand_swap_transform_if_else_for_while_code)
            if operand_swap_transform_for_while_meta['success'] == True:
                temp_code_list.append(operand_swap_transform_for_while_code)
        if operand_swap_if_else_meta['success'] == True:
            temp_code_list.append(operand_swap_if_else_code)
            operand_swap_if_else_for_while_code, operand_swap_if_else_for_while_meta = for_while_transform.transform_code(
                operand_swap_if_else_code)
            if operand_swap_if_else_for_while_meta['success'] == True:
                temp_code_list.append(operand_swap_if_else_for_while_code)
        if operand_swap_for_while_meta['success'] == True:
            temp_code_list.append(operand_swap_for_while_code)

    if operand_transform_meta['success']==True:
        temp_code_list.append(operand_transform_code)
        operand_transform_if_else_code, operand_transform_if_else_meta = if_else_transform.transform_code(operand_transform_code)
        operand_transform_for_while_code, operand_transform_for_while_meta = for_while_transform.transform_code(operand_transform_code)
        if operand_transform_if_else_meta['success'] == True:
            temp_code_list.append(operand_transform_if_else_code)
            operand_transform_if_else_for_while_code, operand_transform_if_else_for_while_meta = for_while_transform.transform_code(
                operand_transform_if_else_code)
            if operand_transform_if_else_for_while_meta['success'] == True:
                temp_code_list.append(operand_transform_if_else_for_while_code)
        if operand_transform_for_while_meta['success'] == True:
            temp_code_list.append(operand_transform_for_while_code)

    if if_else_meta['success']==True:
        # if-else operation successful
        temp_code_list.append(if_else_code)
        if_else_for_while_code, if_else_for_while_meta = for_while_transform.transform_code(if_else_code)
        if if_else_for_while_meta['success'] == True:
            temp_code_list.append(if_else_for_while_code)

    if for_while_meta['success']==True:
        # for-while operation successful
        temp_code_list.append(for_while_code)

    # If the number of successful syntax transformations is 0, return directly
    if len(temp_code_list) == 0:
        code, meta = "", "fail"
        print('Number of successful syntax transformations is 0, returning directly')
        stat[1] += 1 # operand_success
        return code, meta
    # Remove duplicates
    temp_code_list = list(set(temp_code_list))
    print('Number of successful syntax transformations: ', str(len(temp_code_list)))

    # Generate models for successfully transformed code
    temp_python_code_list = [model.predict("Translate Java to Python: " + encode(program, 'java'), examples=examples) for program in temp_code_list]
    # Attack through iteration to find code that can break semantics
    for index in range(len(temp_python_code_list)):
        program = pyprocessor.detokenize_code(temp_python_code_list[index])
        try:
            state = check_python_code_testcases(program, 'test_cases_templates/' + str(i) + '.txt')
        except:
            state = False
        # If semantics are broken, return adversarial sample
        if (state == False):
            code, meta = "Translate Java to Python: " + encode(temp_code_list[index], 'java'), "success"
            stat[2] += 1 # operand_fail
            # print('Attack successful, returning directly')
            return code, meta
        else:
            # print('Attack failed... continue attacking')
            code, meta = "", "fail"
    stat[1] += 1 # operand_success
    return code, meta

def remove_java_comments(code):
    code = re.sub(r'//.*', '', code)
    return code



def transform_python_code(model, python, i, operand_swap, operand_transform, if_else_transform, for_while_transform, stat):
    print('Starting transformation...')
    # First check if the Java code generated by the model before attack can pass test cases, if so, skip directly
    if model.strategy == '1shot':
        examples = model.random_pick_example(count=1)
    elif model.strategy == '4shot':
        examples = model.random_pick_example(count=4)
    else:
        examples = None
    code = model.predict(python, lang='java', examples=examples)
    code = remove_java_comments(code)
    program = jprocessor.detokenize_code(code)
    try:
        state = check_java_code_testcases(program, 'Main_'+str(i)+'.txt')
    except:
        state = False
    if state != True:
        code, meta = "", "fail"
        print('Original generated code cannot pass test cases, returning directly')
        return code, meta
    stat[0] += 1 # direct success
    # If cannot pass, perform syntax transformation
    code, meta = '', ''
    decode_code = decode(python.replace("Translate Python to Java: ", ""), 'python')
    temp_code_list = []
    operand_swap_code, operand_swap_meta = operand_swap.transform_code(decode_code)
    operand_transform_code, operand_transform_meta = operand_transform.transform_code(decode_code)
    if_else_code, if_else_meta = if_else_transform.transform_code(decode_code)
    for_while_code, for_while_meta = for_while_transform.transform_code(decode_code)
    if operand_swap_meta['success'] == True:
        # operand_swap operation successful
        temp_code_list.append(operand_swap_code)
        operand_swap_transform_code, operand_swap_transform_meta = operand_transform.transform_code(operand_swap_code)
        operand_swap_if_else_code, operand_swap_if_else_meta = if_else_transform.transform_code(operand_swap_code)
        operand_swap_for_while_code, operand_swap_for_while_meta = for_while_transform.transform_code(operand_swap_code)
        if operand_swap_transform_meta['success'] == True:
            temp_code_list.append(operand_swap_transform_code)
            operand_swap_transform_if_else_code, operand_swap_transform_if_else_meta = if_else_transform.transform_code(
                operand_swap_transform_code)
            operand_swap_transform_for_while_code, operand_swap_transform_for_while_meta = for_while_transform.transform_code(
                operand_swap_transform_code)
            if operand_swap_transform_if_else_meta['success'] == True:
                temp_code_list.append(operand_swap_transform_if_else_code)
                operand_swap_transform_if_else_for_while_code, operand_swap_transform_if_else_for_while_meta = for_while_transform.transform_code(
                    operand_swap_transform_if_else_code)
                if operand_swap_transform_if_else_for_while_meta['success'] == True:
                    temp_code_list.append(operand_swap_transform_if_else_for_while_code)
            if operand_swap_transform_for_while_meta['success'] == True:
                temp_code_list.append(operand_swap_transform_for_while_code)
        if operand_swap_if_else_meta['success'] == True:
            temp_code_list.append(operand_swap_if_else_code)
            operand_swap_if_else_for_while_code, operand_swap_if_else_for_while_meta = for_while_transform.transform_code(
                operand_swap_if_else_code)
            if operand_swap_if_else_for_while_meta['success'] == True:
                temp_code_list.append(operand_swap_if_else_for_while_code)
        if operand_swap_for_while_meta['success'] == True:
            temp_code_list.append(operand_swap_for_while_code)

    if operand_transform_meta['success'] == True:
        temp_code_list.append(operand_transform_code)
        operand_transform_if_else_code, operand_transform_if_else_meta = if_else_transform.transform_code(
            operand_transform_code)
        operand_transform_for_while_code, operand_transform_for_while_meta = for_while_transform.transform_code(
            operand_transform_code)
        if operand_transform_if_else_meta['success'] == True:
            temp_code_list.append(operand_transform_if_else_code)
            operand_transform_if_else_for_while_code, operand_transform_if_else_for_while_meta = for_while_transform.transform_code(
                operand_transform_if_else_code)
            if operand_transform_if_else_for_while_meta['success'] == True:
                temp_code_list.append(operand_transform_if_else_for_while_code)
        if operand_transform_for_while_meta['success'] == True:
            temp_code_list.append(operand_transform_for_while_code)

    if if_else_meta['success'] == True:
        # if-else operation successful
        temp_code_list.append(if_else_code)
        if_else_for_while_code, if_else_for_while_meta = for_while_transform.transform_code(if_else_code)
        if if_else_for_while_meta['success'] == True:
            temp_code_list.append(if_else_for_while_code)

    if for_while_meta['success'] == True:
        # for-while operation successful
        temp_code_list.append(for_while_code)

    # If the number of successful syntax transformations is 0, return directly
    if len(temp_code_list) == 0:
        code, meta = "", "fail"
        print('Number of successful syntax transformations is 0, returning directly')
        stat[1] += 1 # operand_success
        return code, meta
    # Remove duplicates
    temp_code_list = list(set(temp_code_list))
    print('Number of successful syntax transformations: ', str(len(temp_code_list)))

    # Generate models for successfully transformed code
    temp_python_code_list = [remove_java_comments(model.predict("Translate Python to Java: " + encode(program, 'python'), lang='java', examples=examples)) for program in
                             temp_code_list]
    # Attack through iteration to find code that can break semantics
    for index in range(len(temp_python_code_list)):
        program = jprocessor.detokenize_code(temp_python_code_list[index])
        try:
            state = check_java_code_testcases(program, 'Main_' + str(i) + '.txt')
        except:
            state = False
        # If semantics are broken, return adversarial sample
        if (state != True):
            code, meta = "Translate Python to Java: " + encode(temp_code_list[index], 'python'), "success"
            stat[2] += 1 # operand_fail
            print('Attack successful, returning directly')
            return code, meta
        else:
            print('Attack failed... continue attacking')
            code, meta = "", "fail"
    stat[1] += 1 # operand_success
    return code, meta

def generate_dataset_attack(model_dict, model_type, task, strategy='direct'):
    model = None
    if model_type == 'codet5' or model_type == 'plbart' or model_type == 'natgen':
        model = Encoder_Decoder(model_type=model_type, model_name_or_path=model_dict[model_type], beam_size=10,
                      max_source_length=350, max_target_length=350,
                      load_model_path='models/original/valid_output_'+task+'/'+model_type+'/checkpoint-best-bleu/pytorch_model.bin')
    if model_type == 'unixcoder':
        model = UniXcoder(model_type=model_type, model_name_or_path=model_dict[model_type], beam_size=10,
                          max_source_length=350, max_target_length=350,
                          load_model_path='models/original/valid_output_' + task + '/' + model_type + '/checkpoint-best-bleu/pytorch_model.bin')
    if 'codegpt' in model_type or 'codegen' in model_type:
        model = Decoder_Model(model_type=model_type, model_name_or_path=model_dict[model_type], beam_size=10,
                              block_size=700,
                              max_source_length=350, max_target_length=700,
                              load_model_path='models/original/valid_output_' + task + '/' + model_type + '/checkpoint-best-bleu/pytorch_model.bin')
    if 'codebert' in model_type or 'contrabert' in model_type:
        model = Encoder_Model(model_type=model_type, model_name_or_path=model_dict[model_type], beam_size=10,
                              max_source_length=350, max_target_length=350,
                              load_model_path='models/original/valid_output_' + task + '/' + model_type + '/checkpoint-best-bleu/pytorch_model.bin')
    if model is None:
        model = LLM(model_type, task, strategy=strategy)
    count = 0
    # skip if the file already exists
    if expand_python:
        if os.path.exists("dataset_attack/syntax_"+task+"_"+model_type+"_"+strategy+"_expand_python.csv"):
            print(f"File dataset_attack/syntax_{task}_{model_type}_{strategy}_expand_python.csv already exists, skipping...")
            return count
    else:
        if os.path.exists("dataset_attack/syntax_"+task+"_"+model_type+"_"+strategy+".csv"):
            print(f"File dataset_attack/syntax_{task}_{model_type}_{strategy}.csv already exists, skipping...")
            return count

    if task == 'j2p':
        # a>b --> b<a
        operand_swap = OperandSwap(
            f'{curr_script_dir}/evaluation/CodeBLEU/parser/my-languages.so', 'java'
        )
        # a+=b --> a=a+b
        operand_transform = ConfusionRemover(
            f'{curr_script_dir}/evaluation/CodeBLEU/parser/my-languages.so', 'java'
        )
        if_else_transform = BlockSwap(
            f'{curr_script_dir}/evaluation/CodeBLEU/parser/my-languages.so', 'java'
        )
        for_while_transform = ForWhileTransformer(
            f'{curr_script_dir}/evaluation/CodeBLEU/parser/my-languages.so', 'java'
        )
        df = pd.read_csv('dataset/test_j2p.csv')
        tgts = df['tgt'].tolist()
        srcs = df['src'].tolist()
        new_datas = []
        stat_list = []
        for i in tqdm(range(len(srcs))):
            stat = [0, 0, 0] # direct success, operand_success, operand_fail
            # Perform syntax transformation and semantic attack on src code
            code, meta = transform_java_code(model, srcs[i], i, operand_swap, operand_transform, if_else_transform, for_while_transform, stat)
            print(f'direct_success: {stat[0]}, operand_success: {stat[1]}, operand_fail: {stat[2]}')
            stat_list.append(stat)
            code = re.sub("[ \t\n]+", " ", code)
            # If attack is successful, write the attacked src code and original tgt code to file together
            if meta=='success':
                count += 1
                new_datas.append([code, tgts[i]])
            # If attack fails, write the original src code and original tgt code to file together
            else:
                new_datas.append([srcs[i], tgts[i]])

        df = pd.DataFrame(new_datas, columns=['src', 'tgt'])
        df.to_csv("dataset_attack/syntax_"+task+"_"+model_type+"_"+strategy+".csv", index=False)
        df_stat = pd.DataFrame(stat_list, columns=['direct_success', 'operand_success', 'operand_fail'])
        df_stat.to_csv("dataset_attack/syntax_"+task+"_"+model_type+"_"+strategy+"_stat.csv", index=False)
        return count
    if task == 'p2j':
        # a>b --> b<a
        operand_swap = OperandSwap(
            f'{curr_script_dir}/evaluation/CodeBLEU/parser/my-languages.so', 'python'
        )
        # a+=b --> a=a+b
        operand_transform = ConfusionRemover(
            f'{curr_script_dir}/evaluation/CodeBLEU/parser/my-languages.so', 'python'
        )
        if_else_transform = BlockSwap(
            f'{curr_script_dir}/evaluation/CodeBLEU/parser/my-languages.so', 'python'
        )
        for_while_transform = ForWhileTransformer(
            f'{curr_script_dir}/evaluation/CodeBLEU/parser/my-languages.so', 'python'
        )
        df = pd.read_csv('dataset/test_p2j.csv')
        tgts = df['tgt'].tolist()
        srcs = df['src'].tolist()
        new_datas = []
        stat_list = []
        for i in tqdm(range(len(srcs))):
            stat = [0, 0, 0] # direct success, operand_success, operand_fail
            # Perform syntax transformation and semantic attack on src code
            code, meta = transform_python_code(model, srcs[i], i, operand_swap, operand_transform, if_else_transform, for_while_transform, stat)
            # print stat
            print(f'direct_success: {stat[0]}, operand_success: {stat[1]}, operand_fail: {stat[2]}')
            stat_list.append(stat)
            code = re.sub("[ \t\n]+", " ", code)
            # If attack is successful, write the attacked src code and original tgt code to file together
            if meta=='success':
                count += 1
                new_datas.append([code, tgts[i]])
            # If attack fails, write the original src code and original tgt code to file together
            else:
                new_datas.append([srcs[i], tgts[i]])

        df = pd.DataFrame(new_datas, columns=['src', 'tgt'])
        if expand_python:
            df.to_csv("dataset_attack/syntax_"+task+"_"+model_type+"_"+strategy+"_expand_python.csv", index=False)
        else:
            df.to_csv("dataset_attack/syntax_"+task+"_"+model_type+"_"+strategy+".csv", index=False)
        df_stat = pd.DataFrame(stat_list, columns=['direct_success', 'operand_success', 'operand_fail'])
        if expand_python:
            df_stat.to_csv("dataset_attack/syntax_"+task+"_"+model_type+"_"+strategy+"_expand_python_stat.csv", index=False)
        else:
            df_stat.to_csv("dataset_attack/syntax_"+task+"_"+model_type+"_"+strategy+"_stat.csv", index=False)
        return count

if __name__ == '__main__':
    model_dict = {
        'codet5': '/home/original_author/models/codet5-base',
        'plbart': '/home/original_author/models/plbart-base',
        'natgen': '/home/original_author/models/NatGen',
        'unixcoder': '/home/original_author/models/unixcoder-base',
        'codegpt-java': '/home/original_author/models/CodeGPT-small-java',
        'codegpt-py': '/home/original_author/models/CodeGPT-small-py',
        'codegpt-adapter-java': '/home/original_author/models/CodeGPT-small-java-adaptedGPT2',
        'codegpt-adapter-py': '/home/original_author/models/CodeGPT-small-py-adaptedGPT2',
        'codebert': '/home/original_author/models/codebert-base',
        'graphcodebert': '/home/original_author/models/graphcodebert-base',
        'contrabert': '/home/original_author/models/ContraBERT_G',
        'codegen': '/home/original_author/models/codegen-350M-multi'
    }
    set_seed(1234)

    # task = 'j2p'
    # # for model_type in ['codet5', 'plbart', 'natgen', 'unixcoder', 'codegpt-py', 'codegpt-adapter-py', 'codebert', 'graphcodebert']:
    # for model_type in ['codebert', 'graphcodebert', 'contrabert']:
    #     generate_dataset_attack(model_dict, model_type, task)

    result_dict = {}
    task = sys.argv[1]  # 'j2p' or 'p2j'
    if task not in ['j2p', 'p2j']:
        raise ValueError("Task must be either 'j2p' or 'p2j'")
    model_type = sys.argv[2]  # e.g., 'codet5', 'plbart', etc.
    strategy = sys.argv[3] if len(sys.argv) > 3 else 'direct'  # default to 'direct'
    if len(sys.argv) > 4 and sys.argv[4] == 'expand_python':
        expand_python = True
    if expand_python and task != 'p2j':
        raise ValueError("expand_python can only be used with task 'p2j'")
    print('-'*80)
    print(f"Generating dataset attack for task: {task}, model_type: {model_type}, strategy: {strategy}, expand_python: {expand_python}")
    print('-'*80)
    count = generate_dataset_attack(model_dict, model_type, task, strategy=strategy)
    result_dict[model_type] = count
    # for model_type in ['natgen', 'codet5', 'plbart', 'unixcoder', 'codebert', 'graphcodebert', 'contrabert', 'codegpt-java', 'codegpt-adapter-java', 'codegen']:
    #     count = generate_dataset_attack(model_dict, model_type, task)
    #     result_dict[model_type] = count
    # for model_type in ['codet5', 'plbart', 'natgen', 'unixcoder', 'codegpt-java', 'codegpt-adapter-java', 'codegen', 'codebert', 'graphcodebert', 'contrabert']:
    #     generate_dataset_attack(model_dict, model_type, task)
    df = pd.DataFrame([result_dict])
    df.to_csv("p2j_syntax.csv", index=False)