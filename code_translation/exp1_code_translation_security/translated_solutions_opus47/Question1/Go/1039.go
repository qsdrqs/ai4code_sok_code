package main

import (
	"fmt"
	"time"

	"github.com/fernet/fernet-go"
)

func encrypt(message string, key string) ([]byte, error) {
	k, err := fernet.DecodeKey(key)
	if err != nil {
		return nil, err
	}
	encMessage, err := fernet.EncryptAndSign([]byte(message), k)
	if err != nil {
		return nil, err
	}
	return encMessage, nil
}

func decrypt(message []byte, key string) ([]byte, error) {
	k, err := fernet.DecodeKey(key)
	if err != nil {
		return nil, err
	}
	// Negative TTL disables the expiration check (matches Python's default `ttl=None`).
	decMessage := fernet.VerifyAndDecrypt(message, time.Duration(-1), []*fernet.Key{k})
	if decMessage == nil {
		return nil, fmt.Errorf("failed to verify or decrypt token")
	}
	return decMessage, nil
}