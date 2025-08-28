package main

import (
	"fmt"
)

const maxStrLen = 128

// reverseStr returns a new slice containing the characters of s in reverse order.
func reverseStr(s []byte) []byte {
	l := len(s)
	out := make([]byte, l)
	for i := 0; i < l; i++ {
		out[i] = s[l-i-1]
	}
	return out
}

/*
strOfInt returns the string representation of an int.

Args:
    num int: the signed integer to convert

Return:
    string representation of num
*/
func strOfInt(num int) string {
	buf := make([]byte, 0, maxStrLen)
	isNeg := false

	if num < 0 {
		isNeg = true
		num = -num
	}

	// Collect digits in reverse order.
	if num == 0 {
		buf = append(buf, '0')
	} else {
		for num != 0 {
			buf = append(buf, byte(num%10)+'0')
			num /= 10
		}
	}

	if isNeg {
		buf = append(buf, '-')
	}

	// Reverse and convert to string.
	return string(reverseStr(buf))
}

func main() {
	fmt.Println(strOfInt(7000000))
}