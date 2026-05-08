// Package main provides functions that encrypt and decrypt strings using a symmetric key.
package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"crypto/sha256"
	"encoding/base64"
	"fmt"
	"io"
)

// AESCipher provides AES-256-CBC encryption and decryption with a SHA-256 derived key.
type AESCipher struct {
	bs  int
	key []byte
}

// NewAESCipher creates a new AESCipher instance. The key is hashed with SHA-256.
func NewAESCipher(key string) *AESCipher {
	hash := sha256.Sum256([]byte(key))
	return &AESCipher{
		bs:  32,
		key: hash[:],
	}
}

// Encrypt encrypts the given plaintext and returns a base64-encoded ciphertext.
func (a *AESCipher) Encrypt(raw string) (string, error) {
	padded := a.pad([]byte(raw))

	block, err := aes.NewCipher(a.key)
	if err != nil {
		return "", err
	}

	iv := make([]byte, aes.BlockSize)
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		return "", err
	}

	ciphertext := make([]byte, len(padded))
	mode := cipher.NewCBCEncrypter(block, iv)
	mode.CryptBlocks(ciphertext, padded)

	combined := append(iv, ciphertext...)
	return base64.StdEncoding.EncodeToString(combined), nil
}

// Decrypt decrypts the given base64-encoded ciphertext.
func (a *AESCipher) Decrypt(enc string) (string, error) {
	data, err := base64.StdEncoding.DecodeString(enc)
	if err != nil {
		return "", err
	}

	if len(data) < aes.BlockSize {
		return "", fmt.Errorf("ciphertext too short")
	}

	iv := data[:aes.BlockSize]
	ciphertext := data[aes.BlockSize:]

	if len(ciphertext)%aes.BlockSize != 0 {
		return "", fmt.Errorf("ciphertext is not a multiple of the block size")
	}

	block, err := aes.NewCipher(a.key)
	if err != nil {
		return "", err
	}

	mode := cipher.NewCBCDecrypter(block, iv)
	plaintext := make([]byte, len(ciphertext))
	mode.CryptBlocks(plaintext, ciphertext)

	return string(a.unpad(plaintext)), nil
}

// pad applies PKCS7-style padding using bs (32) as the block size.
func (a *AESCipher) pad(s []byte) []byte {
	padLen := a.bs - len(s)%a.bs
	padding := bytes.Repeat([]byte{byte(padLen)}, padLen)
	return append(s, padding...)
}

// unpad removes PKCS7-style padding.
func (a *AESCipher) unpad(s []byte) []byte {
	length := len(s)
	unpadding := int(s[length-1])
	return s[:length-unpadding]
}

func main() {
	key := "mysecretpassword"
	c := NewAESCipher(key)

	encrypted, err := c.Encrypt("Secret Message A")
	if err != nil {
		fmt.Println("Error:", err)
		return
	}

	decrypted, err := c.Decrypt(encrypted)
	if err != nil {
		fmt.Println("Error:", err)
		return
	}

	fmt.Printf("Encrypted: %s\n", encrypted)
	fmt.Printf("Decrypted: %s\n", decrypted)
}