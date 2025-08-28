package main

import (
	"fmt"
)

// Encrypt encrypts a given string using a symmetric key (simple XOR).
func Encrypt(text, key string) string {
	if len(key) == 0 {
		return text // empty key → nothing to do
	}

	in  := []byte(text) // plaintext to bytes
	keyb := []byte(key) // key to bytes

	out := make([]byte, len(in))
	for i, b := range in {
		out[i] = b ^ keyb[i%len(keyb)]
	}
	return string(out)
}

// Decrypt decrypts a given string using the same symmetric key.
// With XOR, encryption and decryption are identical operations.
func Decrypt(cipher, key string) string {
	return Encrypt(cipher, key)
}

func main() {
	plain := "Hello, XOR!"
	key   := "secret"

	cipher := Encrypt(plain, key)
	restored := Decrypt(cipher, key)

	fmt.Printf("Plain text : %q\n", plain)
	fmt.Printf("Cipher text: %q\n", cipher)
	fmt.Printf("Restored   : %q\n", restored)
}