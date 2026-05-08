package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha512"
	"fmt"
	"math/big"
)

// hashMessage hashes a message using SHA-384 (appropriate for P-384 curve)
func hashMessage(message []byte) []byte {
	hash := sha512.Sum384(message)
	return hash[:]
}

// signMessage creates an ECDSA signature in r||s format (matches python-ecdsa default)
func signMessage(sk *ecdsa.PrivateKey, message []byte) ([]byte, error) {
	hash := hashMessage(message)
	r, s, err := ecdsa.Sign(rand.Reader, sk, hash)
	if err != nil {
		return nil, err
	}
	curveBytes := (sk.Curve.Params().BitSize + 7) / 8
	signature := make([]byte, 2*curveBytes)
	r.FillBytes(signature[:curveBytes])
	s.FillBytes(signature[curveBytes:])
	return signature, nil
}

// verifyMessage verifies a signature in r||s format
func verifyMessage(vk *ecdsa.PublicKey, signature []byte, message []byte) bool {
	hash := hashMessage(message)
	curveBytes := (vk.Curve.Params().BitSize + 7) / 8
	if len(signature) != 2*curveBytes {
		return false
	}
	r := new(big.Int).SetBytes(signature[:curveBytes])
	s := new(big.Int).SetBytes(signature[curveBytes:])
	return ecdsa.Verify(vk, hash, r, s)
}

// ecdsaKey generates a key pair, signs the message, verifies the signature, and returns it
func ecdsaKey(message []byte, key []byte) ([]byte, error) {
	_ = key // unused parameter, matching Python behavior
	sk, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		return nil, err
	}
	vk := &sk.PublicKey

	signature, err := signMessage(sk, message)
	if err != nil {
		return nil, err
	}

	valid := verifyMessage(vk, signature, message)
	fmt.Println(valid)
	return signature, nil
}

// ecdsaVerify verifies a signature against a message using the public key
func ecdsaVerify(signature []byte, message []byte, vk *ecdsa.PublicKey) bool {
	return verifyMessage(vk, signature, message)
}

// ecdsaGenkey generates a new ECDSA key pair (P-384)
func ecdsaGenkey() (*ecdsa.PrivateKey, *ecdsa.PublicKey, error) {
	sk, err := ecdsa.GenerateKey(elliptic.P384(), rand.Reader)
	if err != nil {
		return nil, nil, err
	}
	vk := &sk.PublicKey
	return sk, vk, nil
}

// ecdsaSign signs a message with the private key
func ecdsaSign(sk *ecdsa.PrivateKey, message []byte) ([]byte, error) {
	return signMessage(sk, message)
}

// ecdsaVerifyKey verifies a signature using the verifying key
func ecdsaVerifyKey(vk *ecdsa.PublicKey, signature []byte, message []byte) bool {
	return verifyMessage(vk, signature, message)
}

// privateKeyToString returns the raw bytes of the private key (matches sk.to_string())
func privateKeyToString(sk *ecdsa.PrivateKey) []byte {
	curveBytes := (sk.Curve.Params().BitSize + 7) / 8
	result := make([]byte, curveBytes)
	sk.D.FillBytes(result)
	return result
}

// publicKeyToString returns X||Y concatenated (matches vk.to_string())
func publicKeyToString(vk *ecdsa.PublicKey) []byte {
	curveBytes := (vk.Curve.Params().BitSize + 7) / 8
	result := make([]byte, 2*curveBytes)
	vk.X.FillBytes(result[:curveBytes])
	vk.Y.FillBytes(result[curveBytes:])
	return result
}

func main() {
	message := []byte("This is a test message")
	sk, vk, err := ecdsaGenkey()
	if err != nil {
		fmt.Println("Error generating key:", err)
		return
	}

	fmt.Println(privateKeyToString(sk))
	fmt.Println(publicKeyToString(vk))

	signature, err := ecdsaSign(sk, message)
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}
	fmt.Println(signature)

	valid := ecdsaVerifyKey(vk, signature, message)
	fmt.Println(valid)
}