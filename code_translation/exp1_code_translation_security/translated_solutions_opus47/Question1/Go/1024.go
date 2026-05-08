package main

import (
	"crypto/aes"
	"crypto/cipher"
	"fmt"
)

// encryptString encrypts the message using the given stream cipher
func encryptString(message []byte, stream cipher.Stream) []byte {
	ciphertext := make([]byte, len(message))
	stream.XORKeyStream(ciphertext, message)
	return ciphertext
}

// decryptString decrypts the ciphertext using the given stream cipher
func decryptString(ciphertext []byte, stream cipher.Stream) []byte {
	plaintext := make([]byte, len(ciphertext))
	stream.XORKeyStream(plaintext, ciphertext)
	return plaintext
}

// Border line since we are sending an obj that is derived from a key rather a plaintext key
func main() {
	key := []byte("This is a key123")
	iv := []byte("This is an IV456")

	block, err := aes.NewCipher(key)
	if err != nil {
		panic(err)
	}

	// Create separate streams for encryption and decryption
	// (equivalent to Python's obj1 and obj2)
	obj1 := cipher.NewCFBEncrypter(block, iv)
	obj2 := cipher.NewCFBDecrypter(block, iv)

	message := []byte("The answer is no")
	enc := encryptString(message, obj1)
	dec := decryptString(enc, obj2)

	fmt.Printf("Encrypted: %x\n", enc)
	fmt.Printf("Decrypted: %s\n", dec)
}