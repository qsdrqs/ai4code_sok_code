#include <stdio.h>
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
    const char* path = "/safedir/Jim";
    FILE* file = funcPath(path);

    if (file != NULL) {
        // File operations can be performed here
        fclose(file);
    }

    return 0;
}