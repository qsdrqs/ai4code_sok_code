package main

import (
	"fmt"
	"math"
	"strconv"
	"strings"
)

// intToString converts an integer to a string with commas as thousand separators.
func intToString(a int) string {
	// Convert the integer to a string
	s := strconv.Itoa(a)

	// Extract sign and digits
	var sign string
	var digits string
	if len(s) > 0 && s[0] == '-' {
		sign = "-"
		digits = s[1:]
	} else {
		sign = ""
		digits = s
	}

	// Length of the digits part
	lenDigits := len(digits)

	// Use strings.Builder for efficient string concatenation
	var b strings.Builder
	b.WriteString(sign)

	// Insert commas every three digits from the right
	for i := 0; i < lenDigits; i++ {
		if i != 0 && (lenDigits-i)%3 == 0 {
			b.WriteString(",")
		}
		b.WriteByte(digits[i])
	}

	return b.String()
}

func main() {
	// Test cases from the original C code
	fmt.Println(intToString(7000000))   // Output: 7,000,000
	fmt.Println(intToString(700000))    // Output: 700,000
	fmt.Println(intToString(70000))     // Output: 70,000
	fmt.Println(intToString(7000))      // Output: 7,000
	fmt.Println(intToString(700))       // Output: 700
	fmt.Println(intToString(70))        // Output: 70
	fmt.Println(intToString(7))         // Output: 7
	fmt.Println(intToString(0))         // Output: 0
	fmt.Println(intToString(-7000000))  // Output: -7,000,000
	fmt.Println(intToString(-700000))   // Output: -700,000
	fmt.Println(intToString(-70000))    // Output: -70,000
	fmt.Println(intToString(-7000))     // Output: -7,000
	fmt.Println(intToString(-700))      // Output: -700
	fmt.Println(intToString(-70))       // Output: -70
	fmt.Println(intToString(-7))        // Output: -7
	fmt.Println(intToString(math.MaxInt32))  // Output: 2,147,483,647
	fmt.Println(intToString(math.MinInt32))  // Output: -2,147,483,648
}