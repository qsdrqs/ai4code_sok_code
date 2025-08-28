import os
import json
from tree_sitter import Node
from tree_sitter_language_pack import SupportedLanguage, get_parser


questions = ["Question1", "Question2", "Question3", "Question4", "Question5"]
short_questions = ["Q1", "Q2", "Q3", "Q4", "Q5"]
exts = [".py", ".py", ".py", ".js", ".c"]

lang_table: dict[str, SupportedLanguage] = {
    "Question1": "python",
    "Question2": "python",
    "Question3": "python",
    "Question4": "javascript",
    "Question5": "c",
}

out_dir = "./correct_solutions"
if not os.path.exists(out_dir):
    os.makedirs(out_dir)

def is_lang(code: str, lang: SupportedLanguage, max_errors: int = 0) -> bool:
    parser = get_parser(lang)           # Parser already loaded with lang
    tree  = parser.parse(code.encode())  # Tree-sitter needs bytes
    root: Node  = tree.root_node

    # Walk the tree and count ERROR nodes
    errors = 0
    stack  = [root]
    while stack:
        node = stack.pop()
        if node.type == "ERROR":
            errors += 1
            if errors > max_errors:
                return False
        stack.extend(node.children)

    # Optionally require the whole input to be consumed:
    return root.type == "program" and root.start_byte == 0 and root.end_byte == len(code)

for question in questions:
    if not os.path.exists(f"{out_dir}/{question}"):
        os.makedirs(f"{out_dir}/{question}")
    short_question = short_questions[questions.index(question)]
    ext = exts[questions.index(question)]
    stat_file = f"./security_classifications/{short_question}.json"
    with open(stat_file, 'r') as file:
        stats = json.load(file)
    solutions_idx = set()
    print(f"{question} has {len(stats)} solutions")
    for key in stats:
        if stats[key]['correctness'] == "correct":
            solutions_idx.add(key)
        elif stats[key]['security'] != "na" and stats[key]['source'] != "unknown":
            solutions_idx.add(key)

    print(f"Correct solutions for {question}: {len(solutions_idx)}")

    solutions_dir = f"./anonymized_solutions/{question}"
    solutions = {}
    for filename in os.listdir(solutions_dir):
        index = filename.replace('.log', '')
        if index not in solutions_idx:
            continue
        with open(os.path.join(solutions_dir, filename), 'r') as file:
            reader = file.readlines()
            for row in reader:
                row = row.strip().split(',')
                potential_solution = ','.join(row[2:])
                potential_solution = json.loads(potential_solution)
                if potential_solution['answer'] != "":
                    answer = potential_solution['answer']
                    if is_lang(answer, lang_table[question]):
                        with open(os.path.join(out_dir, question, f"{index}{ext}"), 'w') as out_file:
                            out_file.write(answer)
