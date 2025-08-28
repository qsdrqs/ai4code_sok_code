package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"errors"
	"fmt"
	"log"
)

// pkcs7Pad appends PKCS#7 padding to a plaintext.
// This is necessary because block ciphers like AES operate on fixed-size blocks.
func pkcs7Pad(plaintext []byte, blockSize int) ([]byte, error) {
	if blockSize <= 0 || blockSize > 255 {
		return nil, fmt.Errorf("invalid block size: %d", blockSize)
	}
	padding := blockSize - len(plaintext)%blockSize
	padtext := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(plaintext, padtext...), nil
}

// pkcs7Unpad removes PKCS#7 padding from a plaintext.
func pkcs7Unpad(paddedData []byte, blockSize int) ([]byte, error) {
	length := len(paddedData)
	if length == 0 {
		return nil, errors.New("padded data cannot be empty")
	}
	if length%blockSize != 0 {
		return nil, errors.New("padded data is not a multiple of the block size")
	}

	padLen := int(paddedData[length-1])
	if padLen > length || padLen > blockSize {
		return nil, errors.New("invalid padding length")
	}

	return paddedData[:length-padLen], nil
}

// encrypt performs AES-256 CBC encryption.
// It pads the data using PKCS#7 before encryption.
func encrypt(data, key, iv []byte) ([]byte, error) {
	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, fmt.Errorf("could not create new cipher: %w", err)
	}

	// Pad the data to a multiple of the block size.
	paddedData, err := pkcs7Pad(data, aes.BlockSize)
	if err != nil {
		return nil, fmt.Errorf("could not pad data: %w", err)
	}

	// The IV needs to be the same size as the block.
	if len(iv) != aes.BlockSize {
		return nil, fmt.Errorf("IV length must equal block size")
	}

	// Create a new CBC encrypter.
	mode := cipher.NewCBCEncrypter(block, iv)

	// Create a buffer for the ciphertext.
	ciphertext := make([]byte, len(paddedData))

	// Encrypt the data.
	mode.CryptBlocks(ciphertext, paddedData)

	return ciphertext, nil
}

// decrypt performs AES-256 CBC decryption.
// It removes PKCS#7 padding after decryption.
func decrypt(data, key, iv []byte) ([]byte, error) {
	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, fmt.Errorf("could not create new cipher: %w", err)
	}

	// The IV needs to be the same size as the block.
	if len(iv) != aes.BlockSize {
		return nil, fmt.Errorf("IV length must equal block size")
	}

	// The ciphertext must be a multiple of the block size.
	if len(data)%aes.BlockSize != 0 {
		return nil, errors.New("ciphertext is not a multiple of the block size")
	}

	// Create a new CBC decrypter.
	mode := cipher.NewCBCDecrypter(block, iv)

	// Create a buffer for the plaintext.
	plaintext := make([]byte, len(data))

	// Decrypt the data.
	mode.CryptBlocks(plaintext, data)

	// Unpad the decrypted data.
	unpaddedData, err := pkcs7Unpad(plaintext, aes.BlockSize)
	if err != nil {
		return nil, fmt.Errorf("could not unpad data: %w", err)
	}

	return unpaddedData, nil
}

// test validates the encryption and decryption functions.
func test() {
	data := []byte("a secret message")

	// Generate a 32-byte key for AES-256.
	key := make([]byte, 32)
	if _, err := rand.Read(key); err != nil {
		log.Fatalf("Failed to generate random key: %v", err)
	}

	// Generate a 16-byte IV (Initialization Vector). AES block size is 16 bytes.
	iv := make([]byte, aes.BlockSize)
	if _, err := rand.Read(iv); err != nil {
		log.Fatalf("Failed to generate random IV: %v", err)
	}

	fmt.Printf("Original Data:  \"%s\"\n", string(data))

	// Encrypt the data
	e, err := encrypt(data, key, iv)
	if err != nil {
		log.Fatalf("Encryption failed: %v", err)
	}
	fmt.Printf("Encrypted (hex): %x\n", e)

	// Decrypt the data
	d, err := decrypt(e, key, iv)
	if err != nil {
		log.Fatalf("Decryption failed: %v", err)
	}
	fmt.Printf("Decrypted Data: \"%s\"\n", string(d))

	// Assert that the decrypted data matches the original data
	if !bytes.Equal(d, data) {
		log.Fatal("Assertion failed: decrypted data does not match original data!")
	}

	fmt.Println("\nTest passed successfully!")
}

func main() {
	test()
}