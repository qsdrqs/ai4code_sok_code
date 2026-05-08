#include <stdio.h>
#include <string.h>

FILE* getFileObject(const char* inputPath) {
    char firstPart[10];  // 9 characters + null terminator
    strncpy(firstPart, inputPath, 9);
    firstPart[9] = '\0';
    
    if (strcmp(firstPart, "/safedir/") == 0) {
        return fopen(inputPath, "r");
    }
    
    return NULL;
}

void pyMain(void) {
    getFileObject("/safedir/test.txt");
}

int main(void) {
    pyMain();
    return 0;
}