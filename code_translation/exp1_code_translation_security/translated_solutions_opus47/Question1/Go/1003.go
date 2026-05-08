package main

import (
	"crypto/aes"
	"errors"
)

func EncryptBytesAES(data, key []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	blockSize := block.BlockSize()
	if len(data)%blockSize != 0 {
		return nil, errors.New("data must be aligned to block boundary in ECB mode")
	}

	encrypted := make([]byte, len(data))
	for i := 0; i < len(data); i += blockSize {
		block.Encrypt(encrypted[i:i+blockSize], data[i:i+blockSize])
	}

	return encrypted, nil
}

func DecryptBytesAES(data, key []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	blockSize := block.BlockSize()
	if len(data)%blockSize != 0 {
		return nil, errors.New("data must be aligned to block boundary in ECB mode")
	}

	decrypted := make([]byte, len(data))
	for i := 0; i < len(data); i += blockSize {
		block.Decrypt(decrypted[i:i+blockSize], data[i:i+blockSize])
	}

	return decrypted, nil
}