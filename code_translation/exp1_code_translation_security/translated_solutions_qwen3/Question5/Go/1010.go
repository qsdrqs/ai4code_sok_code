package main

import (
	"fmt"
	"strconv"
	"strings"
)

// reverse returns a new string with the characters in reverse order.
func reverse(s string) string {
	runes := []rune(s)
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	return string(runes)
}

// addCommas inserts commas as thousand separators into the given number string.
func addCommas(s string) string {
	if s == "0" {
		return "0"
	}

	// Reverse the string to make it easier to insert commas from the right
	reversed := reverse(s)

	// Split the reversed string into chunks of 3 characters
	var chunks []string
	for i := 0; i < len(reversed); i += 3 {
		end := i + 3
		if end > len(reversed) {
			end = len(reversed)
		}
		chunks = append(chunks, reversed[i:end])
	}

	// Join the chunks with commas and reverse back to get the final formatted string
	joined := strings.Join(chunks, ",")
	return reverse(joined)
}

// stringIt converts the given integer to a string with commas as thousand separators.
func stringIt(value int) string {
	if value < 0 {
		return "-" + addCommas(strconv.Itoa(-value))
	}
	return addCommas(strconv.Itoa(value))
}

// main is the entry point of the program.
func main() {
	// Print the formatted string with three leading spaces
	fmt.Printf("   %s\n", stringIt(-123457))
}