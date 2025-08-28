package main

import (
	"fmt"
)

// e10 calculates 10 to the power of x.
// This is a direct translation of the C function.
func e10(x int) int {
	v := 1
	for i := 0; i < x; i++ {
		v *= 10
	}
	return v
}

// signum returns -1 for negative numbers, 1 for positive numbers, and 0 for zero.
// This is a direct translation of the C function.
func signum(x int) int {
	if x < 0 {
		return -1
	}
	if x == 0 {
		return 0
	}
	return 1
}

// abs returns the absolute value of an integer.
// This is a direct translation of the C function.
func abs(x int) int {
	// A more common implementation in Go would be:
	// if x < 0 { return -x }; return x
	// But this is a direct translation of the original's logic.
	return x * signum(x)
}

// intToStr converts an integer to a string, adding thousand separators.
// NOTE: The original C implementation had several bugs and relied on unsafe C
// features. This Go version is a clean, idiomatic implementation that correctly
// achieves the intended goal of the original C code.
func intToStr(n int) string {
	// Handle the zero case explicitly.
	if n == 0 {
		return "0"
	}

	// Handle negative numbers by recording the sign and working with the absolute value.
	var isNegative bool
	if n < 0 {
		isNegative = true
		n = -n
	}

	// Build the string's bytes in reverse order.
	var parts []byte
	for i := 0; n > 0; i++ {
		// Every 3 digits (after the first group), prepend a comma.
		if i > 0 && i%3 == 0 {
			parts = append(parts, ',')
		}
		// Append the character for the last digit.
		parts = append(parts, '0'+byte(n%10))
		// Remove the last digit from the number.
		n /= 10
	}

	// If the original number was negative, append the minus sign.
	if isNegative {
		parts = append(parts, '-')
	}

	// The byte slice is currently in reverse (e.g., "5,432,1-").
	// We need to reverse it to get the correct order.
	for i, j := 0, len(parts)-1; i < j; i, j = i+1, j-1 {
		parts[i], parts[j] = parts[j], parts[i]
	}

	return string(parts)
}

func main() {
	fmt.Println(intToStr(55))
	fmt.Println(intToStr(12345))
	fmt.Println(intToStr(-55))
	fmt.Println(intToStr(-123456789))
}