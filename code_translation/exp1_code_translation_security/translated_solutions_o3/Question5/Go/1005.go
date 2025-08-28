package main

import (
	"fmt"
)

const maxLen = 14 // identical to C’s MAX_LEN

// stringify formats i into buf and returns the number of bytes written.
// buf must be at least maxLen bytes long (just like the C version assumes).
func stringify(i int, buf []byte) int {
	if len(buf) < maxLen {
		panic("buffer too small")
	}

	// tmp plays the same role the C array did, pre-filled with '0'.
	tmp := make([]byte, maxLen)
	for n := range tmp {
		tmp[n] = '0'
	}

	abs := i
	if abs < 0 {
		abs = -abs
	}

	k := maxLen - 1 // index we are writing into tmp
	c := 0          // digits written since last comma

	for abs > 0 && k >= 0 {
		// insert a comma every third digit
		if c != 0 && c%3 == 0 {
			tmp[k] = ','
			k--
			c = 0
		}

		digit := abs % 10
		tmp[k] = byte('0' + digit)

		abs /= 10
		k--
		c++
	}

	// add a minus sign if the original value was negative
	if i < 0 && k >= 0 {
		tmp[k] = '-'
		k--
	}

	// copy the useful tail of tmp into the supplied buffer
	n := maxLen - 1 - k              // number of bytes that matter
	copy(buf, tmp[k+1:])             // buf gets the string
	return n                         // length (like the C function)
}

func main() {
	var buf [maxLen]byte
	input := -2147483647

	l := stringify(input, buf[:])
	fmt.Printf("%d :: %s\n", l, string(buf[:l]))
}