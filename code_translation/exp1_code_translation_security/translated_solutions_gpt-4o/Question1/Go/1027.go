package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"fmt"
)

// Pad function to add padding to the message
func pad(data []byte, blockSize int) []byte {
	padding := blockSize - len(data)%blockSize
	padText := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(data, padText...)
}

// Unpad function to remove padding from the message
func unpad(data []byte) []byte {
	length := len(data)
	padding := int(data[length-1])
	return data[:length-padding]
}

// Encrypt function
func encrypt(message string, key []byte) ([]byte, error) {
	// Convert message to bytes
	messageBytes := []byte(message)

	// Pad the message
	messageBytes = pad(messageBytes, aes.BlockSize)

	// Create AES cipher
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	// Create a new CBC mode cipher
	ciphertext := make([]byte, aes.BlockSize+len(messageBytes))
	iv := ciphertext[:aes.BlockSize] // Initialization vector
	mode := cipher.NewCBCEncrypter(block, iv)

	// Encrypt the message
	mode.CryptBlocks(ciphertext[aes.BlockSize:], messageBytes)

	return ciphertext, nil
}

// Decrypt function
func decrypt(ciphertext []byte, key []byte) (string, error) {
	// Create AES cipher
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	// Create a new CBC mode cipher
	iv := ciphertext[:aes.BlockSize] // Initialization vector
	mode := cipher.NewCBCDecrypter(block, iv)

	// Decrypt the message
	messageBytes := make([]byte, len(ciphertext[aes.BlockSize:]))
	mode.CryptBlocks(messageBytes, ciphertext[aes.BlockSize:])

	// Unpad the message
	messageBytes = unpad(messageBytes)

	// Convert message to string
	return string(messageBytes), nil
}

func main() {
	// Example usage
	key := []byte("thisis32bitlongpassphraseimusing") // 32-byte key for AES-256
	message := "Hello, World!"

	// Encrypt the message
	ciphertext, err := encrypt(message, key)
	if err != nil {
		fmt.Println("Error encrypting:", err)
		return
	}
	fmt.Printf("Ciphertext: %x\n", ciphertext)

	// Decrypt the message
	plaintext, err := decrypt(ciphertext, key)
	if err != nil {
		fmt.Println("Error decrypting:", err)
		return
	}
	fmt.Printf("Decrypted message: %s\n", plaintext)
}