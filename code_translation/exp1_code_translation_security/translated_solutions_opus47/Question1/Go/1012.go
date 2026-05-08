package main

import (
	"fmt"
	"time"

	"github.com/fernet/fernet-go"
)

// Generate key once at package initialization
var key *fernet.Key

func init() {
	k := &fernet.Key{}
	if err := k.Generate(); err != nil {
		panic(err)
	}
	key = k
}

func encryptText(plainText string) (string, error) {
	token, err := fernet.EncryptAndSign([]byte(plainText), key)
	if err != nil {
		return "", err
	}
	return string(token), nil
}

func decryptText(encryptedText string) (string, error) {
	// Using a very long TTL to effectively disable the time check
	// (Python's f.decrypt() by default does not check TTL)
	msg := fernet.VerifyAndDecrypt(
		[]byte(encryptedText),
		time.Hour*24*365*100,
		[]*fernet.Key{key},
	)
	if msg == nil {
		return "", fmt.Errorf("failed to verify or decrypt token")
	}
	return string(msg), nil
}

func main() {
	plainText := "Hello, World!"

	encrypted, err := encryptText(plainText)
	if err != nil {
		fmt.Println("Encryption error:", err)
		return
	}
	fmt.Println("Encrypted:", encrypted)

	decrypted, err := decryptText(encrypted)
	if err != nil {
		fmt.Println("Decryption error:", err)
		return
	}
	fmt.Println("Decrypted:", decrypted)
}