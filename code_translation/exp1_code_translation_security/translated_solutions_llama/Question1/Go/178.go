package main

import (
	"fmt"
)

// Function to encrypt a string using Caesar cipher
func encrypt(str string, key int) []string {
	alpha := []string{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"}
	var newString []string

	for _, c := range str {
		char := string(c)
		if char == " " {
			newString = append(newString, " ")
			continue
		}

		alphaIndex := -1
		for i, letter := range alpha {
			if letter == char {
				alphaIndex = i
				break
			}
		}

		if alphaIndex == -1 {
			fmt.Println("Invalid character:", char)
			continue
		}

		newIndex := (alphaIndex + key) % 26
		if newIndex < 0 {
			newIndex += 26
		}

		newString = append(newString, alpha[newIndex])
	}

	return newString
}

// Function to decrypt a string using Caesar cipher
func decrypt(str []string, key int) []string {
	alpha := []string{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"}
	var newString []string

	for _, c := range str {
		if c == " " {
			newString = append(newString, " ")
			continue
		}

		alphaIndex := -1
		for i, letter := range alpha {
			if letter == c {
				alphaIndex = i
				break
			}
		}

		if alphaIndex == -1 {
			fmt.Println("Invalid character:", c)
			continue
		}

		newIndex := (alphaIndex - key + 26) % 26
		newString = append(newString, alpha[newIndex])
	}

	return newString
}

func main() {
	encrypted := encrypt("hello worlz", 3)
	fmt.Println(encrypted)

	decrypted := decrypt(encrypted, 3)
	fmt.Println(decrypted)
}