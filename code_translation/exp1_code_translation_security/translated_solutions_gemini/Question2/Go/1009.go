package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha512"
	"fmt"
	"log"
	"math/big"
)

// sign generates an ECDSA signature for a given message using a private key.
// It first hashes the message with SHA-384, which is appropriate for the P-384 curve.
// The resulting signature is a concatenation of the r and s values.
func sign(sk *ecdsa.PrivateKey, message []byte) ([]byte, error) {
	// Hash the message before signing
	hash := sha512.Sum384(message)

	// Sign the hash of the message
	r, s, err := ecdsa.Sign(rand.Reader, sk, hash[:])
	if err != nil {
		return nil, fmt.Errorf("failed to sign message: %w", err)
	}

	// The public key's curve parameters determine the size of r and s.
	curveBytes := sk.Curve.Params().BitSize / 8
	if curveBytes == 0 {
		return nil, fmt.Errorf("invalid curve parameters")
	}

	// Marshal the signature into a single byte slice.
	// r and s are padded to the curve's size and concatenated.
	signature := make([]byte, curveBytes*2)
	r.FillBytes(signature[:curveBytes])
	s.FillBytes(signature[curveBytes:])

	return signature, nil
}

func main() {
	// The message to be signed, equivalent to Python's b"hehehe"
	message := []byte("hehehe")

	// Generate a private key using the NIST P-384 curve.
	// This is equivalent to SigningKey.generate(curve=NIST384p)
	sk, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// Call the sign function to get the signature
	sig, err := sign(sk, message)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// --- Verification ---
	// In Go, the public key is part of the private key struct.
	// This is equivalent to sk.verifying_key
	vk := &sk.PublicKey

	// For verification, the message must be hashed using the same algorithm
	hash := sha512.Sum384(message)

	// Unmarshal the signature from the byte slice back into r and s values.
	curveBytes := vk.Curve.Params().BitSize / 8
	if len(sig) != curveBytes*2 {
		log.Fatalf("invalid signature length: got %d, want %d", len(sig), curveBytes*2)
	}
	r := new(big.Int).SetBytes(sig[:curveBytes])
	s := new(big.Int).SetBytes(sig[curveBytes:])

	// Verify the signature against the hash using the public key.
	// This is equivalent to vk.verify(sig, message)
	isValid := ecdsa.Verify(vk, hash[:], r, s)

	// Print the verification result (will print "true")
	fmt.Println(isValid)
}