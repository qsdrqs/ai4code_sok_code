package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"encoding/asn1"
	"math/big"
)

// sign_nist256 signs a message using the NIST P-256 curve and returns a 64-byte signature.
// The signature is the concatenation of the 32-byte big-endian r and s values.
func sign_nist256(message []byte, signingKeyRaw []byte) []byte {
	if len(signingKeyRaw) != 32 {
		return nil // Invalid private key length
	}

	curve := elliptic.P256()
	d := new(big.Int).SetBytes(signingKeyRaw)
	if d.Sign() == 0 {
		return nil // Invalid private key (zero)
	}

	// Compute public key from private key
	xBytes, yBytes := curve.ScalarBaseMult(signingKeyRaw)
	pubKey := ecdsa.PublicKey{
		Curve: curve,
		X:     new(big.Int).SetBytes(xBytes),
		Y:     new(big.Int).SetBytes(yBytes),
	}

	// Construct the private key
	privKey := &ecdsa.PrivateKey{
		PublicKey: pubKey,
		D:         d,
	}

	// Compute SHA-256 digest of the message
	digest := sha256.Sum256(message)

	// Sign the digest using ECDSA (DER-encoded signature)
	derSig, err := ecdsa.Sign(rand.Reader, privKey, digest[:])
	if err != nil {
		return nil
	}

	// Parse DER-encoded signature into r and s
	var parsedSig struct {
		R, S *big.Int
	}
	if _, err := asn1.Unmarshal(derSig, &parsedSig); err != nil {
		return nil
	}

	// Encode r and s as 32-byte big-endian values and concatenate
	signature := make([]byte, 64)
	copy(signature[0:32], pad32(parsedSig.R))
	copy(signature[32:64], pad32(parsedSig.S))

	return signature
}

// pad32 ensures a big.Int is encoded as a 32-byte big-endian slice.
// If the value is less than 32 bytes, it is left-padded with zeros.
// If it's longer, only the last 32 bytes are used.
func pad32(b *big.Int) []byte {
	buf := make([]byte, 32)
	if b == nil {
		return buf
	}
	bytes := b.Bytes()
	if len(bytes) > 32 {
		copy(buf, bytes[len(bytes)-32:])
	} else {
		copy(buf[32-len(bytes):], bytes)
	}
	return buf
}

// verify_nist256 verifies a 64-byte signature using the NIST P-256 curve.
// Returns true if the signature is valid, false otherwise.
func verify_nist256(message []byte, signature []byte, verifyingKeyRaw []byte) bool {
	if len(signature) != 64 || len(verifyingKeyRaw) != 64 {
		return false // Invalid input lengths
	}

	curve := elliptic.P256()
	x := new(big.Int).SetBytes(verifyingKeyRaw[:32])
	y := new(big.Int).SetBytes(verifyingKeyRaw[32:])

	// Construct the public key
	pubKey := &ecdsa.PublicKey{
		Curve: curve,
		X:     x,
		Y:     y,
	}

	// Check if the public key is valid (on the curve)
	if !pubKey.Curve.IsOnCurve(pubKey.X, pubKey.Y) {
		return false
	}

	// Extract r and s from the signature
	r := new(big.Int).SetBytes(signature[:32])
	s := new(big.Int).SetBytes(signature[32:])

	// Compute SHA-256 digest of the message
	digest := sha256.Sum256(message)

	// Verify the signature
	return pubKey.Verify(digest[:], r, s)
}