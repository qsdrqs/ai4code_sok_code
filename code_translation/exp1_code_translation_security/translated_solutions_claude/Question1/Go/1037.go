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
	decoded, err := base64.URLEncoding.DecodeString(string(key))
	if err != nil {
		return nil, err
	}
	
	if len(decoded) != 32 {
		return nil, errors.New("key must be 32 bytes")
	}
	
	return &Fernet{
		signingKey:    decoded[:16],
		encryptionKey: decoded[16:],
	}, nil
}

// GenerateKey generates a new Fernet key
func GenerateKey() []byte {
	key := make([]byte, 32)
	rand.Read(key)
	return []byte(base64.URLEncoding.EncodeToString(key))
}

// Encrypt encrypts the given plaintext
func (f *Fernet) Encrypt(plaintext []byte) ([]byte, error) {
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
	
	// Pad plaintext
	paddedPlaintext := pkcs7Pad(plaintext, aes.BlockSize)
	
	// Encrypt
	mode := cipher.NewCBCEncrypter(block, iv)
	ciphertext := make([]byte, len(paddedPlaintext))
	mode.CryptBlocks(ciphertext, paddedPlaintext)
	
	// Create timestamp (8 bytes, big endian)
	timestamp := time.Now().Unix()
	timestampBytes := make([]byte, 8)
	for i := 7; i >= 0; i-- {
		timestampBytes[i] = byte(timestamp & 0xff)
		timestamp >>= 8
	}
	
	// Combine version + timestamp + iv + ciphertext
	version := byte(0x80)
	token := make([]byte, 0, 1+8+len(iv)+len(ciphertext))
	token = append(token, version)
	token = append(token, timestampBytes...)
	token = append(token, iv...)
	token = append(token, ciphertext...)
	
	// Create HMAC
	h := hmac.New(sha256.New, f.signingKey)
	h.Write(token)
	signature := h.Sum(nil)
	
	// Append signature
	token = append(token, signature...)
	
	// Base64 encode
	return []byte(base64.URLEncoding.EncodeToString(token)), nil
}

// Decrypt decrypts the given ciphertext
func (f *Fernet) Decrypt(ciphertext []byte) ([]byte, error) {
	// Base64 decode
	decoded, err := base64.URLEncoding.DecodeString(string(ciphertext))
	if err != nil {
		return nil, err
	}
	
	if len(decoded) < 57 { // 1 + 8 + 16 + 16 + 16 (min size)
		return nil, errors.New("invalid token")
	}
	
	// Extract components
	version := decoded[0]
	if version != 0x80 {
		return nil, errors.New("invalid version")
	}
	
	timestampBytes := decoded[1:9]
	iv := decoded[9:25]
	encryptedData := decoded[25 : len(decoded)-32]
	signature := decoded[len(decoded)-32:]
	
	// Verify HMAC
	h := hmac.New(sha256.New, f.signingKey)
	h.Write(decoded[:len(decoded)-32])
	expectedSignature := h.Sum(nil)
	
	if !hmac.Equal(signature, expectedSignature) {
		return nil, errors.New("invalid signature")
	}
	
	// Check timestamp (optional - Fernet tokens expire after some time)
	var timestamp int64
	for i := 0; i < 8; i++ {
		timestamp = (timestamp << 8) | int64(timestampBytes[i])
	}
	
	// Create AES cipher
	block, err := aes.NewCipher(f.encryptionKey)
	if err != nil {
		return nil, err
	}
	
	// Decrypt
	mode := cipher.NewCBCDecrypter(block, iv)
	plaintext := make([]byte, len(encryptedData))
	mode.CryptBlocks(plaintext, encryptedData)
	
	// Remove padding
	return pkcs7Unpad(plaintext)
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

func encrypt(input []byte, key []byte) ([]byte, error) {
	f, err := NewFernet(key)
	if err != nil {
		return nil, err
	}
	return f.Encrypt(input)
}

func decrypt(input []byte, key []byte) ([]byte, error) {
	f, err := NewFernet(key)
	if err != nil {
		return nil, err
	}
	return f.Decrypt(input)
}

func main() {
	key := GenerateKey()
	input := []byte("super secret")
	
	encryptedInput, err := encrypt(input, key)
	if err != nil {
		fmt.Printf("Encryption error: %v\n", err)
		return
	}
	
	decryptedInput, err := decrypt(encryptedInput, key)
	if err != nil {
		fmt.Printf("Decryption error: %v\n", err)
		return
	}
	
	fmt.Printf("%s\n", input)
	fmt.Printf("%s\n", decryptedInput)
}