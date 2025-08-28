package main

import (
	"fmt"
)

// encrypt takes in a text and a key, and returns the encrypted text.
// The encryption process adds the ASCII values of characters in the text and key.
func encrypt(text string, key string) string {
	result := ""
	for i := 0; i < len(text); i++ {
		// Use modulus to loop back to the start of the key if text is longer
		thisChar := int(text[i]) + int(key[i%len(key)])
		result += string(rune(thisChar))
	}
	return result
}

// decrypt takes in a text and a key, and returns the decrypted text.
// The decryption process subtracts the ASCII values of characters in the text and key.
func decrypt(text string, key string) string {
	result := ""
	for i := 0; i < len(text); i++ {
		// Use modulus to loop back to the start of the key if text is longer
		thisChar := int(text[i]) - int(key[i%len(key)])
		result += string(rune(thisChar))
	}
	return result
}

func main() {
	key := "aaaaaaa"
	decrypted := "abcdefg"
	encrypted := encrypt(decrypted, key)
	fmt.Println("Encrypted:", encrypted)
	fmt.Println("Decrypted:", decrypt(encrypted, key))
}