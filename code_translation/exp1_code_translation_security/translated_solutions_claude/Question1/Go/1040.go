package main

import (
	"fmt"
)

// encrypt a string with a given key
func encrypt(str string, key string) string {
	encrypted := ""
	for i := 0; i < len(str); i++ {
		encrypted += string(str[i] ^ key[i%len(key)])
	}
	return encrypted
}

// decrypt a string with a given key
func decrypt(str string, key string) string {
	return encrypt(str, key)
}

func main() {
	// Test assertion equivalent
	original := "sadasdasdsa"
	key := "1234"
	encrypted := encrypt(original, key)
	decrypted := decrypt(encrypted, key)
	
	if decrypted != original {
		panic(fmt.Sprintf("Assertion failed: expected %s, got %s", original, decrypted))
	}
	
	fmt.Println("Test passed!")
}