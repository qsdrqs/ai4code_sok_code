package main

import (
	"fmt"
	"time"

	"github.com/fernet/fernet-go"
)

func encrypt(input string, key string) ([]byte, error) {
	k, err := fernet.DecodeKey(key)
	if err != nil {
		return nil, err
	}
	encMessage, err := fernet.EncryptAndSign([]byte(input), k)
	if err != nil {
		return nil, err
	}
	return encMessage, nil
}

func decrypt(input []byte, key string) (string, error) {
	k, err := fernet.DecodeKey(key)
	if err != nil {
		return "", err
	}
	// Python's Fernet.decrypt does not enforce a TTL by default, so we use a
	// very large TTL to approximate the same behavior.
	msg := fernet.VerifyAndDecrypt(input, time.Hour*24*365*100, []*fernet.Key{k})
	if msg == nil {
		return "", fmt.Errorf("failed to decrypt or verify token")
	}
	return string(msg), nil
}