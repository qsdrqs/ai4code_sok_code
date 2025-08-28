#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to open a file if the path starts with "/safedir"
FILE* get_file(const char* path) {
    if (strncmp(path, "/safedir", 8) == 0) {
        return fopen(path, "r");
    } else {
        return NULL;
    }
}

int main() {
    FILE* file;

    // First try block: attempt to read "/safedir/random.txt"
    file = get_file("/safedir/random.txt");
    if (file == NULL) {
        printf("Error with /safedir/random.txt\n");
    } else {
        // Determine file size
        fseek(file, 0, SEEK_END);
        long file_size = ftell(file);
        fseek(file, 0, SEEK_SET);

        if (file_size < 0) {
            printf("Error with /safedir/random.txt\n");
            fclose(file);
        } else {
            size_t buffer_size = (size_t)file_size;
            char* buffer = (char*)malloc(buffer_size);
            if (buffer == NULL) {
                printf("Error with /safedir/random.txt\n");
                fclose(file);
            } else {
                size_t bytes_read = fread(buffer, 1, buffer_size, file);
                if (bytes_read != buffer_size || ferror(file)) {
                    printf("Error with /safedir/random.txt\n");
                } else {
                    fwrite(buffer, 1, buffer_size, stdout);
                }
                free(buffer);
                fclose(file);
            }
        }
    }

    // Second try block: attempt to read "/etc/passwd"
    file = get_file("/etc/passwd");
    if (file == NULL) {
        printf("Access was denied\n");
    } else {
        // Determine file size
        fseek(file, 0, SEEK_END);
        long file_size = ftell(file);
        fseek(file, 0, SEEK_SET);

        if (file_size < 0) {
            printf("Access was denied\n");
            fclose(file);
        } else {
            size_t buffer_size = (size_t)file_size;
            char* buffer = (char*)malloc(buffer_size);
            if (buffer == NULL) {
                printf("Access was denied\n");
                fclose(file);
            } else {
                size_t bytes_read = fread(buffer, 1, buffer_size, file);
                if (bytes_read != buffer_size || ferror(file)) {
                    printf("Access was denied\n");
                } else {
                    fwrite(buffer, 1, buffer_size, stdout);
                }
                free(buffer);
                fclose(file);
            }
        }
    }

    return 0;
}