#include <stdio.h>
#include <string.h>
#include <errno.h>
#include <stdlib.h>

/* 
 * C has no exceptions, so we simulate them with error codes
 * stored in a global variable (similar in spirit to errno).
 */
typedef enum {
    ERR_NONE = 0,
    ERR_PATH_NOT_ALLOWED,   /* corresponds to PathNotAllowed */
    ERR_FILE_NOT_FOUND,     /* corresponds to FileNotFoundError */
    ERR_OTHER
} ErrorCode;

static ErrorCode last_error = ERR_NONE;

/*
 * Opens a file for reading if the path starts with "/safedir".
 * Returns NULL on failure and sets last_error accordingly.
 */
FILE* getFilePath(const char* path) {
    last_error = ERR_NONE;
    
    if (strncmp(path, "/safedir", 8) != 0) {
        /* Equivalent to: raise PathNotAllowed("filepath not allowed, ...") */
        last_error = ERR_PATH_NOT_ALLOWED;
        return NULL;
    }
    
    FILE* f = fopen(path, "r");
    if (f == NULL) {
        if (errno == ENOENT) {
            last_error = ERR_FILE_NOT_FOUND;
        } else {
            last_error = ERR_OTHER;
        }
    }
    return f;
}

int main(void) {
    const char* prefix = "/safedir/";
    
    /* Build the "goodPaths" list */
    char goodPaths[2][256];
    snprintf(goodPaths[0], sizeof(goodPaths[0]), "%stest1.txt", prefix);
    snprintf(goodPaths[1], sizeof(goodPaths[1]), "%stest.txt",  prefix);
    const int num_paths = sizeof(goodPaths) / sizeof(goodPaths[0]);
    
    FILE* f;
    
    /* Should open without error */
    for (int i = 0; i < num_paths; i++) {
        f = getFilePath(goodPaths[i]);
        if (f != NULL) {
            fclose(f);
        }
    }
    
    /* Files that do not exist should fail as expected */
    f = getFilePath("/safedir/noexist.txt");
    if (f == NULL) {
        if (last_error == ERR_FILE_NOT_FOUND) {
            /* except FileNotFoundError: pass */
        }
    } else {
        fclose(f);
    }
    
    /* Files outside of /safedir should trigger ERR_PATH_NOT_ALLOWED */
    f = getFilePath("not/a/good/path.txt");
    if (f == NULL) {
        if (last_error == ERR_PATH_NOT_ALLOWED) {
            /* except PathNotAllowed: pass */
        }
    } else {
        fclose(f);
    }
    
    return 0;
}