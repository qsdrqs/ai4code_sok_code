package main

import (
	"crypto/fernet"
	"encoding/base64"
	"errors"
	"fmt"
	"log"

	"github.com/fernet-go/fernet"
)

// GenerateKey generates a new Fernet key.
func GenerateKey() ([]byte, error) {
	return fernet.GenerateKey()
}

// Encrypt encrypts a message using the provided key.
func Encrypt(msg []byte, key []byte) ([]byte, error) {
	f, err := fernet.NewFernet(key)
	if err != nil {
		return nil, err
	}
	return f.Encrypt(msg)
}

// Decrypt decrypts a ciphertext using the provided key.
func Decrypt(ciphertext []byte, key []byte) ([]byte, error) {
	f, err := fernet.NewFernet(key)
	if err != nil {
		return nil, err
	}
	return f.Decrypt(ciphertext)
}

func main() {
	// Generate a new Fernet key
	key, err := GenerateKey()
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("Generated Key:", base64.StdEncoding.EncodeToString(key))

	// Message to encrypt
	msg := []byte("Hello, World!")

	// Encrypt the message
	ciphertext, err := Encrypt(msg, key)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("Encrypted Message:", base64.StdEncoding.EncodeToString(ciphertext))

	// Decrypt the ciphertext
	decryptedMsg, err := Decrypt(ciphertext, key)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("Decrypted Message:", string(decryptedMsg))
}