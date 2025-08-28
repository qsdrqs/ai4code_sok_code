package main

import (
	"fmt"
)

// encrypt encrypts a string using the provided key.
func encrypt(s string, key int) string {
	runes := []rune(s)          // Work with runes to handle Unicode safely.
	for i, r := range runes {
		runes[i] = rune(int(r) + key)
	}
	return string(runes)
}

// decrypt decrypts a string using the provided key.
func decrypt(s string, key int) string {
	runes := []rune(s)
	for i, r := range runes {
		runes[i] = rune(int(r) - key)
	}
	return string(runes)
}

func main() {
	str := "Hello World!"
	key := 5

	encrypted := encrypt(str, key)
	decrypted := decrypt(encrypted, key)

	fmt.Println("Encrypted:", encrypted)
	fmt.Println("Decrypted:", decrypted)
}