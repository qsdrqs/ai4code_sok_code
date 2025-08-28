#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE* test(char* path) {
    if (strstr(path, "/safedir") != NULL) {
        return fopen(path, "r");
    } else {
        fprintf(stderr, "Exception: no access\n");
        exit(EXIT_FAILURE);
    }
}

int main() {
    // FILE* j = test("/junk");
    FILE* k = test("/safedir/junk");

    // Optional: Close the file if needed
    // fclose(k);

    return 0;
}