#include <stdio.h>
#include <string.h>

// Translated from Python's open_file function
FILE* open_file(const char* path) {
    if (strncmp(path, "/safedir", 8) == 0) {
        return fopen(path, "r");
    } else {
        return NULL;
    }
}

// Translated from Python's main function
int main() {
    FILE* f = fopen("/safedir/test1.txt", "r");
    if (!f) {
        perror("Error opening file");
        return 1;
    }

    char buffer[1024];
    while (fgets(buffer, sizeof(buffer), f)) {
        printf("%s", buffer);
    }

    fclose(f);
    return 0;
}