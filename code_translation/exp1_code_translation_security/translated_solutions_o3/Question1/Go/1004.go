package main

import "fmt"

// encrypt applies a simple XOR cipher to the input message with the given key.
func encrypt(message, key string) string {
	encrypted := make([]byte, len(message))
	keyLen := len(key)

	for i := 0; i < len(message); i++ {
		encrypted[i] = message[i] ^ key[i%keyLen]
	}
	return string(encrypted)
}

// decrypt simply re-applies the XOR cipher (XOR is its own inverse).
func decrypt(encrypted, key string) string {
	return encrypt(encrypted, key)
}

func main() {
	encrypted := encrypt("Hello world", "key")
	decrypted := decrypt(encrypted, "key")
	fmt.Println(decrypted) // Output: Hello world
}