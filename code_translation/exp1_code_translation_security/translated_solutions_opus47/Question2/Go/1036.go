package main

import (
	"crypto/sha256"
	"encoding/hex"
	"fmt"

	"github.com/btcsuite/btcd/btcec/v2"
	"github.com/btcsuite/btcd/btcec/v2/ecdsa"
)

func signMessage(message string, privateKey []byte) (string, error) {
	// Hash the message
	messageHash := sha256.Sum256([]byte(message))

	// Load the private key for SECP256k1 curve
	signingKey, _ := btcec.PrivKeyFromBytes(privateKey)

	// Sign the message (DER-encoded signature)
	signature := ecdsa.Sign(signingKey, messageHash[:])

	// Serialize to DER and hex-encode
	return hex.EncodeToString(signature.Serialize()), nil
}

func main() {
	// Test the function
	privateKey, err := hex.DecodeString("e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109")
	if err != nil {
		fmt.Println("Error decoding private key:", err)
		return
	}

	message := "Hello"
	signature, err := signMessage(message, privateKey)
	if err != nil {
		fmt.Println("Error signing message:", err)
		return
	}
	fmt.Println(signature)
}