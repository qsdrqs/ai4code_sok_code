#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool validateDir(const char *dir) {
    return strncmp(dir, "/safedir", 8) == 0;
}

FILE* createFile(const char *dir) {
    bool valid = validateDir(dir);
    if (!valid) {
        return NULL;
    }
    return fopen(dir, "w");
}

int main() {
    FILE *file1 = createFile("test");
    if (file1 == NULL) {
        printf("Failed to create file: test\n");
    } else {
        printf("File created: test\n");
        fclose(file1);
    }

    FILE *file2 = createFile("/safedir/testAgain1.txt");
    if (file2 == NULL) {
        printf("Failed to create file: /safedir/testAgain1.txt\n");
    } else {
        printf("File created: /safedir/testAgain1.txt\n");
        fclose(file2);
    }

    return 0;
}