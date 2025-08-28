package main

import (
	"crypto/aes"
	"errors"
	"fmt"
	"log"
)

// EncryptBytesAES encrypts data using AES in ECB mode.
// Note: The input data length must be a multiple of the AES block size (16 bytes).
// The key must be 16, 24, or 32 bytes long to select AES-128, AES-192, or AES-256.
func EncryptBytesAES(data, key []byte) ([]byte, error) {
	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	// Check if the data length is a multiple of the block size.
	// PyCryptodome's ECB mode also requires this.
	if len(data)%aes.BlockSize != 0 {
		return nil, errors.New("input data must be a multiple of the block size")
	}

	// Create a buffer for the encrypted data.
	encrypted := make([]byte, len(data))
	
	// Encrypt block by block.
	for i := 0; i < len(data); i += aes.BlockSize {
		block.Encrypt(encrypted[i:i+aes.BlockSize], data[i:i+aes.BlockSize])
	}

	return encrypted, nil
}

// DecryptBytesAES decrypts data using AES in ECB mode.
// Note: The input data length must be a multiple of the AES block size (16 bytes).
// The key must be 16, 24, or 32 bytes long to select AES-128, AES-192, or AES-256.
func DecryptBytesAES(data, key []byte) ([]byte, error) {
	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	// Check if the data length is a multiple of the block size.
	if len(data)%aes.BlockSize != 0 {
		return nil, errors.New("input data must be a multiple of the block size")
	}

	// Create a buffer for the decrypted data.
	decrypted := make([]byte, len(data))

	// Decrypt block by block.
	for i := 0; i < len(data); i += aes.BlockSize {
		block.Decrypt(decrypted[i:i+aes.BlockSize], data[i:i+aes.BlockSize])
	}

	return decrypted, nil
}

// Example usage
func main() {
	// The key must be 16 bytes (AES-128), 24 bytes (AES-192), or 32 bytes (AES-256).
	key := []byte("a very secret key 12345678901234") // 32 bytes for AES-256

	// The data to be encrypted. Its length MUST be a multiple of 16 bytes (the AES block size).
	// Here we use a 32-byte message.
	plaintext := []byte("This is a secret message!!_12345")

	fmt.Printf("Original Data:  %s\n", plaintext)
	fmt.Printf("Key:            %s\n", key)
	fmt.Println("------------------------------------")

	// Encrypt the data
	encrypted, err := EncryptBytesAES(plaintext, key)
	if err != nil {
		log.Fatalf("Encryption failed: %v", err)
	}
	fmt.Printf("Encrypted (Hex): %x\n", encrypted)

	// Decrypt the data
	decrypted, err := DecryptBytesAES(encrypted, key)
	if err != nil {
		log.Fatalf("Decryption failed: %v", err)
	}
	fmt.Printf("Decrypted Data: %s\n", decrypted)

	// Verify
	if string(plaintext) == string(decrypted) {
		fmt.Println("\nSuccess: Original and decrypted data match.")
	} else {
		fmt.Println("\nFailure: Data does not match.")
	}
}