#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE* path_to_file(const char* path) {
    /* converts the path to a file object */
    FILE* rtn;
    
    if (strstr(path, "/safedir") != NULL) {
        rtn = fopen(path, "r");
    } else {
        rtn = NULL;
    }
    
    return rtn;
}

int main() {
    FILE* file1 = path_to_file("/safedir/random.txt");
    FILE* file2 = path_to_file("/notsafedir/random.txt");
    
    if (file1 != NULL) {
        printf("File pointer: %p\n", (void*)file1);
        fclose(file1);  // Don't forget to close the file
    } else {
        printf("(null)\n");
    }
    
    if (file2 != NULL) {
        printf("File pointer: %p\n", (void*)file2);
        fclose(file2);  // Don't forget to close the file
    } else {
        printf("(null)\n");
    }
    
    return 0;
}