package main

import (
	"crypto/ecdsa"
	"crypto/sha256"
	"fmt"
	"log"

	// We use the crypto package from go-ethereum to get SECP256k1 support
	"github.comcom/ethereum/go-ethereum/crypto"
	"github.com/ethereum/go-ethereum/crypto/secp256k1"
)

// signAndVerifyMessage replicates the logic of the Python function.
// It takes a private key and a message, signs the message,
// and then verifies the signature using the corresponding public key.
func signAndVerifyMessage(privateKey *ecdsa.PrivateKey, message []byte) (bool, error) {
	// 1. Hash the message
	// Unlike the Python ecdsa library, Go's crypto functions require you to hash the message first.
	// We'll use SHA-256, a common choice.
	hash := sha256.Sum256(message)

	// 2. Sign the hash with the private key
	// The crypto.Sign function returns a signature in [R || S || V] format,
	// where V is the recovery ID.
	signature, err := crypto.Sign(hash[:], privateKey)
	if err != nil {
		return false, fmt.Errorf("failed to sign message: %w", err)
	}

	// 3. Verify the signature
	// To verify, we need the public key in bytes.
	publicKey := privateKey.PublicKey
	pubKeyBytes := crypto.FromECDSAPub(&publicKey)

	// The secp256k1.VerifySignature function expects a signature in [R || S] format.
	// The crypto.Sign function from go-ethereum includes a recovery ID 'V' at the end,
	// so we must slice it off before verifying.
	signatureNoRecoveryID := signature[:len(signature)-1]

	// Verify the signature against the original hash and public key.
	return secp256k1.VerifySignature(pubKeyBytes, hash[:], signatureNoRecoveryID), nil
}

func main() {
	// 1. Generate a new private key using the SECP256k1 curve
	// This is equivalent to: sk = ecdsa.SigningKey.generate(curve=ecdsa.SECP256k1)
	privateKey, err := crypto.GenerateKey()
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// The public key is derived directly from the private key.
	// This is equivalent to: vk = sk.get_verifying_key()
	// In Go, the public key is a field within the private key struct.

	// The message to be signed
	message := []byte("..")

	// Call the function to sign and verify
	isValid, err := signAndVerifyMessage(privateKey, message)
	if err != nil {
		log.Fatalf("Error during signing and verification: %v", err)
	}

	// Print the result (will be `true`)
	fmt.Println(isValid)
}