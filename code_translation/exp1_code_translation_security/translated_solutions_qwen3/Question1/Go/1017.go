package main

import (
	"crypto/rand"
	"github.com/mikelsr/aes-eax"
)

// Encrypt encrypts the given plaintext using AES in EAX mode with the provided key.
// It returns the nonce, authentication tag, and ciphertext, or an error if any step fails.
func Encrypt(key, plaintext []byte) ([]byte, []byte, []byte, error) {
	eax, err := aes_eax.NewEAX(key)
	if err != nil {
		return nil, nil, nil, err
	}

	nonce := make([]byte, 16)
	if _, err := rand.Read(nonce); err != nil {
		return nil, nil, nil, err
	}

	ciphertext, tag, err := eax.Encrypt(nonce, plaintext, nil)
	if err != nil {
		return nil, nil, nil, err
	}

	return nonce, tag, ciphertext, nil
}

// Decrypt decrypts the given ciphertext using AES in EAX mode with the provided key.
// It verifies the authentication tag and returns the plaintext, or an error if verification fails.
func Decrypt(key []byte, nonce, ciphertext, tag []byte) ([]byte, error) {
	eax, err := aes_eax.NewEAX(key)
	if err != nil {
		return nil, err
	}

	plaintext, err := eax.Decrypt(nonce, ciphertext, tag, nil)
	if err != nil {
		return nil, err
	}

	return plaintext, nil
}