package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"io"
)

// pad pads the data using PKCS7 padding (equivalent to pycryptodome's pad)
func pad(data []byte, blockSize int) []byte {
	padding := blockSize - len(data)%blockSize
	padText := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(data, padText...)
}

// unpad removes PKCS7 padding (equivalent to pycryptodome's unpad)
func unpad(data []byte, blockSize int) []byte {
	length := len(data)
	unpadding := int(data[length-1])
	return data[:length-unpadding]
}

func encrypt(message string, key []byte) ([]byte, error) {
	// convert message to bytes
	messageBytes := []byte(message)
	// pad message
	messageBytes = pad(messageBytes, aes.BlockSize)
	// create cipher
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}
	// generate random IV (matches pycryptodome's default behavior when no IV is provided)
	iv := make([]byte, aes.BlockSize)
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		return nil, err
	}
	// encrypt message
	mode := cipher.NewCBCEncrypter(block, iv)
	ciphertext := make([]byte, len(messageBytes))
	mode.CryptBlocks(ciphertext, messageBytes)
	// return ciphertext
	return ciphertext, nil
}

func decrypt(ciphertext []byte, key []byte) (string, error) {
	// create cipher
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}
	// IV (new random IV each time - matching Python's behavior)
	iv := make([]byte, aes.BlockSize)
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		return "", err
	}
	// decrypt message
	mode := cipher.NewCBCDecrypter(block, iv)
	message := make([]byte, len(ciphertext))
	mode.CryptBlocks(message, ciphertext)
	// unpad message
	message = unpad(message, aes.BlockSize)
	// convert message to string
	return string(message), nil
}