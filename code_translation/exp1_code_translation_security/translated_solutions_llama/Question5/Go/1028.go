package main

import (
	"fmt"
	"strconv"
	"strings"
)

// reverse reverses a given string
func reverse(s string) string {
	runes := []rune(s)
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	return string(runes)
}

// insertComma inserts a comma at a specified position in a string
func insertComma(str string, pos int) string {
	return str[:pos] + "," + str[pos:]
}

// intToString converts an integer to a string with commas
func intToString(num int) string {
	str := strconv.Itoa(abs(num))
	if num < 0 {
		str = "-" + str
	}

	length := len(str)
	for i := length - 3; i > 0; i -= 3 {
		str = insertComma(str, i)
	}

	return str
}

// abs returns the absolute value of a number
func abs(n int) int {
	if n < 0 {
		return -n
	}
	return n
}

func main() {
	num := 7000000
	fmt.Println(intToString(num))
}