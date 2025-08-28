package main

import (
	"fmt"
)

// -----------------------------------------------------------------------------
//  convertInt replicates (and fixes) the manual digit extraction done in
//  the C version.  It builds the decimal representation in reverse and then
//  reverses the slice before returning a string.
// -----------------------------------------------------------------------------
func convertInt(input int) string {
	if input == 0 {
		return "0"
	}

	// Enough room for any 32-bit signed integer (10 digits) plus sign if needed.
	var buf [11]byte
	i := 0

	// Build the number in reverse order.
	for input > 0 {
		buf[i] = byte(input%10) + '0'
		input /= 10
		i++
	}

	// Reverse the collected bytes to obtain the correct order.
	out := make([]byte, i)
	for j := 0; j < i; j++ {
		out[j] = buf[i-1-j]
	}

	return string(out)
}

// -----------------------------------------------------------------------------
//  convertIntAI is the Go analogue to the C version that used malloc + sprintf.
//  In Go we simply use fmt.Sprintf (or strconv.Itoa) and let the runtime
//  allocate the string for us.
// -----------------------------------------------------------------------------
func convertIntAI(input int) string {
	return fmt.Sprintf("%d", input)
}

func main() {
	output := convertIntAI(7_000_000)
	fmt.Println(output)
}