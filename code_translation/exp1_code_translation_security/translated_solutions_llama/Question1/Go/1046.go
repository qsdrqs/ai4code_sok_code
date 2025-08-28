package main

import (
	"fmt"
)

// encrypt function takes a message and a key as input, 
// and returns the encrypted message.
func encrypt(msg string, key string) string {
	temp := ""
	for _, c := range msg {
		temp += string(c) + key
		fmt.Println(temp)
	}
	return temp
}

// decrypt function takes an encrypted message and a key as input, 
// and returns the decrypted message.
func decrypt(msg string, key string) string {
	control := 0
	temp := ""
	for i := 0; i < len(msg); i++ {
		if control%2 == 0 {
			temp += string(msg[i])
		}
		control++
	}
	return temp
}

func main() {
	fmt.Println(encrypt('a', 'b'))
	fmt.Println(decrypt("ab", 'b'))
}