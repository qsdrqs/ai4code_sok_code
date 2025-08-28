package main

import (
	"crypto/rand"
	"encoding/base64"
	"fmt"
	"log"

	"golang.org/x/crypto/chacha20poly1305"
)

var key []byte

func init() {
	// Generate a random 32-byte key for ChaCha20-Poly1305
	key = make([]byte, chacha20poly1305.KeySize)
	_, err := rand.Read(key)
	if err != nil {
		log.Fatalf("Failed to generate key: %v", err)
	}
}

func encryptText(plainText string) (string, error) {
	aead, err := chacha20poly1305.New(key)
	if err != nil {
		return "", fmt.Errorf("failed to create AEAD: %v", err)
	}

	nonce := make([]byte, chacha20poly1305.NonceSize)
	_, err = rand.Read(nonce)
	if err != nil {
		return "", fmt.Errorf("failed to generate nonce: %v", err)
	}

	encrypted := aead.Seal(nil, nonce, []byte(plainText), nil)
	// Combine nonce and encrypted text, then encode in base64
	result := append(nonce, encrypted...)
	return base64.StdEncoding.EncodeToString(result), nil
}

func decryptText(encryptedText string) (string, error) {
	aead, err := chacha20poly1305.New(key)
	if err != nil {
		return "", fmt.Errorf("failed to create AEAD: %v", err)
	}

	data, err := base64.StdEncoding.DecodeString(encryptedText)
	if err != nil {
		return "", fmt.Errorf("failed to decode base64: %v", err)
	}

	if len(data) < chacha20poly1305.NonceSize {
		return "", fmt.Errorf("invalid encrypted text")
	}

	nonce, ciphertext := data[:chacha20poly1305.NonceSize], data[chacha20poly1305.NonceSize:]
	plainText, err := aead.Open(nil, nonce, ciphertext, nil)
	if err != nil {
		return "", fmt.Errorf("failed to decrypt: %v", err)
	}

	return string(plainText), nil
}

func main() {
	plainText := "Hello, World!"

	// Encrypt the text
	encryptedText, err := encryptText(plainText)
	if err != nil {
		log.Fatalf("Encryption failed: %v", err)
	}
	fmt.Println("Encrypted Text:", encryptedText)

	// Decrypt the text
	decryptedText, err := decryptText(encryptedText)
	if err != nil {
		log.Fatalf("Decryption failed: %v", err)
	}
	fmt.Println("Decrypted Text:", decryptedText)
}