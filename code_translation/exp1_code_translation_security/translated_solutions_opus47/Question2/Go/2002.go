package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
)

func sign(sk *ecdsa.PrivateKey, m string) ([]byte, error) {
	hash := sha256.Sum256([]byte(m))
	return ecdsa.SignASN1(rand.Reader, sk, hash[:])
}

func test() {
	sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		panic(err)
	}
	vk := &sk.PublicKey
	signature, err := sign(sk, "hello world")
	if err != nil {
		panic(err)
	}
	hash := sha256.Sum256([]byte("hello worfld"))
	fmt.Println(ecdsa.VerifyASN1(vk, hash[:], signature))
}

func main() {
	test()
}