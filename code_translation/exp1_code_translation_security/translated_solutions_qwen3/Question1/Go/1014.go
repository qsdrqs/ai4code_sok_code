package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"crypto/sha256"
	"encoding/base64"
	"errors"
	"fmt"
)

// AESCipher holds the key used for encryption and decryption.
type AESCipher struct {
	key []byte
}

// NewAESCipher creates a new AESCipher instance using the given key string.
// The key is hashed using SHA-256 to produce a 32-byte key.
func NewAESCipher(key string) *AESCipher {
	hashedKey := sha256.Sum256([]byte(key))
	return &AESCipher{
		key: hashedKey[:],
	}
}

// pad adds PKCS#7 padding to the given data using a block size of 32.
func pad(src []byte, blockSize int) []byte {
	padding := blockSize - len(src)%blockSize
	padText := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(src, padText...)
}

// unpad removes PKCS#7 padding from the given data.
// It assumes the padding is valid and does not perform validation.
func unpad(src []byte, blockSize int) ([]byte, error) {
	if len(src) == 0 {
		return nil, errors.New("empty data in unpad")
	}
	padLen := int(src[len(src)-1])
	if padLen > len(src) || padLen > blockSize || padLen <= 0 {
		return nil, errors.New("invalid padding length")
	}
	return src[:len(src)-padLen], nil
}

// Encrypt encrypts the given plaintext string using AES-CBC with a 16-byte IV.
// The IV is prepended to the ciphertext and the result is base64-encoded.
func (a *AESCipher) Encrypt(plainText string) (string, error) {
	// Step 1: Pad the plaintext to a multiple of 32 bytes
	padded := pad([]byte(plainText), 32)

	// Step 2: Generate a random 16-byte IV
	iv := make([]byte, aes.BlockSize)
	if _, err := rand.Read(iv); err != nil {
		return "", err
	}

	// Step 3: Create AES cipher block
	block, err := aes.NewCipher(a.key)
	if err != nil {
		return "", err
	}

	// Step 4: Create CBC encrypter
	cbc := cipher.NewCBCEncrypter(block, iv)

	// Step 5: Encrypt the padded data
	cipherText := make([]byte, len(padded))
	cbc.CryptBlocks(cipherText, padded)

	// Step 6: Prepend IV and base64 encode
	encrypted := make([]byte, len(iv)+len(cipherText))
	copy(encrypted, iv)
	copy(encrypted[len(iv):], cipherText)

	return base64.StdEncoding.EncodeToString(encrypted), nil
}

// Decrypt decrypts the given base64-encoded ciphertext using AES-CBC.
// It extracts the IV from the beginning of the data and unpads the result.
func (a *AESCipher) Decrypt(encrypted string) (string, error) {
	// Step 1: Base64 decode the input
	cipherData, err := base64.StdEncoding.DecodeString(encrypted)
	if err != nil {
		return "", err
	}

	// Step 2: Extract the 16-byte IV
	if len(cipherData) < aes.BlockSize {
		return "", errors.New("ciphertext too short")
	}
	iv := cipherData[:aes.BlockSize]
	cipherText := cipherData[aes.BlockSize:]

	// Step 3: Create AES cipher block
	block, err := aes.NewCipher(a.key)
	if err != nil {
		return "", err
	}

	// Step 4: Create CBC decrypter
	cbc := cipher.NewCBCDecrypter(block, iv)

	// Step 5: Decrypt the data
	plainPadded := make([]byte, len(cipherText))
	cbc.CryptBlocks(plainPadded, cipherText)

	// Step 6: Unpad the result
	plainText, err := unpad(plainPadded, 32)
	if err != nil {
		return "", err
	}

	return string(plainText), nil
}

// Example usage
func main() {
	key := "mysecretpassword"
	cipher := NewAESCipher(key)

	// Encrypt
	encrypted, err := cipher.Encrypt("Secret Message A")
	if err != nil {
		panic(err)
	}
	fmt.Printf("Encrypted: %s\n", encrypted)

	// Decrypt
	decrypted, err := cipher.Decrypt(encrypted)
	if err != nil {
		panic(err)
	}
	fmt.Printf("Decrypted: %s\n", decrypted)
}