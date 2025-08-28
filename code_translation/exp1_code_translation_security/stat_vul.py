import sys
import json

file = sys.argv[1]
# Determine question number by scanning the filename
for i in range(1, 6):
    tag = f'Q{i}'
    full_tag = f'Question{i}'
    if tag in file or full_tag in file:
        question_number = tag
        print(f"Question number determined: {question_number}")
        break
else:
    raise ValueError(f"Could not determine question number from '{file}'")

with open(file, 'r') as f:
    data = json.load(f)

vulnerable_count = 0
total_count = len(data)
for k, v in data.items():
    if v['is_vulnerable']:
        vulnerable_count += 1
vulnerable_percentage = (vulnerable_count / total_count) * 100 if total_count > 0 else 0
print(f"Total: {total_count}, Vulnerable: {vulnerable_count}, Percentage: {vulnerable_percentage:.2f}%")

# load groud truth file
path = './security_classifications/'
ground_truth_file = f'{path}{question_number}.json'
with open(ground_truth_file, 'r') as f:
    ground_truth = json.load(f)

vulnerable_count_gt = 0
total_count_gt = 0
for k, v in ground_truth.items():
    if v['correctness'] == 'correct':
        total_count_gt += 1
        if v['security'] != 'secure':
            vulnerable_count_gt += 1

vulnerable_percentage_gt = (vulnerable_count_gt / total_count_gt) * 100 if total_count_gt > 0 else 0
print(f"Ground Truth - Total: {total_count_gt}, Vulnerable: {vulnerable_count_gt}, Percentage: {vulnerable_percentage_gt:.2f}%")

# load llm truth file
path = './security_classifications_llm/'
llm_truth_file = f'{path}{question_number}.json'
with open(llm_truth_file, 'r') as f:
    llm_truth = json.load(f)
vulnerable_count_llm = 0
total_count_llm = len(llm_truth)
for k, v in llm_truth.items():
    if v['is_vulnerable']:
        vulnerable_count_llm += 1
vulnerable_percentage_llm = (vulnerable_count_llm / total_count_llm) * 100 if total_count_llm > 0 else 0
print(f"LLM - Total: {total_count_llm}, Vulnerable: {vulnerable_count_llm}, Percentage: {vulnerable_percentage_llm:.2f}%")
