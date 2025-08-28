package main

import (
	"fmt"
	"log"

	// fernet-go is a Go implementation of the Fernet spec for symmetric encryption.
	// To install, run: go get github.com/fernet/fernet-go
	"github.com/fernet/fernet-go"
)

// encrypt translates the Python encrypt function.
// It takes a plaintext byte slice and a URL-safe base64-encoded key string.
// It returns the Fernet token as a byte slice, or an error if encryption fails.
func encrypt(input []byte, b64key string) ([]byte, error) {
	// In Python: f = Fernet(key)
	// The fernet-go library requires decoding the base64 key first.
	key, err := fernet.DecodeKey(b64key)
	if err != nil {
		return nil, err
	}

	// In Python: return f.encrypt(input)
	// The fernet-go equivalent is a package-level function.
	return fernet.Encrypt(input, key)
}

// decrypt translates the Python decrypt function.
// It takes a Fernet token byte slice and a URL-safe base64-encoded key string.
// It returns the original plaintext as a byte slice, or an error if decryption fails.
func decrypt(input []byte, b64key string) ([]byte, error) {
	// In Python: f = Fernet(key)
	key, err := fernet.DecodeKey(b64key)
	if err != nil {
		return nil, err
	}

	// In Python: return f.decrypt(input)
	// The fernet-go Decrypt function takes the token, a TTL, and a slice of keys to try.
	// We use fernet.Forever to mimic the default Python behavior of not checking the token's age.
	message := fernet.Decrypt(input, fernet.Forever, []*fernet.Key{key})
	if message == nil {
		// fernet-go's Decrypt returns nil on any failure (e.g., bad key, invalid token).
		return nil, fmt.Errorf("failed to decrypt token: it may be invalid or the key is incorrect")
	}

	return message, nil
}

func main() {
	// In Python: key = Fernet.generate_key()
	// fernet.NewKey() generates a new key object. We use its Encode() method
	// to get the URL-safe base64 string representation required by our functions.
	k := fernet.NewKey()
	key := k.Encode()

	// In Python: input = b'super secret'
	input := []byte("super secret")

	// In Python: encryptedInput = encrypt(input, key)
	encryptedInput, err := encrypt(input, key)
	if err != nil {
		log.Fatalf("Encryption failed: %v", err)
	}

	// In Python: decryptedInput = decrypt(encryptedInput, key)
	decryptedInput, err := decrypt(encryptedInput, key)
	if err != nil {
		log.Fatalf("Decryption failed: %v", err)
	}

	// In Python:
	// print(input)
	// print(decryptedInput)
	fmt.Printf("Original: %s\n", string(input))
	fmt.Printf("Decrypted: %s\n", string(decryptedInput))
}