package main

import (
	"fmt"
	"strconv"
)

// numToStr converts an int to a string with the usual thousands separators
// (e.g. –1234567 becomes "-1,234,567").
func numToStr(num int) string {
	// Convert the number to its base-10 representation first.
	s := strconv.Itoa(num)

	// Special-case zero – nothing to do.
	if num == 0 {
		return s
	}

	// Separate any sign from the absolute value’s digit-string.
	sign := ""
	if num < 0 {
		sign = "-"
		s = s[1:] // strip the leading ‘-’
	}

	length := len(s)
	numCommas := (length - 1) / 3
	out := make([]byte, length+numCommas)

	// Fill the output buffer from the right–hand side.
	i := len(out) - 1      // write index in out
	digitsMoved := 0       // how many digits have been copied since last comma

	for j := length - 1; j >= 0; j-- {
		out[i] = s[j]
		i--
		digitsMoved++

		if digitsMoved == 3 && j != 0 { // time to insert a comma
			out[i] = ','
			i--
			digitsMoved = 0
		}
	}

	// Re-attach any sign that was stripped.
	if sign != "" {
		return sign + string(out)
	}
	return string(out)
}

func main() {
	num := 7042
	str := numToStr(num)
	fmt.Printf("Output: %s\n", str)
}