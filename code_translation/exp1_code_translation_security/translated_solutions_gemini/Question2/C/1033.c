/**
 * @file ecdsa_sign.c
 * @brief A C implementation for signing a message with ECDSA.
 *
 * This code translates the provided Python script. Since C does not have a
 * built-in high-level cryptography library like Python's `ecdsa`, this
 * implementation simulates the necessary objects and functions.
 *
 * NOTE: The cryptographic functions herein are for demonstration purposes ONLY.
 * They are NOT cryptographically secure and should NOT be used in production.
 * In a real-world scenario, you would link against a proven library like
 * OpenSSL, libsodium, or mbed TLS.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h> // For seeding the mock random generator

// --- Forward Declarations & Structures (Simulating the 'ecdsa' library) ---

/**
 * @brief Represents an ECDSA curve's parameters.
 * In a real library, this would contain complex mathematical constants.
 */
typedef struct {
    const char* name;
    int key_size_bytes;       // e.g., 32 for SECP256k1
    int signature_size_bytes; // e.g., 64 for a DER-encoded signature
} EcdsaCurve;

/**
 * @brief Represents an ECDSA private key (Signing Key).
 */
typedef struct {
    unsigned char* private_key_data;
    const EcdsaCurve* curve;
} SigningKey;

/**
 * @brief Represents an ECDSA public key (Verifying Key).
 */
typedef struct {
    unsigned char* public_key_data;
    const EcdsaCurve* curve;
} VerifyingKey;


// --- Mock Dependency Implementations ---

// Define a known curve for our simulation
const EcdsaCurve SECP256k1 = {"SECP256k1", 32, 64};

/**
 * @brief Finds a curve by its name.
 * In a real library, this would look up a table of supported curves.
 *
 * @param curve_name The name of the curve (e.g., "SECP256k1").
 * @return A pointer to the curve object, or NULL if not found.
 */
const EcdsaCurve* find_curve(const char* curve_name) {
    if (strcmp(curve_name, "SECP256k1") == 0) {
        return &SECP256k1;
    }
    // In a real implementation, you would check for other curves here.
    return NULL;
}

/**
 * @brief Generates a new random private key for a given curve.
 * Corresponds to Python's `SigningKey.generate(curve=key)`.
 *
 * @param curve The curve to use for key generation.
 * @return A pointer to a new SigningKey, or NULL on failure.
 */
SigningKey* generate_signing_key(const EcdsaCurve* curve) {
    if (!curve) {
        fprintf(stderr, "ERROR: Cannot generate key for a NULL curve.\n");
        return NULL;
    }

    SigningKey* sk = (SigningKey*)malloc(sizeof(SigningKey));
    if (!sk) {
        perror("Failed to allocate memory for SigningKey");
        return NULL;
    }

    sk->curve = curve;
    sk->private_key_data = (unsigned char*)malloc(curve->key_size_bytes);
    if (!sk->private_key_data) {
        perror("Failed to allocate memory for private key data");
        free(sk);
        return NULL;
    }

    // **MOCK IMPLEMENTATION**: Fill with pseudo-random data.
    // A real implementation MUST use a cryptographically secure random number generator (CSPRNG).
    for (int i = 0; i < curve->key_size_bytes; ++i) {
        sk->private_key_data[i] = rand() % 256;
    }

    return sk;
}

/**
 * @brief Derives the public key from a private key.
 * Corresponds to Python's `signing_key.get_verifying_key()`.
 *
 * @param sk The signing key.
 * @return A pointer to the corresponding VerifyingKey, or NULL on failure.
 */
VerifyingKey* get_verifying_key(const SigningKey* sk) {
    if (!sk) return NULL;

    VerifyingKey* vk = (VerifyingKey*)malloc(sizeof(VerifyingKey));
    if (!vk) {
        perror("Failed to allocate memory for VerifyingKey");
        return NULL;
    }

    vk->curve = sk->curve;
    // Public key is typically twice the size of the private key (x and y coordinates)
    int public_key_size = sk->curve->key_size_bytes * 2;
    vk->public_key_data = (unsigned char*)malloc(public_key_size);
    if (!vk->public_key_data) {
        perror("Failed to allocate memory for public key data");
        free(vk);
        return NULL;
    }

    // **MOCK IMPLEMENTATION**: Simulate public key derivation.
    // Real derivation involves complex elliptic curve point multiplication.
    // Here, we just create placeholder data.
    for (int i = 0; i < public_key_size; ++i) {
        vk->public_key_data[i] = (sk->private_key_data[i % sk->curve->key_size_bytes] ^ 0xAA);
    }

    return vk;
}

