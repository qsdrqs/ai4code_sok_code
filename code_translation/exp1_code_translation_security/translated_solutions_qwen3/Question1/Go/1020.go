package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"encoding/base64"
	"errors"
)

// pad adds PKCS#7 padding to the data to make it a multiple of the AES block size (16 bytes).
func pad(data []byte) []byte {
	padding := aes.BlockSize - (len(data) % aes.BlockSize)
	pad := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(data, pad...)
}

// unpad removes PKCS#7 padding from the data.
// Returns an error if the padding is invalid.
func unpad(data []byte) ([]byte, error) {
	if len(data) == 0 {
		return nil, errors.New("empty data")
	}
	padding := int(data[len(data)-1])
	if padding > aes.BlockSize || padding == 0 {
		return nil, errors.New("invalid padding")
	}
	pad := data[len(data)-padding:]
	for _, v := range pad {
		if v != byte(padding) {
			return nil, errors.New("invalid padding bytes")
		}
	}
	return data[:len(data)-padding], nil
}

// Encrypt encrypts the given plaintext using AES-CBC with the provided key.
// keySize is included for compatibility but not used.
func Encrypt(key []byte, keySize int, plaintext string) (string, error) {
	_ = keySize // suppress unused parameter warning

	// Generate a random 16-byte IV
	iv := make([]byte, aes.BlockSize)
	if _, err := rand.Read(iv); err != nil {
		return "", err
	}

	// Pad the plaintext
	padded := pad([]byte(plaintext))

	// Create AES cipher
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	// Encrypt the padded data
	ciphertext := make([]byte, len(padded))
	mode := cipher.NewCBCEncrypter(block, iv)
	mode.CryptBlocks(ciphertext, padded)

	// Prepend IV and base64 encode
	final := append(iv, ciphertext...)
	return base64.StdEncoding.EncodeToString(final), nil
}

// Decrypt decrypts the given base64-encoded ciphertext using AES-CBC with the provided key.
// keySize is included for compatibility but not used.
func Decrypt(key []byte, keySize int, ciphertext string) (string, error) {
	_ = keySize // suppress unused parameter warning

	// Base64 decode
	data, err := base64.StdEncoding.DecodeString(ciphertext)
	if err != nil {
		return "", err
	}

	// Extract IV and ciphertext
	if len(data) < aes.BlockSize {
		return "", errors.New("ciphertext too short")
	}
	iv := data[:aes.BlockSize]
	ciphertextBytes := data[aes.BlockSize:]

	// Create AES cipher
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	// Check that ciphertext length is a multiple of block size
	if len(ciphertextBytes)%aes.BlockSize != 0 {
		return "", errors.New("ciphertext length not multiple of block size")
	}

	// Decrypt
	plaintext := make([]byte, len(ciphertextBytes))
	mode := cipher.NewCBCDecrypter(block, iv)
	mode.CryptBlocks(plaintext, ciphertextBytes)

	// Unpad and return as string
	unpadded, err := unpad(plaintext)
	if err != nil {
		return "", err
	}
	return string(unpadded), nil
}