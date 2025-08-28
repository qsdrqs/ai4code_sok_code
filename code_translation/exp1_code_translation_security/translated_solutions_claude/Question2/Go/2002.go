package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

// ECDSA signature structure
type Signature struct {
	R, S *big.Int
}

// SigningKey represents an ECDSA private key
type SigningKey struct {
	privateKey *ecdsa.PrivateKey
}

// VerifyingKey represents an ECDSA public key
type VerifyingKey struct {
	publicKey *ecdsa.PublicKey
}

// Generate creates a new random signing key
func Generate() *SigningKey {
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		panic(err)
	}
	return &SigningKey{privateKey: privateKey}
}

// VerifyingKey returns the corresponding public key
func (sk *SigningKey) VerifyingKey() *VerifyingKey {
	return &VerifyingKey{publicKey: &sk.privateKey.PublicKey}
}

// Sign signs a message with the private key
func (sk *SigningKey) Sign(message []byte) *Signature {
	hash := sha256.Sum256(message)
	r, s, err := ecdsa.Sign(rand.Reader, sk.privateKey, hash[:])
	if err != nil {
		panic(err)
	}
	return &Signature{R: r, S: s}
}

// Verify verifies a signature against a message
func (vk *VerifyingKey) Verify(signature *Signature, message []byte) bool {
	hash := sha256.Sum256(message)
	return ecdsa.Verify(vk.publicKey, hash[:], signature.R, signature.S)
}

// sign function equivalent to the Python version
func sign(sk *SigningKey, m string) *Signature {
	return sk.Sign([]byte(m))
}

func test() {
	sk := Generate()
	vk := sk.VerifyingKey()
	signature := sign(sk, "hello world")
	fmt.Println(vk.Verify(signature, []byte("hello worfld")))
}

func main() {
	test()
}