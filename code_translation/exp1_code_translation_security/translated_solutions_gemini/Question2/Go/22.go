package main

import (
	"fmt"
	"log"

	// The go-ethereum crypto library is used for its optimized secp256k1 implementation,
	// which is the curve specified in the Python code.
	"github.com/ethereum/go-ethereum/crypto"
)

// myfunction generates a new SECP256k1 private key and uses it to sign a message.
// It is the Go equivalent of the provided Python function.
//
// Parameters:
//   message: The message to be signed, as a byte slice.
//
// Returns:
//   A 64-byte signature ([R || S]) and an error if one occurred.
func myfunction(message []byte) ([]byte, error) {
	// Python: sk = ecdsa.SigningKey.generate(curve=ecdsa.SECP256k1)
	// The crypto.GenerateKey function from the go-ethereum library creates a
	// private key for the secp256k1 curve.
	privateKey, err := crypto.GenerateKey()
	if err != nil {
		// In Go, errors are returned explicitly.
		return nil, err
	}

	// Python: sig = sk.sign(message)
	// The Python library's sign method typically hashes the message internally
	// before signing. In Go, especially with the go-ethereum library, you
	// must hash the message explicitly first. Keccak256 is the standard
	// hashing algorithm used in the Ethereum ecosystem.
	hash := crypto.Keccak256Hash(message)

	// The crypto.Sign function signs the hash with the private key.
	signature, err := crypto.Sign(hash.Bytes(), privateKey)
	if err != nil {
		return nil, err
	}

	// The signature from crypto.Sign is 65 bytes long: [R || S || V], where V is
	// the recovery ID. The Python ecdsa library often returns a 64-byte
	// signature [R || S]. To match this behavior, we remove the last byte (the V value).
	return signature[:64], nil
}

// main serves as an example of how to use myfunction.
func main() {
	// The message to be signed.
	message := []byte("example message")

	// Call the translated function.
	signature, err := myfunction(message)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// Print the resulting signature.
	fmt.Printf("Message: %s\n", string(message))
	fmt.Printf("Signature (hex): %x\n", signature)
	fmt.Printf("Signature length: %d bytes\n", len(signature))
}