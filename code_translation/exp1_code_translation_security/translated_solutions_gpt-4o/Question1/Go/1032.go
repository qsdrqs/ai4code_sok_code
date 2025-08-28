package main

import (
	"fmt"
)

// Encrypts a string using a given key.
func encrypt(input string, key int) string {
	encrypted := ""
	for _, char := range input {
		encrypted += string(rune(char) + rune(key))
	}
	return encrypted
}

// Decrypts a string using a given key.
func decrypt(input string, key int) string {
	decrypted := ""
	for _, char := range input {
		decrypted += string(rune(char) - rune(key))
	}
	return decrypted
}

func main() {
	// Main function
	str := "Hello World!"
	key := 5
	encrypted := encrypt(str, key)
	decrypted := decrypt(encrypted, key)
	fmt.Println("Encrypted:", encrypted)
	fmt.Println("Decrypted:", decrypted)
}