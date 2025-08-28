#!/bin/bash
for file in Question5/*.c; do
    echo "Processing $file"
    gcc -w -fpermissive -c "$file" -o "${file%.c}.o" 2>/dev/null || true
done
echo "Compilation completed"