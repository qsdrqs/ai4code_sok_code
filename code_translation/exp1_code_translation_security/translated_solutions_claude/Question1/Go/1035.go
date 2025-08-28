package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"fmt"
	"log"
)

var key = []byte("Sixteen byte key")

func encrypt(key []byte, msg []byte) ([]byte, []byte, []byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, nil, nil, err
	}

	// Generate a random nonce
	nonce := make([]byte, 12) // 12 bytes is standard for GCM
	if _, err := rand.Read(nonce); err != nil {
		return nil, nil, nil, err
	}

	// Create GCM mode (similar to EAX mode)
	gcm, err := cipher.NewGCM(block)
	if err != nil {
		return nil, nil, nil, err
	}

	// Encrypt and authenticate
	ciphertext := gcm.Seal(nil, nonce, msg, nil)
	
	// Split ciphertext and tag (tag is appended at the end)
	tagSize := gcm.Overhead()
	actualCiphertext := ciphertext[:len(ciphertext)-tagSize]
	tag := ciphertext[len(ciphertext)-tagSize:]

	return actualCiphertext, tag, nonce, nil
}

func decrypt(key []byte, ciphertext []byte, nonce []byte, tag []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	// Create GCM mode
	gcm, err := cipher.NewGCM(block)
	if err != nil {
		return nil, err
	}

	// Combine ciphertext and tag for decryption
	combined := append(ciphertext, tag...)

	// Decrypt and verify
	plaintext, err := gcm.Open(nil, nonce, combined, nil)
	if err != nil {
		return nil, err
	}

	return plaintext, nil
}

func main() {
	ctext, tag, nonce, err := encrypt(key, []byte("message"))
	if err != nil {
		log.Fatal(err)
	}

	plaintext, err := decrypt(key, ctext, nonce, tag)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println(string(plaintext))
}