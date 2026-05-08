package main

import "fmt"

// encrypt a string with a given key
func encrypt(s, key string) string {
	encrypted := make([]byte, len(s))
	for i := 0; i < len(s); i++ {
		encrypted[i] = s[i] ^ key[i%len(key)]
	}
	return string(encrypted)
}

// decrypt a string with a given key
func decrypt(s, key string) string {
	return encrypt(s, key)
}

func main() {
	if decrypt(encrypt("sadasdasdsa", "1234"), "1234") != "sadasdasdsa" {
		panic("assertion failed")
	}
	fmt.Println("Assertion passed")
}