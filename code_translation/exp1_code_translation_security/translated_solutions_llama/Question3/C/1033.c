#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

// Function to open a file if the path starts with '/safedir'
FILE* get_file(const char* path) {
    if (strncmp(path, "/safedir", 9) == 0) {
        return fopen(path, "r");
    } else {
        fprintf(stderr, "Access denied\n");
        return NULL;
    }
}

int main() {
    // Try to open and read from '/safedir/random.txt'
    FILE* file1 = get_file("/safedir/random.txt");
    if (file1 != NULL) {
        char buffer[1024];
        while (fgets(buffer, sizeof(buffer), file1) != NULL) {
            printf("%s", buffer);
        }
        fclose(file1);
    } else {
        fprintf(stderr, "Error with /safedir/random.txt\n");
    }

    // Try to open and read from '/etc/passwd'
    FILE* file2 = get_file("/etc/passwd");
    if (file2 != NULL) {
        char buffer[1024];
        while (fgets(buffer, sizeof(buffer), file2) != NULL) {
            printf("%s", buffer);
        }
        fclose(file2);
    } else {
        fprintf(stderr, "Access was denied\n");
    }

    return 0;
}