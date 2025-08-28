#include <stdio.h>
#include <string.h>

// Function to convert the path to a file pointer
FILE* path_to_file(const char* path) {
    FILE* rtn = NULL;
    // Check if the path contains "/safedir"
    if (strstr(path, "/safedir") != NULL) {
        rtn = fopen(path, "r");
    }
    return rtn;
}

int main() {
    FILE* file1 = path_to_file("/safedir/random.txt");
    if (file1 != NULL) {
        printf("File opened successfully: /safedir/random.txt\n");
        fclose(file1);
    } else {
        printf("Failed to open file: /safedir/random.txt\n");
    }

    FILE* file2 = path_to_file("/notsafedir/random.txt");
    if (file2 != NULL) {
        printf("File opened successfully: /notsafedir/random.txt\n");
        fclose(file2);
    } else {
        printf("Failed to open file: /notsafedir/random.txt\n");
    }

    return 0;
}