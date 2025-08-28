package main

import (
	"fmt"
)

/*
Function encrypt

Given a message and a key, encrypt the message using XOR encryption.
*/
func encrypt(message, key string) string {
	// In Go, strings are sequences of bytes. To correctly handle Unicode
	// characters like Python's ord() and chr(), we convert the strings to
	// slices of runes. A rune is Go's type for a single Unicode code point.
	messageRunes := []rune(message)
	keyRunes := []rune(key)
	keyLen := len(keyRunes)

	// Create a slice of runes to hold the encrypted result.
	encryptedRunes := make([]rune, len(messageRunes))

	for i, char := range messageRunes {
		// Get the corresponding key character, repeating the key if necessary.
		keyChar := keyRunes[i%keyLen]
		// Perform the XOR operation on the rune (integer) values.
		encryptedRunes[i] = char ^ keyChar
	}

	// Convert the slice of runes back to a string.
	return string(encryptedRunes)
}

/*
Function decrypt

Given an encrypted message and a key, decrypt the message using XOR encryption.
*/
func decrypt(encrypted, key string) string {
	// XOR encryption is symmetric; applying the same operation with the same key
	// will decrypt the message.
	return encrypt(encrypted, key)
}

func main() {
	// This is the main entry point of the program, equivalent to the
	// script execution part in Python.
	originalMessage := "Hello world"
	secretKey := "key"

	// Encrypt the message
	encryptedMessage := encrypt(originalMessage, secretKey)

	// Decrypt the message
	decryptedMessage := decrypt(encryptedMessage, secretKey)

	// Print the final decrypted message to verify it matches the original.
	fmt.Println(decryptedMessage)

	// You can also nest the calls just like in the Python example:
	// fmt.Println(decrypt(encrypt("Hello world", "key"), "key"))
}