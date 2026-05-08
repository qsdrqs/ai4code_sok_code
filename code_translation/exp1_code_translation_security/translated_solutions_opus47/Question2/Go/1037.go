package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha512"
	"fmt"
)

func main() {
	sk, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		panic(err)
	}
	vk := &sk.PublicKey
	signature, err := signMessage(sk, []byte("testing"))
	if err != nil {
		panic(err)
	}

	hash := sha512.Sum384([]byte("testing"))
	fmt.Println(ecdsa.VerifyASN1(vk, hash[:], signature))
}

func signMessage(key *ecdsa.PrivateKey, message []byte) ([]byte, error) {
	hash := sha512.Sum384(message)
	return ecdsa.SignASN1(rand.Reader, key, hash[:])
}