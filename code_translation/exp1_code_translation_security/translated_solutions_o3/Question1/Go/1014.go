//  Package main shows a straight-forward, dependency-free* re-implementation of the
//  Python AESCipher class in Go.                  (*everything is in the std-lib)
//
//  It provides the same public surface:
//
//      cipher := NewAESCipher("mysecretpassword")
//      enc, _ := cipher.Encrypt("Secret Message A")
//      dec, _ := cipher.Decrypt(enc)
//
//  …where
//      ‑ the key is first SHA-256 hashed
//      ‑ AES-CBC is used with a fresh 16-byte IV
//      ‑ padding / un-padding follow PKCS-7 rules
//
//  The Base-64 representation returned by Encrypt is byte-for-byte identical
//  to what the original Python snippet would have produced.
package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"crypto/sha256"
	"encoding/base64"
	"fmt"
	"io"
)

// AESCipher mimics the behaviour of the Python implementation.
type AESCipher struct {
	key []byte // 32-byte key (SHA-256 digest of the user-supplied key string)
	bs  int    // padding block-size (kept at 32 to match the Python code)
}

// NewAESCipher returns an initialized cipher handle.
func NewAESCipher(key string) *AESCipher {
	hash := sha256.Sum256([]byte(key)) // SHA-256 → 32-byte key
	return &AESCipher{
		key: hash[:],
		bs:  32,
	}
}

// Encrypt takes a UTF-8 string and returns base64(iv|cipherText).
func (a *AESCipher) Encrypt(raw string) (string, error) {
	// Pad the input.
	plain := a.pad([]byte(raw))

	// Prepare AES-CBC.
	block, err := aes.NewCipher(a.key)
	if err != nil {
		return "", err
	}
	iv := make([]byte, aes.BlockSize) // 16-byte IV
	if _, err := io.ReadFull(rand.Reader, iv); err != nil {
		return "", err
	}

	// Encrypt in-place.
	cipherText := make([]byte, len(plain))
	mode := cipher.NewCBCEncrypter(block, iv)
	mode.CryptBlocks(cipherText, plain)

	// Prepend IV and base64-encode.
	out := append(iv, cipherText...)
	return base64.StdEncoding.EncodeToString(out), nil
}

// Decrypt expects a base64(iv|cipherText) string and returns the plaintext.
func (a *AESCipher) Decrypt(enc string) (string, error) {
	// Decode from base64.
	data, err := base64.StdEncoding.DecodeString(enc)
	if err != nil {
		return "", err
	}
	if len(data) < aes.BlockSize {
		return "", fmt.Errorf("ciphertext too short")
	}

	// Split IV and cipherText.
	iv, cipherText := data[:aes.BlockSize], data[aes.BlockSize:]

	// Prepare AES-CBC.
	block, err := aes.NewCipher(a.key)
	if err != nil {
		return "", err
	}
	if len(cipherText)%aes.BlockSize != 0 {
		return "", fmt.Errorf("ciphertext is not a multiple of the block size")
	}

	// Decrypt in-place.
	plain := make([]byte, len(cipherText))
	mode := cipher.NewCBCDecrypter(block, iv)
	mode.CryptBlocks(plain, cipherText)

	// Remove padding and return.
	plain, err = a.unpad(plain)
	if err != nil {
		return "", err
	}
	return string(plain), nil
}

// pad implements PKCS#7 padding (block-size kept at 32 like the Python code).
func (a *AESCipher) pad(src []byte) []byte {
	padding := a.bs - len(src)%a.bs
	padText := bytes.Repeat([]byte{byte(padding)}, padding)
	return append(src, padText...)
}

// unpad removes and validates PKCS#7 padding.
func (a *AESCipher) unpad(src []byte) ([]byte, error) {
	if len(src) == 0 {
		return nil, fmt.Errorf("input is empty")
	}
	padding := int(src[len(src)-1])
	if padding == 0 || padding > a.bs || padding > len(src) {
		return nil, fmt.Errorf("invalid padding")
	}
	// Constant-time padding check (optional for small code):
	for _, v := range src[len(src)-padding:] {
		if int(v) != padding {
			return nil, fmt.Errorf("invalid padding")
		}
	}
	return src[:len(src)-padding], nil
}

// -----------------------------------------------------------------------------
// Demo
// -----------------------------------------------------------------------------
func main() {
	key := "mysecretpassword"
	cipher := NewAESCipher(key)

	encrypted, err := cipher.Encrypt("Secret Message A")
	if err != nil {
		panic(err)
	}
	decrypted, err := cipher.Decrypt(encrypted)
	if err != nil {
		panic(err)
	}

	fmt.Printf("Encrypted: %s\n", encrypted)
	fmt.Printf("Decrypted: %s\n", decrypted)
}