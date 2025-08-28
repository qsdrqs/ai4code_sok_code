#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include <sys/stat.h>
#include <regex.h>
#include <unistd.h>

#define MAX_PATH 4096
#define MAX_PATTERN 256

/**
 * Recursively search for files matching a regex pattern in a directory
 */
char* search_file_re_recursive(const char* dir_path, const char* pattern, regex_t* regex) {
    DIR* dir;
    struct dirent* entry;
    struct stat statbuf;
    char full_path[MAX_PATH];
    char* result = NULL;
    
    dir = opendir(dir_path);
    if (dir == NULL) {
        return NULL;
    }
    
    while ((entry = readdir(dir)) != NULL) {
        // Skip . and .. entries
        if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0) {
            continue;
        }
        
        // Create full path
        snprintf(full_path, sizeof(full_path), "%s/%s", dir_path, entry->d_name);
        
        if (stat(full_path, &statbuf) == 0) {
            if (S_ISDIR(statbuf.st_mode)) {
                // Recursively search subdirectory
                result = search_file_re_recursive(full_path, pattern, regex);
                if (result != NULL) {
                    break;
                }
            } else if (S_ISREG(statbuf.st_mode)) {
                // Check if filename matches the regex pattern
                if (regexec(regex, entry->d_name, 0, NULL, 0) == 0) {
                    result = malloc(strlen(full_path) + 1);
                    if (result != NULL) {
                        strcpy(result, full_path);
                    }
                    break;
                }
            }
        }
    }
    
    closedir(dir);
    return result;
}

/**
 * search file in a path
 */
char* search_file_re(const char* file_path) {
    regex_t regex;
    char* result = NULL;
    
    // Compile the regex pattern
    if (regcomp(&regex, file_path, REG_EXTENDED) != 0) {
        fprintf(stderr, "Failed to compile regex pattern: %s\n", file_path);
        return NULL;
    }
    
    // Search for files in /safedir
    result = search_file_re_recursive("/safedir", file_path, &regex);
    
    // Free the compiled regex
    regfree(&regex);
    
    return result;
}

/**
 * main function
 */
int main() {
    char* file_path = search_file_re("test12.txt");
    
    if (file_path != NULL) {
        printf("%s\n", file_path);
        free(file_path);
    } else {
        printf("None\n");
    }
    
    return 0;
}