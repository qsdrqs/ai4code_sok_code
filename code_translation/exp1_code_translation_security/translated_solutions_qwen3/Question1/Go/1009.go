package main

import (
	"crypto/rand"
	"fmt"
	"github.com/mikelsr/aes-eax/eax"
)

// encodeString encrypts the message using AES in EAX mode and returns the ciphertext and tag.
func encodeString(message string, key, nonce []byte) ([]byte, []byte) {
	c, err := eax.NewCipher(key)
	if err != nil {
		panic(err)
	}

	plaintext := []byte(message)
	ciphertextWithTag := c.Seal(nil, nonce, plaintext, nil)

	// Split ciphertext and tag
	ciphertext := ciphertextWithTag[:len(plaintext)]
	tag := ciphertextWithTag[len(plaintext):]

	return ciphertext, tag
}

// decodeString decrypts and verifies the message using the ciphertext and tag.
func decodeString(ciphertext, tag, key, nonce []byte) string {
	c, err := eax.NewCipher(key)
	if err != nil {
		panic(err)
	}

	// Recombine ciphertext and tag
	ciphertextWithTag := append([]byte{}, append(ciphertext, tag...)...)

	plaintext, err := c.Open(nil, nonce, ciphertextWithTag, nil)
	if err != nil {
		panic(err)
	}

	return string(plaintext)
}

func main() {
	originalMessage := "secret data"

	// Generate 16-byte key and nonce
	key := make([]byte, 16)
	nonce := make([]byte, 16)
	if _, err := rand.Read(key); err != nil {
		panic(err)
	}
	if _, err := rand.Read(nonce); err != nil {
		panic(err)
	}

	fmt.Println("Original Message:", originalMessage)

	// Encrypt
	ciphertext, tag := encodeString(originalMessage, key, nonce)

	// Decrypt
	decodedString := decodeString(ciphertext, tag, key, nonce)
	fmt.Println("Decoded Message:", decodedString)
}