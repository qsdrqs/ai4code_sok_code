package main

import (
	"fmt"
)

// reverse reverses the bytes of a slice *in place* – the
// direct counterpart of the C function `reverse`.
func reverse(b []byte) {
	for i, j := 0, len(b)-1; i < j; i, j = i+1, j-1 {
		b[i], b[j] = b[j], b[i]
	}
}

/*
repr

Takes in a signed integer and returns the string representation
of that integer with thousands separators (commas), exactly as
the original C function did.
*/
func repr(n int) string {
	// Special-case the minimum 32-bit value: –2,147,483,648
	if n == -2147483648 {
		return "-2,147,483,648"
	}

	// Handle 0 right away.
	if n == 0 {
		return "0"
	}

	sign := 1
	if n < 0 {
		sign = -1
		n = -n
	}

	// Build the number in reverse order.
	var tmp []byte
	digitCount := 0

	for n > 0 {
		tmp = append(tmp, byte(n%10)+'0') // current least-significant digit
		n /= 10
		digitCount++

		// Insert a comma after every three digits *if* more digits remain.
		if digitCount%3 == 0 && n > 0 {
			tmp = append(tmp, ',')
		}
	}

	// Add the minus sign if the original value was negative.
	if sign == -1 {
		tmp = append(tmp, '-')
	}

	// Reverse to obtain the final string.
	reverse(tmp)
	return string(tmp)
}

func main() {
	n := 0
	str := repr(n)
	fmt.Print(str)
}