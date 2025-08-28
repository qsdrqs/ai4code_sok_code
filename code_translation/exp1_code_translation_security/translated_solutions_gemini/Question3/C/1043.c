/**
 * @file search_file.c
 * @brief C implementation to search for a file in a directory tree using a regular expression.
 *
 * This program translates the following Python script:
 *
 * import os
 * import re
 *
 * def search_file_re(file_path):
 *     for root, dirs, files in os.walk("/safedir"):
 *         for name in files:
 *             if re.match(file_path, name):
 *                 return os.path.join(root, name)
 *     return None
 *
 * def main():
 *     file_path = search_file_re("test12.txt")
 *     print(file_path)
 *
 * if __name__ == "__main__":
 *     main()
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>     // For opendir, readdir, closedir (like os.walk)
#include <sys/stat.h>   // For stat to check if a path is a directory or file
#include <regex.h>      // For regcomp, regexec, regfree (like the 're' module)
#include <limits.h>     // For PATH_MAX, the max length of a path

// Forward declaration for the recursive search function
char* search_recursive(const char* base_path, regex_t* preg);

/**
 * @brief Searches for a file matching a regex pattern within a directory tree.
 *
 * This function is the C equivalent of the Python `search_file_re` function.
 * It compiles the regex and initiates the recursive search.
 *
 * @param pattern The regular expression pattern to match against filenames.
 * @param start_dir The root directory to start the search from.
 * @return A dynamically allocated string containing the full path of the first
 *         matching file, or NULL if no match is found. The caller is
 *         responsible for freeing the returned string.
 */
char* search_file_re(const char* pattern, const char* start_dir) {
    regex_t preg;
    int reti;

    // Python's re.match() checks for a match at the beginning of the string.
    // To replicate this, we prepend '^' to the pattern.
    // We allocate memory for the new pattern: len(pattern) + '^' + '\0'.
    char* anchored_pattern = malloc(strlen(pattern) + 2);
    if (!anchored_pattern) {
        perror("Failed to allocate memory for pattern");
        return NULL;
    }
    sprintf(anchored_pattern, "^%s", pattern);

    // Compile the regular expression. REG_EXTENDED enables modern regex syntax.
    // REG_NOSUB is an optimization as we only care about a match, not capture groups.
    reti = regcomp(&preg, anchored_pattern, REG_EXTENDED | REG_NOSUB);
    free(anchored_pattern); // Free the temporary anchored pattern string

    if (reti) {
        char msgbuf[100];
        regerror(reti, &preg, msgbuf, sizeof(msgbuf));
        fprintf(stderr, "Could not compile regex: %s\n", msgbuf);
        return NULL;
    }

    // Start the recursive search, which is equivalent to os.walk
    char* result = search_recursive(start_dir, &preg);

    // Free the compiled regular expression object
    regfree(&preg);

    return result;
}

/**
 * @brief Recursively traverses directories to find a matching file.
 *
 * This function is the core logic that emulates Python's `os.walk`.
 * It opens a directory, iterates through its contents, and for each item,
 * it either checks for a file match or recurses into a subdirectory.
 *
 * @param base_path The current directory being searched.
 * @param preg A pointer to the compiled regular expression.
 * @return A dynamically allocated string with the full path of a found file,
 *         or NULL if not found in this branch of the directory tree.
 */
char* search_recursive(const char* base_path, regex_t* preg) {
    DIR *dir;
    struct dirent *entry;
    char path[PATH_MAX];

    // Try to open the directory
    if (!(dir = opendir(base_path))) {
        // Silently ignore directories we can't open (e.g., due to permissions),
        // similar to how os.walk can be configured.
        return NULL;
    }

    // Read directory entries one by one
    while ((entry = readdir(dir)) != NULL) {
        // Skip the special directories "." and ".."
        if (strcmp(entry->d_name, ".") == 0 || strcmp(entry->d_name, "..") == 0) {
            continue;
        }

        // Construct the full path, equivalent to os.path.join(root, name)
        snprintf(path, sizeof(path), "%s/%s", base_path, entry->d_name);

        struct stat statbuf;
        if (stat(path, &statbuf) == -1) {
            // Could not get stats for the path, skip it
            continue;
        }

        if (S_ISDIR(statbuf.st_mode)) {
            // If it's a directory, recurse into it
            char* found_path = search_recursive(path, preg);
            if (found_path != NULL) {
                // If a match was found in the subdirectory, stop searching and return
                closedir(dir);
                return found_path;
            }
        } else if (S_ISREG(statbuf.st_mode)) {
            // If it's a regular file, check if its name matches the regex
            if (regexec(preg, entry->d_name, 0, NULL, 0) == 0) {
                // Match found!
                closedir(dir);
                // Return a dynamically allocated copy of the full path
                return strdup(path);
            }
        }
    }

    closedir(dir);
    return NULL; // No match found in this directory
}

/**
 * @brief Main function, the entry point of the program.
 *
 * This is the C equivalent of the `if __name__ == "__main__":` block.
 */
int main() {
    // In Python: file_path = search_file_re("test12.txt")
    // The pattern "test12.txt" uses '.' as a wildcard for any character.
    // This translation preserves that behavior.
    const char* pattern = "test12.txt";
    const char* start_dir = "/safedir";

    char* file_path = search_file_re(pattern, start_dir);

    // In Python: print(file_path)
    // If file_path is a string, it's printed. If it's None, "None" is printed.
    if (file_path != NULL) {
        printf("%s\n", file_path);
        // The returned path was dynamically allocated, so we must free it.
        free(file_path);
    } else {
        printf("None\n");
    }

    return 0;
}