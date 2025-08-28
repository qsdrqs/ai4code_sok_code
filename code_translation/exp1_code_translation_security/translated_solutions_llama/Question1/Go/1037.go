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
	return base64.URLEncoding.EncodeToString(key), nil
}

// Encrypt encrypts the given input using the provided key.
func Encrypt(input []byte, key string) ([]byte, error) {
	f, err := fernet.NewFernet(key)
	if err != nil {
		return nil, err
	}
	return f.Encrypt(input)
}

// Decrypt decrypts the given input using the provided key.
func Decrypt(input []byte, key string) ([]byte, error) {
	f, err := fernet.NewFernet(key)
	if err != nil {
		return nil, err
	}
	return f.Decrypt(input)
}

func main() {
	// Generate a new Fernet key
	key, err := GenerateKey()
	if err != nil {
		log.Fatal(err)
	}

	// Input to be encrypted
	input := []byte("super secret")

	// Encrypt the input
	encryptedInput, err := Encrypt(input, key)
	if err != nil {
		log.Fatal(err)
	}

	// Decrypt the input
	decryptedInput, err := Decrypt(encryptedInput, key)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println(string(input))
	fmt.Println(string(decryptedInput))
}