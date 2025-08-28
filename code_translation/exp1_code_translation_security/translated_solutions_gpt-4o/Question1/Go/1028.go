package main

import (
	"fmt"
)

func encrypt(input string, key string) string {
	encryptedString := ""
	for i := 0; i < len(input); i++ {
		encryptedChar := input[i] ^ key[i%len(key)]
		encryptedString += string(encryptedChar)
	}
	return encryptedString
}

func decrypt(input string, key string) string {
	decryptedString := ""
	for i := 0; i < len(input); i++ {
		decryptedChar := input[i] ^ key[i%len(key)]
		decryptedString += string(decryptedChar)
	}
	return decryptedString
}

func main() {
	originalText := "Hello, World!"
	key := "key"

	encryptedText := encrypt(originalText, key)
	fmt.Println("Encrypted:", encryptedText)

	decryptedText := decrypt(encryptedText, key)
	fmt.Println("Decrypted:", decryptedText)
}