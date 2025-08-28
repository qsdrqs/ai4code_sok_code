package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"errors"
	"fmt"
)

// PKCS7 padding for AES block size (16 bytes)
func pkcs7Pad(data []byte, blockSize int) []byte {
	padding := blockSize - (len(data) % blockSize)
	padText := make([]byte, padding)
	for i := range padText {
		padText[i] = byte(padding)
	}
	return append(data, padText...)
}

// PKCS7 unpadding
func pkcs7Unpad(data []byte, blockSize int) ([]byte, error) {
	if len(data) == 0 {
		return nil, errors.New("data is empty")
	}
	padding := int(data[len(data)-1])
	if padding == 0 || padding > blockSize {
		return nil, errors.New("invalid padding")
	}
	return data[:len(data)-padding], nil
}

// Encrypt data using AES-CBC with PKCS7 padding
func encrypt(data, key, iv []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}
	if len(iv) != block.BlockSize() {
		return nil, errors.New("invalid IV length")
	}

	paddedData := pkcs7Pad(data, block.BlockSize())
	ciphertext := make([]byte, len(paddedData))

	mode := cipher.NewCBCEncrypter(block, iv)
	mode.CryptBlocks(ciphertext, paddedData)

	return ciphertext, nil
}

// Decrypt data using AES-CBC and remove PKCS7 padding
func decrypt(data, key, iv []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}
	if len(iv) != block.BlockSize() {
		return nil, errors.New("invalid IV length")
	}
	if len(data)%block.BlockSize() != 0 {
		return nil, errors.New("data length is not a multiple of block size")
	}

	dst := make([]byte, len(data))
	mode := cipher.NewCBCDecrypter(block, iv)
	mode.CryptBlocks(dst, data)

	return pkcs7Unpad(dst, block.BlockSize())
}

// Test function to validate encryption and decryption
func test() {
	data := []byte("a secret message")
	key := make([]byte, 32) // 256-bit key
	iv := make([]byte, 16)  // 128-bit IV

	// Generate random key and IV
	rand.Read(key)
	rand.Read(iv)

	// Encrypt
	encrypted, err := encrypt(data, key, iv)
	if err != nil {
		panic(err)
	}

	// Decrypt
	decrypted, err := decrypt(encrypted, key, iv)
	if err != nil {
		panic(err)
	}

	// Validate
	if string(decrypted) != string(data) {
		panic("Decrypted data does not match original")
	} else {
		fmt.Println("✅ Encryption and decryption successful.")
	}
}

func main() {
	test()
}