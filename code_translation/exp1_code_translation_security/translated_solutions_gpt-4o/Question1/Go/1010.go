package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"fmt"
	"io"
	"log"
)

// Function to encrypt a string using a symmetric key
func encrypt(key []byte, data string) ([]byte, []byte, error) {
	// Convert the data to bytes
	dataBytes := []byte(data)

	// Create a cipher block
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, nil, err
	}

	// Create a new GCM cipher mode
	aesGCM, err := cipher.NewGCM(block)
	if err != nil {
		return nil, nil, err
	}

	// Generate a nonce
	nonce := make([]byte, aesGCM.NonceSize())
	if _, err = io.ReadFull(rand.Reader, nonce); err != nil {
		return nil, nil, err
	}

	// Encrypt the data
	ciphertext := aesGCM.Seal(nil, nonce, dataBytes, nil)

	// Return the ciphertext and the nonce
	return ciphertext, nonce, nil
}

// Function to decrypt a string
func decrypt(key []byte, nonce, ciphertext []byte) (string, error) {
	// Create a cipher block
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	// Create a new GCM cipher mode
	aesGCM, err := cipher.NewGCM(block)
	if err != nil {
		return "", err
	}

	// Decrypt the data
	dataBytes, err := aesGCM.Open(nil, nonce, ciphertext, nil)
	if err != nil {
		return "", err
	}

	// Return the decrypted data as a string
	return string(dataBytes), nil
}

// Function to generate a symmetric key
func generateKey() ([]byte, error) {
	// Generate a random key
	key := make([]byte, 16)
	if _, err := rand.Read(key); err != nil {
		return nil, err
	}

	return key, nil
}

func main() {
	// Example
	key, err := generateKey()
	if err != nil {
		log.Fatal(err)
	}

	testData := "Jim's test"
	encrypted, nonce, err := encrypt(key, testData)
	if err != nil {
		log.Fatal(err)
	}

	restoredData, err := decrypt(key, nonce, encrypted)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println(restoredData)
}