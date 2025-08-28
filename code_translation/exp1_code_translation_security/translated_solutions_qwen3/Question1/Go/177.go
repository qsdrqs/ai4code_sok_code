package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"io"
)

// encrypt encrypts the given plaintext using AES in CTR mode.
// It returns the ciphertext and the randomly generated nonce.
func encrypt(plaintext, key []byte) ([]byte, []byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, nil, err
	}

	// Generate a 16-byte nonce
	nonce := make([]byte, 16)
	if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
		return nil, nil, err
	}

	// Create a CTR stream cipher
	stream := cipher.NewCTR(block, nonce)

	// Encrypt the plaintext
	ciphertext := make([]byte, len(plaintext))
	stream.XORKeyStream(ciphertext, plaintext)

	return ciphertext, nonce, nil
}

// decrypt decrypts the given ciphertext using AES in CTR mode.
// It requires the ciphertext and the corresponding nonce.
func decrypt(ciphertext, nonce, key []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	// Create a CTR stream cipher
	stream := cipher.NewCTR(block, nonce)

	// Decrypt the ciphertext
	plaintext := make([]byte, len(ciphertext))
	stream.XORKeyStream(plaintext, ciphertext)

	return plaintext, nil
}