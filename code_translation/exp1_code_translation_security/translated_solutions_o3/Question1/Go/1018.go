package cryptohelper

import (
	"time"

	"github.com/fernet/fernet-go"
)

// Encrypt behaves like Python’s  cryptography.fernet.Fernet.encrypt.
//   input – clear-text to encrypt
//   key   – base-64 encoded 32-byte Fernet key (same format as Python)
//
// It returns the raw encrypted token (the Python version returns bytes as well).
func Encrypt(input, key string) ([]byte, error) {
	var k fernet.Key
	// Convert the base-64 text key into a usable *fernet.Key
	if err := k.UnmarshalText([]byte(key)); err != nil {
		return nil, err
	}

	// Encrypt and sign; the result is identical to the Python token.
	token := fernet.EncryptAndSign([]byte(input), &k)
	return token, nil
}

// Decrypt is equivalent to Python’s  cryptography.fernet.Fernet.decrypt.
//   token – data produced by Encrypt (or Python Fernet.encrypt)
//   key   – the same base-64 encoded key used for encryption
//
// It returns the original clear-text string.
func Decrypt(token []byte, key string) (string, error) {
	var k fernet.Key
	if err := k.UnmarshalText([]byte(key)); err != nil {
		return "", err
	}

	// Zero duration ⇒ no TTL check (mirrors Python default).
	plaintext, err := fernet.VerifyAndDecrypt(token, 0*time.Second, []*fernet.Key{&k})
	if err != nil {
		return "", err
	}
	return string(plaintext), nil
}