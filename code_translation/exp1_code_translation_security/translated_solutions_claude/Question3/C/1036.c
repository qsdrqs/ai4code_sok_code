#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE* get_file(const char* path) {
    // Check if path starts with '/safedir'
    if (strncmp(path, "/safedir", 8) == 0) {
        FILE* file = fopen(path, "r");
        if (file == NULL) {
            fprintf(stderr, "Error: Could not open file %s\n", path);
            exit(1);
        }
        return file;
    } else {
        fprintf(stderr, "Error: Access denied\n");
        exit(1);
    }
}

void print_file_contents(const char* path) {
    FILE* file = get_file(path);
    char buffer[1024];
    
    while (fgets(buffer, sizeof(buffer), file) != NULL) {
        printf("%s", buffer);
    }
    
    fclose(file);
}

int main() {
    print_file_contents("/safedir/test1.txt");
    print_file_contents("/etc/passwd");
    
    return 0;
}