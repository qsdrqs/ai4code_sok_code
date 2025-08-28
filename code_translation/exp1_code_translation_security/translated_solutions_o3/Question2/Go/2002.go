package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"encoding/asn1"
	"fmt"
	"log"
	"math/big"
)

// Sign signs the UTF-8 representation of m with sk and
// returns an ASN.1-encoded (r,s) ECDSA signature.
func Sign(sk *ecdsa.PrivateKey, m string) ([]byte, error) {
	digest := sha256.Sum256([]byte(m))

	r, s, err := ecdsa.Sign(rand.Reader, sk, digest[:])
	if err != nil {
		return nil, err
	}

	// Encode (r,s) so we can store them in a single []byte,
	// just like the python-ecdsa library does internally.
	return asn1.Marshal(struct {
		R, S *big.Int
	}{r, s})
}

func test() {
	// Generate a key pair (P-256 curve).
	sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatalf("key generation failed: %v", err)
	}

	// Sign “hello world”.
	sig, err := Sign(sk, "hello world")
	if err != nil {
		log.Fatalf("signing failed: %v", err)
	}

	// Prepare the wrong message (“hello worfld”) for verification.
	digest := sha256.Sum256([]byte("hello worfld"))

	// Split the ASN.1 signature back into r and s.
	var rs struct {
		R, S *big.Int
	}
	if _, err := asn1.Unmarshal(sig, &rs); err != nil {
		log.Fatalf("bad signature format: %v", err)
	}

	// Verify and print the result (should be false).
	ok := ecdsa.Verify(&sk.PublicKey, digest[:], rs.R, rs.S)
	fmt.Println(ok)
}

func main() {
	test()
}