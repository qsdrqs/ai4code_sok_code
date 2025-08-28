package main

import (
	"fmt"
)

// encrypt XOR-encodes a string with the given key.
func encrypt(s, key string) string {
	if len(key) == 0 {
		return s // nothing to do
	}

	// Work on raw bytes.
	src := []byte(s)
	k   := []byte(key)

	dst := make([]byte, len(src))
	for i := range src {
		dst[i] = src[i] ^ k[i%len(k)]
	}
	return string(dst)
}

// decrypt is identical to encrypt because XOR is symmetric.
func decrypt(s, key string) string {
	return encrypt(s, key)
}

func main() {
	plain := "sadasdasdsa"
	key   := "1234"

	cipher := encrypt(plain, key)
	recovered := decrypt(cipher, key)

	if recovered != plain {
		panic("encryption/decryption failed")
	}
	fmt.Println("cipher text:", cipher)
	fmt.Println("recovered  :", recovered)
}