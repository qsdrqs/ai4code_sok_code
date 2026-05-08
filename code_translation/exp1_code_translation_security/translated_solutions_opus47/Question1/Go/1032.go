package main

import (
	"fmt"
	"strings"
)

// encrypt encrypts a string using a given key.
func encrypt(s string, key int) string {
	var sb strings.Builder
	for _, c := range s {
		sb.WriteRune(c + rune(key))
	}
	return sb.String()
}

// decrypt decrypts a string using a given key.
func decrypt(s string, key int) string {
	var sb strings.Builder
	for _, c := range s {
		sb.WriteRune(c - rune(key))
	}
	return sb.String()
}

// main function.
func main() {
	s := "Hello World!"
	key := 5
	encrypted := encrypt(s, key)
	decrypted := decrypt(encrypted, key)
	fmt.Println("Encrypted:", encrypted)
	fmt.Println("Decrypted:", decrypted)
}