package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"fmt"
)

// sign signs the given message using the provided private key and returns the DER-encoded signature.
// It also verifies the signature before returning it.
func sign(msg []byte, key *ecdsa.PrivateKey) []byte {
	// Sign the message using the private key
	r, s, err := ecdsa.SignASN1(rand.Reader, key, msg)
	if err != nil {
		panic(err)
	}

	// Verify the signature using the public key
	if !ecdsa.Verify(&key.PublicKey, msg, r, s) {
		panic("signature verification failed")
	}

	// Marshal the signature into DER format
	signature, err := ecdsa.MarshalECDSASignature(r, s)
	if err != nil {
		panic(err)
	}

	return signature
}

// test generates a new ECDSA private key and signs the message "message".
func test() {
	// Generate a new ECDSA private key using the P-256 curve
	sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		panic(err)
	}

	// Sign the message and print the result
	sig := sign([]byte("message"), sk)
	fmt.Println(sig)
}

// main runs the test function
func main() {
	test()
}