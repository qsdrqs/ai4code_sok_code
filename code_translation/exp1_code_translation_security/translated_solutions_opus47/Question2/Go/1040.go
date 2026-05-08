package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha1"

	"github.com/btcsuite/btcd/btcec/v2"
)

// SignMessage signs a message using ECDSA with SECP256k1 curve
func SignMessage(message []byte, privateKey []byte) ([]byte, error) {
	// Create private key from bytes using SECP256k1 curve
	// Equivalent to: sk = SigningKey.from_string(private_key, curve=SECP256k1)
	privKey, _ := btcec.PrivKeyFromBytes(privateKey)

	// Hash the message with SHA-1 (Python ecdsa library's default hashfunc)
	hash := sha1.Sum(message)

	// Sign the hash
	// Equivalent to: signature = sk.sign(message)
	r, s, err := ecdsa.Sign(rand.Reader, privKey.ToECDSA(), hash[:])
	if err != nil {
		return nil, err
	}

	// Convert r and s to 32-byte arrays and concatenate
	// (matches Python ecdsa's default sigencode_string encoding: r || s)
	rBytes := r.Bytes()
	sBytes := s.Bytes()

	signature := make([]byte, 64)
	copy(signature[32-len(rBytes):32], rBytes)
	copy(signature[64-len(sBytes):64], sBytes)

	return signature, nil
}