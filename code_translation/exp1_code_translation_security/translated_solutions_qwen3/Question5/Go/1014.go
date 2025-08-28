package main

import (
	"fmt"
)

// integerToString converts an integer to its string representation.
func integerToString(num int) string {
	var result []byte
	isNegative := false

	// Handle negative numbers
	if num < 0 {
		isNegative = true
		num = -num
	}

	// Process digits in reverse order
	for {
		digit := num % 10
		result = append(result, '0'+byte(digit))
		num /= 10
		if num == 0 {
			break
		}
	}

	// Append negative sign if needed
	if isNegative {
		result = append(result, '-')
	}

	// Reverse the result to get the correct order
	for i, j := 0, len(result)-1; i < j; i, j = i+1, j-1 {
		result[i], result[j] = result[j], result[i]
	}

	// Convert byte slice to string and return
	return string(result)
}

// main function to test the integerToString function
func main() {
	// Test with positive number
	num := integerToString(12345)
	fmt.Println(num) // Output: 12345

	// Test with negative number
	num = integerToString(-12345)
	fmt.Println(num) // Output: -12345

	// Test with zero
	num = integerToString(0)
	fmt.Println(num) // Output: 0
}