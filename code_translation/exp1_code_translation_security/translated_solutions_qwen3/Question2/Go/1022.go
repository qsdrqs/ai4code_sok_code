package main

import (
	"crypto/sha256"
	"fmt"

	"github.com/btcsuite/btcd/btcec"
)

func main() {
	// Step 1: Generate a private key using the SECP256k1 curve
	privKey, err := btcec.NewPrivateKey(btcec.S256())
	if err != nil {
		panic(err)
	}
	pubKey := privKey.PubKey()

	// Step 2: Define the message to be signed
	message := "My test message"

	// Step 3: Hash the message using SHA-256
	messageHash := sha256.Sum256([]byte(message))

	// Step 4: Sign the message hash
	signature := privKey.Sign(messageHash[:])

	// Step 5: Verify the signature
	valid := signature.Verify(messageHash[:], pubKey)
	fmt.Println(valid) // Should print: true
}