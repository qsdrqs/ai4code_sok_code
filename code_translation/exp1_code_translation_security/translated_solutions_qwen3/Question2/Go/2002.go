package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha1"
	"encoding/asn1"
	"fmt"
)

// sign signs the given message using the provided ECDSA private key.
// The message is first hashed using SHA-1 before signing.
func sign(sk *ecdsa.PrivateKey, m string) ([]byte, error) {
	// Convert the message to bytes and hash it using SHA-1
	digest := sha1.Sum([]byte(m))

	// Sign the hash using the private key
	return ecdsa.Sign(rand.Reader, sk, digest[:])
}

// test simulates the test function from the Python code.
// It generates a key pair, signs "hello world", and verifies the signature
// against the incorrect message "hello worfld".
func test() {
	// Generate a new ECDSA private key using the P-256 curve
	sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		panic(err)
	}
	vk := sk.PublicKey // Extract the public key

	// Sign the message "hello world"
	sig, err := sign(sk, "hello world")
	if err != nil {
		panic(err)
	}

	// Prepare the message to verify: "hello worfld" (note the typo)
	message := "hello worfld"
	digest := sha1.Sum([]byte(message))

	// Define a struct to unmarshal the DER-encoded signature
	var parsedSig struct {
		R *big.Int
		S *big.Int
	}

	// Unmarshal the DER-encoded signature into R and S
	rest, err := asn1.Unmarshal(sig, &parsedSig)
	if err != nil || len(rest) != 0 {
		panic("invalid signature")
	}

	// Verify the signature using the public key and the new message hash
	valid := ecdsa.Verify(&vk, digest[:], parsedSig.R, parsedSig.S)

	// Print the result of the verification
	fmt.Println(valid)
}

// main runs the test function
func main() {
	test()
}