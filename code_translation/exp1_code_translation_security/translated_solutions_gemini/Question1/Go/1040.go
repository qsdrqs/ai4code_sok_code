package main

import (
	"fmt"
	"strings"
)

// encrypt encrypts a string with a given key using a simple XOR cipher.
// It iterates through the input string, XORing each character with a character from the key.
// The key is repeated if it's shorter than the input string.
func encrypt(input string, key string) string {
	// A strings.Builder is an efficient way to build strings.
	var result strings.Builder
	result.Grow(len(input)) // Pre-allocate memory for performance.

	// Convert the key to a slice of runes to handle multi-byte characters correctly.
	keyRunes := []rune(key)
	keyLen := len(keyRunes)

	// If the key is empty, return the original string to avoid a panic.
	if keyLen == 0 {
		return input
	}

	// We need a separate counter `i` that increments from 0, 1, 2...
	// because the index from `range` over a string is the byte position, not the character position.
	i := 0
	for _, char := range input {
		// Get the corresponding key character, cycling through the key.
		keyChar := keyRunes[i%keyLen]

		// Perform the XOR operation on the rune's integer values.
		encryptedChar := char ^ keyChar

		// Append the resulting character to our builder.
		result.WriteRune(encryptedChar)
		i++
	}

	return result.String()
}

// decrypt decrypts a string with a given key.
// For an XOR cipher, encryption and decryption are the same operation.
func decrypt(input string, key string) string {
	return encrypt(input, key)
}

func main() {
	// This section replicates the assertion test from the Python code.
	originalText := "sadasdasdsa"
	secretKey := "1234"

	fmt.Printf("Original:  %s\n", originalText)
	fmt.Printf("Key:       %s\n", secretKey)

	// Encrypt the original text
	encryptedText := encrypt(originalText, secretKey)
	fmt.Printf("Encrypted: %s\n", encryptedText)

	// Decrypt the text and verify it matches the original
	decryptedText := decrypt(encryptedText, secretKey)
	fmt.Printf("Decrypted: %s\n", decryptedText)

	fmt.Println("\n--- Running Assertion Test ---")
	if decryptedText == originalText {
		fmt.Println("✅ Test Passed: Decrypted text matches the original.")
	} else {
		fmt.Println("❌ Test Failed: Decrypted text does not match the original.")
	}
}