package cryptohelpers

import (
	"crypto/aes"
	"crypto/rand"
	"io"

	// 3-rd-party implementation of EAX (the package will be supplied
	// with the rest of the project, so you don’t have to vendor it
	// yourself).
	"github.com/andreburgaud/crypt2go/eax"
)

/*
   The Python original:

       c, n = encrypt(m, sk)
       p    = decrypt((c, n), sk)

   The Go translation uses the very same calling pattern
   (cipher-text + nonce ↔ plain-text).
*/

// Holder used to return / pass around ciphertext + nonce
type CipherAndNonce struct {
	Ciphertext []byte
	Nonce      []byte
}

// Encrypt reproduces the behaviour of
//     AES.new(sk, AES.MODE_EAX).encrypt_and_digest(m)[0]
func Encrypt(m, sk []byte) (CipherAndNonce, error) {
	block, err := aes.NewCipher(sk) // sk must be 16, 24 or 32 bytes long
	if err != nil {
		return CipherAndNonce{}, err
	}

	// Create a random 16-byte nonce (same size PyCrypto uses by default)
	nonce := make([]byte, 16)
	if _, err = io.ReadFull(rand.Reader, nonce); err != nil {
		return CipherAndNonce{}, err
	}

	// Instantiate EAX
	aead, err := eax.NewEAX(block)
	if err != nil {
		return CipherAndNonce{}, err
	}

	// Seal = encrypt + authenticate (digest); additional data == nil
	ciphertext := aead.Seal(nil, nonce, m, nil)

	return CipherAndNonce{Ciphertext: ciphertext, Nonce: nonce}, nil
}

// Decrypt mirrors the Python `decrypt` function.
func Decrypt(cn CipherAndNonce, sk []byte) ([]byte, error) {
	block, err := aes.NewCipher(sk)
	if err != nil {
		return nil, err
	}

	aead, err := eax.NewEAX(block)
	if err != nil {
		return nil, err
	}

	// Open = decrypt + verify
	plaintext, err := aead.Open(nil, cn.Nonce, cn.Ciphertext, nil)
	if err != nil {
		return nil, err
	}

	return plaintext, nil
}