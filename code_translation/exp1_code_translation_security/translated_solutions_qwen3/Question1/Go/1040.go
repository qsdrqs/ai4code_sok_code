package main

import (
	"fmt"
)

// encrypt performs XOR encryption of a string using a repeating key.
func encrypt(s, key string) string {
	keyRunes := []rune(key)
	sRunes := []rune(s)
	encrypted := make([]rune, len(sRunes))

	for i := range sRunes {
		encrypted[i] = sRunes[i] ^ keyRunes[i%len(keyRunes)]
	}

	return string(encrypted)
}

// decrypt is the same as encrypt, since XOR is its own inverse.
func decrypt(s, key string) string {
	return encrypt(s, key)
}

// main runs a test to verify that decrypt(encrypt(s, key), key) == s
func main() {
	original := "sadasdasdsa"
	key := "1234"

	encrypted := encrypt(original, key)
	decrypted := decrypt(encrypted, key)

	if decrypted != original {
		panic(fmt.Sprintf("Test failed: expected %q, got %q", original, decrypted))
	}

	fmt.Println("Test passed: encryption and decryption are symmetric.")
}