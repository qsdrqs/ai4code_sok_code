package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha1"
)

func signMessage(message []byte, key *ecdsa.PrivateKey) ([]byte, error) {
	hash := sha1.Sum([]byte("message"))
	return ecdsa.SignASN1(rand.Reader, key, hash[:])
}