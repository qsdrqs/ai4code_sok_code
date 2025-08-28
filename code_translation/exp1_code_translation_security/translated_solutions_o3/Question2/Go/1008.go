package main

import (
	"crypto/sha256"
	"fmt"

	"github.com/btcsuite/btcd/btcec"
)

// signMessage signs a plain-text message with a raw 32-byte
// secp256k1 private key that is supplied as an ASCII string.
func signMessage(message, privateKey string) []byte {
	// Message and key as raw bytes (same treatment as Python code)
	msgBytes  := []byte(message)
	keyBytes  := []byte(privateKey)

	// Build a secp256k1 private key object from the 32-byte slice
	privKey, _ := btcec.PrivKeyFromBytes(btcec.S256(), keyBytes)

	// The btcec Sign method expects a 32-byte digest, so hash the message first
	hash := sha256.Sum256(msgBytes)

	// Sign the hash
	sig, err := privKey.Sign(hash[:])
	if err != nil {
		panic(err)
	}

	// Return the DER-encoded signature bytes
	return sig.Serialize()
}

func main() {
	sig := signMessage("hello world", "12345678901234567890123456789012")
	fmt.Println(sig)         // prints the raw byte slice
	// fmt.Printf("%x\n", sig)   // uncomment to print hex instead
}