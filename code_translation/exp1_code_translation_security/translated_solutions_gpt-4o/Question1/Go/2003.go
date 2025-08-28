package main

import (
	"bytes"
	"crypto/aes"
	"fmt"
)

func encode(inp, key []byte) ([]byte, error) {
	/*
		Encodes a string using an AES key.
	*/
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	// Ensure the input length is a multiple of the block size
	blockSize := block.BlockSize()
	inp = pad(inp, blockSize)

	encrypted := make([]byte, len(inp))
	for i := 0; i < len(inp); i += blockSize {
		block.Encrypt(encrypted[i:i+blockSize], inp[i:i+blockSize])
	}

	return encrypted, nil
}

func decode(inp, key []byte) ([]byte, error) {
	/*
		Decodes a string using an AES key.
	*/
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	blockSize := block.BlockSize()
	decrypted := make([]byte, len(inp))
	for i := 0; i < len(inp); i += blockSize {
		block.Decrypt(decrypted[i:i+blockSize], inp[i:i+blockSize])
	}

	// Remove padding
	decrypted = unpad(decrypted)

	return decrypted, nil
}

func pad(data []byte, blockSize int) []byte {
	padding := blockSize - len(data)%blockSize
	padText := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(data, padText...)
}

func unpad(data []byte) []byte {
	length := len(data)
	unpadding := int(data[length-1])
	return data[:(length - unpadding)]
}

func main() {
	inp := []byte("1111111111111111")
	key := []byte("1111111111111111")

	encoded, err := encode(inp, key)
	if err != nil {
		fmt.Println("Error encoding:", err)
		return
	}

	decoded, err := decode(encoded, key)
	if err != nil {
		fmt.Println("Error decoding:", err)
		return
	}

	if bytes.Equal(decoded, inp) {
		fmt.Println("Success: Decoded value matches the input.")
	} else {
		fmt.Println("Failure: Decoded value does not match the input.")
	}
}