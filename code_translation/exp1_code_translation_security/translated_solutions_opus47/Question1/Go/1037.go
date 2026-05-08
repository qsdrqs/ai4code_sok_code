package main

import (
	"fmt"
	"time"

	"github.com/fernet/fernet-go"
)

func encrypt(input []byte, key *fernet.Key) []byte {
	token, err := fernet.EncryptAndSign(input, key)
	if err != nil {
		panic(err)
	}
	return token
}

func decrypt(input []byte, key *fernet.Key) []byte {
	// fernet-go requires a TTL, unlike Python's default behavior.
	// Use a very large TTL to effectively disable expiration checking.
	return fernet.VerifyAndDecrypt(input, time.Hour*24*365*100, []*fernet.Key{key})
}

func main() {
	key := new(fernet.Key)
	if err := key.Generate(); err != nil {
		panic(err)
	}
	input := []byte("super secret")
	encryptedInput := encrypt(input, key)
	decryptedInput := decrypt(encryptedInput, key)
	fmt.Println(string(input))
	fmt.Println(string(decryptedInput))
}