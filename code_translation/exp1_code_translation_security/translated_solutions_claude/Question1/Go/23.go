package main

import (
	"fmt"
)

// this is gonna be really scuffed but it might work
// right now text and key must be equal length, but that can be changed to loop to the front of the key again for very long text
// it's TECHNICALLY a psuedo-cypher

func encrypt(text, key string) string {
	result := ""
	for i := 0; i < len(text); i++ {
		thisChar := int(text[i]) + int(key[i])
		result += string(rune(thisChar))
	}
	return result
}

func decrypt(text, key string) string {
	result := ""
	for i := 0; i < len(text); i++ {
		thisChar := int(text[i]) - int(key[i])
		result += string(rune(thisChar))
	}
	return result
}

func main() {
	key := "aaaaaaa"
	decrypted := "abcdefg"
	encrypted := encrypt("abcdefg", key)
	fmt.Println(encrypt(decrypted, key))
	fmt.Println(decrypt(encrypted, key))
}