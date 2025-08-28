package main

import (
	"errors"
	"time"

	"github.com/fernet/fernet-go"
)

// encrypt encrypts the given message using the provided 32-byte key.
// Returns the encrypted token as a byte slice or an error if encryption fails.
func encrypt(msg []byte, key []byte) ([]byte, error) {
	if len(key) != 32 {
		return nil, errors.New("invalid key length: must be 32 bytes")
	}

	var keyArray [32]byte
	copy(keyArray[:], key)

	timestamp := time.Now().Unix()
	message := fernet.NewMessage(msg, uint64(timestamp))

	token, err := fernet.Encrypt(message, keyArray)
	if err != nil {
		return nil, err
	}

	return []byte(token), nil
}

// decrypt decrypts the given Fernet token using the provided 32-byte key.
// Returns the original message as a byte slice or an error if decryption fails.
func decrypt(ciphertext []byte, key []byte) ([]byte, error) {
	if len(key) != 32 {
		return nil, errors.New("invalid key length: must be 32 bytes")
	}

	var keyArray [32]byte
	copy(keyArray[:], key)

	token := fernet.Token(ciphertext)
	message, err := token.Decrypt(keyArray, 0) // TTL = 0 disables timestamp check
	if err != nil {
		return nil, err
	}

	return message.Payload, nil
}