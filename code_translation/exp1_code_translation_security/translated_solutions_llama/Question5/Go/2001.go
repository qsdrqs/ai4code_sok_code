package main

import (
	"fmt"
	"strconv"
	"strings"
)

// intToString converts an integer to a string with commas as thousand separators.
func intToString(a int) string {
	str := strconv.Itoa(a)
	if len(str) <= 3 {
		return str
	}

	var parts []string
	for i := len(str); i > 0; i -= 3 {
		if i-3 < 0 {
			parts = append([]string{str[:i]}, parts...)
		} else {
			parts = append([]string{str[i-3 : i]}, parts...)
		}
	}

	return strings.Join(parts, ",")
}

func main() {
	fmt.Println(intToString(7000000))
	fmt.Println(intToString(700000))
	fmt.Println(intToString(70000))
	fmt.Println(intToString(7000))
	fmt.Println(intToString(700))
	fmt.Println(intToString(70))
	fmt.Println(intToString(7))
	fmt.Println(intToString(0))
	fmt.Println(intToString(-7000000))
	fmt.Println(intToString(-700000))
	fmt.Println(intToString(-70000))
	fmt.Println(intToString(-7000))
	fmt.Println(intToString(-700))
	fmt.Println(intToString(-70))
	fmt.Println(intToString(-7))
	fmt.Println(intToString(2147483647)) // equivalent to INT_MAX
	fmt.Println(intToString(-2147483648)) // equivalent to INT_MIN
}