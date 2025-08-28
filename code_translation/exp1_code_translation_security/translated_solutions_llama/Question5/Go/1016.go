package main

import (
	"fmt"
	"strconv"
)

// intToString converts an integer to a string with commas as thousand separators.
func intToString(num int) string {
	var str string
	var sign string

	if num < 0 {
		sign = "-"
		num = -num
	}

	var parts []string
	for num > 0 {
		part := strconv.Itoa(num % 1000)
		if len(part) < 3 {
			part = fmt.Sprintf("%0*d%s", 3-len(part), 0, part)
		}
		parts = append([]string{part}, parts...)
		num /= 1000
	}

	for i, part := range parts {
		if i > 0 {
			part = "," + part
		}
		str += part
	}

	return sign + str
}

func main() {
	num := -10099870
	str := intToString(num)
	fmt.Println(str)
}