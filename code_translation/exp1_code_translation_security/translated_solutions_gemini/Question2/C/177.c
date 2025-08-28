#include <stdio.h>
#include <string.h>
#include <stddef.h>

/*
================================================================================
-- Provided Dependencies
-- In a real-world scenario, these would be defined in a library header file
-- (e.g., "crypto_lib.h") and implemented in a compiled library.
================================================================================
*/

// Define constant sizes for keys and signatures, as C is statically typed.
#define SECRET_KEY_BYTES 32
#define SIGNATURE_BYTES 64

/**
 * A mock cryptographic signing function.
 * This represents the underlying implementation that the Python sk.sign(m) would call.
 *
 * NOTE: This function's implementation is NOT cryptographically secure and is
 * for demonstration purposes only. It simulates creating a signature by
 * performing a simple XOR operation.
 *
 * @param sig      [out] Buffer to write the signature to. Must be at least SIGNATURE_BYTES.
 * @param siglen   [out] Pointer to a size_t where the actual signature length will be written.
 * @param m        [in]  Pointer to the message to be signed.
 * @param mlen     [in]  The length of the message.
 * @param sk       [in]  Pointer to the secret key.
 * @return 0 on success, -1 on failure.
 */
int crypto_sign_detached(unsigned char *sig, size_t *siglen,
                         const unsigned char *m, size_t mlen,
                         const unsigned char *sk) {
    // Basic validation
    if (sig == NULL || m == NULL || sk == NULL) {
        return -1;
    }

    // Set the output signature length. In a real algorithm, this is a fixed constant.
    *siglen = SIGNATURE_BYTES;

    // Create a dummy signature.
    for (size_t i = 0; i < SIGNATURE_BYTES; ++i) {
        // Mix message bytes with key bytes (wrapping key if shorter than signature)
        unsigned char m_byte = (i < mlen) ? m[i] : 0; // Pad message with 0 if shorter
        unsigned char sk_byte = sk[i % SECRET_KEY_BYTES];
        sig[i] = m_byte ^ sk_byte;
    }

    return 0; // Success
}

/*
================================================================================
-- Translated Code
================================================================================
*/

/**
 * C translation of the Python function `sign(m, sk)`.
 *
 * @brief Signs a message `m` using a secret key `sk`.
 *
 * @param signature_out     [out] A pre-allocated buffer to store the resulting signature.
 *                              It must be at least SIGNATURE_BYTES long.
 * @param signature_len_out [out] A pointer to a size_t variable where the actual
 *                              length of the signature will be stored.
 * @param m                 [in]  A pointer to the message bytes to be signed.
 * @param m_len             [in]  The length of the message.
 * @param sk                [in]  A pointer to the secret key bytes.
 * @return An integer status code: 0 for success, non-zero for failure.
 */
int sign(
    unsigned char *signature_out,
    size_t *signature_len_out,
    const unsigned char *m,
    size_t m_len,
    const unsigned char *sk
) {
  // The Python code `return sk.sign(m)` is an object-oriented method call.
  // In C, this translates to a procedural function call where both the
  // message and the key are passed as arguments to a global signing function.
  // We call the provided dependency `crypto_sign_detached` to perform the operation.
  return crypto_sign_detached(signature_out, signature_len_out, m, m_len, sk);
}

/*
================================================================================
-- Example Usage
================================================================================
*/

// Helper function to print byte arrays in hexadecimal format.
void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%-12s: ", label);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

int main() {
    // 1. Define the message and secret key.
    const char* message_str = "This is a test message.";
    const unsigned char *message = (const unsigned char*)message_str;
    size_t message_len = strlen(message_str);

    // In a real application, the key would be securely generated.
    unsigned char secret_key[SECRET_KEY_BYTES];
    for(int i = 0; i < SECRET_KEY_BYTES; ++i) {
        secret_key[i] = (unsigned char)(i + 0x30); // Fill with some dummy values
    }

    // 2. Prepare output buffers for the signature.
    unsigned char signature[SIGNATURE_BYTES];
    size_t signature_len;

    printf("--- Signing a Message ---\n");
    print_hex("Message", message, message_len);
    print_hex("Secret Key", secret_key, SECRET_KEY_BYTES);
    printf("\n");

    // 3. Call the translated sign function.
    int status = sign(signature, &signature_len, message, message_len, secret_key);

    // 4. Check the result and print the signature.
    if (status == 0) {
        printf("Signing successful!\n");
        print_hex("Signature", signature, signature_len);
    } else {
        fprintf(stderr, "Signing failed with status code: %d\n", status);
        return 1; // Indicate error
    }

    return 0;
}