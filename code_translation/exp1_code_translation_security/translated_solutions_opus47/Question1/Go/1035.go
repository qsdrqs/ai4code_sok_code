package main

import (
	"crypto/aes"
	"crypto/rand"
	"fmt"

	"github.com/pedroalbanese/eax"
)

var key = []byte("Sixteen byte key")

func encrypt(key, msg []byte) (ciphertext, tag, nonce []byte, err error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return
	}

	aead, err := eax.NewEAX(block)
	if err != nil {
		return
	}

	nonce = make([]byte, aead.NonceSize())
	if _, err = rand.Read(nonce); err != nil {
		return
	}

	// Seal returns ciphertext || tag concatenated
	sealed := aead.Seal(nil, nonce, msg, nil)
	tagSize := aead.Overhead()
	ciphertext = sealed[:len(sealed)-tagSize]
	tag = sealed[len(sealed)-tagSize:]

	return
}

func decrypt(key, ciphertext, nonce, tag []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	aead, err := eax.NewEAX(block)
	if err != nil {
		return nil, err
	}

	// Open expects ciphertext || tag concatenated
	sealed := make([]byte, 0, len(ciphertext)+len(tag))
	sealed = append(sealed, ciphertext...)
	sealed = append(sealed, tag...)

	return aead.Open(nil, nonce, sealed, nil)
}

func main() {
	ctext, tag, nonce, err := encrypt(key, []byte("message"))
	if err != nil {
		panic(err)
	}

	plaintext, err := decrypt(key, ctext, nonce, tag)
	if err != nil {
		panic(err)
	}

	fmt.Println(string(plaintext))
}