package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/sha256"
	"encoding/hex"
	"fmt"
	"math/big"
)

func signMessage(message string, privateKey []byte) (string, error) {
	// Hash the message
	messageHash := sha256.Sum256([]byte(message))

	// Create the private key
	curve := elliptic.P256()
	d := new(big.Int).SetBytes(privateKey)
	privKey := new(ecdsa.PrivateKey)
	privKey.PublicKey.Curve = curve
	privKey.D = d
	privKey.PublicKey.X, privKey.PublicKey.Y = curve.ScalarBaseMult(privateKey)

	// Sign the message
	r, s, err := ecdsa.Sign(nil, privKey, messageHash[:])
	if err != nil {
		return "", err
	}

	// Encode the signature in DER format
	signature := append(r.Bytes(), s.Bytes()...)
	return hex.EncodeToString(signature), nil
}

func main() {
	// Test the function
	privateKey, _ := hex.DecodeString("e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109")
	message := "Hello"
	signature, err := signMessage(message, privateKey)
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}
	fmt.Println(signature)
}