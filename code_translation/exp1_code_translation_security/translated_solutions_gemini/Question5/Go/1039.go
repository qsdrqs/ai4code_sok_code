package main

import (
	"fmt"
	"strconv" // Used for an idiomatic alternative to getLength
)

// getLength calculates the number of digits in a positive integer.
// Note: Like the original C code, this implementation returns 0 for 0 or negative numbers.
func getLength(num int) int {
	var length int // In Go, int variables are initialized to 0 by default
	numCopy := num
	for numCopy > 0 {
		numCopy /= 10 // Same as numCopy = numCopy / 10
		length++      // Same as length = length + 1
	}
	return length
}

// A more idiomatic Go way to get the length of a number (including negative sign).
// For example, getLengthIdiomatic(-123) would return 4.
func getLengthIdiomatic(num int) int {
	return len(strconv.Itoa(num))
}

// convertToString is intended to return a string.
// The original C code has several critical errors (see explanation below).
// This Go version fulfills the apparent intent of the C code, which is
// to return the string "test", while ignoring the input number.
func convertToString(num int) string {
	// The C code attempts to create and return the string "test".
	// In Go, strings are managed safely and can be returned directly.
	return "test"
}

func main() {
	num := 7000000
	fmt.Printf("%d", getLength(num))

	// The result of convertToString is printed directly.
	fmt.Print(convertToString(5))

	// A newline is added here for cleaner terminal output, as printf/print do not add one.
	fmt.Println()
}