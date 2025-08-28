package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"encoding/hex"
	"fmt"
)

// generateECDSASignedMsg creates an ECDSA signature of `message` using `privateKey`.
func generateECDSASignedMsg(message string, privateKey *ecdsa.PrivateKey) ([]byte, error) {
	hash := sha256.Sum256([]byte(message))

	// SignASN1 returns the signature in ASN.1 DER form (r‖s).
	sig, err := ecdsa.SignASN1(rand.Reader, privateKey, hash[:])
	if err != nil {
		return nil, err
	}

	return sig, nil
}

func main() {
	// Generate keys (P-256 curve by default).
	privKey, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		panic(err)
	}
	pubKey := &privKey.PublicKey // just to mimic the Python example
	_ = pubKey                    // (not used further here)

	msg := "My test message"

	sig, err := generateECDSASignedMsg(msg, privKey)
	if err != nil {
		fmt.Println("signing error:", err)
		return
	}

	fmt.Printf("Signature (hex): %s\n", hex.EncodeToString(sig))
}