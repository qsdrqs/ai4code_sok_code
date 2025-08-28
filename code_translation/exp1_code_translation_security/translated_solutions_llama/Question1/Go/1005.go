package main

import (
	"crypto/rand"
	"encoding/base64"
	"fmt"
	"log"

	"github.com/fernet-go/fernet"
)

// GenerateKey generates a new Fernet key.
func GenerateKey() ([]byte, error) {
	key := make([]byte, 32)
	if _, err := rand.Read(key); err != nil {
		return nil, err
	}
	return key, nil
}

// Encrypt encrypts a message using a Fernet key.
func Encrypt(msg, key []byte) ([]byte, error) {
	f, err := fernet.NewFernet(key)
	if err != nil {
		return nil, err
	}
	return f.Encrypt(msg)
}

// Decrypt decrypts a message using a Fernet key.
func Decrypt(msg, key []byte) ([]byte, error) {
	f, err := fernet.NewFernet(key)
	if err != nil {
		return nil, err
	}
	return f.Decrypt(msg)
}

func main() {
	// Generate a new Fernet key.
	key, err := GenerateKey()
	if err != nil {
		log.Fatal(err)
	}

	// Base64 encode the key for printing.
	encodedKey := base64.StdEncoding.EncodeToString(key)
	fmt.Printf("Generated Key: %s\n", encodedKey)

	// Message to encrypt.
	msg := []byte("a much longer message with punctuation!")

	// Encrypt the message.
	e, err := Encrypt(msg, key)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Printf("Encrypted Message: %s\n", e)

	// Decrypt the message.
	d, err := Decrypt(e, key)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Printf("Decrypted Message: %s\n", d)
}