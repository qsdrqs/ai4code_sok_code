package main

import (
	"crypto/aes"
	"fmt"
)

func encryptBytesAES(data, key []byte) ([]byte, error) {
	// Create a new AES cipher block
	cipher, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	// Ensure the data length is a multiple of the block size
	if len(data)%aes.BlockSize != 0 {
		return nil, fmt.Errorf("data length must be a multiple of %d bytes", aes.BlockSize)
	}

	// Create a slice to hold the encrypted data
	encrypted := make([]byte, len(data))

	// Encrypt the data block by block
	for i := 0; i < len(data); i += aes.BlockSize {
		cipher.Encrypt(encrypted[i:i+aes.BlockSize], data[i:i+aes.BlockSize])
	}

	return encrypted, nil
}

func decryptBytesAES(data, key []byte) ([]byte, error) {
	// Create a new AES cipher block
	cipher, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	// Ensure the data length is a multiple of the block size
	if len(data)%aes.BlockSize != 0 {
		return nil, fmt.Errorf("data length must be a multiple of %d bytes", aes.BlockSize)
	}

	// Create a slice to hold the decrypted data
	decrypted := make([]byte, len(data))

	// Decrypt the data block by block
	for i := 0; i < len(data); i += aes.BlockSize {
		cipher.Decrypt(decrypted[i:i+aes.BlockSize], data[i:i+aes.BlockSize])
	}

	return decrypted, nil
}

func main() {
	// Example usage
	key := []byte("1234567890123456") // 16-byte key for AES-128
	data := []byte("exampleplaintext") // Must be a multiple of 16 bytes for AES

	encrypted, err := encryptBytesAES(data, key)
	if err != nil {
		fmt.Println("Encryption error:", err)
		return
	}
	fmt.Printf("Encrypted: %x\n", encrypted)

	decrypted, err := decryptBytesAES(encrypted, key)
	if err != nil {
		fmt.Println("Decryption error:", err)
		return
	}
	fmt.Printf("Decrypted: %s\n", decrypted)
}