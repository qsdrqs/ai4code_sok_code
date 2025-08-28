package main

import (
	"fmt"
	"math"
	"strconv"
	"strings"
)

// intToString converts an integer to a string, adding thousand separators.
// This function faithfully translates the logic from the original C code.
func intToString(a int32) string {
	// In Go, we first convert the entire number to a string.
	// This replaces the C `sprintf` call.
	s := strconv.FormatInt(int64(a), 10)

	// Separate the sign (if any) from the number part, similar to the C code's logic.
	var numberPart string
	var sign string
	if a < 0 {
		sign = "-"
		numberPart = s[1:] // The string part after the '-'
	} else {
		numberPart = s
	}

	n := len(numberPart)
	// If the number is short, no commas are needed.
	if n <= 3 {
		return s
	}

	// A strings.Builder is the idiomatic and efficient Go equivalent of building
	// a string in a mutable char array.
	var result strings.Builder
	
	// Reserve space to avoid reallocations. The final length will be the
	// original length plus the number of commas.
	result.Grow(n + (n-1)/3)

	result.WriteString(sign)

	// This loop directly mimics the C code's logic for placing commas.
	// A comma is inserted if we are not at the first digit and the
	// number of remaining digits is a multiple of 3.
	for i, digit := range numberPart {
		if i > 0 && (n-i)%3 == 0 {
			result.WriteByte(',')
		}
		result.WriteRune(digit)
	}

	return result.String()
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

	// In C, INT_MAX and INT_MIN from <limits.h> are typically for 32-bit integers.
	// The Go equivalent constants are in the 'math' package.
	fmt.Println(intToString(int32(math.MaxInt32)))
	fmt.Println(intToString(int32(math.MinInt32)))
}