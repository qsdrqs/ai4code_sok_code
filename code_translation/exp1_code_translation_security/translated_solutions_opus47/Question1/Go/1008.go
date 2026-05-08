package main

import (
	"fmt"
)

// encrypt with key
func encrypt(key, plaintext string) string {
	keyRunes := []rune(key)
	plainRunes := []rune(plaintext)
	ciphertext := make([]rune, len(plainRunes))
	for i := 0; i < len(plainRunes); i++ {
		keyC := keyRunes[i%len(keyRunes)]
		cipherC := rune((plainRunes[i] + keyC) % 256)
		ciphertext[i] = cipherC
	}
	return string(ciphertext)
}

// decrypt with key
func decrypt(key, ciphertext string) string {
	keyRunes := []rune(key)
	cipherRunes := []rune(ciphertext)
	plaintext := make([]rune, len(cipherRunes))
	for i := 0; i < len(cipherRunes); i++ {
		keyC := keyRunes[i%len(keyRunes)]
		plainC := rune((256 + cipherRunes[i] - keyC) % 256)
		plaintext[i] = plainC
	}
	return string(plaintext)
}

func main() {
	fmt.Println(decrypt("123", encrypt("123", "helloworld")))
}