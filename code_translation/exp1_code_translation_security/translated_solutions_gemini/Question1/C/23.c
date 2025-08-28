#include <stdio.h>   // For input/output functions like printf
#include <string.h>  // For string functions like strlen
#include <stdlib.h>  // For memory allocation functions like malloc and free

/*
 * C version of the encryption function.
 * It's TECHNICALLY a pseudo-cipher.
 * Note: This function allocates new memory for the result.
 * The caller is responsible for freeing this memory using free().
 * Right now text and key must be equal length.
 */
char* encrypt(const char* text, const char* key) {
    // Ensure text and key are the same length to prevent errors.
    if (strlen(text) != strlen(key)) {
        fprintf(stderr, "Error: Text and key must have the same length.\n");
        return NULL;
    }

    size_t len = strlen(text);
    // Allocate memory for the result string (+1 for the null terminator '\0')
    char* result = (char*)malloc(len + 1);
    if (result == NULL) {
        perror("Failed to allocate memory for encryption");
        return NULL; // Return NULL if memory allocation fails
    }

    for (size_t i = 0; i < len; i++) {
        // In C, characters are already integer types (their ASCII value).
        // We add the values and cast the result back to a char.
        int this_char = text[i] + key[i];
        result[i] = (char)this_char;
    }
    // Add the null terminator to mark the end of the string.
    result[len] = '\0';

    return result;
}

/*
 * C version of the decryption function.
 * Note: This function also allocates new memory for the result.
 * The caller is responsible for freeing this memory using free().
 */
char* decrypt(const char* text, const char* key) {
    // Ensure text and key are the same length to prevent errors.
    if (strlen(text) != strlen(key)) {
        fprintf(stderr, "Error: Text and key must have the same length.\n");
        return NULL;
    }

    size_t len = strlen(text);
    // Allocate memory for the result string (+1 for the null terminator '\0')
    char* result = (char*)malloc(len + 1);
    if (result == NULL) {
        perror("Failed to allocate memory for decryption");
        return NULL; // Return NULL if memory allocation fails
    }

    for (size_t i = 0; i < len; i++) {
        // Subtract the key's character value from the text's character value.
        int this_char = text[i] - key[i];
        result[i] = (char)this_char;
    }
    // Add the null terminator.
    result[len] = '\0';

    return result;
}

int main() {
    const char* key = "aaaaaaa";
    const char* decrypted = "abcdefg";

    // Encrypt the original text
    char* encrypted = encrypt(decrypted, key);
    if (encrypted == NULL) {
        return 1; // Exit with an error code if encryption failed
    }
    printf("%s\n", encrypted);

    // Decrypt the encrypted text to get the original back
    char* decrypted_back = decrypt(encrypted, key);
    if (decrypted_back == NULL) {
        free(encrypted); // Still need to free the memory from the successful encryption
        return 1; // Exit with an error code
    }
    printf("%s\n", decrypted_back);

    // IMPORTANT: Free the dynamically allocated memory to prevent memory leaks.
    // The 'encrypted' and 'decrypted_back' pointers were returned by malloc.
    free(encrypted);
    free(decrypted_back);

    return 0; // Indicate successful execution
}