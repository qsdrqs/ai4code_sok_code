package main

import (
	"fmt"
	"strconv"
)

// numDigits calculates the number of digits in a given integer.
func numDigits(n int) int {
	digits := 0
	for n != 0 {
		n /= 10
		digits++
	}
	return digits
}

// intToStringWithCommas converts an integer to a string with commas as thousand separators.
func intToStringWithCommas(value int) string {
	isNegative := value < 0
	if isNegative {
		value *= -1
	}
	digits := numDigits(value)
	numCommas := digits / 3
	if digits%3 == 0 {
		numCommas -= 1
	}
	str := strconv.Itoa(value)
	result := ""
	for i := len(str) - 1; i >= 0; i-- {
		result = str[i] + result
		if (len(str)-i)%3 == 0 && i != 0 {
			result = "," + result
		}
	}
	if isNegative {
		result = "-" + result
	}
	fmt.Println(result)
	return result
}

func main() {
	intToStringWithCommas(7000000)
}