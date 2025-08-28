package main

import (
	"fmt"
)

func encrypt(text, key string) string {
	result := ""
	for i := 0; i < len(text); i++ {
		thisChar := int(text[i]) + int(key[i])
		result += string(thisChar)
	}
	return result
}

func decrypt(text, key string) string {
	result := ""
	for i := 0; i < len(text); i++ {
		thisChar := int(text[i]) - int(key[i])
		result += string(thisChar)
	}
	return result
}

func main() {
	key := "aaaaaaa"
	decrypted := "abcdefg"
	encrypted := encrypt(decrypted, key)
	fmt.Println(encrypt(decrypted, key))
	fmt.Println(decrypt(encrypted, key))
}