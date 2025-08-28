package main

import (
	"fmt"
	"strconv"
)

// numToString converts an integer to its string representation.
// In Go, the idiomatic way to do this is using the `strconv` package.
func numToString(num int) string {
	// Itoa (Integer to ASCII) is the most direct and efficient way.
	return strconv.Itoa(num)
}

func main() {
	// We use the fmt package (similar to C's stdio.h) to print to the console.
	// Printf works very similarly to C's printf.
	fmt.Printf("%s", numToString(7000000))
}