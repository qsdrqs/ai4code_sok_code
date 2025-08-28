package main

import (
	"fmt"
)

func encrypt(message, key string) string {
	encrypted := ""
	keyLength := len(key)
	for i := 0; i < len(message); i++ {
		encrypted += string(message[i] ^ key[i%keyLength])
	}
	return encrypted
}

func decrypt(encrypted, key string) string {
	return encrypt(encrypted, key)
}

func main() {
	message := "Hello world"
	key := "key"
	encryptedMessage := encrypt(message, key)
	decryptedMessage := decrypt(encryptedMessage, key)
	fmt.Println(decryptedMessage)
}