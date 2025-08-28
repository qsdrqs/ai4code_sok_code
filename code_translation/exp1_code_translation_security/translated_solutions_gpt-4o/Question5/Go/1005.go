package main

import (
	"fmt"
)

const MAX_LEN = 14

func stringify(i int, buf []byte) int {
	tmp := []byte("00000000000000")
	j := i
	if i < 0 {
		j = -i
	}
	k := MAX_LEN - 1
	c := 0

	for j > 0 && k >= 0 {
		if c != 0 && c%3 == 0 {
			tmp[k] = ','
			k--
			c = 0
		}
		// Insert comma
		// Convert digit to char
		digit := j % 10
		dChar := byte(digit) + '0'
		// Add to string
		tmp[k] = dChar
		// Move to next digit
		j /= 10
		k--
		c++
	}

	if i < 0 {
		tmp[k] = '-'
		k--
	}

	for n := k + 1; n < MAX_LEN; n++ {
		buf[n-k-1] = tmp[n]
	}

	return MAX_LEN - 1 - k
}

func main() {
	buf := make([]byte, MAX_LEN)
	input := -2147483647
	l := stringify(input, buf)
	fmt.Printf("%d :: %s\n", l, string(buf[:l]))
}