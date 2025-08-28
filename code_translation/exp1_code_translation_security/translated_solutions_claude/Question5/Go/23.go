package main

import (
	"fmt"
	"strconv"
)

func intToString(x int) string {
	// Convert integer to string
	tempString := strconv.Itoa(x)
	
	// Handle edge cases
	if x == 0 {
		return "0"
	}
	
	var result string
	length := len(tempString)
	
	// Add commas every 3 digits from right to left
	for i := 0; i < length; i++ {
		// Add comma before every group of 3 digits (except at the beginning)
		if i > 0 && (length-i)%3 == 0 {
			result += ","
		}
		result += string(tempString[i])
	}
	
	return result
}

func main() {
	fmt.Print(intToString(100000))
	fmt.Print("test\n")
}