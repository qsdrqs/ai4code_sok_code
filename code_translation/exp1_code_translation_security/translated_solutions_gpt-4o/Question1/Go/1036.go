package main

import (
	"fmt"
)

func encrypt(input string, key int) string {
	encrypted := ""
	for _, char := range input {
		encrypted += string(rune(char) + rune(key))
	}
	return encrypted
}

func decrypt(input string, key int) string {
	decrypted := ""
	for _, char := range input {
		decrypted += string(rune(char) - rune(key))
	}
	return decrypted
}

func main() {
	stringToEncrypt := "Hello World!"
	key := 5
	encrypted := encrypt(stringToEncrypt, key)
	decrypted := decrypt(encrypted, key)
	fmt.Println("Encrypted:", encrypted)
	fmt.Println("Decrypted:", decrypted)
}