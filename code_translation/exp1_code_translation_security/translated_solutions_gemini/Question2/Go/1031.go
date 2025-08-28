package main

import (
	"crypto"
	"crypto/rand"
	"crypto/rsa"
	"crypto/sha256"
	"fmt"
	"log"
)

// Sign creates a digital signature for a message using an RSA private key.
// It implements the RSASSA-PKCS1-v1_5 signature scheme.
//
// Parameters:
//   msg: The raw message data to be signed.
//   privKey: The rsa.PrivateKey to sign with.
//
// Returns:
//   The signature as a byte slice, or an error if signing fails.
func Sign(msg []byte, privKey *rsa.PrivateKey) ([]byte, error) {
	// Step 1: Create a SHA256 hash of the message.
	// The signer in Python also operates on the hash of the message, not the raw message.
	hasher := sha256.New()
	hasher.Write(msg)
	hashed := hasher.Sum(nil)

	// Step 2: Sign the hashed message using the RSA private key.
	// rsa.SignPKCS1v15 uses the RSASSA-PKCS1-v1_5 signature scheme.
	// rand.Reader is a global, shared instance of a cryptographically
	// secure random number generator, which is required for the signing process.
	signature, err := rsa.SignPKCS1v15(rand.Reader, privKey, crypto.SHA256, hashed)
	if err != nil {
		// If signing fails, wrap the error with more context.
		return nil, fmt.Errorf("failed to sign message: %w", err)
	}

	return signature, nil
}

// main function to demonstrate the Sign function.
func main() {
	// --- SETUP ---
	// In a real application, you would load a private key from a secure source (e.g., a file).
	// For this example, we generate a new 2048-bit RSA key pair.
	privateKey, err := rsa.GenerateKey(rand.Reader, 2048)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// The message we want to sign.
	message := []byte("This is a very important message.")

	// --- SIGNING ---
	fmt.Println("Signing the message...")
	signature, err := Sign(message, privateKey)
	if err != nil {
		log.Fatalf("Error signing message: %v", err)
	}

	fmt.Printf("Original Message: %s\n", message)
	fmt.Printf("Generated Signature (hex): %x\n\n", signature)

	// --- VERIFICATION (for demonstration purposes) ---
	// To prove the signature is valid, we can verify it using the corresponding public key.
	publicKey := &privateKey.PublicKey

	// The verifier must hash the original message with the same algorithm.
	hasher := sha256.New()
	hasher.Write(message)
	hashed := hasher.Sum(nil)

	// Verify the signature against the hash.
	fmt.Println("Verifying the signature...")
	err = rsa.VerifyPKCS1v15(publicKey, crypto.SHA256, hashed, signature)
	if err != nil {
		fmt.Println("Signature verification FAILED!")
	} else {
		fmt.Println("Signature verification SUCCESSFUL!")
	}
}