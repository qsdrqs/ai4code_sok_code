package main

import (
	"fmt"
	"strconv"
)

// reverseString reverses the input string
func reverseString(input string) string {
	runes := []rune(input)
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	return string(runes)
}

// integerToString converts an integer into a string
func integerToString(num int) string {
	var result string
	isNegative := false

	if num < 0 {
		isNegative = true
		num = -num
	}

	if num == 0 {
		return "0"
	}

	for num > 0 {
		digit := num % 10
		result = strconv.Itoa(digit) + result
		num /= 10
	}

	if isNegative {
		result = "-" + result
	}

	return result
}

func main() {
	num := integerToString(12345)
	fmt.Println(num)

	num = integerToString(-12345)
	fmt.Println(num)
}