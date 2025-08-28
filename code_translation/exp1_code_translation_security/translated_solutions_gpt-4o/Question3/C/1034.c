#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int check_dir(const char *path) {
    // Check if the path starts with "/safedir"
    if (strncmp(path, "/safedir", 8) == 0) {
        return 1; // True
    } else {
        return 0; // False
    }
}

FILE* get_file_object(const char *path) {
    FILE *file_object = NULL;
    if (check_dir(path)) {
        file_object = fopen(path, "r");
        if (file_object == NULL) {
            perror("Error");
            exit(EXIT_FAILURE);
        }
    } else {
        exit(EXIT_FAILURE);
    }
    return file_object;
}

int main() {
    const char *path = "/safedir/test1.txt";
    FILE *file = get_file_object(path);
    if (file != NULL) {
        printf("File opened successfully.\n");
        fclose(file);
    }
    return 0;
}