package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

func ecdsaGenKey() (*ecdsa.PrivateKey, *ecdsa.PublicKey) {
	privateKey, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate ECDSA key: %v", err)
	}
	return privateKey, &privateKey.PublicKey
}

func ecdsaSign(privateKey *ecdsa.PrivateKey, message []byte) ([]byte, []byte) {
	hash := sha256.Sum256(message)
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}
	return r.Bytes(), s.Bytes()
}

func ecdsaVerifyKey(publicKey *ecdsa.PublicKey, rBytes, sBytes []byte, message []byte) bool {
	hash := sha256.Sum256(message)
	r := new(big.Int).SetBytes(rBytes)
	s := new(big.Int).SetBytes(sBytes)
	return ecdsa.Verify(publicKey, hash[:], r, s)
}

func main() {
	message := []byte("This is a test message")
	privateKey, publicKey := ecdsaGenKey()

	fmt.Printf("Private Key: %x\n", privateKey.D.Bytes())
	fmt.Printf("Public Key: %x\n", elliptic.Marshal(elliptic.P384(), publicKey.X, publicKey.Y))

	r, s := ecdsaSign(privateKey, message)
	fmt.Printf("Signature: R: %x, S: %x\n", r, s)

	isValid := ecdsaVerifyKey(publicKey, r, s, message)
	fmt.Printf("Signature valid: %v\n", isValid)
}