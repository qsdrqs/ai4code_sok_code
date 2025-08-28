package main

import (
	"encoding/base64"
	"errors"
	"fmt"
	"log"
	"time"

	"github.com/fernet/fernet-go"
)

// Encrypt takes a plaintext string and a URL-safe base64-encoded key,
// and returns the Fernet-encrypted message as bytes.
// It is equivalent to Python's `fernet.encrypt(input.encode())`.
func Encrypt(plaintext string, key string) ([]byte, error) {
	// The key must be URL-safe base64-encoded.
	k, err := fernet.DecodeKey(key)
	if err != nil {
		return nil, fmt.Errorf("failed to decode key: %w", err)
	}

	// Encrypt the message. The input to EncryptAndSign must be bytes.
	encryptedMsg, err := fernet.EncryptAndSign([]byte(plaintext), k)
	if err != nil {
		return nil, fmt.Errorf("failed to encrypt message: %w", err)
	}

	return encryptedMsg, nil
}

// Decrypt takes a Fernet-encrypted message (as bytes) and a URL-safe
// base64-encoded key, and returns the original plaintext string.
// It is equivalent to Python's `fernet.decrypt(input).decode()`.
func Decrypt(encryptedMsg []byte, key string) (string, error) {
	// The key must be URL-safe base64-encoded.
	k, err := fernet.DecodeKey(key)
	if err != nil {
		return "", fmt.Errorf("failed to decode key: %w", err)
	}

	// Decrypt the message. fernet.VerifyAndDecrypt returns nil if the message
	// is invalid or has expired. We check for this and return a specific error.
	// We use fernet.Forever to indicate the token should not expire.
	decryptedBytes := fernet.VerifyAndDecrypt(encryptedMsg, time.Hour, []*fernet.Key{k})
	if decryptedBytes == nil {
		return "", errors.New("failed to verify or decrypt message: invalid token")
	}

	return string(decryptedBytes), nil
}

// main function to demonstrate usage
func main() {
	// 1. Generate a new encryption key.
	// In a real application, you would generate this once and store it securely.
	// The key is already in the required URL-safe base64 format.
	var key string
	// Use a static key for demonstration to have a predictable output.
	// In a real app, use `fernet.GenKey()` to generate a new one.
	keyBytes, _ := base64.URLEncoding.DecodeString("cw_0x689RpI-jtRR7oE8h_eQsKImv68odLUP4wvoVQo=")
	key = base64.URLEncoding.EncodeToString(keyBytes)

	fmt.Printf("Using Key: %s\n", key)

	// 2. Define the message to be encrypted.
	originalMessage := "this is a secret message"
	fmt.Printf("Original Message: %s\n\n", originalMessage)

	// 3. Encrypt the message.
	encrypted, err := Encrypt(originalMessage, key)
	if err != nil {
		log.Fatalf("Encryption failed: %v", err)
	}
	// Encrypted output is raw bytes, often represented in base64 for transport.
	fmt.Printf("Encrypted (raw bytes): %s\n", string(encrypted))
	fmt.Printf("Encrypted (base64 for transport): %s\n\n", base64.StdEncoding.EncodeToString(encrypted))

	// 4. Decrypt the message.
	decrypted, err := Decrypt(encrypted, key)
	if err != nil {
		log.Fatalf("Decryption failed: %v", err)
	}
	fmt.Printf("Decrypted Message: %s\n", decrypted)

	// 5. Verify the result.
	if originalMessage == decrypted {
		fmt.Println("\nSuccess: The decrypted message matches the original.")
	} else {
		fmt.Println("\nFailure: The messages do not match.")
	}
}