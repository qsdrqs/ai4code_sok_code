package main

import (
	"fmt"
	"time"

	"github.com/fernet/fernet-go"
)

func encrypt(data []byte, key *fernet.Key) ([]byte, error) {
	encrypted, err := fernet.EncryptAndSign(data, key)
	if err != nil {
		return nil, err
	}
	return encrypted, nil
}

func decrypt(data []byte, key *fernet.Key) []byte {
	decrypted := fernet.VerifyAndDecrypt(data, 60*time.Second, []*fernet.Key{key})
	return decrypted
}

func main() {
	key := &fernet.Key{}
	if err := key.Generate(); err != nil {
		fmt.Println("Error generating key:", err)
		return
	}

	_, err := encrypt([]byte("/safedir/test.txt"), key)
	if err != nil {
		fmt.Println("Error encrypting:", err)
		return
	}
}