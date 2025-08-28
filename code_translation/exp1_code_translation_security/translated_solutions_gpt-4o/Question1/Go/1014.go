package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"crypto/sha256"
	"encoding/base64"
	"fmt"
	"io"
)

type AESCipher struct {
	key []byte
	bs  int
}

func NewAESCipher(key string) *AESCipher {
	hash := sha256.Sum256([]byte(key))
	return &AESCipher{
		key: hash[:],
		bs:  32,
	}
}

func (a *AESCipher) Encrypt(raw string) (string, error) {
	rawPadded := a.pad([]byte(raw))
	block, err := aes.NewCipher(a.key)
	if err != nil {
		return "", err
	}

	ciphertext := make([]byte, aes.BlockSize+len(rawPadded))
	iv := ciphertext[:aes.BlockSize]
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		return "", err
	}

	mode := cipher.NewCBCEncrypter(block, iv)
	mode.CryptBlocks(ciphertext[aes.BlockSize:], rawPadded)

	return base64.StdEncoding.EncodeToString(ciphertext), nil
}

func (a *AESCipher) Decrypt(enc string) (string, error) {
	ciphertext, err := base64.StdEncoding.DecodeString(enc)
	if err != nil {
		return "", err
	}

	block, err := aes.NewCipher(a.key)
	if err != nil {
		return "", err
	}

	if len(ciphertext) < aes.BlockSize {
		return "", fmt.Errorf("ciphertext too short")
	}

	iv := ciphertext[:aes.BlockSize]
	ciphertext = ciphertext[aes.BlockSize:]

	mode := cipher.NewCBCDecrypter(block, iv)
	mode.CryptBlocks(ciphertext, ciphertext)

	plaintext := a.unpad(ciphertext)
	return string(plaintext), nil
}

func (a *AESCipher) pad(src []byte) []byte {
	padding := a.bs - len(src)%a.bs
	padtext := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(src, padtext...)
}

func (a *AESCipher) unpad(src []byte) []byte {
	length := len(src)
	unpadding := int(src[length-1])
	return src[:(length - unpadding)]
}

func main() {
	key := "mysecretpassword"
	cipher := NewAESCipher(key)

	encrypted, err := cipher.Encrypt("Secret Message A")
	if err != nil {
		fmt.Println("Error encrypting:", err)
		return
	}

	decrypted, err := cipher.Decrypt(encrypted)
	if err != nil {
		fmt.Println("Error decrypting:", err)
		return
	}

	fmt.Printf("Encrypted: %s\n", encrypted)
	fmt.Printf("Decrypted: %s\n", decrypted)
}