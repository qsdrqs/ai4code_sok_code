package main

import (
	"crypto/aes"
	"crypto/cipher"
	"fmt"
)

// Where k is 256 bit UTF-8 encoded.
func encrypt(k []byte, m string) ([]byte, error) {
	block, err := aes.NewCipher(k)
	if err != nil {
		return nil, err
	}
	// pyaes's default counter starts at 1, represented as a big-endian
	// 128-bit integer (i.e. 15 zero bytes followed by 0x01).
	iv := make([]byte, aes.BlockSize)
	iv[aes.BlockSize-1] = 1
	stream := cipher.NewCTR(block, iv)
	ct := make([]byte, len(m))
	stream.XORKeyStream(ct, []byte(m))
	return ct, nil
}

// Where k is UTF-8 encoded.
func decrypt(k, ct []byte) (string, error) {
	block, err := aes.NewCipher(k)
	if err != nil {
		return "", err
	}
	iv := make([]byte, aes.BlockSize)
	iv[aes.BlockSize-1] = 1
	stream := cipher.NewCTR(block, iv)
	m := make([]byte, len(ct))
	stream.XORKeyStream(m, ct)
	return string(m), nil
}

func test() {
	roundTripTest := func() {
		k := []byte("This_key_for_demo_purposes_only!")
		ct, err := encrypt(k, "Text may be any length you wish, no padding is required")
		if err != nil {
			panic(err)
		}
		m, err := decrypt(k, ct)
		if err != nil {
			panic(err)
		}
		fmt.Println(m)
	}
	roundTripTest()
}

func main() {
	test()
}