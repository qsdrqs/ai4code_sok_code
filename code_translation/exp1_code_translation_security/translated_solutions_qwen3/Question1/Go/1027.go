package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"errors"
	"fmt"
	"io"
	"math/rand"
)

// pad adds PKCS#7 padding to the data to match the block size.
func pad(data []byte, blockSize int) []byte {
	padding := blockSize - (len(data) % blockSize)
	padText := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(data, padText...)
}

// unpad removes PKCS#7 padding from the data.
func unpad(data []byte, blockSize int) ([]byte, error) {
	if len(data) == 0 {
		return nil, errors.New("empty data")
	}
	padding := int(data[len(data)-1])
	if padding == 0 || padding > blockSize || len(data) < padding {
		return nil, errors.New("invalid padding")
	}
	for i := len(data) - padding; i < len(data); i++ {
		if data[i] != byte(padding) {
			return nil, errors.New("invalid padding bytes")
		}
	}
	return data[:len(data)-padding], nil
}

// encrypt encrypts a message using AES in CBC mode.
// It returns the IV + ciphertext.
func encrypt(message string, key []byte) ([]byte, error) {
	plainText := []byte(message)
	padded := pad(plainText, aes.BlockSize)

	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	iv := make([]byte, aes.BlockSize)
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		return nil, err
	}

	cipherText := make([]byte, len(padded))
	mode := cipher.NewCBCEncrypter(block, iv)
	mode.CryptBlocks(cipherText, padded)

	// Prepend IV to the ciphertext
	return append(iv, cipherText...), nil
}

// decrypt decrypts a message using AES in CBC mode.
// It expects the IV to be the first block of the ciphertext.
func decrypt(cipherText []byte, key []byte) (string, error) {
	if len(cipherText) < aes.BlockSize {
		return "", errors.New("ciphertext too short")
	}

	iv := cipherText[:aes.BlockSize]
	cipherText = cipherText[aes.BlockSize:]

	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	padded := make([]byte, len(cipherText))
	mode := cipher.NewCBCDecrypter(block, iv)
	mode.CryptBlocks(padded, cipherText)

	plainText, err := unpad(padded, aes.BlockSize)
	if err != nil {
		return "", err
	}

	return string(plainText), nil
}

// Example usage
func main() {
	key := []byte("a very very very secret key!!") // 32 bytes for AES-256
	message := "Hello, world!"

	encrypted, err := encrypt(message, key)
	if err != nil {
		fmt.Println("Encryption error:", err)
		return
	}

	decrypted, err := decrypt(encrypted, key)
	if err != nil {
		fmt.Println("Decryption error:", err)
		return
	}

	fmt.Println("Decrypted message:", decrypted)
}