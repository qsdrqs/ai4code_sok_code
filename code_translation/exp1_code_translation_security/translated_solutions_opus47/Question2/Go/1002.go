package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
)

func sign(key *ecdsa.PrivateKey, data []byte) ([]byte, error) {
	hash := sha256.Sum256(data)
	return ecdsa.SignASN1(rand.Reader, key, hash[:])
}

func test() {
	privateKey, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		panic(err)
	}
	data := []byte("this is some data I'd like to sign")
	signature, err := sign(privateKey, data)
	if err != nil {
		panic(err)
	}
	fmt.Println(signature)
}

func main() {
	test()
}