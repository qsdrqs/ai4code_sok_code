package main

import (
	"fmt"
	"strconv"
)

// int2str converts an integer to its string representation and
// inserts a comma every three digits (counting from the right).
func int2str(num int) string {
	// Turn the whole number into a string first.
	// strconv.Itoa keeps the sign (‘-’) for negative numbers.
	base := strconv.Itoa(num)

	// Separate sign and the unsigned digits so that the grouping
	// logic only works on the digits themselves.
	sign := ""
	digits := base
	if num < 0 {
		sign = "-"
		digits = base[1:] // Strip the leading ‘-’
	}

	length := len(digits)
	if length == 0 {
		return sign
	}

	// How many extra commas do we need?
	// Exactly (length-1)/3 of them.
	var newLen int
	if length%3 == 0 {
		newLen = length + length/3 - 1
	} else {
		newLen = length + length/3
	}

	// Build the result in a byte slice, working from the end.
	out := make([]byte, newLen)

	counter := 0
	j := newLen - 1
	for i := length - 1; i >= 0; i-- {
		counter++
		out[j] = digits[i]
		j--

		if counter%3 == 0 && i != 0 { // Don’t put a comma at the very front
			out[j] = ','
			j--
		}
	}

	return sign + string(out)
}

func main() {
	tests := []int{0, 7, 12, 123, 1234, 1234567, -987654321}
	for _, v := range tests {
		fmt.Printf("%d → %s\n", v, int2str(v))
	}
}