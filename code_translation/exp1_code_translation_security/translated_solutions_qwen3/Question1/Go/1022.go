package main

import (
	"fmt"
	"github.com/fernet/fernet-go"
)

func encrypt(inp string, key fernet.Key) string {
	encrypted := fernet.Encrypt([]byte(inp), key)
	return encrypted.String()
}

func decrypt(inp string, key fernet.Key) string {
	token, err := fernet.DecodeToken(inp)
	if err != nil {
		panic(err)
	}
	decrypted := token.Decrypt()
	if decrypted == nil {
		panic("decryption failed")
	}
	return string(decrypted.Payload)
}

func main() {
	exKey := fernet.GenerateKey()
	encrypted := encrypt("Hello World!", exKey)
	fmt.Println(encrypted)
	decrypted := decrypt(encrypted, exKey)
	fmt.Println(decrypted)
}