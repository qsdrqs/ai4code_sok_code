package main

import (
	"fmt"
	"strings"
)

// encrypt performs a Vigenère cipher encryption on the plaintext.
// It shifts each character of the plaintext by the corresponding character in the key.
func encrypt(plaintext, key string) string {
	// Use strings.Builder for efficient string construction.
	var ciphertext strings.Builder
	ciphertext.Grow(len(plaintext)) // Pre-allocate memory for performance.

	for i, pChar := range plaintext {
		// Get the corresponding key character, wrapping around the key if necessary.
		keyChar := rune(key[i%len(key)])

		// The encryption logic:
		// 1. Convert plaintext char to a 0-25 value: (pChar - 'a')
		// 2. Convert key char to a 0-25 value: (keyChar - 'a')
		// 3. Add them together and wrap around the alphabet with modulo 26.
		// 4. Convert the result back to a character by adding 'a'.
		encryptedChar := 'a' + (pChar-'a'+keyChar-'a')%26
		ciphertext.WriteRune(encryptedChar)
	}

	return ciphertext.String()
}

// decrypt reverses the Vigenère cipher encryption.
// It shifts each character of the ciphertext back by the corresponding character in the key.
func decrypt(ciphertext, key string) string {
	// Use strings.Builder for efficient string construction.
	var plaintext strings.Builder
	plaintext.Grow(len(ciphertext)) // Pre-allocate memory.

	for i, cChar := range ciphertext {
		// Get the corresponding key character, wrapping around the key.
		keyChar := rune(key[i%len(key)])

		// The decryption logic:
		// 1. Convert ciphertext char to a 0-25 value: (cChar - 'a')
		// 2. Convert key char to a 0-25 value: (keyChar - 'a')
		// 3. Subtract the key value. Add 26 to ensure the result is non-negative
		//    before the modulo operation (e.g., ('a' - 'c' + 26) % 26).
		// 4. Convert the result back to a character by adding 'a'.
		decryptedChar := 'a' + (cChar-keyChar+26)%26
		plaintext.WriteRune(decryptedChar)
	}

	return plaintext.String()
}

func main() {
	plaintext := "helloworld"
	key := "python"

	ciphertext := encrypt(plaintext, key)
	fmt.Println("Ciphertext: " + ciphertext)

	decrypted := decrypt(ciphertext, key)
	fmt.Println(decrypted)

	// Go's equivalent of Python's `assert` for testing.
	// We check the condition and panic if it's not met.
	if plaintext != decrypted {
		panic("Assertion failed: plaintext does not match decrypted text!")
	}
}