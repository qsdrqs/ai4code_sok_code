package main

import (
	"fmt"
)

func reverseString(s string) string {
	runes := []rune(s)
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	return string(runes)
}

func itoaRev(n int) string {
	if n == 0 {
		return "0"
	}
	negative := false
	if n < 0 {
		negative = true
		n = -n
	}
	s := ""
	for n > 0 {
		s += string('0' + (n % 10))
		n = n / 10
	}
	if negative {
		s += "-"
	}
	return s
}

func insertComma(s string, pos int) string {
	if pos > len(s) {
		return s
	}
	return s[:pos] + "," + s[pos:]
}

func formatNumber(num int) string {
	s := itoaRev(num)
	initialLength := len(s)
	for i := 3; i <= initialLength; i += 4 {
		s = insertComma(s, i)
	}
	// Remove trailing comma if any
	if len(s) > 0 && s[len(s)-1] == ',' {
		s = s[:len(s)-1]
	}
	s = reverseString(s)
	return s
}

func main() {
	num := 7000
	str := formatNumber(num)
	fmt.Println(str) // Output: "7,000"
}