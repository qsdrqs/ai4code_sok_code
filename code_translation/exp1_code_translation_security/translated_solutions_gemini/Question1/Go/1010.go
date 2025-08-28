package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"fmt"
	"io"
	"log"
)

// generateKey creates a new random 16-byte key for AES-128.
// This is equivalent to Python's os.urandom(16).
func generateKey() ([]byte, error) {
	key := make([]byte, 16) // AES-128 key size
	if _, err := io.ReadFull(rand.Reader, key); err != nil {
		return nil, err
	}
	return key, nil
}

// encrypt encrypts a string using a symmetric key (AES-GCM).
// GCM is an authenticated encryption mode, providing confidentiality and integrity.
// It returns the ciphertext and the nonce used for encryption.
func encrypt(key []byte, data string) ([]byte, []byte, error) {
	// Convert the string data to a byte slice.
	plaintext := []byte(data)

	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, nil, err
	}

	// Create a new GCM cipher mode instance.
	// GCM is a modern, secure, and widely used AEAD (Authenticated Encryption with Associated Data) mode.
	gcm, err := cipher.NewGCM(block)
	if err != nil {
		return nil, nil, err
	}

	// Create a new nonce. A nonce must be unique for each encryption with the same key.
	// GCM's standard nonce size is 12 bytes.
	nonce := make([]byte, gcm.NonceSize())
	if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
		return nil, nil, err
	}

	// Encrypt the data. The gcm.Seal function encrypts and authenticates the plaintext.
	// The first argument is an optional destination buffer (we use nil to let it allocate).
	// The second is the nonce.
	// The third is the plaintext.
	// The fourth is optional additional authenticated data (AAD), which we are not using here.
	// The result (ciphertext) includes both the encrypted data and the authentication tag.
	ciphertext := gcm.Seal(nil, nonce, plaintext, nil)

	return ciphertext, nonce, nil
}

// decrypt decrypts a string using the key and nonce.
// It verifies the integrity of the data using the authentication tag embedded in the ciphertext.
func decrypt(key, nonce, ciphertext []byte) (string, error) {
	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	// Create a new GCM cipher mode instance.
	gcm, err := cipher.NewGCM(block)
	if err != nil {
		return "", err
	}

	// Decrypt the data. The gcm.Open function verifies the authentication tag
	// before decrypting. If the tag is not valid, it will return an error.
	plaintext, err := gcm.Open(nil, nonce, ciphertext, nil)
	if err != nil {
		// This error can indicate that the ciphertext was tampered with.
		return "", err
	}

	// Return the decrypted data as a string.
	return string(plaintext), nil
}

func main() {
	// Example usage:

	// 1. Generate a secret key.
	// In a real application, this key must be stored securely.
	key, err := generateKey()
	if err != nil {
		log.Fatalf("Failed to generate key: %v", err)
	}
	fmt.Printf("Generated Key (Hex): %x\n", key)

	// 2. Define the data to be encrypted.
	testData := "Jim's test"
	fmt.Printf("Original Data: %s\n\n", testData)

	// 3. Encrypt the data.
	encrypted, nonce, err := encrypt(key, testData)
	if err != nil {
		log.Fatalf("Failed to encrypt data: %v", err)
	}
	fmt.Printf("Encrypted Data (Hex): %x\n", encrypted)
	fmt.Printf("Nonce (Hex): %x\n\n", nonce)

	// 4. Decrypt the data.
	restoredData, err := decrypt(key, nonce, encrypted)
	if err != nil {
		log.Fatalf("Failed to decrypt data: %v", err)
	}

	// 5. Print the restored data.
	fmt.Printf("Restored Data: %s\n", restoredData)

	// Verification
	if testData == restoredData {
		fmt.Println("\nSuccess: Original and restored data match.")
	} else {
		fmt.Println("\nFailure: Data does not match.")
	}
}