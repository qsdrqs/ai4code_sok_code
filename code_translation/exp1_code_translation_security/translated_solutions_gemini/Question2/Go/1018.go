package main

import (
	"crypto/ecdsa"    // For ECDSA signing and key structures
	"crypto/elliptic" // To define the elliptic curve to use (e.g., P-256)
	"crypto/rand"     // For a source of cryptographic randomness
	"crypto/sha256"   // For hashing the message before signing
	"encoding/hex"    // To display the byte-slice signature in a readable format
	"fmt"
	"log"
)

// sign generates an ECDSA signature for a message using a private key.
//
// Note: The original Python function ignores its 'msg' parameter and
// signs the hardcoded byte string "message". This Go translation
// replicates that specific behavior.
//
// Parameters:
//   msg: A byte slice representing the message. It is ignored in this implementation.
//   key: A pointer to an ecdsa.PrivateKey used for signing.
//
// Returns:
//   A byte slice containing the ASN.1/DER encoded signature, and an error if one occurred.
func sign(msg []byte, key *ecdsa.PrivateKey) ([]byte, error) {
	// The message to be signed is hardcoded, as in the original Python code.
	// The 'msg' parameter is ignored.
	messageToSign := []byte("message")

	// In ECDSA, we sign the hash of the message, not the message itself.
	// We'll use SHA-256, a standard and secure hashing algorithm.
	hash := sha256.Sum256(messageToSign)

	// Sign the hash. ecdsa.SignASN1 returns the signature as a single byte slice
	// in ASN.1/DER format, which is a common standard.
	sig, err := ecdsa.SignASN1(rand.Reader, key, hash[:])
	if err != nil {
		return nil, fmt.Errorf("failed to sign message: %w", err)
	}

	return sig, nil
}

// main function to demonstrate the usage of the sign function.
func main() {
	// 1. Generate an ECDSA private key for demonstration purposes.
	// We use the P-256 curve, which is a common and secure choice.
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// 2. Define a dummy message. It will be ignored by our sign function,
	// but we pass it to match the function's signature.
	var dummyMsg []byte

	// 3. Call the sign function to generate the signature.
	signature, err := sign(dummyMsg, privateKey)
	if err != nil {
		log.Fatalf("Error during signing: %v", err)
	}

	// 4. Print the signature. Since it's a byte slice, we encode it as
	// a hexadecimal string to make it readable.
	fmt.Println("Generated Signature (Hex):", hex.EncodeToString(signature))

	// 5. (Bonus) Verify the signature to confirm it's correct.
	// The verifier also needs to hash the message.
	messageToSign := []byte("message")
	hash := sha256.Sum256(messageToSign)
	
	// Verification requires the public key, which is derived from the private key.
	publicKey := &privateKey.PublicKey
	
	// ecdsa.VerifyASN1 checks if the signature is valid for the given hash and public key.
	isValid := ecdsa.VerifyASN1(publicKey, hash[:], signature)

	fmt.Println("Signature is valid:", isValid)
}