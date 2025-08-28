package main

import (
	"fmt"
	"io/ioutil"
	"log"

	"github.com/fernet/fernet-go"
)

func encrypt(data []byte, key *fernet.Key) ([]byte, error) {
	token, err := fernet.EncryptAndSign(data, key)
	if err != nil {
		return nil, err
	}
	return token, nil
}

func decrypt(token []byte, key *fernet.Key) ([]byte, error) {
	data, err := fernet.VerifyAndDecrypt(token, 0, []*fernet.Key{key})
	if err != nil {
		return nil, err
	}
	return data, nil
}

func main() {
	// Generate a new Fernet key
	key := fernet.MustGenerateKey()

	// Read the file data
	data, err := ioutil.ReadFile("/safedir/test.txt")
	if err != nil {
		log.Fatalf("Failed to read file: %v", err)
	}

	// Encrypt the data
	encrypted, err := encrypt(data, key)
	if err != nil {
		log.Fatalf("Failed to encrypt data: %v", err)
	}

	fmt.Printf("Encrypted data: %x\n", encrypted)

	// Decrypt the data
	decrypted, err := decrypt(encrypted, key)
	if err != nil {
		log.Fatalf("Failed to decrypt data: %v", err)
	}

	fmt.Printf("Decrypted data: %s\n", decrypted)
}