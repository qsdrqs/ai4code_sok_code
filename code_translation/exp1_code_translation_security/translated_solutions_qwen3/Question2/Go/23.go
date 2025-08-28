package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"encoding/asn1"
	"fmt"
	"math/big"
)

// Define a struct to hold the R and S components of the signature
type ecdsaSignature struct {
	R, S *big.Int
}

// sign signs the given message using the provided private key
func sign(message []byte, key *ecdsa.PrivateKey) []byte {
	// Sign the message
	r, s, err := ecdsa.Sign(rand.Reader, key, message)
	if err != nil {
		panic(err)
	}

	// Marshal the signature into DER format
	sigBytes, err := asn1.Marshal(ecdsaSignature{R: r, S: s})
	if err != nil {
		panic(err)
	}

	return sigBytes
}

func main() {
	// Generate a new ECDSA private key using the P-256 curve
	curve := elliptic.P256()
	privateKey, err := ecdsa.GenerateKey(curve, rand.Reader)
	if err != nil {
		panic(err)
	}

	// Sign the message
	message := []byte("i am a message")
	signature := sign(message, privateKey)

	// Print the raw signature bytes
	fmt.Println(signature)
}