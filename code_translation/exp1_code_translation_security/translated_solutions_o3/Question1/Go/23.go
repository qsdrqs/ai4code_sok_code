package main

import (
	"fmt"
)

// encrypt behaves like the Python version:
// every rune in `text` is shifted by the rune in `key` at the same index.
// (The caller must guarantee the two strings have identical lengths.)
func encrypt(text, key string) string {
	textRunes := []rune(text)
	keyRunes := []rune(key)

	result := make([]rune, len(textRunes))
	for i := 0; i < len(textRunes); i++ {
		result[i] = textRunes[i] + keyRunes[i]
	}
	return string(result)
}

// decrypt reverses `encrypt`.
func decrypt(text, key string) string {
	textRunes := []rune(text)
	keyRunes := []rune(key)

	result := make([]rune, len(textRunes))
	for i := 0; i < len(textRunes); i++ {
		result[i] = textRunes[i] - keyRunes[i]
	}
	return string(result)
}

func main() {
	key := "aaaaaaa"
	plaintext := "abcdefg"

	encrypted := encrypt(plaintext, key)
	fmt.Println(encrypted)              // identical to Python's encrypt output
	fmt.Println(decrypt(encrypted, key)) // should print "abcdefg"
}