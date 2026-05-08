#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

bool check_dir(const char *path) {
    if (path[0] == '/' && path[1] == 's' && path[2] == 'a' && path[3] == 'f'
        && path[4] == 'e' && path[5] == 'd' && path[6] == 'i' && path[7] == 'r') {
        return true;
    } else {
        return false;
    }
}

FILE *get_file_object(const char *path) {
    FILE *file_object = NULL;
    if (check_dir(path)) {
        file_object = fopen(path, "r");
        if (file_object == NULL) {
            printf("Error\n");
            exit(EXIT_FAILURE);
        }
    } else {
        exit(EXIT_FAILURE);
    }
    return file_object;
}

int main(void) {
    FILE *fp = get_file_object("/safedir/test1.txt");
    printf("%p\n", (void *)fp);
    return 0;
}