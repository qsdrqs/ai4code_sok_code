package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

// Signature represents an ECDSA signature
type Signature struct {
	R, S *big.Int
}

// ECDSA utility functions
type Ecdsa struct{}

// Sign creates an ECDSA signature for the given message using the private key
func (e *Ecdsa) Sign(message string, privateKey *ecdsa.PrivateKey) (*Signature, error) {
	// Hash the message
	hash := sha256.Sum256([]byte(message))
	
	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, err
	}
	
	return &Signature{R: r, S: s}, nil
}

// Verify checks if the signature is valid for the given message and public key
func (e *Ecdsa) Verify(message string, signature *Signature, publicKey *ecdsa.PublicKey) bool {
	// Hash the message
	hash := sha256.Sum256([]byte(message))
	
	// Verify the signature
	return ecdsa.Verify(publicKey, hash[:], signature.R, signature.S)
}

// PrivateKey wrapper
type PrivateKey struct {
	key *ecdsa.PrivateKey
}

// NewPrivateKey generates a new private key
func NewPrivateKey() (*PrivateKey, error) {
	key, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		return nil, err
	}
	return &PrivateKey{key: key}, nil
}

// PublicKey returns the public key associated with this private key
func (pk *PrivateKey) PublicKey() *ecdsa.PublicKey {
	return &pk.key.PublicKey
}

// GetKey returns the underlying ecdsa.PrivateKey
func (pk *PrivateKey) GetKey() *ecdsa.PrivateKey {
	return pk.key
}

func main() {
	// Generate Keys
	privateKey, err := NewPrivateKey()
	if err != nil {
		panic(err)
	}
	publicKey := privateKey.PublicKey()

	message := "My test message"

	// Create ECDSA instance
	ecdsa := &Ecdsa{}

	// Generate Signature
	signature, err := ecdsa.Sign(message, privateKey.GetKey())
	if err != nil {
		panic(err)
	}

	// Verify if signature is valid
	isValid := ecdsa.Verify(message, signature, publicKey)
	fmt.Println(isValid)
}