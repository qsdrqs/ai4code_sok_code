#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

// Define a custom error for PathNotAllowed
typedef struct {
    int code;
    char* message;
} PathNotAllowed;

// Function to create a PathNotAllowed error
PathNotAllowed* pathNotAllowed(char* message) {
    PathNotAllowed* error = malloc(sizeof(PathNotAllowed));
    error->code = 1; // Custom error code
    error->message = strdup(message);
    return error;
}

// Function to get a file path and validate it
FILE* getFilePath(const char* path) {
    // Check if path starts with "/safedir"
    if (strncmp(path, "/safedir", strlen("/safedir")) != 0) {
        PathNotAllowed* error = pathNotAllowed("filepath not allowed, only /safedir/... file paths are accessible");
        fprintf(stderr, "Error: %s\n", error->message);
        free(error->message);
        free(error);
        exit(1); // Exit with a non-zero status code
    }

    // Attempt to open the file
    FILE* file = fopen(path, "r");
    if (file == NULL) {
        if (errno == ENOENT) {
            fprintf(stderr, "Error: File not found: %s\n", path);
        } else {
            fprintf(stderr, "Error: Unable to open file: %s\n", strerror(errno));
        }
        exit(1); // Exit with a non-zero status code
    }

    return file;
}

int main() {
    const char* prefix = "/safedir/";
    const char* goodPaths[] = {
        "test1.txt",
        "test.txt"
    };
    int numGoodPaths = sizeof(goodPaths) / sizeof(goodPaths[0]);

    // Construct full paths for good paths
    char fullPaths[10][256];
    for (int i = 0; i < numGoodPaths; i++) {
        snprintf(fullPaths[i], sizeof(fullPaths[i]), "%s%s", prefix, goodPaths[i]);
        // Should open without exception
        FILE* f = getFilePath(fullPaths[i]);
        fclose(f);
    }

    // Files not existing should fail as expected
    const char* nonExistentPath = "/safedir/noexist.txt";
    FILE* f;
    if ((f = fopen(nonExistentPath, "r")) == NULL) {
        if (errno == ENOENT) {
            printf("File not found as expected: %s\n", nonExistentPath);
        } else {
            fprintf(stderr, "Error: Unable to open file: %s\n", strerror(errno));
        }
    } else {
        fclose(f);
    }

    // Files outside of not allowed path should raise PathNotAllowed exception
    const char* badPath = "not/a/good/path.txt";
    if ((f = fopen(badPath, "r")) == NULL) {
        if (errno == ENOENT) {
            printf("File not found as expected: %s\n", badPath);
        } else {
            fprintf(stderr, "Error: Unable to open file: %s\n", strerror(errno));
        }
    } else {
        fclose(f);
    }

    return 0;
}