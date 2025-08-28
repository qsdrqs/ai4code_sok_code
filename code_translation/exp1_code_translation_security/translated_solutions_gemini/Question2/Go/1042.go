package main

import (
	"fmt"

	// This is the Go equivalent of Python's ecdsa library for SECP256k1.
	// It's the standard library used for Ethereum operations.
	"github.com/ethereum/go-ethereum/crypto"
)

// SignMessage signs a given message using an ECDSA private key.
// This function replicates the functionality of the Python version by using
// the SECP256k1 curve, which is standard for Ethereum and Bitcoin.
//
// Parameters:
//   message: The raw message data to be signed.
//   privateKeyBytes: A 32-byte slice representing the ECDSA private key.
//
// Returns:
//   The 65-byte signature in [R || S || V] format, or an error if signing fails.
func SignMessage(message []byte, privateKeyBytes []byte) ([]byte, error) {
	// 1. Load the private key
	// The crypto.ToECDSA function parses a 32-byte private key and returns
	// an *ecdsa.PrivateKey object for the SECP256k1 curve.
	privateKey, err := crypto.ToECDSA(privateKeyBytes)
	if err != nil {
		return nil, fmt.Errorf("failed to parse private key: %w", err)
	}

	// 2. Hash the message before signing
	// Standard cryptographic practice is to sign a fixed-size hash of the
	// message, not the raw message itself. The go-ethereum library's
	// convention is to use Keccak256.
	hash := crypto.Keccak256Hash(message)

	// 3. Sign the hash
	// The crypto.Sign function creates a signature over the provided hash.
	// The signature is 65 bytes long and in the format [R || S || V],
	// where V is the recovery ID (0 or 1).
	signature, err := crypto.Sign(hash.Bytes(), privateKey)
	if err != nil {
		return nil, fmt.Errorf("failed to sign message: %w", err)
	}

	return signature, nil
}