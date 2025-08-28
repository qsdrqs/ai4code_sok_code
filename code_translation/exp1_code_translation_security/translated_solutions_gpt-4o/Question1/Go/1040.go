package main

import (
	"fmt"
)

func encrypt(input string, key string) string {
	encrypted := make([]rune, len(input))
	keyLength := len(key)

	for i, char := range input {
		encrypted[i] = rune(char) ^ rune(key[i%keyLength])
	}

	return string(encrypted)
}

func decrypt(input string, key string) string {
	return encrypt(input, key)
}

func main() {
	original := "sadasdasdsa"
	key := "1234"

	encrypted := encrypt(original, key)
	decrypted := decrypt(encrypted, key)

	fmt.Println("Original:", original)
	fmt.Println("Encrypted:", encrypted)
	fmt.Println("Decrypted:", decrypted)

	if decrypted == original {
		fmt.Println("Decryption successful!")
	} else {
		fmt.Println("Decryption failed.")
	}
}