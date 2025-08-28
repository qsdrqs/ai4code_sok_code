package cryptohelpers

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"errors"
	"io"
)

// Encrypt behaves like the Python encrypt() helper:
//   * converts the message to bytes
//   * pads it (PKCS-7)
//   * creates an AES-CBC cipher with a random IV
//   * returns IV || ciphertext  (IV is needed for decryption)
func Encrypt(message string, key []byte) ([]byte, error) {
	plain := []byte(message)

	// PKCS-7 padding
	plain = pkcs7Pad(plain, aes.BlockSize)

	// AES block cipher
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	// Allocate output:  IV + ciphertext
	out := make([]byte, aes.BlockSize+len(plain))
	iv := out[:aes.BlockSize]

	// Random IV
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		return nil, err
	}

	// CBC encryption
	mode := cipher.NewCBCEncrypter(block, iv)
	mode.CryptBlocks(out[aes.BlockSize:], plain)

	return out, nil
}

// Decrypt mirrors the Python decrypt() helper:
//   * splits IV and ciphertext
//   * AES-CBC decrypts
//   * removes PKCS-7 padding
//   * converts back to string
func Decrypt(ciphertext, key []byte) (string, error) {
	if len(ciphertext) < aes.BlockSize {
		return "", errors.New("ciphertext too short")
	}

	iv := ciphertext[:aes.BlockSize]
	ct := ciphertext[aes.BlockSize:]

	if len(ct)%aes.BlockSize != 0 {
		return "", errors.New("ciphertext is not a multiple of the block size")
	}

	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	mode := cipher.NewCBCDecrypter(block, iv)

	plain := make([]byte, len(ct))
	mode.CryptBlocks(plain, ct)

	plain, err = pkcs7Unpad(plain, aes.BlockSize)
	if err != nil {
		return "", err
	}

	return string(plain), nil
}

// ---- PKCS-7 helpers ----

func pkcs7Pad(data []byte, blockSize int) []byte {
	padLen := blockSize - (len(data) % blockSize)
	padding := bytes.Repeat([]byte{byte(padLen)}, padLen)
	return append(data, padding...)
}

func pkcs7Unpad(data []byte, blockSize int) ([]byte, error) {
	if len(data) == 0 || len(data)%blockSize != 0 {
		return nil, errors.New("invalid padding (size)")
	}

	padLen := int(data[len(data)-1])
	if padLen == 0 || padLen > blockSize {
		return nil, errors.New("invalid padding (value)")
	}

	// Verify padding bytes
	for _, b := range data[len(data)-padLen:] {
		if int(b) != padLen {
			return nil, errors.New("invalid padding (content)")
		}
	}

	return data[:len(data)-padLen], nil
}