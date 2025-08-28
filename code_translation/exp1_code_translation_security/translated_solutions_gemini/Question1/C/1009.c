/**
 * C translation of a Python AES-EAX encryption/decryption example.
 * This code uses the libtomcrypt library.
 *
 * To compile on Linux/macOS:
 * gcc -o eax_example eax_example.c -ltomcrypt
 *
 * To run:
 * ./eax_example
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

// All libtomcrypt functions and definitions are in this header.
#include <tomcrypt.h>

// Helper function to print byte arrays in hexadecimal format.
void print_hex(const char* label, const unsigned char* data, int len) {
    printf("%s: ", label);
    for (int i = 0; i < len; i++) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

/**
 * @brief Encrypts a string using AES-EAX mode.
 *
 * @param message The plaintext string to encrypt.
 * @param key The 16-byte encryption key.
 * @param nonce The 16-byte nonce.
 * @param ciphertext Buffer to store the resulting ciphertext. Its size must be at least strlen(message).
 * @param ciphertext_len Pointer to store the length of the ciphertext.
 * @param tag Buffer to store the 16-byte authentication tag.
 * @param tag_len Pointer to store the length of the tag.
 * @return int 0 on success, -1 on failure.
 */
int encodeString(
    const char* message,
    const unsigned char* key,
    const unsigned char* nonce,
    unsigned char* ciphertext,
    unsigned long* ciphertext_len,
    unsigned char* tag,
    unsigned long* tag_len
) {
    eax_state eax;
    int err;
    int aes_idx = find_cipher("aes");
    if (aes_idx == -1) {
        fprintf(stderr, "Error: AES cipher not found.\n");
        return -1;
    }

    unsigned long message_len = strlen(message);
    *ciphertext_len = message_len; // EAX ciphertext length is same as plaintext
    *tag_len = 16; // We will use a 16-byte tag

    // 1. Initialize the EAX context with key and nonce
    // The 'header' (Associated Data) is NULL since we are not using it.
    if ((err = eax_init(&eax, aes_idx, key, 16, nonce, 16, NULL, 0)) != CRYPT_OK) {
        fprintf(stderr, "eax_init failed: %s\n", error_to_string(err));
        return -1;
    }

    // 2. Encrypt the message
    // In libtomcrypt, this is a single call for EAX.
    if ((err = eax_encrypt(&eax, (const unsigned char*)message, message_len, ciphertext, *ciphertext_len)) != CRYPT_OK) {
        fprintf(stderr, "eax_encrypt failed: %s\n", error_to_string(err));
        return -1;
    }

    // 3. Finalize and get the authentication tag
    if ((err = eax_done(&eax, tag, tag_len)) != CRYPT_OK) {
        fprintf(stderr, "eax_done failed: %s\n", error_to_string(err));
        return -1;
    }

    return 0; // Success
}

/**
 * @brief Decrypts a string using AES-EAX mode and verifies its integrity.
 *
 * @param ciphertext The ciphertext to decrypt.
 * @param ciphertext_len The length of the ciphertext.
 * @param tag The 16-byte authentication tag to verify.
 * @param key The 16-byte decryption key.
 * @param nonce The 16-byte nonce.
 * @param decoded_message Buffer to store the resulting plaintext. Must be large enough.
 * @param decoded_message_len Pointer to store the length of the plaintext.
 * @return int 0 on success, -1 on failure (including tag verification failure).
 */
