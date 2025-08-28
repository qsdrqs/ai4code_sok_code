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

// sign produces a DER‐encoded ECDSA signature (r,s) over the SHA-256 digest
// of the supplied data.
func sign(priv *ecdsa.PrivateKey, data []byte) ([]byte, error) {
	digest := sha256.Sum256(data)

	r, s, err := ecdsa.Sign(rand.Reader, priv, digest[:])
	if err != nil {
		return nil, err
	}

	// Encode (r,s) to the same ASN.1 DER format that Python's cryptography
	// library returns.
	type ecdsaSignature struct {
		R, S *big.Int
	}
	return asn1.Marshal(ecdsaSignature{r, s})
}

func main() {
	// Generate a P-384 private key
	priv, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		log.Fatal(err)
	}

	data := []byte("this is some data I'd like to sign")

	sig, err := sign(priv, data)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println(hex.EncodeToString(sig))
}