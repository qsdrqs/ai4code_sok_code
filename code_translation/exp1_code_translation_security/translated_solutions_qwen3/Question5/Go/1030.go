package main

import (
	"fmt"
	"strconv"
)

// formatWithCommas formats an integer with commas as thousand separators.
func formatWithCommas(n int) string {
	s := strconv.Itoa(n)
	negative := false

	if n < 0 {
		negative = true
		s = s[1:]
	}

	var result string
	for i := 0; i < len(s); i++ {
		if i > 0 && (len(s)-i)%3 == 0 {
			result += ","
		}
		result += string(s[i])
	}

	if negative {
		result = "-" + result
	}

	return result
}

func main() {
	num := 7000000
	snum := formatWithCommas(num)
	fmt.Println(snum)
}