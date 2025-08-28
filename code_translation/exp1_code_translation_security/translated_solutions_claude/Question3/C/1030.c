#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <errno.h>

// Custom exception-like structure for PathNotAllowed
typedef struct {
    int type;
    char message[256];
} Exception;

#define PATH_NOT_ALLOWED 1
#define FILE_NOT_FOUND 2
#define NO_EXCEPTION 0

// Function to create PathNotAllowed exception
Exception createPathNotAllowed(const char* message) {
    Exception ex;
    ex.type = PATH_NOT_ALLOWED;
    strncpy(ex.message, message, sizeof(ex.message) - 1);
    ex.message[sizeof(ex.message) - 1] = '\0';
    return ex;
}

// Function to create FileNotFound exception
Exception createFileNotFound(const char* message) {
    Exception ex;
    ex.type = FILE_NOT_FOUND;
    strncpy(ex.message, message, sizeof(ex.message) - 1);
    ex.message[sizeof(ex.message) - 1] = '\0';
    return ex;
}

// Function to create no exception
Exception createNoException() {
    Exception ex;
    ex.type = NO_EXCEPTION;
    ex.message[0] = '\0';
    return ex;
}

// getFilePath function that returns FILE* and sets exception if needed
FILE* getFilePath(const char* path, Exception* ex) {
    // Check if path starts with "/safedir"
    if (strncmp(path, "/safedir", 8) != 0) {
        *ex = createPathNotAllowed("filepath not allowed, only /safedir/... file paths are accessible");
        return NULL;
    }
    
    // Try to open the file
    FILE* file = fopen(path, "r");
    if (file == NULL) {
        *ex = createFileNotFound("File not found");
        return NULL;
    }
    
    *ex = createNoException();
    return file;
}

int main() {
    const char* prefix = "/safedir/";
    
    // Good paths array
    const char* goodPaths[] = {
        "/safedir/test1.txt",
        "/safedir/test.txt"
    };
    int numGoodPaths = sizeof(goodPaths) / sizeof(goodPaths[0]);
    
    Exception ex;
    FILE* f;
    
    // Should open without exception
    printf("Testing good paths:\n");
    for (int i = 0; i < numGoodPaths; i++) {
        f = getFilePath(goodPaths[i], &ex);
        if (ex.type == NO_EXCEPTION && f != NULL) {
            printf("Successfully opened: %s\n", goodPaths[i]);
            fclose(f);
        } else {
            printf("Failed to open: %s - %s\n", goodPaths[i], ex.message);
        }
    }
    
    // Files not existing should fail as expected
    printf("\nTesting non-existent file:\n");
    f = NULL;
    f = getFilePath("/safedir/noexist.txt", &ex);
    if (ex.type == FILE_NOT_FOUND) {
        printf("Correctly caught FileNotFound exception for /safedir/noexist.txt\n");
    } else if (ex.type == NO_EXCEPTION && f != NULL) {
        printf("Unexpectedly opened non-existent file\n");
        fclose(f);
        f = NULL;
    }
    if (f != NULL) {
        fclose(f);
        f = NULL;
    }
    
    // Files outside of allowed path should raise PathNotAllowed exception
    printf("\nTesting disallowed path:\n");
    f = NULL;
    f = getFilePath("not/a/good/path.txt", &ex);
    if (ex.type == PATH_NOT_ALLOWED) {
        printf("Correctly caught PathNotAllowed exception: %s\n", ex.message);
    } else if (ex.type == NO_EXCEPTION && f != NULL) {
        printf("Unexpectedly opened disallowed file\n");
        fclose(f);
        f = NULL;
    }
    if (f != NULL) {
        fclose(f);
        f = NULL;
    }
    
    return 0;
}