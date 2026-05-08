package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha1"
	"fmt"
)

func sign(sk *ecdsa.PrivateKey, message []byte) []byte {
	// Python's ecdsa library uses SHA-1 as the default hash function
	hash := sha1.Sum(message)
	sig, err := ecdsa.SignASN1(rand.Reader, sk, hash[:])
	if err != nil {
		panic(err)
	}
	return sig
}

func main() {
	message := []byte("hehehe")
	sk, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		panic(err)
	}

	sig := sign(sk, message)

	hash := sha1.Sum(message)
	fmt.Println(ecdsa.VerifyASN1(&sk.PublicKey, hash[:], sig))
}