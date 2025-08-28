package main

import (
	"fmt"
	"strconv"
	"strings"
)

// stringIt returns the decimal representation of value
// with thousands separators (commas) – e.g. -123457 → "-123,457".
func stringIt(value int) string {
	sign := ""
	if value < 0 {
		sign = "-"
		value = -value
	}

	// Convert absolute value to a digit string.
	digits := strconv.Itoa(value)
	n := len(digits)

	// How many commas will we need?
	commaCount := (n - 1) / 3
	if commaCount == 0 {
		return sign + digits
	}

	// Build the result.
	var b strings.Builder
	b.Grow(n + commaCount) // pre-allocate enough space

	// Write the first (possibly short) block of digits.
	first := n % 3
	if first == 0 {
		first = 3
	}
	b.WriteString(digits[:first])

	// Append the remaining blocks, each preceded by a comma.
	for i := first; i < n; i += 3 {
		b.WriteByte(',')
		b.WriteString(digits[i : i+3])
	}

	return sign + b.String()
}

func main() {
	fmt.Printf("   %s\n", stringIt(-123457))
}