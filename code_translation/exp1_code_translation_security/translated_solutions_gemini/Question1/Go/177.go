package main

import (
	"crypto/aes"
	"crypto/cipher"
	"crypto/rand"
	"errors"
	"fmt"
	"io"

	// EAX mode is not in the standard library, so we use a third-party package.
	// To install, run: go get github.com/RyuaNerin/go-eax
	"github.com/RyuaNerin/go-eax"
)

// Encrypt encrypts a message 'm' using a secret key 'sk' with AES-EAX.
// It returns the ciphertext and the nonce used for encryption.
// The returned ciphertext includes the authentication tag.
func encrypt(m, sk []byte) (c []byte, nonce []byte, err error) {
	// Create a new AES cipher block from the secret key.
	block, err := aes.NewCipher(sk)
	if err != nil {
		return nil, nil, err
	}

	// Create a new EAX cipher mode instance.
	// The eax.New function returns an object that satisfies the cipher.AEAD interface.
	aead, err := eax.New(block)
	if err != nil {
		return nil, nil, err
	}

	// Generate a random nonce. The size is determined by the AEAD implementation.
	// It is critical that the nonce is unique for every encryption with the same key.
	nonce = make([]byte, aead.NonceSize())
	if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
		return nil, nil, err
	}

	// Encrypt the message. The Seal function handles both encryption and
	// authentication, returning a single byte slice containing the ciphertext
	// and the authentication tag. We pass 'nil' for the destination to have
	// Seal allocate a new slice. The final argument is for additional
	// authenticated data, which is not used in this example.
	c = aead.Seal(nil, nonce, m, nil)

	return c, nonce, nil
}

// Decrypt decrypts a ciphertext 'c' using the original nonce and secret key 'sk'.
// It returns the original plaintext message.
// An error is returned if the key is wrong or the ciphertext has been tampered with.
func decrypt(c, nonce, sk []byte) (m []byte, err error) {
	// Create a new AES cipher block from the secret key.
	block, err := aes.NewCipher(sk)
	if err != nil {
		return nil, err
	}

	// Create a new EAX cipher mode instance.
	aead, err := eax.New(block)
	if err != nil {
		return nil, err
	}

	// The nonce size must match the AEAD's requirement.
	if len(nonce) != aead.NonceSize() {
		return nil, errors.New("incorrect nonce length")
	}

	// Decrypt the message. The Open function verifies the authentication tag
	// automatically. If the tag is invalid (indicating tampering, wrong key,
	// or wrong nonce), it will return an error.
	m, err = aead.Open(nil, nonce, c, nil)
	if err != nil {
		return nil, err
	}

	return m, nil
}

// --- Example Usage ---
func main() {
	// A secret key (must be 16, 24, or 32 bytes for AES-128, AES-192, or AES-256).
	// In a real application, this should be generated securely and kept secret.
	secretKey := []byte("a very secret key 12345678901234") // 32 bytes for AES-256

	// The message to be encrypted.
	message := []byte("This is a super secret message.")

	fmt.Printf("Original Message: %s\n", message)
	fmt.Printf("Secret Key: %s\n\n", secretKey)

	// Encrypt the message
	ciphertext, nonce, err := encrypt(message, secretKey)
	if err != nil {
		panic(fmt.Sprintf("Encryption failed: %v", err))
	}

	fmt.Printf("Encrypted Ciphertext (hex): %x\n", ciphertext)
	fmt.Printf("Nonce (hex): %x\n\n", nonce)

	// Decrypt the message
	// Note: We pass the exact same ciphertext, nonce, and key.
	decryptedMessage, err := decrypt(ciphertext, nonce, secretKey)
	if err != nil {
		panic(fmt.Sprintf("Decryption failed: %v", err))
	}

	fmt.Printf("Decrypted Message: %s\n", decryptedMessage)

	// Verification
	if string(message) == string(decryptedMessage) {
		fmt.Println("\nSuccess: The decrypted message matches the original.")
	} else {
		fmt.Println("\nFailure: The messages do not match!")
	}

	// --- Example of a failed decryption (tampered data) ---
	fmt.Println("\n--- Tampering with ciphertext to show decryption failure ---")
	// Flip a bit in the ciphertext
	ciphertext[0] ^= 0xff
	fmt.Printf("Tampered Ciphertext (hex): %x\n", ciphertext)

	_, err = decrypt(ciphertext, nonce, secretKey)
	if err != nil {
		fmt.Printf("Decryption correctly failed as expected: %v\n", err)
	} else {
		fmt.Println("Security Alert: Decryption succeeded with tampered data!")
	}
}