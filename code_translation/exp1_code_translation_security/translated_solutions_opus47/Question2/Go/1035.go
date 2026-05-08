package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
)

func sign(msg []byte, sk *ecdsa.PrivateKey) []byte {
	hash := sha256.Sum256(msg)
	sig, err := ecdsa.SignASN1(rand.Reader, sk, hash[:])
	if err != nil {
		panic(err)
	}
	return sig
}

func main() {
	// Note: Go's standard library doesn't include NIST P-192 (NIST192p).
	// Using P-256 as the closest standard alternative available in crypto/elliptic.
	sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		panic(err)
	}
	vk := &sk.PublicKey

	msg := []byte("message")
	signature := sign(msg, sk)

	hash := sha256.Sum256(msg)
	if !ecdsa.VerifyASN1(vk, hash[:], signature) {
		panic("verification failed")
	}
	fmt.Println("Verified")
}