package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/hmac"
	"crypto/rand"
	"crypto/sha256"
	"encoding/base64"
	"errors"
	"fmt"
	"time"

	"golang.org/x/crypto/pbkdf2"
)

func main() {
	token, err := encrypt("nice123", "password123")
	if err != nil {
		panic(err)
	}
	fmt.Println("Encrypted Token:", string(token))

	decrypted, err := decrypt(token, "password123")
	if err != nil {
		panic(err)
	}
	fmt.Println("Decrypted Message:", string(decrypted))
}

// encrypt encrypts a message using a password.
func encrypt(message, password string) ([]byte, error) {
	messageBytes := []byte(message)

	// Generate a 16-byte salt
	salt := make([]byte, 16)
	if _, err := rand.Read(salt); err != nil {
		return nil, err
	}

	// Derive a 32-byte key using PBKDF2
	iterations := 100000
	derivedKey := pbkdf2.Key([]byte(password), salt, iterations, 32, sha256.New)

	// Split into signing and encryption keys
	signingKey := derivedKey[:16]
	encryptionKey := derivedKey[16:]

	// Generate a 16-byte IV
	iv := make([]byte, 16)
	if _, err := rand.Read(iv); err != nil {
		return nil, err
	}

	// Get current timestamp in microseconds
	timestamp := uint64(time.Now().UnixNano() / 1000)

	// Pad the message
	block, err := aes.NewCipher(encryptionKey)
	if err != nil {
		return nil, err
	}
	padded := pkcs7Pad(messageBytes, block.BlockSize())

	// Encrypt the message
	ciphertext := make([]byte, len(padded))
	cipher.NewCBCEncrypter(block, iv).CryptBlocks(ciphertext, padded)

	// Build the Fernet token data
	version := []byte{0x80}
	timestampBytes := make([]byte, 8)
	binary.BigEndian.PutUint64(timestampBytes, timestamp)

	dataToSign := append(version, timestampBytes...)
	dataToSign = append(dataToSign, iv...)
	dataToSign = append(dataToSign, ciphertext...)

	// Compute HMAC
	hmacBytes, err := computeHMAC(dataToSign, signingKey)
	if err != nil {
		return nil, err
	}

	// Final Fernet token
	fernetToken := append(dataToSign, hmacBytes...)

	// Prepend salt and iteration count
	iterBytes := make([]byte, 4)
	binary.BigEndian.PutUint32(iterBytes, uint32(iterations))

	encryptedData := append(salt, iterBytes...)
	encryptedData = append(encryptedData, fernetToken...)

	// Base64 encode
	encoded := make([]byte, base64.RawURLEncoding.EncodedLen(len(encryptedData)))
	base64.RawURLEncoding.Encode(encoded, encryptedData)
	return encoded, nil
}

// decrypt decrypts a token using a password.
func decrypt(tokenBytes []byte, password string) ([]byte, error) {
	// Base64 decode
	decoded := make([]byte, base64.RawURLEncoding.DecodedLen(len(tokenBytes)))
	n, err := base64.RawURLEncoding.Decode(decoded, tokenBytes)
	if err != nil {
		return nil, err
	}
	decoded = decoded[:n]

	// Extract salt, iteration count, and Fernet token
	if len(decoded) < 20 {
		return nil, errors.New("invalid token length")
	}
	salt := decoded[:16]
	iterBytes := decoded[16:20]
	iterations := binary.BigEndian.Uint32(iterBytes)
	fernetToken := decoded[20:]

	// Derive the key
	derivedKey := pbkdf2.Key([]byte(password), salt, int(iterations), 32, sha256.New)
	signingKey := derivedKey[:16]
	encryptionKey := derivedKey[16:]

	// Parse and decrypt the Fernet token
	return parseFernetToken(fernetToken, encryptionKey, signingKey)
}

// parseFernetToken verifies and decrypts a Fernet token.
func parseFernetToken(token []byte, encKey, signKey []byte) ([]byte, error) {
	if len(token) < 49 {
		return nil, errors.New("token too short")
	}

	// Extract version, timestamp, IV, ciphertext, and HMAC
	version := token[0]
	if version != 0x80 {
		return nil, errors.New("invalid version")
	}

	timestampBytes := token[1:9]
	iv := token[9:25]
	dataEnd := len(token) - 32
	if dataEnd < 25 {
		return nil, errors.New("invalid token length")
	}
	ciphertext := token[25:dataEnd]
	receivedHMAC := token[dataEnd:]

	// Re-calculate HMAC
	dataToSign := token[:dataEnd]
	expectedHMAC, err := computeHMAC(dataToSign, signKey)
	if err != nil {
		return nil, err
	}
	if !hmac.Equal(receivedHMAC, expectedHMAC) {
		return nil, errors.New("invalid HMAC")
	}

	// Decrypt
	block, err := aes.NewCipher(encKey)
	if err != nil {
		return nil, err
	}
	plaintextPadded := make([]byte, len(ciphertext))
	cipher.NewCBCDecrypter(block, iv).CryptBlocks(plaintextPadded, ciphertext)

	// Unpad
	return pkcs7Unpad(plaintextPadded, block.BlockSize())
}

// computeHMAC computes HMAC-SHA256 of the given data.
func computeHMAC(data, key []byte) ([]byte, error) {
	mac := hmac.New(sha256.New, key)
	if _, err := mac.Write(data); err != nil {
		return nil, err
	}
	return mac.Sum(nil), nil
}

// pkcs7Pad pads data to the block size using PKCS7.
func pkcs7Pad(data []byte, blockSize int) []byte {
	padding := blockSize - (len(data) % blockSize)
	return append(data, bytes.Repeat([]byte{byte(padding)}, padding)...)
}

// pkcs7Unpad removes PKCS7 padding.
func pkcs7Unpad(data []byte, blockSize int) ([]byte, error) {
	if len(data) == 0 {
		return nil, errors.New("empty data")
	}
	padding := int(data[len(data)-1])
	if padding == 0 || padding > blockSize {
		return nil, errors.New("invalid padding")
	}
	for i := len(data) - padding; i < len(data); i++ {
		if data[i] != byte(padding) {
			return nil, errors.New("invalid padding")
		}
	}
	return data[:len(data)-padding], nil
}