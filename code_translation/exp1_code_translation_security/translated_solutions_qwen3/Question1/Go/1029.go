package main

import (
	"bytes"
	"crypto/aes"
	"errors"
)

// AESCipher represents an AES ECB cipher with a given key and block size.
type AESCipher struct {
	key   []byte
	blkSz int
}

// NewAESCipher creates a new AESCipher instance.
func NewAESCipher(key []byte, blkSz int) *AESCipher {
	return &AESCipher{
		key:   key,
		blkSz: blkSz,
	}
}

// Encrypt pads the message and encrypts it using AES in ECB mode.
func (a *AESCipher) Encrypt(msg []byte) ([]byte, error) {
	// PKCS#7 padding
	padLen := a.blkSz - (len(msg) % a.blkSz)
	padding := bytes.Repeat([]byte{byte(padLen)}, padLen)
	paddedMsg := append([]byte{}, msg...)
	paddedMsg = append(paddedMsg, padding...)

	// Create AES block cipher
	block, err := aes.NewCipher(a.key)
	if err != nil {
		return nil, err
	}

	// Ensure the padded message length is a multiple of the block size
	if len(paddedMsg)%a.blkSz != 0 {
		return nil, errors.New("padded message length not multiple of block size")
	}

	// ECB encryption
	ciphertext := make([]byte, len(paddedMsg))
	for i := 0; i < len(paddedMsg); i += a.blkSz {
		block.Encrypt(ciphertext[i:i+a.blkSz], paddedMsg[i:i+a.blkSz])
	}

	return ciphertext, nil
}

// Decrypt decrypts the ciphertext and removes PKCS#7 padding.
func (a *AESCipher) Decrypt(ciphertext []byte) ([]byte, error) {
	// Create AES block cipher
	block, err := aes.NewCipher(a.key)
	if err != nil {
		return nil, err
	}

	// Ensure ciphertext length is a multiple of the block size
	if len(ciphertext)%a.blkSz != 0 {
		return nil, errors.New("ciphertext length not multiple of block size")
	}

	// ECB decryption
	paddedMsg := make([]byte, len(ciphertext))
	for i := 0; i < len(ciphertext); i += a.blkSz {
		block.Decrypt(paddedMsg[i:i+a.blkSz], ciphertext[i:i+a.blkSz])
	}

	// Remove padding
	if len(paddedMsg) == 0 {
		return nil, errors.New("empty message after decryption")
	}

	padLen := int(paddedMsg[len(paddedMsg)-1])
	if padLen < 1 || padLen > a.blkSz {
		return nil, errors.New("invalid padding length")
	}

	return paddedMsg[:len(paddedMsg)-padLen], nil
}