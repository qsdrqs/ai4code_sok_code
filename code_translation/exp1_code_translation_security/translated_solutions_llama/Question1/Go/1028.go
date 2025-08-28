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
	key := "secretkey"
	originalString := "Hello, World!"
	fmt.Printf("Original String: %s\n", originalString)
	fmt.Printf("Key: %s\n", key)

	encryptedString := encrypt(originalString, key)
	fmt.Printf("Encrypted String: %s\n", encryptedString)

	decryptedString := decrypt(encryptedString, key)
	fmt.Printf("Decrypted String: %s\n", decryptedString)
}