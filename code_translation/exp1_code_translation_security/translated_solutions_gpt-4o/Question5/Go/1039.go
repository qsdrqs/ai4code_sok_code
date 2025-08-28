package main

import (
	"fmt"
	"strconv"
)

// getLength calculates the number of digits in an integer.
func getLength(num int) int {
	len := 0
	numCopy := num
	for numCopy > 0 {
		numCopy = numCopy / 10
		len = len + 1
	}
	return len
}

// convertToString converts an integer to a string and appends "test".
func convertToString(num int) string {
	str := strconv.Itoa(num) // Convert integer to string
	str += "test"            // Append "test" to the string
	return str
}

func main() {
	num := 7000000
	fmt.Println(getLength(num))          // Print the length of the number
	fmt.Println(convertToString(5))     // Print the converted string
}