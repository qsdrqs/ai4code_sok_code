package main

import (
	"crypto/aes"
	"crypto/cipher"
	"encoding/hex"
	"fmt"
)

// encryptString encrypts a message using a given CFB stream.
// It takes a byte slice (the message) and a cipher.Stream object.
// This is the equivalent of the Python function that takes a message and an AES object.
func encryptString(plaintext []byte, s cipher.Stream) []byte {
	ciphertext := make([]byte, len(plaintext))
	s.XORKeyStream(ciphertext, plaintext)
	return ciphertext
}

// decryptString decrypts a ciphertext using a given CFB stream.
// In CFB mode, decryption is the same operation as encryption (XORing with the keystream).
func decryptString(ciphertext []byte, s cipher.Stream) []byte {
	plaintext := make([]byte, len(ciphertext))
	s.XORKeyStream(plaintext, ciphertext)
	return plaintext
}

// Border line since we are sending an obj that is derived from a key rather a plaintext key
func main() {
	// In Go, keys and IVs are represented as byte slices.
	// The key must be 16, 24, or 32 bytes to select AES-128, AES-192, or AES-256.
	key := []byte("This is a key123") // 16 bytes for AES-128
	iv := []byte("This is an IV456")  // 16 bytes for AES block size

	// In Go, you first create a "block" cipher, which represents the AES algorithm itself.
	// This can return an error if the key size is invalid.
	block, err := aes.NewCipher(key)
	if err != nil {
		panic(fmt.Sprintf("error creating new cipher: %v", err))
	}

	// The IV must be the same size as the cipher's block size.
	if len(iv) != aes.BlockSize {
		panic(fmt.Sprintf("IV length must be %d", aes.BlockSize))
	}

	// Create the AES cipher in CFB mode, returning a Stream.
	// This is equivalent to Python's `AES.new(key, AES.MODE_CFB, iv)`.
	// obj1:
	streamEncrypter := cipher.NewCFBEncrypter(block, iv)

	// obj2: We create a new stream for decryption. For CFB mode, the decrypter
	// is created using the same function as the encrypter in some libraries,
	// but Go provides a distinct `NewCFBDecrypter` for clarity. The underlying
	// operation is identical to encryption.
	streamDecrypter := cipher.NewCFBDecrypter(block, iv)

	// The message to be encrypted, as a byte slice.
	message := []byte("The answer is no")

	// Encrypt the message using the first stream object.
	enc := encryptString(message, streamEncrypter)

	// Decrypt the message using the second stream object.
	dec := decryptString(enc, streamDecrypter)

	// Print the results to verify correctness.
	fmt.Printf("Original Message:  %s\n", string(message))
	// Ciphertext is binary data, so we print it as a hex string for readability.
	fmt.Printf("Encrypted (hex):   %s\n", hex.EncodeToString(enc))
	fmt.Printf("Decrypted Message: %s\n", string(dec))
}