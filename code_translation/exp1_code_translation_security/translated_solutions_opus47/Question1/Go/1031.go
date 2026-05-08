package main

import (
	"fmt"
	"time"

	"github.com/fernet/fernet-go"
)

var key *fernet.Key

// encrypt using a symmetric key
func encrypt(message string) []byte {
	encMessage, err := fernet.EncryptAndSign([]byte(message), key)
	if err != nil {
		panic(err)
	}
	return encMessage
}

// decrypt using a symmetric key
func decrypt(encMessage []byte) string {
	decMessage := fernet.VerifyAndDecrypt(encMessage, time.Hour, []*fernet.Key{key})
	if decMessage == nil {
		panic("decryption failed")
	}
	return string(decMessage)
}

func main() {
	message := "the solutions are here somewhere"
	fmt.Println("Secret Message:", message)

	key = new(fernet.Key)
	if err := key.Generate(); err != nil {
		panic(err)
	}

	encMessage := encrypt(message)
	fmt.Println(string(encMessage))

	decMessage := decrypt(encMessage)
	fmt.Println("decrypted Message:", decMessage)
}