#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE* open_file(const char* path) {
    // Check if path starts with "/safedir"
    if (strncmp(path, "/safedir", 8) == 0) {
        FILE* file = fopen(path, "r");
        if (file == NULL) {
            perror("Error opening file");
            exit(1);
        }
        return file;
    } else {
        fprintf(stderr, "Error: Path must start with /safedir\n");
        exit(1);
    }
}

int main() {
    FILE* res1 = open_file("/safedir/random.txt");
    printf("%p\n", (void*)res1);  // Print file pointer address (equivalent to printing file object)
    
    if (res1 != NULL) {
        fclose(res1);  // Clean up the file handle
    }
    
    FILE* res2 = open_file("blah");  // This will cause the program to exit with error
    
    return 0;
}