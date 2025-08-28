package main

import (
	"fmt"
	"strconv"
	"strings"
)

// addCommas inserts commas into the string representation of a number
// to separate every three digits from the right.
func addCommas(s string) string {
	if len(s) <= 3 {
		return s
	}

	// Reverse the string to process from the right
	reversed := []rune(s)
	for i, j := 0, len(reversed)-1; i < j; i, j = i+1, j-1 {
		reversed[i], reversed[j] = reversed[j], reversed[i]
	}

	// Split into chunks of 3 digits
	var chunks []string
	for i := 0; i < len(reversed); i += 3 {
		end := i + 3
		if end > len(reversed) {
			end = len(reversed)
		}
		chunks = append(chunks, string(reversed[i:end]))
	}

	// Join with commas and reverse back
	joined := strings.Join(chunks, ",")
	result := []rune(joined)
	for i, j := 0, len(result)-1; i < j; i, j = i+1, j-1 {
		result[i], result[j] = result[j], result[i]
	}

	return string(result)
}

// numToStr converts an integer to a string with commas as thousand separators.
func numToStr(num int) string {
	s := strconv.Itoa(num)
	if num == 0 {
		return s
	}
	if num < 0 {
		return "-" + addCommas(s[1:])
	}
	return addCommas(s)
}

func main() {
	num := 7042
	str := numToStr(num)
	fmt.Printf("Output: %s\n", str)
}