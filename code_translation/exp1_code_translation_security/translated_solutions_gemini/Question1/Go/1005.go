package main

import (
	"fmt"
	"log"

	// fernet-go is the Go equivalent of Python's cryptography.fernet
	"github.com/fernet/fernet-go"
)

// encrypt encrypts a message using a base64-encoded Fernet key.
// This function mirrors the Python example by creating a new Fernet instance internally for each call.
func encrypt(msg []byte, encodedKey string) ([]byte, error) {
	// In Python: f = Fernet(key)
	// In Go, we decode the key string to get a key object.
	k, err := fernet.DecodeKey(encodedKey)
	if err != nil {
		return nil, fmt.Errorf("failed to decode key: %w", err)
	}

	// In Python: return f.encrypt(msg)
	// In Go, we use the EncryptAndSign function with the message and key object.
	return fernet.EncryptAndSign(msg, k)
}

// decrypt decrypts a Fernet token using a base64-encoded key.
// This function also mirrors the Python example's structure.
func decrypt(token []byte, encodedKey string) ([]byte, error) {
	// In Python: f = Fernet(key)
	// In Go, we decode the key string to get a key object.
	k, err := fernet.DecodeKey(encodedKey)
	if err != nil {
		return nil, fmt.Errorf("failed to decode key: %w", err)
	}

	// In Python: return f.decrypt(msg)
	// In Go, we use the VerifyAndDecrypt function.
	// We use fernet.Forever to disable the TTL (time-to-live) check,
	// matching the default behavior of the Python library's decrypt method.
	return fernet.VerifyAndDecrypt(token, fernet.Forever, k)
}

// The main function corresponds to the `if __name__ == '__main__':` block in Python.
func main() {
	// In Python: key = Fernet.generate_key()
	// The Go library generates a key object directly. We then encode it
	// to get the base64 string representation that Python's Fernet uses.
	keyObj, err := fernet.GenerateKey()
	if err != nil {
		log.Fatalf("failed to generate key: %v", err)
	}
	encodedKey := keyObj.Encode()

	// In Python: msg = b"a much longer message with punctuation!"
	// In Go, a byte slice is created with []byte(...).
	msg := []byte("a much longer message with punctuation!")

	// In Python: e = encrypt(msg, key)
	// Note: The Python `key` is of type `bytes`, but it holds the base64 string.
	// In Go, we pass this encoded key as a standard string.
	encryptedToken, err := encrypt(msg, encodedKey)
	if err != nil {
		log.Fatalf("failed to encrypt message: %v", err)
	}

	// In Python: d = decrypt(e, key)
	decryptedMsg, err := decrypt(encryptedToken, encodedKey)
	if err != nil {
		log.Fatalf("failed to decrypt message: %v", err)
	}

	// In Python: print(e)
	// The encrypted token is URL-safe base64, so it's safe to print as a string.
	fmt.Printf("Encrypted: %s\n", string(encryptedToken))

	// In Python: print(d)
	fmt.Printf("Decrypted: %s\n", string(decryptedMsg))
}