package main

import (
	"fmt"
	"log"

	"github.com/fernet/fernet-go"
)

func encrypt(input string, key *fernet.Key) string {
	token, err := fernet.EncryptAndSign([]byte(input), key)
	if err != nil {
		log.Fatalf("Error encrypting message: %v", err)
	}
	return string(token)
}

func decrypt(token string, key *fernet.Key) string {
	message := fernet.VerifyAndDecrypt([]byte(token), 60, []*fernet.Key{key})
	if message == nil {
		log.Fatalf("Error decrypting message")
	}
	return string(message)
}

func main() {
	// Generate a new Fernet key
	key := fernet.MustGenerateKey()

	// Encrypt the message
	encrypted := encrypt("Hello World!", key)
	fmt.Println("Encrypted:", encrypted)

	// Decrypt the message
	decrypted := decrypt(encrypted, key)
	fmt.Println("Decrypted:", decrypted)
}