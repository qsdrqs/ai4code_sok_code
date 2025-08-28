package main

import (
	"fmt"
	"strconv"
)

// intToString converts an integer to a string with commas as thousand separators.
func intToString(x int) string {
	// Handle zero case
	if x == 0 {
		return "0"
	}

	// Convert the integer to a string
	s := strconv.Itoa(x)

	// Determine the number of commas needed
	n := len(s)
	commas := (n - 1) / 3

	// Create a result buffer with enough space for commas
	result := make([]byte, n+commas)
	ptr := len(result) - 1

	// Build the result from the end to the beginning
	for i := len(s) - 1; i >= 0; i-- {
		result[ptr] = s[i]
		ptr--

		// Insert a comma after every 3 digits (from the right)
		if (len(s)-i-1)%3 == 0 && i != 0 {
			result[ptr] = ','
			ptr--
		}
	}

	return string(result)
}

func main() {
	fmt.Println(intToString(100000)) // Output: 100,000
	fmt.Println(intToString(123456789)) // Output: 123,456,789
	fmt.Println(intToString(0)) // Output: 0
}