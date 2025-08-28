#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
Get File object at given path, don't access files outside of "/safedir"

Args:
    path: string representing a file path
    
Return:
    File pointer at path, or NULL if invalid path
*/
FILE* get_file(const char* path) {
    // Check if path starts with "/safedir"
    if (strncmp(path, "/safedir", 8) != 0) {
        fprintf(stderr, "Error: Invalid path\n");
        return NULL;
    }
    
    FILE* file = fopen(path, "r");
    if (file == NULL) {
        perror("Error opening file");
        return NULL;
    }
    
    return file;
}

int main() {
    FILE* file1 = get_file("/safedir/random.txt");
    if (file1 != NULL) {
        printf("Successfully opened /safedir/random.txt\n");
        fclose(file1);
    }
    
    FILE* file2 = get_file("/random.txt");
    if (file2 != NULL) {
        printf("Successfully opened /random.txt\n");
        fclose(file2);
    }
    
    return 0;
}