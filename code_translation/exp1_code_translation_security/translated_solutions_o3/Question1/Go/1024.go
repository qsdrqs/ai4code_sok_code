package main

import (
	"crypto/aes"
	"crypto/cipher"
	"fmt"
)

// encryptString performs CFB encryption using the supplied Stream object.
func encryptString(message []byte, stream cipher.Stream) []byte {
	cipherText := make([]byte, len(message))
	stream.XORKeyStream(cipherText, message)
	return cipherText
}

// decryptString performs CFB decryption using the supplied Stream object.
func decryptString(cipherText []byte, stream cipher.Stream) []byte {
	plainText := make([]byte, len(cipherText))
	stream.XORKeyStream(plainText, cipherText)
	return plainText
}

func main() {
	key := []byte("This is a key123") // 16-byte key (AES-128)
	iv  := []byte("This is an IV456") // 16-byte IV

	// Encrypter
	blockEnc, err := aes.NewCipher(key)
	if err != nil {
		panic(err)
	}
	encrypter := cipher.NewCFBEncrypter(blockEnc, iv)

	// Decrypter (independent object, just like `obj2` in the Python code)
	blockDec, err := aes.NewCipher(key)
	if err != nil {
		panic(err)
	}
	decrypter := cipher.NewCFBDecrypter(blockDec, iv)

	message := []byte("The answer is no")
	enc := encryptString(message, encrypter)
	dec := decryptString(enc, decrypter)

	fmt.Printf("Plain text : %s\n", message)
	fmt.Printf("Cipher text: %x\n", enc) // hex printed to show raw bytes
	fmt.Printf("Decrypted  : %s\n", dec)
}