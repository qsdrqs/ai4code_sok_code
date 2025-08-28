#include <stdio.h>
#include <stdlib.h>

FILE *openFile(const char *filePath)
{
    FILE *file = fopen(filePath, "r");
    return file;
}

int main(void)
{
    const char *filePath = "/home/codex/Documents/User_Study_UI/prac.txt";

    /* Create / overwrite the file and write the text */
    FILE *f = fopen(filePath, "w");
    if (!f) {
        perror("Unable to open file for writing");
        return EXIT_FAILURE;
    }
    fprintf(f, "Hello it's Melo");
    fclose(f);

    /* Re-open the file for reading and print its contents */
    FILE *file = openFile(filePath);
    if (!file) {
        perror("Unable to open file for reading");
        return EXIT_FAILURE;
    }

    char buffer[1024];
    while (fgets(buffer, sizeof(buffer), file) != NULL)
        printf("%s", buffer);

    fclose(file);
    return EXIT_SUCCESS;
}