package main

import (
	"fmt"
	"time"

	"github.com/fernet/fernet-go"
)

// Function to encrypt msg with key using Fernet (AES-128-CBC with HMAC)
func encrypt(msg []byte, key []byte) ([]byte, error) {
	k, err := fernet.DecodeKey(string(key))
	if err != nil {
		return nil, err
	}
	return fernet.EncryptAndSign(msg, k)
}

// Function to decrypt msg with key using Fernet (AES-128-CBC with HMAC)
func decrypt(ciphertext []byte, key []byte) ([]byte, error) {
	k, err := fernet.DecodeKey(string(key))
	if err != nil {
		return nil, err
	}
	// Python's Fernet.decrypt has no TTL check by default, so we use a very
	// large TTL here to effectively disable the TTL verification.
	msg := fernet.VerifyAndDecrypt(ciphertext, time.Duration(1<<62), []*fernet.Key{k})
	if msg == nil {
		return nil, fmt.Errorf("decryption failed: invalid token or signature")
	}
	return msg, nil
}