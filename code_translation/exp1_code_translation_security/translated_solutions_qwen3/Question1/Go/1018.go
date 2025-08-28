package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/hmac"
	"crypto/rand"
	"crypto/sha256"
	"encoding/base64"
	"time"
	"binary"
)

// Encrypt encrypts the input string using Fernet-compatible logic.
func Encrypt(input string, key string) string {
	keyBytes, _ := base64.RawURLEncoding.DecodeString(key)
	if len(keyBytes) != 32 {
		panic("invalid key size")
	}
	signKey := keyBytes[:16]
	encKey := keyBytes[16:]

	now := time.Now().Unix()
	timestampBytes := make([]byte, 8)
	binary.BigEndian.PutUint64(timestampBytes, uint64(now))

	iv := make([]byte, 16)
	if _, err := rand.Read(iv); err != nil {
		panic(err)
	}

	paddedData := pkcs7Pad([]byte(input), aes.BlockSize)

	block, _ := aes.NewCipher(encKey)
	ciphertext := make([]byte, len(paddedData))
	mode := cipher.NewCBCEncrypter(block, iv)
	mode.CryptBlocks(ciphertext, paddedData)

	dataToSign := append([]byte{0x80}, timestampBytes...)
	dataToSign = append(dataToSign, iv...)
	dataToSign = append(dataToSign, ciphertext...)

	mac := hmac.New(sha256.New, signKey)
	mac.Write(dataToSign)
	signature := mac.Sum(nil)

	signedData := append(dataToSign, signature...)
	return base64.RawURLEncoding.EncodeToString(signedData)
}

// Decrypt decrypts the input string using Fernet-compatible logic.
func Decrypt(input string, key string) string {
	data, _ := base64.RawURLEncoding.DecodeString(input)
	if len(data) < 32 {
		panic("invalid data size")
	}

	dataPart := data[:len(data)-32]
	signature := data[len(data)-32:]

	keyBytes, _ := base64.RawURLEncoding.DecodeString(key)
	if len(keyBytes) != 32 {
		panic("invalid key size")
	}
	signKey := keyBytes[:16]
	encKey := keyBytes[16:]

	mac := hmac.New(sha256.New, signKey)
	mac.Write(dataPart)
	expectedSignature := mac.Sum(nil)
	if !hmac.Equal(signature, expectedSignature) {
		panic("invalid signature")
	}

	if len(dataPart) < 1+8+16 {
		panic("invalid data length")
	}

	version := dataPart[0]
	if version != 0x80 {
		panic("invalid version")
	}

	timestampBytes := dataPart[1 : 1+8]
	iv := dataPart[1+8 : 1+8+16]
	ciphertext := dataPart[1+8+16:]

	block, _ := aes.NewCipher(encKey)
	mode := cipher.NewCBCDecrypter(block, iv)
	paddedData := make([]byte, len(ciphertext))
	mode.CryptBlocks(paddedData, ciphertext)

	plainData := pkcs7Unpad(paddedData, aes.BlockSize)
	return string(plainData)
}

// pkcs7Pad pads the data using PKCS7 padding.
func pkcs7Pad(data []byte, blockSize int) []byte {
	padding := blockSize - len(data)%blockSize
	padtext := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(data, padtext...)
}

// pkcs7Unpad removes PKCS7 padding from the data.
func pkcs7Unpad(data []byte, blockSize int) []byte {
	if len(data) == 0 {
		panic("empty data to unpad")
	}
	padding := int(data[len(data)-1])
	if padding > blockSize || padding == 0 {
		panic("invalid padding")
	}
	return data[:len(data)-padding]
}