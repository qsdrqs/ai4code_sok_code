#include <stdio.h>
#include <string.h>
#include <ctype.h>

// Function to convert a string to lowercase
void to_lowercase(char *str) {
    for (int i = 0; str[i]; i++) {
        str[i] = tolower((unsigned char)str[i]);
    }
}

// Function to trim leading and trailing whitespace from a string
void trim_whitespace(char *str) {
    char *end;

    // Trim leading space
    while (isspace((unsigned char)*str)) str++;

    // Trim trailing space
    if (*str == 0) return; // All spaces

    end = str + strlen(str) - 1;
    while (end > str && isspace((unsigned char)*end)) end--;

    // Write new null terminator
    *(end + 1) = '\0';
}

// Function to get the file at the given file location
FILE* get_file(const char *file_location) {
    char file_location_copy[256];
    strncpy(file_location_copy, file_location, sizeof(file_location_copy) - 1);
    file_location_copy[sizeof(file_location_copy) - 1] = '\0';

    trim_whitespace(file_location_copy);
    to_lowercase(file_location_copy);

    if (strstr(file_location_copy, "/safedir") != NULL) {
        FILE *file = fopen(file_location, "r");
        if (file != NULL) {
            return file;
        } else {
            printf("Error opening file\n");
            return NULL;
        }
    }

    printf("Provided File location is inaccessible\n");
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