// This program demonstrates how to sign a message using an ECDSA private key in Go.
// It provides a function that is an equivalent to the provided Python example.
package main

import (
	"crypto/ecdsa"
	"fmt"
	"log"

	// The go-ethereum crypto library is a popular and well-maintained choice for
	// working with the secp256k1 curve, which is used by Bitcoin and Ethereum.
	// To install it, run: go get github.com/ethereum/go-ethereum
	"github.com/ethereum/go-ethereum/common/hexutil"
	"github.com/ethereum/go-ethereum/crypto"
)

// Sign signs a given message using a given ECDSA signing key.
// This function mirrors the functionality of the Python version.
func Sign(message, privateKeyBytes []byte) ([]byte, error) {
	// 1. Parse the private key from bytes.
	// This is equivalent to Python's `ecdsa.SigningKey.from_string(private_key, curve=ecdsa.SECP256k1)`.
	// The go-ethereum library implicitly uses the secp256k1 curve.
	privateKey, err := crypto.ToECDSA(privateKeyBytes)
	if err != nil {
		// In Go, it's idiomatic to return an error for invalid input or failures.
		return nil, fmt.Errorf("failed to parse private key: %w", err)
	}

	// 2. Hash the message before signing.
	// Cryptographic signing functions typically operate on a fixed-size hash of the
	// message, not the message itself. Keccak256 is the standard hashing algorithm
	// used in Ethereum.
	hash := crypto.Keccak256Hash(message)

	// 3. Sign the hash with the private key.
	// This is equivalent to Python's `signature.sign(message)`.
	// The signature is returned in the [R || S || V] format, where V is the recovery ID.
	signature, err := crypto.Sign(hash.Bytes(), privateKey)
	if err != nil {
		return nil, fmt.Errorf("failed to sign message: %w", err)
	}

	// 4. Return the signed message (signature).
	return signature, nil
}

// main function to demonstrate the usage of the Sign function.
func main() {
	// --- Example Usage ---

	// 1. Generate a new private key for demonstration purposes.
	// In a real application, you would load an existing key from a secure location.
	privateKey, err := crypto.GenerateKey()
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// Convert the ecdsa.PrivateKey object to a byte slice.
	privateKeyBytes := crypto.FromECDSA(privateKey)
	fmt.Printf("Generated Private Key (hex): %s\n", hexutil.Encode(privateKeyBytes))

	// Derive the public key from the private key.
	publicKey := privateKey.Public()
	publicKeyECDSA, ok := publicKey.(*ecdsa.PublicKey)
	if !ok {
		log.Fatal("error casting public key to ECDSA")
	}
	publicKeyBytes := crypto.FromECDSAPub(publicKeyECDSA)
	fmt.Printf("Derived Public Key (hex):    %s\n\n", hexutil.Encode(publicKeyBytes))

	// 2. Define the message to be signed.
	message := []byte("a function in Go that signs a given message")
	fmt.Printf("Message to sign: \"%s\"\n\n", message)

	// 3. Call the Sign function with the message and private key bytes.
	signature, err := Sign(message, privateKeyBytes)
	if err != nil {
		log.Fatalf("Error signing message: %v", err)
	}

	// The resulting signature is 65 bytes long.
	fmt.Printf("Generated Signature (hex): %s\n", hexutil.Encode(signature))
	fmt.Printf("Signature length: %d bytes\n", len(signature))
}