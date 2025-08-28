package main

import (
	"fmt"
)

// reverseStr reverses the contents of a byte slice in-place and returns it.
func reverseStr(b []byte) []byte {
	for i, j := 0, len(b)-1; i < j; i, j = i+1, j-1 {
		b[i], b[j] = b[j], b[i]
	}
	return b
}

// addCommas converts an unsigned integer to a string with commas
// inserted every three digits.
func addCommas(num uint) string {
	if num == 0 {
		return "0"
	}

	// Build the number backwards (least-significant digit first).
	buf := make([]byte, 0, 32) // 32 is plenty for any 64-bit integer.
	count := 0

	for num > 0 {
		digit := byte(num % 10)
		buf = append(buf, '0'+digit)
		num /= 10
		count++

		// After every third digit (except at the most significant end),
		// append a comma.
		if count == 3 && num > 0 {
			buf = append(buf, ',')
			count = 0
		}
	}

	// Reverse the buffer to restore the correct order
	reverseStr(buf)
	return string(buf)
}

func main() {
	num := uint(1_000_000)
	str := addCommas(num)
	fmt.Println(str)
}