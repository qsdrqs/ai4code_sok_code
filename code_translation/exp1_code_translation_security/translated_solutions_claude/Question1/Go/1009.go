package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"fmt"
	"log"
)

func encodeString(message string, key []byte, nonce []byte) ([]byte, []byte, error) {
	// Create AES cipher
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, nil, err
	}

	// Create GCM mode (equivalent to EAX mode for authentication)
	gcm, err := cipher.NewGCM(block)
	if err != nil {
		return nil, nil, err
	}

	// Convert string to bytes
	mBytes := []byte(message)
	
	// Encrypt and authenticate
	ciphertext := gcm.Seal(nil, nonce, mBytes, nil)
	
	// Split ciphertext and tag (GCM appends tag to ciphertext)
	tagSize := gcm.Overhead()
	actualCiphertext := ciphertext[:len(ciphertext)-tagSize]
	tag := ciphertext[len(ciphertext)-tagSize:]
	
	return actualCiphertext, tag, nil
}

func decodeString(ciphertext []byte, tag []byte, key []byte, nonce []byte) (string, error) {
	// Create AES cipher
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	// Create GCM mode
	gcm, err := cipher.NewGCM(block)
	if err != nil {
		return "", err
	}

	// Combine ciphertext and tag for GCM
	combined := append(ciphertext, tag...)
	
	// Decrypt and verify
	data, err := gcm.Open(nil, nonce, combined, nil)
	if err != nil {
		return "", err
	}

	// Convert bytes back to string
	return string(data), nil
}

func getRandomBytes(size int) []byte {
	bytes := make([]byte, size)
	_, err := rand.Read(bytes)
	if err != nil {
		log.Fatal(err)
	}
	return bytes
}

func main() {
	originalMessage := "secret data"
	nonce := getRandomBytes(12) // GCM typically uses 12-byte nonces
	key := getRandomBytes(16)   // 128-bit key

	fmt.Println(originalMessage)
	
	ciphertext, tag, err := encodeString(originalMessage, key, nonce)
	if err != nil {
		log.Fatal(err)
	}
	
	decodedString, err := decodeString(ciphertext, tag, key, nonce)
	if err != nil {
		log.Fatal(err)
	}
	
	fmt.Println(decodedString)
}