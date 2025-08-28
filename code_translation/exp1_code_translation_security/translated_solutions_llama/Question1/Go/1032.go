package main

import (
	"fmt"
)

// encrypt shifts the characters in a string by a given key to encrypt it.
func encrypt(str string, key int) string {
	encrypted := ""
	for _, char := range str {
		encrypted += string(rune(char + rune(key)))
	}
	return encrypted
}

// decrypt shifts the characters in a string back by a given key to decrypt it.
func decrypt(str string, key int) string {
	decrypted := ""
	for _, char := range str {
		decrypted += string(rune(char - rune(key)))
	}
	return decrypted
}

func main() {
	// Define a string and a key for encryption and decryption.
	str := "Hello World!"
	key := 5

	// Encrypt the string.
	encrypted := encrypt(str, key)
	fmt.Println("Encrypted:", encrypted)

	// Decrypt the string.
	decrypted := decrypt(encrypted, key)
	fmt.Println("Decrypted:", decrypted)
}