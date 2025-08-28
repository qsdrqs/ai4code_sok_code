package main

import (
    "crypto/ecdsa"
    "crypto/rand"
    "crypto/sha256"
    "encoding/asn1"
    "math/big"
)

// ecdsaSignature represents the structure of an ECDSA signature in DER format.
type ecdsaSignature struct {
    R *big.Int
    S *big.Int
}

// signUsingECDSA signs a message using ECDSA with SHA-256 and returns the DER-encoded signature.
func signUsingECDSA(key *ecdsa.PrivateKey, message []byte) ([]byte, error) {
    // Step 1: Hash the message using SHA-256
    hash := sha256.Sum256(message)

    // Step 2: Sign the hash using the private key
    r, s, err := ecdsa.Sign(rand.Reader, key, hash[:])
    if err != nil {
        return nil, err
    }

    // Step 3: Marshal the signature into DER format
    signature := ecdsaSignature{
        R: r,
        S: s,
    }

    derSig, err := asn1.Marshal(signature)
    if err != nil {
        return nil, err
    }

    return derSig, nil
}