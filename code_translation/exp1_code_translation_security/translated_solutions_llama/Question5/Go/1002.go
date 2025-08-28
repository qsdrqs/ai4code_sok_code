package main

import (
	"fmt"
	"strconv"
	"strings"
)

const bufferSize = 1024

// convert takes an int64 value and returns a string representation with commas as thousand separators.
func convert(value int64) string {
	numStr := strconv.FormatInt(value, 10)
	return insertCommas(numStr)
}

// insertCommas inserts commas as thousand separators into a string representation of a number.
func insertCommas(numStr string) string {
	var result strings.Builder
	count := 0
	for i := len(numStr) - 1; i >= 0; i-- {
		if count == 3 {
			result.WriteByte(',')
			count = 0
		}
		result.WriteByte(numStr[i])
		count++
	}
	runes := []rune(result.String())
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	return string(runes)
}

func main() {
	test := convert(1000)
	fmt.Println(test)
}