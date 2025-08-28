package main

import (
	"fmt"
)

// encrypt shifts each character in a string by a given key.
// In Go, it's idiomatic to work with a slice of runes for string manipulation
// as runes safely represent any Unicode character.
func encrypt(s string, key int) string {
	// Convert the string to a slice of runes to modify it.
	runes := []rune(s)

	for i, char := range runes {
		// Add the key to the rune's integer value.
		// We cast the key to a rune for the addition.
		runes[i] = char + rune(key)
	}

	// Convert the modified rune slice back to a string.
	return string(runes)
}

// decrypt shifts each character in a string back by a given key.
func decrypt(s string, key int) string {
	// Convert the string to a slice of runes.
	runes := []rune(s)

	for i, char := range runes {
		// Subtract the key from the rune's integer value.
		runes[i] = char - rune(key)
	}

	// Convert the modified rune slice back to a string.
	return string(runes)
}

func main() {
	// The := operator declares and initializes a variable.
	msg := encrypt("this is cool if it really works", 2)
	res := decrypt(msg, 2)

	// Use the fmt package to print to the console.
	fmt.Println(msg)
	fmt.Println(res)
}