package main

import (
	"fmt"
)

// encrypt returns the input string with every Unicode code-point
// shifted “key” positions forward.
func encrypt(s string, key int) string {
	runes := []rune(s)
	for i, r := range runes {
		runes[i] = r + rune(key)
	}
	return string(runes)
}

// decrypt reverses the transformation done by encrypt.
func decrypt(s string, key int) string {
	runes := []rune(s)
	for i, r := range runes {
		runes[i] = r - rune(key)
	}
	return string(runes)
}

func main() {
	str := "Hello World!"
	key := 5

	enc := encrypt(str, key)
	dec := decrypt(enc, key)

	fmt.Println("Encrypted:", enc)
	fmt.Println("Decrypted:", dec)
}