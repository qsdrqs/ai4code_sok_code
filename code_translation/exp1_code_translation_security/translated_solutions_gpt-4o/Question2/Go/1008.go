package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"encoding/hex"
	"fmt"
	"math/big"
)

// signMessage signs a message using an ECDSA private key
func signMessage(message, privateKeyHex string) ([]byte, error) {
	// Decode the private key from hex
	privateKeyBytes, err := hex.DecodeString(privateKeyHex)
	if err != nil {
		return nil, err
	}

	// Create a new ECDSA private key from the bytes
	curve := elliptic.P256() // SECP256k1 is not directly available, using P256 as an example
	privateKey := new(ecdsa.PrivateKey)
	privateKey.PublicKey.Curve = curve
	privateKey.D = new(big.Int).SetBytes(privateKeyBytes)
	privateKey.PublicKey.X, privateKey.PublicKey.Y = curve.ScalarBaseMult(privateKeyBytes)

	// Hash the message
	hash := sha256.Sum256([]byte(message))

	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, err
	}

	// Concatenate r and s to form the signature
	signature := append(r.Bytes(), s.Bytes()...)
	return signature, nil
}

func main() {
	signature, err := signMessage("hello world", "3132333435363738393031323334353637383930313233343536373839303132")
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}
	fmt.Printf("Signature: %x\n", signature)
}