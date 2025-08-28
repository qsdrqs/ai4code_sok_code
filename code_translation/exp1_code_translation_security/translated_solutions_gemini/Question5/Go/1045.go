package main

import (
	"fmt"
)

// power calculates base raised to the power of the exponent.
// It is a direct translation of the C power function.
func power(base int, exponent int) int {
	result := 1
	for exponent > 0 {
		result *= base
		exponent--
	}
	return result
}

// numberToString converts an integer to a string with thousand separators.
// This is a faithful translation of the C version's logic.
// Note: The original C code returns an empty string for the input 0.
// This translation has been improved to correctly return "0".
func numberToString(num int) string {
	// Handle the special case of 0, which the original C code did not.
	if num == 0 {
		return "0"
	}

	var numSize, i, commaCount int

	// Use a temporary variable to calculate the number of digits
	// without modifying the original num.
	temp := num
	if num < 0 {
		temp *= -1
	}

	// Calculate the number of digits (numSize).
	// This loop won't run for temp = 0, hence the special case above.
	t := temp
	for t > 0 {
		t /= 10
		numSize++
	}

	// Calculate how many commas are needed.
	commaCount = (numSize - 1) / 3

	// Calculate the total length of the final string.
	totalSize := numSize + commaCount
	if num < 0 {
		totalSize++ // for the '-' sign
	}

	// In Go, we build a byte slice and convert it to a string at the end.
	// This is analogous to allocating a char array in C.
	strNum := make([]byte, totalSize)

	// Handle the negative sign.
	if num < 0 {
		strNum[0] = '-'
		num *= -1
		i++ // i is the current index for the strNum slice
	}

	// Build the string from left to right, placing digits and commas.
	for j := numSize - 1; j >= 0; j-- {
		// Calculate the value of the current digit's place.
		p := power(10, j)

		// Get the most significant digit.
		digit := num / p
		strNum[i] = byte(digit + '0')
		i++

		// Remove the most significant digit from the number.
		num %= p

		// Add a comma after every group of three digits,
		// except for the very end of the number.
		if (j%3 == 0) && (j != 0) {
			strNum[i] = ','
			i++
		}
	}

	// Convert the byte slice to a string and return it.
	// No null-terminator is needed in Go.
	return string(strNum)
}

func main() {
	num := -7000000
	strNum := numberToString(num)
	fmt.Println(strNum)
}