package main

import (
	"fmt"
	"strconv"
	"strings"
)

// numToStr converts an integer to a string and formats it with commas as
// thousands separators.
func numToStr(num int) string {
	// Handle the case of 0, which needs no formatting.
	if num == 0 {
		return "0"
	}

	// Handle negative numbers by storing the sign and working with the absolute value.
	sign := ""
	if num < 0 {
		sign = "-"
		num = -num // Make the number positive for processing.
	}

	// Convert the positive integer part to a string.
	s := strconv.Itoa(num)
	n := len(s)

	// If the number has 3 or fewer digits, no commas are needed.
	if n <= 3 {
		return sign + s
	}

	// Use a strings.Builder for efficient string construction, which is better
	// than repeated concatenation.
	var result strings.Builder

	// Determine the number of digits in the first group (before the first comma).
	// This can be 1, 2, or 3 digits.
	firstPartLen := n % 3
	if firstPartLen == 0 {
		firstPartLen = 3
	}

	// Write the sign (if any) and the first group of digits.
	result.WriteString(sign)
	result.WriteString(s[:firstPartLen])

	// Loop through the remaining digits, adding a comma and then the next
	// group of 3 digits in each iteration.
	for i := firstPartLen; i < n; i += 3 {
		result.WriteByte(',')
		result.WriteString(s[i : i+3])
	}

	return result.String()
}

func main() {
	num := 7042
	
	// In Go, it's idiomatic for functions to return new values rather than
	// modifying arguments via pointers (like the char* buffer in C).
	str := numToStr(num)
	
	// Print the final formatted string.
	fmt.Printf("Output: %s\n", str)
}