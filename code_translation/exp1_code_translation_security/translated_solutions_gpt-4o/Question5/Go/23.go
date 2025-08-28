package main

import (
	"fmt"
	"strconv"
	"strings"
)

func intToString(x int) string {
	// Convert the integer to a string
	tempString := strconv.Itoa(x)

	// Create a result string with commas
	var result strings.Builder

	// Iterate through the string in reverse to insert commas
	length := len(tempString)
	for i, char := range tempString {
		if (length-i)%3 == 0 && i != 0 {
			result.WriteString(",")
		}
		result.WriteRune(char)
	}

	return result.String()
}

func main() {
	// Test the intToString function
	fmt.Println(intToString(100000)) // Output: 100,000
	fmt.Println("test")
}