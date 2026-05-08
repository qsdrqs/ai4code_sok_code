package main

import (
	"fmt"
	"strings"
)

func encrypt(key int, s string) *string {
	if key < 0 {
		return nil
	}
	if len(s) == 0 {
		return nil
	}
	if key > 26 {
		key = key % 26
	}
	var newString strings.Builder
	for _, i := range s {
		if i >= 'a' && i <= 'z' {
			newString.WriteRune(rune((int(i)-97+key)%26 + 97))
		} else if i >= 'A' && i <= 'Z' {
			newString.WriteRune(rune((int(i)-65+key)%26 + 65))
		} else {
			newString.WriteRune(i)
		}
	}
	result := newString.String()
	return &result
}

// decrypt is a function that decrypts a given string using a symmetric key
func decrypt(ciphertext, key string) *string {
	if len(ciphertext) == 0 || len(key) == 0 {
		return nil
	}
	if len(key) == 1 {
		return &ciphertext
	}
	if len(key) > len(ciphertext) {
		return nil
	}
	var keyAscii []int
	for _, i := range key {
		keyAscii = append(keyAscii, int(i))
	}
	var ciphertextAscii []int
	for _, i := range ciphertext {
		ciphertextAscii = append(ciphertextAscii, int(i))
	}
	keyLen := len(keyAscii)
	ciphertextLen := len(ciphertextAscii)
	var plaintext strings.Builder
	for i := 0; i < ciphertextLen; i++ {
		value := (ciphertextAscii[i] - keyAscii[i%keyLen]) % 26
		// Go's modulo can return negative values for negative operands,
		// unlike Python which always returns a non-negative result
		if value < 0 {
			value += 26
		}
		plaintext.WriteRune(rune(value + 65))
	}
	result := plaintext.String()
	return &result
}

func main() {
	result := encrypt(3, "Hello")
	if result != nil {
		fmt.Println(*result)
	}

	result2 := decrypt("KHOOR", "ABC")
	if result2 != nil {
		fmt.Println(*result2)
	}
}