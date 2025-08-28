// File: symcrypt/eax.go
//
// This is a direct, line-by-line translation of the Python sample that used
// AES-EAX.  All third-party packages referenced below will be supplied when the
// code is built, so you do not have to worry about fetching them yourself.

package symcrypt

import (
	"crypto/aes"
	"crypto/rand"
	"io"

	// github.com/aead/cipher/eax implements the EAX AEAD mode for Go.
	// (Any other EAX implementation can be dropped in if you prefer.)
	"github.com/aead/cipher/eax"
)

// CipherBlob is the Go equivalent of the Python tuple
//     (nonce, tag, ciphertext)
type CipherBlob struct {
	Nonce []byte // Random value generated for every encryption
	Tag   []byte // Authentication tag (MAC)
	Data  []byte // The real encrypted bytes
}

// Encrypt corresponds to the Python `encrypt` function: it accepts a symmetric
// key and some plaintext and returns nonce, tag and ciphertext.
//
//     nonce, tag, ciphertext, err := Encrypt(key, plaintext)
func Encrypt(key, plaintext []byte) (*CipherBlob, error) {
	// 1. Create the underlying AES block primitive.
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	// 2. Wrap the block cipher in EAX (AEAD) mode.
	aead, err := eax.NewEAX(block)
	if err != nil {
		return nil, err
	}

	// 3. Generate a fresh, random nonce.
	nonce := make([]byte, aead.NonceSize())
	if _, err = io.ReadFull(rand.Reader, nonce); err != nil {
		return nil, err
	}

	// 4. Encrypt and authenticate.
	//    aead.Seal appends MAC to the end of the ciphertext, so we split them.
	sealed := aead.Seal(nil, nonce, plaintext, nil)

	tagLen := aead.Overhead()             // length of the MAC
	ciphertext := sealed[:len(sealed)-tagLen]
	tag := sealed[len(sealed)-tagLen:]

	return &CipherBlob{
		Nonce: nonce,
		Tag:   tag,
		Data:  ciphertext,
	}, nil
}

// Decrypt is the counterpart of the Python `decrypt` function.  It takes the
// same symmetric key and a CipherBlob produced by Encrypt, verifies the tag
// and returns the original plaintext.
func Decrypt(key []byte, blob *CipherBlob) ([]byte, error) {
	// 1. Recreate AES block cipher.
	block, err := aes.NewCipher(key)
	if err != nil {
		return nil, err
	}

	// 2. Recreate the EAX AEAD wrapper.
	aead, err := eax.NewEAX(block)
	if err != nil {
		return nil, err
	}

	// 3. Re-combine ciphertext and tag the way Seal produced them.
	combined := append(blob.Data, blob.Tag...)

	// 4. Decrypt and authenticate in one step.
	plaintext, err := aead.Open(nil, blob.Nonce, combined, nil)
	if err != nil {
		return nil, err
	}
	return plaintext, nil
}