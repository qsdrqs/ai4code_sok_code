package main

import (
	"fmt"
)

// Encrypt applies a repeating key XOR-like cipher to a string.
// It adds the byte values of the text and the key, modulo 256.
func Encrypt(key, text string) string {
	// Create a byte slice to hold the encrypted data.
	// Pre-allocating with the same length as the text is efficient.
	encryptedBytes := make([]byte, len(text))

	for i := 0; i < len(text); i++ {
		// Get the corresponding character from the key, repeating the key if necessary.
		keyChar := key[i%len(key)]

		// The encryption logic: (text_char_byte + key_char_byte) % 256
		// In Go, we can perform arithmetic directly on bytes, but casting to int
		// prevents any potential overflow issues and makes the logic clearer.
		encryptedChar := (int(text[i]) + int(keyChar)) % 256
		encryptedBytes[i] = byte(encryptedChar)
	}

	// Convert the byte slice back to a string.
	return string(encryptedBytes)
}

// Decrypt reverses the encryption process.
// It subtracts the byte values of the key from the encrypted text, modulo 256.
func Decrypt(key, encryptedText string) string {
	// Create a byte slice for the decrypted data.
	decryptedBytes := make([]byte, len(encryptedText))

	for i := 0; i < len(encryptedText); i++ {
		// Get the corresponding character from the key, repeating as in encryption.
		keyChar := key[i%len(key)]

		// The decryption logic: (encrypted_char_byte - key_char_byte + 256) % 256
		// The +256 is crucial to handle potential negative results from the subtraction,
		// ensuring the modulo operation works correctly for decryption.
		decryptedChar := (int(encryptedText[i]) - int(keyChar) + 256) % 256
		decryptedBytes[i] = byte(decryptedChar)
	}

	// Convert the byte slice back to a string.
	return string(decryptedBytes)
}

// main function to demonstrate the usage of Encrypt and Decrypt.
func main() {
	secretKey := "myverystrongpassword"
	originalMessage := "Hello, World! This is a secret message that needs to be encrypted."

	fmt.Printf("Original Text:  %s\n", originalMessage)

	// Encrypt the message
	encryptedMessage := Encrypt(secretKey, originalMessage)
	fmt.Printf("Encrypted Text: %s\n", encryptedMessage)

	// Decrypt the message
	decryptedMessage := Decrypt(secretKey, encryptedMessage)
	fmt.Printf("Decrypted Text: %s\n", decryptedMessage)

	// Verify that the decrypted message matches the original
	if originalMessage == decryptedMessage {
		fmt.Println("\nSuccess: The decrypted message matches the original.")
	} else {
		fmt.Println("\nError: The decrypted message does not match the original.")
	}
}