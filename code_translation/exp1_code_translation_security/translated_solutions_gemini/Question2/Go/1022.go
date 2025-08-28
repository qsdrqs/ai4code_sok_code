// To run this Go code, you first need to install the necessary dependency.
// Open your terminal and run the following command:
// go get github.com/ethereum/go-ethereum/crypto
//
// Then, save the code below as a file (e.g., main.go) and execute it with:
// go run main.go

package main

import (
	"crypto/sha256"
	"fmt"
	"log"

	// The go-ethereum/crypto library is used because it provides a convenient
	// implementation of the secp256k1 curve, which is the same curve used
	// by the Python 'ecdsa' library by default.
	"github.com/ethereum/go-ethereum/crypto"
)

func main() {
	// Generate Keys
	// This creates a new private key for the secp256k1 elliptic curve.
	privateKey, err := crypto.GenerateKey()
	if err != nil {
		log.Fatalf("Failed to generate private key: %v", err)
	}

	// The public key is derived from the private key.
	publicKey := &privateKey.PublicKey

	message := "My test message"
	messageBytes := []byte(message)

	// In Go's crypto libraries, you sign the hash of the message, not the
	// raw message data. The Python library handles this hashing step internally.
	// We use SHA-256, a standard and secure hashing algorithm.
	hash := sha256.Sum256(messageBytes)

	// Generate Signature
	// The crypto.Sign function creates a signature and returns it as a byte slice.
	// The format is [R || S || V], where R and S are the signature components,
	// and V is a recovery ID.
	signature, err := crypto.Sign(hash[:], privateKey)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// Verify if signature is valid
	// For verification, we need the public key as a byte slice.
	publicKeyBytes := crypto.FromECDSAPub(publicKey)

	// The crypto.VerifySignature function requires the signature in the [R || S] format.
	// We must slice off the last byte (the V recovery ID) from the generated signature.
	signatureWithoutRecoveryID := signature[:len(signature)-1]

	// The function returns true if the signature is valid for the given hash and public key.
	isValid := crypto.VerifySignature(publicKeyBytes, hash[:], signatureWithoutRecoveryID)

	// Print the verification result (will print "true").
	fmt.Println(isValid)
}