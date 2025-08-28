package main

import (
	"github.com/fernet/fernet-go"
	"encoding/base64"
)

// Encrypt encrypts a message using Fernet encryption.
// Returns the encrypted message as a byte slice and an error if any.
func Encrypt(message string, keyStr string) ([]byte, error) {
	// Decode the base64-encoded key string into a 32-byte key
	keyBytes, err := base64.URLEncoding.DecodeString(keyStr)
	if err != nil {
		return nil, err
	}

	// Ensure the key is exactly 32 bytes
	if len(keyBytes) != 32 {
		return nil, fernet.ErrInvalidKey
	}

	// Create a Fernet key
	var key [32]byte
	copy(key[:], keyBytes)

	// Encrypt the message
	token := fernet.Encrypt([]byte(message), &key)

	// Return the encrypted token as a byte slice
	return []byte(token.String()), nil
}

// Decrypt decrypts a Fernet-encrypted message.
// Returns the decrypted message as a byte slice and an error if any.
func Decrypt(message []byte, keyStr string) ([]byte, error) {
	// Decode the base64-encoded key string into a 32-byte key
	keyBytes, err := base64.URLEncoding.DecodeString(keyStr)
	if err != nil {
		return nil, err
	}

	// Ensure the key is exactly 32 bytes
	if len(keyBytes) != 32 {
		return nil, fernet.ErrInvalidKey
	}

	// Create a Fernet key
	var key [32]byte
	copy(key[:], keyBytes)

	// Decode the token from the encrypted message
	token, err := fernet.DecodeToken(string(message))
	if err != nil {
		return nil, err
	}

	// Decrypt the token
	plaintext, err := token.Decrypt(&key, 0) // 0 means no TTL check
	if err != nil {
		return nil, err
	}

	return plaintext, nil
}