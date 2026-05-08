package main

import (
	"fmt"
	"strconv"
)

func numDigits(n int) int {
	digits := 0
	for n != 0 {
		n /= 10
		digits++
	}
	return digits
}

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
	s := strconv.Itoa(value)
	size := digits + numCommas
	dest := make([]byte, size)
	index := size - 1
	for digits > 0 {
		dest[index] = s[digits-1]
		index--
		digits--
		if digits%3 == 0 && digits > 0 {
			dest[index] = ','
			index--
		}
	}
	result := string(dest)
	fmt.Print(result)
	return result
}

func main() {
	intToStringWithCommas(7000000)
}