package main

import (
	"crypto/sha256"
	"encoding/hex"
	"fmt"
	"github.com/btcsuite/btcd/btcec"
)

// generateEcdsaSignedMsg signs a message using ECDSA with the secp256k1 curve.
func generateEcdsaSignedMsg(message string, privateKey *btcec.PrivateKey) ([]byte, error) {
	// Hash the message using SHA-256
	hashed := sha256.Sum256([]byte(message))

	// Sign the hash with the private key
	signature := privateKey.Sign(hashed[:])

	// Serialize the signature in DER format
	return signature.Serialize(), nil
}

func main() {
	// Generate a new private key on the secp256k1 curve
	privKey, _ := btcec.NewPrivateKey(btcec.S256())

	// Derive the public key (not used in this example, but included for completeness)
	_ = privKey.PubKey()

	// Message to be signed
	message := "My test message"

	// Generate the signature
	sig, _ := generateEcdsaSignedMsg(message, privKey)

	// Print the signature in hexadecimal format
	fmt.Println(hex.EncodeToString(sig))
}