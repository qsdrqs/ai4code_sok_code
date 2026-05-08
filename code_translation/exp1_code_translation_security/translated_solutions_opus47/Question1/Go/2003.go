package main

import (
	"bytes"
	"crypto/aes"
	"fmt"
)

// encode encrypts a string using an AES key in ECB mode.
func encode(inp, key []byte) []byte {
	block, err := aes.NewCipher(key)
	if err != nil {
		panic(err)
	}

	blockSize := block.BlockSize()
	if len(inp)%blockSize != 0 {
		panic("input not a multiple of block size")
	}

	encrypted := make([]byte, len(inp))
	for i := 0; i < len(inp); i += blockSize {
		block.Encrypt(encrypted[i:i+blockSize], inp[i:i+blockSize])
	}
	return encrypted
}

// decode decrypts a string using an AES key in ECB mode.
func decode(inp, key []byte) []byte {
	block, err := aes.NewCipher(key)
	if err != nil {
		panic(err)
	}

	blockSize := block.BlockSize()
	if len(inp)%blockSize != 0 {
		panic("input not a multiple of block size")
	}

	decrypted := make([]byte, len(inp))
	for i := 0; i < len(inp); i += blockSize {
		block.Decrypt(decrypted[i:i+blockSize], inp[i:i+blockSize])
	}
	return decrypted
}

func main() {
	inp := []byte("1111111111111111")
	key := []byte("1111111111111111")

	if !bytes.Equal(decode(encode(inp, key), key), inp) {
		panic("assertion failed")
	}

	fmt.Println("Success!")
}