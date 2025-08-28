package main

import (
	"crypto/rand"
	"fmt"
	"log"
	"math/big"

	// Go equivalent for secp256k1 operations, widely used in the Go ecosystem.
	"github.com/ethereum/go-ethereum/crypto/secp256k1"
	// Go equivalent for SHA3 hashing.
	"golang.org/x/crypto/sha3"
)

// sha3_256Hash computes the SHA3-256 hash of a message string.
// In Go, cryptographic functions typically operate on byte slices ([]byte) rather than large integers.
// This function returns the hash as a []byte, which is the standard input for signing functions.
func sha3_256Hash(msg string) []byte {
	hasher := sha3.New256()
	hasher.Write([]byte(msg))
	return hasher.Sum(nil)
}

// signECDSAsecp256k1 signs a message using a private key on the secp256k1 curve.
// It mimics the Python version by taking a message and a private key (*big.Int)
// and returning the signature components r and s as *big.Int.
func signECDSAsecp256k1(msg string, privKey *big.Int) (r, s *big.Int, err error) {
	// 1. Hash the message
	hash := sha3_256Hash(msg)

	// 2. Sign the hash
	// The go-ethereum library's Sign function requires the private key as a byte slice.
	privKeyBytes := privKey.Bytes()
	sig, err := secp256k1.Sign(hash, privKeyBytes)
	if err != nil {
		return nil, nil, err
	}

	// 3. Extract r and s from the signature
	// The returned signature is a 65-byte slice in [R || S || V] format.
	// R and S are the first two 32-byte components.
	r = new(big.Int).SetBytes(sig[:32])
	s = new(big.Int).SetBytes(sig[32:64])

	return r, s, nil
}

// verifyECDSAsecp256k1 verifies an ECDSA signature.
// It takes the message, signature components (r, s), and the public key.
// The public key is expected in uncompressed format (65 bytes).
func verifyECDSAsecp256k1(msg string, r, s *big.Int, pubKey []byte) bool {
	// 1. Hash the message
	hash := sha3_256Hash(msg)

	// 2. Reconstruct the 64-byte signature from r and s.
	// We must pad r and s to 32 bytes to ensure the signature is the correct length.
	signature := make([]byte, 64)
	r.FillBytes(signature[0:32])
	s.FillBytes(signature[32:64])

	// 3. Verify the signature against the hash and public key.
	// The VerifySignature function expects the signature in [R || S] format.
	return secp256k1.VerifySignature(pubKey, hash, signature)
}

func main() {
	// ECDSA sign message (using the curve secp256k1 + SHA3-256)
	msg := "Message for ECDSA signing"

	// 1. Generate a private key
	// The private key must be a cryptographically secure random integer
	// less than the order of the curve (N).
	curve := secp256k1.S256()
	privKey, err := rand.Int(rand.Reader, curve.N)
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// 2. Sign the message with the private key
	r, s, err := signECDSAsecp256k1(msg, privKey)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// Print the results in the same format as the Python script
	fmt.Println("Message:", msg)
	fmt.Printf("Private key: 0x%x\n", privKey)
	fmt.Printf("Signature: r=0x%x, s=0x%x\n", r, s)

	fmt.Println("\n--- Verification ---")

	// 3. Derive the public key from the private key
	// The public key is a point (X, Y) on the curve.
	pubKeyX, pubKeyY := curve.ScalarBaseMult(privKey.Bytes())

	// Serialize the public key in uncompressed format (0x04 + X + Y).
	// This 65-byte format is standard for passing public keys.
	pubKeyBytes := curve.Marshal(pubKeyX, pubKeyY)
	fmt.Printf("Derived Public Key: 0x%x\n", pubKeyBytes)

	// 4. Verify the signature
	isValid := verifyECDSAsecp256k1(msg, r, s, pubKeyBytes)
	fmt.Printf("Signature valid? %t\n", isValid)
}