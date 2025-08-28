package main

import (
	"fmt"
)

// intToString translates the C function's logic to convert an integer to a 
// string with thousands separators. It is written in an idiomatic Go style,
// using a byte slice for efficient string building.
//
// This translation corrects bugs from the original C code:
// 1. It correctly handles the input `0`.
// 2. It correctly handles negative numbers by working with their absolute value
//    and prepending a '-' sign at the end.
func intToString(num int) string {
	// Handle the edge case for 0, which the original C code handled incorrectly.
	if num == 0 {
		return "0"
	}

	var isNegative bool
	// If the number is negative, note it and work with the absolute value.
	if num < 0 {
		isNegative = true
		num = -num
	}

	// A byte slice is used to build the string in reverse. This is efficient
	// and analogous to building a string backwards in a char array in C.
	var result []byte
	count := 0

	for num > 0 {
		// After every 3 digits, append a comma.
		if count > 0 && count%3 == 0 {
			result = append(result, ',')
		}
		// Convert the last digit to its character representation and append it.
		result = append(result, byte(num%10)+'0')
		num /= 10
		count++
	}

	// If the original number was negative, append the '-' sign.
	if isNegative {
		result = append(result, '-')
	}

	// Reverse the byte slice to get the final string in the correct order.
	for i, j := 0, len(result)-1; i < j; i, j = i+1, j-1 {
		result[i], result[j] = result[j], result[i]
	}

	// Convert the byte slice to a string and return it.
	return string(result)
}

func main() {
	// In Go, variables are often declared and initialized in one step.
	num := -10099870
	
	// Call the translated function.
	str := intToString(num)

	// Print the result. Go's garbage collector handles memory management,
	// so there is no need for a `free()` call.
	fmt.Println(str)
}