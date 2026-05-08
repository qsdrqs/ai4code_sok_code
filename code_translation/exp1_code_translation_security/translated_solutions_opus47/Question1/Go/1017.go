package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"io"
)

// EncryptedData holds the result of encryption, equivalent to Python's (nonce, tag, ciphertext) tuple.
type EncryptedData struct {
	Nonce      []byte
	Tag        []byte
	Ciphertext []byte
}

// Function in Go where one encrypts a given byte slice using a given symmetric key.
// Note: Go's standard library provides AES-GCM, which offers authenticated encryption
// similar to Python's AES EAX mode (both are AEAD modes).
func encrypt(key, plaintext []byte) (*EncryptedData, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	aesgcm, err := cipher.NewGCM(block)
	if err != nil {
		return nil, err
	}

	// Generate a random nonce (similar to PyCryptodome auto-generating one)
	nonce := make([]byte, aesgcm.NonceSize())
	if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
		return nil, err
	}

	// Seal returns the ciphertext with the authentication tag appended at the end
	sealed := aesgcm.Seal(nil, nonce, plaintext, nil)

	// Split the sealed output into ciphertext and tag
	tagSize := aesgcm.Overhead()
	ciphertext := sealed[:len(sealed)-tagSize]
	tag := sealed[len(sealed)-tagSize:]

	return &EncryptedData{
		Nonce:      nonce,
		Tag:        tag,
		Ciphertext: ciphertext,
	}, nil
}

// Function in Go where one decrypts a given EncryptedData using a given symmetric key.
func decrypt(key []byte, data *EncryptedData) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	aesgcm, err := cipher.NewGCM(block)
	if err != nil {
		return nil, err
	}

	// Open expects the tag to be appended to the ciphertext
	combined := make([]byte, 0, len(data.Ciphertext)+len(data.Tag))
	combined = append(combined, data.Ciphertext...)
	combined = append(combined, data.Tag...)

	// This both decrypts and verifies the tag (equivalent to decrypt_and_verify)
	return aesgcm.Open(nil, data.Nonce, combined, nil)
}