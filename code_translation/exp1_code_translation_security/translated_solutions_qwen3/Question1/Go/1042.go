package main

import (
	"unicode"
)

// encrypt shifts each letter in the input string by 'key' positions in the alphabet.
// Non-alphabetic characters are left unchanged.
// Returns an empty string for invalid inputs.
func encrypt(key int, s string) string {
	if key < 0 || len(s) == 0 {
		return ""
	}
	if key > 26 {
		key %= 26
	}

	var newString string
	for _, c := range s {
		if unicode.IsLetter(c) {
			if unicode.IsLower(c) {
				shifted := (int(c) - 97 + key) % 26 + 97
				newString += string(rune(shifted))
			} else {
				shifted := (int(c) - 65 + key) % 26 + 65
				newString += string(rune(shifted))
			}
		} else {
			newString += string(c)
		}
	}
	return newString
}

// decrypt uses a symmetric key to decrypt a given ciphertext.
// It assumes the key and ciphertext are ASCII strings.
// Returns an empty string for invalid inputs.
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
	for i := 0; i < keyLen; i++ {
		keyAscii[i] = int(key[i])
	}

	ciphertextLen := len(ciphertext)
	ciphertextAscii := make([]int, ciphertextLen)
	for i := 0; i < ciphertextLen; i++ {
		ciphertextAscii[i] = int(ciphertext[i])
	}

	plaintext := make([]byte, ciphertextLen)
	for i := 0; i < ciphertextLen; i++ {
		diff := ciphertextAscii[i] - keyAscii[i%keyLen]
		value := (diff%26 + 26) % 26
		plaintext[i] = byte(value + 65)
	}

	return string(plaintext)
}