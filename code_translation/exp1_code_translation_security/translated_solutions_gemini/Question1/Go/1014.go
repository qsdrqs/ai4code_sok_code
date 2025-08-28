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

// AESCipher implements AES encryption and decryption, mirroring the Python example.
type AESCipher struct {
	key []byte
	// bs is the block size for padding. The Python example uses a non-standard
	// padding block size of 32. We replicate that here for compatibility.
	// Note that AES itself always has a block size of 16 bytes.
	bs int
}

// NewAESCipher creates a new AESCipher instance.
// It derives a 256-bit (32-byte) key from the input string using SHA-256,
// just like the Python version.
func NewAESCipher(key string) *AESCipher {
	// Create a SHA-256 hash of the key.
	hasher := sha256.New()
	hasher.Write([]byte(key))
	
	return &AESCipher{
		key: hasher.Sum(nil),
		bs:  32, // Using 32 for padding, as in the Python example.
	}
}

// Encrypt encrypts a raw string using AES-CBC mode.
// The process is:
// 1. Pad the raw data to a multiple of the block size (using our custom size).
// 2. Generate a random Initialization Vector (IV).
// 3. Encrypt the data.
// 4. Prepend the IV to the ciphertext.
// 5. Base64-encode the result (IV + ciphertext).
func (c *AESCipher) Encrypt(raw string) (string, error) {
	rawBytes := []byte(raw)
	paddedRaw := c.pkcs7Pad(rawBytes)

	// Create the AES cipher block.
	block, err := aes.NewCipher(c.key)
	if err != nil {
		return "", err
	}

	// The IV needs to be the same size as the AES block size.
	ciphertext := make([]byte, aes.BlockSize+len(paddedRaw))
	iv := ciphertext[:aes.BlockSize]
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		return "", err
	}

	// Encrypt the data.
	mode := cipher.NewCBCEncrypter(block, iv)
	mode.CryptBlocks(ciphertext[aes.BlockSize:], paddedRaw)

	// Return the base64-encoded IV + ciphertext.
	return base64.StdEncoding.EncodeToString(ciphertext), nil
}

// Decrypt decrypts a base64-encoded string.
// The process is:
// 1. Base64-decode the input string.
// 2. Extract the IV from the beginning of the data.
// 3. Decrypt the remaining ciphertext.
// 4. Unpad the decrypted data.
// 5. Return the result as a UTF-8 string.
func (c *AESCipher) Decrypt(enc string) (string, error) {
	encBytes, err := base64.StdEncoding.DecodeString(enc)
	if err != nil {
		return "", err
	}

	// Create the AES cipher block.
	block, err := aes.NewCipher(c.key)
	if err != nil {
		return "", err
	}

	// Check if the ciphertext is long enough.
	if len(encBytes) < aes.BlockSize {
		return "", errors.New("ciphertext too short")
	}

	// Extract the IV.
	iv := encBytes[:aes.BlockSize]
	ciphertext := encBytes[aes.BlockSize:]

	// CBC mode always works in whole blocks.
	if len(ciphertext)%aes.BlockSize != 0 {
		return "", errors.New("ciphertext is not a multiple of the block size")
	}

	// Decrypt the data.
	mode := cipher.NewCBCDecrypter(block, iv)
	mode.CryptBlocks(ciphertext, ciphertext) // Decrypts in-place.

	// Unpad the decrypted data.
	unpadded, err := c.pkcs7Unpad(ciphertext)
	if err != nil {
		return "", err
	}

	return string(unpadded), nil
}

// pkcs7Pad pads the input byte slice to a multiple of the cipher's block size.
// This is a custom implementation to match the Python code's padding to 32 bytes.
func (c *AESCipher) pkcs7Pad(src []byte) []byte {
	padding := c.bs - len(src)%c.bs
	padtext := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(src, padtext...)
}

// pkcs7Unpad removes PKCS#7 padding from a byte slice.
func (c *AESCipher) pkcs7Unpad(src []byte) ([]byte, error) {
	length := len(src)
	if length == 0 {
		return nil, errors.New("unpad error: input is empty")
	}
	
	unpadding := int(src[length-1])
	if unpadding > length || unpadding > c.bs {
		return nil, errors.New("unpad error: invalid padding")
	}

	return src[:(length - unpadding)], nil
}

// main function demonstrates the usage of the AESCipher.
func main() {
	key := "mysecretpassword"
	cipher := NewAESCipher(key)

	// Encrypt a message
	encrypted, err := cipher.Encrypt("Secret Message A")
	if err != nil {
		panic(fmt.Sprintf("Encryption failed: %v", err))
	}

	// Decrypt the message
	decrypted, err := cipher.Decrypt(encrypted)
	if err != nil {
		panic(fmt.Sprintf("Decryption failed: %v", err))
	}

	fmt.Printf("Encrypted: %s\n", encrypted)
	fmt.Printf("Decrypted: %s\n", decrypted)
}