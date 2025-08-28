package main

import (
	"bytes"
	"crypto/rand"
	"crypto/sha256"
	"encoding/base64"
	"encoding/binary"
	"fmt"
	"log"
	"time"

	"golang.org/x/crypto/pbkdf2"

	"github.com/fernet/fernet-go"
)

/*
   ------------------------------------------------------------------
   Helpers
   ------------------------------------------------------------------
*/

// deriveKey replicates the Python _derive_key() helper.
// It returns the URL-safe base-64 representation of a 32-byte key.
func deriveKey(password, salt []byte, iterations int) []byte {
	key := pbkdf2.Key(password, salt, iterations, 32, sha256.New) // 32-byte raw key
	enc := base64.URLEncoding.EncodeToString(key)                 // url-safe, includes padding
	return []byte(enc)
}

/*
   ------------------------------------------------------------------
   Encrypt & Decrypt
   ------------------------------------------------------------------
*/

// encrypt behaves like the Python encrypt() function.
func encrypt(message, password string, iterations int) ([]byte, error) {
	// 1. random 16-byte salt
	salt := make([]byte, 16)
	if _, err := rand.Read(salt); err != nil {
		return nil, err
	}

	// 2. derive Fernet key from password & salt
	keyEnc := deriveKey([]byte(password), salt, iterations)
	keys, err := fernet.DecodeKeys([]string{string(keyEnc)})
	if err != nil {
		return nil, err
	}
	key := keys[0]

	// 3. Fernet-encrypt the payload
	tokenB64 := fernet.EncryptAndSign([]byte(message), key)

	// 4. Strip Fernet’s base-64 so we can prepend salt/iterations
	tokenRaw, err := base64.URLEncoding.DecodeString(string(tokenB64))
	if err != nil {
		return nil, err
	}

	// 5. Build:  salt (16) | iter(4 big-endian) | raw-token
	var buf bytes.Buffer
	buf.Write(salt)

	iterBytes := make([]byte, 4)
	binary.BigEndian.PutUint32(iterBytes, uint32(iterations))
	buf.Write(iterBytes)

	buf.Write(tokenRaw)

	// 6. One last base-64 (URL-safe) – this is what we hand the caller
	final := base64.URLEncoding.EncodeToString(buf.Bytes())
	return []byte(final), nil
}

// decrypt mirrors the Python decrypt() routine.
func decrypt(token []byte, password string) ([]byte, error) {
	// 1. Undo outer base-64 wrapper
	packed, err := base64.URLEncoding.DecodeString(string(token))
	if err != nil {
		return nil, err
	}
	if len(packed) < 20 { // 16(salt)+4(iter)+something
		return nil, fmt.Errorf("token too short")
	}

	// 2. Split the triple
	salt := packed[:16]
	iterBytes := packed[16:20]
	iterations := int(binary.BigEndian.Uint32(iterBytes))
	rawToken := packed[20:]

	// 3. Re-encode raw Fernet bytes back to URL-safe base-64
	tokenB64 := base64.URLEncoding.EncodeToString(rawToken)

	// 4. Recreate the Fernet key from password & stored salt
	keyEnc := deriveKey([]byte(password), salt, iterations)
	keys, err := fernet.DecodeKeys([]string{string(keyEnc)})
	if err != nil {
		return nil, err
	}
	key := keys[0]

	// 5. Verify & decrypt
	plain, err := fernet.VerifyAndDecrypt([]byte(tokenB64), 0*time.Second, []*fernet.Key{key})
	if err != nil {
		return nil, err
	}
	return plain, nil
}

/*
   ------------------------------------------------------------------
   Demo (equivalent to Python test in original code)
   ------------------------------------------------------------------
*/

func main() {
	token, err := encrypt("nice123", "password123", 100_000)
	if err != nil {
		log.Fatalf("encrypt: %v", err)
	}
	fmt.Println(string(token))

	plain, err := decrypt(token, "password123")
	if err != nil {
		log.Fatalf("decrypt: %v", err)
	}
	fmt.Println(string(plain))
}