package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/hmac"
	"crypto/rand"
	"crypto/sha256"
	"encoding/base64"
	"errors"
	"fmt"
	"time"
)

// Fernet implements Fernet encryption (AES-128-CBC with HMAC)
type Fernet struct {
	signingKey    []byte
	encryptionKey []byte
}

// NewFernet creates a new Fernet instance from a base64-encoded key
func NewFernet(key []byte) (*Fernet, error) {
	decodedKey, err := base64.URLEncoding.DecodeString(string(key))
	if err != nil {
		return nil, err
	}
	
	if len(decodedKey) != 32 {
		return nil, errors.New("fernet key must be 32 bytes")
	}
	
	return &Fernet{
		signingKey:    decodedKey[:16],
		encryptionKey: decodedKey[16:],
	}, nil
}

// GenerateKey generates a new Fernet key
func GenerateKey() ([]byte, error) {
	key := make([]byte, 32)
	_, err := rand.Read(key)
	if err != nil {
		return nil, err
	}
	return []byte(base64.URLEncoding.EncodeToString(key)), nil
}

// Encrypt encrypts the given message
func (f *Fernet) Encrypt(msg []byte) ([]byte, error) {
	// Current timestamp
	timestamp := time.Now().Unix()
	
	// Generate random IV
	iv := make([]byte, 16)
	_, err := rand.Read(iv)
	if err != nil {
		return nil, err
	}
	
	// Pad message to AES block size
	paddedMsg := pkcs7Pad(msg, 16)
	
	// Create AES cipher
	block, err := aes.NewCipher(f.encryptionKey)
	if err != nil {
		return nil, err
	}
	
	// Encrypt using CBC mode
	mode := cipher.NewCBCEncrypter(block, iv)
	ciphertext := make([]byte, len(paddedMsg))
	mode.CryptBlocks(ciphertext, paddedMsg)
	
	// Build token: version (1 byte) + timestamp (8 bytes) + iv (16 bytes) + ciphertext
	token := make([]byte, 0, 1+8+16+len(ciphertext)+32)
	token = append(token, 0x80) // Version
	
	// Add timestamp (big-endian)
	for i := 7; i >= 0; i-- {
		token = append(token, byte(timestamp>>(i*8)))
	}
	
	token = append(token, iv...)
	token = append(token, ciphertext...)
	
	// Calculate HMAC
	h := hmac.New(sha256.New, f.signingKey)
	h.Write(token)
	signature := h.Sum(nil)
	
	token = append(token, signature...)
	
	// Base64 encode the result
	return []byte(base64.URLEncoding.EncodeToString(token)), nil
}

// Decrypt decrypts the given ciphertext
func (f *Fernet) Decrypt(ciphertext []byte) ([]byte, error) {
	// Base64 decode
	token, err := base64.URLEncoding.DecodeString(string(ciphertext))
	if err != nil {
		return nil, err
	}
	
	if len(token) < 57 { // 1 + 8 + 16 + 16 + 32 (minimum size)
		return nil, errors.New("invalid token")
	}
	
	// Extract components
	version := token[0]
	if version != 0x80 {
		return nil, errors.New("invalid version")
	}
	
	// Extract timestamp
	var timestamp int64
	for i := 0; i < 8; i++ {
		timestamp = (timestamp << 8) | int64(token[1+i])
	}
	
	// Check if token is expired (optional - you can add TTL check here)
	_ = timestamp
	
	iv := token[9:25]
	encryptedData := token[25 : len(token)-32]
	signature := token[len(token)-32:]
	
	// Verify HMAC
	h := hmac.New(sha256.New, f.signingKey)
	h.Write(token[:len(token)-32])
	expectedSignature := h.Sum(nil)
	
	if !hmac.Equal(signature, expectedSignature) {
		return nil, errors.New("invalid signature")
	}
	
	// Decrypt
	block, err := aes.NewCipher(f.encryptionKey)
	if err != nil {
		return nil, err
	}
	
	mode := cipher.NewCBCDecrypter(block, iv)
	decrypted := make([]byte, len(encryptedData))
	mode.CryptBlocks(decrypted, encryptedData)
	
	// Remove padding
	return pkcs7Unpad(decrypted)
}

// PKCS7 padding
func pkcs7Pad(data []byte, blockSize int) []byte {
	padding := blockSize - (len(data) % blockSize)
	padText := make([]byte, padding)
	for i := range padText {
		padText[i] = byte(padding)
	}
	return append(data, padText...)
}

// PKCS7 unpadding
func pkcs7Unpad(data []byte) ([]byte, error) {
	if len(data) == 0 {
		return nil, errors.New("empty data")
	}
	
	padding := int(data[len(data)-1])
	if padding > len(data) || padding == 0 {
		return nil, errors.New("invalid padding")
	}
	
	for i := len(data) - padding; i < len(data); i++ {
		if data[i] != byte(padding) {
			return nil, errors.New("invalid padding")
		}
	}
	
	return data[:len(data)-padding], nil
}

// Function to encrypt msg with key using Fernet (AES-128-CBC with HMAC)
func encrypt(msg []byte, key []byte) ([]byte, error) {
	f, err := NewFernet(key)
	if err != nil {
		return nil, err
	}
	return f.Encrypt(msg)
}

// Function to decrypt msg with key using Fernet (AES-128-CBC with HMAC)
func decrypt(ciphertext []byte, key []byte) ([]byte, error) {
	f, err := NewFernet(key)
	if err != nil {
		return nil, err
	}
	return f.Decrypt(ciphertext)
}

// Example usage
func main() {
	// Generate a key
	key, err := GenerateKey()
	if err != nil {
		panic(err)
	}
	
	fmt.Printf("Generated key: %s\n", string(key))
	
	// Test message
	message := []byte("Hello, World!")
	
	// Encrypt
	encrypted, err := encrypt(message, key)
	if err != nil {
		panic(err)
	}
	
	fmt.Printf("Encrypted: %s\n", string(encrypted))
	
	// Decrypt
	decrypted, err := decrypt(encrypted, key)
	if err != nil {
		panic(err)
	}
	
	fmt.Printf("Decrypted: %s\n", string(decrypted))
}