package main

import (
	"fmt"
	"strconv" // Provides integer to string conversion
)

// convert takes an integer and returns its string representation.
// In Go, strings are managed automatically, so there is no need for manual
// memory allocation and deallocation like in C.
func convert(num int) string {
	// The comment from the C code is preserved for context.
	// Did not add comma because it's too complicated

	// strconv.Itoa (Integer to ASCII) is the standard way to convert an int to a string.
	return strconv.Itoa(num)
}

func main() {
	ans := convert(700000)
	fmt.Println(ans)
	// There is no need to call free() in Go. Memory is managed
	// automatically by the garbage collector.
}