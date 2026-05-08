package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"encoding/base64"
	"errors"
	"io"
)

// pad applies PKCS7 padding to the plaintext so its length is a multiple of the AES block size.
func pad(plaintext []byte) []byte {
	blockSize := aes.BlockSize
	padding := blockSize - len(plaintext)%blockSize
	padText := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(plaintext, padText...)
}

// unpad removes PKCS7 padding from the plaintext.
func unpad(plaintext []byte) ([]byte, error) {
	length := len(plaintext)
	if length == 0 {
		return nil, errors.New("invalid padding: empty plaintext")
	}
	padding := int(plaintext[length-1])
	if padding == 0 || padding > length {
		return nil, errors.New("invalid padding")
	}
	return plaintext[:length-padding], nil
}

// encrypt encrypts plaintext with AES-CBC using the given symmetric key.
// keySize is kept in the signature to mirror the original Python function.
func encrypt(key []byte, keySize int, plaintext []byte) ([]byte, error) {
	// Generate a random IV of 16 bytes (equivalent to os.urandom(16)).
	iv := make([]byte, aes.BlockSize)
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		return nil, err
	}

	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	paddedTxt := pad(plaintext)
	cipherTxt := make([]byte, len(paddedTxt))

	mode := cipher.NewCBCEncrypter(block, iv)
	mode.CryptBlocks(cipherTxt, paddedTxt)

	// Prepend the IV to the ciphertext, then base64 encode.
	combined := append(iv, cipherTxt...)
	encoded := []byte(base64.StdEncoding.EncodeToString(combined))
	return encoded, nil
}

// decrypt decrypts base64-encoded ciphertext using the given symmetric key.
func decrypt(key []byte, keySize int, ciphertext []byte) ([]byte, error) {
	decoded, err := base64.StdEncoding.DecodeString(string(ciphertext))
	if err != nil {
		return nil, err
	}

	if len(decoded) < aes.BlockSize {
		return nil, errors.New("ciphertext too short")
	}

	iv := decoded[:aes.BlockSize]
	cipherText := decoded[aes.BlockSize:]

	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	if len(cipherText)%aes.BlockSize != 0 {
		return nil, errors.New("ciphertext is not a multiple of the block size")
	}

	plaintext := make([]byte, len(cipherText))
	mode := cipher.NewCBCDecrypter(block, iv)
	mode.CryptBlocks(plaintext, cipherText)

	return unpad(plaintext)
}