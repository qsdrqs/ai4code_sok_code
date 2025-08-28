package main

import (
	"fmt"
	"strconv"
)

// intToStringWithCommas translates the C function's *intent*.
// It takes an integer and returns it as a string with thousands separators.
// This version is safe, correct, and idiomatic Go.
func intToStringWithCommas(n int) string {
	// First, convert the integer to a plain string.
	// This is the Go equivalent of `sprintf(str, "%d", n)`.
	s := strconv.Itoa(n)
	length := len(s)

	// If the number is 3 digits or less, no commas are needed.
	if length <= 3 {
		return s
	}

	// Calculate the number of commas we'll need to insert.
	commas := (length - 1) / 3

	// Create a new byte slice to build the result string.
	// Its size will be the original length plus the number of commas.
	result := make([]byte, length+commas)

	// Find the length of the first group of digits (1, 2, or 3).
	firstGroupLen := length % 3
	if firstGroupLen == 0 {
		firstGroupLen = 3
	}

	// Pointers to track our position in the source (s) and destination (result)
	srcIndex := 0
	destIndex := 0

	// Copy the first group of digits.
	copy(result[destIndex:], s[srcIndex:srcIndex+firstGroupLen])
	srcIndex += firstGroupLen
	destIndex += firstGroupLen

	// Loop through the rest of the string, adding a comma and then 3 digits.
	for srcIndex < length {
		// Add a comma
		result[destIndex] = ','
		destIndex++

		// Add the next 3 digits
		copy(result[destIndex:], s[srcIndex:srcIndex+3])
		srcIndex += 3
		destIndex += 3
	}

	return string(result)
}

// main is the entry point of the program.
func main() {
	// The C code called: printf("%s", int_to_string(100000));
	// The Go equivalent is:
	fmt.Println(intToStringWithCommas(100000))

	// The C code then called: printf("test\n");
	// The Go equivalent is:
	fmt.Println("test")
}