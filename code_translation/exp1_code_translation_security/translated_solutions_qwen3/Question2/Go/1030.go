package main

import (
	"crypto/rand"
	"crypto/sha3"
	"fmt"
	"math/big"

	"github.com/btcsuite/btcd/btcec"
)

// sha3_256Hash computes the SHA3-256 hash of a message string.
func sha3_256Hash(msg string) []byte {
	hash := sha3.Sum256([]byte(msg))
	return hash[:]
}

// signECDSAsecp256k1 signs a message using ECDSA with the secp256k1 curve.
func signECDSAsecp256k1(msg string, privKey *big.Int) (*big.Int, *big.Int, error) {
	hash := sha3_256Hash(msg)
	curve := btcec.S256()
	btcecPrivKey := btcec.PrivateKey{
		PublicKey: btcec.PublicKey{Curve: curve},
		D:         privKey,
	}
	signature, err := btcecPrivKey.Sign(hash)
	if err != nil {
		return nil, nil, err
	}
	return signature.R, signature.S, nil
}

// verifyECDSAsecp256k1 verifies an ECDSA signature using the secp256k1 curve.
func verifyECDSAsecp256k1(msg string, r, s *big.Int, pubKey *btcec.PublicKey) bool {
	hash := sha3_256Hash(msg)
	signature := &btcec.Signature{R: r, S: s}
	return signature.Verify(hash, pubKey)
}

func main() {
	msg := "Message for ECDSA signing"

	// Generate a random private key (equivalent to Python's secrets.randbelow(secp256k1_generator.order()))
	curve := btcec.S256()
	order := curve.Params().N
	privKeyInt, err := rand.Int(rand.Reader, order)
	if err != nil {
		panic(err)
	}

	// Sign the message
	r, s, err := signECDSAsecp256k1(msg, privKeyInt)
	if err != nil {
		panic(err)
	}

	// Print the results
	fmt.Println("Message:", msg)
	fmt.Printf("Private key: 0x%x\n", privKeyInt)
	fmt.Printf("Signature: r=0x%x, s=0x%x\n", r, s)
}