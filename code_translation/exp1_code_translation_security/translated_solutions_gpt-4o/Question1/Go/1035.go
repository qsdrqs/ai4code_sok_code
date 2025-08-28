package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"fmt"
	"io"
	"log"
)

func encrypt(key []byte, msg []byte) (ciphertext []byte, tag []byte, nonce []byte, err error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, nil, nil, err
	}

	nonce = make([]byte, 12) // AES-GCM standard nonce size is 12 bytes
	if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
		return nil, nil, nil, err
	}

	aesgcm, err := cipher.NewGCM(block)
	if err != nil {
		return nil, nil, nil, err
	}

	ciphertext = aesgcm.Seal(nil, nonce, msg, nil)
	tag = ciphertext[len(ciphertext)-aesgcm.Overhead():]
	return ciphertext[:len(ciphertext)-aesgcm.Overhead()], tag, nonce, nil
}

func decrypt(key []byte, ciphertext []byte, nonce []byte, tag []byte) (plaintext []byte, err error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	aesgcm, err := cipher.NewGCM(block)
	if err != nil {
		return nil, err
	}

	fullCiphertext := append(ciphertext, tag...)
	plaintext, err = aesgcm.Open(nil, nonce, fullCiphertext, nil)
	if err != nil {
		return nil, err
	}

	return plaintext, nil
}

func main() {
	key := []byte("Sixteen byte key")
	msg := []byte("message")

	ctext, tag, nonce, err := encrypt(key, msg)
	if err != nil {
		log.Fatalf("encryption failed: %v", err)
	}

	plaintext, err := decrypt(key, ctext, nonce, tag)
	if err != nil {
		log.Fatalf("decryption failed: %v", err)
	}

	fmt.Println(string(plaintext))
}