package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"crypto/x509"
	"encoding/asn1"
	"encoding/hex"
	"fmt"
	"log"
	"math/big"
)

// ASN.1 structure for DER encoding of ECDSA signature
type ecdsaSignature struct {
	R, S *big.Int
}

func signMessage(message string, privateKeyBytes []byte) (string, error) {
	// Hash the message
	messageHash := sha256.Sum256([]byte(message))

	// Create private key from bytes
	privateKey := new(big.Int).SetBytes(privateKeyBytes)
	
	// Create ECDSA private key
	ecdsaPrivateKey := &ecdsa.PrivateKey{
		PublicKey: ecdsa.PublicKey{
			Curve: elliptic.P256(), // Note: Using P256 instead of secp256k1
		},
		D: privateKey,
	}
	ecdsaPrivateKey.PublicKey.X, ecdsaPrivateKey.PublicKey.Y = ecdsaPrivateKey.PublicKey.Curve.ScalarBaseMult(privateKeyBytes)

	// Sign the message hash
	r, s, err := ecdsa.Sign(rand.Reader, ecdsaPrivateKey, messageHash[:])
	if err != nil {
		return "", err
	}

	// Create DER encoded signature
	signature := ecdsaSignature{R: r, S: s}
	derSignature, err := asn1.Marshal(signature)
	if err != nil {
		return "", err
	}

	// Return hex encoded signature
	return hex.EncodeToString(derSignature), nil
}

func main() {
	// Test the function
	privateKeyHex := "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109"
	privateKey, err := hex.DecodeString(privateKeyHex)
	if err != nil {
		log.Fatal(err)
	}

	message := "Hello"
	signature, err := signMessage(message, privateKey)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println(signature)
}