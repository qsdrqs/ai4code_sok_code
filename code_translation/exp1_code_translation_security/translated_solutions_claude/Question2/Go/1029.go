package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"math/big"
)

// ECDSA key generation, signing and verification functions

func ecdsaKey(message []byte, key []byte) []byte {
	sk, vk := ecdsaGenkey()
	signature := ecdsaSign(sk, message)
	fmt.Println(ecdsaVerifyKey(vk, signature, message))
	return signature
}

func ecdsaVerify(signature []byte, message []byte, vk *ecdsa.PublicKey) bool {
	return ecdsaVerifyKey(vk, signature, message)
}

func ecdsaGenkey() (*ecdsa.PrivateKey, *ecdsa.PublicKey) {
	// Using P-384 curve (equivalent to NIST384p)
	sk, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		panic(err)
	}
	vk := &sk.PublicKey
	return sk, vk
}

func ecdsaSign(sk *ecdsa.PrivateKey, message []byte) []byte {
	// Hash the message using SHA-256
	hash := sha256.Sum256(message)
	
	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, sk, hash[:])
	if err != nil {
		panic(err)
	}
	
	// Combine r and s into a single byte slice
	// This is a simple concatenation - in practice you might want to use ASN.1 DER encoding
	rBytes := r.Bytes()
	sBytes := s.Bytes()
	
	// Pad to ensure consistent length (48 bytes each for P-384)
	rPadded := make([]byte, 48)
	sPadded := make([]byte, 48)
	copy(rPadded[48-len(rBytes):], rBytes)
	copy(sPadded[48-len(sBytes):], sBytes)
	
	signature := append(rPadded, sPadded...)
	return signature
}

func ecdsaVerifyKey(vk *ecdsa.PublicKey, signature []byte, message []byte) bool {
	// Hash the message using SHA-256
	hash := sha256.Sum256(message)
	
	// Extract r and s from signature
	if len(signature) != 96 { // 48 bytes each for r and s
		return false
	}
	
	r := new(big.Int).SetBytes(signature[:48])
	s := new(big.Int).SetBytes(signature[48:])
	
	// Verify the signature
	return ecdsa.Verify(vk, hash[:], r, s)
}

// Helper functions to convert keys to byte strings (similar to Python's to_string())
func privateKeyToString(sk *ecdsa.PrivateKey) []byte {
	return sk.D.Bytes()
}

func publicKeyToString(vk *ecdsa.PublicKey) []byte {
	// Uncompressed point format: 0x04 || x || y
	x := vk.X.Bytes()
	y := vk.Y.Bytes()
	
	// Pad to ensure consistent length (48 bytes each for P-384)
	xPadded := make([]byte, 48)
	yPadded := make([]byte, 48)
	copy(xPadded[48-len(x):], x)
	copy(yPadded[48-len(y):], y)
	
	result := []byte{0x04}
	result = append(result, xPadded...)
	result = append(result, yPadded...)
	return result
}

func main() {
	message := []byte("This is a test message")
	sk, vk := ecdsaGenkey()
	
	fmt.Printf("%x\n", privateKeyToString(sk))
	fmt.Printf("%x\n", publicKeyToString(vk))
	
	signature := ecdsaSign(sk, message)
	fmt.Printf("%x\n", signature)
	fmt.Println(ecdsaVerifyKey(vk, signature, message))
}