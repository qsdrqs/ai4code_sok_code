package main

import (
	"crypto/sha256"
	"github.com/btcsuite/btcd/btcec"
)

// sign signs a message using a given ECDSA private key with the SECP256k1 curve.
// It returns the signature as a 64-byte slice (r || s) or an error if the operation fails.
func sign(message, privateKey []byte) ([]byte, error) {
	// Step 1: Hash the message using SHA-256
	hash := sha256.Sum256(message)

	// Step 2: Parse the private key from the byte slice
	privKey, err := btcec.PrivKeyFromBytes(privateKey)
	if err != nil {
		return nil, err
	}

	// Step 3: Sign the hash using the private key
	signature, err := privKey.Sign(hash[:])
	if err != nil {
		return nil, err
	}

	// Step 4: Serialize the signature into a 64-byte slice (r || s)
	signedMessage := signature.Serialize()

	// Step 5: Return the signed message
	return signedMessage, nil
}