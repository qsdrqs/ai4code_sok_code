package main

import (
	"fmt"
)

// intToString converts an integer to a string, adding commas as thousands separators.
// This is the Go equivalent of the C function `int_to_string`.
func intToString(num int) string {
	// Handle the edge case for 0, which the C code did not.
	if num == 0 {
		return "0"
	}

	// Use a byte slice to build the string in reverse. This is more efficient
	// in Go than repeated string concatenation.
	var result []byte
	isNegative := false

	// Handle negative numbers, which the C code did not.
	if num < 0 {
		isNegative = true
		num = -num // Work with the absolute value.
	}

	digitCount := 0
	for num > 0 {
		// Add a comma after every third digit, but not at the very end of the number.
		if digitCount == 3 {
			result = append(result, ',')
			digitCount = 0
		}

		// Get the last digit and append its character representation.
		// '0' is the ASCII value for the character '0'.
		result = append(result, byte(num%10+'0'))
		num /= 10
		digitCount++
	}

	// If the original number was negative, add the '-' sign.
	if isNegative {
		result = append(result, '-')
	}

	// The string was built backwards (e.g., 7,000,000 -> "000,000,7-"),
	// so we need to reverse it to get the correct order.
	for i, j := 0, len(result)-1; i < j; i, j = i+1, j-1 {
		result[i], result[j] = result[j], result[i]
	}

	// Convert the byte slice to a string and return it.
	return string(result)
}

// The main function demonstrates the usage of intToString.
func main() {
	// The original C code had a bug: printf(*int_to_string(7000000));
	// This translates the *intended* behavior: printf("%s\n", int_to_string(7000000));
	// Go's garbage collector handles memory, so no manual free is needed.
	fmt.Println(intToString(7000000))

	// Additional test cases to show it works correctly
	fmt.Println(intToString(12345))
	fmt.Println(intToString(987654321))
	fmt.Println(intToString(-123456))
	fmt.Println(intToString(0))
	fmt.Println(intToString(100))
}