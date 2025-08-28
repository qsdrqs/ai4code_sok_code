package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"fmt"
	"log"
)

// sign produces an ASN.1-encoded ECDSA signature for msg using sk.
func sign(msg []byte, sk *ecdsa.PrivateKey) ([]byte, error) {
	return ecdsa.SignASN1(rand.Reader, sk, msg)
}

// verify checks that sig is a valid signature of msg under vk.
func verify(sig, msg []byte, vk *ecdsa.PublicKey) bool {
	return ecdsa.VerifyASN1(vk, msg, sig)
}

func main() {
	// Generate a fresh key pair (NIST P-192 if provided, otherwise P-256 here).
	sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader) // replace with elliptic.P192() if supplied
	if err != nil {
		log.Fatalf("key generation failed: %v", err)
	}
	vk := &sk.PublicKey

	// Sign and immediately verify the message.
	message := []byte("message")
	sig, err := sign(message, sk)
	if err != nil {
		log.Fatalf("signing failed: %v", err)
	}

	if !verify(sig, message, vk) {
		log.Fatal("signature verification failed")
	}

	fmt.Println("signature verified successfully")
}