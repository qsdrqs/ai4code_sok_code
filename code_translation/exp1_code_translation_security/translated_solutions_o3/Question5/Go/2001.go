package main

import (
	"fmt"
	"math"
	"strconv"
)

// intToString converts an integer to a string with commas every three digits
// (e.g. 7000000 -> "7,000,000").  Behaviour matches the original C version.
func intToString(a int) string {
	// First convert the entire number to a (plain) decimal string.
	s := strconv.Itoa(a)
	if len(s) == 0 {
		return s
	}

	// Handle the sign (if present).
	negative := false
	if s[0] == '-' {
		negative = true
		s = s[1:] // strip the leading '-'
	}

	digitCount := len(s)
	// How many commas will be inserted?
	commaCount := (digitCount - 1) / 3

	// Prepare an output buffer with enough capacity.
	// +1 leaves room for the '-' sign if we need it.
	out := make([]byte, 0, digitCount+commaCount+1)

	if negative {
		out = append(out, '-')
	}

	// Walk through the digits from left to right, inserting commas
	// whenever the number of remaining digits is a multiple of 3.
	for i, ch := range s {
		if i != 0 && (digitCount-i)%3 == 0 {
			out = append(out, ',')
		}
		out = append(out, byte(ch))
	}

	return string(out)
}

func main() {
	fmt.Println(intToString(7000000))
	fmt.Println(intToString(700000))
	fmt.Println(intToString(70000))
	fmt.Println(intToString(7000))
	fmt.Println(intToString(700))
	fmt.Println(intToString(70))
	fmt.Println(intToString(7))
	fmt.Println(intToString(0))
	fmt.Println(intToString(-7000000))
	fmt.Println(intToString(-700000))
	fmt.Println(intToString(-70000))
	fmt.Println(intToString(-7000))
	fmt.Println(intToString(-700))
	fmt.Println(intToString(-70))
	fmt.Println(intToString(-7))
	fmt.Println(intToString(math.MaxInt32))
	fmt.Println(intToString(math.MinInt32))
}