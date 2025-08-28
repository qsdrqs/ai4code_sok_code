package main

import "fmt"

// Encrypt encrypts a given string using a given symmetric key.
// It performs a simple XOR cipher operation.
func Encrypt(text string, key string) string {
	// An empty key would cause a panic (division by zero), so we handle it.
	if len(key) == 0 {
		return text
	}

	// Create a byte slice of the same length as the input string.
	// This is more efficient than concatenating strings in a loop.
	encrypted := make([]byte, len(text))

	for i := 0; i < len(text); i++ {
		// XOR the byte from the input string with the corresponding byte from the key.
		// The modulo operator (%) ensures the key repeats if it's shorter than the text.
		encrypted[i] = text[i] ^ key[i%len(key)]
	}

	// Convert the resulting byte slice back to a string.
	return string(encrypted)
}

// Decrypt decrypts a given string using a given symmetric key.
// For a simple XOR cipher, the decryption algorithm is identical to the encryption one.
func Decrypt(encryptedText string, key string) string {
	// An empty key would cause a panic (division by zero), so we handle it.
	if len(key) == 0 {
		return encryptedText
	}

	// The decryption process is the same as encryption for XOR ciphers.
	// (A XOR B) XOR B = A
	decrypted := make([]byte, len(encryptedText))

	for i := 0; i < len(encryptedText); i++ {
		decrypted[i] = encryptedText[i] ^ key[i%len(key)]
	}

	return string(decrypted)
}

// main function to demonstrate the usage of Encrypt and Decrypt.
func main() {
	originalMessage := "Hello, Go developers!"
	secretKey := "mysecret"

	fmt.Printf("Original Message: %s\n", originalMessage)
	fmt.Printf("Secret Key:       %s\n\n", secretKey)

	// Encrypt the message
	encryptedMessage := Encrypt(originalMessage, secretKey)
	fmt.Printf("Encrypted Message: %s\n", encryptedMessage)
	// The encrypted output may contain non-printable characters.
	// Printing as hex can be more informative.
	fmt.Printf("Encrypted (Hex):   %x\n\n", encryptedMessage)

	// Decrypt the message
	decryptedMessage := Decrypt(encryptedMessage, secretKey)
	fmt.Printf("Decrypted Message: %s\n", decryptedMessage)

	// Verify the result
	if originalMessage == decryptedMessage {
		fmt.Println("\nSuccess: Decryption restored the original message.")
	} else {
		fmt.Println("\nError: Decryption failed.")
	}
}