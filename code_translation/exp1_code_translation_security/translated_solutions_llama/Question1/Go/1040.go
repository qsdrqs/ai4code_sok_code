package main

import (
	"fmt"
)

// encrypt a string with a given key
func encrypt(string string, key string) string {
	encrypted := ""
	for i := range string {
		encrypted += string(rune(ord(string[i]) ^ ord(key[i%len(key)])))
	}
	return encrypted
}

// decrypt a string with a given key
func decrypt(string string, key string) string {
	return encrypt(string, key)
}

func ord(c byte) byte {
	return c
}

func main() {
	originalString := "sadasdasdsa"
	key := "1234"
	encrypted := encrypt(originalString, key)
	fmt.Printf("Encrypted string: %s\n", encrypted)

	decrypted := decrypt(encrypted, key)
	fmt.Printf("Decrypted string: %s\n", decrypted)

	if decrypted == originalString {
		fmt.Println("Decryption successful. Original string matches decrypted string.")
	} else {
		fmt.Println("Decryption failed.")
	}
}