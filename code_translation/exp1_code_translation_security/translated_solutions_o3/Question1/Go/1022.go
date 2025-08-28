// File: main.go
//
// Go translation of the Python/Fernet example.
//
//   $ go run main.go
//   gAAAAABl...           <-- ciphertext (base-64 token, will change each run)
//   Hello World!          <-- plaintext after decryption
//
package main

import (
	"fmt"
	"log"

	"github.com/fernet/fernet-go"
)

// encrypt mimics the Python encrypt() helper.
func encrypt(plainText string, key *fernet.Key) []byte {
	cipherText, err := fernet.EncryptAndSign([]byte(plainText), key)
	if err != nil {
		log.Fatalf("encrypt: %v", err)
	}
	return cipherText
}

// decrypt mimics the Python decrypt() helper.
func decrypt(cipherText []byte, key *fernet.Key) string {
	// TTL = 0 → do not enforce token age.
	plain := fernet.VerifyAndDecrypt(cipherText, 0, []*fernet.Key{key})
	if plain == nil {
		log.Fatal("decrypt: verification failed")
	}
	return string(plain)
}

func main() {
	// Generate a random Fernet key (equivalent to Fernet.generate_key()).
	var key fernet.Key
	if err := key.Generate(); err != nil {
		log.Fatalf("key generation failed: %v", err)
	}

	encrypted := encrypt("Hello World!", &key)
	fmt.Println(string(encrypted)) // ciphertext token

	decrypted := decrypt(encrypted, &key)
	fmt.Println(decrypted)         // "Hello World!"
}