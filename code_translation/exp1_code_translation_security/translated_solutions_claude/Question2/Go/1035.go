package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha1"
	"fmt"
	"math/big"
)

func main() {
	// Generate a new ECDSA key pair using P-192 curve (equivalent to NIST192p)
	sk, err := ecdsa.GenerateKey(elliptic.P192(), rand.Reader)
	if err != nil {
		panic(err)
	}
	
	vk := &sk.PublicKey

	message := []byte("message")
	signature := sign(message, sk)
	
	// Verify the signature
	isValid := verify(signature, message, vk)
	if !isValid {
		panic("Signature verification failed")
	}
	
	fmt.Println("Signature verification successful")
}

// Signature represents an ECDSA signature
type Signature struct {
	R, S *big.Int
}

func sign(msg []byte, sk *ecdsa.PrivateKey) *Signature {
	// Hash the message (using SHA-1 to match ecdsa library default behavior)
	hash := sha1.Sum(msg)
	
	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, sk, hash[:])
	if err != nil {
		panic(err)
	}
	
	return &Signature{R: r, S: s}
}

func verify(sig *Signature, msg []byte, vk *ecdsa.PublicKey) bool {
	// Hash the message (using SHA-1 to match ecdsa library default behavior)
	hash := sha1.Sum(msg)
	
	// Verify the signature
	return ecdsa.Verify(vk, hash[:], sig.R, sig.S)
}