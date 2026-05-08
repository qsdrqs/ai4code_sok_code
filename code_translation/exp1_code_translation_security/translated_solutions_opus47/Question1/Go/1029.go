package main

import (
	"bytes"
	"crypto/aes"
)

type AEScipher struct {
	Key   []byte
	BlkSz int
}

func NewAEScipher(key []byte, blkSz int) *AEScipher {
	return &AEScipher{
		Key:   key,
		BlkSz: blkSz,
	}
}

func (a *AEScipher) Encrypt(msg []byte) ([]byte, error) {
	// Padding (PKCS7)
	padLen := a.BlkSz - (len(msg) % a.BlkSz)
	padding := bytes.Repeat([]byte{byte(padLen)}, padLen)
	msg = append(msg, padding...)

	// Encryption
	block, err := aes.NewCipher(a.Key)
	if err != nil {
		return nil, err
	}

	ciphertext := make([]byte, len(msg))
	for start := 0; start < len(msg); start += a.BlkSz {
		block.Encrypt(ciphertext[start:start+a.BlkSz], msg[start:start+a.BlkSz])
	}

	return ciphertext, nil
}

func (a *AEScipher) Decrypt(ciphertext []byte) ([]byte, error) {
	// Decryption
	block, err := aes.NewCipher(a.Key)
	if err != nil {
		return nil, err
	}

	msg := make([]byte, len(ciphertext))
	for start := 0; start < len(ciphertext); start += a.BlkSz {
		block.Decrypt(msg[start:start+a.BlkSz], ciphertext[start:start+a.BlkSz])
	}

	// Remove padding
	padLen := int(msg[len(msg)-1])
	return msg[:len(msg)-padLen], nil
}