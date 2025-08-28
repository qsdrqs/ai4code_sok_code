#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>

// Function to convert a string to lowercase
void to_lowercase(char *str) {
    for (int i = 0; str[i]; i++) {
        str[i] = tolower(str[i]);
    }
}

// Function to trim leading and trailing whitespace from a string
void trim_whitespace(char *str) {
    int len = strlen(str);
    int start = 0;
    while (start < len && isspace(str[start])) {
        start++;
    }
    int end = len - 1;
    while (end >= 0 && isspace(str[end])) {
        end--;
    }
    if (start > 0 || end < len - 1) {
        memmove(str, str + start, end - start + 1);
        str[end - start + 1] = '\0';
    }
}

// Function to check if a substring exists in a string
int contains_substring(const char *str, const char *substr) {
    return strstr(str, substr) != NULL;
}

// Function to get a file at a given file location
FILE* get_file(const char *file_location) {
    char *lowercase_location = strdup(file_location);
    if (lowercase_location == NULL) {
        printf("Memory allocation failed\n");
        return NULL;
    }

    to_lowercase(lowercase_location);
    trim_whitespace(lowercase_location);

    if (contains_substring(lowercase_location, "/safedir")) {
        FILE *file = fopen(file_location, "r");
        if (file == NULL) {
            printf("Failed to open file: %s\n", file_location);
        }
        free(lowercase_location);
        return file;
    } else {
        printf("Provided File location is inaccessible\n");
    }

    free(lowercase_location);
    return NULL;
}

int main() {
    FILE *file = get_file("/safedir/test1.txt");
    if (file != NULL) {
        printf("File opened successfully\n");
        fclose(file);
    }
    return 0;
}