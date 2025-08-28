package main

import (
	"bytes"
	"crypto/aes"
	"fmt"
	"log"
)

// encode encrypts inp with key using AES-ECB (no padding, caller
// must supply data whose length is a multiple of aes.BlockSize).
func encode(inp, key []byte) []byte {
	block, err := aes.NewCipher(key)
	if err != nil {
		log.Fatalf("unable to create cipher: %v", err)
	}

	if len(inp)%aes.BlockSize != 0 {
		log.Fatalf("plaintext length (%d) is not a multiple of block size (%d)",
			len(inp), aes.BlockSize)
	}

	out := make([]byte, len(inp))
	for bs := 0; bs < len(inp); bs += aes.BlockSize {
		block.Encrypt(out[bs:bs+aes.BlockSize], inp[bs:bs+aes.BlockSize])
	}
	return out
}

// decode decrypts inp with key using AES-ECB (no padding).
func decode(inp, key []byte) []byte {
	block, err := aes.NewCipher(key)
	if err != nil {
		log.Fatalf("unable to create cipher: %v", err)
	}

	if len(inp)%aes.BlockSize != 0 {
		log.Fatalf("ciphertext length (%d) is not a multiple of block size (%d)",
			len(inp), aes.BlockSize)
	}

	out := make([]byte, len(inp))
	for bs := 0; bs < len(inp); bs += aes.BlockSize {
		block.Decrypt(out[bs:bs+aes.BlockSize], inp[bs:bs+aes.BlockSize])
	}
	return out
}

func main() {
	inp := []byte("1111111111111111") // 16 bytes, exactly one AES block
	key := []byte("1111111111111111") // 16-byte (128-bit) key

	ciphertext := encode(inp, key)
	plaintext := decode(ciphertext, key)

	if !bytes.Equal(plaintext, inp) {
		log.Fatal("decode(encode(inp, key), key) != inp")
	}

	fmt.Printf("Plaintext:  %x\n", inp)
	fmt.Printf("Ciphertext: %x\n", ciphertext)
	fmt.Printf("Round-trip successful: %v\n", bytes.Equal(plaintext, inp))
}