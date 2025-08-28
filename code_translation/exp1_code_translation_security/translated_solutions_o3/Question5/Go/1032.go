package main

import (
	"fmt"
)

/*
 * intToString replicates the C helper that converts an int
 * to its decimal representation without using strconv.
 */
func intToString(num int) string {
	// Special-case zero (the C version never handled it).
	if num == 0 {
		return "0"
	}

	// Determine how many digits we’ll need.
	tmp := num
	length := 0
	for tmp != 0 {
		tmp /= 10
		length++
	}

	// Build the bytes in reverse order.
	bytes := make([]byte, length)
	for i := length - 1; i >= 0; i-- {
		bytes[i] = byte(num%10) + '0'
		num /= 10
	}
	return string(bytes)
}

/*
 * intToStringWithCommas copies the C algorithm that inserts
 * thousands separators into the string representation.
 */
func intToStringWithCommas(num int) string {
	str := intToString(num)
	length := len(str)
	commaCount := (length - 1) / 3
	newLen := length + commaCount

	out := make([]byte, newLen)

	j := 0 // index for the original string
	k := 0 // flag: 1 if we just emitted a comma
	for i := 0; i < newLen; i++ {
		if (length-j)%3 == 0 && k != 1 && i != 0 {
			out[i] = ','
			k = 1
		} else {
			out[i] = str[j]
			j++
			if k == 1 {
				k = 0
			}
		}
	}

	return string(out)
}

func main() {
	num := 7654321
	fmt.Println(intToStringWithCommas(num))
}