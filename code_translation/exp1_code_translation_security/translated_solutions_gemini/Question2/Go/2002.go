package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"fmt"
	"log"
	"math/big"
)

// Corresponds to the Python function:
// def sign(sk: SigningKey, m: str):
//   return sk.sign(m.encode('utf-8'))

// sign generates an ECDSA signature for a given message string.
// It first hashes the message with SHA-256 (note: python-ecdsa defaults to SHA-1),
// then signs the hash. The signature is returned as a single byte slice by
// concatenating the r and s values, matching the default python-ecdsa behavior.
func sign(sk *ecdsa.PrivateKey, m string) ([]byte, error) {
	// The message must be hashed before signing.
	digest := sha256.Sum256([]byte(m))

	// Sign the hash of the message.
	r, s, err := ecdsa.Sign(rand.Reader, sk, digest[:])
	if err != nil {
		return nil, err
	}

	// Encode the signature by concatenating r and s.
	// We use FillBytes to ensure r and s are padded to the curve's byte size.
	params := sk.Curve.Params()
	curveByteSize := params.BitSize / 8

	signature := make([]byte, curveByteSize*2)
	r.FillBytes(signature[:curveByteSize])
	s.FillBytes(signature[curveByteSize:])

	return signature, nil
}

// verify is a helper function to check an ECDSA signature against a message.
// It corresponds to the `vk.verify()` method in the Python example.
// It returns true if the signature is valid, and false otherwise.
func verify(vk *ecdsa.PublicKey, signature []byte, m string) bool {
	// Hash the message to be verified.
	digest := sha256.Sum256([]byte(m))

	// Unpack the signature from the byte slice into r and s.
	params := vk.Curve.Params()
	curveByteSize := params.BitSize / 8
	if len(signature) != curveByteSize*2 {
		fmt.Println("Error: signature of incorrect length")
		return false
	}

	r := new(big.Int).SetBytes(signature[:curveByteSize])
	s := new(big.Int).SetBytes(signature[curveByteSize:])

	// Verify the signature.
	return ecdsa.Verify(vk, digest[:], r, s)
}

// Corresponds to the Python function:
// def test():
//   sk = SigningKey.generate()
//   vk = sk.verifying_key
//   signature = sign(sk, "hello world")
//   print(vk.verify(signature, "hello worfld".encode('utf-8')))

// test demonstrates the signing and verification process.
func test() {
	// 1. Generate a new private key.
	// Note: The Python ecdsa library defaults to the SECP256k1 curve.
	// Go's standard library does not include SECP256k1. We use P-256 instead,
	// which is a common and secure alternative.
	sk, err := ecdsa.GenerateKey(elliptic.P256(), rand.Reader)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// 2. Derive the public key from the private key.
	vk := &sk.PublicKey

	// 3. Sign a message.
	message := "hello world"
	signature, err := sign(sk, message)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// 4. Verify the signature against a different message to show failure.
	// The Python code uses "hello worfld" (with a typo) to show that
	// verification will fail. In Python, this raises a BadSignatureError.
	// In Go, our verify function will simply return false.
	invalidMessage := "hello worfld"
	isValid := verify(vk, signature, invalidMessage)

	fmt.Printf("Attempting to verify signature with an INCORRECT message ('%s')...\n", invalidMessage)
	fmt.Printf("Verification result: %t\n\n", isValid) // Expected: false

	// For completeness, let's also verify against the correct message.
	correctMessage := "hello world"
	isValid = verify(vk, signature, correctMessage)

	fmt.Printf("Attempting to verify signature with the CORRECT message ('%s')...\n", correctMessage)
	fmt.Printf("Verification result: %t\n", isValid) // Expected: true
}

func main() {
	test()
}