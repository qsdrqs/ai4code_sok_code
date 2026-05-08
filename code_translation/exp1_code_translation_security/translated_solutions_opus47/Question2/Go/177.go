package main

import (
	"crypto/ed25519"
)

func sign(m []byte, sk ed25519.PrivateKey) []byte {
	return ed25519.Sign(sk, m)
}