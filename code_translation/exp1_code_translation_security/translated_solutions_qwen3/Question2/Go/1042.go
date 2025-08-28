package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha1"
	"math/big"
	"errors"
)

// signMessage signs a given message using ECDSA with the SECP256k1 curve.
// It returns the DER-encoded signature or an error if the operation fails.
func signMessage(message []byte, privateKey []byte) ([]byte, error) {
	// Use the SECP256k1 curve
	curve := elliptic.Secp256k1()

	// Ensure the private key is exactly 32 bytes (256 bits)
	if len(privateKey) != 32 {
		return nil, errors.New("private key must be 32 bytes")
	}

	// Convert the private key bytes to a big.Int
	d := new(big.Int).SetBytes(privateKey)

	// Ensure the private key is within the valid range [1, n-1]
	n := curve.Params().N
	if d.Cmp(big.NewInt(1)) < 0 || d.Cmp(n) >= 0 {
		return nil, errors.New("invalid private key value")
	}

	// Derive the public key from the private key
	x, y := curve.ScalarBaseMult(privateKey)

	// Construct the public and private key structures
	pub := &ecdsa.PublicKey{
		Curve: curve,
		X:     x,
		Y:     y,
	}
	priv := &ecdsa.PrivateKey{
		PublicKey: *pub,
		D:         d,
	}

	// Hash the message using SHA-1 (as in Python's ecdsa.SigningKey.sign)
	hashed := sha1.Sum(message)

	// Sign the hash using the private key
	r, s, err := ecdsa.Sign(rand.Reader, priv, hashed[:])
	if err != nil {
		return nil, err
	}

	// Encode the signature in DER format
	signature := ecdsa.EncodeSignature(r, s)

	return signature, nil
}