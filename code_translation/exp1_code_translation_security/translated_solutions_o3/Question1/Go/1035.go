package main

import (
	"crypto/aes"
	"crypto/rand"
	"fmt"
	"io"
	"log"

	"github.com/andreburgaud/crypt2go/ae" // EAX implementation (will be supplied)
)

// encrypt reproduces the behaviour of the Python encrypt(..) function
// It returns:   ciphertext, tag (= MAC), nonce  – exactly like the Py-version
func encrypt(key, msg []byte) (ciphertext, tag, nonce []byte, err error) {
	// A random nonce (IV). 16-bytes is common, EAX supports 0-to-16.
	nonce = make([]byte, 16)
	if _, err = io.ReadFull(rand.Reader, nonce); err != nil {
		return
	}

	block, err := aes.NewCipher(key)
	if err != nil {
		return
	}

	eax, err := ae.NewEax(block) // create an EAX context for AES
	if err != nil {
		return
	}
	eax.Nonce = nonce

	ciphertext, err = eax.Encrypt(msg)
	if err != nil {
		return
	}
	tag = eax.Tag // authentication tag produced by Encrypt

	return
}

// decrypt is the pendant of the Python decrypt(..) function.
// If tag verification fails an error is returned – same semantics
func decrypt(key, ciphertext, nonce, tag []byte) ([]byte, error) {
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	eax, err := ae.NewEax(block)
	if err != nil {
		return nil, err
	}
	eax.Nonce = nonce
	eax.Tag = tag

	plaintext, err := eax.Decrypt(ciphertext) // Tag is verified internally
	if err != nil {
		return nil, err
	}
	return plaintext, nil
}

func main() {
	key := []byte("Sixteen byte key") // 16-byte key, identical to the Python sample

	// --- encrypt ------------------------------------------------------------
	ciphertext, tag, nonce, err := encrypt(key, []byte("message"))
	if err != nil {
		log.Fatalf("encryption failed: %v", err)
	}

	// --- decrypt ------------------------------------------------------------
	plaintext, err := decrypt(key, ciphertext, nonce, tag)
	if err != nil {
		log.Fatalf("decryption failed: %v", err)
	}

	fmt.Println(string(plaintext)) // -> "message"
}