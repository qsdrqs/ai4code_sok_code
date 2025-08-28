package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"encoding/base64"
	"errors"
	"fmt"
	"io"
)

// Encrypt encrypts plaintext using a given key with AES-CBC.
// The key size is inferred from the key length (16, 24, or 32 bytes for AES-128, AES-192, or AES-256).
// The output is a Base64 encoded string of the form: IV + Ciphertext.
func Encrypt(key []byte, plaintext []byte) (string, error) {
	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	// Pad the plaintext to a multiple of the block size using PKCS#7 padding.
	paddedPlaintext, err := pkcs7Pad(plaintext, block.BlockSize())
	if err != nil {
		return "", err
	}

	// The IV needs to be unique, but not secure. Therefore, it's common to
	// include it at the beginning of the ciphertext.
	// The IV is always the same size as the block size (16 bytes for AES).
	iv := make([]byte, aes.BlockSize)
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		return "", err
	}

	// Create a new CBC encrypter.
	mode := cipher.NewCBCEncrypter(block, iv)

	// Encrypt the data.
	ciphertext := make([]byte, len(paddedPlaintext))
	mode.CryptBlocks(ciphertext, paddedPlaintext)

	// Prepend the IV to the ciphertext and Base64 encode the result.
	encodedCiphertext := base64.StdEncoding.EncodeToString(append(iv, ciphertext...))

	return encodedCiphertext, nil
}

// Decrypt decrypts a Base64 encoded ciphertext string using a given key.
// It assumes the input string is in the format: IV + Ciphertext.
func Decrypt(key []byte, ciphertext string) ([]byte, error) {
	// Base64 decode the ciphertext.
	decodedCiphertext, err := base64.StdEncoding.DecodeString(ciphertext)
	if err != nil {
		return nil, err
	}

	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	// The IV is the first block of the decoded ciphertext.
	if len(decodedCiphertext) < aes.BlockSize {
		return nil, errors.New("ciphertext too short")
	}
	iv := decodedCiphertext[:aes.BlockSize]
	encryptedData := decodedCiphertext[aes.BlockSize:]

	// The ciphertext must be a multiple of the block size.
	if len(encryptedData)%aes.BlockSize != 0 {
		return nil, errors.New("ciphertext is not a multiple of the block size")
	}

	// Create a new CBC decrypter.
	mode := cipher.NewCBCDecrypter(block, iv)

	// Decrypt the data.
	decryptedText := make([]byte, len(encryptedData))
	mode.CryptBlocks(decryptedText, encryptedData)

	// Unpad the decrypted text.
	return pkcs7Unpad(decryptedText, block.BlockSize())
}

// pkcs7Pad adds PKCS#7 padding to a plaintext slice.
func pkcs7Pad(data []byte, blockSize int) ([]byte, error) {
	if blockSize <= 0 {
		return nil, errors.New("invalid block size")
	}
	padding := blockSize - (len(data) % blockSize)
	padText := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(data, padText...), nil
}

// pkcs7Unpad removes PKCS#7 padding from a decrypted slice.
func pkcs7Unpad(data []byte, blockSize int) ([]byte, error) {
	length := len(data)
	if length == 0 {
		return nil, errors.New("cannot unpad empty data")
	}
	if length%blockSize != 0 {
		return nil, errors.New("data is not block-aligned")
	}
	
	padding := int(data[length-1])
	if padding > blockSize || padding == 0 {
		return nil, errors.New("invalid padding")
	}

	// Check that all padding bytes are correct.
	for i := 0; i < padding; i++ {
		if data[length-1-i] != byte(padding) {
			return nil, errors.New("invalid padding byte")
		}
	}

	return data[:length-padding], nil
}

// --- Example Usage ---
func main() {
	// Use a 32-byte key for AES-256.
	// In a real application, this key should be securely managed and not hardcoded.
	key := []byte("a_very_secret_key_for_aes_256!")
	plaintext := []byte("This is a secret message.")

	fmt.Printf("Original: %s\n", plaintext)

	// Encrypt the message
	// The key_size parameter from Python is not needed; Go infers it from len(key).
	encrypted, err := Encrypt(key, plaintext)
	if err != nil {
		panic(err)
	}
	fmt.Printf("Encrypted (Base64): %s\n", encrypted)

	// Decrypt the message
	decrypted, err := Decrypt(key, encrypted)
	if err != nil {
		panic(err)
	}
	fmt.Printf("Decrypted: %s\n", decrypted)

	// Verify
	if string(plaintext) == string(decrypted) {
		fmt.Println("\nSuccess: Original and decrypted messages match!")
	} else {
		fmt.Println("\nFailure: Messages do not match.")
	}
}