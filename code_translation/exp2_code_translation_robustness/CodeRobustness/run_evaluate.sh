if [[ $1 == "java" ]]; then
echo "Evaluate baseline"
python reproduce_evaluate.py data/translate/test.java-cs.txt.java gpt-4o-baseline.java
echo "-----------------------------"
echo "Evaluate function name attack"
python reproduce_evaluate.py data/translate/test.java-cs.txt.java gpt-4o-funcname.java
echo "-----------------------------"
echo "Evaluate function name attack with CoT"
python reproduce_evaluate.py data/translate/test.java-cs.txt.java gpt-4o-funcname-cot.java
echo "-----------------------------"
echo "Evaluate BFS attack"
python reproduce_evaluate.py data/translate/test.java-cs.txt.java gpt-4o-bfs.java
echo "-----------------------------"
echo "Evaluate BFS attack with CoT"
python reproduce_evaluate.py data/translate/test.java-cs.txt.java gpt-4o-bfs-cot.java
echo "-----------------------------"
echo "Evaluate DFS attack"
python reproduce_evaluate.py data/translate/test.java-cs.txt.java gpt-4o-dfs.java
echo "-----------------------------"
echo "Evaluate DFS attack with CoT"
python reproduce_evaluate.py data/translate/test.java-cs.txt.java gpt-4o-dfs-cot.java
echo "-----------------------------"
echo "Evaluate subtree attack"
python reproduce_evaluate.py data/translate/test.java-cs.txt.java gpt-4o-subtree.java
echo "-----------------------------"
echo "Evaluate subtree attack with CoT"
python reproduce_evaluate.py data/translate/test.java-cs.txt.java gpt-4o-subtree-cot.java
echo "-----------------------------"
else
echo "java to cs"
echo "Evaluate baseline"
python reproduce_evaluate.py data/translate/test.java-cs.txt.cs gpt-4o-baseline.cs
echo "-----------------------------"
echo "Evaluate function name attack"
python reproduce_evaluate.py data/translate/test.java-cs.txt.cs gpt-4o-funcname.cs
echo "-----------------------------"
echo "Evaluate function name attack with CoT"
python reproduce_evaluate.py data/translate/test.java-cs.txt.cs gpt-4o-funcname-cot.cs
echo "-----------------------------"
echo "Evaluate BFS attack"
python reproduce_evaluate.py data/translate/test.java-cs.txt.cs gpt-4o-bfs.cs
echo "-----------------------------"
echo "Evaluate BFS attack with CoT"
python reproduce_evaluate.py data/translate/test.java-cs.txt.cs gpt-4o-bfs-cot.cs
echo "-----------------------------"
echo "Evaluate DFS attack"
python reproduce_evaluate.py data/translate/test.java-cs.txt.cs gpt-4o-dfs.cs
echo "-----------------------------"
echo "Evaluate DFS attack with CoT"
python reproduce_evaluate.py data/translate/test.java-cs.txt.cs gpt-4o-dfs-cot.cs
echo "-----------------------------"
echo "Evaluate subtree attack"
python reproduce_evaluate.py data/translate/test.java-cs.txt.cs gpt-4o-subtree.cs
echo "-----------------------------"
echo "Evaluate subtree attack with CoT"
python reproduce_evaluate.py data/translate/test.java-cs.txt.cs gpt-4o-subtree-cot.cs
echo "-----------------------------"
fi
