#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <sys/types.h>

/*
 * Function get_file
 *
 * Input: file path
 * Returns file as FILE pointer
 *
 * If file is outside of directory "/safedir", return NULL.
 */
FILE* get_file(const char* file_path) {
    if (strncmp(file_path, "/safedir", 8) != 0) {
        printf("File must be inside /safedir\n");
        return NULL;
    }
    FILE* f = fopen(file_path, "r");
    if (f == NULL) {
        printf("File not found\n");
        return NULL;
    }
    return f;
}

int main(void) {
    /* Create the safedir directory if it doesn't exist */
    mkdir("/safedir", 0755);

    /* Write "Hello" to the test file */
    FILE* f = fopen("/safedir/test.txt", "w");
    if (f != NULL) {
        fprintf(f, "Hello");
        fclose(f);
    }

    /* Test 1: Read the file */
    FILE* test_f = get_file("/safedir/test.txt");
    if (test_f != NULL) {
        char line[256];
        if (fgets(line, sizeof(line), test_f) != NULL) {
            printf("%s\n", line);
        }
        fclose(test_f);
    }

    /* Test 2: Should fail - path is outside /safedir */
    test_f = get_file("/test.txt");
    if (test_f == NULL) {
        printf("(null)\n");
    } else {
        printf("%p\n", (void*)test_f);
        fclose(test_f);
    }

    return 0;
}