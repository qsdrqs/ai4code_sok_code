#include <stdio.h>
#include <stdlib.h>
#include <string.h>

const char ALPHA[] = "abcdefghijklmnopqrstuvwxyz";

/* --------------------------------------------------------- */
/*  encrypt : reproduces the Python encrypt(…) function       */
/* --------------------------------------------------------- */
char *encrypt(const char *src, int key)
{
    size_t slen = strlen(src);

    /* Enough room for every input character plus the final '\0' */
    char *newString = (char *)malloc(slen + 1);
    if (!newString) {
        fprintf(stderr, "Out of memory\n");
        exit(EXIT_FAILURE);
    }

    size_t pos = 0;                 /* write index in newString */
    for (size_t i = 0; i < slen; ++i) {
        char c = src[i];

        if (c == ' ') {             /* keep blanks exactly like Python */
            newString[pos++] = ' ';
            continue;
        }

        /* Find the index of c in ALPHA[] (equivalent to alpha.index(c)) */
        const char *p = strchr(ALPHA, c);
        if (!p)                     /* character not in alphabet */
            continue;               /* silently ignore, just like Python would */

        int alphaIndex = (int)(p - ALPHA);
        printf("%d\n", alphaIndex); /* keeps the original debug print */

        /*---  reproduce the original wrap-around logic  ---*/
        if (alphaIndex + key > (int)(strlen(ALPHA) - 1)) {
            alphaIndex = key - ((int)strlen(ALPHA) - 1 - alphaIndex);
        }

        newString[pos++] = ALPHA[alphaIndex + key];
    }

    newString[pos] = '\0';
    return newString;
}

/* --------------------------------------------------------- */
/*  decrypt : reproduces the Python decrypt(…) function       */
/* --------------------------------------------------------- */
char *decrypt(const char *src, int key)
{
    size_t slen = strlen(src);

    char *newString = (char *)malloc(slen + 1);
    if (!newString) {
        fprintf(stderr, "Out of memory\n");
        exit(EXIT_FAILURE);
    }

    size_t pos = 0;
    for (size_t i = 0; i < slen; ++i) {
        char c = src[i];

        if (c == ' ')               /* Python skipped blanks here */
            continue;

        const char *p = strchr(ALPHA, c);
        if (!p)
            continue;

        int alphaIndex = (int)(p - ALPHA);
        newString[pos++] = ALPHA[alphaIndex - key];
    }

    newString[pos] = '\0';
    return newString;
}

/* --------------------------------------------------------- */
/*  main : quick demo, identical to the Python script above   */
/* --------------------------------------------------------- */
int main(void)
{
    char *encrypted = encrypt("hello worlz", 3);
    printf("Encrypted: %s\n", encrypted);

    char *decrypted = decrypt(encrypted, 3);
    printf("Decrypted: %s\n", decrypted);

    free(encrypted);
    free(decrypted);
    return 0;
}