// main.go
package main

import (
	"crypto/aes"
	"crypto/rand"
	"fmt"
	"log"

	"github.com/aead/cipher/eax"
)

// encodeString encrypts the given message with AES-EAX.
// It returns the ciphertext (without the tag) and the authentication tag.
func encodeString(message string, key, nonce []byte) (ciphertext, tag []byte, err error) {
	// Create AES block cipher.
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, nil, err
	}

	// Wrap it with EAX (AEAD) mode.
	aead, err := eax.NewEAX(block)
	if err != nil {
		return nil, nil, err
	}

	// Encrypt (and authenticate) the data.
	// aead.Seal returns ciphertext || tag, so we'll split them.
	buf := aead.Seal(nil, nonce, []byte(message), nil)

	tagSize := aead.Overhead()
	ciphertext = buf[:len(buf)-tagSize]
	tag = buf[len(buf)-tagSize:]

	return ciphertext, tag, nil
}

// decodeString performs the inverse operation of encodeString.
func decodeString(ciphertext, tag, key, nonce []byte) (string, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	aead, err := eax.NewEAX(block)
	if err != nil {
		return "", err
	}

	// Re-assemble “ciphertext || tag” that aead.Open expects.
	data := append(ciphertext, tag...)

	plain, err := aead.Open(nil, nonce, data, nil)
	if err != nil {
		return "", err
	}

	return string(plain), nil
}

func main() {
	originalMessage := "secret data"

	// Generate 16-byte key and 16-byte nonce (matching the Python example).
	key := make([]byte, 16)
	nonce := make([]byte, 16)
	if _, err := rand.Read(key); err != nil {
		log.Fatal(err)
	}
	if _, err := rand.Read(nonce); err != nil {
		log.Fatal(err)
	}

	fmt.Println(originalMessage)

	ciphertext, tag, err := encodeString(originalMessage, key, nonce)
	if err != nil {
		log.Fatal(err)
	}

	decoded, err := decodeString(ciphertext, tag, key, nonce)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println(decoded)
}