package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

func sign(sk *ecdsa.PrivateKey, message []byte) ([]byte, error) {
	// Hash the message
	hash := sha256.Sum256(message)
	
	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, sk, hash[:])
	if err != nil {
		return nil, err
	}
	
	// Encode signature as concatenated r and s values
	signature := make([]byte, 96) // 48 bytes for r + 48 bytes for s (P-384)
	rBytes := r.Bytes()
	sBytes := s.Bytes()
	
	// Pad with leading zeros if necessary and copy to signature
	copy(signature[48-len(rBytes):48], rBytes)
	copy(signature[96-len(sBytes):], sBytes)
	
	return signature, nil
}

func verify(pk *ecdsa.PublicKey, signature []byte, message []byte) bool {
	if len(signature) != 96 {
		return false
	}
	
	// Extract r and s from signature
	r := new(big.Int).SetBytes(signature[:48])
	s := new(big.Int).SetBytes(signature[48:])
	
	// Hash the message
	hash := sha256.Sum256(message)
	
	// Verify the signature
	return ecdsa.Verify(pk, hash[:], r, s)
}

func main() {
	message := []byte("hehehe")
	
	// Generate private key using P-384 curve (equivalent to NIST384p)
	sk, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		panic(err)
	}
	
	// Sign the message
	sig, err := sign(sk, message)
	if err != nil {
		panic(err)
	}
	
	// Verify the signature using the public key
	fmt.Println(verify(&sk.PublicKey, sig, message))
}