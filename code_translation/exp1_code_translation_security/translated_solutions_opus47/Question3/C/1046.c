#include <stdio.h>
#include <string.h>

FILE *openFile(const char *path) {
    /* path.partition("/safedir") - find the separator */
    const char *sep = strstr(path, "/safedir");
    
    /* newpath = sep + tail
     * If "/safedir" is found, sep points to it in the original buffer,
     * and everything after it (tail) follows naturally in memory.
     * If not found, Python gives sep="" and tail="", so newpath="". */
    const char *newpath = (sep != NULL) ? sep : "";
    
    /* return newpath.open() - default Python open() uses read mode */
    return fopen(newpath, "r");
}