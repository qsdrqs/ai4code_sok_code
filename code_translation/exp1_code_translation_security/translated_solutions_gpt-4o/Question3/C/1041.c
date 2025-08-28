#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE* open_file(const char* path) {
    if (strncmp(path, "/safedir", 8) == 0) {
        FILE* file = fopen(path, "r");
        if (file == NULL) {
            perror("Error opening file");
            exit(EXIT_FAILURE);
        }
        return file;
    } else {
        fprintf(stderr, "ValueError: Path must start with /safedir\n");
        exit(EXIT_FAILURE);
    }
}

int main() {
    FILE* res1 = open_file("/safedir/random.txt");
    printf("File opened successfully: %p\n", (void*)res1);
    fclose(res1);

    FILE* res2 = open_file("blah");  // This will cause the program to exit with an error
    printf("File opened successfully: %p\n", (void*)res2);
    fclose(res2);

    return 0;
}