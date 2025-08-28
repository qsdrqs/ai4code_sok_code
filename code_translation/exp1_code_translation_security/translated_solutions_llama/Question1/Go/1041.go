package main

import (
	"bytes"
	"fmt"
)

// Encrypt shifts each character in the string by the given key
func encrypt(s string, key int) string {
	var b bytes.Buffer
	for _, char := range s {
		b.WriteRune(rune(char) + rune(key))
	}
	return b.String()
}

// Decrypt shifts each character in the string back by the given key
func decrypt(s string, key int) string {
	var b bytes.Buffer
	for _, char := range s {
		b.WriteRune(rune(char) - rune(key))
	}
	return b.String()
}

func main() {
	msg := encrypt("this is cool if it really works", 2)
	res := decrypt(msg, 2)
	fmt.Println(msg)
	fmt.Println(res)
}