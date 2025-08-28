#include <stdio.h>
#include <string.h>

// Assuming a structure to represent the file object
typedef struct {
    char name[256];
    char path[256];
} File;

const char* get_file_path(File file) {
    return file.name;
    // The second return statement is unreachable in Python as well as in C.
    // If you intended to return `file.path` instead, you should clarify the logic.
    // For now, we'll keep the logic as is.
    // return file.path;
}

int main() {
    File file;
    strcpy(file.name, "example.txt");
    strcpy(file.path, "/path/to/example.txt");

    printf("File name: %s\n", get_file_path(file));
    return 0;
}