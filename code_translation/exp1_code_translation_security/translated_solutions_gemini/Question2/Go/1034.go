package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

func main() {
	// The Python library likely defaults to the secp256k1 curve.
	// Go's standard library provides P256, P384, and P521. We'll use P256,
	// which is a strong and common choice (also known as secp256r1).
	curve := elliptic.P256()

	// 1. Generate a new private key
	// Corresponds to: privateKey = PrivateKey()
	privateKey, err := ecdsa.GenerateKey(curve, rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// 2. Get the public key from the private key
	// Corresponds to: publicKey = privateKey.publicKey()
	// In Go, the public key is part of the private key struct.
	publicKey := &privateKey.PublicKey

	// 3. Define the message to be signed
	// Corresponds to: message = "Test message"
	message := []byte("Test message")

	// In Go's crypto libraries, you sign the HASH of the message, not the raw message.
	// We'll use SHA-256 to hash our message.
	hash := sha256.Sum256(message)

	// 4. Sign the hash of the message with the private key
	// Corresponds to: signature = Ecdsa.sign(message, privateKey)
	// The signature is returned as two integers, r and s.
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// 5. Verify the signature with the public key
	// Corresponds to: Ecdsa.verify(message, signature, publicKey)
	// The Verify function also takes the hash of the message.
	isVerified := ecdsa.Verify(publicKey, hash[:], r, s)

	// 6. Print the result
	// Corresponds to: print(...)
	fmt.Printf("Signature verified: %t\n", isVerified)
}