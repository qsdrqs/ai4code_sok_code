package main

import (
	"fmt"
)

func encrypt(key, plaintext string) string {
	var ciphertext []rune
	keyRunes := []rune(key)
	plainRunes := []rune(plaintext)

	for i := 0; i < len(plainRunes); i++ {
		keyC := keyRunes[i%len(keyRunes)]
		cipherC := (plainRunes[i] + keyC) % 256
		ciphertext = append(ciphertext, cipherC)
	}

	return string(ciphertext)
}

func decrypt(key string, ciphertext string) string {
	var plaintext []rune
	keyRunes := []rune(key)
	cipherRunes := []rune(ciphertext)

	for i := 0; i < len(cipherRunes); i++ {
		keyC := keyRunes[i%len(keyRunes)]
		plainC := (cipherRunes[i] - keyC + 256) % 256
		plaintext = append(plaintext, plainC)
	}

	return string(plaintext)
}

func main() {
	result := decrypt("123", encrypt("123", "helloworld"))
	fmt.Println(result)
}