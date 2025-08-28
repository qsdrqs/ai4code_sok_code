package main

import (
	"errors"
	"fmt"
	"strings"
)

// Encrypt encrypts a given string using a Caesar cipher with a provided key.
func Encrypt(key int, str string) (string, error) {
	if key < 0 {
		return "", errors.New("key must be a non-negative integer")
	}
	if key > 26 {
		key = key % 26
	}
	if len(str) == 0 {
		return "", errors.New("input string cannot be empty")
	}

	var newString string
	for _, char := range str {
		if strings.ContainsRune("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ", char) {
			if char >= 'a' && char <= 'z' {
				newString += string(rune(((int(char) - 97 + key) % 26) + 97))
			} else {
				newString += string(rune(((int(char) - 65 + key) % 26) + 65))
			}
		} else {
			newString += string(char)
		}
	}
	return newString, nil
}

// Decrypt decrypts a given string using a symmetric key (Vigenère cipher).
func Decrypt(ciphertext string, key string) (string, error) {
	if len(ciphertext) == 0 || len(key) == 0 {
		return "", errors.New("input string and key cannot be empty")
	}
	if len(key) > len(ciphertext) {
		return "", errors.New("key length cannot be greater than ciphertext length")
	}

	var plaintext string
	keyLen := len(key)
	for i, char := range ciphertext {
		if char >= 'A' && char <= 'Z' {
			keyChar := key[i%keyLen]
			if keyChar >= 'a' && keyChar <= 'z' {
				keyChar = keyChar - 32
			}
			value := (int(char) - int(keyChar) + 26) % 26
			plaintext += string(rune(value + 65))
		} else {
			plaintext += string(char)
		}
	}
	return plaintext, nil
}

func main() {
	// Example usage:
	key := 3
	str := "Hello, World!"
	encrypted, err := Encrypt(key, str)
	if err != nil {
		fmt.Println(err)
		return
	}
	fmt.Printf("Encrypted: %s\n", encrypted)

	decrypted, err := Decrypt(encrypted, "test") // Note: Decrypt function expects a key of the same length as ciphertext or a repeating key
	if err != nil {
		fmt.Println(err)
		return
	}
	fmt.Printf("Decrypted: %s\n", decrypted)
}