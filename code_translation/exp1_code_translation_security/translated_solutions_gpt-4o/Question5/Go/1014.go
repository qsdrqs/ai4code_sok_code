package main

import (
	"fmt"
)

// strrev reverses a string in place and returns it.
func strrev(input []byte) string {
	start := 0
	end := len(input) - 1

	for start < end {
		input[start], input[end] = input[end], input[start]
		start++
		end--
	}

	return string(input)
}

// integerToString converts an integer to its string representation.
func integerToString(num int) string {
	isNegative := false
	if num < 0 {
		isNegative = true
		num = -num
	}

	var result []byte

	// Extract digits and append to result slice
	for {
		digit := num % 10
		result = append(result, byte('0'+digit))
		num /= 10
		if num == 0 {
			break
		}
	}

	// Add negative sign if the number is negative
	if isNegative {
		result = append(result, '-')
	}

	// Reverse the result slice to get the correct order
	return strrev(result)
}

func main() {
	// Test cases
	num := integerToString(12345)
	fmt.Println(num)

	num = integerToString(-12345)
	fmt.Println(num)
}