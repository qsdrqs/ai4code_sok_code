package main

import (
	"fmt"
	"strconv"
	"strings"
)

// Helper function to insert a comma into the string at the specified index
func insert(str string, index int) string {
	if index < 0 || index > len(str) {
		return str // Return the original string if the index is out of bounds
	}
	return str[:index] + "," + str[index:]
}

// Function to generate the string representation of a number with commas
func stringRepre(num int) string {
	strnum := strconv.Itoa(num) // Convert the number to a string
	length := len(strnum)       // Length of the original string

	// Calculate the number of commas to insert
	conum := (length - 1) / 3

	// Insert commas from right to left
	for i := 1; i <= conum; i++ {
		index := length - (3 * i)
		strnum = insert(strnum, index)
	}

	return strnum
}

func main() {
	// Example usage: Pass an integer to stringRepre
	num := 1234567890
	result := stringRepre(num)
	fmt.Println(result) // Output: "1,234,567,890"
}