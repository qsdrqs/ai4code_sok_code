package main

import (
	"fmt"
	"github.com/fernet/fernet-go"
)

// Encrypts a message using the provided key.
func encrypt(msg []byte, key fernet.Key) (string, error) {
	return fernet.Encrypt(msg, key)
}

// Decrypts a token using the provided key.
func decrypt(token string, key fernet.Key) ([]byte, error) {
	return fernet.Decrypt(token, key, 0) // 0 means no TTL check
}

func main() {
	// Generate a new Fernet key string
	keyString, err := fernet.GenerateKeyString()
	if err != nil {
		panic(err)
	}

	// Decode the key string into a Fernet Key struct
	keys, err := fernet.DecodeKeys(keyString)
	if err != nil {
		panic(err)
	}
	key := keys[0]

	// Message to encrypt
	msg := []byte("a much longer message with punctuation!")

	// Encrypt the message
	e, err := encrypt(msg, key)
	if err != nil {
		panic(err)
	}

	// Decrypt the encrypted token
	d, err := decrypt(e, key)
	if err != nil {
		panic(err)
	}

	// Print the encrypted token and decrypted message
	fmt.Println(e)
	fmt.Println(string(d))
}