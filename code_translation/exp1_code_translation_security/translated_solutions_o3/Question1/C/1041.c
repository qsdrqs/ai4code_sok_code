/*  caesar.c  */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* Return a newly-allocated string that is “key” positions higher in ASCII. */
char *encrypt(const char *str, int key)
{
    size_t len = strlen(str);
    char *out = malloc(len + 1);          /* +1 for the terminating '\0' */
    if (!out) {
        perror("malloc");
        exit(EXIT_FAILURE);
    }

    for (size_t i = 0; i < len; ++i)
        out[i] = (char)((unsigned char)str[i] + key);

    out[len] = '\0';
    return out;
}

/* Return a newly-allocated string that is “key” positions lower in ASCII. */
char *decrypt(const char *str, int key)
{
    size_t len = strlen(str);
    char *out = malloc(len + 1);
    if (!out) {
        perror("malloc");
        exit(EXIT_FAILURE);
    }

    for (size_t i = 0; i < len; ++i)
        out[i] = (char)((unsigned char)str[i] - key);

    out[len] = '\0';
    return out;
}

int main(void)
{
    char *msg = encrypt("this is cool if it really works", 2);
    char *res = decrypt(msg, 2);

    printf("%s\n", msg);
    printf("%s\n", res);

    free(msg);
    free(res);
    return 0;
}