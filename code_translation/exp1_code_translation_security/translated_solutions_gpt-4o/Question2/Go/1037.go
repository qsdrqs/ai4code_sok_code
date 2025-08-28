package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

func main() {
	// Generate a new ECDSA private key using the P-384 curve
	privateKey, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		fmt.Println("Error generating key:", err)
		return
	}

	// Sign the message
	message := []byte("testing")
	signature, err := signMessage(privateKey, message)
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}

	// Verify the signature
	valid := verifySignature(&privateKey.PublicKey, message, signature)
	fmt.Println(valid)
}

func signMessage(privateKey *ecdsa.PrivateKey, message []byte) ([]byte, error) {
	// Hash the message
	hash := sha256.Sum256(message)

	// Sign the hashed message
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, err
	}

	// Concatenate r and s to form the signature
	signature := append(r.Bytes(), s.Bytes()...)
	return signature, nil
}

func verifySignature(publicKey *ecdsa.PublicKey, message, signature []byte) bool {
	// Hash the message
	hash := sha256.Sum256(message)

	// Split the signature into r and s
	r := new(big.Int).SetBytes(signature[:len(signature)/2])
	s := new(big.Int).SetBytes(signature[len(signature)/2:])

	// Verify the signature
	return ecdsa.Verify(publicKey, hash[:], r, s)
}