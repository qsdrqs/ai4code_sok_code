package main

import (
	"crypto/rand"
	"fmt"
	"log"

	"github.com/gtank/cryptopasta"
)

// encodeStr encrypts the plain text using the provided key.
func encodeStr(plainText string, key *[32]byte) (string, error) {
	ciphertext, err := cryptopasta.Encrypt([]byte(plainText), key)
	if err != nil {
		return "", err
	}
	return string(ciphertext), nil
}

// decodeStr decrypts the encoded string using the provided key.
func decodeStr(encodedStr string, key *[32]byte) (string, error) {
	plaintext, err := cryptopasta.Decrypt([]byte(encodedStr), key)
	if err != nil {
		return "", err
	}
	return string(plaintext), nil
}

func main() {
	// Generate a random key for encryption/decryption
	var key [32]byte
	if _, err := rand.Read(key[:]); err != nil {
		log.Fatalf("Failed to generate key: %v", err)
	}

	// Encode and decode the string
	strEncoded, err := encodeStr("I am okay", &key)
	if err != nil {
		log.Fatalf("Failed to encode string: %v", err)
	}

	strDecoded, err := decodeStr(strEncoded, &key)
	if err != nil {
		log.Fatalf("Failed to decode string: %v", err)
	}

	fmt.Println(strDecoded)
}