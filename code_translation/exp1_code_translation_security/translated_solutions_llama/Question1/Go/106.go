package main

import (
	"fmt"
)

// encrypt encrypts the given plaintext using the provided key.
func encrypt(plaintext, key string) string {
	ciphertext := ""
	for i, char := range plaintext {
		ciphertext += string(rune(((int(char) + int(rune(key[i%len(key)])) - 2*int('a')) % 26) + int('a')))
	}
	return ciphertext
}

// decrypt decrypts the given ciphertext using the provided key.
func decrypt(ciphertext, key string) string {
	plaintext := ""
	for i, char := range ciphertext {
		plaintext += string(rune(((int(char) - int(rune(key[i%len(key)])) + 26) % 26) + int('a')))
	}
	return plaintext
}

func main() {
	plaintext := "helloworld"
	key := "python"
	ciphertext := encrypt(plaintext, key)
	fmt.Println("Ciphertext: " + ciphertext)
	decrypted := decrypt(ciphertext, key)
	fmt.Println(decrypted)
	if plaintext != decrypted {
		fmt.Println("Decryption failed")
	} else {
		fmt.Println("Decryption successful")
	}
}