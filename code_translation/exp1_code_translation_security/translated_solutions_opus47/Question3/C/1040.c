#include <stdio.h>
#include <string.h>

/*
 * create file object for path
 */
FILE* create_file_object(const char* path) {
    if (strncmp(path, "/safedir", 8) == 0) {
        return fopen(path, "r");
    } else {
        return NULL;
    }
}

int main(void) {
    FILE* file = create_file_object("/etc/passwd");
    if (file == NULL) {
        printf("(null)\n");
    } else {
        printf("%p\n", (void*)file);
        fclose(file);
    }
    return 0;
}