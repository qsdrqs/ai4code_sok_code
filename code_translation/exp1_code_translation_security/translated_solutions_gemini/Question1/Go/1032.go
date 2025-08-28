package main

import (
	"fmt"
)

// encrypt encrypts a string using a given key by shifting each character's
// rune value forward by the key amount. This is a simple Caesar cipher.
func encrypt(text string, key int) string {
	// A slice of runes is used to build the new string. Runes are Go's
	// way of representing Unicode code points, similar to Python's ord().
	encryptedRunes := []rune{}
	for _, char := range text {
		// Add the key to the rune value of each character.
		encryptedChar := char + rune(key)
		encryptedRunes = append(encryptedRunes, encryptedChar)
	}
	// Convert the slice of runes back to a string.
	return string(encryptedRunes)
}

// decrypt decrypts a string using a given key by shifting each character's
// rune value backward by the key amount.
func decrypt(text string, key int) string {
	// A slice of runes is used to build the new string.
	decryptedRunes := []rune{}
	for _, char := range text {
		// Subtract the key from the rune value of each character.
		decryptedChar := char - rune(key)
		decryptedRunes = append(decryptedRunes, decryptedChar)
	}
	// Convert the slice of runes back to a string.
	return string(decryptedRunes)
}

// main is the entry point of the program.
func main() {
	originalString := "Hello World!"
	key := 5

	// Encrypt the original string.
	encrypted := encrypt(originalString, key)
	// Decrypt the encrypted string to get the original back.
	decrypted := decrypt(encrypted, key)

	// Print the results to the console.
	fmt.Println("Encrypted:", encrypted)
	fmt.Println("Decrypted:", decrypted)
}