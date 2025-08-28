package main

import (
	"fmt"
)

// Encrypts a given string by shifting each character's Unicode code point
// forward by the specified key value.
func encrypt(text string, key int) string {
	// Convert the string to a slice of runes (Unicode code points)
	runes := []rune(text)
	// Iterate through each rune and shift it forward by the key
	for i := range runes {
		runes[i] += rune(key)
	}
	// Convert the modified rune slice back to a string and return
	return string(runes)
}

// Decrypts a given string by shifting each character's Unicode code point
// backward by the specified key value.
func decrypt(text string, key int) string {
	// Convert the string to a slice of runes (Unicode code points)
	runes := []rune(text)
	// Iterate through each rune and shift it backward by the key
	for i := range runes {
		runes[i] -= rune(key)
	}
	// Convert the modified rune slice back to a string and return
	return string(runes)
}

// Main function to demonstrate encryption and decryption
func main() {
	// Encrypt the string "name" with the key 1234
	encrypted := encrypt("name", 1234)
	fmt.Println("Encrypted:", encrypted)

	// Decrypt the string "ՀԳԿԷ" with the same key
	decrypted := decrypt("ՀԳԿԷ", 1234)
	fmt.Println("Decrypted:", decrypted)
}