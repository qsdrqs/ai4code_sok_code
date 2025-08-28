package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"fmt"
)

func encrypt(data, key, iv []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}
	
	mode := cipher.NewCBCEncrypter(block, iv)
	
	// Create a copy of data since CBC mode modifies the input
	ciphertext := make([]byte, len(data))
	copy(ciphertext, data)
	
	mode.CryptBlocks(ciphertext, ciphertext)
	return ciphertext, nil
}

func decrypt(data, key, iv []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}
	
	mode := cipher.NewCBCDecrypter(block, iv)
	
	// Create a copy of data since CBC mode modifies the input
	plaintext := make([]byte, len(data))
	copy(plaintext, data)
	
	mode.CryptBlocks(plaintext, plaintext)
	return plaintext, nil
}

func test() {
	data := []byte("a secret message")
	
	// Pad data to be multiple of AES block size (16 bytes)
	paddedData := pkcs7Pad(data, aes.BlockSize)
	
	key := make([]byte, 32) // 256-bit key
	if _, err := rand.Read(key); err != nil {
		panic(err)
	}
	
	iv := make([]byte, 16) // 128-bit IV
	if _, err := rand.Read(iv); err != nil {
		panic(err)
	}
	
	e, err := encrypt(paddedData, key, iv)
	if err != nil {
		panic(err)
	}
	
	d, err := decrypt(e, key, iv)
	if err != nil {
		panic(err)
	}
	
	// Remove padding
	unpaddedData := pkcs7Unpad(d)
	
	// Compare original data with decrypted data
	if string(unpaddedData) != string(data) {
		panic("Decrypted data doesn't match original data")
	}
	
	fmt.Println("Test passed!")
}

// PKCS7 padding functions
func pkcs7Pad(data []byte, blockSize int) []byte {
	padding := blockSize - len(data)%blockSize
	padtext := make([]byte, padding)
	for i := range padtext {
		padtext[i] = byte(padding)
	}
	return append(data, padtext...)
}

func pkcs7Unpad(data []byte) []byte {
	length := len(data)
	unpadding := int(data[length-1])
	return data[:(length - unpadding)]
}

func main() {
	test()
}