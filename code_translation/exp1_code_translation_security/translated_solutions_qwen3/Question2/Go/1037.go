package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha512"
	"fmt"
)

// signMessage signs the given message using the provided private key.
// It returns a DER-encoded ECDSA signature.
func signMessage(key *ecdsa.PrivateKey, message []byte) []byte {
	// Hash the message using SHA-384
	hash := sha512.Sum384(message)

	// Sign the hash using the private key
	r, s, err := ecdsa.Sign(rand.Reader, key, hash[:])
	if err != nil {
		panic("failed to sign message: " + err.Error())
	}

	// Marshal the signature into DER format
	sigBytes, err := ecdsa.MarshalECDSASignature(r, s)
	if err != nil {
		panic("failed to marshal signature: " + err.Error())
	}

	return sigBytes
}

func main() {
	// Generate a new ECDSA private key using the P-384 curve
	sk, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		panic("failed to generate private key: " + err.Error())
	}

	// Get the corresponding public key
	vk := &sk.PublicKey

	// Message to be signed
	message := []byte("testing")

	// Sign the message
	signature := signMessage(sk, message)

	// Hash the message again for verification
	hash := sha512.Sum384(message)

	// Verify the signature using the public key
	verified := ecdsa.VerifyASN1(vk, hash[:], signature)

	// Output the result
	fmt.Println(verified) // Should print: true
}