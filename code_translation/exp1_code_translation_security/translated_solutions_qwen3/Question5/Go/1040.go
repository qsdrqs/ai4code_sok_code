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
		value = -value
	}
	digits := numDigits(value)
	if digits == 0 {
		return "0"
	}
	numCommas := digits / 3
	if digits%3 == 0 {
		numCommas -= 1
	}
	s := strconv.Itoa(value)
	size := digits + numCommas
	dest := make([]byte, size)
	index := size - 1
	digitsTmp := digits
	for digitsTmp > 0 {
		dest[index] = s[digitsTmp-1]
		index--
		digitsTmp--
		if digitsTmp > 0 && digitsTmp%3 == 0 {
			dest[index] = ','
			index--
		}
	}
	if isNegative {
		return "-" + string(dest)
	}
	return string(dest)
}

func main() {
	fmt.Println(intToStringWithCommas(7000000))
}