package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"fmt"
	"io"
)

// Function to encrypt a string using a symmetric key
func encrypt(key []byte, data string) ([]byte, []byte, error) {
	// Convert the data to bytes
	dataBytes := []byte(data)
	
	// Create a cipher block using the key
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, nil, err
	}
	
	// Create GCM mode (equivalent to EAX mode functionality)
	gcm, err := cipher.NewGCM(block)
	if err != nil {
		return nil, nil, err
	}
	
	// Generate a random nonce
	nonce := make([]byte, gcm.NonceSize())
	if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
		return nil, nil, err
	}
	
	// Encrypt the data
	ciphertext := gcm.Seal(nil, nonce, dataBytes, nil)
	
	// Return the ciphertext and the nonce
	return ciphertext, nonce, nil
}

// Function to decrypt a string
func decrypt(key []byte, nonce []byte, ciphertext []byte) ([]byte, error) {
	// Create a cipher block using the key
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}
	
	// Create GCM mode
	gcm, err := cipher.NewGCM(block)
	if err != nil {
		return nil, err
	}
	
	// Decrypt the data
	data, err := gcm.Open(nil, nonce, ciphertext, nil)
	if err != nil {
		return nil, err
	}
	
	// Return the decrypted data
	return data, nil
}

// Function to generate a symmetric key
func generateKey() ([]byte, error) {
	// Generate a random key (16 bytes for AES-128)
	key := make([]byte, 16)
	if _, err := io.ReadFull(rand.Reader, key); err != nil {
		return nil, err
	}
	return key, nil
}

func main() {
	// Example usage
	key, err := generateKey()
	if err != nil {
		fmt.Printf("Error generating key: %v\n", err)
		return
	}
	
	testData := "Jim's test"
	encrypted, nonce, err := encrypt(key, testData)
	if err != nil {
		fmt.Printf("Error encrypting: %v\n", err)
		return
	}
	
	restoredData, err := decrypt(key, nonce, encrypted)
	if err != nil {
		fmt.Printf("Error decrypting: %v\n", err)
		return
	}
	
	fmt.Printf("%s\n", string(restoredData))
}