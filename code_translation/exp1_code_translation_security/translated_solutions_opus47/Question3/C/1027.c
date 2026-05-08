#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE* open_file(const char* file_path) {
    // Check if path starts with "/safedir" (equivalent to Python's startswith)
    if (strncmp(file_path, "/safedir", 8) != 0) {
        fprintf(stderr, "Exception: File is outside of /safedir\n");
        exit(EXIT_FAILURE);  // Mimics raising an exception
    }
    
    // Attempt to open the file
    FILE* file_obj = fopen(file_path, "r");
    if (file_obj == NULL) {
        printf("File not found\n");
        return NULL;
    }
    return file_obj;
}

int main(void) {
    FILE* path = open_file("/safedir/Jim");
    
    // Print the file pointer (equivalent to Python's print(path))
    if (path != NULL) {
        printf("%p\n", (void*)path);
        fclose(path);  // Good practice: close the file when done
    } else {
        printf("(null)\n");
    }
    
    return 0;
}