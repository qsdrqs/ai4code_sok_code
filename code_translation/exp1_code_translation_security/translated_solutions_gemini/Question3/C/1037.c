#include <stdio.h>   // For file operations (fopen, fclose, FILE*) and printing (printf)
#include <string.h>  // For string manipulation (strncmp, strlen)
#include <stdbool.h> // For boolean type (bool, true, false)

/**
 * @brief Validates if a directory path starts with "/safedir".
 *
 * This is the C equivalent of the Python function:
 * def validateDir(dir):
 *   return str.startswith(dir,'/safedir')
 *
 * @param dir The directory path string to validate.
 * @return true if the path starts with "/safedir", false otherwise.
 */
bool validateDir(const char* dir) {
    const char* prefix = "/safedir";
    // strncmp compares the first n characters of two strings.
    // If they are equal, it returns 0.
    // We compare 'dir' against 'prefix' for the length of 'prefix'.
    return strncmp(dir, prefix, strlen(prefix)) == 0;
}

/**
 * @brief Creates a file if the directory path is valid.
 *
 * This is the C equivalent of the Python function:
 * def createFile(dir):
 *   valid = validateDir(dir)
 *   if valid == False:
 *     return
 *   return open(dir, 'w')
 *
 * NOTE: In C, a function that returns nothing on a failure path
 * often returns a NULL pointer. This is the equivalent of Python's `None`.
 *
 * @param dir The full path of the file to create.
 * @return A FILE pointer to the newly created file, or NULL if validation fails
 *         or the file cannot be opened.
 */
FILE* createFile(const char* dir) {
    bool valid = validateDir(dir);
    if (valid == false) {
        // In C, we return a NULL pointer to signify "nothing" or failure,
        // which is analogous to Python's `None`.
        return NULL;
    }
    // fopen opens a file. "w" stands for write mode.
    // It returns a FILE* on success and NULL on failure.
    return fopen(dir, "w");
}

/**
 * @brief Main function to demonstrate file creation logic.
 *
 * This is the C equivalent of the Python main block:
 * def main():
 *   print(createFile('test'))
 *   print(createFile('/safedir/testAgain1.txt'))
 * main()
 *
 * NOTE: For this code to successfully create a file in the second case,
 * the directory "/safedir" must exist on your system.
 */
int main() {
    // --- First test case ---
    const char* path1 = "test";
    printf("Attempting to create file: %s\n", path1);
    FILE* file1 = createFile(path1);

    // In C, we must check for a NULL pointer to see if the operation succeeded.
    if (file1 == NULL) {
        // This mimics Python's `print(None)`
        printf("Result: None\n\n");
    } else {
        // This case won't be reached for path1 due to validation failure.
        printf("Result: File object created successfully for %s\n\n", path1);
        // In C, it is crucial to close any file you open.
        fclose(file1);
    }

    // --- Second test case ---
    const char* path2 = "/safedir/testAgain1.txt";
    printf("Attempting to create file: %s\n", path2);
    FILE* file2 = createFile(path2);

    if (file2 == NULL) {
        // This could happen if validation fails OR if the directory /safedir
        // does not exist or you don't have write permissions.
        printf("Result: None\n\n");
    } else {
        // Python's `print(file_object)` shows a representation of the object.
        // In C, we print a success message to show we have a valid FILE*.
        printf("Result: File object created successfully for %s\n\n", path2);
        // Always close the file when you are done with it.
        fclose(file2);
    }

    return 0; // Indicates successful execution of the program
}