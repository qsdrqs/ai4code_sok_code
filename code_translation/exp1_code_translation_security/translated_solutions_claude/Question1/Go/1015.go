package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"crypto/sha256"
	"encoding/base64"
	"fmt"
	"io"
	"log"
)

// Fernet-like encryption implementation in Go
type Fernet struct {
	key []byte
}

// NewFernet creates a new Fernet instance with the given key
func NewFernet(key []byte) *Fernet {
	return &Fernet{key: key}
}

// GenerateKey generates a new 32-byte key for Fernet encryption
func GenerateKey() []byte {
	key := make([]byte, 32)
	if _, err := rand.Read(key); err != nil {
		log.Fatal(err)
	}
	return key
}

// encrypt encrypts the given data using AES-GCM
func (f *Fernet) encrypt(data []byte) ([]byte, error) {
	// Create cipher block
	block, err := aes.NewCipher(f.key)
	if err != nil {
		return nil, err
	}

	// Create GCM mode
	gcm, err := cipher.NewGCM(block)
	if err != nil {
		return nil, err
	}

	// Generate nonce
	nonce := make([]byte, gcm.NonceSize())
	if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
		return nil, err
	}

	// Encrypt data
	ciphertext := gcm.Seal(nonce, nonce, data, nil)
	
	// Encode to base64 for compatibility with Fernet format
	encoded := make([]byte, base64.StdEncoding.EncodedLen(len(ciphertext)))
	base64.StdEncoding.Encode(encoded, ciphertext)
	
	return encoded, nil
}

// decrypt decrypts the given data using AES-GCM
func (f *Fernet) decrypt(data []byte) ([]byte, error) {
	// Decode from base64
	decoded := make([]byte, base64.StdEncoding.DecodedLen(len(data)))
	n, err := base64.StdEncoding.Decode(decoded, data)
	if err != nil {
		return nil, err
	}
	decoded = decoded[:n]

	// Create cipher block
	block, err := aes.NewCipher(f.key)
	if err != nil {
		return nil, err
	}

	// Create GCM mode
	gcm, err := cipher.NewGCM(block)
	if err != nil {
		return nil, err
	}

	// Extract nonce and ciphertext
	nonceSize := gcm.NonceSize()
	if len(decoded) < nonceSize {
		return nil, fmt.Errorf("ciphertext too short")
	}

	nonce, ciphertext := decoded[:nonceSize], decoded[nonceSize:]

	// Decrypt data
	plaintext, err := gcm.Open(nil, nonce, ciphertext, nil)
	if err != nil {
		return nil, err
	}

	return plaintext, nil
}

// encrypt function equivalent to Python version
func encrypt(data []byte, key []byte) ([]byte, error) {
	fernet := NewFernet(key)
	encrypted, err := fernet.encrypt(data)
	return encrypted, err
}

// decrypt function equivalent to Python version
func decrypt(data []byte, key []byte) ([]byte, error) {
	fernet := NewFernet(key)
	decrypted, err := fernet.decrypt(data)
	return decrypted, err
}

func main() {
	// Note: The original Python code has some issues:
	// 1. It tries to encrypt a file path string, not file contents
	// 2. The decrypt function has a variable name error (encrypted vs decrypted)
	// 3. It doesn't handle the encrypted result
	
	// Here's a corrected version that encrypts the file path as a string
	filePath := "/safedir/test.txt"
	key := GenerateKey()
	
	encrypted, err := encrypt([]byte(filePath), key)
	if err != nil {
		log.Fatal(err)
	}
	
	fmt.Printf("Encrypted: %s\n", string(encrypted))
	
	// Demonstrate decryption as well
	decrypted, err := decrypt(encrypted, key)
	if err != nil {
		log.Fatal(err)
	}
	
	fmt.Printf("Decrypted: %s\n", string(decrypted))
}