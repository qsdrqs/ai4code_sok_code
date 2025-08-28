package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
	"fmt"

	"github.com/btcsuite/btcd/btcec" // secp256k1 implementation
)

// sign signs the given message with the provided ECDSA private key and
// returns the DER-encoded (ASN.1) signature.
func sign(message []byte, key *ecdsa.PrivateKey) ([]byte, error) {
	// Hash the message (ECDSA always signs a digest, not raw bytes)
	hash := sha256.Sum256(message)

	// SignASN1 returns the signature in the same DER format that Python's
	// ecdsa.sign() emits by default.
	return ecdsa.SignASN1(rand.Reader, key, hash[:])
}

func main() {
	// Generate a fresh ECDSA key on the secp256k1 curve
	privKey, err := ecdsa.GenerateKey(btcec.S256(), rand.Reader)
	if err != nil {
		panic(err)
	}

	// Sign the message
	message := []byte("helloworld")
	sig, err := sign(message, privKey)
	if err != nil {
		panic(err)
	}

	// Verify the signature
	hash := sha256.Sum256(message)
	valid := ecdsa.VerifyASN1(&privKey.PublicKey, hash[:], sig)

	fmt.Println(valid) // should print: true
}