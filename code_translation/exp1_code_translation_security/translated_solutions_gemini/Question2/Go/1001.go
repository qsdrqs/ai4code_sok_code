package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

// signUsingECDSA signs a message using the given ECDSA private key.
// It first hashes the message with SHA-256 and then signs the hash.
// The signature is returned in ASN.1 DER format, which is a standard representation.
func signUsingECDSA(privateKey *ecdsa.PrivateKey, message []byte) ([]byte, error) {
	// First, hash the message using SHA-256. The ecdsa.SignASN1 function
	// expects a hash of the message, not the message itself.
	hash := sha256.Sum256(message)

	// Sign the hash with the private key.
	// ecdsa.SignASN1 uses a source of randomness (crypto/rand.Reader),
	// the private key, and the hash to create an ASN.1 DER-encoded signature.
	// This is the direct equivalent of the Python code's behavior.
	signature, err := ecdsa.SignASN1(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, err
	}

	return signature, nil
}

// main function to demonstrate the usage of signUsingECDSA
func main() {
	// 1. Generate a new ECDSA private key for demonstration.
	// In a real application, you would load a key from a file or other storage.
	// We use the P-256 curve, which is a common and secure choice.
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// 2. Define the message to be signed.
	message := []byte("this is a test message")

	// 3. Call the signing function.
	signature, err := signUsingECDSA(privateKey, message)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	fmt.Printf("Message: %s\n", message)
	fmt.Printf("Signature (hex): %x\n\n", signature)

	// 4. (Optional but recommended) Verify the signature to confirm it's correct.
	// We need the public key for verification, which can be derived from the private key.
	publicKey := &privateKey.PublicKey

	// The verification function also needs the hash of the original message.
	hash := sha256.Sum256(message)

	// ecdsa.VerifyASN1 checks if the signature is valid for the given hash and public key.
	isValid := ecdsa.VerifyASN1(publicKey, hash[:], signature)

	fmt.Printf("Signature verified successfully: %t\n", isValid)
}