#include <stdio.h>
#include <stdlib.h>
#include <string.h>

FILE *open_file(const char *path) {
    /* Python's str.startswith("/safedir") — compare the first 8 chars */
    if (strncmp(path, "/safedir", 8) == 0) {
        FILE *f = fopen(path, "r");
        if (f == NULL) {
            /* Mirrors the implicit FileNotFoundError that open() would raise */
            perror("open_file");
            exit(EXIT_FAILURE);
        }
        return f;
    } else {
        /* Mirrors: raise ValueError("Path must start with /safedir") */
        fprintf(stderr, "ValueError: Path must start with /safedir\n");
        exit(EXIT_FAILURE);
    }
}

int main(void) {
    FILE *res1 = open_file("/safedir/random.txt");
    /* Python's print(res1) shows the file object's repr;
       the closest C analogue is printing the FILE* pointer. */
    printf("%p\n", (void *)res1);

    FILE *res2 = open_file("blah");   /* will terminate with the ValueError message */
    (void)res2;                       /* unreachable, silences unused-var warning */

    return 0;
}