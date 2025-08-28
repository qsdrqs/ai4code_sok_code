package main

import (
	"fmt"
)

// Encrypt encrypts the plaintext using the Vigenère cipher with the given key.
func encrypt(plaintext, key string) string {
	ciphertext := make([]byte, len(plaintext))
	keyLen := len(key)

	for i := 0; i < len(plaintext); i++ {
		// Convert characters to 0-based index in the alphabet
		p := int(plaintext[i] - 'a')
		k := int(key[i%keyLen] - 'a')

		// Apply Vigenère cipher: (p + k) % 26
		c := (p + k) % 26

		// Convert back to ASCII character
		ciphertext[i] = byte(c + 'a')
	}

	return string(ciphertext)
}

// Decrypt decrypts the ciphertext using the Vigenère cipher with the given key.
func decrypt(ciphertext, key string) string {
	plaintext := make([]byte, len(ciphertext))
	keyLen := len(key)

	for i := 0; i < len(ciphertext); i++ {
		// Convert characters to 0-based index in the alphabet
		c := int(ciphertext[i] - 'a')
		k := int(key[i%keyLen] - 'a')

		// Apply reverse Vigenère cipher: (c - k + 26) % 26
		p := (c - k + 26) % 26

		// Convert back to ASCII character
		plaintext[i] = byte(p + 'a')
	}

	return string(plaintext)
}

// Main function to test the encryption and decryption.
func main() {
	plaintext := "helloworld"
	key := "python"

	ciphertext := encrypt(plaintext, key)
	fmt.Println("Ciphertext:", ciphertext)

	decrypted := decrypt(ciphertext, key)
	fmt.Println("Decrypted:", decrypted)

	// Assert that the decrypted text matches the original plaintext
	if plaintext != decrypted {
		panic("Assertion failed: decrypted text does not match original plaintext")
	}
}