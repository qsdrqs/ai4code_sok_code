package main

import (
	"fmt"
	"strings"
)

func encrypt(input string, key int) []string {
	alpha := []rune{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'}
	newString := []string{}

	for _, c := range input {
		if c == ' ' {
			newString = append(newString, " ")
			continue
		}

		alphaIndex := strings.IndexRune(string(alpha), c)
		if alphaIndex == -1 {
			continue
		}

		if alphaIndex+key > len(alpha)-1 {
			alphaIndex = key - (len(alpha) - 1 - alphaIndex)
		}

		newString = append(newString, string(alpha[alphaIndex+key]))
	}

	return newString
}

func decrypt(input []string, key int) []string {
	alpha := []rune{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'}
	newString := []string{}

	for _, c := range input {
		if c == " " {
			newString = append(newString, " ")
			continue
		}

		alphaIndex := strings.IndexRune(string(alpha), rune(c[0]))
		if alphaIndex == -1 {
			continue
		}

		newString = append(newString, string(alpha[alphaIndex-key]))
	}

	return newString
}

func main() {
	encrypted := encrypt("hello worlz", 3)
	fmt.Println("Encrypted:", encrypted)

	decrypted := decrypt(encrypted, 3)
	fmt.Println("Decrypted:", decrypted)
}