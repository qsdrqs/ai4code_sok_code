package main

import (
	"fmt"
	"strconv"
	"strings"
)

// convert takes an int64 value and returns a string representation
// with thousand separators (commas).
//
// This Go implementation correctly fulfills the intent of the original C code
// while being more robust, idiomatic to Go, and safer. It correctly handles
// edge cases and negative numbers, which the original C code's logic did not.
func convert(value int64) string {
	// In Go, we use strconv.FormatInt to convert a number to a string.
	// This replaces the snprintf call in C.
	s := strconv.FormatInt(value, 10)
	n := len(s)

	// The original C code used fixed-size buffers. Go's strings are dynamic,
	// so we don't need a BUFFER_SIZE constant. If the number is short enough
	// that it doesn't need commas, we can return it directly.
	if n <= 3 {
		return s
	}

	// The original C code's logic for placing commas was flawed for certain
	// negative numbers because it didn't account for the '-' sign. This
	// implementation separates the sign from the digits to apply the comma
	// logic correctly.
	isNegative := value < 0
	if isNegative {
		s = s[1:] // Work with the digits part of the string
		n--
	}

	// Calculate the number of digits in the first group (before the first comma).
	// For 12345, n=5, prefixLen=2. First group is "12".
	// For 123456, n=6, prefixLen=0 -> 3. First group is "123".
	prefixLen := n % 3
	if prefixLen == 0 {
		prefixLen = 3
	}

	// A strings.Builder is the idiomatic and efficient way to build strings in Go,
	// analogous to creating a character buffer in C.
	var builder strings.Builder
	// We can estimate the final size to pre-allocate memory and improve performance.
	builder.Grow(n + (n-1)/3 + 1) // digits + commas + sign

	if isNegative {
		builder.WriteByte('-')
	}

	// Write the first group of digits.
	builder.WriteString(s[:prefixLen])

	// Iterate over the rest of the string, adding a comma before each new group of 3 digits.
	for i := prefixLen; i < n; i += 3 {
		builder.WriteByte(',')
		builder.WriteString(s[i : i+3])
	}

	// In Go, we return the final string. The garbage collector handles memory management,
	// so there is no need for `free` as in C.
	return builder.String()
}

func main() {
	// The main function demonstrates the usage of the convert function,
	// similar to the C example.
	test := convert(1000)
	// fmt.Println is the Go equivalent of printf("%s\n", ...).
	fmt.Println(test)
	// There is no need to free the 'test' variable in Go.
}