package main

import (
	"fmt"
)

/*
A function that reverse a string
*/
func strrev(input string) string {
	runes := []rune(input)
	start := 0
	end := len(runes) - 1
	
	for end > start {
		runes[start], runes[end] = runes[end], runes[start]
		start++
		end--
	}
	
	return string(runes)
}

/*
A function that takes a signed integer num and returns a string representation of the integer
*/
func integerToString(num int) string {
	result := make([]byte, 0, 100)
	isNegative := false

	if num < 0 {
		isNegative = true
		num = -num
	}

	for {
		digit := num % 10
		result = append(result, byte('0'+digit))
		num /= 10
		if num == 0 {
			break
		}
	}

	if isNegative {
		result = append(result, '-')
	}

	return strrev(string(result))
}

func main() {
	num := integerToString(12345)
	fmt.Println(num)

	num = integerToString(-12345)
	fmt.Println(num)
}