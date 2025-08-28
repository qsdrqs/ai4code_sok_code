#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE* test(const char* path) {
    if (strstr(path, "/safedir") != NULL) {
        FILE* file = fopen(path, "r");
        if (file == NULL) {
            fprintf(stderr, "Error: Could not open file %s\n", path);
            exit(1);
        }
        return file;
    }
    fprintf(stderr, "Error: no access\n");
    exit(1);
}

int main() {
    // FILE* j = test("/junk");
    FILE* k = test("/safedir/junk");
    
    // Don't forget to close the file when done
    if (k != NULL) {
        fclose(k);
    }
    
    return 0;
}