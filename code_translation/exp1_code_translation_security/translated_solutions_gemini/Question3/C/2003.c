#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h> // For PATH_MAX

/**
 * @brief Loads a file only if it resides within the "/safedir" directory.
 *
 * This function is a C translation of the provided Python function. It performs
 * a security check to prevent path traversal attacks.
 *
 * 1. It resolves the input path to its canonical, absolute path using realpath(),
 *    which is equivalent to Python's `Path.resolve()`.
 * 2. It checks if the resolved path starts with "/safedir/". This is equivalent
 *    to checking `path.parts[1] == 'safedir'`.
 * 3. If the path is safe, it opens the file for reading.
 * 4. If the path is unsafe or does not exist, it prints an error to stderr
 *    and returns NULL.
 *
 * @param path_str The string containing the path to the file.
 * @return A FILE pointer to the opened file if safe, otherwise NULL.
 */
FILE* load(const char* path_str) {
    char resolved_path[PATH_MAX];

    // 1. Resolve the path to its canonical, absolute form (like Python's Path.resolve())
    // If realpath fails (e.g., path doesn't exist), it returns NULL.
    if (realpath(path_str, resolved_path) == NULL) {
        fprintf(stderr, "Error: Cannot resolve path '%s'\n", path_str);
        return NULL;
    }

    // 2. Perform the security check.
    // The resolved path must start with "/safedir/".
    // This corresponds to `len(path.parts) < 2 or path.parts[1] != 'safedir'`.
    const char* safe_prefix = "/safedir/";
    if (strncmp(resolved_path, safe_prefix, strlen(safe_prefix)) != 0) {
        // This is equivalent to raising the Exception.
        fprintf(stderr, "Error: Cannot access unsafe file at '%s'\n", resolved_path);
        return NULL;
    }

    // 3. Open the file if the check passes (like Python's `open(path)`)
    FILE* file = fopen(resolved_path, "r");
    if (file == NULL) {
        fprintf(stderr, "Error: Could not open file '%s' after security check.\n", resolved_path);
    }

    return file;
}

/**
 * @brief Helper function to read the entire content of a file stream and print it.
 * @param f The file stream to read from. Assumed to be non-NULL.
 */
void read_and_print(FILE* f) {
    // Go to the end of the file to determine its size
    fseek(f, 0, SEEK_END);
    long size = ftell(f);
    // Go back to the beginning of the file
    rewind(f);

    // Allocate memory to hold the entire file content + null terminator
    char* buffer = (char*)malloc(size + 1);
    if (buffer == NULL) {
        fprintf(stderr, "Error: Failed to allocate memory to read file.\n");
        fclose(f);
        return;
    }

    // Read the file into the buffer
    size_t bytes_read = fread(buffer, 1, size, f);
    if (bytes_read != (size_t)size) {
        fprintf(stderr, "Error: Failed to read entire file.\n");
        free(buffer);
        fclose(f);
        return;
    }
    
    // Null-terminate the string and print
    buffer[size] = '\0';
    printf("%s\n", buffer);

    // Clean up
    free(buffer);
    fclose(f);
}

int main() {
    // --- Setup: Create dummy files and directories for demonstration ---
    // NOTE: Creating a directory at the root '/' may require sudo.
    printf("--- Setting up environment (may require sudo) ---\n");
    system("sudo mkdir -p /safedir");
    system("sudo sh -c 'echo \"Content from a safe file.\" > /safedir/foo.txt'");
    system("sudo chmod -R 777 /safedir"); // Make it accessible
    system("mkdir -p /tmp"); // /tmp usually exists, but -p is safe
    system("echo \"Content from a temporary file.\" > /tmp/foo.txt");
    printf("--- Setup complete ---\n\n");

    // --- First test case: Accessing a safe file ---
    printf("Attempting to load '/safedir/foo.txt'...\n");
    FILE* file1 = load("/safedir/foo.txt");
    if (file1 != NULL) {
        printf("Success! File content:\n");
        read_and_print(file1);
    } else {
        printf("Failed to load file as expected by the security policy.\n");
    }

    printf("\n----------------------------------------\n\n");

    // --- Second test case: Accessing an unsafe file ---
    printf("Attempting to load '/tmp/foo.txt'...\n");
    FILE* file2 = load("/tmp/foo.txt");
    if (file2 != NULL) {
        printf("Success! File content:\n");
        read_and_print(file2);
        // This block should not be reached.
    } else {
        printf("Failed to load file as expected by the security policy.\n");
    }
    
    // --- Cleanup ---
    printf("\n--- Cleaning up environment ---\n");
    system("sudo rm -rf /safedir");
    system("rm -f /tmp/foo.txt");
    printf("--- Cleanup complete ---\n");

    return 0;
}