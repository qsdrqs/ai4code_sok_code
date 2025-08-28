package main

import (
	"fmt"
	"log"

	"github.com/fernet/fernet-go"
)

// encrypt encrypts a string using a single Fernet key.
func encrypt(message string, key *fernet.Key) []byte {
	return fernet.EncryptAndSign([]byte(message), key)
}

// decrypt decrypts a Fernet token using a slice of keys
// (a slice is used so key-rotation scenarios are supported).
func decrypt(token []byte, keys []*fernet.Key) (string, error) {
	// ttl = 0  →  no age check on the token
	plain := fernet.VerifyAndDecrypt(token, 0, keys)
	if plain == nil {
		return "", fmt.Errorf("unable to decrypt token")
	}
	return string(plain), nil
}

func main() {
	message := "the solutions are here somewhere"
	fmt.Println("Secret Message:", message)

	// Generate a random symmetric key.
	var key fernet.Key
	if err := key.Generate(); err != nil {
		log.Fatalf("key generation failed: %v", err)
	}
	keySet := []*fernet.Key{&key} // slice required by VerifyAndDecrypt

	// Encrypt
	encMessage := encrypt(message, &key)
	fmt.Printf("Encrypted token: %s\n", encMessage)

	// Decrypt
	decMessage, err := decrypt(encMessage, keySet)
	if err != nil {
		log.Fatalf("decrypt failed: %v", err)
	}
	fmt.Println("Decrypted Message:", decMessage)
}