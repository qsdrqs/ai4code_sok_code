#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/**
 * Get FILE object at given path, don't access files outside of "/safedir"
 * 
 * @param path: string representing a file path
 * @return: FILE pointer at path
 */
FILE* get_file(char* path) {
    if (strlen(path) < 8 || strncmp(path, "/safedir", 8) != 0) {
        fprintf(stderr, "Invalid path\n");
        exit(EXIT_FAILURE);
    }
    return fopen(path, "r");
}

int main() {
    FILE* f1 = get_file("/safedir/random.txt");
    FILE* f2 = get_file("/random.txt");
    
    // Close files if they were opened successfully
    if (f1) fclose(f1);
    if (f2) fclose(f2);
    
    return 0;
}