package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"encoding/hex"
	"fmt"
	"io"
	"log"
)

// encrypt encrypts a plaintext message using a 32-byte key with AES-GCM.
// The nonce is prepended to the ciphertext.
func encrypt(plaintext string, key []byte) ([]byte, error) {
	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	// Create a new GCM (Galois/Counter Mode) cipher, which provides authenticated encryption.
	gcm, err := cipher.NewGCM(block)
	if err != nil {
		return nil, err
	}

	// Create a nonce (number used once). Its length is determined by the GCM implementation.
	// The nonce must be unique for each encryption with the same key.
	nonce := make([]byte, gcm.NonceSize())
	if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
		return nil, err
	}

	// Encrypt the data. The gcm.Seal function appends the result to the first argument.
	// We pass the nonce as the first argument to prepend it to the ciphertext.
	// This is crucial because the nonce is needed for decryption.
	ciphertext := gcm.Seal(nonce, nonce, []byte(plaintext), nil)
	return ciphertext, nil
}

// decrypt decrypts a ciphertext using a 32-byte key with AES-GCM.
// It assumes the nonce is prepended to the ciphertext.
func decrypt(ciphertext []byte, key []byte) (string, error) {
	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	// Create a new GCM cipher.
	gcm, err := cipher.NewGCM(block)
	if err != nil {
		return "", err
	}

	// Get the nonce size.
	nonceSize := gcm.NonceSize()
	if len(ciphertext) < nonceSize {
		return "", fmt.Errorf("ciphertext is too short")
	}

	// Separate the nonce from the actual ciphertext.
	nonce, actualCiphertext := ciphertext[:nonceSize], ciphertext[nonceSize:]

	// Decrypt the data. If the authentication tag (which is part of the ciphertext)
	// is invalid, gcm.Open will return an error.
	plaintext, err := gcm.Open(nil, nonce, actualCiphertext, nil)
	if err != nil {
		return "", err
	}

	return string(plaintext), nil
}

func main() {
	message := "the solutions are here somewhere"
	fmt.Println("Secret Message:", message)

	// Generate a new 32-byte key for AES-256.
	// In a real application, you must save and securely manage this key.
	key := make([]byte, 32)
	if _, err := io.ReadFull(rand.Reader, key); err != nil {
		log.Fatalf("Failed to generate key: %v", err)
	}

	// Encrypt the message.
	encMessage, err := encrypt(message, key)
	if err != nil {
		log.Fatalf("Failed to encrypt: %v", err)
	}
	// We print the encrypted message as a hex string because it's raw bytes.
	fmt.Println("Encrypted Message (Hex):", hex.EncodeToString(encMessage))

	// Decrypt the message.
	decMessage, err := decrypt(encMessage, key)
	if err != nil {
		log.Fatalf("Failed to decrypt: %v", err)
	}
	fmt.Println("Decrypted Message:", decMessage)
}