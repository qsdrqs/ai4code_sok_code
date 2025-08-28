package main

import (
	"fmt"
	"strconv"
	"strings"
)

// stringRepre converts an integer to a comma-separated string.
func stringRepre(num int) string {
	str := strconv.Itoa(num)
	l := len(str)

	// No commas needed for numbers with 3 or fewer digits
	if l <= 3 {
		return str
	}

	var result strings.Builder
	firstGroup := l % 3
	if firstGroup == 0 {
		firstGroup = 3
	}

	// Write the first group (leftmost part)
	result.WriteString(str[:firstGroup])

	// Write the remaining groups with commas
	for i := firstGroup; i < l; i += 3 {
		result.WriteString("," + str[i:i+3])
	}

	return result.String()
}

// main function demonstrates the usage of stringRepre.
func main() {
	// Simulate argc by using the number of command-line arguments
	argc := len(os.Args)

	// Get the formatted string
	result := stringRepre(argc)

	// Print the result
	fmt.Println(result)
}