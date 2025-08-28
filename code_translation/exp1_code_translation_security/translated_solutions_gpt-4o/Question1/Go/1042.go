package main

import (
	"strings"
	"unicode"
)

func encrypt(key int, str string) string {
	if key < 0 || len(str) == 0 {
		return ""
	}

	// Ensure key is within the range of 0-26
	if key > 26 {
		key = key % 26
	}

	var newString strings.Builder
	for _, char := range str {
		if unicode.IsLetter(char) {
			if unicode.IsLower(char) {
				newString.WriteRune(rune((int(char)-97+key)%26 + 97))
			} else {
				newString.WriteRune(rune((int(char)-65+key)%26 + 65))
			}
		} else {
			newString.WriteRune(char)
		}
	}
	return newString.String()
}

func decrypt(ciphertext, key string) string {
	if len(ciphertext) == 0 || len(key) == 0 {
		return ""
	}
	if len(key) == 1 {
		return ciphertext
	}
	if len(key) > len(ciphertext) {
		return ""
	}

	keyLen := len(key)
	keyAscii := make([]int, keyLen)
	for i, char := range key {
		keyAscii[i] = int(char)
	}

	ciphertextAscii := make([]int, len(ciphertext))
	for i, char := range ciphertext {
		ciphertextAscii[i] = int(char)
	}

	var plaintext strings.Builder
	for i, char := range ciphertextAscii {
		value := (char - keyAscii[i%keyLen]) % 26
		if value < 0 {
			value += 26
		}
		plaintext.WriteRune(rune(value + 65))
	}

	return plaintext.String()
}

func main() {
	// Example usage
	encrypted := encrypt(3, "Hello, World!")
	println("Encrypted:", encrypted)

	decrypted := decrypt("KHOOR", "KEY")
	println("Decrypted:", decrypted)
}