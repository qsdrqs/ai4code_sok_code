package main

import (
	"crypto/rand"
	"fmt"
	"io"

	"github.com/mikelsr/aes-eax"
)

var key = []byte("Sixteen byte key")

// encrypt encrypts the message and returns the ciphertext, tag, and nonce.
func encrypt(key, msg []byte) (ciphertext, tag, nonce []byte, err error) {
	c, err := eax.NewCipher(key)
	if err != nil {
		return nil, nil, nil, err
	}

	nonce = make([]byte, c.NonceSize())
	if _, err := io.ReadFull(rand.Reader, nonce); err != nil {
		return nil, nil, nil, err
	}

	// Encrypt the message and get the full encrypted data (ciphertext + tag)
	encrypted := c.Seal(nil, nonce, msg, nil)

	// Split the encrypted data into ciphertext and tag
	tagSize := c.Overhead()
	if len(encrypted) < tagSize {
		return nil, nil, nil, fmt.Errorf("encrypted data too short")
	}

	ciphertext = encrypted[:len(encrypted)-tagSize]
	tag = encrypted[len(encrypted)-tagSize:]

	return ciphertext, tag, nonce, nil
}

// decrypt decrypts the message using the ciphertext, nonce, and tag.
func decrypt(key, ciphertext, nonce, tag []byte) ([]byte, error) {
	c, err := eax.NewCipher(key)
	if err != nil {
		return nil, err
	}

	// Combine ciphertext and tag for decryption
	encryptedData := append(ciphertext, tag...)

	// Decrypt and verify the tag in one step
	return c.Open(nil, nonce, encryptedData, nil)
}

func main() {
	// Encrypt the message
	ctext, tag, nonce, err := encrypt(key, []byte("message"))
	if err != nil {
		panic(err)
	}

	// Decrypt the message
	plaintext, err := decrypt(key, ctext, nonce, tag)
	if err != nil {
		panic(err)
	}

	// Print the decrypted message
	fmt.Println(string(plaintext))
}