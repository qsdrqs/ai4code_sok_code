package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"fmt"
)

type AEScipher struct {
	key    []byte
	blk_sz int
}

// NewAEScipher initializes a new AEScipher instance
func NewAEScipher(key []byte, blk_sz int) *AEScipher {
	return &AEScipher{
		key:    key,
		blk_sz: blk_sz,
	}
}

// Encrypt encrypts the given message with AES in ECB mode
func (a *AEScipher) Encrypt(msg string) ([]byte, error) {
	// Padding
	padLen := a.blk_sz - (len(msg) % a.blk_sz)
	padding := bytes.Repeat([]byte{byte(padLen)}, padLen)
	paddedMsg := append([]byte(msg), padding...)

	// Encryption
	block, err := aes.NewCipher(a.key)
	if err != nil {
		return nil, err
	}

	if len(paddedMsg)%block.BlockSize() != 0 {
		return nil, fmt.Errorf("message is not a multiple of the block size")
	}

	ciphertext := make([]byte, len(paddedMsg))
	for i := 0; i < len(paddedMsg); i += block.BlockSize() {
		block.Encrypt(ciphertext[i:i+block.BlockSize()], paddedMsg[i:i+block.BlockSize()])
	}

	return ciphertext, nil
}

// Decrypt decrypts the given ciphertext with AES in ECB mode
func (a *AEScipher) Decrypt(ciphertext []byte) (string, error) {
	// Decryption
	block, err := aes.NewCipher(a.key)
	if err != nil {
		return "", err
	}

	if len(ciphertext)%block.BlockSize() != 0 {
		return "", fmt.Errorf("ciphertext is not a multiple of the block size")
	}

	decrypted := make([]byte, len(ciphertext))
	for i := 0; i < len(ciphertext); i += block.BlockSize() {
		block.Decrypt(decrypted[i:i+block.BlockSize()], ciphertext[i:i+block.BlockSize()])
	}

	// Remove padding
	padLen := int(decrypted[len(decrypted)-1])
	if padLen > len(decrypted) {
		return "", fmt.Errorf("invalid padding")
	}

	return string(decrypted[:len(decrypted)-padLen]), nil
}

func main() {
	// Example usage
	key := []byte("examplekey123456") // 16 bytes key for AES-128
	blk_sz := 16                     // Block size for AES

	aesCipher := NewAEScipher(key, blk_sz)

	// Encrypt
	msg := "Hello, World!"
	ciphertext, err := aesCipher.Encrypt(msg)
	if err != nil {
		fmt.Println("Error encrypting:", err)
		return
	}
	fmt.Printf("Ciphertext: %x\n", ciphertext)

	// Decrypt
	plaintext, err := aesCipher.Decrypt(ciphertext)
	if err != nil {
		fmt.Println("Error decrypting:", err)
		return
	}
	fmt.Printf("Decrypted message: %s\n", plaintext)
}