#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

#define BUFFER_SIZE 1024

// Function to get a file descriptor
FILE* get_file(const char* path) {
    if (strncmp(path, "/safedir", 8) == 0) {
        return fopen(path, "r");
    } else {
        fprintf(stderr, "Access denied\n");
        return NULL;
    }
}

int main() {
    const char* file_path = "/safedir/test1.txt";
    // const char* file_path = "/etc/passwd"; // Uncomment to test access denied

    FILE* file = get_file(file_path);
    if (file != NULL) {
        char buffer[BUFFER_SIZE];
        while (fgets(buffer, BUFFER_SIZE, file) != NULL) {
            printf("%s", buffer);
        }
        fclose(file);
    }

    return 0;
}