package main

import (
	"crypto/sha1"
	"errors"
	"fmt"

	"github.com/btcsuite/btcec/v2"
)

// signMessage signs a message using an ECDSA private key on the SECP256k1 curve.
// The private key must be exactly 32 bytes long.
// The signature is returned as a 64-byte slice: [r (32 bytes)][s (32 bytes)].
func signMessage(message string, privateKeyStr string) ([]byte, error) {
	// Step 1: Convert message and private key to bytes
	messageBytes := []byte(message)
	privateKeyBytes := []byte(privateKeyStr)

	// Step 2: Validate private key length
	if len(privateKeyBytes) != 32 {
		return nil, errors.New("private key must be exactly 32 bytes")
	}

	// Step 3: Parse the private key
	privKey, err := btcec.PrivKeyFromBytes(privateKeyBytes)
	if err != nil {
		return nil, err
	}

	// Step 4: Compute SHA-1 hash of the message
	hash20 := sha1.Sum(messageBytes)

	// Step 5: Pad the 20-byte SHA-1 hash to 32 bytes by prepending 12 zeros
	var hash32 [32]byte
	copy(hash32[12:], hash20[:]) // hash32 = [0...0][hash20]

	// Step 6: Sign the padded hash
	sig, err := privKey.Sign(hash32[:])
	if err != nil {
		return nil, err
	}

	// Step 7: Encode r and s into 32-byte zero-padded slices
	rBytes := sig.R.Bytes()
	sBytes := sig.S.Bytes()

	rPadded := make([]byte, 32)
	sPadded := make([]byte, 32)

	copy(rPadded[32-len(rBytes):], rBytes)
	copy(sPadded[32-len(sBytes):], sBytes)

	// Step 8: Concatenate r and s to form the final 64-byte signature
	return append(rPadded, sPadded...), nil
}

func main() {
	// Example usage
	sig, err := signMessage("hello world", "12345678901234567890123456789012")
	if err != nil {
		fmt.Println("Error:", err)
		return
	}

	// Print the signature in Go's slice format
	fmt.Printf("Signature: %#v\n", sig)
}