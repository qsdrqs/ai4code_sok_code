#include <stdio.h>
#include <string.h>
#include <stdlib.h>

/*
 * Open a file
 */
FILE* open_path(const char* filename) {
    // Worry about ".." and any way of getting out of directory
    const char* match_pos = strstr(filename, "/safedir/");
    if (match_pos != filename) {  // Check if "/safedir/" is not at the beginning
        return NULL;
    }
    
    // Possible weird cases with encodings of ".." I'd have to research
    if (strstr(filename, "/../") != NULL) {  // Safe but overly conservative
        return NULL;
    }
    
    // Open filename
    return fopen(filename, "w");
}

/*
int main() {
    FILE* file = open_path("/safedir/Jim");
    if (file != NULL) {
        printf("File opened successfully\n");
        fclose(file);
    } else {
        printf("Failed to open file\n");
    }
    return 0;
}
*/