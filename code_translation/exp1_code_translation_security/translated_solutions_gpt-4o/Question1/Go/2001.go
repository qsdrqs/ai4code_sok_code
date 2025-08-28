package main

import (
	"fmt"
	"log"

	"github.com/fernet/fernet-go"
)

// Function to encrypt msg with key using Fernet (AES-128-CBC with HMAC)
func encrypt(msg []byte, key *fernet.Key) ([]byte, error) {
	token, err := fernet.EncryptAndSign(msg, key)
	if err != nil {
		return nil, err
	}
	return token, nil
}

// Function to decrypt msg with key using Fernet (AES-128-CBC with HMAC)
func decrypt(ciphertext []byte, key *fernet.Key) ([]byte, error) {
	msg := fernet.VerifyAndDecrypt(ciphertext, 0, []*fernet.Key{key})
	if msg == nil {
		return nil, fmt.Errorf("decryption failed")
	}
	return msg, nil
}

func main() {
	// Example usage
	key := fernet.MustDecodeKey("your-base64-encoded-key-here")
	message := []byte("Hello, World!")

	encrypted, err := encrypt(message, key)
	if err != nil {
		log.Fatalf("Encryption failed: %v", err)
	}
	fmt.Printf("Encrypted: %s\n", encrypted)

	decrypted, err := decrypt(encrypted, key)
	if err != nil {
		log.Fatalf("Decryption failed: %v", err)
	}
	fmt.Printf("Decrypted: %s\n", decrypted)
}