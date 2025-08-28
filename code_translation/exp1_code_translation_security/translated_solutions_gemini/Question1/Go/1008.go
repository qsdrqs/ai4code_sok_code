package main

import (
	"fmt"
	"strings"
)

// encrypt encrypts plaintext with a repeating key cipher.
// This is a form of Vigenère cipher using modular addition on byte values.
func encrypt(key, plaintext string) string {
	// A strings.Builder is an efficient way to build strings.
	var ciphertext strings.Builder
	// Pre-allocating the builder's capacity can improve performance.
	ciphertext.Grow(len(plaintext))

	for i := 0; i < len(plaintext); i++ {
		// Get the corresponding character from the key, repeating the key if necessary.
		keyChar := key[i%len(key)]
		// Get the character from the plaintext.
		plainChar := plaintext[i]

		// Perform modular addition on the byte values of the characters.
		// We cast to int for the calculation and then back to byte.
		cipherChar := byte((int(plainChar) + int(keyChar)) % 256)

		// Append the resulting byte to our ciphertext.
		ciphertext.WriteByte(cipherChar)
	}

	return ciphertext.String()
}

// decrypt decrypts ciphertext that was encrypted with the corresponding key.
func decrypt(key, ciphertext string) string {
	var plaintext strings.Builder
	plaintext.Grow(len(ciphertext))

	for i := 0; i < len(ciphertext); i++ {
		// Get the corresponding character from the key, repeating as in encryption.
		keyChar := key[i%len(key)]
		// Get the character from the ciphertext.
		cipherChar := ciphertext[i]

		// Perform the reverse operation: modular subtraction.
		// Adding 256 before the modulo ensures the result is non-negative,
		// correctly handling cases where int(cipherChar) < int(keyChar).
		plainChar := byte((256 + int(cipherChar) - int(keyChar)) % 256)

		// Append the resulting byte to our plaintext.
		plaintext.WriteByte(plainChar)
	}

	return plaintext.String()
}

func main() {
	// Encrypt the message "helloworld" with the key "123",
	// then decrypt the result with the same key and print the final output.
	result := decrypt("123", encrypt("123", "helloworld"))
	fmt.Println(result)
}