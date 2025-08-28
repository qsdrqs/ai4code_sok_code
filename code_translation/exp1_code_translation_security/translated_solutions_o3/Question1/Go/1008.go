package main

import (
	"fmt"
)

// encrypt performs the same byte-wise Vigenère style encryption
// that the original Python encrypt() function carried out.
func encrypt(key, plaintext string) string {
	if len(key) == 0 {
		return plaintext
	}

	keyBytes := []byte(key)
	ptBytes := []byte(plaintext)
	ctBytes := make([]byte, len(ptBytes))

	for i, b := range ptBytes {
		keyByte := keyBytes[i%len(keyBytes)]
		ctBytes[i] = byte((int(b) + int(keyByte)) % 256)
	}
	return string(ctBytes)
}

// decrypt reverses the transformation done by encrypt().
func decrypt(key, ciphertext string) string {
	if len(key) == 0 {
		return ciphertext
	}

	keyBytes := []byte(key)
	ctBytes := []byte(ciphertext)
	ptBytes := make([]byte, len(ctBytes))

	for i, b := range ctBytes {
		keyByte := keyBytes[i%len(keyBytes)]
		ptBytes[i] = byte((256 + int(b) - int(keyByte)) % 256)
	}
	return string(ptBytes)
}

func main() {
	result := decrypt("123", encrypt("123", "helloworld"))
	fmt.Println(result) // Output: helloworld
}