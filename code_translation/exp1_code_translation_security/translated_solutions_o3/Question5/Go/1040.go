package main

import (
	"fmt"
)

// numDigits returns the number of decimal digits in n (> 0).
func numDigits(n int) int {
	digits := 0
	for n != 0 {
		n /= 10
		digits++
	}
	return digits
}

// intToStringWithCommas converts an int to a string with thousands separators.
// It also prints the resulting string, exactly like the C version.
func intToStringWithCommas(value int) string {
	isNegative := value < 0
	if isNegative {
		value *= -1
	}

	digits := numDigits(value)
	if digits == 0 { // value was 0 – the C code never had this case
		digits = 1
	}

	numCommas := digits / 3
	if digits%3 == 0 {
		numCommas--
	}

	// s is the plain decimal representation of |value|.
	s := fmt.Sprintf("%d", value)

	size := digits + numCommas               // total length without minus sign
	dest := make([]byte, size)               // destination "char array"
	index := size - 1                        // last position in dest

	for i := digits; i > 0; i-- {            // walk through s from the end
		dest[index] = s[i-1]
		index--
		if i%3 == 0 && i > 0 {               // insert a comma every 3 digits
			dest[index] = ','
			index--
		}
	}

	result := string(dest)
	if isNegative {
		result = "-" + result
	}

	fmt.Print(result)                        // keep the side-effect of printing
	return result
}

func main() {
	intToStringWithCommas(7000000) // prints: 7,000,000
}