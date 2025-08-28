#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int validateDir(const char* dir) {
    const char* prefix = "/safedir";
    return strncmp(dir, prefix, strlen(prefix)) == 0;
}

FILE* createFile(const char* dir) {
    int valid = validateDir(dir);
    if (valid == 0) {  // 0 is false in C
        return NULL;
    }
    return fopen(dir, "w");
}

int main() {
    FILE* file1 = createFile("test");
    FILE* file2 = createFile("/safedir/testAgain1.txt");
    
    if (file1 != NULL) {
        printf("File 1 created successfully\n");
        fclose(file1);
    } else {
        printf("File 1: NULL (invalid directory)\n");
    }
    
    if (file2 != NULL) {
        printf("File 2 created successfully\n");
        fclose(file2);
    } else {
        printf("File 2: NULL (failed to create)\n");
    }
    
    return 0;
}