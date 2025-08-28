package main

import (
	"fmt"
)

// Encrypts the given text using the key by summing the Unicode code points
// of corresponding characters in text and key.
func encrypt(text, key string) string {
	tRunes := []rune(text)
	kRunes := []rune(key)

	if len(tRunes) != len(kRunes) {
		panic("key and text must be the same length")
	}

	result := make([]rune, len(tRunes))
	for i := 0; i < len(tRunes); i++ {
		result[i] = tRunes[i] + kRunes[i]
	}

	return string(result)
}

// Decrypts the given encrypted text using the key by subtracting the Unicode code points
// of corresponding characters in key from the encrypted text.
func decrypt(text, key string) string {
	tRunes := []rune(text)
	kRunes := []rune(key)

	if len(tRunes) != len(kRunes) {
		panic("key and text must be the same length")
	}

	result := make([]rune, len(tRunes))
	for i := 0; i < len(tRunes); i++ {
		result[i] = tRunes[i] - kRunes[i]
	}

	return string(result)
}

func main() {
	key := "aaaaaaa"
	decrypted := "abcdefg"

	// Encrypt the text
	encrypted := encrypt("abcdefg", key)

	// Re-encrypt the decrypted text (should be the same as encrypted)
	fmt.Println(encrypt(decrypted, key))

	// Decrypt the encrypted text
	fmt.Println(decrypt(encrypted, key))
}