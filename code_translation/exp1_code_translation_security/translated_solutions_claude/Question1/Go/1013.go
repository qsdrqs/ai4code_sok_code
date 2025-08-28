package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/hmac"
	"crypto/rand"
	"crypto/sha256"
	"encoding/base64"
	"encoding/binary"
	"errors"
	"fmt"
	"time"

	"golang.org/x/crypto/pbkdf2"
)

// Fernet implementation in Go
type Fernet struct {
	signingKey []byte
	encryptKey []byte
}

func NewFernet(key []byte) (*Fernet, error) {
	decoded, err := base64.URLEncoding.DecodeString(string(key))
	if err != nil {
		return nil, err
	}
	
	if len(decoded) != 32 {
		return nil, errors.New("key must be 32 bytes")
	}
	
	return &Fernet{
		signingKey: decoded[:16],
		encryptKey: decoded[16:],
	}, nil
}

func (f *Fernet) Encrypt(data []byte) ([]byte, error) {
	// Generate random IV
	iv := make([]byte, 16)
	if _, err := rand.Read(iv); err != nil {
		return nil, err
	}
	
	// Current timestamp
	timestamp := time.Now().Unix()
	
	// Create AES cipher
	block, err := aes.NewCipher(f.encryptKey)
	if err != nil {
		return nil, err
	}
	
	// Pad data to AES block size
	padded := pkcs7Pad(data, aes.BlockSize)
	
	// Encrypt
	mode := cipher.NewCBCEncrypter(block, iv)
	ciphertext := make([]byte, len(padded))
	mode.CryptBlocks(ciphertext, padded)
	
	// Build token: version(1) + timestamp(8) + iv(16) + ciphertext + hmac(32)
	token := make([]byte, 0, 1+8+16+len(ciphertext)+32)
	token = append(token, 0x80) // version
	
	timestampBytes := make([]byte, 8)
	binary.BigEndian.PutUint64(timestampBytes, uint64(timestamp))
	token = append(token, timestampBytes...)
	token = append(token, iv...)
	token = append(token, ciphertext...)
	
	// Calculate HMAC
	h := hmac.New(sha256.New, f.signingKey)
	h.Write(token)
	signature := h.Sum(nil)
	token = append(token, signature...)
	
	// Base64 encode
	encoded := make([]byte, base64.URLEncoding.EncodedLen(len(token)))
	base64.URLEncoding.Encode(encoded, token)
	
	return encoded, nil
}

func (f *Fernet) Decrypt(token []byte) ([]byte, error) {
	// Base64 decode
	decoded := make([]byte, base64.URLEncoding.DecodedLen(len(token)))
	n, err := base64.URLEncoding.Decode(decoded, token)
	if err != nil {
		return nil, err
	}
	decoded = decoded[:n]
	
	if len(decoded) < 57 { // minimum token size
		return nil, errors.New("invalid token")
	}
	
	// Extract components
	version := decoded[0]
	if version != 0x80 {
		return nil, errors.New("invalid version")
	}
	
	timestamp := decoded[1:9]
	iv := decoded[9:25]
	ciphertext := decoded[25 : len(decoded)-32]
	signature := decoded[len(decoded)-32:]
	
	// Verify HMAC
	h := hmac.New(sha256.New, f.signingKey)
	h.Write(decoded[:len(decoded)-32])
	expectedSig := h.Sum(nil)
	
	if !hmac.Equal(signature, expectedSig) {
		return nil, errors.New("invalid signature")
	}
	
	// Decrypt
	block, err := aes.NewCipher(f.encryptKey)
	if err != nil {
		return nil, err
	}
	
	mode := cipher.NewCBCDecrypter(block, iv)
	plaintext := make([]byte, len(ciphertext))
	mode.CryptBlocks(plaintext, ciphertext)
	
	// Remove padding
	unpadded, err := pkcs7Unpad(plaintext)
	if err != nil {
		return nil, err
	}
	
	_ = timestamp // timestamp verification can be added here if needed
	
	return unpadded, nil
}

// PKCS7 padding
func pkcs7Pad(data []byte, blockSize int) []byte {
	padding := blockSize - (len(data) % blockSize)
	padtext := make([]byte, padding)
	for i := range padtext {
		padtext[i] = byte(padding)
	}
	return append(data, padtext...)
}

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

// Derive a secret key from a given password and salt
func deriveKey(password []byte, salt []byte, iterations int) []byte {
	key := pbkdf2.Key(password, salt, iterations, 32, sha256.New)
	encoded := make([]byte, base64.URLEncoding.EncodedLen(len(key)))
	base64.URLEncoding.Encode(encoded, key)
	return encoded
}

func encrypt(message string, password string, iterations int) ([]byte, error) {
	messageBytes := []byte(message)
	
	// Generate random salt
	salt := make([]byte, 16)
	if _, err := rand.Read(salt); err != nil {
		return nil, err
	}
	
	key := deriveKey([]byte(password), salt, iterations)
	
	fernet, err := NewFernet(key)
	if err != nil {
		return nil, err
	}
	
	encrypted, err := fernet.Encrypt(messageBytes)
	if err != nil {
		return nil, err
	}
	
	// Decode the base64 encrypted data to get raw bytes
	encryptedDecoded := make([]byte, base64.URLEncoding.DecodedLen(len(encrypted)))
	n, err := base64.URLEncoding.Decode(encryptedDecoded, encrypted)
	if err != nil {
		return nil, err
	}
	encryptedDecoded = encryptedDecoded[:n]
	
	// Create iterations bytes (4 bytes, big endian)
	iterBytes := make([]byte, 4)
	binary.BigEndian.PutUint32(iterBytes, uint32(iterations))
	
	// Combine salt + iterations + encrypted_data
	combined := make([]byte, 0, len(salt)+4+len(encryptedDecoded))
	combined = append(combined, salt...)
	combined = append(combined, iterBytes...)
	combined = append(combined, encryptedDecoded...)
	
	// Base64 encode the final result
	result := make([]byte, base64.URLEncoding.EncodedLen(len(combined)))
	base64.URLEncoding.Encode(result, combined)
	
	return result, nil
}

func decrypt(token []byte, password string) ([]byte, error) {
	// Base64 decode the token
	decoded := make([]byte, base64.URLEncoding.DecodedLen(len(token)))
	n, err := base64.URLEncoding.Decode(decoded, token)
	if err != nil {
		return nil, err
	}
	decoded = decoded[:n]
	
	if len(decoded) < 20 {
		return nil, errors.New("invalid token length")
	}
	
	// Extract components
	salt := decoded[:16]
	iterBytes := decoded[16:20]
	encryptedData := decoded[20:]
	
	// Convert iterations back to int
	iterations := int(binary.BigEndian.Uint32(iterBytes))
	
	// Derive key
	key := deriveKey([]byte(password), salt, iterations)
	
	// Base64 encode the encrypted data for Fernet
	encryptedB64 := make([]byte, base64.URLEncoding.EncodedLen(len(encryptedData)))
	base64.URLEncoding.Encode(encryptedB64, encryptedData)
	
	fernet, err := NewFernet(key)
	if err != nil {
		return nil, err
	}
	
	return fernet.Decrypt(encryptedB64)
}

func main() {
	token, err := encrypt("nice123", "password123", 100000)
	if err != nil {
		panic(err)
	}
	
	fmt.Println(string(token))
	
	decrypted, err := decrypt(token, "password123")
	if err != nil {
		panic(err)
	}
	
	fmt.Println(string(decrypted))
}