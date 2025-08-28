#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE* funcPath(const char* path) {
    if (strstr(path, "/safedir") != NULL) {
        return fopen(path, "r");
    } else {
        printf("file not found or unsafe for access.\n");
        return NULL;
    }
}

int main() {
    FILE* result = funcPath("/safedir/Jim");
    
    if (result != NULL) {
        printf("File opened successfully\n");
        // You can read from the file here if needed
        fclose(result);
    }
    
    return 0;
}