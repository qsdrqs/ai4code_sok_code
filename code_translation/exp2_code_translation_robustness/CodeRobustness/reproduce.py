from custom.attack.attack import TextualAttack, StructuralAttack
from tqdm import tqdm
import sys
import argparse

parser = argparse.ArgumentParser()
parser.add_argument('filename', type=str, help='filename of the dataset')
parser.add_argument('attack_strategy', type=str, help='attack strategy')
parser.add_argument('sub_task', type=str, help='sub task name')

# parser.add_argument('--task', type=str, default='translate',
#                     help='task name')
# parser.add_argument('--sub_task', type=str, default='java-cs',
#                     help='sub task name')
# parser.add_argument('--attack_strategy', type=str, default='funcname',
#                     help='attack strategy')
# parser.add_argument('--adversarial_strategy', type=str, default='funcname',
#                     help='adversarial strategy')

args = parser.parse_args()
args.task = 'translate'
# args.sub_task = 'java-cs'
args.adversarial_strategy = args.attack_strategy

filename = args.filename

class Example(object):
    """A single training/test example."""

    def __init__(self,
                 idx,#
                 source,
                 target='',#
                 url=None,
                 task='',
                 sub_task='',
                 ast=None,
                 raw_code=None,
                 raw_line=None,
                 ):
        self.idx = idx
        self.source = source
        self.target = target
        self.url = url
        self.task = task
        self.sub_task = sub_task
        self.ast = ast
        self.raw_code = raw_code
        self.raw_line = raw_line

def read_translate_examples(filename, data_num):
    """Read examples from filename."""
    examples = []
    assert len(filename.split(',')) == 2
    src_filename = filename.split(',')[0]
    trg_filename = filename.split(',')[1]
    idx = 0
    with open(src_filename, encoding="utf-8") as f1, open(trg_filename, encoding="utf-8") as f2:
        for line1, line2 in tqdm(zip(f1, f2),desc="Read examples"):
            src = line1.strip()
            trg = line2.strip()
            examples.append(
                Example(
                    idx=idx,
                    source=src,
                    target=trg,
                    raw_line=trg,
                )
            )
            idx += 1
            if idx == data_num:
                break
    return examples

examples = read_translate_examples(filename, -1)
print(examples[0].target if args.task=='generate' else examples[0].source)
if args.attack_strategy == 'funcname':
    attack = TextualAttack(args,examples)
else:
    attack = StructuralAttack(args,examples)
examples=attack.process_examples()
print(examples[0].target if args.task=='generate' else examples[0].source)


ext = '.java' if args.sub_task == 'java-cs' else '.cs'
with open(f'{args.sub_task}-{args.attack_strategy}{ext}', 'w', encoding='utf-8') as f:
    for example in examples:
        f.write(example.target if args.task=='generate' else example.source)
        f.write('\n')
