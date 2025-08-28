#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * create file object for path
 */
FILE* create_file_object(const char* path) {
    if (strncmp(path, "/safedir", 8) == 0) {
        return fopen(path, "r");
    } else {
        return NULL;
    }
}

int main() {
    FILE* result = create_file_object("/etc/passwd");
    if (result != NULL) {
        printf("File opened successfully\n");
        fclose(result);
    } else {
        printf("(null)\n");
    }
    
    return 0;
}