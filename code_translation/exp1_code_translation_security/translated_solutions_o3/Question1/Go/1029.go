package main

import (
	"bytes"
	"crypto/aes"
	"fmt"
	"log"
)

// AESCipher is a tiny helper that mimics the Python version shown in the prompt.
type AESCipher struct {
	key     []byte
	blkSize int
}

// NewAESCipher returns a ready-to-use cipher object.
// (blkSize is automatically derived from the underlying AES block size.)
func NewAESCipher(key []byte) (*AESCipher, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}
	return &AESCipher{
		key:     key,
		blkSize: block.BlockSize(),
	}, nil
}

// Encrypt does PKCS-7 padding and then AES-ECB encryption.
func (c *AESCipher) Encrypt(plain []byte) ([]byte, error) {
	plain = c.pkcs7Pad(plain)

	block, err := aes.NewCipher(c.key)
	if err != nil {
		return nil, err
	}

	cipherText := make([]byte, len(plain))
	for i := 0; i < len(plain); i += c.blkSize {
		block.Encrypt(cipherText[i:i+c.blkSize], plain[i:i+c.blkSize])
	}
	return cipherText, nil
}

// Decrypt performs AES-ECB decryption and removes PKCS-7 padding.
func (c *AESCipher) Decrypt(cipherText []byte) ([]byte, error) {
	if len(cipherText)%c.blkSize != 0 {
		return nil, fmt.Errorf("ciphertext is not a multiple of the block size")
	}

	block, err := aes.NewCipher(c.key)
	if err != nil {
		return nil, err
	}

	plain := make([]byte, len(cipherText))
	for i := 0; i < len(cipherText); i += c.blkSize {
		block.Decrypt(plain[i:i+c.blkSize], cipherText[i:i+c.blkSize])
	}

	return c.pkcs7Unpad(plain)
}

// --- internal helpers --------------------------------------------------------

func (c *AESCipher) pkcs7Pad(src []byte) []byte {
	padLen := c.blkSize - len(src)%c.blkSize
	padding := bytes.Repeat([]byte{byte(padLen)}, padLen)
	return append(src, padding...)
}

func (c *AESCipher) pkcs7Unpad(src []byte) ([]byte, error) {
	if len(src) == 0 {
		return nil, fmt.Errorf("empty plaintext after decryption")
	}
	padLen := int(src[len(src)-1])
	if padLen == 0 || padLen > c.blkSize || padLen > len(src) {
		return nil, fmt.Errorf("invalid padding")
	}
	return src[:len(src)-padLen], nil
}

// -----------------------------------------------------------------------------
// Small test/demo

func main() {
	key := []byte("example key 1234") // 16-byte key for AES-128

	aesCipher, err := NewAESCipher(key)
	if err != nil {
		log.Fatal(err)
	}

	plain := []byte("Hello ECB world!")
	fmt.Println("Plaintext:", string(plain))

	cipherText, err := aesCipher.Encrypt(plain)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Printf("Cipher (hex): %x\n", cipherText)

	recovered, err := aesCipher.Decrypt(cipherText)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("Recovered:", string(recovered))
}