package main

import (
	"fmt"
	"strconv"
)

// stringIt returns a string containing the expansion of the signed int with commas.
// This translation closely follows the C code's algorithm of building the
// formatted string from right to left.
func stringIt(value int) string {
	// Handle the sign, similar to the C code.
	prefix := ""
	if value < 0 {
		prefix = "-"
		// Work with the positive version of the number.
		value = -value
	}

	// Convert the number to a string (the part with only digits).
	// This replaces the C `sprintf` call.
	s := strconv.Itoa(value)
	n := len(s)

	// Calculate how many commas are needed, same logic as in the C code.
	commaCount := (n - 1) / 3

	// If no commas are needed, we can return early.
	if commaCount == 0 {
		return prefix + s
	}

	// In C, memory was allocated with `malloc`. In Go, we can create a byte
	// slice of the exact required size. The total length will be the original
	// number of digits plus the number of commas.
	resultLen := n + commaCount
	result := make([]byte, resultLen)

	// We'll work backwards from the end of both the source string ('s') and the
	// result slice, which mimics the C algorithm's right-to-left approach.
	srcIdx := n - 1         // Index for the source string 's'
	destIdx := resultLen - 1 // Index for the destination slice 'result'
	digitsCopied := 0

	for srcIdx >= 0 {
		// After every 3 digits are copied, insert a comma.
		if digitsCopied == 3 {
			result[destIdx] = ','
			destIdx--
			digitsCopied = 0
		}

		// Copy a digit from the source to the destination.
		result[destIdx] = s[srcIdx]
		destIdx--
		srcIdx--
		digitsCopied++
	}

	// Combine the sign prefix with the formatted number string.
	return prefix + string(result)
}

func main() {
	// The C code had debug prints inside the function which are not included
	// in this final translated function, as they were not part of the core logic.
	// The C code also had a comment about not compiling, which is true due to
	// missing includes (like <string.h>). The translated Go code is complete
	// and runnable.
	fmt.Printf("   %s\n", stringIt(-123457))
}