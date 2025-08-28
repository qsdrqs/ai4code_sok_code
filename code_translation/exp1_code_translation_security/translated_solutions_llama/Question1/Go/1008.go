package main

import (
	"fmt"
)

// encrypt encrypts the given plaintext with the provided key.
func encrypt(key string, plaintext string) string {
	ciphertext := make([]byte, 0, len(plaintext))
	for i := range plaintext {
		keyC := key[i%len(key)]
		cipherC := byte((uint(plaintext[i]) + uint(keyC)) % 256)
		ciphertext = append(ciphertext, cipherC)
	}
	return string(ciphertext)
}

// decrypt decrypts the given ciphertext with the provided key.
func decrypt(key string, ciphertext string) string {
	plaintext := make([]byte, 0, len(ciphertext))
	for i := range ciphertext {
		keyC := key[i%len(key)]
		plainC := byte((256 + uint(ciphertext[i]) - uint(keyC)) % 256)
		plaintext = append(plaintext, plainC)
	}
	return string(plaintext)
}

func main() {
	key := "123"
	plaintext := "helloworld"
	ciphertext := encrypt(key, plaintext)
	fmt.Printf("Encrypted: %s\n", ciphertext)
	decrypted := decrypt(key, ciphertext)
	fmt.Printf("Decrypted: %s\n", decrypted)
}