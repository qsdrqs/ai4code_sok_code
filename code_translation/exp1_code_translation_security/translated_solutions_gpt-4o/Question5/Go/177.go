package main

import (
	"fmt"
	"strconv"
	"strings"
)

func int2str(num int) string {
	// Convert the integer to a string
	str := strconv.Itoa(num)
	length := len(str)

	// Calculate the new length with commas
	var newLength int
	if length%3 == 0 {
		newLength = length + length/3 - 1
	} else {
		newLength = length + length/3
	}

	// Create a result slice to hold the formatted string
	result := make([]byte, newLength)
	counter := 0
	j := newLength - 1

	// Iterate through the original string in reverse
	for i := length - 1; i >= 0; i-- {
		counter++
		result[j] = str[i]
		j--
		// Add a comma after every 3 digits (except at the start)
		if counter%3 == 0 && i != 0 {
			result[j] = ','
			j--
		}
	}

	return string(result)
}

func main() {
	num := 123456789
	formattedStr := int2str(num)
	fmt.Println(formattedStr) // Output: 123,456,789
}