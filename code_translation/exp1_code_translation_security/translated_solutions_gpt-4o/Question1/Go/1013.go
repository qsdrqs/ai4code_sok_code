package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"crypto/sha256"
	"encoding/base64"
	"encoding/binary"
	"fmt"
	"golang.org/x/crypto/pbkdf2"
	"io"
)

func deriveKey(password, salt []byte, iterations int) []byte {
	return pbkdf2.Key(password, salt, iterations, 32, sha256.New)
}

func encrypt(message, password string, iterations int) (string, error) {
	messageBytes := []byte(message)
	salt := make([]byte, 16)
	if _, err := io.ReadFull(rand.Reader, salt); err != nil {
		return "", err
	}

	key := deriveKey([]byte(password), salt, iterations)
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	aesGCM, err := cipher.NewGCM(block)
	if err != nil {
		return "", err
	}

	nonce := make([]byte, aesGCM.NonceSize())
	if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
		return "", err
	}

	ciphertext := aesGCM.Seal(nonce, nonce, messageBytes, nil)

	var buffer bytes.Buffer
	buffer.Write(salt)
	iterBytes := make([]byte, 4)
	binary.BigEndian.PutUint32(iterBytes, uint32(iterations))
	buffer.Write(iterBytes)
	buffer.Write(ciphertext)

	return base64.URLEncoding.EncodeToString(buffer.Bytes()), nil
}

func decrypt(token, password string) (string, error) {
	decoded, err := base64.URLEncoding.DecodeString(token)
	if err != nil {
		return "", err
	}

	salt := decoded[:16]
	iterBytes := decoded[16:20]
	iterations := int(binary.BigEndian.Uint32(iterBytes))
	ciphertext := decoded[20:]

	key := deriveKey([]byte(password), salt, iterations)
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	aesGCM, err := cipher.NewGCM(block)
	if err != nil {
		return "", err
	}

	nonceSize := aesGCM.NonceSize()
	nonce, ciphertext := ciphertext[:nonceSize], ciphertext[nonceSize:]

	plaintext, err := aesGCM.Open(nil, nonce, ciphertext, nil)
	if err != nil {
		return "", err
	}

	return string(plaintext), nil
}

func main() {
	token, err := encrypt("nice123", "password123", 100000)
	if err != nil {
		fmt.Println("Encryption error:", err)
		return
	}
	fmt.Println("Encrypted:", token)

	decrypted, err := decrypt(token, "password123")
	if err != nil {
		fmt.Println("Decryption error:", err)
		return
	}
	fmt.Println("Decrypted:", decrypted)
}