package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

func main() {
	sk, err := generateSigningKey()
	if err != nil {
		panic(err)
	}
	
	vk := &sk.PublicKey
	message := []byte("testing")
	signature, err := signMessage(sk, message)
	if err != nil {
		panic(err)
	}
	
	isValid := verify(vk, signature, message)
	fmt.Println(isValid)
}

func generateSigningKey() (*ecdsa.PrivateKey, error) {
	// P-384 curve (equivalent to NIST384p)
	return ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
}

func signMessage(key *ecdsa.PrivateKey, message []byte) (*ECDSASignature, error) {
	// Hash the message using SHA-256
	hash := sha256.Sum256(message)
	
	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, key, hash[:])
	if err != nil {
		return nil, err
	}
	
	return &ECDSASignature{R: r, S: s}, nil
}

func verify(publicKey *ecdsa.PublicKey, signature *ECDSASignature, message []byte) bool {
	// Hash the message using SHA-256
	hash := sha256.Sum256(message)
	
	// Verify the signature
	return ecdsa.Verify(publicKey, hash[:], signature.R, signature.S)
}

// ECDSASignature represents an ECDSA signature
type ECDSASignature struct {
	R, S *big.Int
}