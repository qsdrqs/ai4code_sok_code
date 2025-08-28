package main

import (
	"strconv"
)

// int2str converts a signed integer to a string with commas as thousand separators.
// This function replicates the logic of the provided C function, including its
// potential bugs and inefficiencies.
func int2str(num int) string {
	// Convert the integer to its string representation
	numStr := strconv.Itoa(num)
	length := len(numStr)

	// Calculate the new length of the result string including commas
	var newl int
	if length%3 == 0 {
		newl = length + (length / 3) - 1
	} else {
		newl = length + (length / 3)
	}

	// Initialize the result byte slice with the correct size
	result := make([]byte, newl)

	// Initialize loop variables
	counter := 0
	j := newl - 1

	// Process each digit from the end of the string
	for i := len(numStr) - 1; i >= 0; i-- {
		counter++
		result[j] = numStr[i]

		// Insert a comma every 3 digits
		if counter%3 == 0 {
			result[j] = ','
			j--
		}
		j--
	}

	// Convert the byte slice to a string and return
	return string(result)
}