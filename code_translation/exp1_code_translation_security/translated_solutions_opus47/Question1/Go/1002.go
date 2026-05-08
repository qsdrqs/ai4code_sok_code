package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"fmt"
)

func encrypt(data, key, iv []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}
	mode := cipher.NewCBCEncrypter(block, iv)
	ct := make([]byte, len(data))
	mode.CryptBlocks(ct, data)
	return ct, nil
}

func decrypt(data, key, iv []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}
	mode := cipher.NewCBCDecrypter(block, iv)
	pt := make([]byte, len(data))
	mode.CryptBlocks(pt, data)
	return pt, nil
}

func test() error {
	data := []byte("a secret message")

	key := make([]byte, 32)
	if _, err := rand.Read(key); err != nil {
		return err
	}

	iv := make([]byte, 16)
	if _, err := rand.Read(iv); err != nil {
		return err
	}

	e, err := encrypt(data, key, iv)
	if err != nil {
		return err
	}

	d, err := decrypt(e, key, iv)
	if err != nil {
		return err
	}

	if !bytes.Equal(d, data) {
		return fmt.Errorf("assertion failed: decrypted data does not match original")
	}
	return nil
}

func main() {
	if err := test(); err != nil {
		fmt.Println("Test failed:", err)
		return
	}
	fmt.Println("Test passed")
}