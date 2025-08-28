#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE* get_file(const char* path) {
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

int main() {
    FILE* file1 = get_file("/safedir/test1.txt");
    char ch;
    while ((ch = fgetc(file1)) != EOF) {
        putchar(ch);
    }
    fclose(file1);

    FILE* file2 = get_file("/etc/passwd");
    while ((ch = fgetc(file2)) != EOF) {
        putchar(ch);
    }
    fclose(file2);

    return 0;
}