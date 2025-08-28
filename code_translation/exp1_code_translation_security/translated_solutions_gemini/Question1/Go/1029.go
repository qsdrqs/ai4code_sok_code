package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"encoding/hex"
	"errors"
	"fmt"
)

// AESCipher corresponds to the Python AEScipher class.
// It holds the key and block size for AES operations.
type AESCipher struct {
	key   []byte
	blkSz int
}

// NewAESCipher is a constructor function that creates a new AESCipher.
// It corresponds to the __init__ method in the Python class.
// Note: For AES, the block size is always 16 bytes.
// The key must be 16, 24, or 32 bytes long to select AES-128, AES-192, or AES-256.
func NewAESCipher(key []byte) (*AESCipher, error) {
	// In Go, the block size for AES is a fixed constant.
	// We don't need blk_sz as a parameter, but we'll keep it in the struct
	// for direct correspondence with the Python code.
	if len(key) != 16 && len(key) != 24 && len(key) != 32 {
		return nil, errors.New("invalid key size: must be 16, 24, or 32 bytes")
	}
	return &AESCipher{
		key:   key,
		blkSz: aes.BlockSize, // Always 16 for AES
	}, nil
}

// pkcs7Pad applies PKCS#7 padding to the message.
// This is the logic from the Python encrypt method.
func (c *AESCipher) pkcs7Pad(msg []byte) []byte {
	padLen := c.blkSz - (len(msg) % c.blkSz)
	padding := bytes.Repeat([]byte{byte(padLen)}, padLen)
	return append(msg, padding...)
}

// pkcs7Unpad removes PKCS#7 padding from the message.
// This is the logic from the Python decrypt method.
func (c *AESCipher) pkcs7Unpad(msg []byte) ([]byte, error) {
	if len(msg) == 0 {
		return nil, errors.New("cannot unpad empty message")
	}
	padLen := int(msg[len(msg)-1])
	if padLen > len(msg) || padLen > c.blkSz {
		return nil, errors.New("invalid padding")
	}
	return msg[:len(msg)-padLen], nil
}

// Encrypt encrypts a message using AES in ECB mode with PKCS#7 padding.
// WARNING: ECB mode is insecure and should not be used in production systems.
func (c *AESCipher) Encrypt(msg []byte) ([]byte, error) {
	// 1. Create the AES cipher block
	block, err := aes.NewCipher(c.key)
	if err != nil {
		return nil, err
	}

	// 2. Apply PKCS#7 Padding
	paddedMsg := c.pkcs7Pad(msg)

	// 3. Encrypt using ECB mode
	ciphertext := make([]byte, len(paddedMsg))
	// ECB works by encrypting each block independently.
	for bs, be := 0, c.blkSz; bs < len(paddedMsg); bs, be = bs+c.blkSz, be+c.blkSz {
		block.Encrypt(ciphertext[bs:be], paddedMsg[bs:be])
	}

	return ciphertext, nil
}

// Decrypt decrypts a ciphertext using AES in ECB mode and removes PKCS#7 padding.
// WARNING: ECB mode is insecure and should not be used in production systems.
func (c *AESCipher) Decrypt(ciphertext []byte) ([]byte, error) {
	// 1. Create the AES cipher block
	block, err := aes.NewCipher(c.key)
	if err != nil {
		return nil, err
	}

	// 2. Check if the ciphertext is a multiple of the block size
	if len(ciphertext)%c.blkSz != 0 {
		return nil, cipher.ErrShortBuffer
	}

	// 3. Decrypt using ECB mode
	decryptedMsg := make([]byte, len(ciphertext))
	// ECB works by decrypting each block independently.
	for bs, be := 0, c.blkSz; bs < len(ciphertext); bs, be = bs+c.blkSz, be+c.blkSz {
		block.Decrypt(decryptedMsg[bs:be], ciphertext[bs:be])
	}

	// 4. Remove PKCS#7 Padding
	unpaddedMsg, err := c.pkcs7Unpad(decryptedMsg)
	if err != nil {
		return nil, err
	}

	return unpaddedMsg, nil
}

// main function to demonstrate the usage of the AESCipher.
func main() {
	fmt.Println("--- AES ECB Mode Demonstration ---")
	fmt.Println("WARNING: ECB mode is insecure and should not be used in production.")

	// Use a 16-byte key for AES-128
	// In a real application, this key should be securely managed.
	key := []byte("a very secret key") // 16 bytes

	// Create a new cipher object
	aesCipher, err := NewAESCipher(key)
	if err != nil {
		fmt.Println("Error creating cipher:", err)
		return
	}

	// The message to be encrypted
	originalMessage := []byte("This is a secret message that needs to be encrypted.")

	fmt.Printf("\nOriginal Message: %s\n", originalMessage)

	// Encrypt the message
	ciphertext, err := aesCipher.Encrypt(originalMessage)
	if err != nil {
		fmt.Println("Encryption failed:", err)
		return
	}
	fmt.Printf("Encrypted (hex): %s\n", hex.EncodeToString(ciphertext))

	// Decrypt the message
	decryptedMessage, err := aesCipher.Decrypt(ciphertext)
	if err != nil {
		fmt.Println("Decryption failed:", err)
		return
	}
	fmt.Printf("Decrypted Message: %s\n", decryptedMessage)

	// Verify that the decrypted message matches the original
	if string(originalMessage) == string(decryptedMessage) {
		fmt.Println("\nSuccess: The decrypted message matches the original.")
	} else {
		fmt.Println("\nFailure: The decrypted message does not match the original.")
	}
}