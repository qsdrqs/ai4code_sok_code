package cryptohelpers

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"encoding/base64"
	"errors"
	"io"
)

// PKCS#7 padding -------------------------------------------------------------

// pad appends PKCS#7 padding to src so that its length becomes
// an even multiple of aes.BlockSize (16 bytes).
func pad(src []byte) []byte {
	padding := aes.BlockSize - len(src)%aes.BlockSize
	padText := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(src, padText...)
}

// unpad removes PKCS#7 padding that pad added above.
func unpad(src []byte) ([]byte, error) {
	length := len(src)
	if length == 0 || length%aes.BlockSize != 0 {
		return nil, errors.New("invalid padded data")
	}
	padding := int(src[length-1])
	if padding == 0 || padding > aes.BlockSize || padding > length {
		return nil, errors.New("invalid padding")
	}
	for _, v := range src[length-padding:] {
		if int(v) != padding {
			return nil, errors.New("invalid padding")
		}
	}
	return src[:length-padding], nil
}

// ---------------------------------------------------------------------------

// Encrypt mimics `encrypt` in the Python sample.
// keySize is kept only for compatibility with the original signature;
// AES‐CBC uses the key length directly (16, 24 or 32 bytes).
func Encrypt(key []byte, keySize int, plaintext []byte) (string, error) {
	if len(key) != keySize {
		return "", errors.New("key length does not match keySize")
	}

	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	// Generate random IV (16 bytes = AES block size).
	iv := make([]byte, aes.BlockSize)
	if _, err = io.ReadFull(rand.Reader, iv); err != nil {
		return "", err
	}

	// Pad, encrypt.
	padded := pad(plaintext)
	ciphertext := make([]byte, len(padded))
	mode := cipher.NewCBCEncrypter(block, iv)
	mode.CryptBlocks(ciphertext, padded)

	// Prepend IV and base64-encode.
	out := append(iv, ciphertext...)
	return base64.StdEncoding.EncodeToString(out), nil
}

// Decrypt reproduces the Python `decrypt` function.
func Decrypt(key []byte, keySize int, b64Input string) ([]byte, error) {
	if len(key) != keySize {
		return nil, errors.New("key length does not match keySize")
	}

	raw, err := base64.StdEncoding.DecodeString(b64Input)
	if err != nil {
		return nil, err
	}
	if len(raw) < aes.BlockSize {
		return nil, errors.New("ciphertext too short")
	}

	iv, cipherData := raw[:aes.BlockSize], raw[aes.BlockSize:]

	if len(cipherData)%aes.BlockSize != 0 {
		return nil, errors.New("ciphertext is not a multiple of the block size")
	}

	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	mode := cipher.NewCBCDecrypter(block, iv)
	plainPadded := make([]byte, len(cipherData))
	mode.CryptBlocks(plainPadded, cipherData)

	return unpad(plainPadded)
}