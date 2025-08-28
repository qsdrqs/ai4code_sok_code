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

// encrypt performs AES-CBC encryption.
// The input length must be a multiple of aes.BlockSize (16 bytes).
func encrypt(data, key, iv []byte) ([]byte, error) {
	if len(data)%aes.BlockSize != 0 {
		return nil, fmt.Errorf("plaintext is not a multiple of the block size")
	}
	if len(iv) != aes.BlockSize {
		return nil, fmt.Errorf("IV length must be %d bytes", aes.BlockSize)
	}

	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	cipherText := make([]byte, len(data))
	mode := cipher.NewCBCEncrypter(block, iv)
	mode.CryptBlocks(cipherText, data)

	return cipherText, nil
}

// decrypt performs AES-CBC decryption.
// The input length must be a multiple of aes.BlockSize (16 bytes).
func decrypt(data, key, iv []byte) ([]byte, error) {
	if len(data)%aes.BlockSize != 0 {
		return nil, fmt.Errorf("ciphertext is not a multiple of the block size")
	}
	if len(iv) != aes.BlockSize {
		return nil, fmt.Errorf("IV length must be %d bytes", aes.BlockSize)
	}

	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	plainText := make([]byte, len(data))
	mode := cipher.NewCBCDecrypter(block, iv)
	mode.CryptBlocks(plainText, data)

	return plainText, nil
}

func test() {
	// 16-byte (one-block) message, exactly as in the Python code.
	data := []byte("a secret message")

	// 32-byte random key for AES-256.
	key := make([]byte, 32)
	// 16-byte IV.
	iv := make([]byte, aes.BlockSize)

	if _, err := io.ReadFull(rand.Reader, key); err != nil {
		log.Fatal(err)
	}
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		log.Fatal(err)
	}

	cipherText, err := encrypt(data, key, iv)
	if err != nil {
		log.Fatal(err)
	}

	plainText, err := decrypt(cipherText, key, iv)
	if err != nil {
		log.Fatal(err)
	}

	if !bytes.Equal(plainText, data) {
		log.Fatalf("decryption failed: got %q, want %q", plainText, data)
	}
	fmt.Println("success: decrypted text matches original")
}

func main() {
	test()
}