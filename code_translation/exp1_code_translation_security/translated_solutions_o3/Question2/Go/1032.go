package main

import (
	"crypto/sha256"
	"encoding/hex"
	"fmt"
	"log"

	"github.com/btcsuite/btcd/btcec"
)

// SignMessage signs the supplied message with the given secp256k1 private key
// and returns the DER-encoded ECDSA signature.
func SignMessage(message string, privKeyBytes []byte) ([]byte, error) {
	// 1) Hash the message (SHA-256, like in the Python version)
	hash := sha256.Sum256([]byte(message))

	// 2) Build a private-key object for the secp256k1 curve
	privKey, _ := btcec.PrivKeyFromBytes(btcec.S256(), privKeyBytes)

	// 3) Sign the digest
	sig, err := privKey.Sign(hash[:])
	if err != nil {
		return nil, err
	}

	// 4) Return the DER-encoded signature bytes
	return sig.Serialize(), nil
}

func main() {
	message := "Hello, world!"
	privHex := "18E14A7B6A307F426A94F8114701E7C8E774E7F9A47E2C2035DB29A206321725"

	// Decode hex string into raw bytes
	privKeyBytes, err := hex.DecodeString(privHex)
	if err != nil {
		log.Fatalf("invalid private key hex: %v", err)
	}

	// Sign the message
	signature, err := SignMessage(message, privKeyBytes)
	if err != nil {
		log.Fatalf("failed to sign: %v", err)
	}

	fmt.Printf("Signature: %x\n", signature)
}