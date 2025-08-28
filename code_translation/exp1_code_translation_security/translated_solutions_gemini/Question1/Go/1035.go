package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"fmt"
	"io"
	"log"

	// This is the official supplementary crypto library for Go.
	// To install it, run: go get golang.org/x/crypto/eax
	"golang.org/x/crypto/eax"
)

// encrypt performs AES-EAX encryption on a message.
// It returns the ciphertext (which includes the authentication tag) and the nonce.
func encrypt(key, msg []byte) ([]byte, []byte, error) {
	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, nil, fmt.Errorf("could not create new cipher: %w", err)
	}

	// Create a new EAX cipher using the AES block.
	// We use the default nonce and tag sizes provided by the library.
	aead, err := eax.New(block, eax.DefaultNonceSize, eax.DefaultTagSize)
	if err != nil {
		return nil, nil, fmt.Errorf("could not create EAX cipher: %w", err)
	}

	// Generate a unique nonce for this encryption.
	// It is critical that the nonce is unique for each encryption with the same key.
	nonce := make([]byte, aead.NonceSize())
	if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
		return nil, nil, fmt.Errorf("could not generate nonce: %w", err)
	}

	// Encrypt the message. The Seal function handles encryption and authentication.
	// The result is a single slice containing both the ciphertext and the authentication tag.
	// The first argument is a destination buffer, which we leave as nil to let Seal allocate it.
	// The last argument is for "additional data" to authenticate, which we don't have here.
	ciphertext := aead.Seal(nil, nonce, msg, nil)

	return ciphertext, nonce, nil
}

// decrypt performs AES-EAX decryption on a ciphertext.
// It requires the key, the ciphertext (which includes the tag), and the nonce.
// It automatically verifies the authentication tag.
func decrypt(key, ciphertext, nonce []byte) ([]byte, error) {
	// Create a new AES cipher block from the key.
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, fmt.Errorf("could not create new cipher: %w", err)
	}

	// Create a new EAX cipher using the AES block.
	aead, err := eax.New(block, eax.DefaultNonceSize, eax.DefaultTagSize)
	if err != nil {
		return nil, fmt.Errorf("could not create EAX cipher: %w", err)
	}

	// The Open function handles both decryption and verification of the authentication tag.
	// If the tag is invalid (i.e., the ciphertext has been tampered with), it will return an error.
	plaintext, err := aead.Open(nil, nonce, ciphertext, nil)
	if err != nil {
		return nil, fmt.Errorf("could not decrypt or verify message: %w", err)
	}

	return plaintext, nil
}

func main() {
	// The key must be 16, 24, or 32 bytes long for AES-128, AES-192, or AES-256 respectively.
	key := []byte("Sixteen byte key")
	message := []byte("message")

	fmt.Printf("Original message: %s\n", message)

	// Encrypt the message
	ciphertext, nonce, err := encrypt(key, message)
	if err != nil {
		log.Fatalf("Encryption failed: %v", err)
	}

	// In Go, the ciphertext and tag are combined.
	// The nonce is kept separate, just like in the Python example.
	fmt.Printf("Ciphertext (hex): %x\n", ciphertext)
	fmt.Printf("Nonce (hex):      %x\n", nonce)

	// Decrypt the message
	plaintext, err := decrypt(key, ciphertext, nonce)
	if err != nil {
		log.Fatalf("Decryption failed: %v", err)
	}

	// The output will be the original message as a byte slice, which we print as a string.
	fmt.Printf("Decrypted message: %s\n", plaintext)
}