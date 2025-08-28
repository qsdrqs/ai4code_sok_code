package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"encoding/asn1"
	"fmt"
	"math/big"
)

// structure used to ASN-1/DER-encode the (r,s) pair returned by ecdsa.Sign
type ecdsaSignature struct {
	R, S *big.Int
}

// signMessage generates a random ECDSA key on the requested curve
// and signs the provided message.  Only the signature is returned,
// mirroring the behaviour of the original Python snippet.
func signMessage(message []byte, curve elliptic.Curve) ([]byte, error) {
	// 1. generate a fresh private key
	privKey, err := ecdsa.GenerateKey(curve, rand.Reader)
	if err != nil {
		return nil, err
	}

	// 2. (optional) obtain the verifying (public) key
	_ = &privKey.PublicKey // included just to demonstrate parity with Python

	// 3. sign the message (hash first — SHA-256 here)
	digest := sha256.Sum256(message)
	r, s, err := ecdsa.Sign(rand.Reader, privKey, digest[:])
	if err != nil {
		return nil, err
	}

	// 4. marshal (r,s) into a byte slice (DER)
	sig, err := asn1.Marshal(ecdsaSignature{R: r, S: s})
	if err != nil {
		return nil, err
	}

	return sig, nil
}

// small demo
func main() {
	msg := []byte("hello, world")

	signature, err := signMessage(msg, elliptic.P256())
	if err != nil {
		panic(err)
	}

	fmt.Printf("Signature (DER): %x\n", signature)
}