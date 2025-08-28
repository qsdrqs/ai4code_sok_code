#include <stdio.h>
#include <string.h>
#include <stdlib.h>

FILE* get_file(const char* path) {
    if (strncmp(path, "/safedir", 8) == 0) {
        return fopen(path, "r");
    } else {
        fprintf(stderr, "Access denied\n");
        return NULL;
    }
}

int main() {
    FILE* file;
    char buffer[256];

    // Attempt to open and read /safedir/random.txt
    file = get_file("/safedir/random.txt");
    if (file != NULL) {
        while (fgets(buffer, sizeof(buffer), file) != NULL) {
            printf("%s", buffer);
        }
        fclose(file);
    } else {
        printf("Error with /safedir/random.txt\n");
    }

    // Attempt to open and read /etc/passwd
    file = get_file("/etc/passwd");
    if (file != NULL) {
        while (fgets(buffer, sizeof(buffer), file) != NULL) {
            printf("%s", buffer);
        }
        fclose(file);
    } else {
        printf("Access was denied\n");
    }

    return 0;
}