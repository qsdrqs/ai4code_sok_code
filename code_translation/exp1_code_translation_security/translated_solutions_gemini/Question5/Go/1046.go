package main

import (
	"fmt"
	"strconv"
)

// convert translates an integer to a string, prints the full string to the console,
// and then returns the first character of that string as a rune.
// This mimics the intended behavior of the original C function.
func convert(num int) rune {
	// In Go, the idiomatic way to convert an integer to a string is using
	// the strconv.Itoa function (Integer to ASCII).
	s := strconv.Itoa(num)

	// This line replicates the `printf("%s", buf);` from the C function.
	// It prints the full string representation of the number.
	fmt.Print(s)

	// The C function's signature `char convert(...)` implies it should return a
	// single character. We return the first character of the string.
	// In Go, characters are represented by the `rune` type.
	// We check if the string is not empty to prevent a panic.
	if len(s) > 0 {
		// s[0] gets the first byte, and we convert it to a rune.
		return rune(s[0])
	}

	// Return a zero value for rune if the string is empty.
	return 0
}

func main() {
	// This replicates the C code's `printf("%c", convert(100));`
	// 1. `convert(100)` is called. It will print "100" and return the rune for '1'.
	// 2. `fmt.Printf` then prints the returned character.
	// The final output will be "1001".
	charResult := convert(100)
	fmt.Printf("%c", charResult)
}