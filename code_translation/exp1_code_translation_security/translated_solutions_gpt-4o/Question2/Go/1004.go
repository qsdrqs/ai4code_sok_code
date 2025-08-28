package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/sha256"
	"encoding/hex"
	"fmt"
	"math/big"
)

// Function signMessage
// Input: message and ECDSA key
// Output: message signed using the provided key
func signMessage(message []byte, privateKeyHex string) ([]byte, error) {
	// Decode the private key from hex
	privateKeyBytes, err := hex.DecodeString(privateKeyHex)
	if err != nil {
		return nil, err
	}

	// Create a new ECDSA private key
	curve := elliptic.P256() // SECP256k1 is not directly available, using P256 for demonstration
	privateKey := new(ecdsa.PrivateKey)
	privateKey.D = new(big.Int).SetBytes(privateKeyBytes)
	privateKey.PublicKey.Curve = curve
	privateKey.PublicKey.X, privateKey.PublicKey.Y = curve.ScalarBaseMult(privateKeyBytes)

	// Hash the message
	hash := sha256.Sum256(message)

	// Sign the message
	r, s, err := ecdsa.Sign(nil, privateKey, hash[:])
	if err != nil {
		return nil, err
	}

	// Concatenate r and s to form the signature
	signature := append(r.Bytes(), s.Bytes()...)
	return signature, nil
}

func main() {
	message := []byte("hello")
	privateKeyHex := "71776572747975696f706173646667686a6b6c7a786376626e6d717765727479" // Hex representation of "qwertyuiopasdfghjklzxcvbnmqwerty"

	signature, err := signMessage(message, privateKeyHex)
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}

	fmt.Printf("Signature: %x\n", signature)
}