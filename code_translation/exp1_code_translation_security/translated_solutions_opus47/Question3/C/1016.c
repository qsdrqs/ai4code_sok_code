#include <stdio.h>
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

int main(void) {
    FILE* result1 = path_to_file("/safedir/random.txt");
    if (result1 != NULL) {
        printf("<FILE object at %p>\n", (void*)result1);
        fclose(result1);
    } else {
        printf("(null)\n");
    }
    
    FILE* result2 = path_to_file("/notsafedir/random.txt");
    if (result2 != NULL) {
        printf("<FILE object at %p>\n", (void*)result2);
        fclose(result2);
    } else {
        printf("(null)\n");
    }
    
    return 0;
}