package main

import (
    "crypto/ecdsa"
    "crypto/rand"
    "crypto/sha1"
)

// sign signs the fixed message "message" using the provided ECDSA private key.
// It returns the DER-encoded signature or an error if signing fails.
func sign(_ []byte, key *ecdsa.PrivateKey) ([]byte, error) {
    // Always sign the fixed message "message"
    message := []byte("message")
    digest := sha1.Sum(message)

    // Sign the digest using the private key
    r, s, err := ecdsa.Sign(rand.Reader, key, digest[:])
    if err != nil {
        return nil, err
    }

    // Encode the signature in DER format
    signature := ecdsa.EncodeSignature(r, s)
    return signature, nil
}