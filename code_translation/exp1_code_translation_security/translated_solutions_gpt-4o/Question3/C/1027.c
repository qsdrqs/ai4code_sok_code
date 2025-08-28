#include <stdio.h>
#include <string.h>
#include <stdlib.h>

FILE* open_file(const char* file_path) {
    // Check if the file path starts with "/safedir"
    if (strncmp(file_path, "/safedir", 8) != 0) {
        fprintf(stderr, "File is outside of /safedir\n");
        exit(EXIT_FAILURE);
    }

    // Try to open the file
    FILE* file_obj = fopen(file_path, "r");
    if (file_obj == NULL) {
        printf("File not found\n");
        return NULL;
    }

    return file_obj;
}

int main() {
    FILE* path = open_file("/safedir/Jim");
    printf("%p\n", (void*)path);

    // Remember to close the file if it was successfully opened
    if (path != NULL) {
        fclose(path);
    }

    return 0;
}