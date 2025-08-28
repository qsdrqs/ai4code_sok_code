package main

import (
	"fmt"
	"log"

	"github.com/fernet/fernet-go"
)

// 'key' is a package-level variable, similar to the module-level key in Python.
// It will be initialized once by the init() function before main() is run.
var key *fernet.Key

// The init() function is a special Go function that runs automatically
// on program startup. It's the perfect place to handle one-time setup
// like generating an encryption key.
func init() {
	// Fernet.generate_key() in Python is equivalent to fernet.GenKey() in Go.
	// It returns the key and a potential error, which we must handle.
	var err error
	key, err = fernet.GenKey()
	if err != nil {
		// If we can't generate a key, the application can't run.
		// log.Fatalf will print the error and exit the program.
		log.Fatalf("Failed to generate key: %v", err)
	}
}

// encryptText encrypts a plaintext string and returns a base64-encoded ciphertext string.
// It follows Go's idiomatic error handling by returning an error as the second value.
func encryptText(plainText string) (string, error) {
	// In Go, we convert the string to a byte slice ([]byte).
	// This is equivalent to Python's `byte(plain_text, "UTF-8")`.
	msg := []byte(plainText)

	// Encrypt the message using the global key.
	encryptedBytes, err := fernet.EncryptAndSign(msg, key)
	if err != nil {
		return "", fmt.Errorf("failed to encrypt text: %w", err)
	}

	// The result is a byte slice, which we convert to a string for the return value.
	// This is equivalent to Python's `.decode()`.
	return string(encryptedBytes), nil
}

// decryptText decrypts a Fernet token string and returns the original plaintext string.
func decryptText(encryptedText string) (string, error) {
	// The encrypted token must be converted back to a byte slice for decryption.
	token := []byte(encryptedText)

	// Decrypt the message.
	// The fernet-go library requires a Time-To-Live (TTL) duration.
	// We use fernet.Forever to specify that the token should not expire,
	// matching the default behavior of the Python library.
	// The library also supports key rotation by accepting a slice of keys.
	decryptedBytes := fernet.VerifyAndDecrypt(token, fernet.Forever, []*fernet.Key{key})

	// The library returns 'nil' if decryption fails (e.g., bad signature, wrong key).
	// We check for nil and return a descriptive error.
	if decryptedBytes == nil {
		return "", fmt.Errorf("failed to decrypt or verify token")
	}

	// Convert the decrypted byte slice back to a string and return it.
	return string(decryptedBytes), nil
}

// main function to demonstrate the usage.
func main() {
	originalText := "This is a secret message."
	fmt.Printf("Original Text:  %s\n", originalText)

	// Encrypt the text
	encrypted, err := encryptText(originalText)
	if err != nil {
		log.Fatalf("Encryption failed: %v", err)
	}
	fmt.Printf("Encrypted Text: %s\n", encrypted)

	// Decrypt the text
	decrypted, err := decryptText(encrypted)
	if err != nil {
		log.Fatalf("Decryption failed: %v", err)
	}
	fmt.Printf("Decrypted Text: %s\n", decrypted)
}