package main

import (
	"crypto/sha256"
	"fmt"
	"github.com/btcsuite/btcd/btcec"
)

// signMessage signs the given message using the private key and verifies it using the public key.
func signMessage(sk *btcec.PrivateKey, message string) bool {
	hash := sha256.Sum256([]byte(message))
	sig := sk.Sign(hash[:])
	return sig.Verify(hash[:], sk.PubKey())
}

func main() {
	// Generate a new private key using the SECP256k1 curve
	sk, _ := btcec.NewPrivateKey(btcec.S256())

	// Test the sign and verify function
	result := signMessage(sk, "..")
	fmt.Println(result) // Should print "true"
}