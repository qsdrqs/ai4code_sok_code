package main

import (
	"strings"
)

// Encrypt encrypts a given string using a given symmetric key.
// It uses a simple XOR cipher where each character of the string is XORed
// with a character from the key. The key is repeated if it's shorter than the string.
func Encrypt(text string, key string) string {
	// A strings.Builder is an efficient way to build the output string.
	var encrypted strings.Builder
	encrypted.Grow(len(text))

	// Convert the key to a slice of runes for proper Unicode character handling.
	keyRunes := []rune(key)
	keyLen := len(keyRunes)

	// Ensure the key is not empty to avoid a division by zero error.
	if keyLen == 0 {
		return text // Or handle as an error
	}

	// Iterate over the input string, getting the index and character (as a rune).
	for i, char := range text {
		// Get the corresponding key character, cycling through the key.
		keyChar := keyRunes[i%keyLen]
		
		// Perform the XOR operation on the rune's integer values.
		xoredChar := char ^ keyChar
		
		// Write the resulting character (rune) to the builder.
		encrypted.WriteRune(xoredChar)
	}

	return encrypted.String()
}

// Decrypt decrypts a given string using a given symmetric key.
// For an XOR cipher, the decryption process is identical to the encryption process.
func Decrypt(encryptedText string, key string) string {
	// Applying the same XOR operation with the same key reverses the encryption.
	return Encrypt(encryptedText, key)
}