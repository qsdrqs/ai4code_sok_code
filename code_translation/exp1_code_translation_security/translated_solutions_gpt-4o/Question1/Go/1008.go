package main

import (
	"fmt"
)

func encrypt(key, plaintext string) string {
	ciphertext := make([]rune, len(plaintext))
	for i, char := range plaintext {
		keyChar := rune(key[i%len(key)])
		cipherChar := (char + keyChar) % 256
		ciphertext[i] = cipherChar
	}
	return string(ciphertext)
}

func decrypt(key, ciphertext string) string {
	plaintext := make([]rune, len(ciphertext))
	for i, char := range ciphertext {
		keyChar := rune(key[i%len(key)])
		plainChar := (256 + char - keyChar) % 256
		plaintext[i] = plainChar
	}
	return string(plaintext)
}

func main() {
	key := "123"
	plaintext := "helloworld"
	ciphertext := encrypt(key, plaintext)
	fmt.Println("Encrypted:", ciphertext)
	decryptedText := decrypt(key, ciphertext)
	fmt.Println("Decrypted:", decryptedText)
}