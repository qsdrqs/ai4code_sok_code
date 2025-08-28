package main

import "fmt"

// encrypt performs the same byte–wise Vigenère-style encryption
// as the original Python version.
func encrypt(key, s string) string {
	// Work with raw bytes so we can apply 0-255 arithmetic.
	keyBytes := []byte(key)
	srcBytes := []byte(s)

	keyLen := len(keyBytes)
	out := make([]byte, len(srcBytes))

	for i, b := range srcBytes {
		keyC := keyBytes[i%keyLen]
		out[i] = byte(int(b+keyC) % 256)
	}
	return string(out)
}

// decrypt reverses the transformation done by encrypt.
func decrypt(key, s string) string {
	keyBytes := []byte(key)
	srcBytes := []byte(s)

	keyLen := len(keyBytes)
	out := make([]byte, len(srcBytes))

	for i, b := range srcBytes {
		keyC := keyBytes[i%keyLen]
		out[i] = byte((int(b)-int(keyC)+256) % 256)
	}
	return string(out)
}

// Demonstration
func main() {
	key := "mysecret"
	plain := "Hello, 世界!"

	cipher := encrypt(key, plain)
	fmt.Printf("Encrypted: %q\n", cipher)

	restored := decrypt(key, cipher)
	fmt.Printf("Decrypted: %q\n", restored)
}