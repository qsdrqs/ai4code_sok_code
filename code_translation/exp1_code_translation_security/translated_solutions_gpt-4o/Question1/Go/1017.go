package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"errors"
	"fmt"
	"io"
)

// Encrypt encrypts the given plaintext using the provided key.
func Encrypt(key, plaintext []byte) (nonce, tag, ciphertext []byte, err error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, nil, nil, err
	}

	aesgcm, err := cipher.NewGCM(block)
	if err != nil {
		return nil, nil, nil, err
	}

	nonce = make([]byte, aesgcm.NonceSize())
	if _, err = io.ReadFull(rand.Reader, nonce); err != nil {
		return nil, nil, nil, err
	}

	ciphertext = aesgcm.Seal(nil, nonce, plaintext, nil)
	tag = ciphertext[len(ciphertext)-aesgcm.Overhead():]
	return nonce, tag, ciphertext[:len(ciphertext)-aesgcm.Overhead()], nil
}

// Decrypt decrypts the given ciphertext using the provided key.
func Decrypt(key []byte, nonce, tag, ciphertext []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	aesgcm, err := cipher.NewGCM(block)
	if err != nil {
		return nil, err
	}

	fullCiphertext := append(ciphertext, tag...)
	plaintext, err := aesgcm.Open(nil, nonce, fullCiphertext, nil)
	if err != nil {
		return nil, err
	}

	return plaintext, nil
}

func main() {
	key := []byte("examplekey123456") // 16 bytes key for AES-128
	plaintext := []byte("Hello, World!")

	nonce, tag, ciphertext, err := Encrypt(key, plaintext)
	if err != nil {
		fmt.Println("Encryption error:", err)
		return
	}

	fmt.Printf("Nonce: %x\nTag: %x\nCiphertext: %x\n", nonce, tag, ciphertext)

	decrypted, err := Decrypt(key, nonce, tag, ciphertext)
	if err != nil {
		fmt.Println("Decryption error:", err)
		return
	}

	fmt.Printf("Decrypted: %s\n", decrypted)
}