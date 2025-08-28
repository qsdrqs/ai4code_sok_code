package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

// sign signs a message using the provided ECDSA private key.
// It returns the signature as a byte slice, which is the concatenation
// of the fixed-length, big-endian representations of r and s.
func sign(message string, key *ecdsa.PrivateKey) ([]byte, error) {
	// Step 1: Hash the message using SHA-224
	hasher := sha256.New224()
	_, err := hasher.Write([]byte(message))
	if err != nil {
		return nil, err
	}
	digest := hasher.Sum(nil)

	// Step 2: Sign the hash using ECDSA
	r, s, err := ecdsa.Sign(rand.Reader, key, digest)
	if err != nil {
		return nil, err
	}

	// Step 3: Determine the fixed byte length for r and s
	curve := key.Curve
	n := curve.Params().N
	byteLen := (n.BitLen() + 7) / 8 // Calculate required byte length

	// Step 4: Encode r and s as fixed-length, big-endian byte slices
	rBytes := r.Bytes()
	sBytes := s.Bytes()

	rPadded := make([]byte, byteLen)
	sPadded := make([]byte, byteLen)

	// Pad r and s with leading zeros to ensure fixed length
	copy(rPadded[byteLen-len(rBytes):], rBytes)
	copy(sPadded[byteLen-len(sBytes):], sBytes)

	// Step 5: Concatenate r and s to form the final signature
	signature := append(rPadded, sPadded...)

	return signature, nil
}

func main() {
	// Step 1: Generate a new ECDSA private key using the NIST P-192 curve
	privKey, err := ecdsa.GenerateKey(elliptic.P192(), rand.Reader)
	if err != nil {
		panic(err)
	}

	// Step 2: Sign the message "something"
	sig, err := sign("something", privKey)
	if err != nil {
		panic(err)
	}

	// Step 3: Output the signature (as a slice of bytes)
	fmt.Println(sig)
}