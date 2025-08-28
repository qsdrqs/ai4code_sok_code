#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define ALLOWED_PATH "/safedir"
#define ALLOWED_PATH_LEN 8

FILE* getFile(const char* path, char* error_msg) {
    // Check if path starts with ALLOWED_PATH
    if (strncmp(path, ALLOWED_PATH, ALLOWED_PATH_LEN) != 0) {
        strcpy(error_msg, "Not allowed to get file from this dir");
        return NULL;
    }
    
    // Clear error message on success
    error_msg[0] = '\0';
    
    // Open and return file
    return fopen(path, "r");
}

int main() {
    char error_msg[100];
    FILE* file = getFile("/safedir/test/path", error_msg);
    
    if (file == NULL) {
        if (strlen(error_msg) > 0) {
            printf("%s\n", error_msg);
        } else {
            printf("Failed to open file\n");
        }
    } else {
        printf("File opened successfully\n");
        fclose(file);
    }
    
    return 0;
}