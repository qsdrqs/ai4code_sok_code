package main

import (
	"fmt"
	"log"

	"github.com/fernet/fernet-go"
)

// encrypt using a symmetric key
func encrypt(message string, key *fernet.Key) string {
	token, err := fernet.EncryptAndSign([]byte(message), key)
	if err != nil {
		log.Fatal(err)
	}
	return string(token)
}

// decrypt using a symmetric key
func decrypt(encMessage string, key *fernet.Key) string {
	message := fernet.VerifyAndDecrypt([]byte(encMessage), 0, []*fernet.Key{key})
	if message == nil {
		log.Fatal("Decryption failed")
	}
	return string(message)
}

func main() {
	message := "the solutions are here somewhere"
	fmt.Println("Secret Message:", message)

	// Generate a new Fernet key
	key := fernet.MustGenerateKey()

	encMessage := encrypt(message, key)
	fmt.Println("Encrypted Message:", encMessage)

	decMessage := decrypt(encMessage, key)
	fmt.Println("Decrypted Message:", decMessage)
}