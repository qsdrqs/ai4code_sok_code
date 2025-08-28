package main

import (
	"crypto/aes"
	"crypto/cipher"
	"fmt"
)

func encryptString(message []byte, stream cipher.Stream) []byte {
	ciphertext := make([]byte, len(message))
	stream.XORKeyStream(ciphertext, message)
	return ciphertext
}

func decryptString(ciphertext []byte, stream cipher.Stream) []byte {
	plaintext := make([]byte, len(ciphertext))
	stream.XORKeyStream(plaintext, ciphertext)
	return plaintext
}

func main() {
	key := []byte("This is a key123")
	iv := []byte("This is an IV456")
	
	// Create AES cipher block
	block1, err := aes.NewCipher(key)
	if err != nil {
		panic(err)
	}
	
	block2, err := aes.NewCipher(key)
	if err != nil {
		panic(err)
	}
	
	// Create CFB mode streams
	stream1 := cipher.NewCFBEncrypter(block1, iv)
	stream2 := cipher.NewCFBDecrypter(block2, iv)
	
	message := []byte("The answer is no")
	enc := encryptString(message, stream1)
	dec := decryptString(enc, stream2)
	
	fmt.Printf("Original: %s\n", string(message))
	fmt.Printf("Encrypted: %x\n", enc)
	fmt.Printf("Decrypted: %s\n", string(dec))
}