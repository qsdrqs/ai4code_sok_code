package main

import (
	"fmt"
	"log"

	"github.com/fernet/fernet-go"
)

// ---------------------------------------------------------------------------
// Generate a single global key (equivalent to: key = Fernet.generate_key()).
// ---------------------------------------------------------------------------
var key *fernet.Key

func init() {
	var k fernet.Key
	if err := k.Generate(); err != nil {
		log.Fatalf("unable to generate fernet key: %v", err)
	}
	key = &k
}

// ---------------------------------------------------------------------------
// encryptText :  string -> string
//   Mirrors  f.encrypt(bytes(plain_text, "utf-8")).decode()
// ---------------------------------------------------------------------------
func encryptText(plainText string) string {
	// EncryptAndSign returns a base64-encoded token.
	token := fernet.EncryptAndSign([]byte(plainText), key)
	return string(token)
}

// ---------------------------------------------------------------------------
// decryptText :  string -> (string, error)
//   Mirrors  f.decrypt(token).decode()
//   TTL is set to 0 => no expiration check (same as default in Python snippet).
// ---------------------------------------------------------------------------
func decryptText(encryptedText string) (string, error) {
	plaintext := fernet.VerifyAndDecrypt([]byte(encryptedText), 0, []*fernet.Key{key})
	if plaintext == nil {
		return "", fmt.Errorf("decryption failed or token invalid")
	}
	return string(plaintext), nil
}

// ---------------------------------------------------------------------------
// Simple demo
// ---------------------------------------------------------------------------
func main() {
	msg := "Hello from Go + Fernet!"

	token := encryptText(msg)
	fmt.Println("Encrypted token:", token)

	plain, err := decryptText(token)
	if err != nil {
		log.Fatalf("decryptText error: %v", err)
	}
	fmt.Println("Decrypted text :", plain)
}