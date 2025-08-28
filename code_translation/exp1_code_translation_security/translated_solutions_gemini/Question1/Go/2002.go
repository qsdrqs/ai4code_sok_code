package main

import (
	"crypto/aes"
	"crypto/cipher"
	"fmt"
	"log"
)

// encrypt encrypts a message using AES-256 in CTR mode.
// The key must be 32 bytes long to match the 256-bit key from the example.
// It uses a zero-value IV to match the default behavior of pyaes.AESModeOfOperationCTR.
func encrypt(key []byte, message string) ([]byte, error) {
	plaintext := []byte(message)

	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	// For CTR mode, we need an Initialization Vector (IV). The IV's length
	// must be equal to the block size (16 bytes for AES).
	// A zero-initialized IV is used here to match the default pyaes counter.
	iv := make([]byte, aes.BlockSize)

	// Create a new CTR stream cipher.
	stream := cipher.NewCTR(block, iv)

	// Allocate a byte slice for the ciphertext.
	ciphertext := make([]byte, len(plaintext))

	// Perform the encryption.
	stream.XORKeyStream(ciphertext, plaintext)

	return ciphertext, nil
}

// decrypt decrypts a message using AES-256 in CTR mode.
// The key and IV must be the same as those used for encryption.
func decrypt(key []byte, ciphertext []byte) (string, error) {
	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	// The IV must be the same as the one used for encryption.
	iv := make([]byte, aes.BlockSize)

	// Create a new CTR stream cipher.
	stream := cipher.NewCTR(block, iv)

	// Allocate a byte slice for the plaintext.
	plaintext := make([]byte, len(ciphertext))

	// Perform the decryption. In CTR mode, decryption is the same as encryption.
	stream.XORKeyStream(plaintext, ciphertext)

	// Convert the decrypted byte slice back to a string.
	return string(plaintext), nil
}

// The main function serves as the entry point and replaces the Python test() function.
func main() {
	// This logic corresponds to the round_trip_test function in the Python code.
	
	// The key must be 32 bytes (256 bits) for AES-256.
	key := []byte("This_key_for_demo_purposes_only!")
	message := "Text may be any length you wish, no padding is required"

	// Encrypt the message.
	ciphertext, err := encrypt(key, message)
	if err != nil {
		log.Fatalf("Encryption failed: %v", err)
	}

	// Decrypt the message.
	decryptedMessage, err := decrypt(key, ciphertext)
	if err != nil {
		log.Fatalf("Decryption failed: %v", err)
	}

	// Print the final decrypted message, matching the Python script's output.
	fmt.Println(decryptedMessage)
}