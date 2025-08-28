package main

import (
	"fmt"
	"strconv"
)

func main() {
	// Define the integer you want to convert.
	// Go uses the `:=` operator for short variable declaration and initialization.
	number := -12

	// Convert the integer to a string.
	// The `strconv.Itoa` function (Integer to ASCII) is the standard way to do this.
	// Go strings are a built-in type, so we don't need to pre-allocate a buffer.
	str := strconv.Itoa(number)

	// Print the string.
	// `fmt.Println` prints its arguments followed by a newline.
	fmt.Println(str)
}