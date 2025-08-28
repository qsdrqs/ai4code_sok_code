package main

import (
	"fmt"
)

// encrypt shifts every rune in `text` forward by `key` Unicode code-points.
func encrypt(text string, key int) string {
	runes := []rune(text)
	for i, r := range runes {
		runes[i] = r + rune(key)
	}
	return string(runes)
}

// decrypt shifts every rune in `text` backward by `key` Unicode code-points.
func decrypt(text string, key int) string {
	runes := []rune(text)
	for i, r := range runes {
		runes[i] = r - rune(key)
	}
	return string(runes)
}

func main() {
	fmt.Println(encrypt("name", 1234))
	fmt.Println(decrypt("ՀԳԿԷ", 1234))
}