package main

import (
	"fmt"
	"strconv"
)

// intToString converts an integer to a string and prints it.
func intToString(input int) string {
	// Convert integer to string
	str := strconv.Itoa(input)

	// Print the string
	fmt.Printf("you have entered: %s\n", str)

	return str
}

func main() {
	intToString(7000000)
}