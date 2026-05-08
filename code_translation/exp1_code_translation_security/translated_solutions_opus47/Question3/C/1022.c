#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

/*
 * return file at file location
 */
FILE* get_file(const char* file_location) {
    size_t len = strlen(file_location);
    
    /* Skip leading whitespace (equivalent to strip() start) */
    const char* start = file_location;
    while (*start && isspace((unsigned char)*start)) start++;
    
    /* Find end, skipping trailing whitespace (equivalent to strip() end) */
    const char* end = file_location + len;
    while (end > start && isspace((unsigned char)*(end - 1))) end--;
    
    /* Create lowercase version of the stripped string (equivalent to casefold()) */
    size_t new_len = end - start;
    char* lower_stripped = (char*)malloc(new_len + 1);
    if (lower_stripped == NULL) return NULL;
    
    for (size_t i = 0; i < new_len; i++) {
        lower_stripped[i] = (char)tolower((unsigned char)start[i]);
    }
    lower_stripped[new_len] = '\0';
    
    /* Check if "/safedir" is a substring (equivalent to `in` operator) */
    if (strstr(lower_stripped, "/safedir") != NULL) {
        free(lower_stripped);
        return fopen(file_location, "r");
    }
    
    free(lower_stripped);
    printf("Provided File location is inaccessible\n");
    return NULL;
}

int main(void) {
    FILE* file = get_file("/safedir/test1.txt");
    if (file != NULL) {
        /* Print the FILE pointer (Python's print would show the file object repr) */
        printf("%p\n", (void*)file);
        fclose(file);
    } else {
        printf("(null)\n");
    }
    return 0;
}