package main

import (
	"fmt"
	"strconv"
	"strings"
)

func reverse(s string) string {
	runes := []rune(s)
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	return string(runes)
}

func itoa(n int) string {
	var result strings.Builder
	sign := n
	if sign < 0 {
		n = -n
	}
	
	for {
		result.WriteByte(byte(n%10) + '0')
		n /= 10
		if n == 0 {
			break
		}
	}
	
	if sign < 0 {
		result.WriteByte('-')
	}
	
	return result.String()
}

func insertComma(str string, pos int) string {
	if pos >= len(str) {
		return str
	}
	return str[:pos] + "," + str[pos:]
}

func intToString(num int) string {
	s := itoa(num)
	length := len(s)
	
	for i := 3; i <= length; i += 4 {
		s = insertComma(s, i)
		length = len(s) // Update length after insertion
	}
	
	if len(s) > 0 && s[len(s)-1] == ',' {
		s = s[:len(s)-1]
	}
	
	s = reverse(s)
	return s
}

func main() {
	num := 7000
	result := intToString(num)
	fmt.Print(result)
}