package main

import (
	"fmt"
)

// power replicates the simple integer-power helper that existed in the C code.
func power(base, exponent int) int {
	result := 1
	for exponent > 0 {
		result *= base
		exponent--
	}
	return result
}

// numberToString converts an int to a string and adds commas every three digits.
// It follows the exact approach used in the original C version.
func numberToString(num int) string {
	// Special-case zero so we don't enter the digit-count loop with temp == 0.
	if num == 0 {
		return "0"
	}

	negative := false
	if num < 0 {
		negative = true
		num = -num // work with the absolute value from now on
	}

	// Determine how many digits are in the absolute number.
	temp := num
	numSize := 0
	for temp > 0 {
		temp /= 10
		numSize++
	}

	// Number of commas to insert.
	commaCount := (numSize - 1) / 3

	// Pre-allocate enough space: digits + commas + optional minus sign.
	total := numSize + commaCount
	if negative {
		total++
	}
	buf := make([]byte, 0, total)

	// Add minus sign if necessary.
	if negative {
		buf = append(buf, '-')
	}

	// Build the string from most-significant digit to least-significant,
	// inserting commas every three digits (except at the end).
	for j := numSize - 1; j >= 0; j-- {
		digit := (num / power(10, j)) + 0 // integer division to isolate the digit
		buf = append(buf, byte(digit)+'0')
		num %= power(10, j) // discard the digit we just emitted

		if j%3 == 0 && j != 0 { // place a comma after every three digits
			buf = append(buf, ',')
		}
	}

	return string(buf)
}

func main() {
	num := -7000000
	fmt.Println(numberToString(num)) // Output: -7,000,000
}