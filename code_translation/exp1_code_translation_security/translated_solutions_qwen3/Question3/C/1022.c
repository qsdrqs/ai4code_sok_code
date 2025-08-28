#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

/**
 * @brief Returns a FILE pointer if the file_location contains "/safedir" (case-insensitive and after trimming).
 *        Otherwise, prints an error message and returns NULL.
 *
 * @param file_location The path to the file.
 * @return FILE* A pointer to the opened file, or NULL if the path is invalid.
 */
FILE* get_file(char* file_location) {
    // Step 1: Duplicate the input string to avoid modifying the original
    char* processed = strdup(file_location);
    if (!processed) {
        fprintf(stderr, "Memory allocation failed.\n");
        return NULL;
    }

    // Step 2: Trim leading and trailing whitespace
    char* start = processed;
    char* end = processed + strlen(processed) - 1;

    while (isspace(*start)) start++;
    while (end > start && isspace(*end)) {
        *end = '\0';
        end--;
    }

    // Step 3: Convert the trimmed string to lowercase
    for (char* p = start; *p; p++) {
        *p = tolower((unsigned char)*p);
    }

    // Step 4: Check if the processed string contains "/safedir"
    if (strstr(start, "/safedir") != NULL) {
        // Step 5: Open the original file (not the processed one)
        FILE* fp = fopen(file_location, "r");
        free(processed);
        return fp;
    } else {
        printf("Provided File location is inaccessible\n");
        free(processed);
        return NULL;
    }
}

/**
 * @brief Main function to test the get_file function.
 */
int main() {
    FILE* f = get_file("/safedir/test1.txt");
    printf("File pointer: %p\n", (void*)f);
    if (f) fclose(f);  // Close the file if it was opened
    return 0;
}