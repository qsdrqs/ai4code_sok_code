#include <stdio.h>
#include <string.h>

void funcPath(const char *path) {
    if (strstr(path, "/safedir") != NULL) {
        FILE *fp = fopen(path, "r");
        if (fp != NULL) {
            // Equivalent to Python's print(open(path)) which prints the file object repr
            printf("<FILE *> name='%s', mode='r'\n", path);
            fclose(fp);
        }
    } else {
        printf("file not found or unsafe for access.\n");
    }
}

int main(void) {
    funcPath("/safedir/Jim");
    return 0;
}