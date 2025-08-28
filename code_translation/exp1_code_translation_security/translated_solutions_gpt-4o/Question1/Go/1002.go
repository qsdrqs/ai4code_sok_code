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

func encrypt(data, key, iv []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	if len(data)%aes.BlockSize != 0 {
		return nil, fmt.Errorf("data is not a multiple of the block size")
	}

	ciphertext := make([]byte, len(data))
	mode := cipher.NewCBCEncrypter(block, iv)
	mode.CryptBlocks(ciphertext, data)

	return ciphertext, nil
}

func decrypt(data, key, iv []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	if len(data)%aes.BlockSize != 0 {
		return nil, fmt.Errorf("data is not a multiple of the block size")
	}

	plaintext := make([]byte, len(data))
	mode := cipher.NewCBCDecrypter(block, iv)
	mode.CryptBlocks(plaintext, data)

	return plaintext, nil
}

func test() {
	data := []byte("a secret message")
	key := make([]byte, 32)
	iv := make([]byte, aes.BlockSize)

	if _, err := io.ReadFull(rand.Reader, key); err != nil {
		log.Fatal(err)
	}
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		log.Fatal(err)
	}

	// Pad data to be a multiple of the block size
	padding := aes.BlockSize - len(data)%aes.BlockSize
	padtext := bytes.Repeat([]byte{byte(padding)}, padding)
	data = append(data, padtext...)

	e, err := encrypt(data, key, iv)
	if err != nil {
		log.Fatal(err)
	}

	d, err := decrypt(e, key, iv)
	if err != nil {
		log.Fatal(err)
	}

	// Remove padding
	d = d[:len(d)-int(d[len(d)-1])]

	if !bytes.Equal(d, []byte("a secret message")) {
		log.Fatal("decrypted data does not match original")
	}

	fmt.Println("Encryption and decryption successful")
}

func main() {
	test()
}