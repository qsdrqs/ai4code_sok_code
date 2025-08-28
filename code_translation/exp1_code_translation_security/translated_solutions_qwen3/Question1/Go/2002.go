package main

import (
	"crypto/aes"
	"crypto/cipher"
	"fmt"
)

// encrypt encrypts the given string using AES-CTR with a 256-bit key.
// The nonce is 8 zero bytes, as in the original Python code.
func encrypt(k []byte, m string) []byte {
	block, err := aes.NewCipher(k)
	if err != nil {
		panic(err) // Handle error in production code
	}

	// CTR requires a nonce of half the block size (8 bytes for AES)
	nonce := make([]byte, 8)

	// Create the CTR stream
	stream := cipher.NewCTR(block, nonce)

	// Convert the string to bytes
	pt := []byte(m)

	// Allocate space for the ciphertext
	ct := make([]byte, len(pt))

	// Encrypt the plaintext
	stream.XORKeyStream(ct, pt)

	return ct
}

// decrypt decrypts the given ciphertext using AES-CTR with the same key.
// The nonce is again 8 zero bytes.
func decrypt(k []byte, ct []byte) string {
	block, err := aes.NewCipher(k)
	if err != nil {
		panic(err) // Handle error in production code
	}

	// Use the same 8-byte zero nonce
	nonce := make([]byte, 8)

	// Create the CTR stream
	stream := cipher.NewCTR(block, nonce)

	// Allocate space for the plaintext
	pt := make([]byte, len(ct))

	// Decrypt the ciphertext
	stream.XORKeyStream(pt, ct)

	// Convert the decrypted bytes back to a UTF-8 string
	return string(pt)
}

// test runs a round-trip encryption and decryption test.
func test() {
	// Key must be 32 bytes (256 bits) for AES-256
	k := []byte("This_key_for_demo_purposes_only!")

	// Message to encrypt
	message := "Text may be any length you wish, no padding is required"

	// Encrypt the message
	ct := encrypt(k, message)

	// Decrypt the ciphertext
	decrypted := decrypt(k, ct)

	// Print the result
	fmt.Println(decrypted)
}

// main runs the test function
func main() {
	test()
}