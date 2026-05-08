package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha1"
)

func sign(msg []byte, key *ecdsa.PrivateKey) ([]byte, error) {
	hash := sha1.Sum([]byte("message"))
	sig, err := ecdsa.SignASN1(rand.Reader, key, hash[:])
	return sig, err
}