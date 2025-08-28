package main

import (
	"fmt"
	"strconv"
)

func main() {
	// Define the integer you want to convert
	number := -12

	// Convert the integer to a string
	str := strconv.Itoa(number)

	// Print the string
	fmt.Println(str)
}