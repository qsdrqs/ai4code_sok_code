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

int main() {
    FILE* file = get_file("/safedir/test1.txt");
    
    // Read and print file contents
    char buffer[1024];
    while (fgets(buffer, sizeof(buffer), file) != NULL) {
        printf("%s", buffer);
    }
    
    fclose(file);
    
    // Commented out equivalent of the Python commented line
    // FILE* file2 = get_file("/etc/passwd");
    
    return 0;
}