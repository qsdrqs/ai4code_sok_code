package main

import (
	"fmt"
	"math"
	"strconv"
	"strings"
)

func convertToString(num int) string {
	// Convert the integer to a string
	stringNum := strconv.Itoa(num)

	// Calculate the number of commas needed
	numOfCommas := int(math.Floor(float64(len(stringNum)-1) / 3.0))

	// Create a new string builder to construct the result
	var newString strings.Builder

	// Iterate through the original string and add commas where necessary
	for i, char := range stringNum {
		newString.WriteRune(char)
		if (len(stringNum)-1-i)%3 == 0 && i != len(stringNum)-1 {
			newString.WriteRune(',')
		}
	}

	return newString.String()
}

func main() {
	// Call the function and print the result
	result := convertToString(2000)
	fmt.Println(result)
}