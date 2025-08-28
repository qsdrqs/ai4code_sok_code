package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/sha256"
	"encoding/asn1"
	"math/big"
)

// signString signs the given data using the provided ECDSA private key with SHA-256 as the hash function.
// It returns the signature in ASN.1 DER format, which is compatible with the Python cryptography library.
//
// Parameters:
//   - privateKey: A pointer to an ecdsa.PrivateKey using the P-384 (SECP384R1) curve.
//   - data: The raw data to be signed.
//
// Returns:
//   - The signature in ASN.1 DER format as a byte slice.
//   - An error if any step in the signing process fails.
func signString(privateKey *ecdsa.PrivateKey, data []byte) ([]byte, error) {
	// Step 1: Hash the input data using SHA-256
	hashed := sha256.Sum256(data)

	// Step 2: Sign the hashed data using the ECDSA private key
	r, s, err := ecdsa.Sign(nil, privateKey, hashed[:])
	if err != nil {
		return nil, err
	}

	// Step 3: Encode the signature (r, s) into ASN.1 DER format
	type ecdsaSignature struct {
		R, S *big.Int
	}
	signature, err := asn1.Marshal(ecdsaSignature{R: r, S: s})
	if err != nil {
		return nil, err
	}

	return signature, nil
}