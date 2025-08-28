package main

import (
	"fmt"
)

// encrypt encrypts a given string using a given symmetric key.
func encrypt(string string, key string) string {
	encryptedString := ""
	for i := range string {
		encryptedString += string(rune(ord(string[i]) ^ ord(key[i%len(key)])))
	}
	return encryptedString
}

// decrypt decrypts a given string using a given symmetric key.
func decrypt(string string, key string) string {
	decryptedString := ""
	for i := range string {
		decryptedString += string(rune(ord(string[i]) ^ ord(key[i%len(key)])))
	}
	return decryptedString
}

// ord returns the ASCII value of a character.
func ord(c byte) byte {
	return c
}

func main() {
	key := "my_secret_key"
	input := "Hello, World!"
	fmt.Printf("Input: %s\n", input)
	fmt.Printf("Key: %s\n", key)

	encrypted := encrypt(input, key)
	fmt.Printf("Encrypted: %s\n", encrypted)

	decrypted := decrypt(encrypted, key)
	fmt.Printf("Decrypted: %s\n", decrypted)
}