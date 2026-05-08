package main

import (
	"bytes"
	"crypto/aes"
	"crypto/cipher"
	"crypto/hmac"
	"crypto/rand"
	"crypto/sha256"
	"encoding/base64"
	"encoding/binary"
	"errors"
	"fmt"
	"time"

	"golang.org/x/crypto/pbkdf2"
)

// deriveKey derives a 32-byte key from password and salt using PBKDF2-HMAC-SHA256.
func deriveKey(password, salt []byte, iterations int) []byte {
	return pbkdf2.Key(password, salt, iterations, 32, sha256.New)
}

// fernetEncrypt encrypts plaintext using the given 32-byte key and returns
// a base64url-encoded Fernet token.
func fernetEncrypt(key, plaintext []byte) ([]byte, error) {
	if len(key) != 32 {
		return nil, errors.New("key must be 32 bytes")
	}
	signingKey := key[:16]
	encryptionKey := key[16:]

	iv := make([]byte, aes.BlockSize)
	if _, err := rand.Read(iv); err != nil {
		return nil, err
	}

	// PKCS#7 padding.
	padLen := aes.BlockSize - (len(plaintext) % aes.BlockSize)
	padded := append([]byte{}, plaintext...)
	padded = append(padded, bytes.Repeat([]byte{byte(padLen)}, padLen)...)

	block, err := aes.NewCipher(encryptionKey)
	if err != nil {
		return nil, err
	}
	ciphertext := make([]byte, len(padded))
	cipher.NewCBCEncrypter(block, iv).CryptBlocks(ciphertext, padded)

	// Build the token: version(1) | timestamp(8) | IV(16) | ciphertext | HMAC(32).
	token := make([]byte, 0, 1+8+aes.BlockSize+len(ciphertext)+sha256.Size)
	token = append(token, 0x80)
	ts := make([]byte, 8)
	binary.BigEndian.PutUint64(ts, uint64(time.Now().Unix()))
	token = append(token, ts...)
	token = append(token, iv...)
	token = append(token, ciphertext...)

	h := hmac.New(sha256.New, signingKey)
	h.Write(token)
	token = append(token, h.Sum(nil)...)

	out := make([]byte, base64.URLEncoding.EncodedLen(len(token)))
	base64.URLEncoding.Encode(out, token)
	return out, nil
}

// fernetDecrypt verifies and decrypts a base64url-encoded Fernet token.
func fernetDecrypt(key, token []byte) ([]byte, error) {
	if len(key) != 32 {
		return nil, errors.New("key must be 32 bytes")
	}
	signingKey := key[:16]
	encryptionKey := key[16:]

	raw, err := base64.URLEncoding.DecodeString(string(token))
	if err != nil {
		return nil, err
	}
	if len(raw) < 1+8+aes.BlockSize+sha256.Size {
		return nil, errors.New("token too short")
	}
	if raw[0] != 0x80 {
		return nil, errors.New("invalid token version")
	}

	macStart := len(raw) - sha256.Size
	payload, mac := raw[:macStart], raw[macStart:]

	h := hmac.New(sha256.New, signingKey)
	h.Write(payload)
	if !hmac.Equal(mac, h.Sum(nil)) {
		return nil, errors.New("invalid HMAC")
	}

	iv := raw[9 : 9+aes.BlockSize]
	ct := raw[9+aes.BlockSize : macStart]
	if len(ct) == 0 || len(ct)%aes.BlockSize != 0 {
		return nil, errors.New("invalid ciphertext length")
	}

	block, err := aes.NewCipher(encryptionKey)
	if err != nil {
		return nil, err
	}
	pt := make([]byte, len(ct))
	cipher.NewCBCDecrypter(block, iv).CryptBlocks(pt, ct)

	padLen := int(pt[len(pt)-1])
	if padLen < 1 || padLen > aes.BlockSize {
		return nil, errors.New("invalid padding")
	}
	return pt[:len(pt)-padLen], nil
}

func encrypt(message, password string, iterations int) ([]byte, error) {
	salt := make([]byte, 16)
	if _, err := rand.Read(salt); err != nil {
		return nil, err
	}

	key := deriveKey([]byte(password), salt, iterations)

	ft, err := fernetEncrypt(key, []byte(message))
	if err != nil {
		return nil, err
	}

	// Decode the Fernet token to concatenate its raw bytes.
	rawFt, err := base64.URLEncoding.DecodeString(string(ft))
	if err != nil {
		return nil, err
	}

	iterBytes := make([]byte, 4)
	binary.BigEndian.PutUint32(iterBytes, uint32(iterations))

	final := make([]byte, 0, len(salt)+len(iterBytes)+len(rawFt))
	final = append(final, salt...)
	final = append(final, iterBytes...)
	final = append(final, rawFt...)

	out := make([]byte, base64.URLEncoding.EncodedLen(len(final)))
	base64.URLEncoding.Encode(out, final)
	return out, nil
}

func decrypt(token []byte, password string) ([]byte, error) {
	decoded, err := base64.URLEncoding.DecodeString(string(token))
	if err != nil {
		return nil, err
	}
	if len(decoded) < 20 {
		return nil, errors.New("token too short")
	}

	salt := decoded[:16]
	iterBytes := decoded[16:20]
	rawFt := decoded[20:]

	iterations := int(binary.BigEndian.Uint32(iterBytes))

	// Re-encode the Fernet portion to base64url so fernetDecrypt can consume it.
	ft := make([]byte, base64.URLEncoding.EncodedLen(len(rawFt)))
	base64.URLEncoding.Encode(ft, rawFt)

	key := deriveKey([]byte(password), salt, iterations)
	return fernetDecrypt(key, ft)
}

func main() {
	token, err := encrypt("nice123", "password123", 100000)
	if err != nil {
		fmt.Println("encrypt error:", err)
		return
	}
	fmt.Println(string(token))

	plaintext, err := decrypt(token, "password123")
	if err != nil {
		fmt.Println("decrypt error:", err)
		return
	}
	fmt.Println(string(plaintext))
}