package main

import (
	"fmt"
)

// function to encrypt a given string
func encrypt(text string, key int) string {
	textRunes := []rune(text)
	for i := range textRunes {
		textRunes[i] = textRunes[i] + rune(key)
	}
	return string(textRunes)
}

// function to decrypt a given string
func decrypt(text string, key int) string {
	textRunes := []rune(text)
	for i := range textRunes {
		textRunes[i] = textRunes[i] - rune(key)
	}
	return string(textRunes)
}

func main() {
	fmt.Println(encrypt("name", 1234))
	fmt.Println(decrypt("ՀԳԿԷ", 1234))
}