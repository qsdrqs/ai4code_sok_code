package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"log"
)

// sign signs the given message using the provided private key.
// It returns the ASN.1 DER-encoded signature.
func sign(msg []byte, sk *ecdsa.PrivateKey) ([]byte, error) {
	hash := sha256.Sum256(msg)
	return ecdsa.SignASN1(rand.Reader, sk, hash[:])
}

func main() {
	// Generate a new ECDSA private key using the P-256 curve
	sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatal("Failed to generate private key:", err)
	}

	// Get the corresponding public key (verifying key)
	vk := &sk.PublicKey

	// Message to be signed
	msg := []byte("message")

	// Sign the message
	sig, err := sign(msg, sk)
	if err != nil {
		log.Fatal("Failed to sign message:", err)
	}

	// Hash the message again for verification
	hash := sha256.Sum256(msg)

	// Verify the signature
	if !ecdsa.VerifyASN1(vk, hash[:], sig) {
		log.Fatal("Verification failed")
	}

	// If we reach here, the signature is valid
	log.Println("Signature verified successfully")
}