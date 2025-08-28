package main

import (
	"crypto/aes"
	"crypto/cipher"
	"fmt"
)

// encrypt replicates the behaviour of pyaes.AESModeOfOperationCTR(key).encrypt(..)
// k must be 16, 24 or 32 bytes long (32 bytes == 256-bit key).
func encrypt(k []byte, m string) []byte {
	// Create the underlying AES block cipher.
	block, err := aes.NewCipher(k)
	if err != nil {
		panic(err) // keep the example short – bubble up real code in practice
	}

	// pyaes (when you don’t supply a counter) starts with a zero IV/counter.
	iv := make([]byte, aes.BlockSize) // 16 zero bytes

	// Build a CTR stream and encrypt.
	stream := cipher.NewCTR(block, iv)
	plain := []byte(m)
	ciphertext := make([]byte, len(plain))
	stream.XORKeyStream(ciphertext, plain)

	return ciphertext
}

// decrypt mirrors encrypt and returns the original UTF-8 string.
func decrypt(k, ct []byte) string {
	block, err := aes.NewCipher(k)
	if err != nil {
		panic(err)
	}

	iv := make([]byte, aes.BlockSize) // same zero IV
	stream := cipher.NewCTR(block, iv)

	plain := make([]byte, len(ct))
	stream.XORKeyStream(plain, ct)

	return string(plain) // Go strings are UTF-8 by definition
}

// Demonstrates a full encrypt-then-decrypt round-trip.
func roundTripTest() {
	k := []byte("This_key_for_demo_purposes_only!") // 32-byte / 256-bit key

	ct := encrypt(k,
		"Text may be any length you wish, no padding is required",
	)

	fmt.Println(decrypt(k, ct))
}

func main() {
	roundTripTest()
}