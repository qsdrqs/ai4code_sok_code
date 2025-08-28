package main

import (
	"fmt"
	"strconv"
	"strings"
)

// intToStringWithCommas formats an integer with commas as thousands separators.
// It correctly handles positive, negative, and zero values, producing a result
// that is safe to use and return, unlike the original C version.
func intToStringWithCommas(value int) string {
	// Handle the simple case of 0. The original C code was buggy for this case.
	if value == 0 {
		return "0"
	}

	// Handle negative numbers by remembering the sign and working with the absolute value.
	var isNegative bool
	if value < 0 {
		isNegative = true
		value = -value
	}

	// In Go, it's idiomatic to convert the number to a string to get its digits,
	// rather than creating a separate `num_digits` function.
	s := strconv.Itoa(value)
	n := len(s)

	// If the number has 3 or fewer digits, no commas are needed.
	if n <= 3 {
		if isNegative {
			return "-" + s
		}
		return s
	}

	// Use a strings.Builder for efficient string construction. This is preferable
	// to manual byte slice manipulation for performance and readability.
	var result strings.Builder

	// Calculate the number of digits in the first group (before the first comma).
	// For 1,234,567 (n=7), the first group length is 1 (7 % 3).
	// For 123,456 (n=6), the first group length is 3 (6 % 3 is 0, so we use 3).
	firstGroupLen := n % 3
	if firstGroupLen == 0 {
		firstGroupLen = 3
	}

	// Add the negative sign prefix if the original number was negative.
	if isNegative {
		result.WriteString("-")
	}

	// Write the first group of digits.
	result.WriteString(s[:firstGroupLen])

	// Loop through the remaining digits and write them in groups of three,
	// each preceded by a comma.
	for i := firstGroupLen; i < n; i += 3 {
		result.WriteByte(',')
		result.WriteString(s[i : i+3])
	}

	return result.String()
}

func main() {
	// The original C code called the function with 7,000,000.
	fmt.Println("Original test case:")
	fmt.Println(intToStringWithCommas(7000000)) // Expected: 7,000,000

	// Additional test cases to demonstrate the robustness of the Go implementation.
	fmt.Println("\nAdditional test cases:")
	fmt.Println(intToStringWithCommas(123))         // Expected: 123
	fmt.Println(intToStringWithCommas(1234))        // Expected: 1,234
	fmt.Println(intToStringWithCommas(123456))      // Expected: 123,456
	fmt.Println(intToStringWithCommas(-7000000))    // Expected: -7,000,000
	fmt.Println(intToStringWithCommas(-12345))      // Expected: -12,345
	fmt.Println(intToStringWithCommas(0))           // Expected: 0
	fmt.Println(intToStringWithCommas(-100))        // Expected: -100
	fmt.Println(intToStringWithCommas(999999999))   // Expected: 999,999,999
}