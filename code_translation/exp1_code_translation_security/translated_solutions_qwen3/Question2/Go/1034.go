package main

import (
	"crypto/sha256"
	"fmt"
	"github.com/btcsuite/btcec/v2"
)

func main() {
	// Generate a private key using the secp256k1 curve
	privKey, err := btcec.NewPrivateKey(btcec.S256())
	if err != nil {
		panic(err)
	}

	// Derive the public key from the private key
	pubKey := privKey.PubKey()

	// Define the message to be signed
	message := "Test message"

	// Hash the message using SHA-256
	hash := sha256.Sum256([]byte(message))

	// Sign the hash using the private key
	signature, err := privKey.Sign(hash[:])
	if err != nil {
		panic(err)
	}

	// Verify the signature using the public key and the same hash
	valid := signature.Verify(hash[:], pubKey)

	// Print the result of the verification
	fmt.Println(valid)
}