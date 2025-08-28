package main

import (
	"fmt"
	"log"

	"github.com/fernet/fernet-go"
)

// encrypt takes a plaintext string and a base64-encoded key, and returns the
// Fernet-encrypted token as bytes.
func encrypt(plaintext string, key string) ([]byte, error) {
	// Decode the base64-encoded key.
	k, err := fernet.DecodeKey(key)
	if err != nil {
		return nil, fmt.Errorf("failed to decode key: %w", err)
	}

	// Encrypt the message. The input to EncryptAndSign must be bytes.
	encrypted, err := fernet.EncryptAndSign([]byte(plaintext), k)
	if err != nil {
		return nil, fmt.Errorf("failed to encrypt message: %w", err)
	}

	return encrypted, nil
}

// decrypt takes a Fernet token (as bytes) and a base64-encoded key, and returns
// the original plaintext string.
func decrypt(token []byte, key string) (string, error) {
	// Decode the base64-encoded key.
	k, err := fernet.DecodeKey(key)
	if err != nil {
		return "", fmt.Errorf("failed to decode key: %w", err)
	}

	// Decrypt the token. The library expects a slice of valid keys.
	// We provide a TTL of 0, which means the token never expires.
	decryptedBytes := fernet.VerifyAndDecrypt(token, 0, []*fernet.Key{k})

	// If decryption fails (e.g., bad signature, invalid format), the library
	// returns nil. We check for this and return an error.
	if decryptedBytes == nil {
		return "", fmt.Errorf("failed to decrypt token: invalid token or signature")
	}

	return string(decryptedBytes), nil
}

func main() {
	// Generate a new encryption key.
	// In the Python example, ex_key is a base64-encoded string.
	// GenKey() creates a Key object, and Encode() converts it to the
	// equivalent base64 string format.
	keyObj, err := fernet.GenKey()
	if err != nil {
		log.Fatalf("Failed to generate key: %v", err)
	}
	exKey := keyObj.Encode()

	// --- Encryption ---
	message := "Hello World!"
	encrypted, err := encrypt(message, exKey)
	if err != nil {
		log.Fatalf("Encryption failed: %v", err)
	}
	// The encrypted output is a byte slice, but it's a valid UTF-8 string
	// (the Fernet token format), so we can print it as a string.
	fmt.Printf("Encrypted: %s\n", encrypted)

	// --- Decryption ---
	decrypted, err := decrypt(encrypted, exKey)
	if err != nil {
		log.Fatalf("Decryption failed: %v", err)
	}
	fmt.Printf("Decrypted: %s\n", decrypted)
}