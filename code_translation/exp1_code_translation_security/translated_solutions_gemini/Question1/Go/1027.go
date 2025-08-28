package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"encoding/hex"
	"errors"
	"fmt"
	"io"
)

// pkcs7Pad appends PKCS#7 padding to a plaintext slice.
func pkcs7Pad(plaintext []byte, blockSize int) ([]byte, error) {
	if blockSize <= 0 {
		return nil, errors.New("invalid block size")
	}
	if len(plaintext) == 0 {
		return nil, errors.New("cannot pad empty plaintext")
	}

	padding := blockSize - len(plaintext)%blockSize
	padtext := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(plaintext, padtext...), nil
}

// pkcs7Unpad removes PKCS#7 padding from a plaintext slice.
func pkcs7Unpad(plaintext []byte, blockSize int) ([]byte, error) {
	if blockSize <= 0 {
		return nil, errors.New("invalid block size")
	}
	if len(plaintext) == 0 {
		return nil, errors.New("cannot unpad empty plaintext")
	}
	if len(plaintext)%blockSize != 0 {
		return nil, errors.New("plaintext is not a multiple of the block size")
	}

	length := len(plaintext)
	padding := int(plaintext[length-1])

	if padding > length || padding > blockSize {
		return nil, errors.New("invalid padding")
	}

	return plaintext[:length-padding], nil
}

// Encrypt encrypts a message using AES-CBC with a given key.
// A random IV is generated and prepended to the ciphertext.
func Encrypt(message string, key []byte) ([]byte, error) {
	// Convert message to bytes
	plaintext := []byte(message)

	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	// Pad the message to a multiple of the block size.
	paddedPlaintext, err := pkcs7Pad(plaintext, aes.BlockSize)
	if err != nil {
		return nil, err
	}

	// The IV needs to be unique, but not secure. Therefore, it's common to
	// prepend it to the ciphertext.
	ciphertext := make([]byte, aes.BlockSize+len(paddedPlaintext))
	iv := ciphertext[:aes.BlockSize]
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		return nil, err
	}

	// Create a new CBC encrypter.
	mode := cipher.NewCBCEncrypter(block, iv)

	// Encrypt the message.
	mode.CryptBlocks(ciphertext[aes.BlockSize:], paddedPlaintext)

	// Return the IV + ciphertext.
	return ciphertext, nil
}

// Decrypt decrypts a ciphertext using AES-CBC with a given key.
// It assumes the IV is prepended to the ciphertext.
func Decrypt(ciphertext []byte, key []byte) (string, error) {
	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	// The ciphertext must be at least one block size long.
	if len(ciphertext) < aes.BlockSize {
		return "", errors.New("ciphertext too short")
	}

	// Extract the IV from the beginning of the ciphertext.
	iv := ciphertext[:aes.BlockSize]
	encryptedMessage := ciphertext[aes.BlockSize:]

	// The encrypted message must be a multiple of the block size.
	if len(encryptedMessage)%aes.BlockSize != 0 {
		return "", errors.New("ciphertext is not a multiple of the block size")
	}

	// Create a new CBC decrypter.
	mode := cipher.NewCBCDecrypter(block, iv)

	// Decrypt the message. The decryption can be done in-place.
	mode.CryptBlocks(encryptedMessage, encryptedMessage)

	// Unpad the decrypted message.
	unpaddedMessage, err := pkcs7Unpad(encryptedMessage, aes.BlockSize)
	if err != nil {
		return "", err
	}

	// Convert the message to a string and return.
	return string(unpaddedMessage), nil
}

// Main function to demonstrate the usage.
func main() {
	// The key must be 16, 24, or 32 bytes long for AES-128, AES-192, or AES-256.
	// Using a 32-byte key for AES-256.
	key := []byte("a_very_secret_key_for_aes_256!")
	message := "This is a secret message."

	fmt.Printf("Original Message: %s\n", message)
	fmt.Printf("AES Key: %s\n\n", string(key))

	// Encrypt the message
	ciphertext, err := Encrypt(message, key)
	if err != nil {
		panic(fmt.Sprintf("Encryption failed: %v", err))
	}
	fmt.Printf("Ciphertext (hex): %s\n", hex.EncodeToString(ciphertext))

	// Decrypt the message
	decryptedMessage, err := Decrypt(ciphertext, key)
	if err != nil {
		panic(fmt.Sprintf("Decryption failed: %v", err))
	}
	fmt.Printf("Decrypted Message: %s\n", decryptedMessage)

	// Verify correctness
	if message == decryptedMessage {
		fmt.Println("\nSuccess: Original and decrypted messages match.")
	} else {
		fmt.Println("\nFailure: Messages do not match.")
	}
}