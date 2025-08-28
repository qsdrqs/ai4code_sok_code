package main

import (
	"crypto/sha256"
	"encoding/hex"
	"fmt"
	"log"

	// The btcec library provides robust support for the SECP256k1 elliptic curve,
	// which is used in the original Python code.
	"github.com/btcsuite/btcd/btcec/v2"
	"github.com/btcsuite/btcd/btcec/v2/ecdsa"
)

// SignMessage signs a message using a private key with ECDSA over the SECP256k1 curve.
// It replicates the behavior of the Python code by hashing the message, signing the hash,
// and returning a DER-encoded signature as a hex string.
func SignMessage(message string, privateKeyBytes []byte) (string, error) {
	// 1. Hash the message using SHA-256.
	// This is equivalent to Python's `hashlib.sha256(message.encode('utf-8')).digest()`.
	messageHash := sha256.Sum256([]byte(message))

	// 2. Create a private key object from the raw bytes.
	// This is equivalent to `ecdsa.SigningKey.from_string(private_key, curve=ecdsa.SECP256k1)`.
	// The btcec library is specifically for the SECP256k1 curve.
	privateKey, _ := btcec.PrivKeyFromBytes(privateKeyBytes)

	// 3. Sign the message hash.
	// This is equivalent to `signing_key.sign_digest(message_hash, ...)`.
	// The btcec library uses RFC6979 for deterministic nonce generation, just like the Python library,
	// ensuring the same signature is produced for the same key and message.
	signature := ecdsa.Sign(privateKey, messageHash[:])

	// 4. Serialize the signature to the DER format.
	// This is equivalent to `sigencode=ecdsa.util.sigencode_der`.
	derSignature := signature.Serialize()

	// 5. Convert the DER-encoded signature to a hex string and return it.
	// This is equivalent to `binascii.hexlify(signature).decode('utf-8')`.
	return hex.EncodeToString(derSignature), nil
}

func main() {
	// Test the function
	// Equivalent to Python's `binascii.unhexlify(...)`
	privateKeyHex := "e331b6d69882b4cb4ea581d88e0b604039a3de5967688d3dcffdd2270c0fd109"
	privateKeyBytes, err := hex.DecodeString(privateKeyHex)
	if err != nil {
		log.Fatalf("Failed to decode private key from hex: %v", err)
	}

	message := "Hello"

	// Call the signing function
	signature, err := SignMessage(message, privateKeyBytes)
	if err != nil {
		log.Fatalf("Failed to sign message: %v", err)
	}

	// Print the resulting signature. The output will be identical to the Python script's output.
	fmt.Println(signature)
}