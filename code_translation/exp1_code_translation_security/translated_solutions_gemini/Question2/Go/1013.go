package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"encoding/asn1"
	"encoding/hex"
	"fmt"
	"log"
	"math/big"
)

// sign generates an ECDSA signature for a given message using a private key.
// The message is first hashed using SHA-256, and the resulting signature is
// encoded in ASN.1 DER format, which is a standard for ECDSA signatures.
func sign(message string, key *ecdsa.PrivateKey) ([]byte, error) {
	// The first step is to hash the message. The signature is calculated over the
	// hash of the data, not the data itself.
	hasher := sha256.New()
	hasher.Write([]byte(message))
	hash := hasher.Sum(nil)

	// ecdsa.Sign uses a source of randomness (rand.Reader) and the private key
	// to sign the hash. It returns the two components of the signature, r and s.
	r, s, err := ecdsa.Sign(rand.Reader, key, hash)
	if err != nil {
		return nil, err
	}

	// The Python library's default behavior is to concatenate r and s. However,
	// the standard and more interoperable way to represent an ECDSA signature
	// is to use ASN.1 DER encoding. We will use the standard format here.
	// We define a struct to hold the r and s values for marshaling.
	type ecdsaSignature struct {
		R, S *big.Int
	}

	// Marshal the R and S components into a DER-encoded byte slice.
	signature, err := asn1.Marshal(ecdsaSignature{R: r, S: s})
	if err != nil {
		return nil, err
	}

	return signature, nil
}

func main() {
	// The Python `ecdsa` library defaults to the NIST192p curve. This curve is
	// considered less secure by modern standards and is not implemented in Go's
	// standard crypto library. We will use P-256, which is a common, secure,
	// and recommended choice.
	privateKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		// Use log.Fatalf to print the error and exit if key generation fails.
		log.Fatalf("Failed to generate private key: %v", err)
	}

	message := "something"
	signature, err := sign(message, privateKey)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// The Python code prints the raw signature bytes, which can be unreadable in a
	// terminal. We'll print the signature as a hex-encoded string for clarity.
	fmt.Println(hex.EncodeToString(signature))
}