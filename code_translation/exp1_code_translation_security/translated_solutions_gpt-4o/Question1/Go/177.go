package main

import (
	"crypto/aes"
	"crypto/cipher"
	"fmt"
)

// Encrypt encrypts the message m using the secret key sk and returns the ciphertext and nonce.
func encrypt(m, sk []byte) ([]byte, []byte, error) {
	block, err := aes.NewCipher(sk)
	if err != nil {
		return nil, nil, err
	}

	aesgcm, err := cipher.NewGCM(block)
	if err != nil {
		return nil, nil, err
	}

	nonce := make([]byte, aesgcm.NonceSize())
	ciphertext := aesgcm.Seal(nil, nonce, m, nil)
	return ciphertext, nonce, nil
}

// Decrypt decrypts the ciphertext using the secret key sk and nonce.
func decrypt(c, nonce, sk []byte) ([]byte, error) {
	block, err := aes.NewCipher(sk)
	if err != nil {
		return nil, err
	}

	aesgcm, err := cipher.NewGCM(block)
	if err != nil {
		return nil, err
	}

	plaintext, err := aesgcm.Open(nil, nonce, c, nil)
	if err != nil {
		return nil, err
	}

	return plaintext, nil
}

func main() {
	// Example usage
	message := []byte("Your message here")
	secretKey := []byte("your-32-byte-long-secret-key!") // Ensure this is 32 bytes for AES-256

	ciphertext, nonce, err := encrypt(message, secretKey)
	if err != nil {
		fmt.Println("Encryption error:", err)
		return
	}

	plaintext, err := decrypt(ciphertext, nonce, secretKey)
	if err != nil {
		fmt.Println("Decryption error:", err)
		return
	}

	fmt.Println("Decrypted message:", string(plaintext))
}