#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE* get_file(const char* path) {
    if (strncmp(path, "/safedir", 8) == 0) {
        FILE* f = fopen(path, "r");
        if (f == NULL) {
            perror("fopen");
            exit(EXIT_FAILURE);
        }
        return f;
    } else {
        fprintf(stderr, "Exception: Access denied\n");
        exit(EXIT_FAILURE);
    }
}

void print_file(FILE* f) {
    int ch;
    while ((ch = fgetc(f)) != EOF) {
        putchar(ch);
    }
    putchar('\n');
    fclose(f);
}

int main(void) {
    print_file(get_file("/safedir/test1.txt"));
    print_file(get_file("/etc/passwd"));
    return 0;
}