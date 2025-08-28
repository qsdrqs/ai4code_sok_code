package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha3"
	"fmt"
	"math/big"
)

func sha3_256Hash(msg string) *big.Int {
	hasher := sha3.New256()
	hasher.Write([]byte(msg))
	hashBytes := hasher.Sum(nil)
	return new(big.Int).SetBytes(hashBytes)
}

func signECDSAsecp256k1(msg string, privKey *ecdsa.PrivateKey) (r, s *big.Int, err error) {
	msgHash := sha3_256Hash(msg)
	r, s, err = ecdsa.Sign(rand.Reader, privKey, msgHash.Bytes())
	return
}

func verifyECDSAsecp256k1(msg string, r, s *big.Int, pubKey *ecdsa.PublicKey) bool {
	msgHash := sha3_256Hash(msg)
	return ecdsa.Verify(pubKey, msgHash.Bytes(), r, s)
}

func main() {
	// ECDSA sign message (using the curve secp256k1 + SHA3-256)
	msg := "Message for ECDSA signing"

	// Generate a private key
	privKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		fmt.Println("Error generating private key:", err)
		return
	}

	// Sign the message
	r, s, err := signECDSAsecp256k1(msg, privKey)
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}

	// Print the results
	fmt.Println("Message:", msg)
	fmt.Printf("Private key: %x\n", privKey.D)
	fmt.Printf("Signature: r=%x, s=%x\n", r, s)

	// Verify the signature
	valid := verifyECDSAsecp256k1(msg, r, s, &privKey.PublicKey)
	fmt.Println("Signature valid:", valid)
}