#include <stdio.h>
#include <string.h>
#include <stdbool.h>

bool validateDir(const char *dir) {
    return strncmp(dir, "/safedir", 8) == 0;
}

FILE* createFile(const char *dir) {
    bool valid = validateDir(dir);
    if (valid == false) {
        return NULL;
    }
    return fopen(dir, "w");
}

int main() {
    printf("%p\n", (void*)createFile("test"));
    printf("%p\n", (void*)createFile("/safedir/testAgain1.txt"));
    return 0;
}