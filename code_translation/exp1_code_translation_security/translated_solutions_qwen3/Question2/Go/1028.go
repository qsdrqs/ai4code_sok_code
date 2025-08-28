package main

import (
    "crypto/ecdsa"
    "crypto/rand"
)

// sign signs a given message using an ECDSA signing key.
func sign(priv *ecdsa.PrivateKey, message []byte) ([]byte, error) {
    return ecdsa.Sign(rand.Reader, priv, message)
}

// verify verifies a given signature using an ECDSA verification key.
func verify(pub *ecdsa.PublicKey, message []byte, signature []byte) bool {
    valid, err := ecdsa.VerifyASN1(pub, message, signature)
    if err != nil {
        return false
    }
    return valid
}