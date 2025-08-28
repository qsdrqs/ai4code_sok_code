package main

import (
	"crypto/aes"
	"crypto/cipher"
	"fmt"
)

// encrypt encrypts a message using AES-CTR mode
// k is 256 bit (32 bytes) key, m is the message string
func encrypt(k []byte, m string) []byte {
	block, err := aes.NewCipher(k)
	if err != nil {
		panic(err)
	}
	
	// Create a zero IV for CTR mode (matching pyaes default behavior)
	iv := make([]byte, aes.BlockSize)
	
	stream := cipher.NewCTR(block, iv)
	
	plaintext := []byte(m)
	ciphertext := make([]byte, len(plaintext))
	stream.XORKeyStream(ciphertext, plaintext)
	
	return ciphertext
}

// decrypt decrypts ciphertext using AES-CTR mode
// k is the key, ct is the ciphertext
func decrypt(k []byte, ct []byte) string {
	block, err := aes.NewCipher(k)
	if err != nil {
		panic(err)
	}
	
	// Create a zero IV for CTR mode (matching pyaes default behavior)
	iv := make([]byte, aes.BlockSize)
	
	stream := cipher.NewCTR(block, iv)
	
	plaintext := make([]byte, len(ct))
	stream.XORKeyStream(plaintext, ct)
	
	return string(plaintext)
}

func test() {
	roundTripTest := func() {
		k := []byte("This_key_for_demo_purposes_only!")
		ct := encrypt(
			k,
			"Text may be any length you wish, no padding is required",
		)
		fmt.Println(decrypt(k, ct))
	}
	
	roundTripTest()
}

func main() {
	test()
}