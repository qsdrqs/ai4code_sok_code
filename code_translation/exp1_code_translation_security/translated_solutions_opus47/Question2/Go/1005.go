package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
)

func sign(msg []byte, key *ecdsa.PrivateKey) []byte {
	vk := &key.PublicKey
	hash := sha256.Sum256(msg)
	signature, err := ecdsa.SignASN1(rand.Reader, key, hash[:])
	if err != nil {
		panic(err)
	}
	if !ecdsa.VerifyASN1(vk, hash[:], signature) {
		panic("verification failed")
	}
	return signature
}

func test() {
	sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		panic(err)
	}
	fmt.Println(sign([]byte("message"), sk))
}

func main() {
	test()
}