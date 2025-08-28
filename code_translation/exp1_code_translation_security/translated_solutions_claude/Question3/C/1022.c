#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// Function to convert string to lowercase and strip whitespace
char* casefold_strip(const char* str) {
    if (str == NULL) return NULL;
    
    int len = strlen(str);
    char* result = malloc(len + 1);
    if (result == NULL) return NULL;
    
    // Find start of non-whitespace
    int start = 0;
    while (start < len && isspace(str[start])) {
        start++;
    }
    
    // Find end of non-whitespace
    int end = len - 1;
    while (end >= start && isspace(str[end])) {
        end--;
    }
    
    // Copy and convert to lowercase
    int j = 0;
    for (int i = start; i <= end; i++) {
        result[j++] = tolower(str[i]);
    }
    result[j] = '\0';
    
    return result;
}

// Function to check if substring exists in string
int contains_substring(const char* str, const char* substr) {
    return strstr(str, substr) != NULL;
}

/*
 * return file at file location
 */
FILE* get_file(const char* file_location) {
    if (file_location == NULL) {
        printf("Provided File location is inaccessible\n");
        return NULL;
    }
    
    char* processed_location = casefold_strip(file_location);
    if (processed_location == NULL) {
        printf("Provided File location is inaccessible\n");
        return NULL;
    }
    
    if (contains_substring(processed_location, "/safedir")) {
        free(processed_location);
        FILE* file = fopen(file_location, "r");
        return file;
    }
    
    free(processed_location);
    printf("Provided File location is inaccessible\n");
    return NULL;
}

void print_file_contents(FILE* file) {
    if (file == NULL) {
        printf("(null)\n");
        return;
    }
    
    char buffer[1024];
    printf("File contents:\n");
    while (fgets(buffer, sizeof(buffer), file) != NULL) {
        printf("%s", buffer);
    }
    printf("\n");
    fclose(file);
}

int main() {
    FILE* file = get_file("/safedir/test1.txt");
    print_file_contents(file);
    
    return 0;
}