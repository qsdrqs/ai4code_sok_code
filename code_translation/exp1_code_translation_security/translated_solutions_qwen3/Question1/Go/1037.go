package main

import (
	"fmt"
	"time"

	fernet "github.com/fernet/fernet-go"
)

// Encrypts the input using the provided Fernet key and returns the encrypted token as a string.
func encrypt(input []byte, key fernet.Key) string {
	token := fernet.Encrypt(key, input)
	return token.String()
}

// Decrypts the provided token string using the given Fernet key and returns the original plaintext.
func decrypt(tokenStr string, key fernet.Key) []byte {
	token, _ := fernet.ParseToken(tokenStr)
	plaintext, _ := token.Decrypt(key, time.Time{})
	return plaintext
}

func main() {
	// Generate a new Fernet key
	key := fernet.GenerateKey()

	// Define the input data to be encrypted
	input := []byte("super secret")

	// Encrypt the input
	encryptedInput := encrypt(input, key)

	// Decrypt the encrypted token
	decryptedInput := decrypt(encryptedInput, key)

	// Print the original and decrypted data
	fmt.Println(string(input))
	fmt.Println(string(decryptedInput))
}