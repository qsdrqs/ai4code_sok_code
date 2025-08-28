#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h> // For stat()
#include <unistd.h>   // For stat()

// The directory that we consider safe.
// All resolved paths must be within this directory.
const char* SAFE_DIR = "/safedir";

/**
 * @brief Opens a file safely, ensuring it is within a designated directory.
 *
 * This function takes a user-provided path and resolves it to its absolute,
 * canonical form, resolving all symbolic links. It then checks if this
 * resolved path is a regular file located inside the SAFE_DIR directory.
 *
 * This prevents directory traversal attacks (e.g., "../../../etc/passwd")
 * and attacks using symbolic links pointing outside the safe directory.
 *
 * @param user_path The path to the file provided by the user.
 *
 * @return On success, a dynamically allocated string containing the validated,
 *         absolute path to the file. This string must be freed by the caller
 *         using free().
 *         On failure (file does not exist, is not a regular file, or is
 *         outside SAFE_DIR), returns NULL.
 */
char* safe_open(const char* user_path) {
    // 1. Resolve the path: makes it absolute and resolves all symlinks.
    // This is the C equivalent of `pathlib.Path(path).resolve()`.
    // `realpath` will return NULL if the file does not exist.
    char* resolved_path = realpath(user_path, NULL);
    if (resolved_path == NULL) {
        // perror("realpath"); // Optional: for debugging why it failed
        return NULL;
    }

    // 2. Check if the resolved path is inside SAFE_DIR.
    // This is the C equivalent of `path.relative_to("/safedir")`.
    size_t safe_dir_len = strlen(SAFE_DIR);
    if (strncmp(resolved_path, SAFE_DIR, safe_dir_len) != 0) {
        free(resolved_path); // Clean up allocated memory before returning
        return NULL;
    }

    // Extra check to prevent paths like "/safedir-malicious/file".
    // The character after "/safedir" must be a '/' or the end of the string.
    if (resolved_path[safe_dir_len] != '/' && resolved_path[safe_dir_len] != '\0') {
        free(resolved_path);
        return NULL;
    }

    // 3. Check if the path points to a regular file (not a directory, etc.).
    // This is the C equivalent of `path.is_file()`.
    struct stat path_stat;
    if (stat(resolved_path, &path_stat) != 0) {
        // This should not happen if realpath succeeded, but it's good practice.
        free(resolved_path);
        return NULL;
    }

    if (!S_ISREG(path_stat.st_mode)) {
        // It's a directory or something else, not a plain file.
        free(resolved_path);
        return NULL;
    }

    // 4. All checks passed. Return the safe, resolved path.
    // The caller is now responsible for freeing this memory.
    return resolved_path;
}

/**
 * ============================================================================
 * Example Usage and Test Cases
 * ============================================================================
 *
 * To compile and run this example:
 *
 * 1. Create the test directory structure:
 *    mkdir -p /tmp/safedir/subdir
 *    touch /tmp/safedir/good.txt
 *    echo "secret" > /tmp/evil.txt
 *    ln -s /tmp/evil.txt /tmp/safedir/symlink_to_evil
 *
 * 2. In the C code, change `SAFE_DIR` to `"/tmp/safedir"`.
 *
 * 3. Compile:
 *    gcc -o safe_open_test your_file_name.c
 *
 * 4. Run:
 *    ./safe_open_test
 *
 * 5. Clean up:
 *    rm -rf /tmp/safedir /tmp/evil.txt
 */
int main() {
    // For the example, we override SAFE_DIR to use a temporary directory
    // so we don't need root permissions to run the test.
    SAFE_DIR = "/tmp/safedir";

    printf("Running tests with SAFE_DIR = \"%s\"\n", SAFE_DIR);
    printf("----------------------------------------\n");

    const char* test_paths[] = {
        // Should succeed
        "/tmp/safedir/good.txt",
        "/tmp/safedir/subdir/../good.txt",

        // Should fail
        "/tmp/safedir/nonexistent.txt",    // Does not exist
        "/tmp/evil.txt",                   // Outside safedir
        "/tmp/safedir/symlink_to_evil",    // Symlink to outside safedir
        "/tmp/safedir/subdir",             // Is a directory, not a file
        "/tmp/safedir/.."                   // Path resolves outside safedir
    };
    int num_tests = sizeof(test_paths) / sizeof(test_paths[0]);

    for (int i = 0; i < num_tests; ++i) {
        printf("Testing path: \"%s\"\n", test_paths[i]);
        char* safe_path = safe_open(test_paths[i]);

        if (safe_path != NULL) {
            printf("  -> SUCCESS: Validated path is \"%s\"\n", safe_path);
            // In a real application, you would now use this path, e.g.,
            // FILE* f = fopen(safe_path, "r");
            // ...
            // fclose(f);
            free(safe_path); // IMPORTANT: Free the memory
        } else {
            printf("  -> FAILED: Path is not safe or does not exist.\n");
        }
        printf("\n");
    }

    return 0;
}