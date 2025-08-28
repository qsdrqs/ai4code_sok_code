package main

import (
	"fmt"
	"strconv" // Provides string conversion functions
)

// stringRep converts an integer to its string representation.
// In Go, we return a simple 'string' type. The language handles all memory management.
func stringRep(num int) string {
	// strconv.Itoa (Integer to ASCII) is the standard, efficient way to convert an int to a string.
	return strconv.Itoa(num)
}

func main() {
	leet := 1337
	leetStr := stringRep(leet)
	fmt.Printf("%s", leetStr)
	// No need to free memory like in C. Go's garbage collector handles it automatically.
}