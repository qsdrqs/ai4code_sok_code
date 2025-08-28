package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"encoding/hex"
	"fmt"
	"log"
)

// Function to generate a new ECDSA key pair
func generateKeyPair() (*ecdsa.PrivateKey, *ecdsa.PublicKey) {
	privateKey, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		log.Fatal(err)
	}
	publicKey := privateKey.Public()
	return privateKey, publicKey
}

// Function to sign a message with a private key
func signMessage(privateKey *ecdsa.PrivateKey, message []byte) ([]byte, error) {
	r, s, err := ecdsa.SignASN1(rand.Reader, privateKey, message)
	if err != nil {
		return nil, err
	}
	signature := append(r.Bytes(), s.Bytes()...)
	return signature, nil
}

// Function to verify a signature with a public key and message
func verifySignature(publicKey *ecdsa.PublicKey, message []byte, signature []byte) bool {
	r := new(big.Int).SetBytes(signature[:len(signature)/2])
	s := new(big.Int).SetBytes(signature[len(signature)/2:])
	return ecdsa.VerifyASN1(publicKey, message, r, s)
}

func main() {
	privateKey, publicKey := generateKeyPair()
	message := []byte("testing")
	signature, err := signMessage(privateKey, message)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println(verifySignature(publicKey, message, signature))
}