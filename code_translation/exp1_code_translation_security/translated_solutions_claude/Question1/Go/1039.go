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

// Fernet implements Fernet symmetric encryption
type Fernet struct {
	signingKey    []byte
	encryptionKey []byte
}

// NewFernet creates a new Fernet instance from a base64-encoded key
func NewFernet(key string) (*Fernet, error) {
	keyBytes, err := base64.URLEncoding.DecodeString(key)
	if err != nil {
		return nil, err
	}
	
	if len(keyBytes) != 32 {
		return nil, errors.New("key must be 32 bytes")
	}
	
	return &Fernet{
		signingKey:    keyBytes[:16],
		encryptionKey: keyBytes[16:],
	}, nil
}

// GenerateKey generates a new Fernet key
func GenerateKey() string {
	key := make([]byte, 32)
	rand.Read(key)
	return base64.URLEncoding.EncodeToString(key)
}

// Encrypt encrypts a message and returns base64-encoded ciphertext
func (f *Fernet) Encrypt(message []byte) ([]byte, error) {
	// Create AES cipher
	block, err := aes.NewCipher(f.encryptionKey)
	if err != nil {
		return nil, err
	}
	
	// Generate IV
	iv := make([]byte, aes.BlockSize)
	if _, err := rand.Read(iv); err != nil {
		return nil, err
	}
	
	// Pad message to block size
	paddedMessage := pkcs7Pad(message, aes.BlockSize)
	
	// Encrypt
	mode := cipher.NewCBCEncrypter(block, iv)
	ciphertext := make([]byte, len(paddedMessage))
	mode.CryptBlocks(ciphertext, paddedMessage)
	
	// Create token: version (1 byte) + timestamp (8 bytes) + iv (16 bytes) + ciphertext + hmac (32 bytes)
	timestamp := time.Now().Unix()
	token := make([]byte, 1+8+len(iv)+len(ciphertext))
	
	token[0] = 0x80 // version
	for i := 0; i < 8; i++ {
		token[1+i] = byte(timestamp >> (56 - 8*i))
	}
	copy(token[9:], iv)
	copy(token[9+len(iv):], ciphertext)
	
	// Calculate HMAC
	h := hmac.New(sha256.New, f.signingKey)
	h.Write(token)
	signature := h.Sum(nil)
	
	// Append HMAC to token
	finalToken := append(token, signature...)
	
	// Return base64 encoded token
	encoded := base64.URLEncoding.EncodeToString(finalToken)
	return []byte(encoded), nil
}

// Decrypt decrypts a base64-encoded Fernet token
func (f *Fernet) Decrypt(token []byte) ([]byte, error) {
	// Decode base64
	decoded, err := base64.URLEncoding.DecodeString(string(token))
	if err != nil {
		return nil, err
	}
	
	if len(decoded) < 57 { // minimum token size
		return nil, errors.New("invalid token")
	}
	
	// Extract components
	version := decoded[0]
	if version != 0x80 {
		return nil, errors.New("invalid version")
	}
	
	// Extract timestamp (8 bytes)
	var timestamp int64
	for i := 0; i < 8; i++ {
		timestamp = (timestamp << 8) | int64(decoded[1+i])
	}
	
	// Extract IV (16 bytes)
	iv := decoded[9:25]
	
	// Extract ciphertext and signature
	ciphertext := decoded[25 : len(decoded)-32]
	signature := decoded[len(decoded)-32:]
	
	// Verify HMAC
	h := hmac.New(sha256.New, f.signingKey)
	h.Write(decoded[:len(decoded)-32])
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
	plaintext := make([]byte, len(ciphertext))
	mode.CryptBlocks(plaintext, ciphertext)
	
	// Remove padding
	unpaddedMessage, err := pkcs7Unpad(plaintext)
	if err != nil {
		return nil, err
	}
	
	return unpaddedMessage, nil
}

// PKCS7 padding functions
func pkcs7Pad(data []byte, blockSize int) []byte {
	padding := blockSize - (len(data) % blockSize)
	padText := make([]byte, padding)
	for i := range padText {
		padText[i] = byte(padding)
	}
	return append(data, padText...)
}

func pkcs7Unpad(data []byte) ([]byte, error) {
	if len(data) == 0 {
		return nil, errors.New("empty data")
	}
	
	padding := int(data[len(data)-1])
	if padding > len(data) {
		return nil, errors.New("invalid padding")
	}
	
	for i := len(data) - padding; i < len(data); i++ {
		if data[i] != byte(padding) {
			return nil, errors.New("invalid padding")
		}
	}
	
	return data[:len(data)-padding], nil
}

// Encrypt function equivalent to Python version
func encrypt(message, key string) ([]byte, error) {
	fernet, err := NewFernet(key)
	if err != nil {
		return nil, err
	}
	
	encMessage, err := fernet.Encrypt([]byte(message))
	if err != nil {
		return nil, err
	}
	
	return encMessage, nil
}

// Decrypt function equivalent to Python version
func decrypt(message []byte, key string) ([]byte, error) {
	fernet, err := NewFernet(key)
	if err != nil {
		return nil, err
	}
	
	decMessage, err := fernet.Decrypt(message)
	if err != nil {
		return nil, err
	}
	
	return decMessage, nil
}

// Example usage
func main() {
	// Generate a key (you can also use an existing key)
	key := GenerateKey()
	fmt.Println("Generated key:", key)
	
	// Test the functions
	message := "Hello, World!"
	
	// Encrypt
	encrypted, err := encrypt(message, key)
	if err != nil {
		fmt.Println("Encryption error:", err)
		return
	}
	fmt.Println("Encrypted:", string(encrypted))
	
	// Decrypt
	decrypted, err := decrypt(encrypted, key)
	if err != nil {
		fmt.Println("Decryption error:", err)
		return
	}
	fmt.Println("Decrypted:", string(decrypted))
}