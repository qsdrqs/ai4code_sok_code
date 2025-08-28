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
	"io"
)

// encode_str encrypts plaintext using a key, mimicking cryptocode.encrypt.
// It uses AES-256-CBC, prepends the IV, and Base64 encodes the result.
func encode_str(plainText string, key string) (string, error) {
	// Hash the key to create a 32-byte key for AES-256.
	keyBytes := sha256.Sum256([]byte(key))

	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(keyBytes[:])
	if err != nil {
		return "", err
	}

	// Pad the plaintext to a multiple of the block size using PKCS#7.
	paddedPlainText := pkcs7Pad([]byte(plainText), aes.BlockSize)

	// The ciphertext will be the IV plus the encrypted data.
	// The IV is the same size as the AES block size.
	cipherText := make([]byte, aes.BlockSize+len(paddedPlainText))
	iv := cipherText[:aes.BlockSize]

	// Generate a cryptographically secure random IV.
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		return "", err
	}

	// Encrypt the data using CBC mode.
	mode := cipher.NewCBCEncrypter(block, iv)
	mode.CryptBlocks(cipherText[aes.BlockSize:], paddedPlainText)

	// Return the Base64 encoded string of the IV + ciphertext.
	return base64.StdEncoding.EncodeToString(cipherText), nil
}

// decode_str decrypts a string using a key, mimicking cryptocode.decrypt.
func decode_str(encodedStr string, key string) (string, error) {
	// Hash the key to get the same 32-byte key used for encryption.
	keyBytes := sha256.Sum256([]byte(key))

	// Decode the Base64 string to get the raw IV + ciphertext.
	cipherText, err := base64.StdEncoding.DecodeString(encodedStr)
	if err != nil {
		return "", err
	}

	// Create a new AES cipher block.
	block, err := aes.NewCipher(keyBytes[:])
	if err != nil {
		return "", err
	}

	// The ciphertext must be at least one block size long to contain the IV.
	if len(cipherText) < aes.BlockSize {
		return "", errors.New("ciphertext too short")
	}

	// The IV is the first block of the ciphertext.
	iv := cipherText[:aes.BlockSize]
	cipherText = cipherText[aes.BlockSize:]

	// The remaining ciphertext must be a multiple of the block size.
	if len(cipherText)%aes.BlockSize != 0 {
		return "", errors.New("ciphertext is not a multiple of the block size")
	}

	// Decrypt the data using CBC mode.
	mode := cipher.NewCBCDecrypter(block, iv)
	// CryptBlocks can work in-place if the input and output are the same slice.
	mode.CryptBlocks(cipherText, cipherText)

	// Unpad the decrypted data to get the original plaintext.
	unpaddedText, err := pkcs7Unpad(cipherText)
	if err != nil {
		return "", err
	}

	return string(unpaddedText), nil
}

// pkcs7Pad adds PKCS#7 padding to a byte slice.
func pkcs7Pad(data []byte, blockSize int) []byte {
	padding := blockSize - len(data)%blockSize
	padText := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(data, padText...)
}

// pkcs7Unpad removes PKCS#7 padding from a byte slice.
func pkcs7Unpad(data []byte) ([]byte, error) {
	length := len(data)
	if length == 0 {
		return nil, errors.New("unpadding error: input data is empty")
	}
	unpadding := int(data[length-1])
	if unpadding > length {
		return nil, errors.New("unpadding error: invalid padding")
	}
	return data[:(length - unpadding)], nil
}

func main() {
	// The main logic from the Python script, now in Go.
	strEncoded1, err := encode_str("I am okay", "wow")
	if err != nil {
		// In Go, it's idiomatic to handle errors explicitly.
		fmt.Println("Error during encryption:", err)
		return
	}

	decodedStr, err := decode_str(strEncoded1, "wow")
	if err != nil {
		fmt.Println("Error during decryption:", err)
		return
	}

	fmt.Println(decodedStr)
}