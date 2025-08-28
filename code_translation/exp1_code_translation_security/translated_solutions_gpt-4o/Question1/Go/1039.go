package main

import (
	"fmt"
	"log"

	"github.com/fernet/fernet-go"
)

func encrypt(message string, key *fernet.Key) ([]byte, error) {
	token, err := fernet.EncryptAndSign([]byte(message), key)
	if err != nil {
		return nil, err
	}
	return token, nil
}

func decrypt(token []byte, key *fernet.Key) (string, error) {
	message, err := fernet.VerifyAndDecrypt(token, 60, []*fernet.Key{key})
	if err != nil {
		return "", err
	}
	return string(message), nil
}

func main() {
	// Example usage
	key := fernet.MustGenerateKey()
	message := "Hello, World!"

	encMessage, err := encrypt(message, key)
	if err != nil {
		log.Fatalf("Encryption failed: %v", err)
	}
	fmt.Printf("Encrypted: %s\n", encMessage)

	decMessage, err := decrypt(encMessage, key)
	if err != nil {
		log.Fatalf("Decryption failed: %v", err)
	}
	fmt.Printf("Decrypted: %s\n", decMessage)
}