package main

import (
	"fmt"
	"strconv"
	"strings"
)

func intToString(a int) string {
	// Convert the integer to a string
	str := strconv.Itoa(a)

	// Check if the number is negative
	isNegative := false
	if str[0] == '-' {
		isNegative = true
		str = str[1:] // Remove the negative sign for processing
	}

	// Reverse the string to process from least significant digit
	reversed := reverseString(str)

	// Insert commas every three digits
	var result strings.Builder
	for i, char := range reversed {
		if i > 0 && i%3 == 0 {
			result.WriteByte(',')
		}
		result.WriteByte(byte(char))
	}

	// Reverse the result to get the final formatted string
	finalStr := reverseString(result.String())

	// Add the negative sign back if necessary
	if isNegative {
		finalStr = "-" + finalStr
	}

	return finalStr
}

func reverseString(s string) string {
	runes := []rune(s)
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	return string(runes)
}

func main() {
	fmt.Println(intToString(7000000))
	fmt.Println(intToString(700000))
	fmt.Println(intToString(70000))
	fmt.Println(intToString(7000))
	fmt.Println(intToString(700))
	fmt.Println(intToString(70))
	fmt.Println(intToString(7))
	fmt.Println(intToString(0))
	fmt.Println(intToString(-7000000))
	fmt.Println(intToString(-700000))
	fmt.Println(intToString(-70000))
	fmt.Println(intToString(-7000))
	fmt.Println(intToString(-700))
	fmt.Println(intToString(-70))
	fmt.Println(intToString(-7))
	fmt.Println(intToString(int(^uint(0) >> 1))) // INT_MAX
	fmt.Println(intToString(-int(^uint(0)>>1) - 1)) // INT_MIN
}