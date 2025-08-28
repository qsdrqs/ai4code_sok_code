package main

import (
	"fmt"
)

func encrypt(plaintext, key string) string {
	ciphertext := ""
	for i := 0; i < len(plaintext); i++ {
		c := (int(plaintext[i]) + int(key[i%len(key)]) - 2*int('a')) % 26 + int('a')
		ciphertext += string(c)
	}
	return ciphertext
}

func decrypt(ciphertext, key string) string {
	plaintext := ""
	for i := 0; i < len(ciphertext); i++ {
		p := (int(ciphertext[i]) - int(key[i%len(key)]) + 26) % 26 + int('a')
		plaintext += string(p)
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