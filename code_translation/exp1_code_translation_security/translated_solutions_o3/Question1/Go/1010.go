package main

/*
   Import cryptographic libraries
*/
import (
	"crypto/aes"
	"crypto/rand"
	"fmt"
	"io"

	"github.com/aead/cipher/eax"
)

/*
   Function to generate a symmetric key
*/
func generateKey() ([]byte, error) {
	key := make([]byte, 16) // 16-byte (128-bit) key – same size as in the Python example
	if _, err := io.ReadFull(rand.Reader, key); err != nil {
		return nil, err
	}
	return key, nil
}

/*
   Function to encrypt a string using a symmetric key
*/
func encrypt(key []byte, data string) (ciphertext []byte, nonce []byte, err error) {
	// Create an AES block cipher as the primitive
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, nil, err
	}

	// Create the EAX AEAD instance
	eaxCipher, err := eax.NewEAX(block)
	if err != nil {
		return nil, nil, err
	}

	// Generate a random nonce.  EAX supports nonce sizes up to 16 bytes.
	nonce = make([]byte, 16)
	if _, err = io.ReadFull(rand.Reader, nonce); err != nil {
		return nil, nil, err
	}

	// Encrypt and authenticate
	ciphertext = eaxCipher.Seal(nil, nonce, []byte(data), nil)
	return ciphertext, nonce, nil
}

/*
   Function to decrypt a string
*/
func decrypt(key []byte, nonce []byte, ciphertext []byte) (string, error) {
	// Recreate the AES block cipher
	block, err := aes.NewCipher(key)
	if err != nil {
		return "", err
	}

	// Recreate the EAX AEAD instance
	eaxCipher, err := eax.NewEAX(block)
	if err != nil {
		return "", err
	}

	// Decrypt and verify
	plaintext, err := eaxCipher.Open(nil, nonce, ciphertext, nil)
	if err != nil {
		return "", err
	}

	return string(plaintext), nil
}

/*
   Example / quick test
*/
func main() {
	key, err := generateKey()
	if err != nil {
		panic(err)
	}

	testData := "Jim's test"

	encrypted, nonce, err := encrypt(key, testData)
	if err != nil {
		panic(err)
	}

	restored, err := decrypt(key, nonce, encrypted)
	if err != nil {
		panic(err)
	}

	fmt.Println(restored)
}