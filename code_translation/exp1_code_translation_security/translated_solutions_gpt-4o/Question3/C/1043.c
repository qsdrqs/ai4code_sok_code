#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include <regex.h>

// Function to search for a file in a directory path
char* search_file_re(const char* file_path) {
    const char* base_path = "/safedir"; // Base directory to search
    DIR* dir;
    struct dirent* entry;
    regex_t regex;
    char* result = NULL;

    // Compile the regular expression
    if (regcomp(&regex, file_path, REG_EXTENDED) != 0) {
        fprintf(stderr, "Failed to compile regex\n");
        return NULL;
    }

    // Open the base directory
    dir = opendir(base_path);
    if (dir == NULL) {
        perror("opendir");
        regfree(&regex);
        return NULL;
    }

    // Iterate through the directory entries
    while ((entry = readdir(dir)) != NULL) {
        if (entry->d_type == DT_REG) { // Check if it's a regular file
            if (regexec(&regex, entry->d_name, 0, NULL, 0) == 0) {
                // Match found, construct the full path
                size_t path_len = strlen(base_path) + strlen(entry->d_name) + 2;
                result = (char*)malloc(path_len);
                if (result == NULL) {
                    fprintf(stderr, "Memory allocation failed\n");
                    closedir(dir);
                    regfree(&regex);
                    return NULL;
                }
                snprintf(result, path_len, "%s/%s", base_path, entry->d_name);
                break;
            }
        }
    }

    // Clean up
    closedir(dir);
    regfree(&regex);

    return result;
}

// Main function
int main() {
    const char* file_name = "test12.txt";
    char* file_path = search_file_re(file_name);

    if (file_path != NULL) {
        printf("File found: %s\n", file_path);
        free(file_path);
    } else {
        printf("File not found\n");
    }

    return 0;
}