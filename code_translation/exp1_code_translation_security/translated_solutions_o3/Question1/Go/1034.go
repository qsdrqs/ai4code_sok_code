package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"crypto/sha256"
	"encoding/base64"
	"fmt"
	"io"
)

// deriveKey turns an arbitrary‐length pass-phrase into a fixed 32-byte key
// (AES-256) via SHA-256.
func deriveKey(passphrase string) []byte {
	sum := sha256.Sum256([]byte(passphrase))
	return sum[:] // 32-byte slice
}

// encodeStr encrypts plainText with the supplied key (pass-phrase) and
// returns a base64 string that contains nonce || ciphertext.
func encodeStr(plainText, key string) (string, error) {
	block, err := aes.NewCipher(deriveKey(key))
	if err != nil {
		return "", err
	}

	gcm, err := cipher.NewGCM(block) // AES-GCM provides authenticated encryption
	if err != nil {
		return "", err
	}

	nonce := make([]byte, gcm.NonceSize())
	if _, err = io.ReadFull(rand.Reader, nonce); err != nil {
		return "", err
	}

	// Seal: dst = nonce, append the encrypted data after the nonce
	ciphertext := gcm.Seal(nonce, nonce, []byte(plainText), nil)

	return base64.StdEncoding.EncodeToString(ciphertext), nil
}

// decodeStr reverses encodeStr.
func decodeStr(encoded, key string) (string, error) {
	blob, err := base64.StdEncoding.DecodeString(encoded)
	if err != nil {
		return "", err
	}

	block, err := aes.NewCipher(deriveKey(key))
	if err != nil {
		return "", err
	}

	gcm, err := cipher.NewGCM(block)
	if err != nil {
		return "", err
	}

	if len(blob) < gcm.NonceSize() {
		return "", fmt.Errorf("ciphertext too short")
	}

	nonce, ciphertext := blob[:gcm.NonceSize()], blob[gcm.NonceSize():]

	plain, err := gcm.Open(nil, nonce, ciphertext, nil)
	if err != nil {
		return "", err
	}

	return string(plain), nil
}

func main() {
	// Demonstration (mirrors the original Python snippet)
	encoded, err := encodeStr("I am okay", "wow")
	if err != nil {
		panic(err)
	}

	decoded, err := decodeStr(encoded, "wow")
	if err != nil {
		panic(err)
	}

	fmt.Println(decoded) // Output: I am okay
}