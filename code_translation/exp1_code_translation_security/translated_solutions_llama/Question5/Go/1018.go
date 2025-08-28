package main

import (
	"fmt"
	"strconv"
)

// Function to add commas as thousand separators to a given integer
func change(input int) string {
	// Convert the integer to a string
	str := strconv.Itoa(input)

	// Initialize variables
	var result string
	count := len(str)

	// Calculate the number of commas needed
	numCommas := count / 3

	// Build the result string with commas
	for i := count; i > 0; i-- {
		result = str[i-1] + result
		if (count-i+1)%3 == 0 && i != 1 {
			result = "," + result
		}
	}

	return result
}

func main() {
	snum := change(700000000)
	fmt.Println(snum)
}