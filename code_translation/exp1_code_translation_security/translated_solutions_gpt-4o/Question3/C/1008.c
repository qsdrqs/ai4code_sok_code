#include <stdio.h>
#include <string.h>
#include <stdlib.h>

FILE* test(const char* path) {
    if (strstr(path, "/safedir") != NULL) {
        FILE* file = fopen(path, "r");
        if (file == NULL) {
            perror("Error opening file");
            exit(EXIT_FAILURE);
        }
        return file;
    }
    fprintf(stderr, "Exception: no access\n");
    exit(EXIT_FAILURE);
}

int main() {
    // FILE* j = test("/junk");
    FILE* k = test("/safedir/junk");
    
    // Remember to close the file if it was successfully opened
    if (k != NULL) {
        fclose(k);
    }

    return 0;
}