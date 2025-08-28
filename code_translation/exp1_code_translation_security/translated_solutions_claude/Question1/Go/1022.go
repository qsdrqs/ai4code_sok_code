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
func NewFernet(key []byte) (*Fernet, error) {
	if len(key) != 32 {
		return nil, errors.New("key must be 32 bytes")
	}
	
	return &Fernet{
		signingKey:    key[:16],
		encryptionKey: key[16:],
	}, nil
}

// GenerateKey generates a new 32-byte key for Fernet
func GenerateKey() ([]byte, error) {
	key := make([]byte, 32)
	_, err := rand.Read(key)
	if err != nil {
		return nil, err
	}
	return key, nil
}

// Encrypt encrypts the given plaintext
func (f *Fernet) Encrypt(plaintext []byte) ([]byte, error) {
	// Create AES cipher
	block, err := aes.NewCipher(f.encryptionKey)
	if err != nil {
		return nil, err
	}

	// Generate random IV
	iv := make([]byte, aes.BlockSize)
	if _, err := rand.Read(iv); err != nil {
		return nil, err
	}

	// Create timestamp (8 bytes, big endian)
	timestamp := time.Now().Unix()
	timestampBytes := make([]byte, 8)
	for i := 7; i >= 0; i-- {
		timestampBytes[i] = byte(timestamp & 0xff)
		timestamp >>= 8
	}

	// Pad plaintext to block size
	padding := aes.BlockSize - len(plaintext)%aes.BlockSize
	padded := make([]byte, len(plaintext)+padding)
	copy(padded, plaintext)
	for i := len(plaintext); i < len(padded); i++ {
		padded[i] = byte(padding)
	}

	// Encrypt
	mode := cipher.NewCBCEncrypter(block, iv)
	ciphertext := make([]byte, len(padded))
	mode.CryptBlocks(ciphertext, padded)

	// Create token: version (1 byte) + timestamp (8 bytes) + iv (16 bytes) + ciphertext + hmac (32 bytes)
	version := byte(0x80)
	token := make([]byte, 1+8+16+len(ciphertext))
	token[0] = version
	copy(token[1:9], timestampBytes)
	copy(token[9:25], iv)
	copy(token[25:], ciphertext)

	// Calculate HMAC
	h := hmac.New(sha256.New, f.signingKey)
	h.Write(token)
	signature := h.Sum(nil)

	// Append HMAC to token
	finalToken := make([]byte, len(token)+len(signature))
	copy(finalToken, token)
	copy(finalToken[len(token):], signature)

	// Base64 encode
	encoded := base64.URLEncoding.EncodeToString(finalToken)
	return []byte(encoded), nil
}

// Decrypt decrypts the given ciphertext
func (f *Fernet) Decrypt(token []byte) ([]byte, error) {
	// Base64 decode
	decoded, err := base64.URLEncoding.DecodeString(string(token))
	if err != nil {
		return nil, err
	}

	if len(decoded) < 57 { // minimum size: 1 + 8 + 16 + 16 + 32
		return nil, errors.New("invalid token")
	}

	// Extract components
	tokenData := decoded[:len(decoded)-32]
	signature := decoded[len(decoded)-32:]

	// Verify HMAC
	h := hmac.New(sha256.New, f.signingKey)
	h.Write(tokenData)
	expectedSignature := h.Sum(nil)
	if !hmac.Equal(signature, expectedSignature) {
		return nil, errors.New("invalid signature")
	}

	// Extract version, timestamp, iv, and ciphertext
	version := tokenData[0]
	if version != 0x80 {
		return nil, errors.New("invalid version")
	}

	iv := tokenData[9:25]
	ciphertext := tokenData[25:]

	// Create AES cipher
	block, err := aes.NewCipher(f.encryptionKey)
	if err != nil {
		return nil, err
	}

	// Decrypt
	if len(ciphertext)%aes.BlockSize != 0 {
		return nil, errors.New("invalid ciphertext length")
	}

	mode := cipher.NewCBCDecrypter(block, iv)
	plaintext := make([]byte, len(ciphertext))
	mode.CryptBlocks(plaintext, ciphertext)

	// Remove padding
	if len(plaintext) == 0 {
		return nil, errors.New("invalid padding")
	}
	padding := int(plaintext[len(plaintext)-1])
	if padding > aes.BlockSize || padding == 0 {
		return nil, errors.New("invalid padding")
	}
	
	return plaintext[:len(plaintext)-padding], nil
}

// Helper functions to match the Python API
func encrypt(inp string, key []byte) ([]byte, error) {
	fernet, err := NewFernet(key)
	if err != nil {
		return nil, err
	}
	return fernet.Encrypt([]byte(inp))
}

func decrypt(inp []byte, key []byte) (string, error) {
	fernet, err := NewFernet(key)
	if err != nil {
		return "", err
	}
	decrypted, err := fernet.Decrypt(inp)
	if err != nil {
		return "", err
	}
	return string(decrypted), nil
}

func main() {
	// Generate key
	exKey, err := GenerateKey()
	if err != nil {
		panic(err)
	}

	// Encrypt
	encrypted, err := encrypt("Hello World!", exKey)
	if err != nil {
		panic(err)
	}
	fmt.Println(string(encrypted))

	// Decrypt
	decrypted, err := decrypt(encrypted, exKey)
	if err != nil {
		panic(err)
	}
	fmt.Println(decrypted)
}