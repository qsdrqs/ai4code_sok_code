package main

import (
	"fmt"
	"log"

	"github.com/fernet/fernet-go"
)

// Encrypt encrypts the input string using the provided key.
func Encrypt(input string, key string) (string, error) {
	var fernetKey fernet.Key
	err := fernetKey.FromString(key)
	if err != nil {
		return "", err
	}

	token, err := fernet.EncryptAndSign([]byte(input), &fernetKey)
	if err != nil {
		return "", err
	}

	return string(token), nil
}

// Decrypt decrypts the input string using the provided key.
func Decrypt(input string, key string) (string, error) {
	var fernetKey fernet.Key
	err := fernetKey.FromString(key)
	if err != nil {
		return "", err
	}

	message := fernet.VerifyAndDecrypt([]byte(input), 60, []*fernet.Key{&fernetKey})
	if message == nil {
		return "", fmt.Errorf("decryption failed")
	}

	return string(message), nil
}

func main() {
	// Example usage
	key := "your-32-byte-base64-url-safe-key" // Replace with your actual key
	plaintext := "Hello, World!"

	encrypted, err := Encrypt(plaintext, key)
	if err != nil {
		log.Fatalf("Encryption failed: %v", err)
	}
	fmt.Printf("Encrypted: %s\n", encrypted)

	decrypted, err := Decrypt(encrypted, key)
	if err != nil {
		log.Fatalf("Decryption failed: %v", err)
	}
	fmt.Printf("Decrypted: %s\n", decrypted)
}