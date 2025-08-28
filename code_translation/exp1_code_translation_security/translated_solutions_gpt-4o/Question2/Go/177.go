package main

import (
	"crypto"
	"crypto/rand"
	"fmt"
)

// Assuming sk is of a type that implements the crypto.Signer interface
func sign(m []byte, sk crypto.Signer) ([]byte, error) {
	return sk.Sign(rand.Reader, m, crypto.Hash(0))
}

func main() {
	// Example usage
	// sk would be your private key implementing crypto.Signer
	// message := []byte("your message here")
	// signature, err := sign(message, sk)
	// if err != nil {
	//     fmt.Println("Error signing message:", err)
	// } else {
	//     fmt.Println("Signature:", signature)
	// }
}