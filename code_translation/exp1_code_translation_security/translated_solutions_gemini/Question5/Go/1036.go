package main

import (
	"fmt"
	"strconv"
	"strings"
)

// The original C code's num_digits function was buggy and not needed for an
// idiomatic Go solution, so it has not been translated.

/**
 * formatWithCommas translates the *intent* of the C function `return_string`.
 * The original C code is critically flawed and does not work as intended.
 * This Go version correctly implements the apparent goal: formatting an integer
 * with thousands separators (e.g., 7000000 -> "7,000,000").
 */
func formatWithCommas(num int) string {
	// Convert the integer to a string using the standard library.
	s := strconv.Itoa(num)

	// If the number is negative, we handle the sign separately.
	var sign string
	if num < 0 {
		sign = "-"
		s = s[1:] // Use the part of the string after the sign
	}

	n := len(s)
	if n <= 3 {
		// No commas needed for numbers with 3 or fewer digits.
		return sign + s
	}

	// A strings.Builder is an efficient way to build the result string.
	var builder strings.Builder

	// The number of digits in the first group (before the first comma).
	// For 7,000,000 (n=7), this is 7 % 3 = 1.
	// For 100,000 (n=6), this is 6 % 3 = 0, so we use 3.
	firstGroupLen := n % 3
	if firstGroupLen == 0 {
		firstGroupLen = 3
	}

	// Write the sign and the first group of digits.
	builder.WriteString(sign)
	builder.WriteString(s[:firstGroupLen])

	// Loop through the rest of the string, adding a comma and then 3 digits.
	for i := firstGroupLen; i < n; i += 3 {
		builder.WriteByte(',')
		builder.WriteString(s[i : i+3])
	}

	return builder.String()
}

func main() {
	num := 7000000
	// In Go, strings are returned by value and are managed safely.
	str := formatWithCommas(num)
	// The original C code prints the final string to the console.
	fmt.Printf("%s", str)
}