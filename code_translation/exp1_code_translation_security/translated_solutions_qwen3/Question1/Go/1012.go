package main

import (
	"crypto/rand"
	"encoding/base64"
	"time"

	"github.com/fernet/fernet-go"
)

// Global key variable used for encryption and decryption
var key fernet.Key

// init initializes the global key with a randomly generated Fernet-compatible key
func init() {
	// Generate 32 random bytes
	keyBytes := make([]byte, 32)
	_, err := rand.Read(keyBytes)
	if err != nil {
		panic("Failed to generate random key: " + err.Error())
	}

	// Encode the key as a base64 URL-safe string
	keyStr := base64.URLEncoding.EncodeToString(keyBytes)

	// Decode the string into a Fernet Key struct
	key = fernet.MustDecodeKey(keyStr)
}

// encryptText encrypts a plain text string using the Fernet key
func encryptText(plainText string) (string, error) {
	// Encrypt the plain text using the current time
	encrypted, err := fernet.Encrypt([]byte(plainText), key, time.Now())
	if err != nil {
		return "", err
	}
	// Convert the encrypted byte slice to a string
	return string(encrypted), nil
}

// decryptText decrypts an encrypted string using the Fernet key
func decryptText(encryptedText string) (string, error) {
	// Decrypt the encrypted text, ignoring TTL check
	message, err := fernet.Decrypt([]byte(encryptedText), []fernet.Key{key}, nil)
	if err != nil {
		return "", err
	}
	// Return the decrypted payload as a string
	return string(message.Payload), nil
}