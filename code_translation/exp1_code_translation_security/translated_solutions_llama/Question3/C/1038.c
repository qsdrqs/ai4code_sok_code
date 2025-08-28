#include <stdio.h>
#include <stdlib.h>

// Function to get the file name and path
void get_file_path(FILE* file, char** name, char** path) {
    // Get the file name and path from the file descriptor
    // This is not directly possible with FILE*
    // You would need to use fileno() and then try to get the file name from /proc or similar
    // Here, I'll provide an example using fd and then trying to construct the path
    #ifdef _GNU_SOURCE
        #include <fcntl.h>
        #include <libgen.h>
        #include <limits.h>

        int fd = fileno(file);
        char fd_path[PATH_MAX];
        char file_path[PATH_MAX];

        snprintf(fd_path, PATH_MAX, "/proc/self/fd/%d", fd);
        ssize_t len = readlink(fd_path, file_path, PATH_MAX - 1);
        if (len != -1)
            file_path[len] = '\0';

        *path = strdup(dirname(file_path));
        *name = strdup(basename(file_path));
    #else
        // Handle the case when _GNU_SOURCE is not defined
        // You might need to provide the file name and path when opening the file
        // For example:
        *name = "example.txt";
        *path = "/path/to/example.txt";
    #endif
}

int main() {
    // Example usage:
    FILE* file = fopen("example.txt", "r");

    char* name;
    char* path;

    get_file_path(file, &name, &path);

    printf("File Name: %s\n", name);
    printf("File Path: %s\n", path);

    free(name);
    free(path);

    fclose(file);

    return 0;
}