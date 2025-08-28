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
	original := "Hello, World!"
	key := "key"
	
	encrypted := encrypt(original, key)
	fmt.Println("Encrypted:", encrypted)
	
	decrypted := decrypt(encrypted, key)
	fmt.Println("Decrypted:", decrypted)
}