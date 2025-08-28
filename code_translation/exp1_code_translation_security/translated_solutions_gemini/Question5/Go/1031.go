package main

import (
	"fmt"
	"strconv"
)

// intToString converts an integer to a string, prints it to the console,
// and returns the resulting string.
func intToString(input int) string {
	// In Go, the idiomatic way to convert an integer to a string
	// is using the `Itoa` (Integer to ASCII) function from the `strconv` package.
	s := strconv.Itoa(input)

	// `fmt.Printf` is the Go equivalent of C's `printf`.
	// A newline character `\n` is added for cleaner terminal output.
	fmt.Printf("you have entered: %s\n", s)

	// The original C code returned 0 (a NULL pointer), which is not useful.
	// A proper Go function should return the value it generated.
	return s
}

func main() {
	// We call the function, but since we don't need the returned string
	// in this main function, we don't assign it to a variable.
	intToString(7000000)
}