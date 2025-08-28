package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"crypto/sha256"
	"encoding/base64"
	"fmt"
	"io"
)

// AESCipher struct equivalent to the Python class
type AESCipher struct {
	bs  int
	key []byte
}

// NewAESCipher creates a new AESCipher instance (constructor equivalent)
func NewAESCipher(key string) *AESCipher {
	hash := sha256.Sum256([]byte(key))
	return &AESCipher{
		bs:  32,
		key: hash[:],
	}
}

// Encrypt encrypts the given plaintext string
func (a *AESCipher) Encrypt(raw string) (string, error) {
	// Pad the input
	padded := a.pad(raw)
	
	// Create AES cipher
	block, err := aes.NewCipher(a.key)
	if err != nil {
		return "", err
	}
	
	// Generate random IV
	iv := make([]byte, aes.BlockSize)
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		return "", err
	}
	
	// Encrypt using CBC mode
	mode := cipher.NewCBCEncrypter(block, iv)
	ciphertext := make([]byte, len(padded))
	mode.CryptBlocks(ciphertext, padded)
	
	// Prepend IV to ciphertext and encode to base64
	result := append(iv, ciphertext...)
	return base64.StdEncoding.EncodeToString(result), nil
}

// Decrypt decrypts the given base64 encoded ciphertext
func (a *AESCipher) Decrypt(enc string) (string, error) {
	// Decode from base64
	data, err := base64.StdEncoding.DecodeString(enc)
	if err != nil {
		return "", err
	}
	
	// Extract IV and ciphertext
	iv := data[:aes.BlockSize]
	ciphertext := data[aes.BlockSize:]
	
	// Create AES cipher
	block, err := aes.NewCipher(a.key)
	if err != nil {
		return "", err
	}
	
	// Decrypt using CBC mode
	mode := cipher.NewCBCDecrypter(block, iv)
	plaintext := make([]byte, len(ciphertext))
	mode.CryptBlocks(plaintext, ciphertext)
	
	// Unpad and return as string
	unpadded := a.unpad(plaintext)
	return string(unpadded), nil
}

// pad adds PKCS7 padding to the input string
func (a *AESCipher) pad(s string) []byte {
	data := []byte(s)
	padding := a.bs - len(data)%a.bs
	padtext := make([]byte, padding)
	for i := range padtext {
		padtext[i] = byte(padding)
	}
	return append(data, padtext...)
}

// unpad removes PKCS7 padding from the input bytes
func (a *AESCipher) unpad(s []byte) []byte {
	length := len(s)
	if length == 0 {
		return s
	}
	unpadding := int(s[length-1])
	if unpadding > length {
		return s
	}
	return s[:(length - unpadding)]
}

// Example usage (equivalent to the commented Python code)
func main() {
	key := "mysecretpassword"
	cipher := NewAESCipher(key)
	
	encrypted, err := cipher.Encrypt("Secret Message A")
	if err != nil {
		fmt.Printf("Encryption error: %v\n", err)
		return
	}
	
	decrypted, err := cipher.Decrypt(encrypted)
	if err != nil {
		fmt.Printf("Decryption error: %v\n", err)
		return
	}
	
	fmt.Printf("Encrypted: %s\n", encrypted)
	fmt.Printf("Decrypted: %s\n", decrypted)
}