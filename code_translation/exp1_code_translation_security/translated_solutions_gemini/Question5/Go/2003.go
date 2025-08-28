package main

import (
	"fmt"
	"strconv"
	"strings"
)

// numToString converts an integer to a string, adding commas for thousands separators.
// This is a Go-idiomatic translation of the C code's intent. It achieves the
// same goal but uses standard library features for robustness and clarity,
// avoiding a bug present in the original C implementation's loop.
func numToString(num int) string {
	// Handle the simple case of zero, as in the C code.
	if num == 0 {
		return "0"
	}

	// Use a strings.Builder for efficient string construction.
	var sb strings.Builder

	// Handle negative numbers by writing the sign first and then using the absolute value.
	if num < 0 {
		sb.WriteRune('-')
		num = -num
	}

	// Convert the positive number to a base-10 string.
	s := strconv.Itoa(num)
	n := len(s)

	// Calculate the length of the first group of digits (from the left).
	// This will be 1, 2, or 3.
	firstGroupLen := n % 3
	if firstGroupLen == 0 {
		firstGroupLen = 3
	}

	// Write the first group of digits.
	sb.WriteString(s[:firstGroupLen])

	// Loop through the remaining digits, writing a comma then the next 3 digits.
	for i := firstGroupLen; i < n; i += 3 {
		sb.WriteRune(',')
		sb.WriteString(s[i : i+3])
	}

	return sb.String()
}

func main() {
	// The C code uses printf("%s", ...), which does not print a newline.
	// fmt.Print is the Go equivalent.
	fmt.Print(numToString(-5305000))
}