package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"encoding/base64"
	"errors"
	"fmt"
	"strings"

	"golang.org/x/crypto/scrypt"
)

func encodeStr(plainText, key string) (string, error) {
	// Generate a random 16-byte salt (AES block size)
	salt := make([]byte, 16)
	if _, err := rand.Read(salt); err != nil {
		return "", err
	}

	// Derive a 32-byte key using scrypt (N=16384, r=8, p=1)
	privateKey, err := scrypt.Key([]byte(key), salt, 16384, 8, 1, 32)
	if err != nil {
		return "", err
	}

	// Create AES cipher
	block, err := aes.NewCipher(privateKey)
	if err != nil {
		return "", err
	}

	// Use GCM with 16-byte nonce (PyCryptodome's default)
	aesGCM, err := cipher.NewGCMWithNonceSize(block, 16)
	if err != nil {
		return "", err
	}

	// Generate random nonce
	nonce := make([]byte, 16)
	if _, err := rand.Read(nonce); err != nil {
		return "", err
	}

	// Encrypt; Go's Seal returns ciphertext with tag appended
	sealed := aesGCM.Seal(nil, nonce, []byte(plainText), nil)

	// Split into ciphertext and tag (tag is the last 16 bytes)
	tagSize := aesGCM.Overhead()
	cipherText := sealed[:len(sealed)-tagSize]
	tag := sealed[len(sealed)-tagSize:]

	// Build "ciphertext*salt*nonce*tag" (each base64-encoded)
	result := base64.StdEncoding.EncodeToString(cipherText) + "*" +
		base64.StdEncoding.EncodeToString(salt) + "*" +
		base64.StdEncoding.EncodeToString(nonce) + "*" +
		base64.StdEncoding.EncodeToString(tag)

	return result, nil
}

func decodeStr(strEncoded, key string) (string, error) {
	parts := strings.Split(strEncoded, "*")
	if len(parts) != 4 {
		return "", errors.New("invalid encoded string format")
	}

	cipherText, err := base64.StdEncoding.DecodeString(parts[0])
	if err != nil {
		return "", err
	}
	salt, err := base64.StdEncoding.DecodeString(parts[1])
	if err != nil {
		return "", err
	}
	nonce, err := base64.StdEncoding.DecodeString(parts[2])
	if err != nil {
		return "", err
	}
	tag, err := base64.StdEncoding.DecodeString(parts[3])
	if err != nil {
		return "", err
	}

	// Derive the same key using scrypt with the stored salt
	privateKey, err := scrypt.Key([]byte(key), salt, 16384, 8, 1, 32)
	if err != nil {
		return "", err
	}

	block, err := aes.NewCipher(privateKey)
	if err != nil {
		return "", err
	}

	aesGCM, err := cipher.NewGCMWithNonceSize(block, len(nonce))
	if err != nil {
		return "", err
	}

	// Go expects ciphertext + tag concatenated
	fullCipherText := append(cipherText, tag...)

	plainText, err := aesGCM.Open(nil, nonce, fullCipherText, nil)
	if err != nil {
		return "", err
	}

	return string(plainText), nil
}

func main() {
	strEncoded1, err := encodeStr("I am okay", "wow")
	if err != nil {
		fmt.Println("Error:", err)
		return
	}

	decoded, err := decodeStr(strEncoded1, "wow")
	if err != nil {
		fmt.Println("Error:", err)
		return
	}

	fmt.Println(decoded)
}