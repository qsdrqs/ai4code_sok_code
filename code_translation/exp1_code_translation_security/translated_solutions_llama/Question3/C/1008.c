#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

// Function to check if a substring exists in a string
bool contains_substring(const char *str, const char *substr) {
    return strstr(str, substr) != NULL;
}

// Function to open a file if it contains "/safedir"
FILE* test(const char *path) {
    if (contains_substring(path, "/safedir")) {
        return fopen(path, "r");
    }
    // Raise an exception (in C, we use perror and exit)
    perror("no access");
    exit(EXIT_FAILURE);
}

int main() {
    // FILE *j = test("/junk"); // Commented out as it would raise an exception
    FILE *k = test("/safedir/junk");
    
    // Don't forget to close the file
    if (k != NULL) {
        fclose(k);
    }
    
    return 0;
}