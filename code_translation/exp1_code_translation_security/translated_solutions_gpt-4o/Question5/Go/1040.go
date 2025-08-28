package main

import (
	"fmt"
	"strconv"
)

// Function to calculate the number of digits in an integer
func numDigits(n int) int {
	digits := 0
	for n != 0 {
		n /= 10
		digits++
	}
	return digits
}

// Function to convert an integer to a string with commas
func intToStringWithCommas(value int) string {
	isNegative := value < 0
	if isNegative {
		value *= -1
	}

	digits := numDigits(value)
	numCommas := digits / 3
	if digits%3 == 0 {
		numCommas--
	}

	// Convert the integer to a string
	s := strconv.Itoa(value)
	size := digits + numCommas
	dest := make([]byte, size)

	index := size - 1
	for i := len(s) - 1; i >= 0; i-- {
		dest[index] = s[i]
		index--
		if (len(s)-i)%3 == 0 && i != 0 {
			dest[index] = ','
			index--
		}
	}

	// Convert the byte slice to a string
	result := string(dest)
	if isNegative {
		result = "-" + result
	}

	fmt.Println(result)
	return result
}

func main() {
	intToStringWithCommas(7000000)
}