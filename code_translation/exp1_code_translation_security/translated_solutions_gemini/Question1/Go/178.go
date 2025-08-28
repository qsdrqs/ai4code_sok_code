package main

import (
	"fmt"
)

// findIndex is a helper function that mimics Python's `list.index()` method.
// It finds the first index of a given value in a slice of strings.
// It returns -1 if the value is not found.
func findIndex(slice []string, value string) int {
	for i, item := range slice {
		if item == value {
			return i
		}
	}
	return -1
}

// encrypt function translates the Python encrypt function.
// It takes a string and an integer key and returns a slice of strings.
func encrypt(str string, key int) []string {
	alpha := []string{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"}
	var newString []string

	for _, c := range str {
		charStr := string(c)
		if charStr == " " {
			newString = append(newString, " ")
			continue
		}

		alphaIndex := findIndex(alpha, charStr)
		// This print statement is preserved from the original Python code.
		fmt.Println(alphaIndex)

		// The wrap-around logic is translated directly from the Python code.
		if alphaIndex+key > len(alpha)-1 {
			alphaIndex = key - (len(alpha) - 1 - alphaIndex)
		}

		// The final character is determined by this operation, same as in Python.
		newString = append(newString, alpha[alphaIndex+key])
	}

	return newString
}

// decrypt function translates the Python decrypt function.
// It takes a slice of strings and an integer key, returning the decrypted slice of strings.
func decrypt(str []string, key int) []string {
	alpha := []string{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"}
	var newString []string

	for _, c := range str {
		if c == " " {
			// This replicates the behavior of the Python code, which drops spaces during decryption.
			continue
		}
		alphaIndex := findIndex(alpha, c)
		newString = append(newString, alpha[alphaIndex-key])
	}

	return newString
}

func main() {
	// The main execution flow is identical to the Python script.
	encrypted := encrypt("hello worlz", 3)
	fmt.Println(encrypted)

	decrypted := decrypt(encrypted, 3)
	fmt.Println(decrypted)
}