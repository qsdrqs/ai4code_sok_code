package main

import (
	"fmt"
	"log"
	"time"

	"github.com/fernet/fernet-go"
)

// encrypt encrypts input with the supplied key and returns the token.
func encrypt(input []byte, key *fernet.Key) ([]byte, error) {
	return fernet.EncryptAndSign(input, key)
}

// decrypt verifies / decrypts the token with the supplied key and returns
// the plaintext.  A TTL of 0 disables the age check, matching the Python
// example’s behaviour.
func decrypt(token []byte, key *fernet.Key) ([]byte, error) {
	return fernet.VerifyAndDecrypt(token, 0*time.Second, []*fernet.Key{key})
}

func main() {
	// Generate a fresh key (32 random bytes, base-64 encoded internally).
	var key fernet.Key
	if err := key.Generate(); err != nil {
		log.Fatalf("unable to generate key: %v", err)
	}

	input := []byte("super secret")

	encryptedInput, err := encrypt(input, &key)
	if err != nil {
		log.Fatalf("encryption failed: %v", err)
	}

	decryptedInput, err := decrypt(encryptedInput, &key)
	if err != nil {
		log.Fatalf("decryption failed: %v", err)
	}

	fmt.Println(string(input))
	fmt.Println(string(decryptedInput))
}