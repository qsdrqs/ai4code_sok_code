package main

import (
	"fmt"
)

// Function to encrypt a given string
func encrypt(text string, key int) string {
	textList := []rune(text)
	for i := 0; i < len(textList); i++ {
		textList[i] = rune(int(textList[i]) + key)
	}
	return string(textList)
}

// Function to decrypt a given string
func decrypt(text string, key int) string {
	textList := []rune(text)
	for i := 0; i < len(textList); i++ {
		textList[i] = rune(int(textList[i]) - key)
	}
	return string(textList)
}

func main() {
	encrypted := encrypt("name", 1234)
	fmt.Println(encrypted)

	decrypted := decrypt("ՀԳԿԷ", 1234)
	fmt.Println(decrypted)
}