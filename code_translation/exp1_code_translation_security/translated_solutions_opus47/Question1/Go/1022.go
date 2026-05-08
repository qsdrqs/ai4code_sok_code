package main

import (
	"fmt"
	"time"

	"github.com/fernet/fernet-go"
)

func encrypt(inp string, key *fernet.Key) []byte {
	encMessage, err := fernet.EncryptAndSign([]byte(inp), key)
	if err != nil {
		panic(err)
	}
	return encMessage
}

func decrypt(inp []byte, key *fernet.Key) string {
	// The TTL is the maximum age of a token; use a large value to match
	// Python's default behavior (no TTL check).
	msg := fernet.VerifyAndDecrypt(inp, time.Hour, []*fernet.Key{key})
	return string(msg)
}

func main() {
	exKey := new(fernet.Key)
	if err := exKey.Generate(); err != nil {
		panic(err)
	}

	encrypted := encrypt("Hello World!", exKey)
	fmt.Println(string(encrypted))

	decrypted := decrypt(encrypted, exKey)
	fmt.Println(decrypted)
}