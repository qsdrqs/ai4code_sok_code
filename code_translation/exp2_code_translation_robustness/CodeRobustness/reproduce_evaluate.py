from evaluator.bleu import _bleu
import calc_code_bleu
import sys
import tempfile

if len(sys.argv) != 3:
    print("Usage: python reproduce_evaluate.py <gold_file> <output_file>")
    sys.exit(1)

gold_file = sys.argv[1]
output_file = sys.argv[2]

def shrink_file(gold_fn_file, output_fn_file):
    with open(gold_fn_file, 'r') as f:
        gold_lines = f.readlines()
    with open(output_fn_file, 'r') as f:
        output_lines = f.readlines()
    if len(gold_lines) > len(output_lines):
        # output only has a subset of gold functions
        gold_lines = gold_lines[:len(output_lines)]
    with tempfile.NamedTemporaryFile(prefix='coderobustness', delete=False, mode='w') as temp_gold_file:
        temp_gold_file.writelines(gold_lines)
        temp_gold_file.flush()
    return temp_gold_file.name

def get_bleu(gold_fn_file, output_fn_file):
    gold_fn_file = shrink_file(gold_fn_file, output_fn_file)
    bleu = round(_bleu(gold_fn_file, output_fn_file), 2)
    return bleu


def get_code_bleu(gold_fn_file, output_fn_file):
    lang = 'c_sharp'
    if gold_fn_file.endswith('.java'):
        lang = 'java'
    gold_fn_file = shrink_file(gold_fn_file, output_fn_file)
    codebleu = calc_code_bleu.get_codebleu(gold_fn_file, output_fn_file, lang)
    return codebleu * 100


def get_soft_em(gold_fn, output_fn):
    gold_lines = gold_fn.strip().splitlines()
    out_lines = output_fn.strip().splitlines()

    total_lines = len(gold_lines)

    total_ratio = 0.0
    for idx, gold_line in enumerate(gold_lines):
        gold_line = gold_line.strip()
        out_line = out_lines[idx].strip() if idx < len(out_lines) else ""
        L = len(gold_line)
        if L == 0:
            ratio = 1.0 if out_line == "" else 0.0
        else:
            matches = sum(1 for i, ch in enumerate(gold_line) if i < len(out_line) and out_line[i] == ch)
            ratio = matches / L
        total_ratio += ratio

    avg_ratio = total_ratio / total_lines
    return avg_ratio * 100.0


def get_em(gold_fn, output_fn):
    return 100.0 if gold_fn.strip() == output_fn.strip() else 0.0

def main():
    with open(gold_file, 'r') as f:
        gold_fns = f.readlines()

    with open(output_file, 'r') as f:
        output_fns = f.readlines()

    gold_fns = [x.strip() for x in gold_fns]
    output_fns = [x.strip() for x in output_fns]

    assert len(gold_fns) >= len(output_fns), "The number of gold files and output files must be the same."
    if len(output_fns) < len(gold_fns):
        # output only has a subset of gold functions
        gold_fns = gold_fns[:len(output_fns)]


    total_em = 0.0
    total_soft_em = 0.0
    for i in range(len(gold_fns)):
        gold_fn = gold_fns[i]
        output_fn = output_fns[i]

        total_em += get_em(gold_fn, output_fn)
        total_soft_em += get_soft_em(gold_fn, output_fn)

    avg_em = total_em / len(gold_fns)
    avg_soft_em = total_soft_em / len(gold_fns)

    avg_bleu = get_bleu(gold_file, output_file)
    avg_code_bleu = get_code_bleu(gold_file, output_file)

    print(f"Average BLEU: {avg_bleu}")
    print(f"Average CodeBLEU: {avg_code_bleu}")
    print(f"Average EM: {avg_em}")
    print(f"Average Soft-EM: {avg_soft_em}")


if __name__ == '__main__':
    main()
