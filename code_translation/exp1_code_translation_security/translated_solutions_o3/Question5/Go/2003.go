package main

import (
	"fmt"
)

const MAX_LEN = 16

// numToString mimics the original C function.
// It converts an integer to a string with commas as thousands
// separators and a leading minus sign for negative numbers.
func numToString(num int) string {
	// Special-case zero.
	if num == 0 {
		return "0"
	}

	// Handle the sign.
	isNeg := false
	if num < 0 {
		isNeg = true
		num = -num
	}

	// Count the digits.
	curNum := num
	totalDigits := 0
	for curNum > 0 {
		curNum /= 10
		totalDigits++
	}

	// How many commas will we need?
	numCommas := (totalDigits - 1) / 3
	strLen := totalDigits + numCommas
	if isNeg {
		strLen++
	}

	// Create a byte slice of the final length.
	str := make([]byte, strLen)

	// Fill the slice from right to left.
	curNum = num
	index := 0      // How many positions we’ve filled
	digits := 0     // Digits since the last comma
	for curNum > 0 {
		// Current digit
		str[strLen-index-1] = byte(curNum%10) + '0'
		curNum /= 10
		index++
		digits++

		// Insert comma every three digits (except at the very left)
		if digits%3 == 0 && index < strLen {
			str[strLen-index-1] = ','
			index++
		}
	}

	// Add the minus sign if needed.
	if isNeg {
		str[0] = '-'
	}

	return string(str)
}

func main() {
	fmt.Print(numToString(-5305000))
}