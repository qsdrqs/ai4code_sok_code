#include <stdio.h>
#include <string.h>

#define ALLOWED_PATH "/safedir"

FILE* getFile(const char* path) {
    // Check if the path starts with ALLOWED_PATH
    if (strncmp(path, ALLOWED_PATH, strlen(ALLOWED_PATH)) != 0) {
        printf("Not allowed to get file from this dir\n");
        return NULL;
    }
    // Open the file in read mode
    FILE* file = fopen(path, "r");
    if (file == NULL) {
        printf("Failed to open the file\n");
    }
    return file;
}

int main() {
    const char* path = "/safedir/test/path";
    FILE* file = getFile(path);
    if (file != NULL) {
        // Do something with the file
        fclose(file);
    }
    return 0;
}