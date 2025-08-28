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
	R *big.Int
	S *big.Int
}

// String returns a string representation of the signature
func (sig *Signature) String() string {
	return fmt.Sprintf("Signature{R: %s, S: %s}", sig.R.String(), sig.S.String())
}

// PrivateKey wraps the standard library's ECDSA private key
type PrivateKey struct {
	key *ecdsa.PrivateKey
}

// NewPrivateKey generates a new ECDSA private key using P-256 curve
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

// generateECDSASignedMsg signs a message using ECDSA
func generateECDSASignedMsg(message string, privateKey *PrivateKey) (*Signature, error) {
	// Hash the message
	hash := sha256.Sum256([]byte(message))
	
	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, privateKey.key, hash[:])
	if err != nil {
		return nil, err
	}
	
	return &Signature{R: r, S: s}, nil
}

func main() {
	// Generate Keys
	privateKey, err := NewPrivateKey()
	if err != nil {
		fmt.Printf("Error generating private key: %v\n", err)
		return
	}
	
	publicKey := privateKey.PublicKey()
	_ = publicKey // publicKey is available but not used in this example
	
	message := "My test message"
	signature, err := generateECDSASignedMsg(message, privateKey)
	if err != nil {
		fmt.Printf("Error signing message: %v\n", err)
		return
	}
	
	fmt.Println(signature)
}