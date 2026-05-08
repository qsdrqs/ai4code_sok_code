#include <stdio.h>
#include <string.h>

FILE* read_in(const char* path) {
    if (strstr(path, "/safedir") != NULL) {
        return fopen(path, "r");
    } else {
        return NULL;
    }
}