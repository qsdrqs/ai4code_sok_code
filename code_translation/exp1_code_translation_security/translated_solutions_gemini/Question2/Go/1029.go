package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha512"
	"fmt"
	"log"
	"math/big"
)

/*
This Go program is a translation of the provided Python script that demonstrates
ECDSA (Elliptic Curve Digital Signature Algorithm) operations.

Key translation choices:
1.  Curve: The Python code uses `NIST384p`. The corresponding curve in Go's
    standard library is `elliptic.P384()`.
2.  Hashing: The Python `ecdsa` library automatically uses a hash function
    appropriate for the curve size (SHA-384 for P-384). In Go, we must
    explicitly hash the message using `sha512.Sum384()` before signing or
    verifying.
3.  Signature Format: The Python library returns a signature as a raw byte
    string concatenating the `r` and `s` values. Go's `ecdsa.Sign` returns `r`
    and `s` as two `*big.Int` values. This translation replicates the Python
    format by converting `r` and `s` to fixed-size byte slices (padded to the
    curve's byte size) and concatenating them. The verification function
    performs the reverse operation.
4.  Key Serialization:
    - `sk.to_string()` in Python returns the raw private scalar `d`. In Go, this
      is `privateKey.D.Bytes()`.
    - `vk.to_string()` in Python returns the uncompressed point `0x04 || X || Y`.
      In Go, `elliptic.Marshal(curve, X, Y)` produces this format.
*/

// EcdsaKey corresponds to the Python `ecdsa_key` function.
// It generates a new key pair, signs a message, verifies the signature,
// and returns the signature.
// Note: The original Python function had an unused 'key' parameter, which is omitted here.
func EcdsaKey(message []byte) ([]byte, error) {
	// Generate a new key pair
	sk, err := EcdsaGenkey()
	if err != nil {
		return nil, err
	}
	vk := &sk.PublicKey

	// Sign the message
	signature, err := EcdsaSign(sk, message)
	if err != nil {
		return nil, err
	}

	// Verify the signature and print the result, as in the Python version.
	// The Python version would raise an exception on failure, while this Go
	// version will print `false`.
	isValid := EcdsaVerifyKey(vk, signature, message)
	fmt.Printf("Internal verification in EcdsaKey: %t\n", isValid)

	return signature, nil
}

// EcdsaVerify corresponds to the Python `ecdsa_verify` function.
// It's a wrapper for EcdsaVerifyKey with a different parameter order.
func EcdsaVerify(signature []byte, message []byte, vk *ecdsa.PublicKey) bool {
	return EcdsaVerifyKey(vk, signature, message)
}

// EcdsaGenkey corresponds to the Python `ecdsa_genkey` function.
// It generates a new ECDSA private key for the P-384 curve.
// The public key can be accessed via `privateKey.PublicKey`.
func EcdsaGenkey() (*ecdsa.PrivateKey, error) {
	curve := elliptic.P384()
	privateKey, err := ecdsa.GenerateKey(curve, rand.Reader)
	if err != nil {
		return nil, fmt.Errorf("failed to generate key: %w", err)
	}
	return privateKey, nil
}

// EcdsaSign corresponds to the Python `ecdsa_sign` function.
// It signs a message with the given private key.
func EcdsaSign(sk *ecdsa.PrivateKey, message []byte) ([]byte, error) {
	// Hash the message using SHA-384
	hash := sha512.Sum384(message)

	// Sign the hash
	r, s, err := ecdsa.Sign(rand.Reader, sk, hash[:])
	if err != nil {
		return nil, fmt.Errorf("failed to sign message: %w", err)
	}

	// Serialize the signature by concatenating r and s,
	// each padded to the curve's byte size.
	curveBytes := sk.Curve.Params().BitSize / 8
	rBytes := make([]byte, curveBytes)
	sBytes := make([]byte, curveBytes)
	r.FillBytes(rBytes)
	s.FillBytes(sBytes)

	signature := append(rBytes, sBytes...)
	return signature, nil
}

// EcdsaVerifyKey corresponds to the Python `ecdsa_verify_key` function.
// It verifies the signature of a message using the public key.
func EcdsaVerifyKey(vk *ecdsa.PublicKey, signature []byte, message []byte) bool {
	// Hash the message using SHA-384
	hash := sha512.Sum384(message)

	// Deserialize the signature by splitting the byte slice into r and s.
	curveBytes := vk.Curve.Params().BitSize / 8
	if len(signature) != 2*curveBytes {
		fmt.Println("malformed signature")
		return false
	}
	r := new(big.Int).SetBytes(signature[:curveBytes])
	s := new(big.Int).SetBytes(signature[curveBytes:])

	// Verify the signature
	return ecdsa.Verify(vk, hash[:], r, s)
}

func main() {
	// The message to be signed
	message := []byte("This is a test message")

	// 1. Generate a new key pair
	sk, err := EcdsaGenkey()
	if err != nil {
		log.Fatalf("Failed to generate key pair: %v", err)
	}
	vk := &sk.PublicKey

	// 2. Print the raw key bytes, similar to Python's `to_string()`
	// sk.to_string() -> private scalar 'd'
	skBytes := sk.D.Bytes()
	fmt.Printf("Private key (raw scalar): %x\n", skBytes)

	// vk.to_string() -> uncompressed point 0x04 || X || Y
	vkBytes := elliptic.Marshal(vk.Curve, vk.X, vk.Y)
	fmt.Printf("Public key (uncompressed): %x\n", vkBytes)

	// 3. Sign the message
	signature, err := EcdsaSign(sk, message)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}
	fmt.Printf("Signature (r || s): %x\n", signature)

	// 4. Verify the signature
	isValid := EcdsaVerifyKey(vk, signature, message)
	fmt.Printf("Verification result: %t\n", isValid)

	// Example of a failed verification
	fmt.Println("\n--- Testing with wrong message ---")
	wrongMessage := []byte("This is the wrong message")
	isStillValid := EcdsaVerifyKey(vk, signature, wrongMessage)
	fmt.Printf("Verification with wrong message: %t\n", isStillValid)
}