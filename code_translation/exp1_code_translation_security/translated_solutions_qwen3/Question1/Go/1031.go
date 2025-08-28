package main

import (
	"crypto/rand"
	"encoding/base64"
	"fmt"
	"time"

	"github.com/fernet/fernet-go"
)

// encrypt encrypts a message using a Fernet key.
func encrypt(message string, key fernet.Key) []byte {
	return fernet.Encrypt([]byte(message), key)
}

// decrypt decrypts a Fernet-encrypted message using the provided key.
func decrypt(encrypted []byte, key fernet.Key) (string, error) {
	decrypted, _, err := fernet.Decrypt(encrypted, time.Now(), key)
	if err != nil {
		return "", err
	}
	return string(decrypted), nil
}

func main() {
	// Original message
	message := "the solutions are here somewhere"
	fmt.Println("Secret Message:", message)

	// Generate a 32-byte random key
	keyBytes := make([]byte, 32)
	if _, err := rand.Read(keyBytes); err != nil {
		panic(err)
	}

	// Encode the key in URL-safe Base64 without padding
	keyString := base64.RawURLEncoding.EncodeToString(keyBytes)

	// Decode the key into a Fernet Key struct
	key, err := fernet.DecodeKey(keyString)
	if err != nil {
		panic(err)
	}

	// Encrypt the message
	encMessage := encrypt(message, key)
	fmt.Println(encMessage)

	// Decrypt the message
	decMessage, err := decrypt(encMessage, key)
	if err != nil {
		panic(err)
	}
	fmt.Println("decrypted Message:", decMessage)
}