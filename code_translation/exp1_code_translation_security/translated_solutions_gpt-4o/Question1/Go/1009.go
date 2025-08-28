package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"encoding/hex"
	"fmt"
	"io"
)

func encodeString(message string, key []byte, nonce []byte) ([]byte, []byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, nil, err
	}

	aesgcm, err := cipher.NewGCM(block)
	if err != nil {
		return nil, nil, err
	}

	mBytes := []byte(message)
	ciphertext := aesgcm.Seal(nil, nonce, mBytes, nil)
	tag := ciphertext[len(ciphertext)-aesgcm.Overhead():]
	return ciphertext[:len(ciphertext)-aesgcm.Overhead()], tag, nil
}

func decodeString(ciphertext []byte, tag []byte, key []byte, nonce []byte) (string, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	aesgcm, err := cipher.NewGCM(block)
	if err != nil {
		return "", err
	}

	fullCiphertext := append(ciphertext, tag...)
	data, err := aesgcm.Open(nil, nonce, fullCiphertext, nil)
	if err != nil {
		return "", err
	}

	return string(data), nil
}

func main() {
	originalMessage := "secret data"
	nonce := make([]byte, 12) // GCM standard nonce size is 12 bytes
	if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
		panic(err)
	}

	key := make([]byte, 16) // AES-128
	if _, err := io.ReadFull(rand.Reader, key); err != nil {
		panic(err)
	}

	fmt.Println("Original Message:", originalMessage)
	ciphertext, tag, err := encodeString(originalMessage, key, nonce)
	if err != nil {
		panic(err)
	}

	fmt.Println("Ciphertext (hex):", hex.EncodeToString(ciphertext))
	fmt.Println("Tag (hex):", hex.EncodeToString(tag))

	decodedString, err := decodeString(ciphertext, tag, key, nonce)
	if err != nil {
		panic(err)
	}

	fmt.Println("Decoded String:", decodedString)
}