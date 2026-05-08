#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Returns a FILE* if the path is allowed and opens successfully, 
// otherwise returns NULL (equivalent to raising an exception in Python)
FILE* get_file(const char* path) {
    if (strncmp(path, "/safedir", 8) == 0) {
        return fopen(path, "r");  // Returns NULL if file can't be opened
    } else {
        return NULL;  // "Access denied"
    }
}

int main() {
    FILE* file;
    char buffer[1024];
    size_t bytes_read;
    
    // First try block - equivalent to try/except for /safedir/random.txt
    file = get_file("/safedir/random.txt");
    if (file != NULL) {
        while ((bytes_read = fread(buffer, 1, sizeof(buffer) - 1, file)) > 0) {
            buffer[bytes_read] = '\0';
            printf("%s", buffer);
        }
        printf("\n");
        fclose(file);
    } else {
        printf("Error with /safedir/random.txt\n");
    }
    
    // Second try block - equivalent to try/except for /etc/passwd
    file = get_file("/etc/passwd");
    if (file != NULL) {
        while ((bytes_read = fread(buffer, 1, sizeof(buffer) - 1, file)) > 0) {
            buffer[bytes_read] = '\0';
            printf("%s", buffer);
        }
        printf("\n");
        fclose(file);
    } else {
        printf("Access was denied\n");
    }
    
    return 0;
}