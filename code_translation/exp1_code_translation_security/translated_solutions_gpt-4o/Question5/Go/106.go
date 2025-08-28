package main

import (
	"fmt"
)

// reverseStr reverses a given string and returns the reversed string.
func reverseStr(str string) string {
	len := len(str)
	newStr := make([]byte, len)
	for i := 0; i < len; i++ {
		newStr[i] = str[len-i-1]
	}
	return string(newStr)
}

/*
strOfInt returns a string representation of a signed integer.

Args:
    num: input number as a signed integer

Return:
    string representation of the signed integer
*/
func strOfInt(num int) string {
	const MAX_STR_LEN = 128
	str := make([]byte, 0, MAX_STR_LEN)
	isNeg := false

	if num < 0 {
		isNeg = true
		num = -num
	}

	for {
		str = append(str, byte(num%10)+'0')
		num /= 10
		if num == 0 {
			break
		}
	}

	if isNeg {
		str = append(str, '-')
	}

	return reverseStr(string(str))
}

func main() {
	fmt.Println(strOfInt(7000000))
}