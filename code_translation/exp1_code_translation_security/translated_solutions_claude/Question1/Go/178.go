package main

import (
	"fmt"
	"strings"
)

func encrypt(str string, key int) []string {
	alpha := []string{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"}
	var newString []string
	
	for _, c := range str {
		if string(c) == " " {
			newString = append(newString, " ")
			continue
		}
		
		alphaIndex := indexOf(alpha, string(c))
		fmt.Println(alphaIndex)
		
		if alphaIndex+key > len(alpha)-1 {
			alphaIndex = key - (len(alpha) - 1 - alphaIndex)
		}
		newString = append(newString, alpha[alphaIndex+key])
	}
	
	return newString
}

func decrypt(str []string, key int) []string {
	alpha := []string{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"}
	var newString []string
	
	for _, c := range str {
		if c == " " {
			continue
		}
		
		alphaIndex := indexOf(alpha, c)
		newString = append(newString, alpha[alphaIndex-key])
	}
	
	return newString
}

// Helper function to find index of string in slice (equivalent to Python's list.index())
func indexOf(slice []string, item string) int {
	for i, v := range slice {
		if v == item {
			return i
		}
	}
	return -1 // Not found
}

func main() {
	encrypted := encrypt("hello worlz", 3)
	fmt.Println(encrypted)
	
	decrypted := decrypt(encrypted, 3)
	fmt.Println(decrypted)
}