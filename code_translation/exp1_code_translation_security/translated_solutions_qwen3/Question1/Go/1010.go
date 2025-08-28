package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"fmt"
	"io"
)

// generateKey generates a 16-byte (128-bit) symmetric key.
func generateKey() ([]byte, error) {
	key := make([]byte, 16)
	if _, err := io.ReadFull(rand.Reader, key); err != nil {
		return nil, err
	}
	return key, nil
}

// encrypt encrypts the given data using AES in CTR mode.
func encrypt(key []byte, data string) ([]byte, []byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, nil, err
	}

	nonce := make([]byte, 16)
	if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
		return nil, nil, err
	}

	ciphertext := make([]byte, len(data))
	stream := cipher.NewCTR(block, nonce)
	stream.XORKeyStream(ciphertext, []byte(data))

	return ciphertext, nonce, nil
}

// decrypt decrypts the given ciphertext using AES in CTR mode.
func decrypt(key, nonce, ciphertext []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	plaintext := make([]byte, len(ciphertext))
	stream := cipher.NewCTR(block, nonce)
	stream.XORKeyStream(plaintext, ciphertext)

	return plaintext, nil
}

// Example usage
func main() {
	key, err := generateKey()
	if err != nil {
		panic(err)
	}

	testData := "Jim's test"
	encrypted, nonce, err := encrypt(key, testData)
	if err != nil {
		panic(err)
	}

	restoredData, err := decrypt(key, nonce, encrypted)
	if err != nil {
		panic(err)
	}

	fmt.Println(string(restoredData)) // Output: Jim's test
}