package main

import (
	"fmt"
	"strconv"
	"strings"
)

// reverseStr reverses a string and returns the reversed string
func reverseStr(str string) string {
	runes := []rune(str)
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	return string(runes)
}

// addCommas takes an integer, converts it to a string, and adds commas where appropriate
func addCommas(num int) string {
	str := ""
	count := 0

	for num != 0 {
		digit := num % 10
		str += strconv.Itoa(digit)
		num /= 10
		count++

		// Add a comma after every 3 digits, except for the last group
		if count == 3 && num != 0 {
			str += ","
			count = 0
		}
	}

	// Reverse the string to get the final result
	return reverseStr(str)
}

func main() {
	num := 1000000
	str := addCommas(num)
	fmt.Println(str)
}