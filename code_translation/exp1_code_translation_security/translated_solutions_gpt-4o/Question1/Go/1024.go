package main

import (
	"crypto/aes"
	"crypto/cipher"
	"fmt"
)

func encryptString(message string, block cipher.Block, iv []byte) ([]byte, error) {
	cfb := cipher.NewCFBEncrypter(block, iv)
	ciphertext := make([]byte, len(message))
	cfb.XORKeyStream(ciphertext, []byte(message))
	return ciphertext, nil
}

func decryptString(ciphertext []byte, block cipher.Block, iv []byte) (string, error) {
	cfb := cipher.NewCFBDecrypter(block, iv)
	plaintext := make([]byte, len(ciphertext))
	cfb.XORKeyStream(plaintext, ciphertext)
	return string(plaintext), nil
}

func main() {
	key := []byte("This is a key123")
	iv := []byte("This is an IV456")

	block, err := aes.NewCipher(key)
	if err != nil {
		fmt.Println("Error creating cipher:", err)
		return
	}

	message := "The answer is no"
	enc, err := encryptString(message, block, iv)
	if err != nil {
		fmt.Println("Error encrypting message:", err)
		return
	}

	dec, err := decryptString(enc, block, iv)
	if err != nil {
		fmt.Println("Error decrypting message:", err)
		return
	}

	fmt.Printf("Encrypted: %x\n", enc)
	fmt.Printf("Decrypted: %s\n", dec)
}