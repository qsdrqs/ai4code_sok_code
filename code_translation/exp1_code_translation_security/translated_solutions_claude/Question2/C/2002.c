#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>
#include <stdbool.h>

// ECDSA dependencies - simplified implementation
typedef struct {
    uint8_t private_key[32];
} SigningKey;

typedef struct {
    uint8_t public_key[64];
} VerifyingKey;

typedef struct {
    uint8_t r[32];
    uint8_t s[32];
} Signature;

// Mock ECDSA implementation (in real code, use a proper crypto library like OpenSSL)
SigningKey* SigningKey_generate() {
    SigningKey* sk = malloc(sizeof(SigningKey));
    // In real implementation, generate proper random private key
    for (int i = 0; i < 32; i++) {
        sk->private_key[i] = rand() % 256;
    }
    return sk;
}

VerifyingKey* SigningKey_get_verifying_key(SigningKey* sk) {
    VerifyingKey* vk = malloc(sizeof(VerifyingKey));
    // In real implementation, derive public key from private key
    for (int i = 0; i < 64; i++) {
        vk->public_key[i] = sk->private_key[i % 32] ^ (i + 1);
    }
    return vk;
}

Signature* SigningKey_sign(SigningKey* sk, const uint8_t* message, size_t message_len) {
    Signature* sig = malloc(sizeof(Signature));
    // Mock signature generation (in real implementation, use proper ECDSA)
    for (int i = 0; i < 32; i++) {
        sig->r[i] = sk->private_key[i] ^ message[i % message_len];
        sig->s[i] = sk->private_key[i] + message[i % message_len] + i;
    }
    return sig;
}

bool VerifyingKey_verify(VerifyingKey* vk, Signature* signature, const uint8_t* message, size_t message_len) {
    // Mock verification (in real implementation, use proper ECDSA verification)
    // This will return false for different messages, true for same message
    uint8_t expected_r[32];
    for (int i = 0; i < 32; i++) {
        expected_r[i] = (vk->public_key[i] ^ vk->public_key[i + 32]) ^ message[i % message_len];
    }
    
    for (int i = 0; i < 32; i++) {
        if (signature->r[i] != expected_r[i]) {
            return false;
        }
    }
    return true;
}

// Helper function to encode string to UTF-8 (simplified - assumes ASCII input)
uint8_t* encode_utf8(const char* str, size_t* out_len) {
    *out_len = strlen(str);
    uint8_t* encoded = malloc(*out_len);
    memcpy(encoded, str, *out_len);
    return encoded;
}

// Translated functions
Signature* sign(SigningKey* sk, const char* m) {
    size_t encoded_len;
    uint8_t* encoded = encode_utf8(m, &encoded_len);
    Signature* result = SigningKey_sign(sk, encoded, encoded_len);
    free(encoded);
    return result;
}

void test() {
    SigningKey* sk = SigningKey_generate();
    VerifyingKey* vk = SigningKey_get_verifying_key(sk);
    Signature* signature = sign(sk, "hello world");
    
    size_t verify_msg_len;
    uint8_t* verify_msg = encode_utf8("hello worfld", &verify_msg_len);
    
    bool result = VerifyingKey_verify(vk, signature, verify_msg, verify_msg_len);
    printf("%s\n", result ? "true" : "false");
    
    // Cleanup
    free(sk);
    free(vk);
    free(signature);
    free(verify_msg);
}

int main() {
    test();
    return 0;
}