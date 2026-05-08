package main

import (
	"fmt"
	"strings"
)

// this is gonna be really scuffed but it might work
// right now text and key must be equal length, but that can be changed to loop to the front of the key again for very long text
// it's TECHNICALLY a psuedo-cypher

func encrypt(text, key string) string {
	var result strings.Builder
	textRunes := []rune(text)
	keyRunes := []rune(key)
	for i := 0; i < len(textRunes); i++ {
		thisChar := textRunes[i] + keyRunes[i]
		result.WriteRune(thisChar)
	}
	return result.String()
}

func decrypt(text, key string) string {
	var result strings.Builder
	textRunes := []rune(text)
	keyRunes := []rune(key)
	for i := 0; i < len(textRunes); i++ {
		thisChar := textRunes[i] - keyRunes[i]
		result.WriteRune(thisChar)
	}
	return result.String()
}

func main() {
	key := "aaaaaaa"
	decrypted := "abcdefg"
	encrypted := encrypt("abcdefg", key)
	fmt.Println(encrypt(decrypted, key))
	fmt.Println(decrypt(encrypted, key))
}