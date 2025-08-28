package crypto

import (
	"errors"
	"time"

	"github.com/fernet/fernet-go"
)

// encrypt encrypts msg with key using Fernet (AES-128-CBC + HMAC-SHA256).
// key must be a URL-safe base64-encoded 32-byte string (same format as in Python).
func encrypt(msg []byte, key string) ([]byte, error) {
	var k fernet.Key
	if err := k.Decode(key); err != nil {
		return nil, err
	}
	// EncryptAndSign returns the Fernet token (cipher-text + MAC + metadata).
	return fernet.EncryptAndSign(msg, &k), nil
}

// decrypt decrypts ciphertext with key using Fernet (AES-128-CBC + HMAC-SHA256).
// Returns an error when the token is malformed, has an invalid MAC, or is otherwise
// not decryptable.
func decrypt(ciphertext []byte, key string) ([]byte, error) {
	var k fernet.Key
	if err := k.Decode(key); err != nil {
		return nil, err
	}

	// 0 means “no TTL/never expire”; mimic Python’s default behaviour.
	plain := fernet.VerifyAndDecrypt(ciphertext, 0*time.Second, &k)
	if plain == nil {
		return nil, errors.New("fernet: decryption failed or token expired")
	}
	return plain, nil
}