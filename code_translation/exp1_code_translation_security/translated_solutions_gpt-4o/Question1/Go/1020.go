package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"encoding/base64"
	"fmt"
	"io"
)

// Pad the plaintext to make its length a multiple of the block size
func pad(data []byte, blockSize int) []byte {
	padding := blockSize - len(data)%blockSize
	padText := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(data, padText...)
}

// Unpad the plaintext after decryption
func unpad(data []byte) []byte {
	length := len(data)
	unpadding := int(data[length-1])
	return data[:(length - unpadding)]
}

// Encrypt the plaintext using AES encryption
func encrypt(key []byte, plaintext string) (string, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	// Generate a random IV
	iv := make([]byte, aes.BlockSize)
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		return "", err
	}

	// Pad the plaintext
	paddedText := pad([]byte(plaintext), aes.BlockSize)

	// Encrypt the plaintext
	ciphertext := make([]byte, len(paddedText))
	mode := cipher.NewCBCEncrypter(block, iv)
	mode.CryptBlocks(ciphertext, paddedText)

	// Prepend the IV to the ciphertext and encode in Base64
	finalCiphertext := append(iv, ciphertext...)
	return base64.StdEncoding.EncodeToString(finalCiphertext), nil
}

// Decrypt the ciphertext using AES decryption
func decrypt(key []byte, ciphertext string) (string, error) {
	// Decode the Base64-encoded ciphertext
	decodedCiphertext, err := base64.StdEncoding.DecodeString(ciphertext)
	if err != nil {
		return "", err
	}

	// Extract the IV and the actual ciphertext
	iv := decodedCiphertext[:aes.BlockSize]
	encryptedText := decodedCiphertext[aes.BlockSize:]

	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	// Decrypt the ciphertext
	decrypted := make([]byte, len(encryptedText))
	mode := cipher.NewCBCDecrypter(block, iv)
	mode.CryptBlocks(decrypted, encryptedText)

	// Unpad the decrypted plaintext
	plaintext := unpad(decrypted)
	return string(plaintext), nil
}

func main() {
	// Example usage
	key := []byte("thisis32bitlongpassphrase!!!") // 32-byte key for AES-256
	plaintext := "Hello, World!"

	// Encrypt the plaintext
	encrypted, err := encrypt(key, plaintext)
	if err != nil {
		fmt.Println("Error encrypting:", err)
		return
	}
	fmt.Println("Encrypted:", encrypted)

	// Decrypt the ciphertext
	decrypted, err := decrypt(key, encrypted)
	if err != nil {
		fmt.Println("Error decrypting:", err)
		return
	}
	fmt.Println("Decrypted:", decrypted)
}