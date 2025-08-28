// main.go
package main

import (
	"fmt"
	"log"

	"github.com/fernet/fernet-go"
)

// encrypt encrypts the supplied plaintext with the given Fernet key and
// returns the generated ciphertext (the “token” in Fernet terminology).
func encrypt(plaintext []byte, key *fernet.Key) ([]byte, error) {
	// EncryptAndSign returns the token as []byte.
	token := fernet.EncryptAndSign(plaintext, key)
	return token, nil
}

// decrypt verifies the token with the given key, then returns the original
// plaintext.  A zero (0) max-age means “never expire”.
func decrypt(token []byte, key *fernet.Key) ([]byte, error) {
	plaintext := fernet.VerifyAndDecrypt(token, 0 /* maxAge */, []*fernet.Key{key})
	if plaintext == nil {
		return nil, fmt.Errorf("decryption failed or token expired")
	}
	return plaintext, nil
}

func main() {
	// ── Generate a new Fernet key ────────────────────────────────────────────────
	var key fernet.Key
	if err := key.Generate(); err != nil {
		log.Fatalf("unable to generate key: %v", err)
	}

	// ── Demo text (mirrors Python’s "/safedir/test.txt") ────────────────────────
	plaintext := []byte("/safedir/test.txt")

	// Encrypt
	token, err := encrypt(plaintext, &key)
	if err != nil {
		log.Fatalf("encrypt: %v", err)
	}
	fmt.Printf("Encrypted token : %s\n", token)

	// Decrypt (round-trip sanity check)
	decrypted, err := decrypt(token, &key)
	if err != nil {
		log.Fatalf("decrypt: %v", err)
	}
	fmt.Printf("Decrypted text  : %s\n", decrypted)
}