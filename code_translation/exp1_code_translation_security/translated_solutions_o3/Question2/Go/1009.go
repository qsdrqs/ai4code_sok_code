package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha512"
	"fmt"
	"log"
)

// sign signs msg with the given private key and returns a DER-encoded ECDSA signature.
func sign(priv *ecdsa.PrivateKey, msg []byte) ([]byte, error) {
	hash := sha512.Sum384(msg)                     // SHA-384 is the usual hash for P-384
	sig, err := ecdsa.SignASN1(rand.Reader, priv, hash[:])
	if err != nil {
		return nil, err
	}
	return sig, nil
}

func main() {
	message := []byte("hehehe")

	// Generate a new P-384 (a.k.a. NIST384p) key pair.
	priv, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		log.Fatalf("key generation failed: %v", err)
	}

	// Sign the message.
	sig, err := sign(priv, message)
	if err != nil {
		log.Fatalf("signing failed: %v", err)
	}

	// Verify the signature.
	hash := sha512.Sum384(message)
	ok := ecdsa.VerifyASN1(&priv.PublicKey, hash[:], sig)

	fmt.Println(ok) // should print: true
}