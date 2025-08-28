package main

import (
	"fmt"
)

// Encrypt encrypts a given string using a provided key.
func Encrypt(key string, str string) string {
	encryptedText := make([]byte, len(str))
	for i := range str {
		keyC := key[i%len(key)]
		encryptedC := byte((int(str[i]) + int(keyC)) % 256)
		encryptedText[i] = encryptedC
	}
	return string(encryptedText)
}

// Decrypt decrypts a given string using a provided key.
func Decrypt(key string, str string) string {
	decryptedText := make([]byte, len(str))
	for i := range str {
		keyC := key[i%len(key)]
		decryptedC := byte((int(str[i]) - int(keyC) + 256) % 256)
		decryptedText[i] = decryptedC
	}
	return string(decryptedText)
}

func main() {
	key := "secretkey"
	str := "Hello, World!"

	encrypted := Encrypt(key, str)
	fmt.Printf("Encrypted: %s\n", encrypted)

	decrypted := Decrypt(key, encrypted)
	fmt.Printf("Decrypted: %s\n", decrypted)
}