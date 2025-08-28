package main

import (
	"crypto/aes"
	"crypto/cipher"
)

// encryptString encrypts the given message using the provided CFB stream.
func encryptString(message string, stream cipher.Stream) []byte {
	ciphertext := make([]byte, len(message))
	stream.XORKeyStream(ciphertext, []byte(message))
	return ciphertext
}

// decryptString decrypts the given ciphertext using the provided CFB stream.
func decryptString(ciphertext []byte, stream cipher.Stream) string {
	plaintext := make([]byte, len(ciphertext))
	stream.XORKeyStream(plaintext, ciphertext)
	return string(plaintext)
}

func main() {
	// Define the key and IV (both must be 16 bytes for AES-128)
	key := []byte("This is a key123")
	iv := []byte("This is an IV456")

	// Create a new AES block cipher
	block, err := aes.NewCipher(key)
	if err != nil {
		panic(err)
	}

	// Create CFB encrypter and decrypter using the same key and IV
	encryptStream := cipher.NewCFBEncrypter(block, iv)
	decryptStream := cipher.NewCFBDecrypter(block, iv)

	// Message to be encrypted
	message := "The answer is no"

	// Encrypt the message
	encrypted := encryptString(message, encryptStream)

	// Decrypt the message
	decrypted := decryptString(encrypted, decryptStream)

	// At this point, 'decrypted' should be equal to 'message'
}