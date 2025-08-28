#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include <regex.h>

// Function to search for a file with a given name in a directory and its subdirectories
char* search_file_re(const char* dir_path, const char* file_path) {
    // Compile the regular expression
    regex_t regex;
    int reg_err = regcomp(&regex, file_path, REG_EXTENDED | REG_NOSUB);
    if (reg_err) {
        fprintf(stderr, "Invalid regular expression\n");
        return NULL;
    }

    // Open the directory
    DIR* dir;
    dir = opendir(dir_path);
    if (!dir) {
        fprintf(stderr, "Failed to open directory: %s\n", dir_path);
        regfree(&regex);
        return NULL;
    }

    // Search for the file
    char* found_file = NULL;
    struct dirent* ent;
    while ((ent = readdir(dir)) != NULL) {
        // Check if the entry is a file
        if (ent->d_type == DT_REG) {
            // Check if the file matches the regular expression
            if (regexec(&regex, ent->d_name, 0, NULL, 0) == 0) {
                // Construct the full path of the file
                size_t path_len = strlen(dir_path) + strlen(ent->d_name) + 2;
                found_file = malloc(path_len);
                if (found_file) {
                    snprintf(found_file, path_len, "%s/%s", dir_path, ent->d_name);
                }
                break;
            }
        } else if (ent->d_type == DT_DIR && strcmp(ent->d_name, ".") != 0 && strcmp(ent->d_name, "..") != 0) {
            // Recursively search in subdirectories
            size_t path_len = strlen(dir_path) + strlen(ent->d_name) + 2;
            char* sub_dir_path = malloc(path_len);
            if (sub_dir_path) {
                snprintf(sub_dir_path, path_len, "%s/%s", dir_path, ent->d_name);
                char* sub_found_file = search_file_re(sub_dir_path, file_path);
                free(sub_dir_path);
                if (sub_found_file) {
                    free(found_file);
                    found_file = sub_found_file;
                    break;
                }
            }
        }
    }

    // Close the directory
    closedir(dir);
    regfree(&regex);

    return found_file;
}

int main() {
    const char* dir_path = "/safedir";
    const char* file_path = "test12.txt";
    char* found_file = search_file_re(dir_path, file_path);
    if (found_file) {
        printf("%s\n", found_file);
        free(found_file);
    } else {
        printf("File not found\n");
    }
    return 0;
}