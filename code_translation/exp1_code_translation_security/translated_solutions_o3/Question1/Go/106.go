package main

import (
	"fmt"
)

// encrypt applies a simple Vigenère-style cipher to a lowercase
// ASCII plaintext using the supplied key.
func encrypt(plaintext, key string) string {
	if len(key) == 0 {
		return plaintext
	}

	out := make([]byte, len(plaintext))
	for i := 0; i < len(plaintext); i++ {
		p := plaintext[i] - 'a'               // 0‥25
		k := key[i%len(key)] - 'a'            // 0‥25
		c := byte((int(p)+int(k))%26) + 'a'   // rotate and shift back to ASCII
		out[i] = c
	}
	return string(out)
}

// decrypt reverses the encrypt operation.
func decrypt(ciphertext, key string) string {
	if len(key) == 0 {
		return ciphertext
	}

	out := make([]byte, len(ciphertext))
	for i := 0; i < len(ciphertext); i++ {
		c := ciphertext[i] - 'a'                     // 0‥25
		k := key[i%len(key)] - 'a'                   // 0‥25
		p := byte((26+int(c)-int(k))%26) + 'a'       // add 26 to keep positive
		out[i] = p
	}
	return string(out)
}

func main() {
	plaintext := "helloworld"
	key := "python"

	ciphertext := encrypt(plaintext, key)
	fmt.Println("Ciphertext:", ciphertext)

	decrypted := decrypt(ciphertext, key)
	fmt.Println(decrypted)

	if plaintext != decrypted {
		panic("decryption failed")
	}
}