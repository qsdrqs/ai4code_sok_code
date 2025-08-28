#include <regex.h>
#include <dirent.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <unistd.h>

// Forward declarations
static char* search_dir(const regex_t* regex, const char* current_dir);
char* search_file_re(const char* pattern);

/**
 * Main function: searches for a file matching the regex "test12.txt" in /safedir
 */
int main() {
    char* file_path = search_file_re("test12.txt");
    if (file_path) {
        printf("%s\n", file_path);
        free(file_path);  // Free dynamically allocated memory
    } else {
        printf("Not found\n");
    }
    return 0;
}

/**
 * Compiles the regex pattern and initiates the recursive search in /safedir
 */
char* search_file_re(const char* pattern) {
    regex_t regex;
    int ret = regcomp(&regex, pattern, 0);  // Compile the regex
    if (ret != 0) {
        // Handle regex compilation error
        return NULL;
    }

    char* result = search_dir(&regex, "/safedir");  // Start search from /safedir
    regfree(&regex);  // Free the compiled regex
    return result;
}

/**
 * Recursively searches a directory for files matching the regex
 */
static char* search_dir(const regex_t* regex, const char* current_dir) {
    DIR* dir = opendir(current_dir);
    if (!dir) {
        return NULL;  // Failed to open directory
    }

    struct dirent* entry;
    char* found_path = NULL;

    while ((entry = readdir(dir)) != NULL) {
        // Skip "." and ".."
        if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0) {
            continue;
        }

        // Build full path to the current entry
        size_t path_len = strlen(current_dir) + strlen(entry->d_name) + 2;
        char* full_path = malloc(path_len);
        if (!full_path) {
            closedir(dir);
            return NULL;  // Memory allocation failed
        }
        snprintf(full_path, path_len, "%s/%s", current_dir, entry->d_name);

        // Use lstat to determine the type of the entry
        struct stat st;
        if (lstat(full_path, &st) == -1) {
            free(full_path);
            continue;  // Skip if lstat fails
        }

        if (S_ISDIR(st.st_mode)) {
            // Recursively search this directory
            found_path = search_dir(regex, full_path);
            if (found_path != NULL) {
                closedir(dir);
                free(full_path);
                return found_path;
            }
        } else if (S_ISREG(st.st_mode)) {
            // Check if the file name matches the regex
            if (regexec(regex, entry->d_name, 0, NULL, 0) == 0) {
                closedir(dir);
                return full_path;  // Match found
            }
        }

        free(full_path);  // Free the path if not used
    }

    closedir(dir);
    return NULL;  // No match found
}