package main

import (
	"fmt"
)

// Function encrypt
// Given a message and a key, encrypt the message using XOR encryption.
func encrypt(message string, key string) string {
	if len(key) == 0 {
		return ""
	}

	encrypted := ""
	for i := range message {
		encrypted += string(rune(int(message[i]) ^ int(key[i%len(key)])))
	}
	return encrypted
}

// Function decrypt
// Given an encrypted message and a key, decrypt the message using XOR encryption.
func decrypt(encrypted string, key string) string {
	return encrypt(encrypted, key)
}

func main() {
	originalMessage := "Hello world"
	key := "key"
	encryptedMessage := encrypt(originalMessage, key)
	fmt.Printf("Encrypted message: %s\n", encryptedMessage)

	decryptedMessage := decrypt(encryptedMessage, key)
	fmt.Printf("Decrypted message: %s\n", decryptedMessage)
}