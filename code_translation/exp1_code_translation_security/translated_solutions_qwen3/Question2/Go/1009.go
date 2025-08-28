package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha512"
	"encoding/asn1"
	"fmt"
	"log"
)

// ecdsaSignature represents the structure of an ECDSA signature for ASN.1 marshaling.
type ecdsaSignature struct {
	R *big.Int
	S *big.Int
}

// sign signs the given message using the provided ECDSA private key.
// It returns the DER-encoded signature.
func sign(sk *ecdsa.PrivateKey, message []byte) ([]byte, error) {
	// Step 1: Hash the message using SHA-384
	digest := sha512.Sum384(message)

	// Step 2: Sign the hash using the private key
	r, s, err := ecdsa.Sign(rand.Reader, sk, digest[:])
	if err != nil {
		return nil, err
	}

	// Step 3: Marshal the signature into DER format
	sig := ecdsaSignature{R: r, S: s}
	derSig, err := asn1.Marshal(sig)
	if err != nil {
		return nil, err
	}

	return derSig, nil
}

func main() {
	// Step 1: Define the message to be signed
	message := []byte("hehehe")

	// Step 2: Generate a private key using the NIST P-384 curve
	sk, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		log.Fatal("Failed to generate private key:", err)
	}

	// Step 3: Sign the message
	sig, err := sign(sk, message)
	if err != nil {
		log.Fatal("Failed to sign message:", err)
	}

	// Step 4: Verify the signature
	{
		// Re-hash the message
		digest := sha512.Sum384(message)

		// Unmarshal the DER-encoded signature
		var sigObj ecdsaSignature
		_, err := asn1.Unmarshal(sig, &sigObj)
		if err != nil {
			log.Fatal("Failed to unmarshal signature:", err)
		}

		// Verify the signature using the public key
		valid := ecdsa.Verify(&sk.PublicKey, digest[:], sigObj.R, sigObj.S)
		fmt.Println("Signature is valid:", valid)
	}
}