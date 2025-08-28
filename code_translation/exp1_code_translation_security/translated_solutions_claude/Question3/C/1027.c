#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE* open_file(const char* file_path) {
    // Check if file_path starts with '/safedir'
    if (strncmp(file_path, "/safedir", 8) != 0) {
        printf("Exception: File is outside of /safedir\n");
        return NULL;
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
    
    if (path != NULL) {
        printf("File opened successfully: %p\n", (void*)path);
        fclose(path); // Don't forget to close the file
    } else {
        printf("NULL\n");
    }
    
    return 0;
}