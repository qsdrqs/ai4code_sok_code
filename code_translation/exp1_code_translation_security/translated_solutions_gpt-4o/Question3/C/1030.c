#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define PATH_NOT_ALLOWED 1

void handlePathNotAllowed() {
    fprintf(stderr, "filepath not allowed, only /safedir/... file paths are accessible\n");
    exit(PATH_NOT_ALLOWED);
}

FILE* getFilePath(const char* path) {
    if (strncmp(path, "/safedir", 8) != 0) {
        handlePathNotAllowed();
    }

    FILE* file = fopen(path, "r");
    if (file == NULL) {
        perror("Error opening file");
        exit(EXIT_FAILURE);
    }

    return file;
}

int main() {
    const char* prefix = "/safedir/";

    const char* goodPaths[] = {
        "/safedir/test1.txt",
        "/safedir/test.txt"
    };

    // should open without exception
    for (int i = 0; i < 2; i++) {
        FILE* f = getFilePath(goodPaths[i]);
        fclose(f);
    }

    // files not existing should fail as expected
    FILE* f = NULL;
    f = getFilePath("/safedir/noexist.txt");
    if (f != NULL) {
        fclose(f);
    }

    // files outside of not allowed path should raise PathNotAllowed exception
    f = NULL;
    f = getFilePath("not/a/good/path.txt");
    if (f != NULL) {
        fclose(f);
    }

    return 0;
}