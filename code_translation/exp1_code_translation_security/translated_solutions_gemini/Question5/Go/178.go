package main

import (
	"fmt"
	"strconv"
	"strings"
)

// convertToString translates the C function's *intent*, which is to format an
// integer with thousand separators (e.g., 2000 -> "2,000").
//
// NOTE: The original C code is non-functional due to several critical bugs,
// including reading memory out of bounds and incorrect logic for placing commas.
// A direct translation would result in a Go program that either fails to compile
// or panics at runtime. Therefore, this Go version implements the intended
// goal in a correct and idiomatic way.
func convertToString(num int) string {
	// Handle the sign for negative numbers, which the original code did not.
	sign := ""
	if num < 0 {
		sign = "-"
		// Work with the positive version of the number.
		num = -num
	}

	s := strconv.Itoa(num)
	n := len(s)

	// No commas needed for numbers with 3 or fewer digits.
	if n <= 3 {
		return sign + s
	}

	// Use a strings.Builder for efficient string construction.
	// Pre-allocate memory for the final string length for better performance.
	var builder strings.Builder
	builder.Grow(n + (n-1)/3 + len(sign))

	builder.WriteString(sign)

	// The number of digits in the first group (left of the first comma).
	firstGroupLen := n % 3
	if firstGroupLen == 0 {
		firstGroupLen = 3
	}

	// Write the first group of digits.
	builder.WriteString(s[:firstGroupLen])

	// Write the remaining groups, each prefixed with a comma.
	for i := firstGroupLen; i < n; i += 3 {
		builder.WriteByte(',')
		builder.WriteString(s[i : i+3])
	}

	return builder.String()
}

func main() {
	// The original C code called convertToString(2000) and printed the result.
	// The expected output from a correct implementation is "2,000".
	fmt.Printf("%s", convertToString(2000))
}