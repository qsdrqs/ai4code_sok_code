package main

import (
	"fmt"
)

func encrypt(input string, key int) string {
	newString := ""
	for _, char := range input {
		newString += string(rune(char) + rune(key))
	}
	return newString
}

func decrypt(input string, key int) string {
	newString := ""
	for _, char := range input {
		newString += string(rune(char) - rune(key))
	}
	return newString
}

func main() {
	msg := encrypt("this is cool if it really works", 2)
	res := decrypt(msg, 2)
	fmt.Println(msg)
	fmt.Println(res)
}