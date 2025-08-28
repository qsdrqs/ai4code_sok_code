package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

func sign(inp string, key *ecdsa.PrivateKey) (r, s *big.Int, err error) {
	hash := sha256.Sum256([]byte(inp))
	return ecdsa.Sign(rand.Reader, key, hash[:])
}

func main() {
	// Generate a new ECDSA private key using P-256 curve (equivalent to secp256r1)
	sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		panic(err)
	}
	
	r, s, err := sign("Hello world", sk)
	if err != nil {
		panic(err)
	}
	
	fmt.Printf("r: %s\ns: %s\n", r.String(), s.String())
}