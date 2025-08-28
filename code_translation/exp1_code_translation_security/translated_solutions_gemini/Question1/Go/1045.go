package main

import (
	"fmt"
)

// encrypt encrypts a given string using a simple character offset.
// It iterates over each character (rune) of the input string, adds the key
// to its Unicode code point, and returns the resulting string.
func encrypt(text string, key int) string {
	// Convert the string to a slice of runes to handle Unicode characters correctly.
	// A rune is Go's representation for a character (Unicode code point).
	runes := []rune(text)

	for i, char := range runes {
		// Add the key to the character's integer value and update the slice.
		runes[i] = char + rune(key)
	}

	// Convert the modified slice of runes back to a string.
	return string(runes)
}

// decrypt decrypts a given string using a simple character offset.
// It is the inverse of the encrypt function. It subtracts the key from each
// character's Unicode code point.
func decrypt(text string, key int) string {
	// Convert the string to a slice of runes.
	runes := []rune(text)

	for i, char := range runes {
		// Subtract the key from the character's integer value.
		runes[i] = char - rune(key)
	}

	// Convert the slice of runes back to a string.
	return string(runes)
}

func main() {
	// Encrypt the string "name" with key 1234 and print the result.
	encryptedText := encrypt("name", 1234)
	fmt.Println(encryptedText)

	// Decrypt the string "ՀԳԿԷ" with key 1234 and print the result.
	decryptedText := decrypt("ՀԳԿԷ", 1234)
	fmt.Println(decryptedText)
}