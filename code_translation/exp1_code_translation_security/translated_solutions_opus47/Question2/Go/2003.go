package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
)

func sign(inp string, key *ecdsa.PrivateKey) ([]byte, error) {
	hash := sha256.Sum256([]byte(inp))
	return ecdsa.SignASN1(rand.Reader, key, hash[:])
}

func main() {
	sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		panic(err)
	}

	sig, err := sign("Hello world", sk)
	if err != nil {
		panic(err)
	}

	fmt.Println(sig)
}