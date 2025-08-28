package main

import (
	"crypto/sha256"
	"encoding/hex"
	"fmt"
	"math/big"

	"github.com/btcsuite/btcd/btcec/v2"
)

// signMessage signs a message using ECDSA with the SECP256k1 curve.
// It returns the signature in hexadecimal format.
func signMessage(message string, privateKey []byte) (string, error) {
	// Convert the private key bytes to a big.Int
	d := new(big.Int).SetBytes(privateKey)

	// Validate the private key
	if !btcec.IsValidPrivateKey(d) {
		return "", fmt.Errorf("invalid private key")
	}

	// Create a new private key from the scalar
	privKey := btcec.NewPrivateKey(d)

	// Hash the message using SHA-256
	messageHash := sha256.Sum256([]byte(message))

	// Sign the hash
	signature, err := privKey.Sign(messageHash[:])
	if err != nil {
		return "", err
	}

	// Serialize the signature in DER format
	derSignature := signature.Serialize()

	// Return the hex-encoded signature
	return hex.EncodeToString(derSignature), nil
}

func main() {
	// Test the function
	privateKeyHex := "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109"
	privateKeyBytes, err := hex.DecodeString(privateKeyHex)
	if err != nil {
		panic(err)
	}

	message := "Hello"
	signature, err := signMessage(message, privateKeyBytes)
	if err != nil {
		panic(err)
	}

	fmt.Println(signature)
}