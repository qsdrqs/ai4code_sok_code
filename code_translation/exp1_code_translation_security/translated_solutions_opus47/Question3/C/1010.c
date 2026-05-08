#include <stdio.h>
#include <string.h>

/*
 * Open a file
 */
FILE* open_path(const char* filename) {
    // Worry about ".." and any way of getting out of director
    const char* match = strstr(filename, "/safedir/");
    if (match != filename) {  // In Python: find() returns 0 only if substring is at start
        return NULL;
    }
    // Possible weird cases with encodings of ".." I'd have to research
    if (strstr(filename, "/../") != NULL) {  // Safe but overly conservative
        return NULL;
    }
    // Open filename
    return fopen(filename, "w");
}

// int main(void) {
//     FILE* f = open_path("/safedir/Jim");
//     if (f == NULL) {
//         printf("(null)\n");
//     } else {
//         printf("File opened\n");
//         fclose(f);
//     }
//     return 0;
// }