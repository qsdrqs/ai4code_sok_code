#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int check_dir(const char* path) {
    if (strlen(path) >= 8 && 
        path[0] == '/' && 
        path[1] == 's' && 
        path[2] == 'a' && 
        path[3] == 'f' && 
        path[4] == 'e' && 
        path[5] == 'd' && 
        path[6] == 'i' && 
        path[7] == 'r') {
        return 1; // True
    } else {
        return 0; // False
    }
}

FILE* get_file_object(const char* path) {
    if (check_dir(path)) {
        FILE* file_object = fopen(path, "r");
        if (file_object == NULL) {
            printf("Error\n");
            exit(1);
        }
        return file_object;
    } else {
        exit(1);
    }
}

int main() {
    FILE* file = get_file_object("/safedir/test1.txt");
    printf("%p\n", (void*)file);
    
    // Don't forget to close the file when done
    if (file != NULL) {
        fclose(file);
    }
    
    return 0;
}