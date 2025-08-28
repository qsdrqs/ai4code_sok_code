package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha1"
	"fmt"
	"math/big"
)

// SigningKey represents an ECDSA signing key (equivalent to Python's SigningKey)
type SigningKey struct {
	privateKey *ecdsa.PrivateKey
}

// VerifyingKey represents an ECDSA verifying key (equivalent to Python's VerifyingKey)
type VerifyingKey struct {
	publicKey *ecdsa.PublicKey
}

// Signature represents an ECDSA signature
type Signature struct {
	R, S *big.Int
}

// Generate creates a new SigningKey (uses P-192 curve to match NIST192p)
func Generate() *SigningKey {
	privateKey, err := ecdsa.GenerateKey(elliptic.P192(), rand.Reader)
	if err != nil {
		panic(err)
	}
	return &SigningKey{privateKey: privateKey}
}

// VerifyingKey returns the corresponding verifying key
func (sk *SigningKey) VerifyingKey() *VerifyingKey {
	return &VerifyingKey{publicKey: &sk.privateKey.PublicKey}
}

// Sign signs a message and returns the signature
func (sk *SigningKey) Sign(message []byte) *Signature {
	// Hash the message (using SHA-1 to match common ECDSA usage)
	hash := sha1.Sum(message)
	
	r, s, err := ecdsa.Sign(rand.Reader, sk.privateKey, hash[:])
	if err != nil {
		panic(err)
	}
	
	return &Signature{R: r, S: s}
}

// Verify verifies a signature against a message
func (vk *VerifyingKey) Verify(signature *Signature, message []byte) bool {
	hash := sha1.Sum(message)
	return ecdsa.Verify(vk.publicKey, hash[:], signature.R, signature.S)
}

// sign function equivalent to the Python version
func sign(message []byte, key *SigningKey) *Signature {
	vk := key.VerifyingKey()
	_ = vk // equivalent to Python's vk = key.verifying_key (stored but not used)
	signature := key.Sign(message)
	return signature
}

func main() {
	// Sample code equivalent (commented out in original)
	// sk := Generate()
	// vk := sk.VerifyingKey()
	// signature := sk.Sign([]byte("message"))
	// fmt.Println(vk.Verify(signature, []byte("message"))) // should print true
	
	// Main execution equivalent to Python print statement
	signature := sign([]byte("i am a message"), Generate())
	fmt.Printf("%+v\n", signature)
}