int decodeString(
    const unsigned char* ciphertext,
    unsigned long ciphertext_len,
    const unsigned char* tag,
    const unsigned char* key,
    const unsigned char* nonce,
    char* decoded_message,
    unsigned long* decoded_message_len
) {
    eax_state eax;
    int err;
    int aes_idx = find_cipher("aes");
    if (aes_idx == -1) {
        fprintf(stderr, "Error: AES cipher not found.\n");
        return -1;
    }

    *decoded_message_len = ciphertext_len;

    // 1. Initialize the EAX context with key and nonce
    if ((err = eax_init(&eax, aes_idx, key, 16, nonce, 16, NULL, 0)) != CRYPT_OK) {
        fprintf(stderr, "eax_init failed: %s\n", error_to_string(err));
        return -1;
    }

    // 2. Decrypt the ciphertext
    if ((err = eax_decrypt(&eax, ciphertext, ciphertext_len, (unsigned char*)decoded_message, *decoded_message_len)) != CRYPT_OK) {
        fprintf(stderr, "eax_decrypt failed: %s\n", error_to_string(err));
        return -1;
    }

    // 3. Finalize and verify the authentication tag
    // This is the crucial step that corresponds to "decrypt_and_verify".
    // It will return CRYPT_INVALID_PACKET if the tag does not match.
    unsigned long tag_len = 16;
    if ((err = eax_done(&eax, (unsigned char*)tag, &tag_len)) != CRYPT_OK) {
        fprintf(stderr, "Tag verification failed: %s\n", error_to_string(err));
        return -1;
    }

    // Null-terminate the resulting string
    decoded_message[*decoded_message_len] = '\0';

    return 0; // Success
}


int main() {
    // Register the AES cipher descriptor with libtomcrypt
    if (register_cipher(&aes_desc) == -1) {
        fprintf(stderr, "Error registering AES cipher.\n");
        return EXIT_FAILURE;
    }

    // Initialize a random number generator (PRNG)
    prng_state prng;
    unsigned char entropy[1024]; // Buffer for entropy
    int err;
    if ((err = rng_make_prng(128, find_prng("yarrow"), &prng, NULL)) != CRYPT_OK) {
        fprintf(stderr, "Error setting up PRNG: %s\n", error_to_string(err));
        return EXIT_FAILURE;
    }
    // It's good practice to add entropy from a system source, but for this example,
    // we will skip it to keep it simple and deterministic. For production, use:
    // yarrow_add_entropy(entropy, 1024, &prng);

    // --- Main Logic ---
    const char* originalMessage = "secret data";
    unsigned long originalMessage_len = strlen(originalMessage);

    // Buffers for key, nonce, ciphertext, tag, and the final decoded message
    unsigned char key[16];
    unsigned char nonce[16];
    unsigned char ciphertext[64]; // Ensure buffer is large enough
    unsigned char tag[16];
    char decodedString[64]; // Ensure buffer is large enough

    // Generate random key and nonce (equivalent to get_random_bytes)
    yarrow_read(key, 16, &prng);
    yarrow_read(nonce, 16, &prng);

    printf("Original Message: %s\n\n", originalMessage);
    print_hex("Generated Key", key, 16);
    print_hex("Generated Nonce", nonce, 16);
    printf("\n---ENCRYPTING---\n");

    unsigned long ciphertext_len;
    unsigned long tag_len;

    if (encodeString(originalMessage, key, nonce, ciphertext, &ciphertext_len, tag, &tag_len) != 0) {
        fprintf(stderr, "Encryption failed!\n");
        return EXIT_FAILURE;
    }

    print_hex("Ciphertext", ciphertext, ciphertext_len);
    print_hex("Tag", tag, tag_len);
    printf("\n---DECRYPTING---\n");

    unsigned long decoded_len;
    if (decodeString(ciphertext, ciphertext_len, tag, key, nonce, decodedString, &decoded_len) != 0) {
        fprintf(stderr, "Decryption or verification failed!\n");
        return EXIT_FAILURE;
    }

    printf("Decoded String: %s\n\n", decodedString);

    // Final verification
    if (strcmp(originalMessage, decodedString) == 0) {
        printf("SUCCESS: Original and decoded messages match.\n");
    } else {
        printf("FAILURE: Messages do not match.\n");
    }

    // Clean up
    unregister_cipher(&aes_desc);
    prng_done(&prng);

    return EXIT_SUCCESS;
}