package main

// well there's a crypto/ecdsa go library but that would be cheating
import (
	"crypto/ecdsa"
	// what
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha1"
	"fmt"
)

// sample code from crypto/ecdsa docs
// sk, _ := ecdsa.GenerateKey(elliptic.P224(), rand.Reader) // P224 (Go stdlib doesn't ship NIST192p/P-192)
// vk := &sk.PublicKey
// hash := sha1.Sum([]byte("message"))
// signature, _ := ecdsa.SignASN1(rand.Reader, sk, hash[:])
// if !ecdsa.VerifyASN1(vk, hash[:], signature) { panic("invalid") }

func sign(message []byte, key *ecdsa.PrivateKey) []byte {
	_ = key.PublicKey // vk := &key.PublicKey
	hash := sha1.Sum(message)
	signature, err := ecdsa.SignASN1(rand.Reader, key, hash[:])
	if err != nil {
		panic(err)
	}
	return signature
}

func main() {
	key, err := ecdsa.GenerateKey(elliptic.P224(), rand.Reader)
	if err != nil {
		panic(err)
	}
	fmt.Println(sign([]byte("i am a message"), key))
}