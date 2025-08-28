package main

import (
	"fmt"
)

// reverse reverses the contents of a byte slice in-place.
func reverse(buf []byte) {
	for i, j := 0, len(buf)-1; i < j; i, j = i+1, j-1 {
		buf[i], buf[j] = buf[j], buf[i]
	}
}

// integerToString converts an int to its decimal string representation.
func integerToString(num int) string {
	// Special-case zero so we don’t return an empty string.
	if num == 0 {
		return "0"
	}

	negative := false
	if num < 0 {
		negative = true
		num = -num // Note: identical to original C, so overflow for MinInt is ignored.
	}

	// Build the digits in reverse order.
	var digits []byte
	for n := num; n > 0; n /= 10 {
		digit := n % 10
		digits = append(digits, byte('0'+digit))
	}

	if negative {
		digits = append(digits, '-')
	}

	// Reverse the slice so digits appear in correct order.
	reverse(digits)

	return string(digits)
}

func main() {
	fmt.Println(integerToString(12345))   // 12345
	fmt.Println(integerToString(-12345))  // -12345
}