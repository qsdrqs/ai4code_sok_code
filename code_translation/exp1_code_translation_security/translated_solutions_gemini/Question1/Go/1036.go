package main

import (
	"fmt"
)

// Encrypt encrypts a string by shifting each character's rune value by the key.
// This is a Go equivalent of the Python encrypt function.
func encrypt(s string, key int) string {
	// In Go, strings are immutable. To build a new string, it's common to
	// use a slice of runes (a rune represents a Unicode code point).
	var encryptedRunes []rune

	// We loop over the input string. In Go, ranging over a string
	// yields runes (characters) and their byte indices.
	for _, char := range s {
		// We shift the rune value by the key and append it to our slice.
		// The key (int) must be cast to a rune type for the addition.
		encryptedRunes = append(encryptedRunes, char+rune(key))
	}

	// Finally, we convert the slice of runes back into a string.
	return string(encryptedRunes)
}

// Decrypt decrypts a string by shifting each character's rune value back by the key.
// This is a Go equivalent of the Python decrypt function.
func decrypt(s string, key int) string {
	var decryptedRunes []rune

	for _, char := range s {
		// We reverse the encryption process by subtracting the key.
		decryptedRunes = append(decryptedRunes, char-rune(key))
	}

	return string(decryptedRunes)
}

// main is the entry point of the Go program.
func main() {
	// In Go, we use `:=` for short variable declaration and initialization.
	text := "Hello World!"
	key := 5

	encrypted := encrypt(text, key)
	decrypted := decrypt(encrypted, key)

	// `fmt.Println` is Go's equivalent of Python's `print()`.
	fmt.Println("Encrypted:", encrypted)
	fmt.Println("Decrypted:", decrypted)
}