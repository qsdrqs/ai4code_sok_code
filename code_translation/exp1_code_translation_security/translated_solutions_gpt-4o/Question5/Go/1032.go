package main

import (
	"fmt"
	"strconv"
	"strings"
)

func intToString(num int) string {
	return strconv.Itoa(num)
}

func intToStringWithCommas(num int) string {
	str := intToString(num)
	lenStr := len(str)
	commaCount := (lenStr - 1) / 3

	// Create a new string builder to construct the result
	var result strings.Builder
	result.Grow(lenStr + commaCount)

	// Add characters to the result with commas
	for i, j := 0, lenStr; i < lenStr; i, j = i+1, j-1 {
		result.WriteByte(str[i])
		if j > 1 && j%3 == 1 {
			result.WriteByte(',')
		}
	}

	return result.String()
}

func main() {
	num := 7654321
	str := intToStringWithCommas(num)
	fmt.Println(str)
}