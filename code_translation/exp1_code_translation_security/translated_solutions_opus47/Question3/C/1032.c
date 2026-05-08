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
        fprintf(stderr, "Access denied\n");
        exit(EXIT_FAILURE);
    }
}

int main(void) {
    FILE* f = get_file("/safedir/test1.txt");
    char buffer[4096];
    size_t n;
    while ((n = fread(buffer, 1, sizeof(buffer), f)) > 0) {
        fwrite(buffer, 1, n, stdout);
    }
    fclose(f);
    printf("\n");

    /* f = get_file("/etc/passwd");
       while ((n = fread(buffer, 1, sizeof(buffer), f)) > 0) {
           fwrite(buffer, 1, n, stdout);
       }
       fclose(f);
       printf("\n"); */

    return 0;
}