package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"crypto/sha256"
	"encoding/base64"
	"errors"
	"fmt"
	"io"
)

// PKCS7Padding pads the data to be a multiple of block size using PKCS#7.
func pkcs7Padding(data []byte, blockSize int) []byte {
	padding := blockSize - len(data)%blockSize
	padText := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(data, padText...)
}

// PKCS7Unpadding removes PKCS#7 padding from the data.
func pkcs7Unpadding(data []byte) ([]byte, error) {
	if len(data) == 0 {
		return nil, errors.New("empty data")
	}
	padding := int(data[len(data)-1])
	if padding > len(data) || padding == 0 {
		return nil, errors.New("invalid padding")
	}
	for i := len(data) - padding; i < len(data); i++ {
		if data[i] != byte(padding) {
			return nil, errors.New("invalid padding")
		}
	}
	return data[:len(data)-padding], nil
}

// encode_str encrypts the plaintext using AES-256-CBC and returns the base64-encoded string.
func encode_str(plainText, key string) (string, error) {
	// Derive a 32-byte key using SHA-256
	keyHash := sha256.Sum256([]byte(key))

	// Create AES cipher
	block, err := aes.NewCipher(keyHash[:])
	if err != nil {
		return "", err
	}

	// Pad plaintext
	blockSize := block.BlockSize()
	plaintextPadded := pkcs7Padding([]byte(plainText), blockSize)

	// Generate random IV
	iv := make([]byte, blockSize)
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		return "", err
	}

	// Encrypt
	ciphertext := make([]byte, len(plaintextPadded))
	mode := cipher.NewCBCEncrypter(block, iv)
	mode.CryptBlocks(ciphertext, plaintextPadded)

	// Prepend IV to ciphertext
	full := append(iv, ciphertext...)

	// Base64 encode
	return base64.StdEncoding.EncodeToString(full), nil
}

// decode_str decrypts the base64-encoded string using AES-256-CBC.
func decode_str(strEncoded, key string) (string, error) {
	// Base64 decode
	full, err := base64.StdEncoding.DecodeString(strEncoded)
	if err != nil {
		return "", err
	}

	// Derive a 32-byte key using SHA-256
	keyHash := sha256.Sum256([]byte(key))

	// Create AES cipher
	block, err := aes.NewCipher(keyHash[:])
	if err != nil {
		return "", err
	}

	blockSize := block.BlockSize()
	if len(full) < blockSize {
		return "", errors.New("data too short")
	}

	// Extract IV and ciphertext
	iv := full[:blockSize]
	ciphertext := full[blockSize:]

	// Decrypt
	plaintextPadded := make([]byte, len(ciphertext))
	mode := cipher.NewCBCDecrypter(block, iv)
	mode.CryptBlocks(plaintextPadded, ciphertext)

	// Remove padding
	plaintext, err := pkcs7Unpadding(plaintextPadded)
	if err != nil {
		return "", err
	}

	return string(plaintext), nil
}

func main() {
	str_encoded1, _ := encode_str("I am okay", "wow")
	decrypted, _ := decode_str(str_encoded1, "wow")
	fmt.Println(decrypted)
}