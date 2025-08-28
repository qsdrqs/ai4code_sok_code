package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"errors"
	"fmt"
	"io"
)

// encrypt encrypts plaintext using a given key.
// The key must be 16, 24, or 32 bytes to select AES-128, AES-192, or AES-256.
// It returns the nonce and the ciphertext. The ciphertext returned by GCM's Seal()
// function contains both the encrypted data and the authentication tag.
func encrypt(key, plaintext []byte) (nonce, ciphertext []byte, err error) {
	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, nil, err
	}

	// Create a new GCM AEAD (Authenticated Encryption with Associated Data).
	aead, err := cipher.NewGCM(block)
	if err != nil {
		return nil, nil, err
	}

	// Create a nonce (number used once). GCM requires a nonce for each encryption.
	// The nonce must be unique for a given key, but does not need to be secret.
	nonce = make([]byte, aead.NonceSize())
	if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
		return nil, nil, err
	}

	// Encrypt the data. The Seal function appends the result to the first argument
	// and returns the updated slice. A nil first argument forces Seal to allocate
	// a new slice, which is what we want. The result is the ciphertext + an authentication tag.
	ciphertext = aead.Seal(nil, nonce, plaintext, nil)

	return nonce, ciphertext, nil
}

// decrypt decrypts ciphertext using a given key.
// It takes the nonce and ciphertext that were returned by the encrypt function.
// It returns the original plaintext if decryption is successful.
// If the key is incorrect or the ciphertext has been tampered with,
// it will return an error, protecting data integrity.
func decrypt(key, nonce, ciphertext []byte) (plaintext []byte, err error) {
	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	// Create a new GCM AEAD.
	aead, err := cipher.NewGCM(block)
	if err != nil {
		return nil, err
	}

	// Decrypt the data. The Open function verifies the authentication tag
	// automatically. If the tag is invalid (indicating tampering or wrong key),
	// it will return an error.
	plaintext, err = aead.Open(nil, nonce, ciphertext, nil)
	if err != nil {
		// A common error here is "cipher: message authentication failed",
		// which means the key was wrong or the data was corrupted.
		return nil, err
	}

	return plaintext, nil
}

// main function to demonstrate the usage.
func main() {
	// Use a 32-byte key for AES-256.
	// In a real application, this key should be securely generated and managed.
	// For example: key := make([]byte, 32); _, err := rand.Read(key);
	key := []byte("a-very-secret-key-for-aes-256!")
	plaintext := []byte("This is a super secret message.")

	fmt.Printf("Original: %s\n", string(plaintext))
	fmt.Println("------------------------------------")

	// Encrypt the message
	nonce, ciphertext, err := encrypt(key, plaintext)
	if err != nil {
		panic(err)
	}

	fmt.Printf("Nonce (hex): %x\n", nonce)
	fmt.Printf("Ciphertext (hex): %x\n", ciphertext)
	fmt.Println("------------------------------------")

	// Decrypt the message
	decryptedText, err := decrypt(key, nonce, ciphertext)
	if err != nil {
		panic(err)
	}

	fmt.Printf("Decrypted: %s\n", string(decryptedText))

	// --- DEMONSTRATE FAILURE ---
	fmt.Println("\n--- Tampering Demo ---")

	// 1. Try to decrypt with the wrong key
	wrongKey := []byte("this-is-the-wrong-key-for-aes!")
	_, err = decrypt(wrongKey, nonce, ciphertext)
	if err != nil {
		fmt.Printf("Decryption with WRONG KEY failed as expected: %v\n", err)
	}

	// 2. Try to decrypt with tampered ciphertext
	// Flip a single bit in the ciphertext
	ciphertext[0] ^= 0xff
	_, err = decrypt(key, nonce, ciphertext)
	if err != nil {
		fmt.Printf("Decryption with TAMPERED DATA failed as expected: %v\n", err)
	}
}