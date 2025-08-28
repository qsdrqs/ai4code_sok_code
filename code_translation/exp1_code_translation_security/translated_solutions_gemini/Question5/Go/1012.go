package main

import (
	"fmt"
	"strconv"
	"strings"
)

// The 'insert' helper function from the C code was a non-functional stub.
// In Go, strings are immutable, so we cannot "insert" characters into them.
// The idiomatic approach is to build a new string from its parts.
// We will do this inside the formatWithCommas function for efficiency.

// stringRepre in the C code was intended to format a number with thousands
// separators (e.g., 1234567 -> "1,234,567").
// This Go function, formatWithCommas, correctly implements that intended logic.
func formatWithCommas(num int) string {
	// Convert the integer to its string representation.
	s := strconv.Itoa(num)

	// Handle negative numbers by processing the absolute value and adding the sign back later.
	isNegative := false
	if s[0] == '-' {
		isNegative = true
		s = s[1:] // Use the string without the sign for formatting.
	}

	n := len(s)
	if n <= 3 {
		// If the number is 3 digits or less, no commas are needed.
		// We still need to return the original number as a string.
		return strconv.Itoa(num)
	}

	// Use a strings.Builder for efficient string construction. It's much
	// faster than repeated string concatenation.
	var builder strings.Builder

	// Determine the length of the first group of digits (before the first comma).
	// For 12345, n=5, n%3=2. The first group is "12".
	// For 123456, n=6, n%3=0. The first group should be "123", so we adjust.
	firstGroupLen := n % 3
	if firstGroupLen == 0 {
		firstGroupLen = 3
	}

	// Add the negative sign back to the builder if the number was negative.
	if isNegative {
		builder.WriteByte('-')
	}

	// Write the first group of digits.
	builder.WriteString(s[:firstGroupLen])

	// Loop through the rest of the string, adding a comma and then a 3-digit group.
	for i := firstGroupLen; i < n; i += 3 {
		builder.WriteByte(',')
		builder.WriteString(s[i : i+3])
	}

	return builder.String()
}

// The main function in the C code was non-standard and would not compile.
// A standard Go main function does not return any values. Its purpose is to
// execute the program's logic.
//
// The C code intended to format `argc` (the argument count). To demonstrate the
// function's capability more clearly, we will use a larger, more illustrative number.
func main() {
	numberToFormat := 1234567890
	formattedString := formatWithCommas(numberToFormat)

	// Print the original number and its formatted version.
	fmt.Printf("Original number: %d\n", numberToFormat)
	fmt.Printf("Formatted string: %s\n", formattedString)

	// Example with a negative number
	negativeNumber := -9876543
	formattedNegative := formatWithCommas(negativeNumber)
	fmt.Printf("\nOriginal number: %d\n", negativeNumber)
	fmt.Printf("Formatted string: %s\n", formattedNegative)
}