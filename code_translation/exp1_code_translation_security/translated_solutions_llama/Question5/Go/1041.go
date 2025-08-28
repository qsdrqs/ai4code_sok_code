package main

import (
	"fmt"
	"strconv"
	"strings"
)

// reverse_str takes a string and reverses it
func reverse_str(s string) string {
	runes := []rune(s)
	for i, j := 0, len(runes)-1; i < j; i, j = i+1, j-1 {
		runes[i], runes[j] = runes[j], runes[i]
	}
	return string(runes)
}

// add_commas takes an unsigned integer, converts it into a string,
// adds commas where appropriate, and returns the new string
func add_commas(num uint64) string {
	str := strconv.FormatUint(num, 10)
	reversedStr := reverse_str(str)
	var result string
	for i, char := range reversedStr {
		result += string(char)
		if (len(reversedStr)-i)%3 == 0 && i != len(reversedStr)-1 {
			result += ","
		}
	}
	return reverse_str(result)
}

func main() {
	num := uint64(1000000)
	str := add_commas(num)
	fmt.Println(str)
}