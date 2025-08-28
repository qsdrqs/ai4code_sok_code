package main

import (
	"fmt"
	"log"
	"time"

	"github.com/fernet/fernet-go"
)

// To run this code, you first need to install the dependency:
// go get github.com/fernet/fernet-go

// Encrypt takes a slice of bytes and a Fernet key, and returns the encrypted data.
// In Go, it's idiomatic for functions that can fail to return an error as the last value.
func Encrypt(data []byte, key *fernet.Key) ([]byte, error) {
	// The fernet-go library's Encrypt function expects a slice of keys
	// for key rotation purposes. For a simple encryption, we just pass a slice
	// containing our single key.
	keys := []*fernet.Key{key}
	
	// fernet.Encrypt automatically handles creating the Fernet token.
	encrypted, err := fernet.Encrypt(data, keys)
	if err != nil {
		return nil, err
	}

	return encrypted, nil
}

// Decrypt takes encrypted data and a Fernet key, and returns the original data.
// Note: The original Python code had a bug `return decrypted` where the variable
// was named `encrypted`. This Go version corrects the logic.
func Decrypt(data []byte, key *fernet.Key) ([]byte, error) {
	// Just like with Encrypt, we create a slice of keys.
	// Decrypt will try each key in the slice until one works.
	keys := []*fernet.Key{key}

	// The Decrypt function requires a TTL (Time-To-Live) to check for token expiration.
	// To replicate the default Python behavior (which checks the timestamp but has no
	// explicit TTL), we can use `fernet.Forever` to accept any valid token
	// regardless of its age.
	decrypted := fernet.Decrypt(data, fernet.Forever, keys)
	if decrypted == nil {
		// fernet-go's Decrypt returns nil if decryption fails for any reason
		// (e.g., invalid key, corrupted data, expired TTL).
		return nil, fmt.Errorf("failed to decrypt token")
	}

	return decrypted, nil
}

// The main function serves as the entry point for the program,
// similar to the `if __name__ == "__main__":` block in Python.
func main() {
	// In Go, we must explicitly handle errors.
	// Fernet.generate_key() becomes fernet.GenerateKey()
	key, err := fernet.GenerateKey()
	if err != nil {
		// log.Fatalf prints the error and exits the program.
		log.Fatalf("Failed to generate key: %v", err)
	}

	// The data to be encrypted. In Go, we work with byte slices (`[]byte`) for raw data.
	message := []byte("/safedir/test.txt")

	fmt.Printf("Original data: %s\n", message)
	fmt.Printf("Using key: %s\n\n", key.Encode())

	// --- Encryption ---
	encrypted, err := Encrypt(message, key)
	if err != nil {
		log.Fatalf("Failed to encrypt data: %v", err)
	}
	// Encrypted data is not human-readable.
	fmt.Printf("Encrypted data: %s\n", encrypted)

	// --- Decryption ---
	decrypted, err := Decrypt(encrypted, key)
	if err != nil {
		log.Fatalf("Failed to decrypt data: %v", err)
	}
	fmt.Printf("Decrypted data: %s\n", decrypted)
}