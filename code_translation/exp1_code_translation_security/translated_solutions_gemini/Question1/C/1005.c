#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fernet.h> // The header for the fernet-c library

/**
 * @brief Encrypts a message using a Fernet key.
 * 
 * @param msg The plaintext message to encrypt.
 * @param msg_len The length of the plaintext message.
 * @param key The URL-safe base64-encoded Fernet key.
 * @param encrypted_len A pointer to a size_t variable where the length of the 
 *                      encrypted token will be stored.
 * @return A dynamically allocated, null-terminated string containing the 
 *         encrypted token. The caller is responsible for freeing this memory.
 *         Returns NULL on failure.
 */
char* encrypt_message(const unsigned char* msg, size_t msg_len, const char* key, size_t* encrypted_len) {
    // Calculate the required buffer size for the encrypted token.
    // Fernet adds overhead (timestamp, IV, HMAC), so the output is larger.
    size_t output_size = fernet_encrypt_output_size(msg_len);
    char* output = (char*)malloc(output_size);
    if (output == NULL) {
        perror("Failed to allocate memory for encryption output");
        return NULL;
    }

    // Perform the encryption.
    // The fernet_encrypt function returns the number of bytes written.
    int bytes_written = fernet_encrypt(
        (uint8_t*)output, // Output buffer
        output_size,      // Size of the output buffer
        (const uint8_t*)key, // The key
        msg,              // The message to encrypt
        msg_len           // The length of the message
    );

    if (bytes_written < 0) {
        fprintf(stderr, "Encryption failed.\n");
        free(output);
        return NULL;
    }

    *encrypted_len = (size_t)bytes_written;
    return output;
}

/**
 * @brief Decrypts a Fernet token using a key.
 * 
 * @param token The encrypted Fernet token.
 * @param key The URL-safe base64-encoded Fernet key.
 * @param decrypted_len A pointer to a size_t variable where the length of the
 *                      decrypted message will be stored.
 * @return A dynamically allocated buffer containing the decrypted message.
 *         The caller is responsible for freeing this memory.
 *         Returns NULL on failure (e.g., invalid key, corrupted token).
 */
unsigned char* decrypt_message(const char* token, const char* key, size_t* decrypted_len) {
    // The decrypted message will be smaller than the token.
    // We can allocate a buffer of the same size as the token for simplicity,
    // as it's guaranteed to be large enough.
    size_t token_len = strlen(token);
    unsigned char* output = (unsigned char*)malloc(token_len + 1); // +1 for potential null terminator
    if (output == NULL) {
        perror("Failed to allocate memory for decryption output");
        return NULL;
    }

    // Perform the decryption.
    // The function returns the number of bytes in the decrypted message,
    // or a negative value on error.
    int bytes_written = fernet_decrypt(
        output,           // Output buffer for the decrypted message
        token_len,        // Size of the output buffer
        (const uint8_t*)key, // The key
        (const uint8_t*)token // The token to decrypt
    );

    if (bytes_written < 0) {
        fprintf(stderr, "Decryption failed. The token may be invalid or the key incorrect.\n");
        free(output);
        return NULL;
    }

    *decrypted_len = (size_t)bytes_written;
    output[*decrypted_len] = '\0'; // Null-terminate the result for easy printing
    return output;
}

int main() {
    // 1. Generate a Fernet key.
    // A Fernet key is a 44-byte URL-safe base64-encoded string.
    // We need a buffer of size 45 to include the null terminator.
    char key[45];
    fernet_generate_key((uint8_t*)key);

    // 2. Define the message.
    const char* msg_str = "a much longer message with punctuation!";
    const unsigned char* msg = (const unsigned char*)msg_str;
    size_t msg_len = strlen(msg_str);

    printf("Original Key: %s\n", key);
    printf("Original Msg: %s\n\n", msg);

    // 3. Encrypt the message.
    size_t encrypted_len = 0;
    char* e = encrypt_message(msg, msg_len, key, &encrypted_len);
    if (e == NULL) {
        return 1; // Exit with an error code
    }

    // 4. Decrypt the message.
    size_t decrypted_len = 0;
    unsigned char* d = decrypt_message(e, key, &decrypted_len);
    if (d == NULL) {
        free(e);
        return 1; // Exit with an error code
    }

    // 5. Print the results.
    printf("Encrypted: %s\n", e);
    printf("Decrypted: %s\n", d);

    // 6. Clean up dynamically allocated memory.
    free(e);
    free(d);

    return 0;
}