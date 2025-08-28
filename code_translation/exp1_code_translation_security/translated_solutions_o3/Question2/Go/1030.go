package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"fmt"
	"math/big"

	"golang.org/x/crypto/sha3"

	// Any secp-256k1 implementation that satisfies elliptic.Curve is fine.
	// The following import works with:  github.com/decred/dcrd/dcrec/secp256k1/v4
	"github.com/decred/dcrd/dcrec/secp256k1/v4"
)

// sha3_256Hash returns the SHA3-256 digest of msg as a *big.Int.
func sha3_256Hash(msg string) *big.Int {
	h := sha3.New256()
	h.Write([]byte(msg))
	digest := h.Sum(nil)                // 32-byte slice
	return new(big.Int).SetBytes(digest) // convert to big.Int
}

// signECDSAsecp256k1 signs the given message with the supplied private key.
// (privKey must already be < curve order).
func signECDSAsecp256k1(msg string, privKey *big.Int) (r, s *big.Int, err error) {
	curve := secp256k1.S256() // secp256k1 curve (implements elliptic.Curve)

	// Build an ecdsa.PrivateKey from the raw scalar.
	pk := new(ecdsa.PrivateKey)
	pk.PublicKey.Curve = curve
	pk.D = privKey
	pk.PublicKey.X, pk.PublicKey.Y = curve.ScalarBaseMult(privKey.Bytes())

	hashBytes := sha3_256Hash(msg).Bytes()
	r, s, err = ecdsa.Sign(rand.Reader, pk, hashBytes)
	return
}

// verifyECDSAsecp256k1 verifies an (r,s) signature over msg with pubKey.
func verifyECDSAsecp256k1(msg string, r, s *big.Int, pubKey *ecdsa.PublicKey) bool {
	hashBytes := sha3_256Hash(msg).Bytes()
	return ecdsa.Verify(pubKey, hashBytes, r, s)
}

func main() {
	curve := secp256k1.S256()
	msg := "Message for ECDSA signing"

	// Generate a random private scalar < curve order.
	privKey, err := rand.Int(rand.Reader, curve.Params().N)
	if err != nil {
		panic(err)
	}

	// Sign
	r, s, err := signECDSAsecp256k1(msg, privKey)
	if err != nil {
		panic(err)
	}

	// Reconstruct the public key for printing / verification.
	pubX, pubY := curve.ScalarBaseMult(privKey.Bytes())
	pubKey := ecdsa.PublicKey{Curve: curve, X: pubX, Y: pubY}

	fmt.Println("Message:", msg)
	fmt.Printf("Private key: 0x%x\n", privKey)
	fmt.Printf("Signature: r=0x%x, s=0x%x\n", r, s)

	// (Optional) Quick verification demo
	ok := verifyECDSAsecp256k1(msg, r, s, &pubKey)
	fmt.Println("Signature valid?", ok)
}