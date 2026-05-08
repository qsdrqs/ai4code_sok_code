package main

import "fmt"

func encrypt(plaintext, key string) string {
	ciphertext := ""
	for i := 0; i < len(plaintext); i++ {
		ciphertext += string(rune((int(plaintext[i])+int(key[i%len(key)])-2*int('a'))%26 + int('a')))
	}
	return ciphertext
}

func decrypt(ciphertext, key string) string {
	plaintext := ""
	for i := 0; i < len(ciphertext); i++ {
		plaintext += string(rune((int(ciphertext[i])-int(key[i%len(key)])+26)%26 + int('a')))
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
		panic("assertion failed: plaintext != decrypted")
	}
}