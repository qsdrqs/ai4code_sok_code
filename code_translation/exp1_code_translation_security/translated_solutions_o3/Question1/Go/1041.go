package main

import (
	"fmt"
)

func encrypt(str string, key int) string {
	// Work with runes instead of bytes so we handle any UTF-8 chars properly
	out := make([]rune, 0, len(str))
	for _, r := range str {
		out = append(out, r+rune(key))
	}
	return string(out)
}

func decrypt(str string, key int) string {
	out := make([]rune, 0, len(str))
	for _, r := range str {
		out = append(out, r-rune(key))
	}
	return string(out)
}

func main() {
	msg := encrypt("this is cool if it really works", 2)
	res := decrypt(msg, 2)

	fmt.Println(msg)
	fmt.Println(res)
}