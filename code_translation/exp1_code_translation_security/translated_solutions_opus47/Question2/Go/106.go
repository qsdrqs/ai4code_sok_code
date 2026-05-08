package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"

	"github.com/btcsuite/btcd/btcec/v2"
)

// sign signs a given message using a given ECDSA signing key
//
// Args:
//
//	message: input message
//	key: ECDSA key used to sign the message
//
// Return:
//
//	signed message
func sign(message []byte, key *ecdsa.PrivateKey) []byte {
	hash := sha256.Sum256(message)
	r, s, err := ecdsa.Sign(rand.Reader, key, hash[:])
	if err != nil {
		panic(err)
	}
	// Concatenate r and s (each padded to 32 bytes) to form the signature
	rBytes := r.Bytes()
	sBytes := s.Bytes()
	signature := make([]byte, 64)
	copy(signature[32-len(rBytes):32], rBytes)
	copy(signature[64-len(sBytes):64], sBytes)
	return signature
}

func main() {
	sk, err := ecdsa.GenerateKey(btcec.S256(), rand.Reader)
	if err != nil {
		panic(err)
	}
	vk := &sk.PublicKey

	signedMessage := sign([]byte("helloworld"), sk)

	hash := sha256.Sum256([]byte("helloworld"))
	r := new(big.Int).SetBytes(signedMessage[:32])
	s := new(big.Int).SetBytes(signedMessage[32:])
	fmt.Println(ecdsa.Verify(vk, hash[:], r, s))
}