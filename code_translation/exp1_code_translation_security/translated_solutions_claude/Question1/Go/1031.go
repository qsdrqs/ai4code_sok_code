package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"encoding/base64"
	"fmt"
	"io"
)

// FernetLike provides Fernet-like encryption functionality
type FernetLike struct {
	key []byte
}

// NewFernetLike creates a new FernetLike instance with the given key
func NewFernetLike(key []byte) *FernetLike {
	return &FernetLike{key: key}
}

// GenerateKey generates a new 32-byte key for AES-256
func GenerateKey() ([]byte, error) {
	key := make([]byte, 32) // 32 bytes for AES-256
	_, err := rand.Read(key)
	if err != nil {
		return nil, err
	}
	return key, nil
}

// Encrypt encrypts a message using AES-GCM
func (f *FernetLike) Encrypt(message string) (string, error) {
	block, err := aes.NewCipher(f.key)
	if err != nil {
		return "", err
	}

	gcm, err := cipher.NewGCM(block)
	if err != nil {
		return "", err
	}

	nonce := make([]byte, gcm.NonceSize())
	if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
		return "", err
	}

	ciphertext := gcm.Seal(nonce, nonce, []byte(message), nil)
	return base64.StdEncoding.EncodeToString(ciphertext), nil
}

// Decrypt decrypts a message using AES-GCM
func (f *FernetLike) Decrypt(encMessage string) (string, error) {
	data, err := base64.StdEncoding.DecodeString(encMessage)
	if err != nil {
		return "", err
	}

	block, err := aes.NewCipher(f.key)
	if err != nil {
		return "", err
	}

	gcm, err := cipher.NewGCM(block)
	if err != nil {
		return "", err
	}

	nonceSize := gcm.NonceSize()
	if len(data) < nonceSize {
		return "", fmt.Errorf("ciphertext too short")
	}

	nonce, ciphertext := data[:nonceSize], data[nonceSize:]
	plaintext, err := gcm.Open(nil, nonce, ciphertext, nil)
	if err != nil {
		return "", err
	}

	return string(plaintext), nil
}

// encrypt using a symmetric key
func encrypt(fernet *FernetLike, message string) (string, error) {
	encMessage, err := fernet.Encrypt(message)
	return encMessage, err
}

// decrypt using a symmetric key
func decrypt(fernet *FernetLike, encMessage string) (string, error) {
	decMessage, err := fernet.Decrypt(encMessage)
	return decMessage, err
}

func main() {
	message := "the solutions are here somewhere"
	fmt.Println("Secret Message:", message)
	
	key, err := GenerateKey()
	if err != nil {
		fmt.Printf("Error generating key: %v\n", err)
		return
	}
	
	fernet := NewFernetLike(key)
	
	encMessage, err := encrypt(fernet, message)
	if err != nil {
		fmt.Printf("Error encrypting: %v\n", err)
		return
	}
	fmt.Println(encMessage)
	
	decMessage, err := decrypt(fernet, encMessage)
	if err != nil {
		fmt.Printf("Error decrypting: %v\n", err)
		return
	}
	fmt.Println("decrypted Message:", decMessage)
}