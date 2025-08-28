package main

import (
	"fmt"
	"strconv"
)

// getLength returns the number of digits in the given integer.
func getLength(num int) int {
	length := 0
	numCopy := num
	for numCopy > 0 {
		numCopy /= 10
		length++
	}
	return length
}

// convertToString converts the given integer to a string and appends "test".
func convertToString(num int) string {
	return strconv.Itoa(num) + "test"
}

func main() {
	num := 7000000
	fmt.Print(getLength(num))         // Outputs: 7
	fmt.Print(convertToString(5))     // Outputs: 5test
}