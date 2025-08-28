package main

import (
	"fmt"
)

// Encrypts a string using a given key by shifting each character's Unicode code point by the key value.
func encrypt(s string, key int) string {
	encrypted := ""
	for _, c := range s {
		encrypted += string(int(c) + key)
	}
	return encrypted
}

// Decrypts a string using a given key by shifting each character's Unicode code point back by the key value.
func decrypt(s string, key int) string {
	decrypted := ""
	for _, c := range s {
		decrypted += string(int(c) - key)
	}
	return decrypted
}

func main() {
	// Example usage of the encrypt and decrypt functions
	s := "Hello World!"
	key := 5
	encrypted := encrypt(s, key)
	decrypted := decrypt(encrypted, key)
	fmt.Println("Encrypted:", encrypted)
	fmt.Println("Decrypted:", decrypted)
}