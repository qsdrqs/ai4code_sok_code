package main

import (
	"crypto/ecdsa"
	"crypto/rand"
	"fmt"
	"math/big"

	"github.com/btcsuite/btcd/btcec/v2"
	"golang.org/x/crypto/sha3"
)

// sha3_256Hash computes SHA3-256 hash of the message and returns the hash bytes.
func sha3_256Hash(msg string) []byte {
	hash := sha3.Sum256([]byte(msg))
	return hash[:]
}

// signECDSAsecp256k1 signs a message using ECDSA with secp256k1 curve and SHA3-256.
func signECDSAsecp256k1(msg string, privKeyInt *big.Int) (*big.Int, *big.Int, error) {
	msgHash := sha3_256Hash(msg)

	curve := btcec.S256()
	priv := new(ecdsa.PrivateKey)
	priv.PublicKey.Curve = curve
	priv.D = privKeyInt
	priv.PublicKey.X, priv.PublicKey.Y = curve.ScalarBaseMult(privKeyInt.Bytes())

	return ecdsa.Sign(rand.Reader, priv, msgHash)
}

// verifyECDSAsecp256k1 verifies an ECDSA signature using secp256k1 curve and SHA3-256.
func verifyECDSAsecp256k1(msg string, r, s, pubX, pubY *big.Int) bool {
	msgHash := sha3_256Hash(msg)

	curve := btcec.S256()
	pub := &ecdsa.PublicKey{
		Curve: curve,
		X:     pubX,
		Y:     pubY,
	}

	return ecdsa.Verify(pub, msgHash, r, s)
}

func main() {
	// ECDSA sign message (using the curve secp256k1 + SHA3-256)
	msg := "Message for ECDSA signing"
	curve := btcec.S256()

	// signing key, must be integer less than curve order
	privKey, err := rand.Int(rand.Reader, curve.Params().N)
	if err != nil {
		panic(err)
	}

	r, s, err := signECDSAsecp256k1(msg, privKey)
	if err != nil {
		panic(err)
	}

	fmt.Println("Message:", msg)
	fmt.Printf("Private key: 0x%x\n", privKey)
	fmt.Printf("Signature: r=0x%x, s=0x%x\n", r, s)
}