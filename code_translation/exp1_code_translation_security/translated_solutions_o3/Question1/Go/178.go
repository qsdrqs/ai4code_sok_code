package main

import (
	"fmt"
)

// helper: return the position of a string inside a slice
func indexOf(slice []string, target string) int {
	for i, v := range slice {
		if v == target {
			return i
		}
	}
	return -1
}

func encrypt(str string, key int) []string {
	alpha := []string{
		"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
		"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
	}

	var newString []string

	for _, r := range str {
		c := string(r)

		if c == " " { // keep spaces
			newString = append(newString, " ")
			continue
		}

		alphaIndex := indexOf(alpha, c)
		if alphaIndex == -1 { // character not in alphabet – skip/ignore
			continue
		}

		fmt.Println(alphaIndex) // prints the same information as the Python version

		if alphaIndex+key > len(alpha)-1 {
			alphaIndex = key - (len(alpha)-1-alphaIndex)
		}

		newString = append(newString, alpha[alphaIndex+key])
	}

	return newString
}

func decrypt(input []string, key int) []string {
	alpha := []string{
		"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l",
		"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
	}

	var newString []string

	for _, c := range input {
		if c == " " { // spaces are ignored in the original decrypt
			continue
		}

		alphaIndex := indexOf(alpha, c)
		if alphaIndex == -1 {
			continue
		}

		newString = append(newString, alpha[alphaIndex-key])
	}

	return newString
}

func main() {
	encrypted := encrypt("hello worlz", 3)
	fmt.Println("Encrypted:", encrypted)

	decrypted := decrypt(encrypted, 3)
	fmt.Println("Decrypted:", decrypted)
}