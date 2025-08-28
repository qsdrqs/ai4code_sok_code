package main

import (
	"crypto/sha256"
	"encoding/hex"
	"fmt"
	"log"

	"github.com/decred/dcrd/dcrec/secp256k1/v4"
)

// signMessage hashes the message and returns a DER–encoded signature as hex.
func signMessage(message string, privateKey []byte) (string, error) {
	// 1. Hash the message (SHA-256)
	hash := sha256.Sum256([]byte(message))

	// 2. Construct a signing key from the raw private-key bytes.
	privKey := secp256k1.PrivKeyFromBytes(privateKey)

	// 3. Sign the hash.
	sig := privKey.Sign(hash[:])

	// 4. DER-encode the signature and convert it to hex.
	der := sig.Serialize()
	return hex.EncodeToString(der), nil
}

func main() {
	// Same private key (hex) as in the Python example.
	keyHex := "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109"

	// Decode the hex string to raw bytes.
	keyBytes, err := hex.DecodeString(keyHex)
	if err != nil {
		log.Fatalf("invalid private key: %v", err)
	}

	message := "Hello"

	// Sign and print the result.
	sigHex, err := signMessage(message, keyBytes)
	if err != nil {
		log.Fatalf("could not sign message: %v", err)
	}

	fmt.Println(sigHex)
}