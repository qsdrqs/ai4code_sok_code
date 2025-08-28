package main

import (
    "crypto/ecdsa"
    "crypto/elliptic"
    "crypto/rand"
    "encoding/asn1"
    "math/big"
)

// SignMessage generates a new ECDSA private key on the specified curve,
// signs the given message, and returns the DER-encoded signature.
//
// Parameters:
//   - message: The data to be signed (as a byte slice).
//   - curve: The elliptic curve to use for key generation (e.g., elliptic.P256()).
//
// Returns:
//   - A DER-encoded signature as a byte slice.
//   - An error if any step fails (e.g., key generation or signing).
func SignMessage(message []byte, curve elliptic.Curve) ([]byte, error) {
    // Step 1: Generate a new ECDSA private key on the specified curve
    privKey, err := ecdsa.GenerateKey(curve, rand.Reader)
    if err != nil {
        return nil, err
    }

    // Step 2: Sign the message using the private key
    r, s, err := ecdsa.Sign(rand.Reader, privKey, message)
    if err != nil {
        return nil, err
    }

    // Step 3: Marshal the signature into DER format
    type ecdsaSignature struct {
        R, S *big.Int
    }

    signature, err := asn1.Marshal(ecdsaSignature{R: r, S: s})
    if err != nil {
        return nil, err
    }

    return signature, nil
}