#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/*
 * Concatenate each character of msg with key, printing the
 * partially-built string at every step (exactly like the Python version).
 *
 *  msg  – original message (null-terminated C string)
 *  key  – key to insert after every character (null-terminated C string)
 *
 *  RETURN: freshly-allocated encrypted string.
 *          The caller must free( ) it after use.
 */
char *encrypt(const char *msg, const char *key)
{
    size_t msgLen = strlen(msg);
    size_t keyLen = strlen(key);

    /* final length = msgLen * (1 + keyLen)            */
    /* +1 for the terminating '\0' character           */
    size_t outLen  = msgLen * (1 + keyLen);
    char *temp     = malloc(outLen + 1);

    if (temp == NULL) {                       /* allocation check            */
        fprintf(stderr, "Out of memory!\n");
        exit(EXIT_FAILURE);
    }

    temp[0] = '\0';                           /* start with empty string     */

    size_t pos = 0;                           /* current write index         */
    for (size_t i = 0; i < msgLen; ++i) {
        printf("%s\n", temp);                 /* print the current buffer    */

        temp[pos++] = msg[i];                 /* append current character    */
        memcpy(temp + pos, key, keyLen);      /* append key                  */
        pos += keyLen;
        temp[pos]  = '\0';                    /* keep string 0-terminated    */
    }
    return temp;                              /* caller will free( )         */
}

/*
 * Extract every second character (indices 0,2,4,…) from msg.
 * The key argument is present only to mirror the Python signature;
 * it is not used by the algorithm (same as in the original script).
 *
 * RETURN: freshly-allocated decrypted string.
 *         The caller must free( ) it after use.
 */
char *decrypt(const char *msg, const char *key)
{
    (void) key;                               /* suppress “unused parameter” */
    size_t msgLen = strlen(msg);
    size_t outLen = (msgLen + 1) / 2;         /* ceil(msgLen/2)              */

    char *temp = malloc(outLen + 1);
    if (temp == NULL) {
        fprintf(stderr, "Out of memory!\n");
        exit(EXIT_FAILURE);
    }

    size_t pos = 0;
    for (size_t i = 0; i < msgLen; ++i) {
        if (i % 2 == 0)                       /* even index → keep char      */
            temp[pos++] = msg[i];
    }
    temp[pos] = '\0';
    return temp;
}

/* ------------------------------------------------------------- */

int main(void)
{
    char *enc = encrypt("a", "b");
    printf("%s\n", enc);          /* prints the encrypted text     */

    char *dec = decrypt("ab", "b");
    printf("%s\n", dec);          /* prints the decrypted text     */

    free(enc);
    free(dec);
    return 0;
}