// main.go
package main

import (
	"fmt"
	"log"

	"github.com/fernet/fernet-go"
)

// encrypt encrypts msg with key and returns the token.
func encrypt(msg []byte, key *fernet.Key) []byte {
	return fernet.EncryptAndSign(msg, key)
}

// decrypt verifies the token with key and returns the plaintext.
func decrypt(token []byte, key *fernet.Key) ([]byte, error) {
	// TTL of 0 disables expiration checks (mirrors Python example).
	plain, err := fernet.VerifyAndDecrypt(token, 0, []*fernet.Key{key})
	if err != nil {
		return nil, err
	}
	return plain, nil
}

func main() {
	// Generate a brand-new key, equivalent to Fernet.generate_key().
	var key fernet.Key
	if err := key.Generate(); err != nil {
		log.Fatalf("key generation failed: %v", err)
	}

	msg := []byte("a much longer message with punctuation!")

	cipher := encrypt(msg, &key)

	plain, err := decrypt(cipher, &key)
	if err != nil {
		log.Fatalf("decrypt failed: %v", err)
	}

	fmt.Printf("%s\n", cipher) // ciphertext token
	fmt.Printf("%s\n", plain)  // original plaintext
}