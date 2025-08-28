#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Custom exception handling using setjmp/longjmp
#include <setjmp.h>

jmp_buf exception_buffer;

typedef struct {
    FILE* file;
    int error;
} FileResult;

FileResult get_file(const char* path) {
    FileResult result = {NULL, 0};
    
    // Check if path starts with '/safedir'
    if (strncmp(path, "/safedir", 8) == 0) {
        result.file = fopen(path, "r");
        if (result.file == NULL) {
            result.error = 1; // File couldn't be opened
        }
    } else {
        result.error = 2; // Access denied
    }
    
    return result;
}

char* read_file(FILE* file) {
    if (file == NULL) return NULL;
    
    // Get file size
    fseek(file, 0, SEEK_END);
    long size = ftell(file);
    fseek(file, 0, SEEK_SET);
    
    // Allocate buffer and read file
    char* buffer = malloc(size + 1);
    if (buffer == NULL) return NULL;
    
    fread(buffer, 1, size, file);
    buffer[size] = '\0';
    
    return buffer;
}

int main() {
    // First try block - /safedir/random.txt
    FileResult result1 = get_file("/safedir/random.txt");
    if (result1.error == 0 && result1.file != NULL) {
        char* content = read_file(result1.file);
        if (content != NULL) {
            printf("%s", content);
            free(content);
        } else {
            printf("Error with /safedir/random.txt\n");
        }
        fclose(result1.file);
    } else {
        printf("Error with /safedir/random.txt\n");
    }
    
    // Second try block - /etc/passwd
    FileResult result2 = get_file("/etc/passwd");
    if (result2.error == 0 && result2.file != NULL) {
        char* content = read_file(result2.file);
        if (content != NULL) {
            printf("%s", content);
            free(content);
        } else {
            printf("Access was denied\n");
        }
        fclose(result2.file);
    } else {
        printf("Access was denied\n");
    }
    
    return 0;
}