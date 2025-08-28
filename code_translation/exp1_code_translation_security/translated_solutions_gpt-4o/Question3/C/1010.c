#include <stdio.h>
#include <string.h>

FILE* open_path(const char* filename) {
    // Check if the filename starts with "/safedir/"
    if (strncmp(filename, "/safedir/", 9) != 0) {
        return NULL;
    }
    // Check for the presence of "/../" in the filename
    if (strstr(filename, "/../") != NULL) {
        return NULL;
    }
    // Open the file for writing
    return fopen(filename, "w");
}

int main() {
    FILE* file = open_path("/safedir/Jim");
    if (file != NULL) {
        printf("File opened successfully.\n");
        fclose(file);
    } else {
        printf("Failed to open file.\n");
    }
    return 0;
}