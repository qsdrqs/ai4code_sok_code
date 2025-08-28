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

	"github.com/fernet/fernet-go"
	"golang.org/x/crypto/pbkdf2"
)

// deriveKey derives a secret key from a given password and salt, matching the Python implementation.
// The key is returned as a URL-safe base64 encoded string, as expected by the Fernet library.
func deriveKey(password, salt []byte, iterations int) ([]byte, error) {
	// Python's PBKDF2HMAC with SHA256 and length=32 is equivalent to this.
	rawKey := pbkdf2.Key(password, salt, iterations, 32, sha256.New)

	// The Python Fernet implementation expects the key to be base64 encoded.
	encodedKey := make([]byte, base64.URLEncoding.EncodedLen(len(rawKey)))
	base64.URLEncoding.Encode(encodedKey, rawKey)
	return encodedKey, nil
}

// encrypt encrypts a message with a password, producing a token that is compatible
// with the Python version.
func encrypt(message, password string, iterations int) ([]byte, error) {
	// 1. Generate a cryptographically secure salt.
	salt := make([]byte, 16)
	if _, err := rand.Read(salt); err != nil {
		return nil, fmt.Errorf("failed to generate salt: %w", err)
	}

	// 2. Derive the encryption key from the password and salt.
	keyBytes, err := deriveKey([]byte(password), salt, iterations)
	if err != nil {
		return nil, fmt.Errorf("failed to derive key: %w", err)
	}

	// The fernet-go library expects the key in a specific struct.
	key, err := fernet.DecodeKey(string(keyBytes))
	if err != nil {
		return nil, fmt.Errorf("failed to decode fernet key: %w", err)
	}

	// 3. Encrypt the message using Fernet.
	// The result from fernet-go is already a URL-safe base64 encoded string.
	encryptedData, err := fernet.EncryptAndSign([]byte(message), key)
	if err != nil {
		return nil, fmt.Errorf("failed to encrypt message: %w", err)
	}

	// 4. The Python implementation decodes the Fernet token before prepending salt and iterations.
	// We must do the same for compatibility.
	rawEncrypted, err := base64.URLEncoding.DecodeString(string(encryptedData))
	if err != nil {
		return nil, fmt.Errorf("failed to decode fernet token: %w", err)
	}

	// 5. Convert iterations to a 4-byte big-endian slice.
	iterBytes := make([]byte, 4)
	binary.BigEndian.PutUint32(iterBytes, uint32(iterations))

	// 6. Construct the final payload: salt + iterations + raw_encrypted_data
	var payload bytes.Buffer
	payload.Write(salt)
	payload.Write(iterBytes)
	payload.Write(rawEncrypted)

	// 7. Base64 encode the entire payload to create the final token.
	finalToken := make([]byte, base64.URLEncoding.EncodedLen(payload.Len()))
	base64.URLEncoding.Encode(finalToken, payload.Bytes())

	return finalToken, nil
}

// decrypt decrypts a token created by the encrypt function (or its Python equivalent).
func decrypt(token, password []byte) ([]byte, error) {
	// 1. Base64 decode the incoming token.
	decoded, err := base64.URLEncoding.DecodeString(string(token))
	if err != nil {
		return nil, fmt.Errorf("failed to base64 decode token: %w", err)
	}

	if len(decoded) < 20 {
		return nil, fmt.Errorf("invalid token: too short")
	}

	// 2. Extract the salt, iterations, and encrypted data.
	salt := decoded[:16]
	iterBytes := decoded[16:20]
	encryptedPart := decoded[20:]

	iterations := int(binary.BigEndian.Uint32(iterBytes))

	// 3. Re-derive the key using the extracted salt and iterations.
	keyBytes, err := deriveKey(password, salt, iterations)
	if err != nil {
		return nil, fmt.Errorf("failed to derive key: %w", err)
	}

	key, err := fernet.DecodeKey(string(keyBytes))
	if err != nil {
		return nil, fmt.Errorf("failed to decode fernet key: %w", err)
	}

	// 4. The Python Fernet implementation expects a base64 encoded token.
	// We must re-encode the encrypted part before passing it to the decrypt function.
	fernetToken := make([]byte, base64.URLEncoding.EncodedLen(len(encryptedPart)))
	base64.URLEncoding.Encode(fernetToken, encryptedPart)

	// 5. Decrypt the message. TTL is set to 0 to disable expiration checks.
	decrypted := fernet.VerifyAndDecrypt(fernetToken, 0*time.Second, []*fernet.Key{key})
	if decrypted == nil {
		return nil, fmt.Errorf("failed to verify or decrypt token: invalid token or password")
	}

	return decrypted, nil
}

func main() {
	// The default iterations in the Python script is 100,000
	const iterations = 100_000

	// Encrypt the message
	token, err := encrypt("nice123", "password123", iterations)
	if err != nil {
		log.Fatalf("Encryption failed: %v", err)
	}
	fmt.Println(string(token))

	// Decrypt the message
	decrypted, err := decrypt(token, []byte("password123"))
	if err != nil {
		log.Fatalf("Decryption failed: %v", err)
	}
	fmt.Println(string(decrypted))
}