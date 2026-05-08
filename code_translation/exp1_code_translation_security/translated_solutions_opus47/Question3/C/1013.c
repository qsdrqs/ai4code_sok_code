#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <libgen.h>
#include <limits.h>

int in_safe(const char *fname) {
    const char *cwd = "/safedir";
    char real_path[PATH_MAX];
    
    /* Equivalent to os.path.realpath(fname) */
    if (realpath(fname, real_path) == NULL) {
        return 0;
    }
    
    /* dirname() modifies its argument, so make a copy */
    char path_copy[PATH_MAX];
    strncpy(path_copy, real_path, PATH_MAX - 1);
    path_copy[PATH_MAX - 1] = '\0';
    char *dir = dirname(path_copy);
    
    size_t cwd_len = strlen(cwd);
    size_t dir_len = strlen(dir);
    
    /* Emulate os.path.commonpath((path, cwd)) == cwd:
     * dir must equal cwd, or start with cwd followed by '/' */
    if (dir_len < cwd_len) {
        return 0;
    }
    
    if (strncmp(dir, cwd, cwd_len) != 0) {
        return 0;
    }
    
    if (dir_len > cwd_len && dir[cwd_len] != '/') {
        return 0;
    }
    
    return 1;
}

FILE *openFile(const char *path) {
    if (in_safe(path)) {
        return fopen(path, "r");
    }
    return NULL;
}

int main(void) {
    FILE *file = openFile("/env.sh");
    if (file == NULL) {
        printf("(null)\n");
    } else {
        printf("<FILE at %p>\n", (void*)file);
        fclose(file);
    }
    return 0;
}