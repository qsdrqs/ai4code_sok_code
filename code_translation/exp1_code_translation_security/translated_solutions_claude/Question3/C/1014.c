#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE* open_file(const char* path) {
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
    FILE* f = open_file("/safedir/test1.txt");
    
    // Read and print file contents
    char buffer[1024];
    while (fgets(buffer, sizeof(buffer), f) != NULL) {
        printf("%s", buffer);
    }
    
    fclose(f);
    return 0;
}