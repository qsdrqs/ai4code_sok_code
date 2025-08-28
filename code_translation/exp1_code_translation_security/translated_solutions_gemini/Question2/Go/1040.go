package main

import (
	"encoding/hex"
	"fmt"
	"log"

	"github.com/ethereum/go-ethereum/crypto"
	"github.com/ethereum/go-ethereum/crypto/secp256k1"
)

// SignMessage signs a message using a private key with the SECP256k1 curve.
// This function is a direct translation of the Python version, which signs the
// raw message data directly.
//
// Note: In most real-world applications, you should sign a HASH of the
// message, not the raw message itself.
//
// Parameters:
//   message: The raw data (byte slice) to be signed.
//   privateKeyBytes: The 32-byte private key.
//
// Returns:
//   A 64-byte signature [R || S], or an error if signing fails.
func SignMessage(message, privateKeyBytes []byte) ([]byte, error) {
	// The secp256k1.Sign function from the go-ethereum library creates a
	// recoverable ECDSA signature. It returns the signature in [R || S || V]
	// format, where V is the recovery ID. The total length is 65 bytes.
	signature, err := secp256k1.Sign(message, privateKeyBytes)
	if err != nil {
		return nil, fmt.Errorf("failed to sign message: %w", err)
	}

	// The Python `ecdsa` library's default `sign` method returns a 64-byte
	// signature (concatenation of R and S). To match this behavior, we must
	// truncate the last byte (the recovery ID 'V') from the 65-byte signature.
	if len(signature) != 65 {
		return nil, fmt.Errorf("invalid signature length: expected 65, got %d", len(signature))
	}
	return signature[:64], nil
}

// main function demonstrates how to use SignMessage and verifies the result.
func main() {
	// --- Example Usage ---

	// 1. Generate a new private key for demonstration purposes.
	// In a real application, you would load an existing private key from a secure source.
	privateKey, err := crypto.GenerateKey()
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// Get the private key as a 32-byte slice.
	privateKeyBytes := crypto.FromECDSA(privateKey)
	fmt.Printf("Generated Private Key (hex): %x\n", privateKeyBytes)

	// 2. Define the message to be signed.
	message := []byte("this is a test message")
	fmt.Printf("Message to sign: \"%s\"\n", string(message))

	// 3. Sign the message using our function.
	signature, err := SignMessage(message, privateKeyBytes)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// 4. Print the resulting signature.
	fmt.Printf("Signature (hex): %s\n", hex.EncodeToString(signature))
	fmt.Printf("Signature length: %d bytes\n", len(signature))

	// 5. (Optional but recommended) Verify the signature to confirm it's correct.
	// To verify, we need the corresponding public key.
	publicKeyBytes := crypto.FromECDSAPub(&privateKey.PublicKey)

	// The `secp256k1.VerifySignature` function checks if the signature is valid
	// for the given public key and data. It expects the 64-byte signature format.
	isValid := secp256k1.VerifySignature(publicKeyBytes, message, signature)
	fmt.Printf("Is the signature valid? %t\n", isValid)

	// Example with an invalid verification
	fmt.Println("\n--- Tampering with the message to show failed verification ---")
	tamperedMessage := []byte("this is a tampered message")
	isTamperedValid := secp256k1.VerifySignature(publicKeyBytes, tamperedMessage, signature)
	fmt.Printf("Is the signature valid for the tampered message? %t\n", isTamperedValid)
}