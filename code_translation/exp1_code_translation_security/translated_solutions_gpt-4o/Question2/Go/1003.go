package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/sha256"
	"math/big"
	"fmt"
)

// signNIST256 signs a message using the provided raw signing key.
func signNIST256(message []byte, signingKeyRaw []byte) ([]byte, []byte, error) {
	curve := elliptic.P256()
	x, y := elliptic.Unmarshal(curve, signingKeyRaw)
	if x == nil || y == nil {
		return nil, nil, fmt.Errorf("invalid signing key")
	}
	privateKey := &ecdsa.PrivateKey{
		PublicKey: ecdsa.PublicKey{
			Curve: curve,
			X:     x,
			Y:     y,
		},
		D: new(big.Int).SetBytes(signingKeyRaw),
	}

	hash := sha256.Sum256(message)
	r, s, err := ecdsa.Sign(nil, privateKey, hash[:])
	if err != nil {
		return nil, nil, err
	}
	return r.Bytes(), s.Bytes(), nil
}

// verifyNIST256 verifies a signature using the provided raw verifying key.
func verifyNIST256(message []byte, rBytes []byte, sBytes []byte, verifyingKeyRaw []byte) bool {
	curve := elliptic.P256()
	x, y := elliptic.Unmarshal(curve, verifyingKeyRaw)
	if x == nil || y == nil {
		return false
	}
	publicKey := &ecdsa.PublicKey{
		Curve: curve,
		X:     x,
		Y:     y,
	}

	hash := sha256.Sum256(message)
	r := new(big.Int).SetBytes(rBytes)
	s := new(big.Int).SetBytes(sBytes)

	return ecdsa.Verify(publicKey, hash[:], r, s)
}

func main() {
	// Example usage
	message := []byte("Hello, World!")
	signingKeyRaw := []byte{ /* your signing key bytes here */ }
	verifyingKeyRaw := []byte{ /* your verifying key bytes here */ }

	r, s, err := signNIST256(message, signingKeyRaw)
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}

	isValid := verifyNIST256(message, r, s, verifyingKeyRaw)
	fmt.Println("Signature valid:", isValid)
}