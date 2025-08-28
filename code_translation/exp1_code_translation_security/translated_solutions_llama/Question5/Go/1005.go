package main

import (
	"fmt"
	"strconv"
)

// MAX_LEN represents the maximum length of the string buffer.
const MAX_LEN = 14

// stringify converts an integer into a string with commas as thousand separators.
func stringify(i int, buf []byte) int {
	tmp := make([]byte, MAX_LEN)
	copy(tmp, []byte("00000000000000"))

	j := abs(i)
	k := MAX_LEN - 1
	c := 0

	for j > 0 && k >= 0 {
		if c != 0 && c%3 == 0 {
			tmp[k] = ','
			k--
			c = 0
		}
		// Convert digit to char
		digit := j % 10
		d_char := byte(digit + '0')
		// Add to string
		tmp[k] = d_char
		// Move to next digit
		j /= 10
		k--
		c++
	}

	if i < 0 {
		tmp[k] = '-'
		k--
	}

	// Copy tmp to buf, skipping the first character of tmp (which is always 0)
	for n := k; n < MAX_LEN; n++ {
		buf[n-k] = tmp[n]
	}

	return MAX_LEN - 1 - k
}

// abs returns the absolute value of an integer.
func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func main() {
	buf := make([]byte, MAX_LEN)
	input := -2147483647
	l := stringify(input, buf)
	fmt.Printf("%d :: %s\n", l, string(buf[:l]))
}