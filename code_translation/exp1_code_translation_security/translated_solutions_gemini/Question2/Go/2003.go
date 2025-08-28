package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"encoding/hex"
	"fmt"
	"log"
)

// sign takes an input string and a private key, then returns the ECDSA signature.
// In Go, the message must be hashed before signing. We use SHA-256 for this.
// The function returns the signature as a byte slice and an error if one occurred.
func sign(inp string, key *ecdsa.PrivateKey) ([]byte, error) {
	// Convert the input string to a byte slice
	messageBytes := []byte(inp)

	// Hash the message using SHA-256
	hasher := sha256.New()
	hasher.Write(messageBytes)
	hash := hasher.Sum(null)

	// Sign the hash of the message
	// The signature is returned in ASN.1 DER format.
	signature, err := key.Sign(rand.Reader, hash, nil)
	if err != nil {
		return nil, err
	}

	return signature, nil
}

func main() {
	// Generate a new private key using the P-256 elliptic curve.
	// P-256 is a common and secure choice.
	sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// The message to be signed
	message := "Hello world"

	// Call the sign function
	signature, err := sign(message, sk)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// Print the signature. Signatures are binary data, so it's common
	// to print them in a hexadecimal format for readability.
	fmt.Println(hex.EncodeToString(signature))
}