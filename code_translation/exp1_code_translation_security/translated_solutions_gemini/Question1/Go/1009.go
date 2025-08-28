package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"fmt"
	"log"

	// The EAX mode is not in the standard library, but is available in the
	// supplementary crypto repository.
	"golang.org/x/crypto/eax"
)

// encodeString encrypts a message using AES-EAX.
// It takes a string message, a key, and a nonce.
// It returns the ciphertext and the authentication tag, or an error.
// This is equivalent to the Python version's `encodeString` function.
func encodeString(message string, key []byte, nonce []byte) ([]byte, []byte, error) {
	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, nil, fmt.Errorf("could not create new cipher: %w", err)
	}

	// Create a new EAX instance.
	aead, err := eax.New(block)
	if err != nil {
		return nil, nil, fmt.Errorf("could not create EAX instance: %w", err)
	}

	// Convert the string message to a byte slice for encryption.
	mBytes := []byte(message)

	// Encrypt the message. The `Seal` function handles both encryption and
	// authentication, returning a single byte slice containing the
	// ciphertext followed by the authentication tag.
	encryptedData := aead.Seal(nil, nonce, mBytes, nil)

	// To match the Python function's return of [ciphertext, tag], we split
	// the combined result from `Seal`. The tag size is determined by aead.Overhead().
	tagSize := aead.Overhead()
	ciphertext := encryptedData[:len(encryptedData)-tagSize]
	tag := encryptedData[len(encryptedData)-tagSize:]

	return ciphertext, tag, nil
}

// decodeString decrypts a message using AES-EAX.
// In Go, it's more idiomatic to pass ciphertext and tag as separate arguments
// rather than a slice of slices like the Python version's list `p`.
// It returns the decrypted string, or an error if decryption or authentication fails.
func decodeString(ciphertext []byte, tag []byte, key []byte, nonce []byte) (string, error) {
	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", fmt.Errorf("could not create new cipher: %w", err)
	}

	// Create a new EAX instance.
	aead, err := eax.New(block)
	if err != nil {
		return "", fmt.Errorf("could not create EAX instance: %w", err)
	}

	// The `Open` function requires the ciphertext and tag to be in a single slice.
	// We concatenate them before passing them to `Open`.
	encryptedData := append(ciphertext, tag...)

	// Decrypt and verify the message. If the tag is invalid, `Open` will return an error.
	data, err := aead.Open(nil, nonce, encryptedData, nil)
	if err != nil {
		return "", fmt.Errorf("could not decrypt or verify data: %w", err)
	}

	// Decode the resulting plaintext from bytes to a string.
	return string(data), nil
}

func main() {
	originalMessage := "secret data"

	// Generate a random 16-byte key for AES-128.
	// This is the Go equivalent of `get_random_bytes(16)`.
	key := make([]byte, 16)
	if _, err := rand.Read(key); err != nil {
		log.Fatalf("Failed to generate random key: %v", err)
	}

	// Generate a random 16-byte nonce. For EAX, the nonce size must match the
	// cipher's block size (16 bytes for AES).
	nonce := make([]byte, 16)
	if _, err := rand.Read(nonce); err != nil {
		log.Fatalf("Failed to generate random nonce: %v", err)
	}

	fmt.Println("Original Message:", originalMessage)

	// Encrypt the message.
	ciphertext, tag, err := encodeString(originalMessage, key, nonce)
	if err != nil {
		log.Fatalf("Encryption failed: %v", err)
	}

	// Decrypt the message.
	decodedString, err := decodeString(ciphertext, tag, key, nonce)
	if err != nil {
		log.Fatalf("Decryption failed: %v", err)
	}

	fmt.Println("Decoded Message: ", decodedString)
}