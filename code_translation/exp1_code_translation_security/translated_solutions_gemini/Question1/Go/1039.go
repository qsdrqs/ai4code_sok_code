package main

import (
	"encoding/base64"
	"errors"
	"fmt"
	"log"
	"time"

	"github.com/fernet/fernet-go"
)

// encrypt mimics the behavior of Python's fernet.encrypt.
// It takes a message string and a URL-safe base64-encoded 32-byte key.
// It returns the encrypted message as a byte slice.
func encrypt(message string, keyString string) ([]byte, error) {
	// Decode the base64-encoded key.
	key, err := fernet.DecodeKey(keyString)
	if err != nil {
		return nil, fmt.Errorf("failed to decode key: %w", err)
	}

	// Encrypt the message. The message is first converted to a byte slice.
	encryptedMessage, err := fernet.EncryptAndSign([]byte(message), key)
	if err != nil {
		return nil, fmt.Errorf("failed to encrypt message: %w", err)
	}

	return encryptedMessage, nil
}

// decrypt mimics the behavior of Python's fernet.decrypt.
// It takes an encrypted byte slice and a URL-safe base64-encoded 32-byte key.
// It returns the decrypted message as a string.
func decrypt(encryptedMessage []byte, keyString string) (string, error) {
	// Decode the base64-encoded key.
	key, err := fernet.DecodeKey(keyString)
	if err != nil {
		return "", fmt.Errorf("failed to decode key: %w", err)
	}

	// Decrypt the message.
	// The TTL (Time-To-Live) is set to the default, which checks the timestamp
	// in the token and rejects it if it's too old.
	// The library expects a slice of valid keys.
	decryptedBytes := fernet.DecryptAndVerify(encryptedMessage, fernet.DefaultTTL, []*fernet.Key{key})

	// DecryptAndVerify returns nil on failure (e.g., invalid signature, expired TTL).
	if decryptedBytes == nil {
		return "", errors.New("decryption failed: invalid or expired token")
	}

	return string(decryptedBytes), nil
}

// main function to demonstrate the usage of encrypt and decrypt.
func main() {
	// 1. Generate a new Fernet key.
	// In a real application, you would generate this once and store it securely.
	key := fernet.NewKey()
	encodedKey := key.Encode() // Get the base64 string representation of the key.

	fmt.Printf("Generated Fernet Key: %s\n", encodedKey)

	// 2. Define the original message.
	originalMessage := "This is a super secret message."
	fmt.Printf("Original Message: %s\n\n", originalMessage)

	// 3. Encrypt the message.
	fmt.Println("--- Encrypting ---")
	encrypted, err := encrypt(originalMessage, encodedKey)
	if err != nil {
		log.Fatalf("Encryption failed: %v", err)
	}
	// Print the encrypted message as a base64 string for readability.
	fmt.Printf("Encrypted (raw bytes): %s\n", string(encrypted))
	fmt.Printf("Encrypted (base64 for display): %s\n\n", base64.StdEncoding.EncodeToString(encrypted))


	// 4. Decrypt the message.
	fmt.Println("--- Decrypting ---")
	decrypted, err := decrypt(encrypted, encodedKey)
	if err != nil {
		log.Fatalf("Decryption failed: %v", err)
	}
	fmt.Printf("Decrypted Message: %s\n\n", decrypted)

	// 5. Verify correctness.
	if originalMessage == decrypted {
		fmt.Println("Success: The decrypted message matches the original.")
	} else {
		fmt.Println("Failure: The messages do not match.")
	}
}