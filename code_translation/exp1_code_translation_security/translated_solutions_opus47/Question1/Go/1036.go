package main

import "fmt"

// encrypt encrypts a string using a given key.
func encrypt(s string, key int) string {
	encrypted := ""
	for _, c := range s {
		encrypted += string(c + rune(key))
	}
	return encrypted
}

// decrypt decrypts a string using a given key.
func decrypt(s string, key int) string {
	decrypted := ""
	for _, c := range s {
		decrypted += string(c - rune(key))
	}
	return decrypted
}

// main is the main function.
func main() {
	s := "Hello World!"
	key := 5
	encrypted := encrypt(s, key)
	decrypted := decrypt(encrypted, key)
	fmt.Println("Encrypted:", encrypted)
	fmt.Println("Decrypted:", decrypted)
}