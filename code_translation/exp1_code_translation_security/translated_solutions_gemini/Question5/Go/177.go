package main

import (
	"fmt"
	"strconv"
	"strings"
)

// formatWithCommas translates an integer into a string with thousand separators.
// It is a corrected and idiomatic Go implementation of the C code's intent.
func formatWithCommas(num int) string {
	// Use strconv.Itoa for a safe and standard conversion from int to string.
	s := strconv.Itoa(num)

	// If the number is negative, we handle the sign separately.
	isNegative := false
	if s[0] == '-' {
		isNegative = true
		s = s[1:] // Work with the positive part of the number
	}

	n := len(s)
	// If the number is short, no commas are needed.
	if n <= 3 {
		// We still need to return the original number if it was negative.
		if isNegative {
			return "-" + s
		}
		return s
	}

	// Use strings.Builder for efficient string construction.
	var builder strings.Builder

	// The first group of digits might be 1, 2, or 3 characters long.
	firstGroupLen := n % 3
	if firstGroupLen == 0 {
		// If length is a multiple of 3 (e.g., 6 for "123456"), the first group has 3 digits.
		firstGroupLen = 3
	}

	// Write the first group of digits.
	builder.WriteString(s[:firstGroupLen])

	// Loop through the rest of the string, adding a comma and the next 3 digits.
	for i := firstGroupLen; i < n; i += 3 {
		builder.WriteByte(',')
		builder.WriteString(s[i : i+3])
	}

	// Prepend the negative sign if the original number was negative.
	if isNegative {
		return "-" + builder.String()
	}

	return builder.String()
}

// main function to demonstrate the usage of formatWithCommas.
func main() {
	fmt.Println(formatWithCommas(123))
	fmt.Println(formatWithCommas(1234))
	fmt.Println(formatWithCommas(123456))
	fmt.Println(formatWithCommas(1234567))
	fmt.Println(formatWithCommas(-54321))
	fmt.Println(formatWithCommas(-987654321))
	fmt.Println(formatWithCommas(0))
}