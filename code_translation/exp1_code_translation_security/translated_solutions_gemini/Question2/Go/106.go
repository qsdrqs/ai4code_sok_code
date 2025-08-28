package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"

	// This dependency provides the SECP256k1 curve implementation.
	// To install it, run: go get github.com/btcsuite/btcd/btcec/v2
	"github.com/btcsuite/btcd/btcec/v2"
)

/*
Signs a given message using a given ECDSA signing key.

In Go's crypto/ecdsa, signing is performed on a message hash, not the raw message.
This function first hashes the message with SHA-256 and then signs the hash.

Args:
    message: input message as a byte slice.
    key: ECDSA private key used to sign the message.

Return:
    The ASN.1 DER encoded signature and an error if one occurred.
*/
func sign(message []byte, key *ecdsa.PrivateKey) ([]byte, error) {
	// The message must be hashed before signing.
	hasher := sha256.New()
	hasher.Write(message)
	msgHash := hasher.Sum(nil)

	// Sign the hash of the message.
	// ecdsa.SignASN1 returns the signature in ASN.1 DER format, which is a common
	// standard and matches the default behavior of the Python ecdsa library.
	signature, err := ecdsa.SignASN1(rand.Reader, key, msgHash)
	if err != nil {
		return nil, err
	}
	return signature, nil
}

func main() {
	// Python: sk = ecdsa.SigningKey.generate(curve=ecdsa.SECP256k1)
	// We use the btcec library for the S256 (SECP256k1) curve.
	privateKey, err := ecdsa.GenerateKey(btcec.S256(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// Python: vk = sk.get_verifying_key()
	// The public key is part of the private key struct in Go.
	publicKey := &privateKey.PublicKey

	// The message to be signed.
	message := []byte("helloworld")

	// Python: signed_message = sign(b"helloworld", sk)
	signedMessage, err := sign(message, privateKey)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// Python: print(vk.verify(signed_message, b"helloworld"))
	// To verify, we also need the hash of the original message.
	hasher := sha256.New()
	hasher.Write(message)
	msgHash := hasher.Sum(nil)

	// Verify the signature against the hash using the public key.
	// ecdsa.VerifyASN1 expects the signature in ASN.1 DER format.
	verified := ecdsa.VerifyASN1(publicKey, msgHash, signedMessage)

	// Print the verification result (will be true if the signature is valid).
	fmt.Println(verified)
}