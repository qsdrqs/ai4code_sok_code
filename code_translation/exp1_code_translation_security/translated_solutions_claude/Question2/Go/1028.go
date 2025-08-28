package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
	"math/big"
)

// ECDSASignature represents an ECDSA signature
type ECDSASignature struct {
	R, S *big.Int
}

// sign function that signs a given message using an ECDSA signing key
// Parameters:
//   - privateKey: ECDSA private key for signing
//   - message: message to be signed (as byte slice)
// Returns:
//   - ECDSASignature: the signature
//   - error: error if signing fails
func sign(privateKey *ecdsa.PrivateKey, message []byte) (*ECDSASignature, error) {
	// Hash the message
	hash := sha256.Sum256(message)
	
	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, err
	}
	
	return &ECDSASignature{R: r, S: s}, nil
}

// verify function that verifies a given signature using an ECDSA verification key
// Parameters:
//   - publicKey: ECDSA public key for verification
//   - message: message to be verified (as byte slice)
//   - signature: signature to be verified
// Returns:
//   - bool: true if signature is valid, false otherwise
func verify(publicKey *ecdsa.PublicKey, message []byte, signature *ECDSASignature) bool {
	// Hash the message
	hash := sha256.Sum256(message)
	
	// Verify the signature
	return ecdsa.Verify(publicKey, hash[:], signature.R, signature.S)
}