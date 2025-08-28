package main

import (
	"fmt"
	"strings"
)

// reverseStr takes a string and returns a new string with the characters in reverse order.
// This is the Go equivalent of the C function `reverse_str`.
func reverseStr(s string) string {
	// In Go, strings are immutable. To modify the characters, we first convert
	// the string to a slice of runes, which supports multi-byte characters.
	runes := []rune(s)
	
	// Use a two-pointer approach to swap elements from the beginning and end,
	// moving towards the center.
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	
	// Convert the modified rune slice back to a string.
	return string(runes)
}

// addCommas takes an unsigned integer, converts it to a string, and adds
// comma separators for thousands. This is the Go equivalent of `add_commas`.
func addCommas(num uint) string {
	// Handle the edge case of the number being 0.
	if num == 0 {
		return "0"
	}

	// A strings.Builder is an efficient way to build a string piece by piece.
	// It's the idiomatic Go alternative to manual memory allocation and concatenation.
	var builder strings.Builder
	count := 0

	// This loop processes the number from right to left, building the string in reverse.
	for num > 0 {
		// Get the last digit of the number.
		digit := num % 10
		// Convert the digit (e.g., 5) to its character representation ('5') and append it.
		builder.WriteByte(byte(digit) + '0')
		// Remove the last digit from the number.
		num /= 10
		count++

		// If we have added 3 digits and there are more digits left in the number,
		// add a comma.
		if count == 3 && num > 0 {
			builder.WriteByte(',')
			count = 0
		}
	}

	// The string was built in reverse (e.g., "000,001" for 1,000,000).
	// We now call reverseStr to get the correct order.
	reversedString := builder.String()
	return reverseStr(reversedString)
}

// main is the entry point of the program.
func main() {
	num := 1000000
	
	// Call addCommas to format the number. We cast the int to a uint
	// to match the function's required type.
	str := addCommas(uint(num))
	
	// Print the final, formatted string.
	fmt.Println(str)
	
	// In Go, there is no need to manually free memory like in C with `free(str)`.
	// The garbage collector handles this automatically.
}