package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

// sign takes a message and a private key, and returns the ASN.1 encoded signature.
// Note: Unlike the Python ecdsa library which hashes the message internally,
// Go's standard library requires you to hash the message first before signing.
func sign(msg []byte, sk *ecdsa.PrivateKey) ([]byte, error) {
	// Hash the message with SHA-256
	hasher := sha256.New()
	hasher.Write(msg)
	msgHash := hasher.Sum(nil)

	// Sign the hash of the message
	signature, err := ecdsa.SignASN1(rand.Reader, sk, msgHash)
	if err != nil {
		return nil, err
	}
	return signature, nil
}

func main() {
	// In Python: sk = SigningKey.generate() # uses NIST192p
	// In Go, we explicitly specify the curve, P-192.
	privateKey, err := ecdsa.GenerateKey(elliptic.P192(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// In Python: vk = sk.verifying_key
	// In Go, the PublicKey is part of the PrivateKey struct.
	publicKey := &privateKey.PublicKey

	// The message to sign
	message := []byte("message")

	// Generate the signature
	signature, err := sign(message, privateKey)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// In Python: assert vk.verify(sign(b"message", sk), b"message")
	// In Go, we must perform the same hashing step before verifying.
	hasher := sha256.New()
	hasher.Write(message)
	messageHash := hasher.Sum(nil)

	// Verify the signature
	verified := ecdsa.VerifyASN1(publicKey, messageHash, signature)

	// Assert the verification
	if !verified {
		log.Fatal("Signature verification failed!")
	}

	fmt.Println("Signature verified successfully!")
}