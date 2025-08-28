package main

import (
	"crypto/fernet"
	"encoding/base64"
	"errors"
	"fmt"
	"log"

	"github.com/fernet-go/fernet"
)

// Encrypt encrypts a message using a provided key.
func Encrypt(message string, key []byte) ([]byte, error) {
	f, err := fernet.NewFernet(key)
	if err != nil {
		return nil, err
	}
	encMessage, err := f.Encrypt([]byte(message))
	if err != nil {
		return nil, err
	}
	return encMessage, nil
}

// Decrypt decrypts a message using a provided key.
func Decrypt(message []byte, key []byte) (string, error) {
	f, err := fernet.NewFernet(key)
	if err != nil {
		return "", err
	}
	decMessage, err := f.Decrypt(message)
	if err != nil {
		return "", err
	}
	return string(decMessage), nil
}

func main() {
	// Generate a key (for demonstration purposes only)
	key, err := fernet.GenerateKey()
	if err != nil {
		log.Fatal(err)
	}

	message := "Hello, World!"
	fmt.Println("Original Message:", message)

	encMessage, err := Encrypt(message, key)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("Encrypted Message:", base64.StdEncoding.EncodeToString(encMessage))

	decMessage, err := Decrypt(encMessage, key)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Println("Decrypted Message:", decMessage)
}