package fernetutil

import (
	"time"

	"github.com/fernet/fernet-go"
)

// Encrypt works the same way as the Python version:
//   encMessage = Fernet(key).encrypt(message.encode())
//
// `key` must be the URL-safe base-64 string that represents a
// Fernet key (exactly what Python’s cryptography.fernet uses).
//
// The returned value is the generated Fernet token (cipher-text).
func Encrypt(message string, key string) ([]byte, error) {
	var fKey fernet.Key
	if err := fKey.FromString(key); err != nil {
		return nil, err
	}

	token := fernet.EncryptAndSign([]byte(message), &fKey)
	return token, nil
}

// Decrypt mirrors the Python call:
//   decMessage = Fernet(key).decrypt(token)
//
// `token` is the data returned by Encrypt (or any valid Fernet token
// created with the same key).  It returns the original plain-text
// message.
//
// `maxAge` is set to 0 so the token’s age is not checked, which is the
// closest behaviour to Python’s default.
func Decrypt(token []byte, key string) (string, error) {
	var fKey fernet.Key
	if err := fKey.FromString(key); err != nil {
		return "", err
	}

	plain, err := fernet.VerifyAndDecrypt(token, 0*time.Second, []*fernet.Key{&fKey})
	if err != nil {
		return "", err
	}

	return string(plain), nil
}