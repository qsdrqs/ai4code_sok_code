package main

import (
	"fmt"
	"strconv"
	"strings"
)

// reverse reverses a string in place
func reverse(s []rune) {
	for i, j := 0, len(s)-1; i < j; i, j = i+1, j-1 {
		s[i], s[j] = s[j], s[i]
	}
}

// itoa converts an integer to its string representation
func itoa(n int) string {
	sign := ""
	if n < 0 {
		sign = "-"
		n = -n
	}

	var result []rune
	for {
		result = append(result, rune(n%10+'0'))
		n /= 10
		if n == 0 {
			break
		}
	}

	if sign == "-" {
		result = append(result, '-')
	}

	reverse(result)
	return string(result)
}

// insertComma inserts a comma at the specified position in the string
func insertComma(str string, pos int) string {
	return str[:pos] + "," + str[pos:]
}

// intToString converts an integer to a string with commas as thousand separators
func intToString(num int) string {
	// Convert the integer to a string
	s := itoa(num)

	// Reverse the string to process from the least significant digit
	runes := []rune(s)
	reverse(runes)

	// Insert commas every 3 digits
	for i := 3; i < len(runes); i += 4 {
		runes = append(runes[:i], append([]rune{','}, runes[i:]...)...)
	}

	// Reverse the string back to its original order
	reverse(runes)

	return string(runes)
}

func main() {
	num := 7000
	result := intToString(num)
	fmt.Println(result)
}