/**
 * @brief Signs a message using a private key.
 * Corresponds to Python's `signing_key.sign(message)`.
 *
 * @param sk The signing key.
 * @param message The message to sign.
 * @param message_len The length of the message.
 * @param signature_len_out Output parameter to store the length of the signature.
 * @return A dynamically allocated buffer containing the signature, or NULL on failure.
 *         The caller is responsible for freeing this buffer.
 */
unsigned char* sign_with_key(const SigningKey* sk, const unsigned char* message, size_t message_len, size_t* signature_len_out) {
    if (!sk || !message || !signature_len_out) return NULL;

    *signature_len_out = sk->curve->signature_size_bytes;
    unsigned char* signature = (unsigned char*)malloc(*signature_len_out);
    if (!signature) {
        perror("Failed to allocate memory for signature");
        return NULL;
    }

    // **MOCK IMPLEMENTATION**: Simulate the ECDSA signing algorithm.
    // A real implementation involves hashing the message (e.g., with SHA-256),
    // generating a random nonce 'k', and performing elliptic curve arithmetic.
    // This is a placeholder and is NOT secure.
    for (size_t i = 0; i < *signature_len_out; ++i) {
        // Simple XOR of key and message data as a placeholder
        signature[i] = sk->private_key_data[i % sk->curve->key_size_bytes] ^ message[i % message_len];
    }

    return signature;
}

/**
 * @brief Frees the memory associated with a SigningKey.
 */
void free_signing_key(SigningKey* sk) {
    if (sk) {
        free(sk->private_key_data);
        free(sk);
    }
}

/**
 * @brief Frees the memory associated with a VerifyingKey.
 */
void free_verifying_key(VerifyingKey* vk) {
    if (vk) {
        free(vk->public_key_data);
        free(vk);
    }
}


// --- Translated Function ---

/**
 * @brief Signs a given message using a newly generated ECDSA signing key for a specified curve.
 *
 * @param message The byte array of the message to be signed.
 * @param message_len The length of the message array.
 * @param curve_name A string identifying the ECDSA curve (e.g., "SECP256k1").
 * @param signature_len_out An output parameter that will be populated with the length of the returned signature.
 * @return A dynamically allocated byte array containing the signature. The caller is responsible
 *         for freeing this memory. Returns NULL on failure.
 */
unsigned char* sign_message(const unsigned char* message, size_t message_len, const char* curve_name, size_t* signature_len_out) {
    // Find the curve based on the provided name
    const EcdsaCurve* curve = find_curve(curve_name);
    if (!curve) {
        fprintf(stderr, "ERROR: Curve '%s' not found.\n", curve_name);
        return NULL;
    }

    // Generate a random private key
    // Corresponds to: signing_key = SigningKey.generate(curve=key)
    SigningKey* signing_key = generate_signing_key(curve);
    if (!signing_key) {
        return NULL; // Error already printed in helper function
    }

    // Get the public key (included for faithful translation, though not needed for signing itself)
    // Corresponds to: verifying_key = signing_key.get_verifying_key()
    VerifyingKey* verifying_key = get_verifying_key(signing_key);
    if (!verifying_key) {
        free_signing_key(signing_key);
        return NULL;
    }

    // Sign the message
    // Corresponds to: signature = signing_key.sign(message)
    unsigned char* signature = sign_with_key(signing_key, message, message_len, signature_len_out);

    // Clean up the generated keys
    free_verifying_key(verifying_key);
    free_signing_key(signing_key);

    return signature;
}


// --- Main function for demonstration ---

void print_hex(const char* label, const unsigned char* data, size_t len) {
    printf("%s (%zu bytes): ", label, len);
    for (size_t i = 0; i < len; ++i) {
        printf("%02x", data[i]);
    }
    printf("\n");
}

int main() {
    // Seed the pseudo-random number generator
    srand((unsigned int)time(NULL));

    // The message to be signed
    const char* message_str = "This is a test message for ECDSA signing.";
    const unsigned char* message = (const unsigned char*)message_str;
    size_t message_len = strlen(message_str);

    // The curve to use
    const char* curve_name = "SECP256k1";

    printf("Signing message: \"%s\"\n", message_str);
    printf("Using curve: %s\n\n", curve_name);

    // Call the translated function
    size_t signature_len = 0;
    unsigned char* signature = sign_message(message, message_len, curve_name, &signature_len);

    if (signature) {
        print_hex("Generated Signature", signature, signature_len);

        // IMPORTANT: Free the memory allocated by sign_message
        free(signature);
    } else {
        fprintf(stderr, "Failed to sign the message.\n");
        return 1; // Indicate failure
    }

    return 0; // Indicate success
}