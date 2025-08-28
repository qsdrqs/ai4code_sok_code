package main

import (
	"fmt"
)

func encrypt(key, text string) string {
	encryptedText := make([]rune, len(text))
	for i, char := range text {
		keyChar := rune(key[i%len(key)])
		encryptedChar := (char + keyChar) % 256
		encryptedText[i] = encryptedChar
	}
	return string(encryptedText)
}

func decrypt(key, text string) string {
	decryptedText := make([]rune, len(text))
	for i, char := range text {
		keyChar := rune(key[i%len(key)])
		decryptedChar := (char - keyChar + 256) % 256
		decryptedText[i] = decryptedChar
	}
	return string(decryptedText)
}

func main() {
	key := "mysecretkey"
	originalText := "Hello, World!"
	
	encrypted := encrypt(key, originalText)
	fmt.Println("Encrypted:", encrypted)
	
	decrypted := decrypt(key, encrypted)
	fmt.Println("Decrypted:", decrypted)
}