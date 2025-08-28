package main

import (
	"fmt"
)

// this is gonna be really scuffed but it might work
// right now text and key must be equal length, but that can be changed to loop to the front of the key again for very long text
// it's TECHNICALLY a psuedo-cypher

// encrypt adds the Unicode values of the key's characters to the text's characters.
// This function mimics the behavior of the Python version.
func encrypt(text, key string) string {
	// In Go, it's idiomatic to work with a slice of runes for character-level manipulation,
	// especially for Unicode. A 'rune' is an alias for int32 and is Go's equivalent
	// of a character or Unicode code point, similar to what Python's ord() returns.
	textRunes := []rune(text)
	keyRunes := []rune(key)

	// We'll build a slice of runes for the result. This is more efficient than
	// concatenating strings in a loop in Go.
	resultRunes := make([]rune, len(textRunes))

	for i := 0; i < len(textRunes); i++ {
		// Add the rune values together. This is the direct equivalent of:
		// this_char = ord(text[i]) + ord(key[i])
		thisChar := textRunes[i] + keyRunes[i]
		resultRunes[i] = thisChar
	}

	// Convert the slice of runes back to a string, similar to Python's `"".join(...)`.
	return string(resultRunes)
}

// decrypt subtracts the Unicode values of the key's characters from the text's characters.
func decrypt(text, key string) string {
	textRunes := []rune(text)
	keyRunes := []rune(key)

	resultRunes := make([]rune, len(textRunes))

	for i := 0; i < len(textRunes); i++ {
		// Subtract the rune values. This is the direct equivalent of:
		// this_char = ord(text[i]) - ord(key[i])
		thisChar := textRunes[i] - keyRunes[i]
		resultRunes[i] = thisChar
	}

	return string(resultRunes)
}

// The main function is the entry point for the program.
func main() {
	key := "aaaaaaa"
	decrypted := "abcdefg"

	// Encrypt the original text.
	encrypted := encrypt("abcdefg", key)

	// Print the encrypted text. fmt.Println is Go's equivalent of print().
	fmt.Println(encrypt(decrypted, key))

	// Decrypt the encrypted text and print the original.
	fmt.Println(decrypt(encrypted, key))
}