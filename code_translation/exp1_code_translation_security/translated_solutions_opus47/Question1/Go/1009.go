package main

import (
	"crypto/aes"
	"crypto/rand"
	"fmt"

	"github.com/enceve/crypto/eax"
)

func encodeString(message string, key, nonce []byte) [][]byte {
	block, err := aes.NewCipher(key)
	if err != nil {
		panic(err)
	}

	aead, err := eax.NewEAX(block)
	if err != nil {
		panic(err)
	}

	// Encode string as bytes
	mBytes := []byte(message)
	// Seal encrypts and appends the tag to the ciphertext
	sealed := aead.Seal(nil, nonce, mBytes, nil)

	// Split ciphertext and tag (tag is the last Overhead() bytes)
	tagSize := aead.Overhead()
	ciphertext := sealed[:len(sealed)-tagSize]
	tag := sealed[len(sealed)-tagSize:]

	return [][]byte{ciphertext, tag}
}

func decodeString(p [][]byte, key, nonce []byte) string {
	block, err := aes.NewCipher(key)
	if err != nil {
		panic(err)
	}

	aead, err := eax.NewEAX(block)
	if err != nil {
		panic(err)
	}

	// Combine ciphertext and tag since Go's AEAD.Open expects them concatenated
	combined := make([]byte, 0, len(p[0])+len(p[1]))
	combined = append(combined, p[0]...)
	combined = append(combined, p[1]...)

	data, err := aead.Open(nil, nonce, combined, nil)
	if err != nil {
		panic(err)
	}

	// Decode from bytes to string
	return string(data)
}

func main() {
	originalMessage := "secret data"

	nonce := make([]byte, 16)
	if _, err := rand.Read(nonce); err != nil {
		panic(err)
	}

	key := make([]byte, 16)
	if _, err := rand.Read(key); err != nil {
		panic(err)
	}

	fmt.Println(originalMessage)

	ciphertext := encodeString(originalMessage, key, nonce)
	decodedString := decodeString(ciphertext, key, nonce)
	fmt.Println(decodedString)
}