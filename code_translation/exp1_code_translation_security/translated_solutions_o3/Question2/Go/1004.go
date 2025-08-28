package main

import (
	"crypto/sha1"
	"fmt"
	"log"

	// 3rd-party implementation of secp256k1.
	//   go get github.com/btcsuite/btcd/btcec
	"github.com/btcsuite/btcd/btcec"
)

// signMessage signs arbitrary data with a raw 32-byte secp256k1 private key.
// It mirrors the behaviour of  python-ecdsa’s  SigningKey.sign, which hashes
// the message with SHA-1 and then signs the digest, returning a DER-encoded
// ECDSA signature.
func signMessage(message, privKeyBytes []byte) ([]byte, error) {
	// Build the ECDSA private key from the provided 32-byte buffer.
	privKey, _ := btcec.PrivKeyFromBytes(btcec.S256(), privKeyBytes)

	// python-ecdsa’s default hash function is SHA-1.
	digest := sha1.Sum(message)

	// Sign the 20-byte SHA-1 digest.
	sig, err := privKey.Sign(digest[:])
	if err != nil {
		return nil, err
	}

	// Serialize the signature in ASN.1/DER format (same as python-ecdsa).
	return sig.Serialize(), nil
}

func main() {
	msg := []byte("hello")
	priv := []byte("qwertyuiopasdfghjklzxcvbnmqwerty")

	sig, err := signMessage(msg, priv)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Printf("Signature: %x\n", sig)
}