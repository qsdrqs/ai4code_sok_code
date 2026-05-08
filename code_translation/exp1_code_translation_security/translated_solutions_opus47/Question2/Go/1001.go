package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
)

func signUsingECDSA(key *ecdsa.PrivateKey, message []byte) ([]byte, error) {
	hash := sha256.Sum256(message)
	return ecdsa.SignASN1(rand.Reader, key, hash[:])
}