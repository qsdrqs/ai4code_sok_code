package main

import "fmt"

// Encrypt
//
// Given a message and a key, encrypt the message using XOR encryption.
func encrypt(message, key string) string {
	encrypted := make([]byte, len(message))
	for i := 0; i < len(message); i++ {
		encrypted[i] = message[i] ^ key[i%len(key)]
	}
	return string(encrypted)
}

// Decrypt
//
// Given an encrypted message and a key, decrypt the message using XOR encryption.
func decrypt(encrypted, key string) string {
	return encrypt(encrypted, key)
}

func main() {
	fmt.Println(decrypt(encrypt("Hello world", "key"), "key"))
}