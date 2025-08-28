package main

import (
	"fmt"
	"strconv"
)

func intToString(input int) string {
	// Convert the integer to a string
	str := strconv.Itoa(input)

	// Print the string
	fmt.Printf("you have entered: %s", str)

	// Return the string
	return str
}

func main() {
	intToString(7000000)
}