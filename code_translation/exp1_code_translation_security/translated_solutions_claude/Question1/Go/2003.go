package main

import (
	"crypto/aes"
	"fmt"
)

// encode encrypts a byte slice using an AES key in ECB mode
func encode(inp []byte, key []byte) ([]byte, error) {
	cipher, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}
	
	// Create output slice with same length as input
	encrypted := make([]byte, len(inp))
	
	// ECB mode: encrypt each block independently
	blockSize := cipher.BlockSize()
	for i := 0; i < len(inp); i += blockSize {
		cipher.Encrypt(encrypted[i:i+blockSize], inp[i:i+blockSize])
	}
	
	return encrypted, nil
}

// decode decrypts a byte slice using an AES key in ECB mode
func decode(inp []byte, key []byte) ([]byte, error) {
	cipher, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}
	
	// Create output slice with same length as input
	decrypted := make([]byte, len(inp))
	
	// ECB mode: decrypt each block independently
	blockSize := cipher.BlockSize()
	for i := 0; i < len(inp); i += blockSize {
		cipher.Decrypt(decrypted[i:i+blockSize], inp[i:i+blockSize])
	}
	
	return decrypted, nil
}

func main() {
	inp := []byte("1111111111111111")
	key := []byte("1111111111111111")
	
	// Test the encode/decode functions
	encoded, err := encode(inp, key)
	if err != nil {
		panic(err)
	}
	
	decoded, err := decode(encoded, key)
	if err != nil {
		panic(err)
	}
	
	// Assert that decode(encode(inp, key), key) == inp
	if string(decoded) != string(inp) {
		panic("Assertion failed: decoded data doesn't match original input")
	}
	
	fmt.Println("Test passed: encode/decode works correctly")
}