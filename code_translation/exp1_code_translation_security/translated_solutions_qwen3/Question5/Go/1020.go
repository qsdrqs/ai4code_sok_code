package main

import (
	"fmt"
	"strconv"
)

func main() {
	// Define the integer you want to convert
	number := -12

	// Define the string you want to store the integer in
	var str string

	// Convert the integer to a string
	str = strconv.Itoa(number)

	// Print the string
	fmt.Printf("%s\n", str)
}