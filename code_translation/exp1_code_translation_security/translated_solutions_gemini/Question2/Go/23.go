package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

// The original Python code uses the 'ecdsa' library.
// Go has a built-in crypto library that we can use instead.

// The sample code in the Python script mentions NIST192p.
// This curve is considered weak by modern standards and is not implemented
// in Go's standard library. We will use P-256, which is a secure and
// commonly used alternative.

// sign creates a digital signature for a message using a private key.
// In Go, it's standard practice to sign a hash of the message, not the raw message itself.
// We will use SHA-256 for hashing.
// The function returns an ASN.1 encoded signature.
func sign(message []byte, key *ecdsa.PrivateKey) ([]byte, error) {
	// The Python code gets the verifying key but doesn't use it in the sign function.
	// In Go, the public key is part of the private key struct, so we don't need a separate step.
	// var vk = key.PublicKey

	// Hash the message with SHA-256
	hasher := sha256.New()
	hasher.Write(message)
	hash := hasher.Sum(nil)

	// Sign the hash of the message
	signature, err := ecdsa.SignASN1(rand.Reader, key, hash)
	if err != nil {
		return nil, err
	}

	return signature, nil
}

func main() {
	// Generate a new private key using the P-256 curve.
	// This is the equivalent of Python's SigningKey.generate().
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	message := []byte("i am a message")

	// Call the sign function with the message and the generated key.
	signature, err := sign(message, privateKey)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// The Python code prints the raw bytes of the signature.
	// In Go, printing a byte slice shows the decimal values of each byte.
	// For a more common hexadecimal representation, you could use:
	// fmt.Printf("%x\n", signature)
	fmt.Println(signature)
}