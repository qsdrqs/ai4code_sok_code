#include <stdio.h>
#include <stdlib.h>

FILE* openFile(const char* filePath) {
    FILE* file = fopen(filePath, "r");
    return file;
}

int main() {
    const char* filePath = "/home/codex/Documents/User_Study_UI/prac.txt";
    
    // Equivalent to: with open(filePath, 'w') as f: f.write("Hello it's Melo")
    FILE* f = fopen(filePath, "w");
    if (f != NULL) {
        fprintf(f, "Hello it's Melo");
        fclose(f);
    }
    
    // Equivalent to: file = openFile(filePath)
    FILE* file = openFile(filePath);
    
    // Equivalent to: print(file.read())
    if (file != NULL) {
        int ch;
        while ((ch = fgetc(file)) != EOF) {
            putchar(ch);
        }
        printf("\n");  // print() adds a trailing newline in Python
        fclose(file);
    }
    
    return 0;
}