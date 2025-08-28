#include <stdio.h>
#include <string.h>

char* path_from_str(const char* p) {
    // Check if the path starts with "/safedir"
    if (strncmp(p, "/safedir", 8) != 0 || (p[8] != '/' && p[8] != '\0')) {
        return NULL;
    }
    return (char*)p;
}

int main() {
    const char* path1 = path_from_str("/safedir/junk");
    const char* path2 = path_from_str("/bin");
    const char* path3 = path_from_str("/safedir/doesntexist");

    if (path1) {
        printf("%s\n", path1);
    } else {
        printf("None\n");
    }

    if (path2) {
        printf("%s\n", path2);
    } else {
        printf("None\n");
    }

    if (path3) {
        printf("%s\n", path3);
    } else {
        printf("None\n");
    }

    return 0;
}