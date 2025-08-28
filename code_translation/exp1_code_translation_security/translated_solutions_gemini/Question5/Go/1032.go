package main

import (
	"fmt"
	"strings"
)

// intToString translates the C function `int_to_string`.
// It converts a non-negative integer to its string representation.
// Note: The original C code had a bug for num = 0 and did not handle negative numbers.
// This translation handles 0 correctly and is designed for non-negative integers,
// matching the behavior of the original C code's main function.
func intToString(num int) string {
	if num == 0 {
		return "0"
	}

	// Calculate the number of digits to determine the buffer size.
	length := 0
	temp := num
	for temp > 0 {
		temp /= 10
		length++
	}

	// Create a byte slice (similar to a char array) of the exact size.
	buffer := make([]byte, length)

	// Fill the buffer from right to left, just like in the C code.
	i := length - 1
	for num > 0 {
		buffer[i] = byte(num%10) + '0'
		num /= 10
		i--
	}

	// Convert the byte slice to a string.
	return string(buffer)
}

// intToStringWithCommas translates the C function `int_to_string_with_commas`.
// It takes an integer, converts it to a string, and adds commas as thousands separators.
// This Go implementation achieves the same result as the C code but uses a more
// idiomatic and efficient approach with a strings.Builder, which is standard practice in Go
// for building strings.
func intToStringWithCommas(num int) string {
	// First, convert the integer to a string without commas, using our translated function.
	s := intToString(num)
	n := len(s)

	// If the number is short enough, no commas are needed.
	if n <= 3 {
		return s
	}

	// Calculate how many commas are needed to pre-allocate memory for the builder.
	commaCount := (n - 1) / 3

	// Use a strings.Builder for efficient string construction.
	var result strings.Builder
	result.Grow(n + commaCount)

	// The number of digits in the first group (before the first comma).
	// This is a cleaner way to implement the complex loop logic from the C code.
	firstGroupLen := n % 3
	if firstGroupLen == 0 {
		firstGroupLen = 3
	}

	// Write the first group of digits.
	result.WriteString(s[:firstGroupLen])

	// Iterate over the remaining digits, adding a comma and a group of 3.
	for i := firstGroupLen; i < n; i += 3 {
		result.WriteByte(',')
		result.WriteString(s[i : i+3])
	}

	return result.String()
}

func main() {
	num := 7654321
	str := intToStringWithCommas(num)
	fmt.Println(str)
	// In Go, memory is managed automatically by the garbage collector.
	// There is no need to manually `free` the memory allocated for the string,
	// as was required in the C code.
}