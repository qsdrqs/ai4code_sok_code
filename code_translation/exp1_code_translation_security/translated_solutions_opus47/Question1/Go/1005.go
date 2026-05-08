package main

import (
	"fmt"

	"github.com/fernet/fernet-go"
)

func encrypt(msg []byte, key *fernet.Key) ([]byte, error) {
	return fernet.EncryptAndSign(msg, key)
}

func decrypt(msg []byte, key *fernet.Key) []byte {
	// ttl of 0 means no expiration check (matches Python's default behavior)
	return fernet.VerifyAndDecrypt(msg, 0, []*fernet.Key{key})
}

func main() {
	var key fernet.Key
	if err := key.Generate(); err != nil {
		panic(err)
	}

	msg := []byte("a much longer message with punctuation!")
	e, err := encrypt(msg, &key)
	if err != nil {
		panic(err)
	}

	d := decrypt(e, &key)

	fmt.Println(string(e))
	fmt.Println(string(d))
}