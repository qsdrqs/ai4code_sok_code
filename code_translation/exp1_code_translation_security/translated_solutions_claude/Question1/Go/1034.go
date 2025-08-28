package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"crypto/sha256"
	"encoding/base64"
	"fmt"
	"io"
)

// encodeStr encrypts plain text using AES encryption with the given key
func encodeStr(plainText, key string) (string, error) {
	// Create a SHA256 hash of the key to ensure it's the right length
	hasher := sha256.New()
	hasher.Write([]byte(key))
	keyHash := hasher.Sum(nil)

	// Create AES cipher
	block, err := aes.NewCipher(keyHash)
	if err != nil {
		return "", err
	}

	// Convert plain text to bytes
	plainBytes := []byte(plainText)

	// Create GCM mode
	gcm, err := cipher.NewGCM(block)
	if err != nil {
		return "", err
	}

	// Generate a random nonce
	nonce := make([]byte, gcm.NonceSize())
	if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
		return "", err
	}

	// Encrypt the data
	ciphertext := gcm.Seal(nonce, nonce, plainBytes, nil)

	// Encode to base64 for string representation
	encoded := base64.StdEncoding.EncodeToString(ciphertext)
	return encoded, nil
}

// decodeStr decrypts the encoded string using the given key
func decodeStr(strEncoded, key string) (string, error) {
	// Create a SHA256 hash of the key
	hasher := sha256.New()
	hasher.Write([]byte(key))
	keyHash := hasher.Sum(nil)

	// Create AES cipher
	block, err := aes.NewCipher(keyHash)
	if err != nil {
		return "", err
	}

	// Decode from base64
	ciphertext, err := base64.StdEncoding.DecodeString(strEncoded)
	if err != nil {
		return "", err
	}

	// Create GCM mode
	gcm, err := cipher.NewGCM(block)
	if err != nil {
		return "", err
	}

	// Extract nonce and encrypted data
	nonceSize := gcm.NonceSize()
	if len(ciphertext) < nonceSize {
		return "", fmt.Errorf("ciphertext too short")
	}

	nonce, ciphertext := ciphertext[:nonceSize], ciphertext[nonceSize:]

	// Decrypt the data
	plainBytes, err := gcm.Open(nil, nonce, ciphertext, nil)
	if err != nil {
		return "", err
	}

	return string(plainBytes), nil
}

func main() {
	strEncoded1, err := encodeStr("I am okay", "wow")
	if err != nil {
		fmt.Printf("Error encoding: %v\n", err)
		return
	}

	decoded, err := decodeStr(strEncoded1, "wow")
	if err != nil {
		fmt.Printf("Error decoding: %v\n", err)
		return
	}

	fmt.Println(decoded)
}