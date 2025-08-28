package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
)

func sign(sk *ecdsa.PrivateKey, message string) ([]byte, []byte, error) {
	hash := sha256.Sum256([]byte(message))
	r, s, err := ecdsa.Sign(rand.Reader, sk, hash[:])
	if err != nil {
		return nil, nil, err
	}
	return r.Bytes(), s.Bytes(), nil
}

func verify(vk *ecdsa.PublicKey, message string, rBytes, sBytes []byte) bool {
	hash := sha256.Sum256([]byte(message))
	var r, s = new(big.Int), new(big.Int)
	r.SetBytes(rBytes)
	s.SetBytes(sBytes)
	return ecdsa.Verify(vk, hash[:], r, s)
}

func main() {
	sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate key: %v", err)
	}
	vk := &sk.PublicKey

	r, s, err := sign(sk, "hello world")
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// Note: The message here is intentionally incorrect to match the Python code's behavior
	isValid := verify(vk, "hello worfld", r, s)
	fmt.Println(isValid)
}