package main

import (
	"fmt"
	"strconv"
)

// getLength returns the number of digits in a given integer.
func getLength(num int) int {
	return len(strconv.Itoa(num))
}

// convertToString converts an integer to a string.
func convertToString(num int) string {
	return strconv.Itoa(num)
}

func main() {
	num := 7000000
	fmt.Print(getLength(num))
	fmt.Print(convertToString(5))
}