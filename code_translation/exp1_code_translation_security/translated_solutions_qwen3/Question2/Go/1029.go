package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha1"
	"encoding/asn1"
	"fmt"
	"math/big"
)

// ecdsaGenKey generates a new ECDSA key pair using the P-384 curve.
func ecdsaGenKey() (*ecdsa.PrivateKey, *ecdsa.PublicKey) {
	sk, _ := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	return sk, &sk.PublicKey
}

// publicKeyToBytes converts a public key to its byte representation (uncompressed format).
func publicKeyToBytes(vk *ecdsa.PublicKey) []byte {
	return elliptic.Marshal(vk.Curve, vk.X, vk.Y)
}

// privateKeyToBytes converts a private key to its byte representation, padded to the curve's order size.
func privateKeyToBytes(sk *ecdsa.PrivateKey) []byte {
	params := sk.Curve.Params()
	orderSize := (params.BitSize + 7) / 8
	bytes := sk.D.Bytes()
	if len(bytes) == orderSize {
		return bytes
	}
	padded := make([]byte, orderSize)
	copy(padded[orderSize-len(bytes):], bytes)
	return padded
}

// ecdsaSign signs a message using the private key and returns the DER-encoded signature.
func ecdsaSign(sk *ecdsa.PrivateKey, message []byte) []byte {
	// Hash the message using SHA-1
	h := sha1.New()
	h.Write(message)
	digest := h.Sum(nil)

	// Sign the hash
	r, s, _ := ecdsa.Sign(rand.Reader, sk, digest)

	// Marshal the signature into DER format
	type sigStruct struct {
		R, S *big.Int
	}
	sig, _ := asn1.Marshal(sigStruct{R: r, S: s})
	return sig
}

// ecdsaVerifyKey verifies a DER-encoded signature using the public key and message.
func ecdsaVerifyKey(vk *ecdsa.PublicKey, signature []byte, message []byte) bool {
	// Hash the message using SHA-1
	h := sha1.New()
	h.Write(message)
	digest := h.Sum(nil)

	// Unmarshal the DER-encoded signature
	var sigStruct struct {
		R, S *big.Int
	}
	if _, err := asn1.Unmarshal(signature, &sigStruct); err != nil {
		return false
	}

	// Verify the signature
	return ecdsa.Verify(vk, digest, sigStruct.R, sigStruct.S)
}

func main() {
	message := []byte("This is a test message")

	// Generate key pair
	sk, vk := ecdsaGenKey()

	// Convert keys to bytes
	skBytes := privateKeyToBytes(sk)
	vkBytes := publicKeyToBytes(vk)

	// Print keys
	fmt.Println("Private key:", skBytes)
	fmt.Println("Public key:", vkBytes)

	// Sign the message
	signature := ecdsaSign(sk, message)
	fmt.Println("Signature:", signature)

	// Verify the signature
	verified := ecdsaVerifyKey(vk, signature, message)
	fmt.Println("Verified:", verified)
}