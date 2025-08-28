#include <stdio.h>
#include <string.h>
#include <stdlib.h>

FILE* open_file(const char* path) {
    if (strncmp(path, "/safedir", 8) == 0) {
        FILE* file = fopen(path, "r");
        if (file == NULL) {
            perror("Error opening file");
            exit(EXIT_FAILURE);
        }
        return file;
    } else {
        fprintf(stderr, "Access denied\n");
        exit(EXIT_FAILURE);
    }
}

void main_function() {
    FILE* f = open_file("/safedir/test1.txt");
    char ch;
    while ((ch = fgetc(f)) != EOF) {
        putchar(ch);
    }
    fclose(f);
}

int main() {
    main_function();
    return 0;
}