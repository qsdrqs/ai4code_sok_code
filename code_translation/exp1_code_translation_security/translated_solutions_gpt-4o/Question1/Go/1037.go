package main

import (
	"fmt"
	"log"

	"github.com/fernet/fernet-go"
)

func encrypt(input []byte, key *fernet.Key) []byte {
	token, err := fernet.EncryptAndSign(input, key)
	if err != nil {
		log.Fatalf("Error encrypting: %v", err)
	}
	return token
}

func decrypt(input []byte, key *fernet.Key) []byte {
	msg := fernet.VerifyAndDecrypt(input, 0, []*fernet.Key{key})
	if msg == nil {
		log.Fatalf("Error decrypting: message is nil")
	}
	return msg
}

func main() {
	var key fernet.Key
	if err := key.Generate(); err != nil {
		log.Fatalf("Error generating key: %v", err)
	}

	input := []byte("super secret")
	encryptedInput := encrypt(input, &key)
	decryptedInput := decrypt(encryptedInput, &key)

	fmt.Println(string(input))
	fmt.Println(string(decryptedInput))
}