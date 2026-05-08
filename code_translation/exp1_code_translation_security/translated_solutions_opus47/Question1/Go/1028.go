// Package main provides two functions where one encrypts and the other
// decrypts a given string using a given symmetric key.
package main

import "fmt"

// Encrypt encrypts a given string using a given symmetric key.
func Encrypt(s, key string) string {
	encrypted := make([]byte, len(s))
	for i := 0; i < len(s); i++ {
		encrypted[i] = s[i] ^ key[i%len(key)]
	}
	return string(encrypted)
}

// Decrypt decrypts a given string using a given symmetric key.
func Decrypt(s, key string) string {
	decrypted := make([]byte, len(s))
	for i := 0; i < len(s); i++ {
		decrypted[i] = s[i] ^ key[i%len(key)]
	}
	return string(decrypted)
}

func main() {
	original := "Hello, World!"
	key := "secret"

	encrypted := Encrypt(original, key)
	fmt.Printf("Encrypted: %q\n", encrypted)

	decrypted := Decrypt(encrypted, key)
	fmt.Printf("Decrypted: %s\n", decrypted)
}