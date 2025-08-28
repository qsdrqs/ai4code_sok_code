package main

import (
	"crypto/aes"
	"crypto/cipher"
	"encoding/hex"
	"fmt"
)

// Encrypt encrypts the message m using the key k.
func encrypt(k []byte, m string) ([]byte, error) {
	block, err := aes.NewCipher(k)
	if err != nil {
		return nil, err
	}

	// Create a new byte array for the ciphertext
	ciphertext := make([]byte, len(m))

	// Create a new CTR stream
	stream := cipher.NewCTR(block, k[:aes.BlockSize])

	// Encrypt the message
	stream.XORKeyStream(ciphertext, []byte(m))

	return ciphertext, nil
}

// Decrypt decrypts the ciphertext ct using the key k.
func decrypt(k []byte, ct []byte) (string, error) {
	block, err := aes.NewCipher(k)
	if err != nil {
		return "", err
	}

	// Create a new byte array for the decrypted text
	decrypted := make([]byte, len(ct))

	// Create a new CTR stream
	stream := cipher.NewCTR(block, k[:aes.BlockSize])

	// Decrypt the ciphertext
	stream.XORKeyStream(decrypted, ct)

	return string(decrypted), nil
}

func test() {
	roundTripTest := func() {
		k := []byte("This_key_for_demo_purposes_only!")
		ct, err := encrypt(k, "Text may be any length you wish, no padding is required")
		if err != nil {
			fmt.Println("Encryption error:", err)
			return
		}

		// Print the ciphertext in hex format for better readability
		fmt.Println("Ciphertext (hex):", hex.EncodeToString(ct))

		decrypted, err := decrypt(k, ct)
		if err != nil {
			fmt.Println("Decryption error:", err)
			return
		}

		fmt.Println("Decrypted text:", decrypted)
	}

	roundTripTest()
}

func main() {
	test()
}