package main

import (
	"fmt"
)

// Function to encrypt a given string
func encrypt(text string, key int) string {
	runes := []rune(text)
	for i := range runes {
		runes[i] = rune(rune(runes[i]) + rune(key))
	}
	return string(runes)
}

// Function to decrypt a given string
func decrypt(text string, key int) string {
	runes := []rune(text)
	for i := range runes {
		runes[i] = rune(rune(runes[i]) - rune(key))
	}
	return string(runes)
}

func main() {
	fmt.Println(encrypt("name", 1234))
	fmt.Println(decrypt("ՀԳԿԷ", 1234))
}