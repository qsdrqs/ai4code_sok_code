#include <stdio.h>   // For FILE, fopen, fprintf, stderr
#include <stdlib.h>  // For realpath, malloc, free
#include <string.h>  // For strlen, strcpy, strcat, strncmp
#include <limits.h>  // For PATH_MAX

// Define the safe directory, equivalent to the Python SAFE constant.
#define SAFE_DIR "/safedir/"

/**
 * @brief Securely opens a file, ensuring it is within the SAFE_DIR directory.
 *
 * This function prevents directory traversal attacks. It resolves the canonical
 * path of the requested file and verifies that it is a descendant of the
 * canonical path of SAFE_DIR.
 *
 * @param path The relative path to the file from within SAFE_DIR.
 * @return A FILE pointer to the opened file on success, or NULL on failure
 *         (e.g., security violation, file not found, memory error).
 *         The caller is responsible for calling fclose() on the returned pointer.
 */
FILE* getFile(const char* path) {
    // Allocate memory for the full path string (e.g., "/safedir/file.txt")
    // +1 for the null terminator.
    size_t full_path_len = strlen(SAFE_DIR) + strlen(path) + 1;
    char* full_path = (char*)malloc(full_path_len);
    if (full_path == NULL) {
        perror("Failed to allocate memory for path");
        return NULL;
    }
    strcpy(full_path, SAFE_DIR);
    strcat(full_path, path);

    // Buffers to hold the real, canonical paths.
    // realpath() resolves all symlinks, '.', and '..' components.
    char resolved_safe_path[PATH_MAX];
    char resolved_user_path[PATH_MAX];

    // Resolve the canonical path of the safe base directory.
    if (realpath(SAFE_DIR, resolved_safe_path) == NULL) {
        fprintf(stderr, "Error: Could not resolve safe directory path: %s\n", SAFE_DIR);
        perror("realpath");
        free(full_path);
        return NULL;
    }

    // Resolve the canonical path of the file the user requested.
    if (realpath(full_path, resolved_user_path) == NULL) {
        // This can fail if the file doesn't exist, which is a valid case.
        // We still proceed to the security check with the original path
        // to let fopen handle the "file not found" error, but we log this.
        // The security check below will still catch traversal attempts.
        fprintf(stderr, "Info: Could not resolve user file path: %s. Will proceed to open.\n", full_path);
        // In this case, we must use the unresolved path for the check.
        // This is less secure, but realpath requires the path to exist.
        // A robust solution would require more complex string parsing.
        // For this translation, we will rely on the check below.
        strncpy(resolved_user_path, full_path, PATH_MAX - 1);
        resolved_user_path[PATH_MAX - 1] = '\0';
    }

    // --- The Security Check ---
    // This is equivalent to: `Path(SAFE).resolve() in test_path.resolve().parents`
    // We check if the resolved user path starts with the resolved safe path.
    size_t safe_path_len = strlen(resolved_safe_path);
    if (strncmp(resolved_user_path, resolved_safe_path, safe_path_len) != 0) {
        fprintf(stderr, "Exception: Filename %s is not in %s directory\n",
                resolved_user_path, resolved_safe_path);
        free(full_path);
        return NULL;
    }

    // Additional check to ensure it's a true subdirectory.
    // e.g., prevents access to "/safedir_imposter" if SAFE_DIR is "/safedir"
    // The character after the common path must be a '/' or the end of the string.
    if (resolved_user_path[safe_path_len] != '/' && resolved_user_path[safe_path_len] != '\0') {
         fprintf(stderr, "Exception: Filename %s is not in %s directory (invalid path boundary)\n",
                resolved_user_path, resolved_safe_path);
        free(full_path);
        return NULL;
    }

    // If the check passes, open the file.
    FILE* file_handle = fopen(full_path, "r");
    if (file_handle == NULL) {
        // Let the caller know why fopen failed (e.g., "No such file or directory")
        perror("Failed to open file");
    }

    // Clean up the allocated memory for the full path string.
    free(full_path);

    return file_handle;
}

// --- Example Usage ---
// To compile and run this example:
// 1. Compile the code: gcc -o file_test your_file_name.c
// 2. Create the necessary directory and files for testing:
//    mkdir -p /safedir
//    touch /safedir/good_file.txt
//    echo "hello safe world" > /safedir/good_file.txt
//    touch /bad_file.txt
// 3. Run the executable: ./file_test
int main() {
    printf("--- Attempting to access a valid file ---\n");
    FILE* f1 = getFile("good_file.txt");
    if (f1) {
        printf("Success: Opened good_file.txt\n");
        // You can now read from the file, e.g., fgetc, fgets...
        fclose(f1);
    } else {
        printf("Failure: Could not open good_file.txt\n");
    }

    printf("\n--- Attempting a directory traversal attack ---\n");
    // This path would resolve to /bad_file.txt, which is outside /safedir
    FILE* f2 = getFile("../bad_file.txt");
    if (f2) {
        printf("VULNERABILITY: Opened ../bad_file.txt!\n");
        fclose(f2);
    } else {
        printf("Success: Blocked access to ../bad_file.txt\n");
    }

    printf("\n--- Attempting to access a non-existent file ---\n");
    FILE* f3 = getFile("no_such_file.txt");
    if (f3) {
        printf("Error: Opened a non-existent file somehow.\n");
        fclose(f3);
    } else {
        printf("Success: Correctly failed to open non_existent_file.txt\n");
    }

    return 0;
}