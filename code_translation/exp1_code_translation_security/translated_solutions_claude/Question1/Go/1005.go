package main

import (
	"fmt"
	"log"

	"github.com/fernet/fernet-go"
)

func encrypt(msg []byte, key []byte) ([]byte, error) {
	k := fernet.MustDecodeKeys(string(key))
	token, err := fernet.EncryptAndSign(msg, k[0])
	if err != nil {
		return nil, err
	}
	return token, nil
}

func decrypt(msg []byte, key []byte) ([]byte, error) {
	k := fernet.MustDecodeKeys(string(key))
	plaintext := fernet.VerifyAndDecrypt(msg, 0, k)
	return plaintext, nil
}

func main() {
	key := fernet.GenerateKey()
	msg := []byte("a much longer message with punctuation!")
	
	e, err := encrypt(msg, key)
	if err != nil {
		log.Fatal(err)
	}
	
	d, err := decrypt(e, key)
	if err != nil {
		log.Fatal(err)
	}
	
	fmt.Println(string(e))
	fmt.Println(string(d))
}