package main

import (
	"fmt"
	"strconv"
	"strings"
)

func numToStr(num int) string {
	// Convert the number to a string
	str := strconv.Itoa(num)

	// Handle the case where the number is 0
	if num == 0 {
		return str
	}

	// Determine if the number is negative
	isNegative := num < 0

	// Remove the negative sign for easier processing
	if isNegative {
		str = str[1:]
	}

	// Insert commas
	var result strings.Builder
	length := len(str)
	digitsMoved := 0

	for i := length - 1; i >= 0; i-- {
		result.WriteByte(str[i])
		digitsMoved++
		if digitsMoved == 3 && i != 0 {
			result.WriteByte(',')
			digitsMoved = 0
		}
	}

	// Reverse the result since we built it backwards
	finalStr := result.String()
	runes := []rune(finalStr)
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}

	// Add the negative sign back if necessary
	if isNegative {
		return "-" + string(runes)
	}
	return string(runes)
}

func main() {
	num := 7042
	str := numToStr(num)
	fmt.Printf("Output: %s\n", str)
}