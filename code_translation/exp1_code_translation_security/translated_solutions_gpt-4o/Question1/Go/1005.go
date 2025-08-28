package main

import (
	"fmt"
	"log"

	"github.com/fernet/fernet-go"
)

func encrypt(msg []byte, key *fernet.Key) ([]byte, error) {
	token, err := fernet.EncryptAndSign(msg, key)
	if err != nil {
		return nil, err
	}
	return token, nil
}

func decrypt(token []byte, key *fernet.Key) ([]byte, error) {
	msg := fernet.VerifyAndDecrypt(token, 0, []*fernet.Key{key})
	if msg == nil {
		return nil, fmt.Errorf("decryption failed")
	}
	return msg, nil
}

func main() {
	// Generate a new Fernet key
	key := fernet.MustGenerateKey()

	// Message to encrypt
	msg := []byte("a much longer message with punctuation!")

	// Encrypt the message
	e, err := encrypt(msg, key)
	if err != nil {
		log.Fatalf("encryption failed: %v", err)
	}

	// Decrypt the message
	d, err := decrypt(e, key)
	if err != nil {
		log.Fatalf("decryption failed: %v", err)
	}

	// Print the encrypted and decrypted messages
	fmt.Println("Encrypted:", string(e))
	fmt.Println("Decrypted:", string(d))
}