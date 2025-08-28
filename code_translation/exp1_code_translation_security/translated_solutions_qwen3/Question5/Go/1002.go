package main

import (
	"fmt"
	"strconv"
	"strings"
)

// convert takes an int64 value and returns a string representation
// of the number with commas inserted as thousand separators.
func convert(value int64) string {
	// Step 1: Convert the integer to a string
	s := strconv.FormatInt(value, 10)

	// Step 2: Use strings.Builder to efficiently build the result
	var b strings.Builder

	// Step 3: Iterate over each character in the string
	for i := 0; i < len(s); i++ {
		// Append the current digit
		b.WriteByte(s[i])

		// Calculate the distance to the end of the string
		dist := len(s) - i - 1

		// Insert a comma if the distance is a multiple of 3 and not zero
		if dist > 0 && dist%3 == 0 {
			b.WriteByte(',')
		}
	}

	// Step 4: Return the final formatted string
	return b.String()
}

// main is the entry point of the program
func main() {
	// Test the convert function with the value 1000
	test := convert(1000)

	// Print the result
	fmt.Println(test)
}