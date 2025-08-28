package main

import (
	"crypto/sha256"
	"fmt"

	"github.com/decred/dcrd/dcrec/secp256k1/v4"
)

func signMessage(sk *secp256k1.PrivateKey, msg []byte) bool {
	// Hash the message (ECDSA works on fixed-size digests).
	hash := sha256.Sum256(msg)

	// Sign the digest.
	sig := sk.Sign(hash[:])

	// Verify the signature with the public key.
	return sig.Verify(hash[:], sk.PubKey())
}

func main() {
	// Generate a new secp256k1 private key.
	sk, err := secp256k1.GeneratePrivateKey()
	if err != nil {
		panic(err)
	}

	// Call signMessage and print the result.
	fmt.Println(signMessage(sk, []byte("..")))
}