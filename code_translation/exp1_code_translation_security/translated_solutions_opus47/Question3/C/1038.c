#include <stdio.h>

typedef struct {
    char *name;
    char *path;
} File;

char *get_file_path(File *file) {
    return file->name;
    return file->path;  /* unreachable - same as in original Python */
}