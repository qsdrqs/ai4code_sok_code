#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE* test(const char* path) {
    if (strstr(path, "/safedir") != NULL) {
        return fopen(path, "r");
    }
    fprintf(stderr, "Exception: no access\n");
    exit(1);
}

int main() {
    //FILE* j = test("/junk");
    FILE* k = test("/safedir/junk");
    
    return 0;
}